package com.tree.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tree.spring.member.domain.MemberVO;
import com.tree.spring.member.service.MemberService;
import com.tree.spring.member.service.impl.MemberServiceImpl;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	@RequestMapping(value="/main")
	public String showMain(HttpServletRequest req) {
//		request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req,res)
		return "main";
	}
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String memberInsertForm() {
		return "member/insert";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String memberInsert(
			@RequestParam("memberId") String memberId
			,@RequestParam("memberPw") String memberPw
			,@RequestParam("memberName") String memberName
			,@RequestParam("memberAge") String memberAge
			,@RequestParam("memberGender") String memberGender
			,@RequestParam("memberEmail") String memberEmail
			,@RequestParam("memberPhone") String memberPhone
			,@RequestParam("memberAddress") String memberAddress
			,HttpServletRequest request, HttpServletResponse response) {
		MemberVO member = new MemberVO(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress);	
		//MemberServiceImpl mService = new MemberServiceImpl(); 강한 결합
		
		int result = mService.insertMember(member);
			if(result >0) {
				//성공시 메인페이지 (로그인 페이지) 로 이동
//				response.sendRedirect("/");
				return "redirect:/";
				
			}else {
				return "common/error";
			}
		
//		String memberId= request.getParameter("memberId");
//		String memberPw = request.getParameter("memberPw");
//		String memberName =request.getParameter("memberName");
//		int memberAge =Integer.parseInt(request.getParameter("memberAge"));
//		String memberGender =request.getParameter("memberGender");		
//		String memberEmail =request.getParameter("memberEmail");
//		String memberPhone =request.getParameter("memberPhone");
//		String memberAddress =request.getParameter("memberAddr");
			
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String memberLogin(
			@RequestParam("memberId") String memberId
			,@RequestParam("memberPw") String memberPw
			,HttpSession session
			,Model model){
				try {
					MemberVO member = new MemberVO(memberId, memberPw);
					member = mService.selectOneByLogin(member);
					if(member !=null) {
						session.setAttribute("memberName", member.getMemberName());
						session.setAttribute("memberId", member.getMemberId());
						return "redirect:/";
					}else {
						model.addAttribute("errorMsg","존재하지 않는 정보입니다");
						return "common/error";
					}
				} catch (Exception e) {
					model.addAttribute("errorMsg",e.getMessage());
					return "common/error";
				}
				
			}
	@RequestMapping(value="/logout",method=RequestMethod.GET)		
	public String memberLogout(HttpSession session) {
		if(session !=null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String memberMyPage(HttpSession session, Model model) {
		try {
			String memberId =(String) session.getAttribute("memberId");
			MemberVO member =mService.selectOneById(memberId);
			if(member !=null) {
				//request.setAttribute("member",member); 원래는 이렇게 받았었는데  model을 활용하여 다른 방식으로 받는다 
				model.addAttribute("member",member); //기능을 아래 request.getRequestDispatcher와 동일하게 하는 것 
				//request.getRequestDispatcher("/WEB-INF/views/member/detail.jsp").forward(req,res);
				return "member/detail";
			}else {
				model.addAttribute("errorMsg","존재하지 않는 정보입니다");
				return "common/error";
			}
				
		} catch (Exception e) {
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String memberDelete(HttpSession session, Model model) {
		try {
			String memberId = (String) session.getAttribute("memberId");
			int result =mService.deleteMember(memberId);
			
			if(result>0) {
				//로그아웃
				return "redirect:/member/logout";
			}else {
				model.addAttribute("errorMsg","존재하지 않는 정보입니다");
				return "common/error";
			}
			
		} catch (Exception e) {
			model.addAttribute("errorMsg",e.getMessage());
			return "error";
		}
	}
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String memberUpdateForm(HttpSession session, Model model) {
		try {
			String memberId = (String) session.getAttribute("memberId");;
			MemberVO member=mService.selectOneById(memberId);
			if(member !=null) {
				model.addAttribute("member", member);
				return "member/update";
			}else {
				model.addAttribute("errorMsg","존재하지 않는 정보입니다");
				return "common/error";
			}
		} catch (Exception e) {
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String memberUpdate(@RequestParam("memberId")String memberId
			,@RequestParam("memberPw")String memberPw
			,@RequestParam("memberEmail")String memberEmail
			,@RequestParam("memberPhone")String memberPhone
			,@RequestParam("memberAddress")String memberAddress
			,Model model) {
		try {
			MemberVO member= new MemberVO(memberId, memberPw,memberEmail,memberPhone,memberAddress);
			int result = mService.updateMember(member);
			if(result>0) {
				return"redirect:/member/detail";
			}else {
				model.addAttribute("errorMsg","서비스가 완료되지 않았습니다");
				return "common/error";
			}
		} catch (Exception e) {
			e.printStackTrace();// 콘솔창에 오류 메시지 나오게 하는 명령문
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
		
	}
	
	
}
