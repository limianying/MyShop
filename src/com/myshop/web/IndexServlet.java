package com.myshop.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.domain.Product;
import com.myshop.service.ProductService;
import com.myshop.service.impl.ProductServiceImpl;


public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductService service = new ProductServiceImpl();
		List<Product> Hotproduct = null;
		try {
			Hotproduct = service.findHotProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<Product> NewProduct = null;
		try {
			NewProduct = service.findNewProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("NewProduct", NewProduct);
		request.setAttribute("Hotproduct", Hotproduct);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}