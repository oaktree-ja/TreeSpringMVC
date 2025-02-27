package com.tree.spring.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.tree.spring.notice.controller.dto.NoticeAddRequest;
import com.tree.spring.notice.controller.dto.NoticeModifyRequest;
import com.tree.spring.notice.domain.NoticeVO;

public interface NoticeStore {
	
		
	int insertNotice(SqlSession session, NoticeAddRequest notice);

	List<NoticeVO> selectList(SqlSession session,int currentPage);

	int getTotalCount(SqlSession session);

	NoticeVO selectOneByNo(SqlSession session, int noticeNo);

	int updateNotice(SqlSession session, NoticeModifyRequest notice);

	int deleteNotice(SqlSession session, int noticeNo);

	List<NoticeVO> searchListByKeyword(SqlSession session, Map<String, String> paramMap, int currentPage);

	int getTotalCount(SqlSession session, Map<String, String> paramMap);

	
	
	
	
}
