package com.docker.handwrite.handbutterknife.lib_plan_reflection;

import android.app.Activity;

import com.docker.lib_annotation.BindView;

import java.lang.reflect.Field;

public class ReflectBinding {
    // 在库lib里面反射 有性能问题 不知道用户怎么设置 设置多少 读注解耗时
    public static void bind(Activity context) {
        for (Field declaredField : context.getClass().getDeclaredFields()) {
            //跨包访问时候 有权限问题 扩大权限
            declaredField.setAccessible(true);
            try {
                BindView annotation = declaredField.getAnnotation(BindView.class);
                if (annotation != null) {
                    declaredField.set(context, context.findViewById(annotation.value()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    // 所以使用AnnotationProcesser 编译时生成中间类 在bind的时候去调用中间类
    // 注解不需要运行时 影响的性能很小 可以忽略
}
