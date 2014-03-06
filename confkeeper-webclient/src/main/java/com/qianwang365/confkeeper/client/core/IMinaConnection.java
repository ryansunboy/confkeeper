/**
 * IMinaConnection.java
 */
package com.qianwang365.confkeeper.client.core;

import com.qianwang365.confkeeper.client.content.event.ContentListener;
import com.qianwang365.confkeeper.client.exception.KeyClashException;
import com.qianwang365.confkeeper.client.utils.Constants;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description mina连接基础行为
 * @version 1.0
 */
public interface IMinaConnection<C> extends IClientConnector {
	public static class MinaConnParam extends IClientConnector.ConnectionParam
			implements IConnectionParam {
		protected long timeout;

		/**
		 * @param host
		 * @param port
		 */
		public MinaConnParam(String host, int port) {
			this(host, port, Constants.DEFAULT_TIMEOUT);
		}

		/**
		 * @param host
		 * @param port
		 * @param timeout
		 */
		public MinaConnParam(String host, int port, long timeout) {
			super(host, port);
			this.timeout = timeout;
		}

		/**
		 * @return the timeout
		 */
		public final long getTimeout() {
			return timeout;
		}

		/**
		 * @param timeout
		 *            the timeout to set
		 */
		public final void setTimeout(long timeout) {
			this.timeout = timeout;
		}
	}

	void regeditListener(String key, ContentListener<C> l)
			throws KeyClashException;

	void unregeditListener(String key);
}
