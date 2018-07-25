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


public class AdminProductListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminProductService service = new AdminProductServiceImpl();
		List<Product> ProductList = null;
		try {
			ProductList = service.findAllProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		List<Category> categoryList = null;
		try {
			categoryList = service.findCategoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("ProductList", ProductList);
		
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}