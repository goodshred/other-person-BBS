/**
 * 
 */
package com.bbs.dao;

import java.util.List;
import java.util.Set;

import com.bbs.model.Followcard;
import com.bbs.model.Post;

/**
 * 帖子的数据库访问接口
 * @author 张建浩、卜凡、卢静、余莎、姚文娜
 * @version 1.0
 * 2016年3月16日下午1:35:35
 */
public interface PostDao {
	
	/**
	 * 向帖子表中增加一条记录
	 * @param post 帖子
	 */
	public void pushlish(Post post);
	
	/**
	 * 获取指定帖子的回复贴
	 * @param postId 帖子id
	 * @return 回复贴列表
	 */
//	public Set<Followcard> getFollowCards(int postId);
	
	/**
	 * 获取论坛精华帖
	 * @return
	 */
	public List<Post> getBestPosts(int pageIndex,int pageSize);
	
	/**
	 * 分页查找最新发表的帖子
	 * @param pageIndex	页码，从1开始
	 * @param pageSize 页面大小
	 * @return
	 */
	public List<Post> getLatestPosts(int pageIndex,int pageSize );
	
	public Post getPostById(int postId);

	/**
	 * 获取指定帖子的回复贴
	 * @param postId 帖子id,页数，页面大小
	 * @return 回复贴列表
	 */
	List<Followcard> getFollowCards(int postId, int pageIndex, int pageSize);
	
	public List<Post> search(String keyword);
	public List<Post> getPostByType(int type,int pageIndex,int pageSize);
	
	public List<Post> getPostByUserId(int userId,int pageIndex,int pageSize);

	public void autoIncreaseReply(int postId);

	public void delete(int postId);

	public void updateType(Integer postId);

	public void updatePost(Post post);

	void autoIncreaseViewNum(int postId);

	List<Post> getHotPosts(int pageIndex, int pageSize); 

}
