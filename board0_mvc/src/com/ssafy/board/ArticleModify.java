package com.ssafy.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.board.dao.ArticleDaoImpl;
import com.ssafy.board.dto.ArticleDto;

/**
 * Servlet implementation class ArticleModify
 */
@WebServlet("/modify")
public class ArticleModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int articleNo = Integer.parseInt(request.getParameter("viewno"));
		ArticleDto articleDto = ArticleDaoImpl.getArticle().viewArticle(articleNo);
		request.setAttribute("articleDto", articleDto);
		
		// Modify 페이지에 Forwarding
		String path = "board/modify.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

}
