package com.bbs.model;

import java.sql.Timestamp;

/**
 * BestPost entity. @author MyEclipse Persistence Tools
 */

public class BestPost implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Post post;
	private Timestamp applyDate;
	private Integer state;

	// Constructors

	/** default constructor */
	public BestPost() {
	}

	/** minimal constructor */
	public BestPost(User user, Post post, Timestamp applyDate) {
		this.user = user;
		this.post = post;
		this.applyDate = applyDate;
	}

	/** full constructor */
	public BestPost(User user, Post post, Timestamp applyDate, Integer state) {
		this.user = user;
		this.post = post;
		this.applyDate = applyDate;
		this.state = state;
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

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Timestamp getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Timestamp applyDate) {
		this.applyDate = applyDate;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}