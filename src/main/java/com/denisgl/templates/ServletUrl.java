package com.denisgl.templates;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * If servlet url and and name of view are the same 
 * then this annotaion will be used in View.enum as marker 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ServletUrl {

}
