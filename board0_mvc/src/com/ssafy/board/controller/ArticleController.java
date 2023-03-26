package com.ssafy.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.board.dto.ArticleDto;
import com.ssafy.board.service.ArticleService;
import com.ssafy.board.service.ArticleServiceImpl;

/**
 * Servlet implementation class ArticleController
 */
@WebServlet("/article")
public class ArticleController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	ArticleService articleService = ArticleServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String path ="";
		
		if("list".equals(action)) {
			List<ArticleDto> list = articleService.ArticleList();
			request.setAttribute("articles", list);
			path = "/board/list.jsp";
			forward(request, response, path);	
		}else if("mvwrite".equals(action)) {
			path = "/board/write.jsp";
			forward(request, response, path);
		}else if("register".equals(action)) {
			ArticleDto article = new ArticleDto();
			article.setUserId(request.getParameter("userid"));
			article.setSubject(request.getParameter("subject"));
			article.setContent(request.getParameter("content"));
			
			int cnt = articleService.writeArticle(article);
			List<ArticleDto> list = articleService.ArticleList();
			request.setAttribute("articles", list);
			path = "/article?action=list";
			reDirect(request, response, path);
		}else if(action.startsWith("view")) {
			int articleNo = Integer.parseInt(request.getParameter("viewno"));
			System.out.println(articleNo);
			ArticleDto article = articleService.viewArticle(articleNo);
			request.setAttribute("articledto", article);
			path = "/board/view.jsp";
			forward(request, response, path);
		}else if("delete".equals(action)) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	private void reDirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}
	
}
