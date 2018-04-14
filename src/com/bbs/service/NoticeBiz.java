package com.bbs.service;

import java.util.List;

import com.bbs.model.Notice;

public interface NoticeBiz {

	public abstract List<Notice> getNotice(int pageIndex, int pageSize);

	public abstract void publish(Notice notice);
	public Notice getNoticeById(int noticeId);

}