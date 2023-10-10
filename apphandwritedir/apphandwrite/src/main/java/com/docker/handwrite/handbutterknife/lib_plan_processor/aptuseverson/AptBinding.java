package com.docker.handwrite.handbutterknife.lib_plan_processor.aptuseverson;

import android.app.Activity;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AptBinding {
    public static void bind(Activity context) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName(context.getClass().getCanonicalName() + "Binding");
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(context.getClass());//参数类型
            declaredConstructor.newInstance(context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


//        for (Field declaredField : context.getClass().getDeclaredFields()) {
//            //跨包访问时候 有权限问题 扩大权限
//            declaredField.setAccessible(true);
//            try {
//                BindView annotation = declaredField.getAnnotation(BindView.class);
//                if (annotation != null) {
//                    declaredField.set(context, context.findViewById(annotation.value()));
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
