package com.bbs.service;

import com.bbs.model.BlackList;

public interface BlackListBiz {
	public  void save(BlackList transientInstance);

	public  int getLevel(int userId);

	

	public void update(BlackList blackList);

	public void remove(int userId);

}
