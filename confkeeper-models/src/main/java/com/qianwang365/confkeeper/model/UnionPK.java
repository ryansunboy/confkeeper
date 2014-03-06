/**
 * UnionPK.java
 */
package com.qianwang365.confkeeper.model;

import java.io.Serializable;

/**
 * @author Yate
 * @date 2013年9月26日
 * @description TODO
 * @version 1.0
 */
public interface UnionPK extends Serializable {
	public interface UnionPKEntity<T extends UnionPK> {
		T createPK(String... objects);
	}
}
