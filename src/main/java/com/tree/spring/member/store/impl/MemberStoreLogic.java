package com.tree.spring.member.store.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tree.spring.member.domain.MemberVO;
import com.tree.spring.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{

	@Override
	public int insertMember(SqlSession session, MemberVO member) {
		int result = session.insert("MemberMapper.insertMember",member);
		return result;
	}

	@Override
	public int updateMember(SqlSession session, MemberVO member) {
		int result= session.update("MemberMapper.updateMember", member);
		return result;
	}

	@Override
	public int deleteMember(SqlSession session, String memberId) {
		int result = session.delete("MemberMapper.deleteMember",memberId);
		return result;
	}

	@Override
	public MemberVO selectOneByLogin(SqlSession session, MemberVO member) {
		MemberVO result = session.selectOne("MemberMapper.selectOneByLogin", member);
		return result;
	}

	@Override
	public MemberVO selectOneById(SqlSession session, String memberId) {
		MemberVO result = session.selectOne("MemberMapper.selectOneById",memberId);
		return result;
	}

}
