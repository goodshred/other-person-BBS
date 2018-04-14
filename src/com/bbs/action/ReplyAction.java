package com.bbs.action;

import java.sql.Timestamp;

import com.bbs.model.Followcard;
import com.bbs.model.Post;
import com.bbs.model.User;
import com.bbs.service.BlackListBiz;
import com.bbs.service.FollowcardBiz;
import com.bbs.service.PostBiz;

public class ReplyAction extends BaseAction{
	private int postId;
	private int pageNum;
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	private String content;
	private FollowcardBiz followcardBiz;
	private PostBiz postBiz;
	
	private BlackListBiz blackListBiz;
	
	
	
	
	
	public void setBlackListBiz(BlackListBiz blackListBiz) {
		this.blackListBiz = blackListBiz;
	}
	public void setPostBiz(PostBiz postBiz) {
		this.postBiz = postBiz;
	}
	public int getPostId() {
		return postId;
	}
	public void setFollowcardBiz(FollowcardBiz followcardBiz) {
		this.followcardBiz = followcardBiz;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Override
	public String execute() throws Exception {
		if (content != null){
			int userId = (Integer) getSession().get("userId");
			int level = blackListBiz.getLevel(userId);
			if (level == 4 || level == 2||level ==1){
				this.addFieldError("limit", "你已被管理员限制发表回复");
				return "post";
			}
			Followcard followcard = new Followcard();
			followcard.setFollowContent(content);
			Post post = new Post();
			post.setId(postId);
			followcard.setPost(post);
			followcard.setFollowDate(new Timestamp(System.currentTimeMillis()));
			User user = new User();
			user.setId(userId);
			System.out.println("user id:"+userId+user.getUsername());
			user.setUsername((String) getSession().get("username"));
			followcard.setUser(user);
			followcardBiz.addReply(followcard);
			postBiz.autoIncreaseReply(postId);
			getRequest().put("postId", postId);
			getRequest().put("page", pageNum);
			return SUCCESS;
		}
		return ERROR;
	}
	

}
