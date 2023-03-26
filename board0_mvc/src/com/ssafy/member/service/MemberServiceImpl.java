package com.ssafy.member.service;

import java.sql.SQLException;

import com.ssafy.member.dao.MemberDao;
import com.ssafy.member.dao.MemberDaoImpl;
import com.ssafy.member.dto.MemberDto;

public class MemberServiceImpl implements MemberService {

	MemberDao memberDao = MemberDaoImpl.getMemberDao();
	
	private MemberServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static MemberService memberService = new MemberServiceImpl();
	
	public static MemberService getInstance() {
		return memberService;
	}
	
	@Override
	public int idCheck(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int joinMember(MemberDto member) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws SQLException {
		// TODO Auto-generated method stub
		return memberDao.loginMember(userId, userPwd);
	}

}
