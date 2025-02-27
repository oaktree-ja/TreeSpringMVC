package com.tree.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tree.spring.board.controller.dto.BoardAddRequest;
import com.tree.spring.board.domain.BoardVO;

public interface BoardStore {

	List<BoardVO> selectBoardList(SqlSession session);
	int insertBoard(SqlSession session, BoardAddRequest board);
}
