/**
 * IConnectorFactory.java
 */
package com.qianwang365.confkeeper.server.factory;

import com.qianwang365.confkeeper.server.core.IServerAcceptor;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
public interface IAcceptorFactory<T extends IServerAcceptor, P extends IServerAcceptor.StarterParam> {
	T getConnector(final P p);
}
