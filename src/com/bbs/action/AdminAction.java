package com.bbs.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.bbs.model.Admin;
import com.bbs.model.User;
import com.bbs.service.AdminBiz;
import com.bbs.utils.Utils;
import com.opensymphony.xwork2.ActionContext;

public class AdminAction extends BaseAction{
	private String username;
	private String password;
	private String email;
	private File photoImg;
	private String photoImgFileName;
	private AdminBiz adminBiz;
	private String sex;
	
	
	
	
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public File getPhotoImg() {
		return photoImg;
	}

	public void setPhotoImg(File photoImg) {
		this.photoImg = photoImg;
	}

	public String getPhotoImgFileName() {
		return photoImgFileName;
	}

	public void setPhotoImgFileName(String photoImgFileName) {
		this.photoImgFileName = photoImgFileName;
	}

	public AdminBiz getAdminBiz() {
		return adminBiz;
	}

	public void setAdminBiz(AdminBiz adminBiz) {
		this.adminBiz = adminBiz;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String update() throws IOException{
if (username!=null || password != null || email != null || photoImg != null){
			Admin admin = adminBiz.getAdminById((Integer)getSession().get("adminid"));
			if (username != null&&username.length()>0)
			admin.setUserName(username);
			if (sex != null&&sex.length()>0)
				admin.setSex(sex);;
			if (email != null&&email.length()>0)
			admin.setEmail(email);
			if (password != null&&password.length()>0)
			admin.setPassword(password);
			switch (adminBiz.isExist(admin)) {
			case 1:
				int id = adminBiz.getAdminIdByUsername(username);
				if (id !=-1 &&id != (Integer)getSession().get("adminid")){
					System.out.println("该用户已存在");
					addFieldError("username", "该用户名已存在");
					return SUCCESS;
				}
			case 2:
				int id2 = adminBiz.getAdminIdByEmail(email);
				if (id2!=2&&id2 != (Integer)getSession().get("adminid")){
					System.out.println("该邮箱已存在");
					addFieldError("email","该邮箱已存在");
					return SUCCESS;
				}
			
			}
			if (photoImg != null){
				String root = ServletActionContext.getServletContext().getRealPath("/upload/headImg");
				System.out.println(root);
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
				System.out.println(dstFile.toPath());
				if (!dstFile.getParentFile().exists()){
					dstFile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(photoImg,dstFile);
				admin.setPhotoUrl(abstractPath);
				ActionContext.getContext().put("message", "上传成功");
			}
			adminBiz.updateAdmin(admin);
			this.addFieldError("update_result", "修改成功");
			return SUCCESS;
		}
		
		return SUCCESS;
	}
	
	public String login(){
		if (username != null && password != null){
			int result = adminBiz.login(username, password);
			if (result > 0){
				//将用户id，和姓名写入session
				getSession().put("adminname", username);
				getSession().put("adminid", result);
				return SUCCESS;
			}
			switch (result) {
			case -1:
				this.addFieldError("adminPassword", "密码不正确");
				return "login";
			case 0:
				this.addFieldError("adminName", "该用户不存在");
				return "login";
			default:
				break;
			}
		
		return SUCCESS;
		}
		return "login";
	}

}
