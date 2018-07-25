package com.myshop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.myshop.domain.Category;
import com.myshop.domain.Order;
import com.myshop.domain.Product;
import com.myshop.utils.DataSourceUtils;



public class AdminProductDao {

	public List<Product> findAllProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product";
		List<Product> ProductList = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return ProductList;
	}

	public List<Category> findCategoryList() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		List<Category> categoryList = runner.query(sql, new BeanListHandler<Category>(Category.class));
		return categoryList;
	}

	public void addProduct(Product product) throws SQLException {

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		runner.update(sql, product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(),
				product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(),
				product.getCid());
	}

	public void deleteProduct(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from product where pid =?";
		runner.update(sql, pid);

	}

	public Product findProductUI(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner();
		Connection conn = DataSourceUtils.getConnection();
		String sql = "select * from product where pid = ?";
		Product product = runner.query(conn, sql, new BeanHandler<Product>(Product.class), pid);
		return product;
	}

	public void updateProduct(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid =?";
		runner.update(sql, product.getPname(), product.getMarket_price(), product.getShop_price(), product.getPimage(),
				product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCid(),
				product.getPid());
	}

	public Category findCategoryListByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category where cid = ?";
		Category category = runner.query(sql, new BeanHandler<Category>(Category.class), cid);
		return category;

	}

	public void updateCategory(String cname, String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update category set cname = ? where cid = ?";
		runner.update(sql, cname, cid);

	}

	public void addCategory(String cname, String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values(?,?)";
		runner.update(sql, cid, cname);

	}

	public void addCategory(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from category where cid = ?";
		runner.update(sql, cid);

	}

	public List<Order> findAllOrder() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders";
		List<Order> orderList = runner.query(sql, new BeanListHandler<Order>(Order.class));

		return orderList;
	}

	public List<Map<String, Object>> findOrderDetai(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select p.pimage,p.pname,p.shop_price,o.count,o.subtotal "
				+ "from product p,orderItem o "
				+ "where p.pid = o.pid and o.oid = ?";
		
		List<Map<String, Object>> mapList = runner.query(sql, new MapListHandler(),oid);
		return mapList;
		
		
	}

}
