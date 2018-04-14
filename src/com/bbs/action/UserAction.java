package com.bbs.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.bbs.model.User;
import com.bbs.service.UserBiz;
import com.bbs.utils.Utils;
import com.opensymphony.xwork2.ActionContext;

public class UserAction  extends BaseAction{
	private String username;
	private String password;
	private String sex;
	private String email;
	private File photoImg;
	private String photoImgFileName;
	
	public String getPhotoImgFileName() {
		return photoImgFileName;
	}

	public void setPhotoImgFileName(String photoImgFileName) {
		this.photoImgFileName = photoImgFileName;
	}

	public void setPhotoImg(File photoImg) {
		this.photoImg = photoImg;
	}
	private UserBiz userBiz;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	@Override
	public String execute() throws Exception {
		if (username!=null || password != null || email != null || photoImg != null){
			
			User user = userBiz.getUserById((Integer)getSession().get("userId"));
			if (username != null&&username.length()>0)
			user.setUsername(username);
			if (sex != null&&sex.length()>0)
				user.setSex(sex);
			if (email != null&&email.length()>0)
			user.setEmail(email);
			if (password != null&&password.length()>0)
				user.setPassword(password);
			System.out.println("username:"+username+email);
			switch (userBiz.isExist(user)) {
			case 1:
				int id = userBiz.getUserIdByUsername(username);
				if (id!= -1&&id != (Integer)getSession().get("userId")){
					System.out.println("该用户已存在");
					addFieldError("username", "该用户名已存在");
					return SUCCESS;
				}
				
			case 2:
				int id2 = userBiz.getUserIdByEmail(email);
				System.out.println("id2");
				if (id2!=-1&&id2 != (Integer)getSession().get("userId")){
					System.out.println("该邮箱已存在");
					addFieldError("email","该邮箱已存在");
					return SUCCESS;
				}
			default:
				break;
			}
			if (photoImg != null){
				String root = ServletActionContext.getServletContext().getRealPath("/upload/headImg");
				System.out.println(root+photoImg.getName());
				String filename = photoImgFileName;
				int index = filename.indexOf("\\");
				if (index != -1){
					filename = filename.substring(index+1);
				}
				int code = filename.hashCode();//得到哈希码
				String hex = Integer.toHexString(code);//转化成16进制
				File dstDir = new File(root,hex.charAt(0)+"/"+hex.charAt(1));
				String saveFilename = Utils.createUUID()+filename;//防止文件重名
				String abstractPath = "/upload/headImg/"+hex.charAt(0)+"/"+hex.charAt(1)+"/"+saveFilename;
				File dstFile = new File(dstDir,saveFilename);
				if (!dstFile.getParentFile().exists()){
					dstFile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(photoImg,dstFile);
				user.setPhotoUrl(abstractPath);
				ActionContext.getContext().put("message", "上传成功");
			}
			userBiz.update(user);
			this.addFieldError("update_result", "修改成功");
		}
		
		return SUCCESS;
	}
	
	

}
