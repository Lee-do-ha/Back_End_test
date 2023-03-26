package com.ssafy.member.service;

import java.sql.SQLException;

import com.ssafy.member.dto.MemberDto;

public interface MemberService {
	int idCheck(String userId) throws SQLException;
	int joinMember(MemberDto member) throws SQLException;
	MemberDto loginMember(String userId, String userPwd) throws SQLException;
	
}
