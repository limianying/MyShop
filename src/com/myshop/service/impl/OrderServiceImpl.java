package com.myshop.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.myshop.dao.OrderDao;
import com.myshop.domain.Order;
import com.myshop.service.OrderService;
import com.myshop.utils.DataSourceUtils;


public class OrderServiceImpl implements OrderService{

	@Override
	public void submitOrder(Order order) {
		//开启事务管理
		OrderDao dao = new OrderDao();
		
		try {
			DataSourceUtils.startTransaction();
			
			dao.addOrder(order);
			dao.addOrderItem(order);
			
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

	@Override
	public void updateAddr(Order order) throws SQLException {

		OrderDao dao = new OrderDao();
		dao.updateAddr(order);
	}

	@Override
	public void updateOrderState(String r6_Order) throws SQLException {
		OrderDao dao = new OrderDao();
		dao.updateOrderState(r6_Order);
		
		
	}

	@Override
	public List<Order> findAllOrderByUID(String uid) throws SQLException {
		OrderDao dao = new OrderDao();
	return dao.findAllOrderByUID(uid);
	}

	@Override
	public List<Map<String, Object>> findAllOrderItemByOid(String oid) throws SQLException {
		OrderDao dao = new OrderDao();
		return dao.findAllOrderItemByOid(oid);
	}
}
