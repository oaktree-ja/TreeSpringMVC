package com.tree.spring.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tree.spring.board.controller.dto.BoardAddRequest;
import com.tree.spring.board.controller.dto.BoardModifyRequest;
import com.tree.spring.board.domain.BoardVO;
import com.tree.spring.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore{

	@Override
	public int insertBoard(SqlSession session, BoardAddRequest board) {
		int result= session.insert("BoardMapper.insertBoard",board);
		return result;
	}

	@Override
	public List<BoardVO> selectBoardList(SqlSession session) {
		List<BoardVO> bList = session.selectList("BoardMapper.selectBoardList",session);
		return bList;
	}

	@Override
	public BoardVO selectOneByNo(SqlSession session, int boardNo) {
		BoardVO board=session.selectOne("BoardMapper.selectOneByNo",boardNo);
		return board;
	}

	@Override
	public int updateBoard(SqlSession session, BoardModifyRequest board) {
		int result = session.update("BoardMapper.updateBoard",board);
		return result;
	}

	@Override
	public int deleteBoard(SqlSession session, int boardNo) {
		int result= session.update("BoardMapper.deleteBoard",boardNo);
		return result;
	}

}
