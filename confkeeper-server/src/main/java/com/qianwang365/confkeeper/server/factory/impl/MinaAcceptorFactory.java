/**
 * ConfKeeperConnectorFactory.java
 */
package com.qianwang365.confkeeper.server.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.server.core.IMinaAcceptor;
import com.qianwang365.confkeeper.server.core.IMinaAcceptor.MinaStarterParam;
import com.qianwang365.confkeeper.server.core.impl.MinaAcceptor;
import com.qianwang365.confkeeper.server.factory.IAcceptorFactory;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description mina连接工厂
 * @version 1.0
 */
public class MinaAcceptorFactory
		implements
		IAcceptorFactory<IMinaAcceptor<JSONObject>, IMinaAcceptor.MinaStarterParam> {

	private static MinaAcceptorFactory sign_instance;

	private MinaAcceptorFactory() {
	}

	public static MinaAcceptorFactory getInstance() {
		if (sign_instance == null) {
			synchronized (MinaAcceptorFactory.class) {
				if (sign_instance == null) {
					sign_instance = new MinaAcceptorFactory();
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
	public IMinaAcceptor<JSONObject> getConnector(MinaStarterParam p) {
		return new MinaAcceptor<MinaStarterParam>(p);
	}

}
