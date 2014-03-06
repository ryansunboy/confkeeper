/**
 * IClientConfig.java
 */
package com.qianwang365.confkeeper.server.conf;

import com.qianwang365.confkeeper.server.conf.IServerConfig.Formatter;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
public interface IServerConfig<F extends Formatter> {
	public static interface Formatter {
		String format();
	}

	F getFormatter();
}
