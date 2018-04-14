package com.bbs.serviceImpl;

import com.bbs.dao.SubForumDao;
import com.bbs.model.SubForum;
import com.bbs.service.SubForumBiz;

public class SubForumBizImpl implements SubForumBiz{
	private SubForumDao subDao;
	
	

	public void setSubDao(SubForumDao subDao) {
		this.subDao = subDao;
	}



	


	@Override
	public void add(SubForum transientInstance) {
		subDao.save(transientInstance);
		
	}
	

}
