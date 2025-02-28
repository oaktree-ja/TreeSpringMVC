package com.tree.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tree.spring.board.controller.dto.BoardAddRequest;
import com.tree.spring.board.controller.dto.BoardModifyRequest;
import com.tree.spring.board.domain.BoardVO;

public interface BoardStore {

	int insertBoard(SqlSession session, BoardAddRequest board);

	List<BoardVO> selectBoardList(SqlSession session);
	
	BoardVO selectOneByNo(SqlSession session, int boardNo);
	
	int updateBoard(SqlSession session, BoardModifyRequest board);

	int deleteBoard(SqlSession session,int boardNo);

	
}
