package com.tree.spring.member.controller.dto;

import org.springframework.web.bind.annotation.RequestParam;

public class JoinRequest {
	
	//insert.jsp 의 form 태그에 있는 input태그의 name 값이 dto의 필드 명과 
	// 같아야 한다.
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberAge;
	private String memberGender;
	private String memberEmail;
	private String memberPhone;
	private String memberAddress;
	
	
	
	public JoinRequest() {
		super();
	}

	

	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public String getMemberPw() {
		return memberPw;
	}



	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}



	public String getMemberName() {
		return memberName;
	}



	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}



	public String getMemberAge() {
		return memberAge;
	}



	public void setMemberAge(String memberAge) {
		this.memberAge = memberAge;
	}



	public String getMemberGender() {
		return memberGender;
	}



	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}



	public String getMemberEmail() {
		return memberEmail;
	}



	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}



	public String getMemberPhone() {
		return memberPhone;
	}



	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}



	public String getMemberAddress() {
		return memberAddress;
	}



	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}



	public JoinRequest(String memberId, String memberPw, String memberName, String memberAge, String memberGender,
			String memberEmail, String memberPhone, String memberAddress) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberAge = memberAge;
		this.memberGender = memberGender;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberAddress = memberAddress;
	}



	@Override
	public String toString() {
		return "JoinRequest [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberAge=" + memberAge + ", memberGender=" + memberGender + ", memberEmail=" + memberEmail
				+ ", memberPhone=" + memberPhone + ", memberAddress=" + memberAddress + "]";
	}
	
	

}
