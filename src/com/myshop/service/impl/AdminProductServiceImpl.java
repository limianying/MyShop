package com.myshop.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.myshop.dao.AdminProductDao;
import com.myshop.domain.Category;
import com.myshop.domain.Order;
import com.myshop.domain.Product;
import com.myshop.service.AdminProductService;


public class AdminProductServiceImpl implements AdminProductService {

	@Override
	public List<Product> findAllProduct() throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findAllProduct();
	}

	@Override
	public List<Category> findCategoryList() throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findCategoryList();
	}

	@Override
	public void addProduct(Product product) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.addProduct(product);
		
	}

	@Override
	public void deleteProduct(String pid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.deleteProduct(pid);
		
	}

	@Override
	public Product findProductUI(String pid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findProductUI(pid);
	}

	@Override
	public void updateProduct(Product product) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.updateProduct(product);
	}

	@Override
	public Category findCategoryListByCid(String cid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findCategoryListByCid(cid);
		
	}

	@Override
	public void updateCategory(String cname,String cid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.updateCategory(cname,cid);
		
		
		
	}

	@Override
	public void addCategory(String cname, String cid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.addCategory(cname,cid);
		
	}

	@Override
	public void deleteCategory(String cid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.addCategory(cid);
		
	}

	@Override
	public List<Order> findAllOrder() {
		AdminProductDao dao = new AdminProductDao();
		List<Order> orderList = null;
		try {
			orderList = dao.findAllOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<Map<String, Object>> findOrderDetai(String oid) {
		AdminProductDao dao = new AdminProductDao();
		List<Map<String, Object>> mapList = null;
		try {
			mapList = dao.findOrderDetai(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapList;
	}

}
