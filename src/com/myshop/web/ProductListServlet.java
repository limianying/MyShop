package com.myshop.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.domain.Product;
import com.myshop.service.ProductService;
import com.myshop.service.impl.ProductServiceImpl;
import com.myshop.vo.pageBean;


public class ProductListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		
		//当前页
		String currentPage1 = request.getParameter("currentPage");
		if(currentPage1 == null) {
			currentPage1 = "1";
		}
		int currentPage = Integer.parseInt(currentPage1);
		//显示页数
		int currentCount = 12;
		
		
		pageBean<Product> pagebean = null;

		ProductService service = new ProductServiceImpl();
		try {
			pagebean = service.findPageBean(currentPage, currentCount,cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("pagebean", pagebean);
		
		
		List<Product> historyProductList = new ArrayList<Product>();
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie:cookies) {
				if("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();
					String[] split = pids.split("-");
					for(String pid:split) {
						try {
							Product pro = service.findProductByPid(pid);
							historyProductList.add(pro);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		
		
		
		request.setAttribute("historyProductList", historyProductList);
		
		request.getRequestDispatcher("/product_list.jsp").forward(request,response);
	
	

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}