package com.tree.spring.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tree.spring.board.controller.dto.BoardAddRequest;
import com.tree.spring.board.domain.BoardVO;
import com.tree.spring.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore{

	@Override
	public List<BoardVO> selectBoardList(SqlSession session) {
		List<BoardVO> bList = session.selectList("BoardMapper.selectBoardList",session);
		return bList;
	}

	@Override
	public int insertBoard(SqlSession session, BoardAddRequest board) {
		int result= session.insert("BoardMapper.insertBoard",board);
		return result;
	}

}
