/**
 * 
 */
package com.bbs.dao;

import java.util.List;

import org.hibernate.sql.Update;

import com.bbs.model.Admin;
import com.bbs.model.User;

/**
 * 管理员访问借口类
 * @author 张建浩、卜凡、卢静、余莎、姚文娜
 * @version 1.0
 * 2016年3月16日下午1:32:23
 */
public interface AdminDao {
	/**
	 * 用户登陆
	 * @param user 用户对象
	 * @return 对象引用列表
	 */
	public List<Admin> login(String username);
	
	
	
	public void updateAdmin(Admin admin);
	/**
	 * 修改用户密码
	 * @param userId 用户id
	 * @param oldpassword 旧密码
	 * @param newpassword 新密码
	 * @return true修改成功，false 未找到与userid和旧密码匹配的用户，修改失败
	 */
	public boolean changePassword(int userId,int oldpasswod,int newpassword);



	public Admin getAdminById(Integer adminId);



	public int getAdminIdByUsername(String username);



	public int getAdminIdByEmail(String email);



	public int isExist(Admin admin);



	
//	public int isExist(User user);

}
