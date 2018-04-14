package com.bbs.dao;

import java.util.List;

import com.bbs.model.Notice;
/**
 * 
 * @author 张建浩、卜凡、卢静、余莎、姚文娜
 * @version 1.0
 * 2016年3月22日上午9:52:33
 */
public interface NoticeDao {

	public  List<Notice> getNotice(int pageIndex, int pageSize);

	public  void publish(Notice notice);

	public Notice getNoticeById(int noticeId);

}