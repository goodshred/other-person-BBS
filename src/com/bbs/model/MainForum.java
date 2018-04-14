package com.bbs.model;

import java.util.HashSet;
import java.util.Set;

/**
 * MainForum entity. @author MyEclipse Persistence Tools
 */

public class MainForum implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String info;
	private Set subForums = new HashSet(0);

	// Constructors

	/** default constructor */
	public MainForum() {
	}

	/** minimal constructor */
	public MainForum(String title) {
		this.title = title;
	}

	/** full constructor */
	public MainForum(String title, String info, Set subForums) {
		this.title = title;
		this.info = info;
		this.subForums = subForums;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set getSubForums() {
		return this.subForums;
	}

	public void setSubForums(Set subForums) {
		this.subForums = subForums;
	}

}