/**
 * EndParamEntity.java
 */
package com.qianwang365.confkeeper.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * @author Yate
 * @date Dec 11, 2013
 * @description TODO
 * @version 1.0
 */
@Alias(value = "EndParamEntity")
public class EndParamEntity implements Serializable {

	public enum ParamType {
		STATIC(0), DYNAMIC(1);
		final int type;

		private ParamType(int type) {
			this.type = type;
		}

		public final int getType() {
			return type;
		}
	}

	private Long id;
	private Integer type;
	private String key;
	private String value;

	private EndPointEntity endpoint;

	/**
	 * 
	 */
	public EndParamEntity() {
	}

	/**
	 * @param type
	 * @param key
	 * @param value
	 * @param endpoint
	 */
	public EndParamEntity(Integer type, String key, String value,
			EndPointEntity endpoint) {
		super();
		this.type = type;
		this.key = key;
		this.value = value;
		this.endpoint = endpoint;
	}

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the key
	 */
	public final String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public final void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public final void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the endpoint
	 */
	public final EndPointEntity getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint
	 *            the endpoint to set
	 */
	public final void setEndpoint(EndPointEntity endpoint) {
		this.endpoint = endpoint;
	}

	public final Integer getType() {
		return type;
	}

	public final void setType(Integer type) {
		this.type = type;
	}

}
