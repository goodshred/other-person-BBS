package com.bbs.model;

import java.sql.Timestamp;

/**
 * Followcard entity. @author MyEclipse Persistence Tools
 */

public class Followcard implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Post post;
	private String followContent;
	private Timestamp followDate;

	// Constructors

	/** default constructor */
	public Followcard() {
	}

	/** minimal constructor */
	public Followcard(User user, Post post, Timestamp followDate) {
		this.user = user;
		this.post = post;
		this.followDate = followDate;
	}

	/** full constructor */
	public Followcard(User user, Post post, String followContent,
			Timestamp followDate) {
		this.user = user;
		this.post = post;
		this.followContent = followContent;
		this.followDate = followDate;
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

	public String getFollowContent() {
		return this.followContent;
	}

	public void setFollowContent(String followContent) {
		this.followContent = followContent;
	}

	public Timestamp getFollowDate() {
		return this.followDate;
	}

	public void setFollowDate(Timestamp followDate) {
		this.followDate = followDate;
	}
	public String getTime(){
		String time = getFollowDate().toString();
		int index = time.lastIndexOf('.');
		if (index != -1)
		return time.substring(0,index);
		else 
			return time.toString();
	}

}