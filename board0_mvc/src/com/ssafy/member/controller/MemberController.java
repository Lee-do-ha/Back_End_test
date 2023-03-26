package com.ssafy.member.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.member.dao.MemberDao;
import com.ssafy.member.dao.MemberDaoImpl;
import com.ssafy.member.dto.MemberDto;
import com.ssafy.member.service.MemberService;
import com.ssafy.member.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MemberService memberService = MemberServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String path = "";
		if("login".equals(action)) {
			String userId = request.getParameter("userid");
			String userPwd = request.getParameter("userpwd");
			
			try {
				MemberDto memberDto = memberService.loginMember(userId, userPwd);
				System.out.println(memberDto);
				if(memberDto != null) {
					request.setAttribute("userinfo", memberDto);
					path ="/index.jsp";
					forward(request, response, path);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
