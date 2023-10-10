package com.docker.lib_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author docker
 */
@Retention(RetentionPolicy.RUNTIME) //在运行时使用 则需要这个 AnnotationProcess改为source
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface BindView {
    int value() default 0;

//    int id(); // 赋值属性 方法的样子
//
//    String name() default "docker";
}
