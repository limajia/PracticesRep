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
        //这里确定的是处理class字节码插桩Transform

    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
        //这里确定的是全工程范围
    }

    @Override
    public boolean isIncremental() {
        return false;
    }
}

/*
*
1.应用多个 Transform：要应用多个 Transform，只需在相应的构建类型或产品风味中添加多个 transformClassesWith 或 transformResourcesWith 配置。例如：
gradle
Copy code
android {
    buildTypes {
        release {
            // 应用第一个自定义 Transform
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            transformClassesWith MyCustomTransform1
            // 应用第二个自定义 Transform
            transformClassesWith MyCustomTransform2
        }
    }
}
*

*2. 在以上buildTypes { release { 列出 构建过程，包括编译、资源处理、打包、签名和多个变体的多个Transform (AGP插件中默认有这些Transform)。
*
* 在 `buildTypes` 部分，你可以配置多个 Transform 以处理构建过程中的不同阶段。以下是一个示例，展示了如何配置多个 Transform，包括编译、资源处理、打包、签名和不同的构建变体：

```gradle
android {
    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'

            // 配置编译时的 Transform
            transformClassesWith MyCompileTimeTransform

            // 配置资源处理时的 Transform
            transformResourcesWith MyResourceTransform

            // 配置打包时的 Transform
            transformDexWith MyDexTransform
            transformNativeLibsWith MyNativeLibTransform

            // 配置签名
            signingConfig signingConfigs.release

            // 配置不同的构建变体
            productFlavors {
                flavor1 {
                    // 针对 flavor1 应用编译时的 Transform
                    transformClassesWith MyFlavor1Transform
                }
                flavor2 {
                    // 针对 flavor2 应用编译时的 Transform
                    transformClassesWith MyFlavor2Transform
                }
            }
        }
    }
}
```
在上述示例中，我们配置了不同阶段的多个 Transform，包括编译时的 `MyCompileTimeTransform`、资源处理时的 `MyResourceTransform`、打包时的 `MyDexTransform` 和 `MyNativeLibTransform`，以及不同构建变体（`flavor1` 和 `flavor2`）的独立的编译时 Transform。这允许你在构建过程的不同阶段应用自定义转换，以满足特定需求。
请注意，`MyCompileTimeTransform`、`MyResourceTransform`、`MyDexTransform`、`MyNativeLibTransform`、`MyFlavor1Transform` 和 `MyFlavor2Transform` 都是自定义 Transform 类，它们实现了相应的转换逻辑。你需要创建这些 Transform 类来执行所需的操作。每个 Transform 类负责在其指定的阶段执行相应的操作。
*
*
*
* 3.可以修改这些默认的Transform吗？（都是按照顺序加载的Transform）
* 是的，你可以修改或扩展默认的 Transform，以满足你的特定需求。Android Gradle 插件 (AGP) 提供了灵活的方式来应用自定义 Transform 或修改默认 Transform 的行为。以下是一些常见的方法：

1. **自定义编译时 Transform**：你可以创建一个自定义编译时 Transform，用于修改编译后的 Java 或 Kotlin 字节码。这可以用于实现代码混淆、插桩、性能优化等。要执行自定义编译时 Transform，你可以使用 `transformClassesWith` 或 `registerTransform` 来指定你的 Transform。

2. **自定义资源处理 Transform**：如果你需要自定义资源处理，例如在资源文件编译阶段添加额外的资源或对资源进行特殊处理，你可以创建一个自定义资源处理 Transform。使用 `transformResourcesWith` 或 `registerTransform` 配置你的 Transform。

3. **自定义字节码转换 Transform**：如果需要对字节码进行更复杂的转换，可以创建一个自定义字节码转换 Transform。这对于执行高级字节码操作非常有用。使用 `registerTransform` 配置字节码转换 Transform。

4. **自定义 DEX 转换 Transform**：如果需要对 DEX 文件进行自定义处理，你可以创建一个自定义 DEX 转换 Transform。这通常用于多模块应用程序中，以处理多个 DEX 文件的合并。使用 `registerTransform` 配置 DEX 转换 Transform。

5. **自定义 Native 库处理 Transform**：如果你需要对 Native 库文件进行特殊处理，例如在构建过程中生成或修改库文件，你可以通过使用 NDK 构建工具来扩展或修改 Native 库处理。

6. **自定义资源压缩和打包 Transform**：如果需要修改 APK 打包的行为，你可以通过创建自定义 Gradle Task 或修改 APK 打包配置来实现。

要应用自定义 Transform 或修改默认 Transform，你通常需要编写相应的 Java 或 Kotlin 代码，并在 Gradle 构建文件中指定 Transform 类和配置。具体的实现方式会根据你的需求和 Android 项目的特定情况而变化。

请注意，修改默认的 Transform 或添加自定义 Transform 时要小心，确保不会破坏应用的稳定性或导致不希望的行为。建议在开发和测试过程中充分测试自定义 Transform，以确保其与你的应用程序一起正常工作。

*
* 4.定义多个Transform，如何确定哪个是字节码转换 Transform和DEX 转换 Transform？
*
*Transform 类实现了 org.gradle.api.tasks.PathSensitivity 接口。
getInputTypes() 方法返回了 QualifiedContent.DefaultContentType.CLASSES 类型 或资源类型
* isIncremental() 方法通常返回 false，因为 DEX 转换通常是非增量的。
*
*
* 5.自定义字节码插桩为什么使用BuildSrc目录？
*在 Android Studio 工程的根目录中 , 创建 buildSrc 目录 , 该目录是特殊目录 , AS 自动为该目录引入了 Groovy / Java / Gradle 的 API 依赖。
* * */