package com.bbs.model;

/**
 * BlackList entity. @author MyEclipse Persistence Tools
 */

public class BlackList implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Integer level;

	// Constructors

	/** default constructor */
	public BlackList() {
	}

	/** minimal constructor */
	public BlackList(User user) {
		this.user = user;
	}

	/** full constructor */
	public BlackList(User user, Integer level) {
		this.user = user;
		this.level = level;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}