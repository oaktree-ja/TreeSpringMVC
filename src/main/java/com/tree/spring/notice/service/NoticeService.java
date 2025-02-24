package com.tree.spring.notice.service;

import java.util.List;
import java.util.Map;

import com.tree.spring.notice.domain.NoticeVO;

public interface NoticeService {

	int insertNotice(NoticeVO notice);

	List<NoticeVO> selectList(int currentPage);

	int getTotalcount();
	
	int getTotalcount(Map<String, String> paramMap);

	NoticeVO selectOneByNo(int noticeNo);

	int updateNotice(NoticeVO notice);

	int deleteNotice(int noticeNo);

	List<NoticeVO> searchListByKeyword(Map<String, String> paramMap, int currentPage);

	

	
	
	
	
}
