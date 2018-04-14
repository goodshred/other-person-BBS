package com.bbs.action;

import java.sql.Timestamp;

import com.bbs.model.Admin;
import com.bbs.model.Notice;
import com.bbs.service.NoticeBiz;

public class NoticeAction extends BaseAction{
	private String title;
	private String content;
	
	private NoticeBiz noticeBiz;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setNoticeBiz(NoticeBiz noticeBiz) {
		this.noticeBiz = noticeBiz;
	}
	
	@Override
	public String execute() throws Exception {
		if (title != null && content != null){
			Notice notice = new Notice();
			notice.setTitle(title);
			notice.setContent(content);
			notice.setNoticeDate(new Timestamp(System.currentTimeMillis()));
			Admin admin = new Admin();
			admin.setId((Integer)getSession().get("adminid"));
			notice.setAdmin(admin);
			noticeBiz.publish(notice);
			this.addFieldError("notice_result","发表成功");
			return SUCCESS;
		}
		return ERROR;
	}
	

}
