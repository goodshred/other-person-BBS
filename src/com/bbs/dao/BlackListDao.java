package com.bbs.dao;

import com.bbs.model.BlackList;

public interface BlackListDao {

	public abstract void save(BlackList transientInstance);

	public abstract int getLevel(int userId);

	public abstract void update(BlackList blackList);

	public abstract void remove(int userId);

}