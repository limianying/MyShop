package com.myshop.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.myshop.domain.Product;
import com.myshop.utils.DataSourceUtils;
import com.myshop.vo.Condition;



public class SearchProductDao {

	public List<Product> searchProductByCondition(Condition condition) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<String> list = new ArrayList<String>();
		
		String sql = "select * from product where 1 =1";
		
		if(condition.getPname()!=null && !condition.getPname().trim().equals("")) {
			sql += " and pname like ? ";
			list.add("%"+condition.getPname().trim()+"%");
		}
		if(condition.getIs_hot()!=null && !condition.getIs_hot().trim().equals("")) {
			sql += " and is_hot = ? ";
			list.add(condition.getIs_hot().trim());
		}
		if(condition.getCid()!=null && !condition.getCid().trim().equals("")) {
			sql += " and cid = ? ";
			list.add(condition.getCid().trim());
		}
		
		List<Product> searchProductList = runner.query(sql, new BeanListHandler<Product>(Product.class), list.toArray());
		
		return searchProductList;
		
		
		
		
		
		
		
		
		
		
		
	}

}
