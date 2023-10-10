package com.docker.buildsrc;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.jetbrains.annotations.NotNull;

// https://blog.csdn.net/Poulfei/article/details/118568911 插件调试步骤
public class TestPlugin implements Plugin<Project> {
    @Override
    public void apply(@NotNull Project project) {
        System.out.println("插件挂载了，然后注册一个transform,transform里面做了埋点或稳定性插桩,");
        AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
        appExtension.registerTransform(new TestTransform());
        //gradle 8.0后会移除transform，改用transformAction https://mp.weixin.qq.com/s/-k3FpIa0dcjo5yahQ0xKgA
    }
}