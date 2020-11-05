package net.binis.codegen.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@CodeAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface CodePrototype {

    String name() default "";
    boolean generateConstructor() default true;
    boolean generateInterface() default true;
    boolean generateModifier() default false;
    boolean base() default false;
    Class<?> baseModifierClass() default void.class;
    Class<?> creatorClass() default void.class;
    boolean creatorModifier() default false;
    Class<?> mixInClass() default void.class;

}
