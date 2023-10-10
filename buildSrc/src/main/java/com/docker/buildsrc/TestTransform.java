package com.docker.buildsrc;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.utils.FileUtils;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

public class TestTransform extends Transform {

    @Override
    public void transform(TransformInvocation transformInvocation)
            throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        //
        //当前是否是增量编译(由isIncremental() 方法的返回和当前编译是否有增量基础)
        boolean isIncremental = transformInvocation.isIncremental();
        //消费型输入，可以从中获取jar包和class文件夹路径。需要输出给下一个任务
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        //OutputProvider管理输出路径，如果消费型输入为空，你会发现OutputProvider == null
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();

        for (TransformInput input : inputs) {
            //Failed resolution of: Landroidx/appcompat/R$drawable;(不遍历处理的话会出现这个bug)
            for (JarInput jarInput : input.getJarInputs()) {
                File dest = outputProvider.getContentLocation(
                        jarInput.getFile().getAbsolutePath(),
                        jarInput.getContentTypes(),
                        jarInput.getScopes(),
                        Format.JAR);
                //将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
                FileUtils.copyFile(jarInput.getFile(), dest);
            }

            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {
//                FileUtils.copyDirectory(
//                        directoryInput.getFile(),
//                        outputProvider.getContentLocation(directoryInput.getName(), getInputTypes(), getScopes(), Format.DIRECTORY));

                File dest = outputProvider.getContentLocation(directoryInput.getName(),
                        directoryInput.getContentTypes(), directoryInput.getScopes(),
                        Format.DIRECTORY);
                // 插桩
                replaceFileClass(directoryInput.getFile());
                //将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了, 此目的就是把我们修改之后的文件按照android编译要求放置到本来该放置的位置，以助于apk打包。
                FileUtils.copyDirectory(directoryInput.getFile(), dest);
                System.out.println("docker  from: " + directoryInput.getFile().getAbsolutePath());
                System.out.println("docker  dest: " + dest.getAbsolutePath());
            }
        }
    }

    /**
     * 获取指定文件夹下面的.class文件
     *
     * @param replaceFile 替换文件夹的路径
     */
    private void replaceFileClass(File replaceFile) {
        File[] subFile = replaceFile.listFiles();
        assert subFile != null;
        for (File file : subFile) {
            // 判断是否为文件夹
            if (!file.isDirectory()) {
                hook(file.getAbsolutePath());//这里处理文件
            } else {
                replaceFileClass(file);//这里递归处理目录
            }
        }
    }

    /**
     * 替换文件，改变文件
     *
     * @param replaceFileAbsPath
     */
    private void hook(String replaceFileAbsPath) {
        System.out.println("replaceFileAbsPath = " + replaceFileAbsPath);
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        ClassReader classReader = null;
        try {
            inputStream = new FileInputStream(replaceFileAbsPath);
            classReader = new ClassReader(inputStream);

            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            TestClassVisitor classVisitor = new TestClassVisitor(classWriter);

            classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);

            outputStream = new FileOutputStream(replaceFileAbsPath);
            outputStream.write(classWriter.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert outputStream != null;
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Transform的名称  Task :app:transformClassesWithRandomNameOfMljForDebug
    @Override
    public String getName() {
        return " RandomNameOfMlj";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }
}
