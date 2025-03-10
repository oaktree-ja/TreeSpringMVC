package com.tree.spring.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tree.spring.board.controller.dto.BoardAddRequest;
import com.tree.spring.board.controller.dto.BoardModifyRequest;
import com.tree.spring.board.domain.BoardVO;
import com.tree.spring.board.service.BoardService;
import com.tree.spring.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService{

		private BoardStore bStore;
		private SqlSession session;
		@Autowired
		public BoardServiceImpl(BoardStore bStore, SqlSession session) {
			this.bStore=bStore;
			this.session=session;
		}
	
	@Override
		public int insertBoard(BoardAddRequest board) {
			int result = bStore.insertBoard(session,board);
			return result;
		}

	@Override
	public List<BoardVO> selectBoardList() {
		List<BoardVO> bList = bStore.selectBoardList(session);
		return bList;
	}

	@Override
	public BoardVO selectOneByNo(int boardNo) {
		BoardVO board=bStore.selectOneByNo(session, boardNo);
		return board;
	}

	@Override
	public int updateBoard(BoardModifyRequest board) {
		int result= bStore.updateBoard(session, board);
		return result;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int result =bStore.deleteBoard(session, boardNo);
		return result;
	}

}
