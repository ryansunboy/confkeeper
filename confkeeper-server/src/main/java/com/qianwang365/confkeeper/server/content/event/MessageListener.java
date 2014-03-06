/**
 * ContentListener.java
 */
package com.qianwang365.confkeeper.server.content.event;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description listener
 * @version 1.0
 */
public interface MessageListener<C> {

	void headleEvent(MessageEvent<C> event);
}
