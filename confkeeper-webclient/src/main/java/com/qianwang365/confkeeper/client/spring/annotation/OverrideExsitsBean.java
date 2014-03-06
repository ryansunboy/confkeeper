/**
 * OverrideExsitsBean.java
 */
package com.qianwang365.confkeeper.client.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Yate
 * @date Dec 25, 2013
 * @description TODO
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OverrideExsitsBean {
	Class<?> override();

	int value() default 0;
}
