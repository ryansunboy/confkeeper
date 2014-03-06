/**
 * IClientConnector.java
 */
package com.qianwang365.confkeeper.client.core;

import java.net.InetSocketAddress;

import com.qianwang365.confkeeper.client.conf.IClientConfig;
import com.qianwang365.confkeeper.client.conf.IClientConfig.Formatter;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description 客户端连接基础行为
 * @version 1.0
 */
public interface IClientConnector {
	public static interface IConnectionParam {
		InetSocketAddress getServerAddress();
	}

	public static class ConnectionParam implements IConnectionParam {
		protected final String host;
		protected final int port;

		/**
		 * @param host
		 * @param port
		 */
		public ConnectionParam(String host, int port) {
			super();
			this.host = host;
			this.port = port;
		}

		/**
		 * @return the host
		 */
		public final String getHost() {
			return host;
		}

		/**
		 * @return the port
		 */
		public final int getPort() {
			return port;
		}

		public final InetSocketAddress getServerAddress() {
			return new InetSocketAddress(this.host, this.port);
		}
	}

	void init();

	<F extends Formatter> void connection(IClientConfig<F> conf);

	void disconnection();

	ConnectionParam getConnParam();
}
