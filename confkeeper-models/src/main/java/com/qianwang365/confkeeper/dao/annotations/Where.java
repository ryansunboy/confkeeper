/**
 * Where.java
 */
package com.qianwang365.confkeeper.dao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Yate
 * @date 2013年10月15日
 * @description 用于解决复杂WHERE
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Where {
	String toSQL() default "toSQL";
}
