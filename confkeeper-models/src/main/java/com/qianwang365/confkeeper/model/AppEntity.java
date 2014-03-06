/**
 * AppEntity.java
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
@Alias(value = "AppEntity")
public class AppEntity implements Serializable {

	private Long id;
	private String appName;
	private String appNote;

	private List<EndPointEntity> versions = Collections.emptyList();

	/**
	 * 
	 */
	public AppEntity() {
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
	public final AppEntity setId(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the appName
	 */
	public final String getAppName() {
		return appName;
	}

	/**
	 * @param appName
	 *            the appName to set
	 */
	public final AppEntity setAppName(String appName) {
		this.appName = appName;
		return this;
	}

	/**
	 * @return the appNote
	 */
	public final String getAppNote() {
		return appNote;
	}

	/**
	 * @param appNote
	 *            the appNote to set
	 */
	public final AppEntity setAppNote(String appNote) {
		this.appNote = appNote;
		return this;
	}

	/**
	 * @return the versions
	 */
	public final List<EndPointEntity> getVersions() {
		return versions;
	}

	/**
	 * @param versions
	 *            the versions to set
	 */
	public final void setVersions(List<EndPointEntity> versions) {
		this.versions = versions;
	}

}
