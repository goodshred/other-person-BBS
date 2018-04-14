package com.bbs.serviceImpl;

import java.util.List;

import com.bbs.dao.NoticeDao;
import com.bbs.model.Notice;
import com.bbs.service.NoticeBiz;

/**
 * 
 * @author 张建浩、卜凡、卢静、余莎、姚文娜
 * @version 1.0
 * 2016年3月23日上午11:32:48
 */
public class NoticeBizImpl implements NoticeBiz {
	private NoticeDao noticeDao;
	
	
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	/* (non-Javadoc)
	 * @see com.bbs.bizImpl.NoticeBiz#getNotice(int, int)
	 */
	@Override
	public  List<Notice> getNotice(int pageIndex, int pageSize){
		return noticeDao.getNotice(pageIndex, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.bbs.bizImpl.NoticeBiz#publish(com.bbs.model.Notice)
	 */
	@Override
	public  void publish(Notice notice){
		noticeDao.publish(notice);
		
	}
	
	public Notice getNoticeById(int noticeId){
		return noticeDao.getNoticeById(noticeId);
		
	}

}
