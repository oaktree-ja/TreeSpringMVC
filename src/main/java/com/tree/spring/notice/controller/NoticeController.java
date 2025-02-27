package com.tree.spring.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tree.spring.common.FileUtil;
import com.tree.spring.common.PageUtil;
import com.tree.spring.notice.controller.dto.NoticeAddRequest;
import com.tree.spring.notice.controller.dto.NoticeModifyRequest;
import com.tree.spring.notice.domain.NoticeVO;
import com.tree.spring.notice.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private NoticeService nService;
	private FileUtil fileUtill;
	private PageUtil pageUtill;
	
	@Autowired
	public NoticeController(NoticeService nService, FileUtil fileUtill, PageUtil pageUtill) {
		this.nService=nService;
		this.fileUtill=fileUtill;
		this.pageUtill=pageUtill;
	}
	@GetMapping("/insert")
	//@RequestMapping(value="/notice/insert", method=RequestMethod.GET)
	public String showNoticeForm() {
		return "notice/insert";
	}
	@PostMapping("/insert")
	//@RequestMapping(value="/notice/insert", method=RequestMethod.POST)
	public String noticeInsert(@ModelAttribute NoticeAddRequest notice
			, @RequestParam("uploadFile") MultipartFile uploadFile
			, HttpSession session
			, Model model) {
		try {
			String noticeWriter = session.getAttribute("memberId") != null 
					? (String)session.getAttribute("memberId") : "anonymous";
			String noticeFilename = null;
			String noticeFileRename = null;
			String noticeFilepath = null;
			if(uploadFile !=null && !uploadFile.getOriginalFilename().isBlank()) {
				Map<String, String>fileInfo= fileUtill.saveFile(uploadFile,session,"notice");
				noticeFilename= fileInfo.get("nFilename");
				if(noticeFilename != null) {
					noticeFileRename=fileInfo.get("nFileRename");
					noticeFilepath=fileInfo.get("nFilepath");
				}
				notice.setNoticeWriter(noticeWriter);
				notice.setNoticeFilename(noticeFilename);
				notice.setNoticeFileRename(noticeFileRename);
				notice.setNoticeFilepath(noticeFilepath);
			}
			
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
	@GetMapping("/update")
	//@RequestMapping(value="/notice/update",method=RequestMethod.GET)
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
	@PostMapping("/update")
	//@RequestMapping(value="/notice/update",method=RequestMethod.POST)
	public String updateNotice(
			@ModelAttribute NoticeModifyRequest notice
			,@RequestParam("reloadFile") MultipartFile reloadFile
			,Model model,HttpSession session) {
		try {
			String noticeFilename = null;
			String noticeFileRename = null;
			String noticeFilepath=null;
			if(reloadFile !=null&& !reloadFile.getOriginalFilename().isBlank()) {
				Map<String, String> fileInfo = fileUtill.saveFile(reloadFile, session,"notice");
				noticeFilename = fileInfo.get("nFilename");
				noticeFileRename = fileInfo.get("nFileRename");
				noticeFilepath = fileInfo.get("nFilepath");
				notice.setNoticeFilename(noticeFilename);
				notice.setNoticeFileRename(noticeFileRename);
				notice.setNoticeFilepath(noticeFilepath);
			}
			int result = nService.updateNotice(notice);
			//NoticeVO notice = new NoticeVO(noticeNo, noticeSubject, noticeContent, noticeFilename, noticeFileRename, noticeFilepath);
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
	@GetMapping("/delete")
	//@RequestMapping(value="/notice/delete",method=RequestMethod.GET)
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
	
	@GetMapping("/list")
	//@RequestMapping(value="/notice/list", method=RequestMethod.GET)
	public String showNoticeList(
			@RequestParam(value="page",defaultValue="1")int currentPage
			,Model model) {
		try {
			//int currentPage=request.getParameter("currentPage") !=null ?Integer.parseInt(request.getParameter("currentPage")):"1";
			List<NoticeVO> nList = nService.selectList(currentPage);
			int totalCount=nService.getTotalcount();
			Map<String, Integer>pageInfo = pageUtill.generatePageInfo(totalCount, currentPage);
			if(!nList.isEmpty()) {
				model.addAttribute("maxPage",pageInfo.get("maxPage"));
				model.addAttribute("startNavi",pageInfo.get("startNavi"));
				model.addAttribute("endNavi",pageInfo.get("endNavi"));
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
	
	@GetMapping("/detail")
	//@RequestMapping(value="/notice/detail", method=RequestMethod.GET)
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
	@GetMapping("/search")
	//@RequestMapping(value="/notice/search", method=RequestMethod.GET)
	public String showSearchList(
			 @RequestParam("searchCondition")String searchCondition
			,@RequestParam("searchKeyword")String searchKeyword
			,@RequestParam(value="page", defaultValue="1")int currentPage
			,Model model) {
				//1.VO 만들기
				//SearchVO search= new SearchVO(searchCondition, searchKeyword);
				//2.HashMap사용하기
			try {
				Map<String, String>paramMap= new HashMap<String,String>();
				paramMap.put("searchCondition",searchCondition);
				paramMap.put("searchKeyword",searchKeyword);
				List<NoticeVO> searchList = nService.searchListByKeyword(paramMap, currentPage);
				int totalCount=nService.getTotalcount(paramMap);
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
				model.addAttribute("searchList",searchList);
				model.addAttribute("maxPage",maxPage);
				model.addAttribute("startNavi",startNavi);
				model.addAttribute("endNavi",endNavi);
				model.addAttribute("searchCondition",searchCondition);
				model.addAttribute("searchKeyword",searchKeyword);
				return "notice/search";
				
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				model.addAttribute("errorMsg",e.getMessage());
				return "common/error";
			}
	}

	
}
