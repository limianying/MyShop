package com.myshop.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.domain.Cart;
import com.myshop.domain.CartItem;


public class DeleteCartItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart != null) {
			Map<String, CartItem> cartItems = cart.getCartItems();
			
			cart.setTotal(cart.getTotal()-cartItems.get(pid).getSubtotal());
			cartItems.remove(pid);
			cart.setCartItems(cartItems);
		}
		
		session.setAttribute("cart", cart);
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}