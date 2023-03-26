package com.ssafy.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.board.dto.ArticleDto;
import com.ssafy.jdbc.util.DBUtil;

public class ArticleDaoImpl implements ArticleDao {

	private static ArticleDao articleDao;
	
	public static ArticleDao getArticle() {
		if(articleDao == null) {
			articleDao = new ArticleDaoImpl();
		}
		return articleDao;
	}
	@Override
	public int writeArticle(ArticleDto article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into board(user_id, subject, content) \n");
			sql.append("values (?, ?, ?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, article.getUserId());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	@Override
	public List<ArticleDto> ArticleList() {
		List<ArticleDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from board\n");
			sql.append("order by article_no desc limit 0, 20\n");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ArticleDto article = new ArticleDto();
				article.setArticleNo(rs.getInt("article_no"));
				article.setUserId(rs.getString("user_id"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setHit(rs.getInt("hit"));
				article.setRegisterTime(rs.getString("register_time"));
				
				list.add(article);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public ArticleDto viewArticle(int articleNo) {
		ArticleDto articledto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select article_no, user_id, subject, content, hit, register_time \n");
			sql.append("from board \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articledto = new ArticleDto();
				articledto.setArticleNo(rs.getInt("article_no"));
				articledto.setUserId(rs.getString("user_id"));
				articledto.setSubject(rs.getString("subject"));
				articledto.setContent(rs.getString("content"));
				articledto.setHit(rs.getInt("hit"));
				articledto.setRegisterTime(rs.getString("register_time"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return articledto;
	}
	
	@Override
	public void updateHit(int articleNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board \n");
			sql.append("set hit = hit + 1 \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}	
	}

	@Override
	public int modifyArticle(ArticleDto articleDto) {
		
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board \n");
			sql.append("set subject = ?, content = ?, register_time = CURRENT_TIMESTAMP \n");
			sql.append("where article_no = ? and user_id = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, articleDto.getSubject());
			pstmt.setString(2, articleDto.getContent());
			pstmt.setInt(3, articleDto.getArticleNo());
			if(articleDto.getUserId() == null) return 0;
			pstmt.setString(4, articleDto.getUserId());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		return cnt;
	}
	@Override
	public int removeArticle(int articleNo) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from board \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			cnt = pstmt.executeUpdate();

			System.out.println("글 삭제");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		return cnt;
	}
	
}
