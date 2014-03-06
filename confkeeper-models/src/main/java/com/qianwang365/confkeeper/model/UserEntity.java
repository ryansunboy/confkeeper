/**
 * UserEntity.java
 */
package com.qianwang365.confkeeper.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Yate
 * @date Dec 11, 2013
 * @description TODO
 * @version 1.0
 */
@Alias(value = "UserEntity")
public class UserEntity implements Serializable {

	private Long id;
	private String userName;
	private transient String passwd;
	private Date creationTime = new Date();

	/**
	 * 
	 */
	public UserEntity() {
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
	 * @return the userName
	 */
	public final String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public final void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the passwd
	 */
	public final String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 *            the passwd to set
	 */
	public final void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return the creationTime
	 */
	public final Date getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime
	 *            the creationTime to set
	 */
	public final void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

}
