package com.myshop.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.myshop.dao.ProductDao;
import com.myshop.domain.Product;
import com.myshop.service.ProductService;
import com.myshop.vo.pageBean;


public class ProductServiceImpl implements ProductService {

	@Override
	public Product findProductByPid(String pid) throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findProductByPid(pid);
	}

	@Override
	public Product findProductInfo(String pid) throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findProductInfo(pid);
	}

	@Override
	public pageBean<Product> findPageBean(int currentPage, int currentCount,String cid) throws SQLException {
		ProductDao dao = new ProductDao();
		
		pageBean<Product> pagebean = new pageBean<Product>();
		
		pagebean.setCurrentPage(currentPage);
		pagebean.setCurrentCount(currentCount);
		pagebean.setCid(cid);
		
		int totalCount = dao.getTotalCount(cid);
		pagebean.setTotalCount(totalCount);
		
		//封装总页数(向上取整)
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		pagebean.setTotalPage(totalPage);
		
		//封装productList
		int index = (currentPage-1)*currentCount;
		List<Product> productList = dao.findProductListForPagebean(index,currentCount,cid);
		pagebean.setProductList(productList);
		return pagebean;
	}

	@Override
	public List<Product> findHotProduct() throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findHotProduct();
	}

	@Override
	public List<Product> findNewProduct() throws SQLException {
		ProductDao dao = new ProductDao();
		
		return dao.findNewProduct();
	}

	public pageBean<Product> findSearchByName(String cid) throws SQLException {
			ProductDao dao = new ProductDao();
			pageBean<Product> pagebean = new pageBean<Product>();
			
			List<Product> productList = dao.findProductListForPagebean1(cid);
			pagebean.setProductList(productList);
			return pagebean;
		}
		
		
		
		
		
		
		
		
	}

