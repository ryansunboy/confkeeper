/**
 * IClientConnector.java
 */
package com.qianwang365.confkeeper.server.core;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.qianwang365.confkeeper.server.utils.Constants;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description 客户端连接基础行为
 * @version 1.0
 */
public interface IServerAcceptor {
	public static interface IStarterParam {
		InetSocketAddress getServerAddress();
	}

	public static class StarterParam implements IStarterParam {
		protected final String host;
		protected final int port;

		public StarterParam(int port) {
			this.host = Constants.LOCALHOST;
			this.port = port;
		}

		public StarterParam(String host, int port) {
			super();
			this.host = host;
			this.port = port;
		}

		/**
		 * @return the port
		 */
		public final int getPort() {
			return port;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.qianwang365.confkeeper.server.core.IServerAcceptor.IStarterParam
		 * #getServerAddress()
		 */
		@Override
		public final InetSocketAddress getServerAddress() {
			return new InetSocketAddress(this.host, this.port);
		}
	}

	void start() throws IOException;

	void shutdown();

	void reset() throws IOException;

	StarterParam getStarterParam();
}
