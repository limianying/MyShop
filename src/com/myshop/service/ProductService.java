package com.myshop.service;

import java.sql.SQLException;
import java.util.List;

import com.myshop.domain.Product;
import com.myshop.vo.pageBean;


public interface ProductService {

	Product findProductByPid(String pid) throws SQLException;

	Product findProductInfo(String pid) throws SQLException;

	pageBean<Product> findPageBean(int currentPage, int currentCount,String cid) throws SQLException;

	List<Product> findHotProduct() throws SQLException;

	List<Product> findNewProduct()throws SQLException;

	pageBean<Product> findSearchByName(String cid)throws SQLException;

}
