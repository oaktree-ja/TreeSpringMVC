package com.tree.spring.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tree.spring.member.controller.dto.JoinRequest;
import com.tree.spring.member.controller.dto.LoginRequest;
import com.tree.spring.member.controller.dto.ModifyRequest;
import com.tree.spring.member.domain.MemberVO;
import com.tree.spring.member.service.MemberService;
import com.tree.spring.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberStore mStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertMember(JoinRequest member) {
		
		int result = mStore.insertMember(session, member);
		return result;
	}

	@Override
	public int updateMember(ModifyRequest member) {
		int result= mStore.updateMember(session, member);
		return result;
	}

	@Override
	public int deleteMember(String memberId) {
		int result = mStore.deleteMember(session, memberId);
		return result;
	}

	@Override
	public MemberVO selectOneByLogin(LoginRequest member) {
		MemberVO result = mStore.selectOneByLogin(session, member);
		return result;
	}

	@Override
	public MemberVO selectOneById(String memberId) {
		MemberVO result = mStore.selectOneById(session, memberId);
		return result;
	}
	
}
