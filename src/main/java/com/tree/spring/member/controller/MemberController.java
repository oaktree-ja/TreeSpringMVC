package com.tree.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tree.spring.member.controller.dto.JoinRequest;
import com.tree.spring.member.controller.dto.LoginRequest;
import com.tree.spring.member.controller.dto.ModifyRequest;
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
	@GetMapping("/insert")
	public String memberInsertForm() {
		return "member/insert";
	}
	@PostMapping("/insert")
	public String memberInsert(
			@ModelAttribute JoinRequest member
			,HttpServletRequest request, HttpServletResponse response) {
		int result = mService.insertMember(member);
			if(result >0) {
				//성공시 메인페이지 (로그인 페이지) 로 이동
				return "redirect:/";
				
			}else {
				return "common/error";
			}
	}
	@PostMapping("/login")
	public String memberLogin(
			@ModelAttribute LoginRequest member
			,HttpSession session
			,Model model){
				try {
					MemberVO member1 = mService.selectOneByLogin(member);
					if(member1 !=null) {
						session.setAttribute("memberName", member1.getMemberName());
						session.setAttribute("memberId", member1.getMemberId());
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
	@GetMapping("/logout")
	public String memberLogout(HttpSession session) {
		if(session !=null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@GetMapping("/detail")
	public String memberMyPage(HttpSession session, Model model) {
		try {
			String memberId =(String) session.getAttribute("memberId");
			MemberVO member =mService.selectOneById(memberId);
			if(member !=null) {
				model.addAttribute("member",member); //기능을 아래 request.getRequestDispatcher와 동일하게 하는 것 
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
	
	@GetMapping("/delete")
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
	@GetMapping("/update")
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
	@PostMapping("/update")
	public String memberUpdate(
			@ModelAttribute ModifyRequest member
			,Model model) {
		try {
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
