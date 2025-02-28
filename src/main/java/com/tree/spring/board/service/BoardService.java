package com.tree.spring.board.service;

import java.util.List;

import com.tree.spring.board.controller.dto.BoardAddRequest;
import com.tree.spring.board.controller.dto.BoardModifyRequest;
import com.tree.spring.board.domain.BoardVO;

public interface BoardService {

	int insertBoard(BoardAddRequest board);

	List<BoardVO> selectBoardList();

	BoardVO selectOneByNo(int boardNo);

	int updateBoard(BoardModifyRequest board);

	int deleteBoard(int boardNo);

}
