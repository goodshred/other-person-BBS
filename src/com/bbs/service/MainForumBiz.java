package com.bbs.service;

import java.util.List;

import com.bbs.model.MainForum;

public interface MainForumBiz {
	public List<MainForum> getAllMainForums();

	public MainForum getMainForumById(int type);

	public boolean add(MainForum main);

}
