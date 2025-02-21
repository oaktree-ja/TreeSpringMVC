package com.tree.spring.notice.controller;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tree.spring.notice.domain.NoticeVO;
import com.tree.spring.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	private NoticeService nService;
	@Autowired
	public NoticeController(NoticeService nService) {
		this.nService=nService;
		
	}
	
	@RequestMapping(value="/notice/insert", method=RequestMethod.GET)
	public String showNoticeForm() {
		return "notice/insert";
	}
	
	@RequestMapping(value="/notice/insert", method=RequestMethod.POST)
	public String noticeInsert(@RequestParam("noticeSubject")String noticeSubject
			,@RequestParam("noticeContent")String noticeContent
			,@RequestParam("uploadFile")MultipartFile uploadFile
			,HttpSession session,
			Model model) {
		try {
			String noticeWriter = session.getAttribute("memberId") !=null
					? (String)session.getAttribute("memberId") :"anonymous";
			String noticeFilename=uploadFile.getOriginalFilename();
			String noticeFileRename=null;
			String noticeFilepath=null;
			
			if(noticeFilename !=null) {
				//중복된 파일 이름을 다르게 저장하기 위한 FileRename
				//시간을 이용하기 위한 SimpleDateFormat
				SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
				//현재 시간을 내가 원하는 패턴으로 변환
				String transStr = sdf.format(new Date(System.currentTimeMillis()));
				//원본 파일의 확장자 가져오기
				String ext= noticeFilename.substring(noticeFilename.lastIndexOf("."+1));
				//파일 이름 변경 완료
				noticeFileRename = transStr +"."+ext;
				noticeFilepath="/resources/nUploadFiles/"+noticeFileRename;
				String folderPath=session.getServletContext().getRealPath("/resources/nUploadFiles");
				String savePath = folderPath + "\\"+noticeFileRename;
				
				uploadFile.transferTo(new File(savePath));
			}
			
			NoticeVO notice= new NoticeVO(noticeSubject, noticeContent, noticeWriter, noticeFilename, noticeFileRename, noticeFilepath);
			int result = nService.insertNotice(notice);
			if(result >0) {
				return"redirect:/notice/list";
			}else {
				model.addAttribute("errorMsg", "공지사항이 등록되지 않았습니다");
				return"common/error";
			}
		} catch (Exception e) {
			//콘솔창에 빨간줄 나오게 하는 것
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return"common/error";
		}
		
	}
	
	@RequestMapping(value="/notice/list", method=RequestMethod.GET)
	public String showNoticeList(
			@RequestParam(value="page",defaultValue="1")int currentPage
			,Model model) {
		try {
			//int currentPage=request.getParameter("currentPage") !=null ?Integer.parseInt(request.getParameter("currentPage")):"1";
			List<NoticeVO> nList = nService.selectList(currentPage);
			int boardLimit =10;
			int naviLimit =5;
			//page1-5 statNavi->1, endNavi->5
			//page6-10 statNavi->6, endNavi->10
			//page11-15 statNavi->11, endNavi->15
			
			int startNavi=1;
			int endNavi=5;
			if(!nList.isEmpty()) {
				model.addAttribute("startNavi",startNavi);
				model.addAttribute("endNavi",endNavi);
				model.addAttribute("nList",nList);
				return"notice/list";
			}else {
				model.addAttribute("errorMsg","데이터가 존재하지 않습니다");
				return"common/error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return"common/error";
		}
	}
	
	
}
