package com.myshop.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.domain.Cart;
import com.myshop.domain.CartItem;
import com.myshop.domain.Product;
import com.myshop.service.ProductService;
import com.myshop.service.impl.ProductServiceImpl;

public class AddToCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProductService service = new ProductServiceImpl();

		String pid = request.getParameter("pid");
		Product product = null;
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));

		try {
			product = service.findProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 计算小计
		double subtotal = product.getShop_price() * buyNum;

		// 封装购物项
		CartItem cartitem = new CartItem();
		cartitem.setBuynum(buyNum);
		cartitem.setProduct(product);
		cartitem.setSubtotal(subtotal);

		// 获得购物车
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
		}
		Map<String, CartItem> cartItems = cart.getCartItems();

		double newSubtotal = 0d;
		// 如果购物车中存在该商品

		if (cartItems.containsKey(pid)) {
			//重复的购物项
			CartItem cartItem = cartItems.get(pid);
			
			int oldBuyNum = cartItem.getBuynum();
			oldBuyNum += buyNum;

			cartItem.setBuynum(oldBuyNum);

			// 计算小计
			double oldsubtotal = cartItem.getSubtotal();

		    newSubtotal = product.getShop_price() * buyNum;

			cartItem.setSubtotal(oldsubtotal + newSubtotal);

			cart.setCartItems(cartItems);
		} else {
			// 如果车中没有该商品
			cart.getCartItems().put(product.getPid(), cartitem);
			newSubtotal = buyNum * product.getShop_price();
		}

		// 计算总计
		double total = cart.getTotal() + newSubtotal;
		cart.setTotal(total);

		// 封装购物车
		session.setAttribute("cart", cart);

		// 转发跳转页面
		request.getRequestDispatcher("/cart.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}