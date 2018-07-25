package com.myshop.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if("zhangsan".equals(username) && username != null && "123456".equals(password) && password != null) {
			response.sendRedirect(request.getContextPath()+"/admin/welcome.jsp");
		}else {
			String LoginInfo = "账号或者密码错误";
			request.setAttribute("LoginInfo", LoginInfo);
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);;
		}
	
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}