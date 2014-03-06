/**
 * IConnectorFactory.java
 */
package com.qianwang365.confkeeper.client.factory;

import com.qianwang365.confkeeper.client.core.IClientConnector;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
public interface IConnectorFactory<T extends IClientConnector, P extends IClientConnector.ConnectionParam> {
	T getConnector(final P p);
}
