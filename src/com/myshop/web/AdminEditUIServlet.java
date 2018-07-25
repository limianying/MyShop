package com.myshop.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.domain.Category;
import com.myshop.domain.Product;
import com.myshop.service.AdminProductService;
import com.myshop.service.impl.AdminProductServiceImpl;


public class AdminEditUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		Product product = new Product();
		List<Category> categoryList = null;
		AdminProductService service = new AdminProductServiceImpl();
		try {
			product = service.findProductUI(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			categoryList = service.findCategoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("product", product);
		
		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
		
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}