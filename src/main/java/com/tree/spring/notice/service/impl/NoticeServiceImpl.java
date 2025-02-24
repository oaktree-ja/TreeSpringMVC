package com.tree.spring.notice.service.impl;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tree.spring.notice.domain.NoticeVO;
import com.tree.spring.notice.service.NoticeService;
import com.tree.spring.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{

	private NoticeStore nStore;
	
	private SqlSession session;
	
	@Autowired
	public NoticeServiceImpl(NoticeStore nStore, SqlSession session) {
		this.nStore=nStore;
		this.session=session;
	}
	
	@Override
	public int insertNotice(NoticeVO notice) {
		int result= nStore.insertNotice(session,notice); 
		return result;
	}

	@Override
	public List<NoticeVO> selectList(int currentPage) {
		List<NoticeVO> nList =nStore.selectList(session,currentPage);
		return nList;
	}

	@Override
	public int getTotalcount() {
		int totalCount = nStore.getTotalCount(session);
		return totalCount;
	}

	@Override
	public NoticeVO selectOneByNo(int noticeNo) {
		NoticeVO notice = nStore.selectOneByNo(session, noticeNo);
		return notice;
	}

	@Override
	public int updateNotice(NoticeVO notice) {
		int result = nStore.updateNotice(session, notice);
		return result;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		int result = nStore.deleteNotice(session, noticeNo);
		return result;
	}

	@Override
	public List<NoticeVO> searchListByKeyword(Map<String, String> paramMap) {
		List<NoticeVO> searchList=nStore.searchListByKeyword(session,paramMap);
		return searchList;
	}

}
