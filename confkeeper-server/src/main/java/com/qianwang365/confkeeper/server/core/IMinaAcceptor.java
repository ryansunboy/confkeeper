/**
 * IMinaConnection.java
 */
package com.qianwang365.confkeeper.server.core;

import com.qianwang365.confkeeper.server.content.event.MessageListener;
import com.qianwang365.confkeeper.server.exception.KeyClashException;
import com.qianwang365.confkeeper.server.utils.Constants;
import com.qianwang365.confkeeper.server.utils.PropertyUtils;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description mina连接基础行为
 * @version 1.0
 */
public interface IMinaAcceptor<C> extends IServerAcceptor {
	public static class MinaStarterParam extends IServerAcceptor.StarterParam
			implements IStarterParam {
		protected long timeout;
		protected int bufferSize;
		protected int readIdle;
		protected int writeIdle;
		
		/**
		 * @param host
		 * @param port
		 */
		public MinaStarterParam(int port) {
			super(Constants.LOCALHOST, port);
			this.timeout = Long.parseLong(PropertyUtils.getInstance().getValue(
					"server.timeout", "10000"));
			this.bufferSize = Integer.parseInt(PropertyUtils.getInstance()
					.getValue("server.buffer", "2048"));
			this.readIdle = Integer.parseInt(PropertyUtils.getInstance()
					.getValue("server.readldle", "60"));
			this.writeIdle = Integer.parseInt(PropertyUtils.getInstance()
					.getValue("server.writeIdle", "60"));
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

		/**
		 * @return the bufferSize
		 */
		public final int getBufferSize() {
			return bufferSize;
		}

		/**
		 * @param bufferSize
		 *            the bufferSize to set
		 */
		public final void setBufferSize(int bufferSize) {
			this.bufferSize = bufferSize;
		}

		/**
		 * @return the readIdle
		 */
		public final int getReadIdle() {
			return readIdle;
		}

		/**
		 * @param readIdle
		 *            the readIdle to set
		 */
		public final void setReadIdle(int readIdle) {
			this.readIdle = readIdle;
		}

		/**
		 * @return the writeIdle
		 */
		public final int getWriteIdle() {
			return writeIdle;
		}

		/**
		 * @param writeIdle
		 *            the writeIdle to set
		 */
		public final void setWriteIdle(int writeIdle) {
			this.writeIdle = writeIdle;
		}

	}

	void regeditListener(String key, MessageListener<C> l)
			throws KeyClashException;

	void unregeditListener(String key);
}
