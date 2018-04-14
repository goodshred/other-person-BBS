package com.bbs.service;

import java.util.List;

import com.bbs.model.BestPost;

public interface BestPostBiz {
	
	public void insert(BestPost post);
	public List<BestPost> getBestPosts(int pageInde,int pageSize);
	public void delete(int postId);
	public void updateState(Integer postId, int state);
	public List<BestPost> getPostsByUserId(int userId,int pageIndex,int pageSize);
	public boolean isExist(int postId);

}
