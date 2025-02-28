package com.tree.spring.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tree.spring.board.controller.dto.BoardAddRequest;
import com.tree.spring.board.controller.dto.BoardModifyRequest;
import com.tree.spring.board.domain.BoardVO;
import com.tree.spring.board.service.BoardService;
import com.tree.spring.common.FileUtil;

@Controller
public class BoardContoller {
	
	private BoardService bService;
	private FileUtil fileUtil;
	@Autowired
	public void BoardController(BoardService bService, FileUtil fileUtil) {
		this.bService=bService;
		this.fileUtil=fileUtil;
	}
	@GetMapping("/board/list")
	public String showBoardList(
			Model model) {
		try {
			List<BoardVO> bList = bService.selectBoardList();
			model.addAttribute("bList",bList);
			return "board/list";
		} catch (Exception e) {
			return "common/error";
		}
	}
	
	@GetMapping("/board/detail/{boardNo}")
	public String showBoardDetail(Model model,@PathVariable("boardNo")int boardNo) {
		try {
			BoardVO board= bService.selectOneByNo(boardNo);
			model.addAttribute("board",board);
			return"board/detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	
	
	@GetMapping("/board/insert")
	public String showBoardInsertForm() {
		return"board/insert";
	}
	
	@PostMapping("/board/insert")
	public String insertBoard(@ModelAttribute BoardAddRequest board
			,@RequestParam("uploadFile")MultipartFile uploadFile
			,HttpSession session
			,Model model){
		
		try {
			if(session.getAttribute("memberId") !=null) {
				board.setBoardWriter((String)session.getAttribute("memberId"));
			}else {
				model.addAttribute("errorMsg","로그인이 필요합니다..!!");
				return "common/error";
			}
			if(uploadFile !=null && !uploadFile.getOriginalFilename().isBlank()) {
				Map<String, String> fileInfo = fileUtil.saveFile(uploadFile,session,"board");
				board.setBoardFilename(fileInfo.get("bFilename"));
				board.setBoardFileRename(fileInfo.get("bFileRename"));
				board.setBoardFilepath(fileInfo.get("bFilepath"));
			}
			int result=bService.insertBoard( board);
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	
	@GetMapping("/board/modify/{boardNo}")
	public String showBoardModifyForm(@PathVariable int boardNo
			,Model model) {
		try {
			BoardVO board = bService.selectOneByNo(boardNo);
			model.addAttribute("board",board);
			return"/board/modify";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return"common/error";
		
		}
		
		
	}
	@PostMapping("/board/modify")
	public String modifyBoard( Model model,
			@ModelAttribute BoardModifyRequest board,
			@RequestParam("reloadFile")MultipartFile reloadFile
			,HttpSession session){
		
		try {
			if(session.getAttribute("memberId") ==null) {
				model.addAttribute("errorMsg","로그인이 필요합니다");
				return"common/error";
			}
			String memberId=(String)session.getAttribute("memberId");
			if(!memberId.equals(board.getBoardWriter())) {
					model.addAttribute("errorMsg", "자신이 작성한 글만 수정할수 있습니다");
					return "common/error";
			}
			if(reloadFile !=null && !reloadFile.getOriginalFilename().isBlank()){
				Map<String, String> fileInfo =fileUtil.saveFile(reloadFile, session, "board");
				board.setBoardFilename(fileInfo.get("bFilename"));
				board.setBoardFileRename(fileInfo.get("bFileRename"));
				board.setBoardFilepath(fileInfo.get("bFilepath"));
			}
			
			int result = bService.updateBoard(board);
			return "redirect:/board/detail/"+board.getBoardNo();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return"common/error";
		}	
	}
	
	
	@GetMapping("/board/delete/{boardNo}")
	public String deleteBoard(@PathVariable int boardNo,Model model) {
		try {
			int result = bService.deleteBoard(boardNo);
			return "redirect:/board/list";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	
	
	
	
	
	
	
}
