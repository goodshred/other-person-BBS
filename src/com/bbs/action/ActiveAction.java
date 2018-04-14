package com.bbs.action;

import com.bbs.service.UserBiz;

/**
 * 激活信息处理
 * @author 张建浩、卜凡、卢静、余莎、姚文娜
 * @version 1.0
 * 2016年3月16日下午4:38:12
 */
public class ActiveAction extends BaseAction {
	private String code;
	private UserBiz userBiz;

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	
	/**
	 * @param userBiz the userBiz to set
	 */
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}


	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
        //未激活，激活
		if (code == null)
			return "index";
        int result = userBiz.activeUser(code);
        String str = "message";
        if (result == 0)
        	getRequest().put("message", "该激活码已失效请重新注册");
        else if (result == -1)
        	getRequest().put("message", "该激活码已激活，请勿重复激活");
        else  if (result == 1){
        	getRequest().put("message", "恭喜您！账户激活成功，五秒钟后自动跳转，无法跳转请点击下面按钮");
        	str = "skip_login";
        }
		return str;
	}
	

}
