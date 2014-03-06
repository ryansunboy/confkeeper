/**
 * APIConnectorFactory.java
 */
package com.qianwang365.confkeeper.server.manager.factory;

import org.apache.http.NameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.server.manager.IAPIConnector;
import com.qianwang365.confkeeper.server.manager.impl.APIConnector;

/**
 * @author Yate
 * @date Jan 2, 2014
 * @description TODO
 * @version 1.0
 */
public class APIConnectorFactory {

	public IAPIConnector<NameValuePair, JSONObject> getConnector() {
		return new APIConnector(false);
	}

	public IAPIConnector<NameValuePair, JSONObject> getConnector(
			boolean useProxy) {
		return new APIConnector(useProxy);
	}
}
