package com.docker.lib_processor;

import com.docker.lib_annotation.BindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

// https://www.jianshu.com/p/4c0fbe5b27a2/
// javaPoet其实就是对apt的封装，生成新的文件
// 若使用autoserver库，其作用是自动生成meta-info数据
public class BindingProcessor extends AbstractProcessor {
    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnvironment) {
        //test//
        System.out.println("=========开始=============");
        Set<? extends Element> rootElements = roundEnvironment.getRootElements();
        for (Element rootElement : rootElements) {
            System.out.println(rootElement.toString());
        }
        System.out.println("======================");
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        for (Element element : elementsAnnotatedWith) {
            System.out.println(element.toString());
        }
        System.out.println("=========结束=============");
        //test//
        for (Element element : roundEnvironment.getRootElements()) {
            String packageStr = element.getEnclosingElement().toString();
            String classStr = element.getSimpleName().toString();
            // 引用第三方库 生成文件
            ClassName className = ClassName.get(packageStr, classStr + "Binding");
            MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(ClassName.get(packageStr, classStr), "activity");
            boolean hasBinding = false;

            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.FIELD) {
                    BindView bindView = enclosedElement.getAnnotation(BindView.class);
                    if (bindView != null) {
                        hasBinding = true;
                        constructorBuilder.addStatement("activity.$N = activity.findViewById($L)",
                                enclosedElement.getSimpleName(), bindView.value());
                    }
                }
            }

            TypeSpec builtClass = TypeSpec.classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(constructorBuilder.build())
                    .build();

            if (hasBinding) {
                try {
                    JavaFile.builder(packageStr, builtClass)
                            .build().writeTo(filer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    /*
    =========开始=============
    com.docker.handwrite.handglide.MD5
    com.docker.handwrite.handglide.diapatcher.BitmapDispatcher
    com.docker.handwrite.handglide.GlideActivity
    com.docker.handwrite.handglide.cache.DoubleLruCache
    com.docker.handwrite.handglide.cache.BitmapCache
    com.docker.handwrite.handglide.cache.MemoryLruCache
    com.docker.handwrite.handglide.cache.DiskBitmapCache
    com.docker.handwrite.handglide.cache.disk.DiskLruCache
    com.docker.handwrite.handglide.cache.disk.Util
    com.docker.handwrite.handglide.cache.disk.StrictLineReader
    com.docker.handwrite.handglide.manager.RequestManager
    com.docker.handwrite.handglide.request.BitmapRequest
    com.docker.handwrite.handglide.request.RequestListener
    com.docker.handwrite.handglide.Glide
    com.docker.handwrite.handeventbus.core.DoSubscribe
    com.docker.handwrite.handeventbus.core.SubscribleMethod
    com.docker.handwrite.handeventbus.core.DoEventBus
    com.docker.handwrite.handeventbus.core.DoThreadMode
    com.docker.handwrite.handeventbus.EventBusActivity
    com.docker.handwrite.handbutterknife.lib_reflection.ReflectBinding
    com.docker.handwrite.handbutterknife.lib_processor.sample.MainActivityBind
    com.docker.handwrite.handbutterknife.lib_processor.AptBinding
    com.docker.handwrite.handbutterknife.lib_processor.src.BindingProcessor
    com.docker.handwrite.handbutterknife.TestButterKnifeMainActivity
    com.docker.handwrite.MainActivity
    com.docker.handwrite.BuildConfig
            ======================
    com.docker.handwrite.handbutterknife.TestButterKnifeMainActivity
            mTextView
            =========结束=============
    警告: No SupportedSourceVersion annotation found on com.docker.lib_processor.BindingProcessor, returning RELEASE_6.
    警告: 来自注释处理程序 'org.gradle.api.internal.tasks.compile.processing.TimeTrackingProcessor' 的受支持 source 版本 'RELEASE_6' 低于 -source '1.7'
            =========开始=============
    com.docker.handwrite.handbutterknife.TestButterKnifeMainActivityBinding
            ======================
            =========结束=============
            =========开始=============
            ======================
            =========结束=============
    */

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(BindView.class.getCanonicalName());
    }
}