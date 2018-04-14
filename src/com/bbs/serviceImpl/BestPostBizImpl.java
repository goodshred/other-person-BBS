package com.bbs.serviceImpl;

import java.util.List;

import com.bbs.dao.BestPostDao;
import com.bbs.model.BestPost;
import com.bbs.service.BestPostBiz;

public class BestPostBizImpl implements BestPostBiz {
	
	private BestPostDao bestPostDao;
	
	
	

	

	public void setBestPostDao(BestPostDao bestPostDao) {
		this.bestPostDao = bestPostDao;
	}

	@Override
	public void insert(BestPost post) {
		bestPostDao.insert(post);
		
	}

	@Override
	public List<BestPost> getBestPosts(int pageInde, int pageSize) {
		return bestPostDao.getBestPosts(pageInde, pageSize);
	}

	@Override
	public void delete(int postId) {
		bestPostDao.delete(postId);
	}

	@Override
	public void updateState(Integer postId, int state) {
		bestPostDao.updateState(postId,state);
	}

	@Override
	public List<BestPost> getPostsByUserId(int userId,int pageIndex,int pageSize) {
		return bestPostDao.getRecordsByUserId(userId,pageIndex,pageSize);
		
	}

	@Override
	public boolean isExist(int postId) {
		return bestPostDao.isExist(postId);
		
	}

}
