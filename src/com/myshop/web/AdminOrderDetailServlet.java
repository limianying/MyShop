package com.myshop.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.myshop.service.AdminProductService;
import com.myshop.service.impl.AdminProductServiceImpl;

public class AdminOrderDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		String oid = request.getParameter("oid");
		
		AdminProductService service = new AdminProductServiceImpl();
		
		List<Map<String,Object>> mapList = service.findOrderDetai(oid);
		
		Gson gson = new Gson();
		String json = gson.toJson(mapList);
		
		/*
		 * [{"pimage":"products/1/c_0001.jpg","shop_price":1299.0,"pname":"小米 4c 标准版","subtotal":1299.0,"count":1},
		 * {"pimage":"products/1/c_0012.jpg","shop_price":2499.0,"pname":"华为 麦芒4","subtotal":2499.0,"count":1}]
		 */
		
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write(json);
		
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}