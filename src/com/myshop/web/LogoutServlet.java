package com.myshop.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		//移除Cookie
		Cookie cookie_username = new Cookie("cookie_username","");
		Cookie cookie_password = new Cookie("cookie_password","");
		cookie_username.setMaxAge(0);
		cookie_password.setMaxAge(0);
		
		response.addCookie(cookie_username);
		response.addCookie(cookie_password);
		
		
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}