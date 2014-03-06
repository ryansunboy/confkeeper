/**
 * ConfKeeperConnectorFactory.java
 */
package com.qianwang365.confkeeper.client.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.client.core.IMinaConnection;
import com.qianwang365.confkeeper.client.core.IMinaConnection.MinaConnParam;
import com.qianwang365.confkeeper.client.core.impl.MinaConnector;
import com.qianwang365.confkeeper.client.factory.IConnectorFactory;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description mina连接工厂
 * @version 1.0
 */
public class MinaConnectorFactory
		implements
		IConnectorFactory<IMinaConnection<JSONObject>, IMinaConnection.MinaConnParam> {

	private static MinaConnectorFactory sign_instance;

	private MinaConnectorFactory() {
	}

	public static MinaConnectorFactory getInstance() {
		if (sign_instance == null) {
			synchronized (MinaConnectorFactory.class) {
				if (sign_instance == null) {
					sign_instance = new MinaConnectorFactory();
				}
			}
		}
		return sign_instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.client.base.IConnectorFactory#getProduct(com
	 * .qianwang365.confkeeper.client.base.IClientConnector.ConnectionParam)
	 */
	@Override
	public IMinaConnection<JSONObject> getConnector(MinaConnParam p) {
		return new MinaConnector<MinaConnParam>(p);
	}

}
