/**
 * ContentListener.java
 */
package com.qianwang365.confkeeper.client.content.event;

import com.qianwang365.confkeeper.client.exception.FailException;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description listener
 * @version 1.0
 */
public interface ContentListener<C> {

	void headleEvent(ContentEvent<C> event) throws FailException;
}
