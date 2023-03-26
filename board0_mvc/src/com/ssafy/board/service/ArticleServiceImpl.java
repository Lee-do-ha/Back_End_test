package com.ssafy.board.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.board.dao.ArticleDao;
import com.ssafy.board.dao.ArticleDaoImpl;
import com.ssafy.board.dto.ArticleDto;
import com.ssafy.jdbc.util.DBUtil;

public class ArticleServiceImpl implements ArticleService {
	
	ArticleDao articledao = ArticleDaoImpl.getArticle();
	
	private static ArticleService articleService = new ArticleServiceImpl();
	
	private ArticleServiceImpl() {
		
	}
	
	public static ArticleService getInstance() {
		return articleService;
	}
	@Override
	public int writeArticle(ArticleDto article) {
		// TODO Auto-generated method stub
		return articledao.writeArticle(article);
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
		// TODO Auto-generated method stub
		return articledao.viewArticle(articleNo);
	}

	@Override
	public int modifyArticle(ArticleDto articleDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeArticle(int articleNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateHit(int articleNo) {
		// TODO Auto-generated method stub

	}

}
