package com.bbs.action;


import java.sql.Timestamp;

import com.bbs.constants.Constant;
import com.bbs.model.User;
import com.bbs.service.UserBiz;
import com.bbs.utils.MailUtil;
import com.bbs.utils.Utils;

/**
 * @author 张建浩、卜凡、卢静、余莎、姚文娜
 * @version 1.0
 * 2016年3月16日下午3:08:03
 */
public class RegistAction extends BaseAction {
	private String username;
	private String password;
	private String email;
	private UserBiz userBiz;
	
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		System.out.println(username);
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		System.out.println(user.getEmail());
		switch (userBiz.isExist(user)) {
		case 1:
			addFieldError("username", "该用户名已被注册");
			return "regist";
		case 2:
			addFieldError("email","该邮箱已被注册");
			return "regist";
		default:
			break;
		}		
		String code = Utils.createUUID();
		user.setActiveCode(code);
		user.setLevel(1);
		user.setType(1);
		user.setSex("男");
		user.setRegisterDate(new Timestamp(System.currentTimeMillis()));
		user.setHasActive(0);
		user.setPhotoUrl("/upload/default/head_icon.jpg");//默认头像
		userBiz.regist(user);
		MailUtil mail = new MailUtil();
		mail.sendEmail(user.getEmail(),code,Constant.ACTIVE_EMAIL);
		//注册完成后跳转至中转页面，等待用户邮箱验证
		return "skip";
	}
	
	
	
	
	

	

}
