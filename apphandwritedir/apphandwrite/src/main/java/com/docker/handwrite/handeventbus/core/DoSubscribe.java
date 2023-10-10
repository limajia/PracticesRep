package com.docker.handwrite.handeventbus.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoSubscribe {
    DoThreadMode threadMode() default DoThreadMode.POSTING;
}
