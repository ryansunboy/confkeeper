/**
 * EndPointEntity.java
 */
package com.qianwang365.confkeeper.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.type.Alias;

/**
 * @author Yate
 * @date Dec 11, 2013
 * @description TODO
 * @version 1.0
 */
@Alias(value = "EndPointEntity")
public class EndPointEntity implements Serializable {

	public static enum EpState {
		ON(1), OFF(0);

		int var = 0;

		/**
		 * @param state
		 */
		private EpState(int state) {
			this.var = state;
		}

	}

	private Long id;
	private String trunk;
	private Integer state;

	private AppEntity app = new AppEntity();

	private List<EndParamEntity> params = Collections.emptyList();

	public EndPointEntity() {
	}

	/**
	 * @param id
	 */
	public EndPointEntity(Long id) {
		super();
		this.id = id;
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
	 * @return the trunk
	 */
	public final String getTrunk() {
		return trunk;
	}

	/**
	 * @param trunk
	 *            the trunk to set
	 */
	public final void setTrunk(String trunk) {
		this.trunk = trunk;
	}

	/**
	 * @return the state
	 */
	public final Integer getState() {
		return state;
	}

	public final void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the app
	 */
	public final AppEntity getApp() {
		return app;
	}

	/**
	 * @param app
	 *            the app to set
	 */
	public final void setApp(AppEntity app) {
		this.app = app;
	}

	/**
	 * @return the params
	 */
	public final List<EndParamEntity> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public final void setParams(List<EndParamEntity> params) {
		this.params = params;
	}

}
