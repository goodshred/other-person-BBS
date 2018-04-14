package com.bbs.action;

import java.util.List;

import com.bbs.model.Post;
import com.bbs.service.MainForumBiz;
import com.bbs.service.PostBiz;

public class MoreAction extends BaseAction {
	private int type;//1,2,3.4.5.6.7.
	private int page;
	private PostBiz postBiz;
	private MainForumBiz mainForumBiz;
	
	
	
	
	

	public void setMainForumBiz(MainForumBiz mainForumBiz) {
		this.mainForumBiz = mainForumBiz;
	}

	public MainForumBiz getMainForumBiz() {
		return mainForumBiz;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setPage(int page) {
		this.page = page;
	}


	public void setPostBiz(PostBiz postBiz) {
		this.postBiz = postBiz;
	}


	@Override
	public String execute() throws Exception {
		System.out.println("type"+":"+type+"page:"+page);
		String typename = null;
		switch (type) {
		case -1:
			typename = "论坛新帖";
			break;
		case -2:
			typename = "精华帖";
			break;
		case -3:
			typename = "论坛热帖";
			break;
		default:
			typename = mainForumBiz.getMainForumById(type).getTitle();
			break;
		}
		if (type>-4&&page>0){
			List<Post>posts = postBiz.getPostByType(type, page, 10);
//			for (Post post:posts){
//				System.out.println("差找到"+post.getTitle());
//			}
			getRequest().put("posts", posts);
			getRequest().put("page", page);
			getRequest().put("type", type);
			getRequest().put("typename", typename);
			return SUCCESS;
		}
		return ERROR;
	}
	

}
