package com.bbs.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SubForum entity. @author MyEclipse Persistence Tools
 */

public class SubForum implements java.io.Serializable {

	// Fields

	private Integer id;
	private MainForum mainForum;
	private String title;
	private String info;
	private Set posts = new HashSet(0);

	// Constructors

	/** default constructor */
	public SubForum() {
	}

	/** minimal constructor */
	public SubForum(MainForum mainForum, String title) {
		this.mainForum = mainForum;
		this.title = title;
	}

	/** full constructor */
	public SubForum(MainForum mainForum, String title, String info, Set posts) {
		this.mainForum = mainForum;
		this.title = title;
		this.info = info;
		this.posts = posts;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MainForum getMainForum() {
		return this.mainForum;
	}

	public void setMainForum(MainForum mainForum) {
		this.mainForum = mainForum;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Set getPosts() {
		return this.posts;
	}

	public void setPosts(Set posts) {
		this.posts = posts;
	}

}