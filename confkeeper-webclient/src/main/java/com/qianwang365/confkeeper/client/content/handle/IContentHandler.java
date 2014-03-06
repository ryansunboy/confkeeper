/**
 * IContentHandler.java
 */
package com.qianwang365.confkeeper.client.content.handle;

import com.qianwang365.confkeeper.client.exception.FailException;

/**
 * @author Yate
 * @date Dec 25, 2013
 * @description TODO
 * @version 1.0
 */
public interface IContentHandler<C> {
	void handleContent(C c) throws FailException;
}
