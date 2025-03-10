package com.tree.spring.notice.store.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tree.spring.notice.controller.dto.NoticeAddRequest;
import com.tree.spring.notice.controller.dto.NoticeModifyRequest;
import com.tree.spring.notice.domain.NoticeVO;
import com.tree.spring.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore{

	@Override
	public int insertNotice(SqlSession session, NoticeAddRequest notice) {
		int result = session.insert("NoticeMapper.insertNotice",notice);
		return result;
	}

	@Override
	public List<NoticeVO> selectList(SqlSession session, int currentPage) {
		int limit=10;
		//1page: offset->0
		//2page:offset->10
		//3page: offset->20
		int offset =(currentPage-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<NoticeVO> nList = session.selectList("NoticeMapper.selectList", null, rowBounds); 
		return nList;
	}
	
	@Override
	public List<NoticeVO> searchListByKeyword(SqlSession session, Map<String, String> paramMap, int currentPage) {
		int limit=10;
		int offset =(currentPage-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<NoticeVO> searchList= session.selectList("NoticeMapper.searchListByKeyword",paramMap ,rowBounds);
		return searchList;
	}
	
	@Override
	public int getTotalCount(SqlSession session) {
		int totalCount = session.selectOne("NoticeMapper.getTotalCount");
		return totalCount;
	}

	@Override
	public int getTotalCount(SqlSession session, Map<String, String> paramMap) {
		int totalCount = session.selectOne("NoticeMapper.getTotalCountBySearch",paramMap);
		return totalCount;
	}
	
	@Override
	public NoticeVO selectOneByNo(SqlSession session, int noticeNo) {
		NoticeVO notice = session.selectOne("NoticeMapper.selectOneByNo",noticeNo);
		return notice;
	}

	@Override
	public int updateNotice(SqlSession session, NoticeModifyRequest notice) {
		int result = session.update("NoticeMapper.updateNotice",notice);
		return result;
	}

	@Override
	public int deleteNotice(SqlSession session, int noticeNo) {
		int result = session.update("NoticeMapper.deleteNotice", noticeNo);
		return result;
	}

	

	
	
	
	


}
