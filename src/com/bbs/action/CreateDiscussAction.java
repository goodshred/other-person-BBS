package com.bbs.action;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbs.model.MainForum;
import com.bbs.model.SubForum;
import com.bbs.service.MainForumBiz;
import com.bbs.service.SubForumBiz;

public class CreateDiscussAction extends BaseAction{
	private String mainForum;
	private String subForum;
	
	private MainForumBiz mainBiz;
	private SubForumBiz subBiz;
	
	
	
	
	
	public MainForumBiz getMainBiz() {
		return mainBiz;
	}
	public SubForumBiz getSubBiz() {
		return subBiz;
	}
	public void setSubBiz(SubForumBiz subBiz) {
		this.subBiz = subBiz;
	}
	public void setMainBiz(MainForumBiz mainBiz) {
		this.mainBiz = mainBiz;
	}
	public String getMainForum() {
		return mainForum;
	}
	public void setMainForum(String mainForum) {
		this.mainForum = mainForum;
	}
	public String getSubForum() {
		return subForum;
	}
	public void setSubForum(String subForum) {
		this.subForum = subForum;
	}
	
	
	@Override
	public String execute() {
		try {
			if (mainForum != null &&mainForum.length()>0 && subForum != null && subForum.length()>0){
				MainForum main = new MainForum();
				main.setTitle(mainForum);
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				if (mainBiz == null)
					 mainBiz = (MainForumBiz)context.getBean("mainForumBiz");
				if (!mainBiz.add(main)){
					this.addFieldError("create_result", "该板块已经存在");
					return "create";
				}
					
				String [] subf = subForum.split(",");
				int size = subf.length;
				for (int i=0; i<size; i++){
					System.out.println("子版块:"+subf[i]);
					SubForum sub = new SubForum(main,subf[i]);
					if (subBiz == null)
						subBiz = (SubForumBiz)context.getBean("subForumBiz");
					subBiz.add(sub);
				}
				this.addFieldError("create_result", "创建成功");
				return SUCCESS;
			}
			return ERROR;
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	
}
