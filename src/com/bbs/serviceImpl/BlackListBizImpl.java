package com.bbs.serviceImpl;

import com.bbs.dao.BlackListDao;
import com.bbs.model.BlackList;
import com.bbs.service.BlackListBiz;

public class BlackListBizImpl implements BlackListBiz {
	private BlackListDao blackListDao;
	

	

	

	public void setBlackListDao(BlackListDao blackListDao) {
		this.blackListDao = blackListDao;
	}

	@Override
	public void save(BlackList transientInstance) {
		blackListDao.save(transientInstance);
		
	}

	@Override
	public int getLevel(int userId) {
		return blackListDao.getLevel(userId);
	}

	

	@Override
	public void update(BlackList blackList) {
		blackListDao.update(blackList);
		
	}

	@Override
	public void remove(int userId) {
		blackListDao.remove(userId);		
	}

}
