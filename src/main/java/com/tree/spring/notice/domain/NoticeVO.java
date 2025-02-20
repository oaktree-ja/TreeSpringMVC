package com.tree.spring.notice.domain;

import java.sql.Timestamp;

public class NoticeVO {
	private int noticeNo;
	private String noticeSubject;
	private String noticeContent;
	private String noticeWriter;
	private String noticeFilename;
	private String noticeFileRename;
	private String noticeFilepath;
	private Timestamp noticeDate;
	private Timestamp updateDate;
	private int noticeCount;
	private String noticeYn;
	
	
	public NoticeVO() {
		super();
	}


	public int getNoticeNo() {
		return noticeNo;
	}


	public String getNoticeSubject() {
		return noticeSubject;
	}


	public String getNoticeContent() {
		return noticeContent;
	}


	public String getNoticeWriter() {
		return noticeWriter;
	}


	public String getNoticeFilename() {
		return noticeFilename;
	}


	public String getNoticeFileRename() {
		return noticeFileRename;
	}


	public String getNoticeFilepath() {
		return noticeFilepath;
	}


	public Timestamp getNoticeDate() {
		return noticeDate;
	}


	public Timestamp getUpdateDate() {
		return updateDate;
	}


	public int getNoticeCount() {
		return noticeCount;
	}


	public String getNoticeYn() {
		return noticeYn;
	}


	@Override
	public String toString() {
		return "NoticeVO [noticeNo=" + noticeNo + ", noticeSubject=" + noticeSubject + ", noticeContent="
				+ noticeContent + ", noticeWriter=" + noticeWriter + ", noticeFilename=" + noticeFilename
				+ ", noticeFileRename=" + noticeFileRename + ", noticeFilepath=" + noticeFilepath + ", noticeCount="
				+ noticeCount + ", noticeYn=" + noticeYn + "]";
	}

	
}

