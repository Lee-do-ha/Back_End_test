package com.ssafy.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.jdbc.util.DBUtil;
import com.ssafy.member.dto.MemberDto;

public class MemberDaoImpl implements MemberDao {

	
	private MemberDaoImpl() {
		
	}
	
	private static MemberDao memberDao = new MemberDaoImpl();
	
	public static MemberDao getMemberDao() {
		return memberDao;
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
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto memberDto = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select user_id, user_name from members where user_id = ? and user_password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return memberDto;
	}

}
