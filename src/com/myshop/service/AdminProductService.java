package com.myshop.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.myshop.domain.Category;
import com.myshop.domain.Order;
import com.myshop.domain.Product;


public interface AdminProductService {

	List<Product> findAllProduct() throws SQLException;

	List<Category> findCategoryList() throws SQLException;

	void addProduct(Product product) throws SQLException;

	void deleteProduct(String pid) throws SQLException;

	Product findProductUI(String pid) throws SQLException;

	void updateProduct(Product product) throws SQLException;

	Category findCategoryListByCid(String cid) throws SQLException;

	void updateCategory(String cname,String cid)throws SQLException;

	void addCategory(String cname, String cid)throws SQLException;

	void deleteCategory(String cid)throws SQLException;

	List<Order> findAllOrder();

	List<Map<String, Object>> findOrderDetai(String oid);



}
