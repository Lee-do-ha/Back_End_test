package com.ssafy.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.board.dao.ArticleDaoImpl;
import com.ssafy.board.dto.ArticleDto;

@WebServlet("/register")
public class ArticleRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 1. form 데이터 들고오기
		ArticleDto articleDto = new ArticleDto();
		String viewno = request.getParameter("articleNo");
		if (viewno != null)
			articleDto.setArticleNo(Integer.parseInt(viewno));
		articleDto.setUserId(request.getParameter("userid"));
		articleDto.setSubject(request.getParameter("subject"));
		articleDto.setContent(request.getParameter("content"));

		System.out.println(articleDto.toString());

		// DB에 저장
		int cnt = viewno == null ? ArticleDaoImpl.getArticle().writeArticle(articleDto)
				: ArticleDaoImpl.getArticle().modifyArticle(articleDto);

		List<ArticleDto> list = ArticleDaoImpl.getArticle().ArticleList();

		request.setAttribute("articles", list);

		// 결과 페이지 호출
		String path = "board/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);

	}

}
