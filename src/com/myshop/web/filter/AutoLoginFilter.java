package com.myshop.web.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.domain.User;
import com.myshop.service.UserService;
import com.myshop.service.impl.UserServiceImpl;


public class AutoLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		// 转型为与协议相关对象
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		HttpSession session = request.getSession();
		String cookie_username = null;
		String cookie_password = null;

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("cookie_username".equals(cookie.getName())) {
					cookie_username = cookie.getValue();
				}
				if ("cookie_password".equals(cookie.getName())) {
					cookie_password = cookie.getValue();
				}

			}
		}
		if (cookie_username != null && cookie_password != null) {
			UserService service = new UserServiceImpl();
			User user = null;
			try {
				user = service.login(cookie_username, cookie_password);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (user != null) {
				session.setAttribute("user", user);
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
