package com.myshop.web;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.domain.Cart;
import com.myshop.domain.CartItem;
import com.myshop.domain.Order;
import com.myshop.domain.OrderItem;
import com.myshop.domain.User;
import com.myshop.service.OrderService;
import com.myshop.service.impl.OrderServiceImpl;
import com.myshop.utils.CommonUtils;


public class SubmitCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		// 封装一个订单
		Order order = new Order();

		// 1.oid
		order.setOid(CommonUtils.getUUID());
		// 2.ordertime
		order.setOrdertime(new Date());
		// 3.total
		order.setTotal(cart.getTotal());
		// 4.state,1为已付款，0为未付款
		order.setState(0);
		// 5.address
		order.setAddress(null);
		// 6.name
		order.setName(null);
		// 7.telephone
		order.setTelephone(null);
		// 8.uid
		order.setUser(user);
		// 9.List<OrderItem> orderItems = new ArrayList<OrderItem>();
		Map<String, CartItem> cartItems = cart.getCartItems();
		
		for(Map.Entry<String, CartItem> entry:cartItems.entrySet()) {
			OrderItem orderItem = new OrderItem();
			CartItem cartitem = entry.getValue();
			//`itemid` varchar(32) NOT NULL,
			orderItem.setItemid(CommonUtils.getUUID());
			  //`count` int(11) DEFAULT NULL,
			orderItem.setCount(cartitem.getBuynum());
			  //`subtotal` double DEFAULT NULL,
			orderItem.setSubtotal(cartitem.getSubtotal());
			 // `pid` varchar(32) DEFAULT NULL,
			orderItem.setProduct(cartitem.getProduct());
			 // `oid` varchar(32) DEFAULT NULL,
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		
		OrderService service = new OrderServiceImpl();

		service.submitOrder(order);
		
		session.setAttribute("order",order);
		
		response.sendRedirect(request.getContextPath()+"/order_info.jsp");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}