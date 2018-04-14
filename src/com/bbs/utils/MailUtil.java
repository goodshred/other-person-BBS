package com.bbs.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.bbs.constants.Constant;

/**
 * @author 张建浩、卜凡、卢静、余莎、姚文娜
 * @version 1.0
 * 2016年3月16日下午4:12:07
 */
public class MailUtil {
	
	private String username;
	private String password;
	private String mailHost;
	private String fromAddres;
	
	public MailUtil() throws FileNotFoundException, IOException {
		super();
		init();
	}
	private void init() throws FileNotFoundException, IOException{
		Properties properties = new Properties();
		properties.load(this.getClass().getClassLoader().getResourceAsStream("com/bbs/utils/mail.properties"));
		username = properties.getProperty("username");
	    password = properties.getProperty("password");
	    mailHost = properties.getProperty("mailHost");
	    fromAddres = properties.getProperty("fromAddress");
		
	}
	/**
	 * 发送邮件的核心方法，邮件发送失败会抛出相应的异常，调用者应该捕捉这些异常
	 * @param to 邮件的发送对象
	 * @param info 邮件的正文的具体内容
	 * @param type 这里可选类型有两种 Utils.ACTIVE_EMAIL,和FIND_PASSWORD_EMAIL 
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnknownHostException
	 */

	public  void sendEmail(String to,String code ,int type) throws AddressException, MessagingException, UnknownHostException{
		//第一步：创建propeties
		Properties props = new Properties();
		props.put("mail.host",mailHost);
		props.put("mail.smtp.auth","true");
		//第二步：获取用户名和密码进行认证
		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(username, password);
			}                                                                                                                                                                                                 
			
		};
		//第三步：获取session对象
		Session session = Session.getInstance(props, authenticator);
		//第四步：设置邮件发送信息
		MimeMessage message = new MimeMessage(session);
      
			message.setFrom(new InternetAddress(fromAddres));
			message.setRecipient(RecipientType.TO,new InternetAddress(to));
			if (type == Constant.ACTIVE_EMAIL){
			message.setSubject("BBS技术论坛激活验证信息");
			String info = "欢迎注册我们的技术论坛，点击下方链接激活您的账户<br/><br/>";
			//获取服务器地址
			String warning = "<br/><br/>本邮件为系统邮件，请勿回复";
			message.setContent(info+"http://"+Constant.HOST_IP+":"+Constant.PORT+"/"+Constant.PROJECT_NAME+"/active.action?code="+code+warning,"text/html;charset=utf-8");
			}
//			else if (type == Utils.FIND_PASSWORD_EMAIL){
////				message.setSubject("找回密码邮件");
////				message.setContent("尊敬的用户！您的密码为“"+info+"”请牢记好自己的密码，请勿泄露自己的密码，本邮件由张建浩的聊天室发送,请不要回复!","text/html;charset=utf-8");
//				
//			}
			Transport.send(message);
	}

}
