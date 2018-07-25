package com.myshop.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.service.AdminProductService;
import com.myshop.service.impl.AdminProductServiceImpl;
import com.myshop.utils.CommonUtils;


public class AddCategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname = request.getParameter("cname");
		String cid = CommonUtils.getUUID();
		
		AdminProductService service = new AdminProductServiceImpl();
		try {
			service.addCategory(cname,cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/adminCategoryUI");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
