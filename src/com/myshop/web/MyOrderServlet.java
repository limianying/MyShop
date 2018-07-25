package com.myshop.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.myshop.domain.Order;
import com.myshop.domain.OrderItem;
import com.myshop.domain.Product;
import com.myshop.domain.User;
import com.myshop.service.OrderService;
import com.myshop.service.impl.OrderServiceImpl;


public class MyOrderServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		
		/*		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
			
		}
		OrderService service = new OrderServiceImpl();
		
		List<Order> orderList = new ArrayList<Order>();
		
		try {
			orderList = service.findAllOrderByUID(user.getUid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(orderList != null) {
			for (Order order : orderList) {
				String oid = order.getOid();
				try {
					List<Map<String, Object>> mapList = service.findAllOrderItemByOid(oid);
					
					for (Map<String, Object>  map : mapList) {
						try {
							
							OrderItem item = new OrderItem();
							BeanUtils.populate(item, map);
							
							Product product = new Product();
							BeanUtils.populate(product, map);
							
							item.setProduct(product);
							order.getOrderItems().add(item);
						} catch (IllegalAccessException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		session.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
		*/
		

		//获得我的订单
	
		HttpSession session = request.getSession();
	
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
			
		}
		
		OrderService service = new OrderServiceImpl();
		//查询该用户的所有的订单信息(单表查询orders表)
		//集合中的每一个Order对象的数据是不完整的 缺少List<OrderItem> orderItems数据
		List<Order> orderList = new ArrayList<Order>();;
		try {
			orderList = service.findAllOrderByUID(user.getUid());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//循环所有的订单 为每个订单填充订单项集合信息
		if(orderList!=null){
			
			for(Order order : orderList){
				//获得每一个订单的oid
				String oid = order.getOid();
				//查询该订单的所有的订单项---mapList封装的是多个订单项和该订单项中的商品的信息
				List<Map<String, Object>> mapList = null;
				try {
					mapList = service.findAllOrderItemByOid(oid);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//将mapList转换成List<OrderItem> orderItems 
				for(Map<String,Object> map : mapList){
					
					try {
						//从map中取出count subtotal 封装到OrderItem中
						OrderItem item = new OrderItem();
						//item.setCount(Integer.parseInt(map.get("count").toString()));
						BeanUtils.populate(item, map);
						//从map中取出pimage pname shop_price 封装到Product中
						Product product = new Product();
						BeanUtils.populate(product, map);
						//将product封装到OrderItem
						item.setProduct(product);
						//将orderitem封装到order中的orderItemList中
						order.getOrderItems().add(item);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
					
					
				}
	
			}
		}
		
		
		//orderList封装完整了
		request.setAttribute("orderList", orderList);
		
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
	
	

		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}


