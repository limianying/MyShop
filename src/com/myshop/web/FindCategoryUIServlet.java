package com.myshop.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.domain.Category;
import com.myshop.service.AdminProductService;
import com.myshop.service.impl.AdminProductServiceImpl;


public class FindCategoryUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		AdminProductService service = new AdminProductServiceImpl();
		Category category = null;
		try {
			category = service.findCategoryListByCid(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("category", category);
		request.getRequestDispatcher("/admin/category/edit.jsp").forward(request, response);
	
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	
	}
}