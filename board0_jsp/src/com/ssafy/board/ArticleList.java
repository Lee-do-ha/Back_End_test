package com.ssafy.board;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.board.dao.ArticleDaoImpl;
import com.ssafy.board.dto.ArticleDto;
import com.ssafy.jdbc.util.DBUtil;

@WebServlet("/list")
public class ArticleList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ArticleDto> list = ArticleDaoImpl.getArticle().ArticleList();
		
		request.setAttribute("articles", list);
		
		String path = "board/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	
	}
}
