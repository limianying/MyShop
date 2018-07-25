package com.myshop.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.domain.Product;
import com.myshop.service.ProductService;
import com.myshop.service.impl.ProductServiceImpl;
import com.myshop.vo.pageBean;


public class IndexSearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String pname = request.getParameter("searchname");
		String cid = request.getParameter("cid");
		
		pageBean<Product> pagebean = null;
		
		ProductService service = new ProductServiceImpl();
		try {
			pagebean = service.findSearchByName(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("pagebean", pagebean);
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}