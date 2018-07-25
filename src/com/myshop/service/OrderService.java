package com.myshop.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.myshop.domain.Order;


public interface OrderService {

	void submitOrder(Order order);

	void updateAddr(Order order) throws SQLException;

	void updateOrderState(String r6_Order)throws SQLException;

	List<Order> findAllOrderByUID(String uid)throws SQLException;

	List<Map<String, Object>> findAllOrderItemByOid(String oid)throws SQLException;

}
