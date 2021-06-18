package com.docker.buildsrc;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.jetbrains.annotations.NotNull;

public class TestPlugin implements Plugin<Project> {
    @Override
    public void apply(@NotNull Project project) {
        System.out.println("插件打印出东西了");
//        try {
//            int a = 10;
//            int b = 0;
//            System.out.println("jieguo = "+ a/b);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //

        AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
        appExtension.registerTransform(new TestTransform());
    }
}