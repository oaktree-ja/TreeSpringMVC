package com.tree.spring.notice.service;

import java.util.List;

import com.tree.spring.notice.domain.NoticeVO;

public interface NoticeService {

	int insertNotice(NoticeVO notice);

	List<NoticeVO> selectList(int currentPage);

	int getTotalcount();

	NoticeVO selectOneByNo(int noticeNo);

	int updateNotice(NoticeVO notice);

	int deleteNotice(int noticeNo);

	
	
	
	
}
