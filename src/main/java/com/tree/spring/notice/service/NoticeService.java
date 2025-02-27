package com.tree.spring.notice.service;

import java.util.List;
import java.util.Map;

import com.tree.spring.notice.controller.dto.NoticeAddRequest;
import com.tree.spring.notice.controller.dto.NoticeModifyRequest;
import com.tree.spring.notice.domain.NoticeVO;

public interface NoticeService {

	int insertNotice(NoticeAddRequest notice);

	List<NoticeVO> selectList(int currentPage);

	int getTotalcount();
	
	NoticeVO selectOneByNo(int noticeNo);

	int updateNotice(NoticeModifyRequest notice);

	int deleteNotice(int noticeNo);

	List<NoticeVO> searchListByKeyword(Map<String, String> paramMap, int currentPage);

	int getTotalcount(Map<String, String> paramMap);

	

	
	
	
	
}
