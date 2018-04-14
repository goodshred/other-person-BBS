package com.bbs.dao;

import java.util.List;

import com.bbs.model.MainForum;

public interface MainForumDao {

	public abstract void save(MainForum transientInstance);

	public abstract void delete(MainForum persistentInstance);

	public abstract MainForum findById(java.lang.Integer id);


	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByTitle(Object title);

	public abstract List findByInfo(Object info);

	public abstract List<MainForum> findAll();




	

}