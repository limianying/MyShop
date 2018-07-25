package com.myshop.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.domain.Product;
import com.myshop.service.ProductService;
import com.myshop.service.impl.ProductServiceImpl;


public class ProductInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String currentPage = request.getParameter("currentPage");
		String cid = request.getParameter("cid");
		
		ProductService service = new ProductServiceImpl();
		Product productInfo = null;
		try {
			productInfo = service.findProductInfo(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("cid", cid);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("productInfo", productInfo);
		
		String pids = pid;
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie cookie:cookies) {
				if("pids".equals(cookie.getName())) {
					pids = cookie.getValue();
					
					String[] split = pids.split("-");
					List<String> asList = Arrays.asList(split);
					LinkedList<String> list = new LinkedList<String>(asList);
					
					if(list.contains(pid)) {
						list.remove(pid);
						list.addFirst(pid);
					}else {
						list.addFirst(pid);
						
					}
					
					StringBuffer sb = new StringBuffer();
					for(int i = 0;i<list.size();i++) {
						sb.append(list.get(i));
						sb.append("-");
					}
					
					pids = sb.substring(0, sb.length()-1);
					
				}
			}
		}
		
		Cookie cookie_pids = new Cookie("pids",pids);
		response.addCookie(cookie_pids);
		
		
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}