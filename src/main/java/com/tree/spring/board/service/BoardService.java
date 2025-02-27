package com.tree.spring.board.service;

import java.util.List;

import com.tree.spring.board.controller.dto.BoardAddRequest;
import com.tree.spring.board.domain.BoardVO;

public interface BoardService {

	List<BoardVO> selectBoardList();

	int insertBoard(BoardAddRequest board);

}
