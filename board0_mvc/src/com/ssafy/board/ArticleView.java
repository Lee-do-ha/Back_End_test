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
 * Servlet implementation class ArticleView
 */
@WebServlet("/view")
public class ArticleView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int articleNo = Integer.parseInt(request.getParameter("viewno"));
		System.out.println(articleNo);
		ArticleDto articledto = ArticleDaoImpl.getArticle().viewArticle(articleNo);
		request.setAttribute("articledto", articledto);
		
		// View 페이지에 Forwarding
		String path = "/board/view.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
