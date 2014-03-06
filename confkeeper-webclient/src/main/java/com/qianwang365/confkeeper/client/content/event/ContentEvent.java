/**
 * EventSource.java
 */
package com.qianwang365.confkeeper.client.content.event;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description event
 * @version 1.0
 */
public class ContentEvent<C> {
	protected final ContentEventType type;
	protected final C content;

	/**
	 * @param type
	 */
	public ContentEvent(ContentEventType type, C c) {
		this.type = type;
		this.content = c;
	}

	/**
	 * @return the type
	 */
	public final ContentEventType getType() {
		return type;
	}

	/**
	 * @return the content
	 */
	public final C getContent() {
		return content;
	}

}
