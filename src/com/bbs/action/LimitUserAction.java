package com.bbs.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import com.bbs.model.BlackList;
import com.bbs.model.User;
import com.bbs.service.BlackListBiz;
import com.bbs.service.UserBiz;

public class LimitUserAction extends BaseAction {
	
	private String keywords;
	private int userId;
	private int level;
	
	private BlackListBiz blackListBiz;
	
	
	
	public void setBlackListBiz(BlackListBiz blackListBiz) {
		this.blackListBiz = blackListBiz;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private UserBiz userBiz;

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public UserBiz getUserBiz() {
		return userBiz;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
	
	@Override
	public String execute() throws Exception {
		if (keywords != null){
			keywords = new String(keywords.getBytes("iso8859-1"),"utf-8");
			List<User> uses = userBiz.getUserLike(keywords);
			getRequest().put("uses", uses);
			getRequest().put("keywords", keywords);
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String limit(){
		try {
			keywords = new String(keywords.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userId>0&& level>0){
			System.out.println("limit utf-8:"+keywords);
			BlackList blackList = new BlackList();
		    User user = new User();
		    user.setId(userId);
		    blackList.setUser(user);
		    blackList.setLevel(level);
			int lev = blackListBiz.getLevel(userId);
			System.out.println(lev);
			if (lev>0){//表中存在
				if (lev != level)
				blackListBiz.update(blackList);
			}else {
				blackListBiz.save(blackList);
			}
			
		}else if (level<0){
			blackListBiz.remove(userId);
		}
		System.out.println("before query"+keywords);
		List<User> uses = userBiz.getUserLike(keywords);
		getRequest().put("uses", uses);
		getRequest().put("keywords", keywords);
		return SUCCESS;
	}
	
				
	
	

}
