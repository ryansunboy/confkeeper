/**
 * IContentHandler.java
 */
package com.qianwang365.confkeeper.server.content.handle.impl;

/**
 * @author Yate
 * @date Dec 25, 2013
 * @description TODO
 * @version 1.0
 */
public interface IContentHandler<C,V> {
	V handleContent(C c);
}
