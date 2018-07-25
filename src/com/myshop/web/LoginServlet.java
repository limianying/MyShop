package com.myshop.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.domain.User;
import com.myshop.service.UserService;
import com.myshop.service.impl.UserServiceImpl;


public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkimg = request.getParameter("checkImg");
		
		HttpSession session = request.getSession();
		UserService service = new UserServiceImpl();
		User user = null;
		
		String word = (String) session.getAttribute("checkcode_session");
		
		if(!word.equals(checkimg) || checkimg == null ) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		
		try {
			user = service.login(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String autoLogin = request.getParameter("autoLogin");
		
		if(user != null) {
			if(autoLogin != null) {
				//设置Cookie的登陆信息
				Cookie cookie_username = new Cookie("cookie_username",username);
				Cookie cookie_password = new Cookie("cookie_password",password);
				
				cookie_username.setMaxAge(60*60);
				cookie_password.setMaxAge(60*60);
				
				cookie_username.setPath(request.getContextPath());
				cookie_password.setPath(request.getContextPath());
				
				
				response.addCookie(cookie_username);
				response.addCookie(cookie_password);
			}
			
			
			
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/index");
		}
		else {
			request.setAttribute("loginInfo", "用户名错误或者密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);;
		}
	
	
		
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}