package com.myshop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.myshop.domain.Product;
import com.myshop.utils.DataSourceUtils;

public class ProductDao {

	public Product findProductByPid(String pid) throws SQLException {

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid = ?";
		Product productList = runner.query(sql, new BeanHandler<Product>(Product.class), pid);

		return productList;
	}

	public Product findProductInfo(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid = ?";
		Product productInfo = runner.query(sql, new BeanHandler<Product>(Product.class), pid);
		return productInfo;
	}

	public int getTotalCount(String cid) throws SQLException {
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid = ?";
		Long totalCount = (Long)runner.query(sql, new ScalarHandler(),cid);
		return totalCount.intValue();
	}

public int getTotalCount1(String cid) throws SQLException {
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where 1 = 1 and pname like ?";
		cid = "%"+cid.trim()+"%";
		Long totalCount = (Long)runner.query(sql, new ScalarHandler(),cid);
		return totalCount.intValue();
	}
	
	public List<Product> findProductListForPagebean(int index, int currentCount,String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid = ? limit ?,?";
		List<Product> productList = runner.query(sql, new BeanListHandler<Product>(Product.class),cid,index,currentCount);
		return productList;
	}
	
	public List<Product> findProductListForPagebean1(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where 1 = 1 and pname like ?";
		cid = "%"+cid.trim()+"%";
		List<Product> productList = runner.query(sql, new BeanListHandler<Product>(Product.class),cid);
		return productList;
	}
	

	public List<Product> findHotProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot = 1 limit 0,9";
		List<Product> Hotproduct = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return Hotproduct;
	}

	public List<Product> findNewProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product order by pdate desc limit 0,9";
		List<Product> NewProduct = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return NewProduct;
	}



}
