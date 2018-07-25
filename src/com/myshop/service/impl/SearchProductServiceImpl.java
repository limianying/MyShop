package com.myshop.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.myshop.dao.SearchProductDao;
import com.myshop.domain.Product;
import com.myshop.service.SearchProductService;
import com.myshop.vo.Condition;


public class SearchProductServiceImpl implements SearchProductService {

	@Override
	public List<Product> searchProductByCondition(Condition condition) throws SQLException {
		SearchProductDao dao = new SearchProductDao();
		return dao.searchProductByCondition(condition);
		
	}


}
