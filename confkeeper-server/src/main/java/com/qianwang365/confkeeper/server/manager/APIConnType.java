/**
 * APIConnType.java
 */
package com.qianwang365.confkeeper.server.manager;

/**
 * @author Yate
 * @date Dec 31, 2013
 * @description TODO
 * @version 1.0
 */
public enum APIConnType {
	GET(0), POST(1);
	final int value;

	private APIConnType(int value) {
		this.value = value;
	}

}
