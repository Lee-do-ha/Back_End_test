package com.ssafy.member.dao;

import java.sql.SQLException;

import com.ssafy.member.dto.MemberDto;

public interface MemberDao {
	int idCheck(String userId) throws SQLException;
	int joinMember(MemberDto member) throws SQLException;
	MemberDto loginMember(String userId, String userPwd) throws SQLException;
	
}
