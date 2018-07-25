package com.myshop.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.myshop.domain.Category;
import com.myshop.domain.Product;
import com.myshop.service.AdminProductService;
import com.myshop.service.SearchProductService;
import com.myshop.service.impl.AdminProductServiceImpl;
import com.myshop.service.impl.SearchProductServiceImpl;
import com.myshop.vo.Condition;


public class SearchProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> properties = request.getParameterMap();
		Condition condition = new Condition();
		try {
			BeanUtils.populate(condition, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		List<Product> searchProductList = null;
		AdminProductService service = new AdminProductServiceImpl();
		List<Category> categoryList = null;
		try {
			categoryList = service.findCategoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		SearchProductService service1 = new SearchProductServiceImpl();
		try {
			searchProductList = service1.searchProductByCondition(condition);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("ProductList", searchProductList);
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);;
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}