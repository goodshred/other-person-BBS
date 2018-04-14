package com.bbs.action;

import com.bbs.model.BestPost;
import com.bbs.model.Post;
import com.bbs.model.User;
import com.bbs.service.BestPostBiz;
import com.bbs.service.PostBiz;

public class PostAction extends BaseAction{
	private Integer postId;
	private int state;
	
	
	public void setState(int state) {
		this.state = state;
	}
	private BestPostBiz bestPostBiz;
	
	
	

	public void setBestPostBiz(BestPostBiz bestPostBiz) {
		this.bestPostBiz = bestPostBiz;
	}


	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	private PostBiz postBiz;
	
	
	public void setPostBiz(PostBiz postBiz) {
		this.postBiz = postBiz;
	}


	@Override
	public String execute() throws Exception {
		if (postId != null){
			System.out.println("id"+postId);
			Post post = postBiz.getPostById(postId);
			if (post == null)
				return ERROR;
			getRequest().put("post", post);
			return SUCCESS;
		}
		return ERROR;
	}
	
	/**
	 * 申请精华帖
	 * @return
	 */
	public String applyBestPost(){
		if (postId >0 && getSession().get("username") !=null){
			BestPost bestPost = new BestPost();
			Post post = new Post();
			post.setId(postId);
			bestPost.setPost(post);
			User user = new User();
			user.setId((Integer)getSession().get("userId"));
			bestPost.setUser(user);
			bestPost.setState(1);//1表示请求正在等待处理
			bestPostBiz.insert(bestPost);
			
		}
		return SUCCESS;
	}
	
	
	/**
	 * 管理员回复精华帖
	 */
	
	public String replyapply(){
		if (postId >0 && getSession().get("adminname") !=null){
			
			bestPostBiz.updateState(postId,state);
			if (state == 2)//同意
				postBiz.updateType(postId);
			return SUCCESS;
		}
		return ERROR;
		
	}
	
	
	
	
	public String delete(){
		if (postId >0 && getSession().get("adminname") !=null){
			postBiz.delete(postId);
			return SUCCESS;
		}
		return ERROR;
	}
	
	/**
	 * 编辑申请
	 */
	
	public String editPost(){
		if (postId>0){
			Post post = postBiz.getPostById(postId);
			getRequest().put("post", post);
			return SUCCESS;
		}
		return ERROR;
	}

}
