package com.myshop.service;

import java.sql.SQLException;
import java.util.List;

import com.myshop.domain.Product;
import com.myshop.vo.Condition;


public interface SearchProductService {

	List<Product> searchProductByCondition(Condition condition) throws SQLException;

}
