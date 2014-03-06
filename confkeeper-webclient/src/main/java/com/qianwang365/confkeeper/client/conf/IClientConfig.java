/**
 * IClientConfig.java
 */
package com.qianwang365.confkeeper.client.conf;

import com.qianwang365.confkeeper.client.conf.IClientConfig.Formatter;


/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
public interface IClientConfig<F extends Formatter> {
	public static interface Formatter {
		String format();
	}

	F getFormatter();
}
