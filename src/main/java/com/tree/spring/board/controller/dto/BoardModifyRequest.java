package com.tree.spring.board.controller.dto;

public class BoardModifyRequest {
	private int    boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardFilename;
	private String boardFileRename;
	private String boardFilepath;

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}


	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}




	public String getBoardContent() {
		return boardContent;
	}




	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}




	public String getBoardWriter() {
		return boardWriter;
	}




	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}




	public String getBoardFilename() {
		return boardFilename;
	}




	public void setBoardFilename(String boardFilename) {
		this.boardFilename = boardFilename;
	}




	public String getBoardFileRename() {
		return boardFileRename;
	}




	public void setBoardFileRename(String boardFileRename) {
		this.boardFileRename = boardFileRename;
	}




	public String getBoardFilepath() {
		return boardFilepath;
	}




	public void setBoardFilepath(String boardFilepath) {
		this.boardFilepath = boardFilepath;
	}




	@Override
	public String toString() {
		return "BoardModifyRequest [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent="
				+ boardContent + ", boardWriter=" + boardWriter + ", boardFilename=" + boardFilename
				+ ", boardFileRename=" + boardFileRename + ", boardFilepath=" + boardFilepath + "]";
	}



}
