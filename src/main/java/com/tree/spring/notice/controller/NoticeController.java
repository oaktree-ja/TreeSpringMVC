package com.tree.spring.notice.controller;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String noticeInsert(@RequestParam("noticeSubject") String noticeSubject
			, @RequestParam("noticeContent") String noticeContent
			, @RequestParam("uploadFile") MultipartFile uploadFile
			, HttpSession session
			, Model model) {
		try {
			String noticeWriter = session.getAttribute("memberId") != null 
					? (String)session.getAttribute("memberId") : "anonymous";
			String noticeFilename = uploadFile.getOriginalFilename();
			String noticeFileRename = null;
			String noticeFilepath = null;
			if(noticeFilename != null) {
				// 중복된 파일이름을 다르게 저장하기 위한 FileRename
				// 시간을 이용하기 위한 SimpleDateFormat
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				// 현재 시간을 내가 원하는 패턴으로 변환
				String transStr = sdf.format(new Date(System.currentTimeMillis())); // 20250220173811
				// 원본 파일의 확장자 가져오기
				String ext = noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
				// 파일이름 변경완료
				noticeFileRename = transStr + "." + ext;
				noticeFilepath = "/resources/nUploadFiles/"+noticeFileRename;
				String folderPath = session.getServletContext().getRealPath("/resources/nUploadFiles");
				String savePath =  folderPath + "\\" + noticeFileRename;
				uploadFile.transferTo(new File(savePath));
			}
			NoticeVO notice = new NoticeVO(noticeSubject, noticeContent, noticeWriter
						, noticeFilename, noticeFileRename, noticeFilepath);
			int result = nService.insertNotice(notice);
			if(result > 0) {
				return "redirect:/notice/list";
			}else {
				model.addAttribute("errorMsg", "공지사항 등록이 완료되지 않았습니다.");
				return "common/error";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}
		
	}
	@RequestMapping(value="/notice/update",method=RequestMethod.GET)
	public String showModifyForm(
			@RequestParam("noticeNo")int noticeNo
			,Model model) {
		try {
			NoticeVO notice = nService.selectOneByNo(noticeNo);
			model.addAttribute("notice",notice);
			return"notice/update";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}
		
	}
	
	@RequestMapping(value="/notice/update",method=RequestMethod.POST)
	public String updateNotice(@RequestParam("noticeNo") int noticeNo
			,@RequestParam("noticeSubject") String noticeSubject
			,@RequestParam("noticeContent") String noticeContent
			,@RequestParam("reloadFile") MultipartFile reloadFile
			,Model model,HttpSession session) {
		try {
			String noticeFilename = reloadFile.getOriginalFilename();
			String noticeFileRename = null;
			String noticeFilepath=null;
			if(noticeFilename !=null) {
				String ext = noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
				noticeFileRename = UUID.randomUUID()+"."+ext;
				String folderPath = session.getServletContext().getRealPath("/resources/nUploadFiles");
				String savePath = folderPath +"\\" +noticeFileRename;
				reloadFile.transferTo(new File(savePath));
				noticeFilepath ="/resources/nUploadFiles/"+noticeFileRename;
				
			}
			NoticeVO notice = new NoticeVO(noticeNo, noticeSubject, noticeContent, noticeFilename, noticeFileRename, noticeFilepath);
			int result = nService.updateNotice(notice);
			if(result >0) {
				return"redirect:/notice/detail?noticeNo="+notice.getNoticeNo();
			}else {
				model.addAttribute("errorMsg","수정이 완료되지 않았습니다");
				return "common/error";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}
	}
	
	@RequestMapping(value="/notice/delete",method=RequestMethod.GET)
	public String deleteNotice(
			@RequestParam("noticeNo")int noticeNo
			,Model model) {
		try {
			int result =nService.deleteNotice(noticeNo);
			return "redirect:/notice/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}
		
	}
	
	
	@RequestMapping(value="/notice/list", method=RequestMethod.GET)
	public String showNoticeList(
			@RequestParam(value="page",defaultValue="1")int currentPage
			,Model model) {
		try {
			//int currentPage=request.getParameter("currentPage") !=null ?Integer.parseInt(request.getParameter("currentPage")):"1";
			List<NoticeVO> nList = nService.selectList(currentPage);
			int totalCount=nService.getTotalcount();
			int boardLimit =10;
			int maxPage =0;
			if(totalCount % boardLimit !=0) {
				maxPage = totalCount / boardLimit +1;
			}else {
				maxPage = totalCount/boardLimit;
			}
			int naviLimit =5;
			//page1-5 statNavi->1, endNavi->5
			//page6-10 statNavi->6, endNavi->10
			//page11-15 statNavi->11, endNavi->15
			
			int startNavi=((currentPage-1)/naviLimit)*naviLimit+1;
			int endNavi=(startNavi-1)+naviLimit;
			if(endNavi >maxPage) {
				endNavi = maxPage;
			}
			if(!nList.isEmpty()) {
				model.addAttribute("maxPage",maxPage);
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
	
	@RequestMapping(value="/notice/detail", method=RequestMethod.GET)
	public String showNoticeDetail(
			@RequestParam("noticeNo") int noticeNo
			,Model model) {
		try {
			NoticeVO notice = nService.selectOneByNo(noticeNo);
			model.addAttribute("notice",notice);
			return "notice/detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	

	
	
}
