package com.tree.spring.member.service;

import com.tree.spring.member.domain.MemberVO;

public interface MemberService {
	
	/**
	 * 회원 정보 등록 Service
	 * @param MemberVO
	 * @return int
	 */
	int insertMember(MemberVO member);
	/**
	 * 회원정보 수정 Service
	 * @param MemberVO
	 * @return int
	 */
	
	int updatetMember(MemberVO member);
	/**
	 *회원 정보 삭제 Service
	 * @param member
	 * @return int
	 */
	
	int deleteMember(String memberId);
	/**
	 * 회원 로그인 Service
	 * @param MemberVO
	 * @return MemberVO
	 */
	
	MemberVO selectOneByLogin(MemberVO member);
	/**
	 * 회원 정보 아이디로 조회 Service
	 * @param String
	 * @return MemberVO
	 */
	MemberVO selectOneById(String memberId);
	
}
