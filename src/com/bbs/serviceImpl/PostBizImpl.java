package com.bbs.serviceImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.bbs.dao.MainForumDao;
import com.bbs.dao.PostDao;
import com.bbs.model.Followcard;
import com.bbs.model.MainForum;
import com.bbs.model.Post;
import com.bbs.model.SubForum;
import com.bbs.service.PostBiz;


/**
 * 
 * @author 张建浩、卜凡、卢静、余莎、姚文娜
 * @version 1.0
 * 2016年3月23日上午11:32:57
 */
public class PostBizImpl implements PostBiz {
	private PostDao postDao;
	



	/* (non-Javadoc)
	 * @see com.bbs.bizImpl.PostBiz#setPostDao(com.bbs.dao.PostDao)
	 */
	@Override
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	
	/* (non-Javadoc)
	 * @see com.bbs.bizImpl.PostBiz#pushlish(com.bbs.model.Post)
	 */
	@Override
	public void pushlish(Post post){
		postDao.pushlish(post);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.bbs.bizImpl.PostBiz#getBestPosts(int, int)
	 */
	@Override
	public List<Post> getBestPosts(int pageIndex,int pageSize){
		return postDao.getBestPosts(pageIndex, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.bbs.bizImpl.PostBiz#getLatestPosts(int, int)
	 */
	@Override
	public List<Post> getLatestPosts(int pageIndex,int pageSize ){
		return postDao.getLatestPosts(pageIndex, pageSize);
	}
	
	public Post getPostById(int postId){
		return postDao.getPostById(postId);
	}

	@Override
	public List<Followcard> getFollowCards(int postId, int pageIndex,
			int pageSize) {
		return postDao.getFollowCards(postId, pageIndex, pageSize);
	}
	
	public List<Post> searchPosts(String keyword){
		return postDao.search(keyword);
		
	}
	public List<Post> getPostByType(int type,int pageIndex,int pageSize){
		return postDao.getPostByType(type, pageIndex, pageSize);
	}

	@Override
	public List<Post> getPostByUserId(int userId,int pageIndex,int pageSize){
		return postDao.getPostByUserId(userId,pageIndex,pageSize);
	}

	@Override
	public void autoIncreaseReply(int postId) {
		postDao.autoIncreaseReply(postId);
		
	}

	@Override
	public void delete(int postId) {
		postDao.delete(postId);
		
	}

	@Override
	public void updateType(Integer postId) {
		postDao.updateType(postId);
		
	}

	@Override
	public void updatePost(int postId, String title, String content,
			int mainForum, int subForum) {
		
		Post post = postDao.getPostById(postId);
		post.setTitle(title);
		post.setCardContent(content);
		post.setSendDate(new Timestamp(System.currentTimeMillis()));
		MainForum mainForum2 = new MainForum();
		mainForum2.setId(mainForum);
		SubForum sub = new SubForum();
		sub.setMainForum(mainForum2);
		sub.setId(subForum);
		post.setSubForum(sub);
		postDao.updatePost(post);
		
		
	}
	@Override
	public void autoIncreaseViewNum(int postId){
		postDao.autoIncreaseViewNum(postId);
	}

	@Override
	public List<Post> getHotPosts(int pageIndex, int pageSize) {
		return postDao.getHotPosts(pageIndex, pageSize);
	}

	
	
	
	

}
