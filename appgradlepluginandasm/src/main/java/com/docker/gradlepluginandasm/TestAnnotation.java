package com.docker.gradlepluginandasm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author docker
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface TestAnnotation {
    int age() default 0;

    String name() default "dockerInner";
}