/**
 * 
 */
package com.bbs.dao;

import java.util.List;

import com.bbs.model.User;


/**
 * 用户访问接口
 * @author 张建浩、卜凡、卢静、余莎、姚文娜
 * @version 1.0
 * 2016年3月16日下午1:33:02
 */
public interface UserDao {
	/**
	 * 注册用户
	 * @param user 用户对象
	 * @return 成功返回true,失败返回false
	 */
	public boolean regist(User user);
	
	/**
	 * 用户登陆
	 * @param user 用户对象
	 * @return 对象引用列表
	 */
	public List<User> login(String username);
	
	
	
	/**
	 * 激活用户
	 * @param userId 用户id
	 * @param activeCode 激活码
	 * @return 1-激活成功,0-该用户不存在，-1已经激活
	 */
	public int  activeUser(String activeCode);
	public void updateCode(String username,String code);
	
	
	/**
	 * 修改用户密码
	 * @param userId 用户id
	 * @param oldpassword 旧密码
	 * @param newpassword 新密码
	 * @return true修改成功，false 未找到与userid和旧密码匹配的用户，修改失败
	 */
	public boolean changePassword(int userId,int oldpasswod,int newpassword);
	
	public int isExist(User user);
	
	public void update(User user);

	public User getUserById(Integer integer);

	public int getUserByEmail(String email);

	public int getUserIdByUsername(String username);

	public List<User> getUserLike(String like);
	

}
