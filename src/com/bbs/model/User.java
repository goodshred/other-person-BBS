package com.bbs.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String sex;
	private String photoUrl;
	private String email;
	private Integer type;
	private Timestamp registerDate;
	private String signature;
	private Integer level;
	private String activeCode;
	private Integer hasActive;
	private Set bestPosts = new HashSet(0);
	private Set posts = new HashSet(0);
	private Set blackLists = new HashSet(0);
	private Set followcards = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, String email, Integer type,
			Timestamp registerDate, Integer level) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.type = type;
		this.registerDate = registerDate;
		this.level = level;
	}

	/** full constructor */
	public User(String username, String password, String sex, String photoUrl,
			String email, Integer type, Timestamp registerDate,
			String signature, Integer level, String activeCode,
			Integer hasActive, Set bestPosts, Set posts, Set blackLists,
			Set followcards) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.photoUrl = photoUrl;
		this.email = email;
		this.type = type;
		this.registerDate = registerDate;
		this.signature = signature;
		this.level = level;
		this.activeCode = activeCode;
		this.hasActive = hasActive;
		this.bestPosts = bestPosts;
		this.posts = posts;
		this.blackLists = blackLists;
		this.followcards = followcards;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public Timestamp getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getActiveCode() {
		return this.activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public Integer getHasActive() {
		return this.hasActive;
	}

	public void setHasActive(Integer hasActive) {
		this.hasActive = hasActive;
	}

	public Set getBestPosts() {
		return this.bestPosts;
	}

	public void setBestPosts(Set bestPosts) {
		this.bestPosts = bestPosts;
	}

	public Set getPosts() {
		return this.posts;
	}

	public void setPosts(Set posts) {
		this.posts = posts;
	}

	public Set getBlackLists() {
		return this.blackLists;
	}

	public void setBlackLists(Set blackLists) {
		this.blackLists = blackLists;
	}

	public Set getFollowcards() {
		return this.followcards;
	}

	public void setFollowcards(Set followcards) {
		this.followcards = followcards;
	}

}