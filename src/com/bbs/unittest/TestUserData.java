package com.bbs.unittest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.bbs.constants.Constant;
import com.bbs.dao.BestPostDao;
import com.bbs.daoImpl.BestPostDaoImpl;
import com.bbs.daoImpl.PostDaoImpl;
import com.bbs.hibernate.factory.HibernateSessionFactory;
import com.bbs.model.BestPost;
import com.bbs.model.Followcard;
import com.bbs.model.Post;
import com.bbs.model.User;
import com.bbs.service.BestPostBiz;
import com.bbs.serviceImpl.BestPostBizImpl;
import com.bbs.utils.MailUtil;

/**
 * @author 张建浩、卜凡、卢静、余莎、姚文娜
 * @version 1.0
 * 2016年3月17日下午1:39:30
 */
public class TestUserData {

	@Test
	public void test() {
//		testFollowCards();
//		testSearchPost("android");
//		testSearchByForum(2, 1, 2);
//		testFollowCards();
//		testReadBestList(1, 10);
		testChangePostType();
	}
	
	public void testChangePostType(){
		PostDaoImpl daoImpl = new PostDaoImpl();
		daoImpl.updateType(1);
	}
	
	public void testReadBestList(int pageIndex,int pageSize){
		BestPostDao dao = new BestPostDaoImpl();
		List<BestPost> posts = dao.getBestPosts(pageIndex, pageSize);
		System.out.println("size:"+posts.size());
		for (BestPost post:posts){
			System.out.println(post.getPost().getTitle());
		}
		
	}
	/**
	 * 测试增加回复数目
	 * @param postId
	 */
	public void testAddReply(int postId){
		PostDaoImpl dao = new PostDaoImpl();
		dao.autoIncreaseReply(postId);
	}
	
	public void testSearchByForum(int type,int pageIndex,int pageSize){
		PostDaoImpl dao = new PostDaoImpl();
		List<Post> posts = dao.getPostByType(type,pageIndex,pageSize);
		for (Post post:posts){
			System.out.println("差找到"+post.getTitle());
		}
	}
	
	public void testSearchPost(String keywords){
		PostDaoImpl dao = new PostDaoImpl();
		List<Post> posts = dao.search(keywords);
		System.out.println("差找到"+posts.size()+"条记录");
		for (Post post:posts){
			System.out.println("差找到"+post.getTitle());
			
		}
		
	}
	
	
	public void testFollowCards(){
		PostDaoImpl dao = new PostDaoImpl();
//		Post post = dao.getPostById(1);
		List<Followcard> followcards = dao.getFollowCards(66, 2, 5);
//		System.out.println(post.getCardContent());
//		List<Followcard> followcards = dao.getFollowCards(2, 2, 2);
		for (Followcard followcard:followcards){
			String content = followcard.getFollowContent();
			System.out.println(content);
		}

	}
	
	public void testPost(){
		PostDaoImpl dao = new PostDaoImpl();
		List<Post> posts = dao.getLatestPosts(0, 5);
		for (Post post:posts){
			System.out.println("title:"+post.getTitle());
			System.out.println("user:"+post.getUser().getUsername());
			System.out.println("content:"+post.getCardContent());
			Set<Followcard> followcards = post.getFollowcards();
			Iterator<Followcard> iterator = followcards.iterator();
			while (iterator.hasNext()){
				Followcard followcard = iterator.next();
				System.out.println("reply:"+followcard.getFollowContent()+"---reply user:"+followcard.getUser().getUsername());
			}
		}
	}
	
	
	
	public void testHQL(){
		Session session = HibernateSessionFactory.getSession();
		String sql = "from User user where username=?";
		Query query = session.createQuery(sql);
		query.setString(0, "aa");
		List<User> users = query.list();
		System.out.println(users.size());
	}
	
	public void testMail(){
		Properties properties = new Properties();
		try {
			;
			URL uri = getClass().getClassLoader().getResource("com//bbs//unittest//mail.properties");
			if (uri == null)
				System.out.println("meiyou ");
			properties.load(this.getClass().getClassLoader().getResourceAsStream("com/bbs/utils/mail.properties"));
		} 
			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username = properties.getProperty("username");
	    String password = properties.getProperty("password");
	    String mailHost = properties.getProperty("mailHost");
	    String fromAddres = properties.getProperty("fromAddress");
	    
	    System.out.print(username+password);
	    
	    try {
	    	MailUtil mail = new MailUtil();
			mail.sendEmail("1519503862@qq.com", "12345", Constant.ACTIVE_EMAIL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
}
