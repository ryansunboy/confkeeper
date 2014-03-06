/**
 * IAPIConnector.java
 */
package com.qianwang365.confkeeper.server.manager;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;

/**
 * @author Yate
 * @date Dec 31, 2013
 * @description TODO
 * @version 1.0
 */
public interface IAPIConnector<PV extends NameValuePair, V> {
	V invoke(APIConnType method, String targetUrl, String referUrl,
			List<NameValuePair> requestHeaders,
			List<NameValuePair> requestEntity, String requestEntityCharset)
			throws ParseException, IOException;
}
