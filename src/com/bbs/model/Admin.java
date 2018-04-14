package com.bbs.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String password;
	private String photoUrl;
	private String email;
	private Integer type;
	private String sex;
	private Set notices = new HashSet(0);

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(String userName, String password, String email, Integer type) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.type = type;
	}

	/** full constructor */
	public Admin(String userName, String password, String photoUrl,
			String email, Integer type, String sex, Set notices) {
		this.userName = userName;
		this.password = password;
		this.photoUrl = photoUrl;
		this.email = email;
		this.type = type;
		this.sex = sex;
		this.notices = notices;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Set getNotices() {
		return this.notices;
	}

	public void setNotices(Set notices) {
		this.notices = notices;
	}

	

}