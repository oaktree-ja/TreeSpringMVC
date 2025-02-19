package com.tree.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tree.spring.member.domain.MemberVO;
import com.tree.spring.member.service.MemberService;
import com.tree.spring.member.service.impl.MemberServiceImpl;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	@RequestMapping(value="/main")
	public String showMain(HttpServletRequest req) {
//		request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req,res)
		return "main";
	}
	@RequestMapping(value="/member/insert", method=RequestMethod.GET)
	public String memberInsertForm() {
		return "member/insert";
	}
	@RequestMapping(value="/member/insert", method=RequestMethod.POST)
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
	@RequestMapping(value="/member/login",method=RequestMethod.POST)
	public String memberLogin(
			@RequestParam("memberId") String memberId
			,@RequestParam("memberPw") String memberPw
			,HttpSession session){
				try {
					MemberVO member = new MemberVO(memberId, memberPw);
					member = mService.selectOneByLogin(member);
					if(member !=null) {
						session.setAttribute("memberName", member.getMemberName());
						session.setAttribute("memberId", member.getMemberId());
						return "redirect:/";
					}else {
						return "common/error";
					}
				} catch (Exception e) {
					return "common/error";
				}
				
			}
			
}
