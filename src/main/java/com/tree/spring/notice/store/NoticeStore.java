package com.tree.spring.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tree.spring.notice.domain.NoticeVO;

public interface NoticeStore {
	
		
	int insertNotice(SqlSession session, NoticeVO notice);

	List<NoticeVO> selectList(SqlSession session,int currentPage);

	
	
	
	
}
