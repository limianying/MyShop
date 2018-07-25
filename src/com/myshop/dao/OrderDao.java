package com.myshop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.myshop.domain.Order;
import com.myshop.domain.OrderItem;
import com.myshop.utils.DataSourceUtils;



public class OrderDao {

	public void addOrder(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();

		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();
		/*
		 * `oid` varchar(32) NOT NULL, `ordertime` datetime DEFAULT NULL, `total` double
		 * DEFAULT NULL, `state` int(11) DEFAULT NULL, `address` varchar(30) DEFAULT
		 * NULL, `name` varchar(20) DEFAULT NULL, `telephone` varchar(20) DEFAULT NULL,
		 * `uid` varchar(32) DEFAULT NULL,
		 */

		runner.update(conn, sql, order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(),
				order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());

	}

	public void addOrderItem(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();

		String sql = "insert into orderitem values(?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();

		List<OrderItem> orderItems = order.getOrderItems();

		/*
		 * `itemid` varchar(32) NOT NULL, `count` int(11) DEFAULT NULL, `subtotal`
		 * double DEFAULT NULL, `pid` varchar(32) DEFAULT NULL, `oid` varchar(32)
		 * DEFAULT NULL,
		 */

		for (OrderItem orderItem : orderItems) {
			runner.update(conn, sql, orderItem.getItemid(), orderItem.getCount(), orderItem.getSubtotal(),
					orderItem.getProduct().getPid(), orderItem.getOrder().getOid());
		}

	}

	public void updateAddr(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "update orders set address=?,name=?,telephone=? where oid=?";
		runner.update(sql, order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
		
		
	}

	public void updateOrderState(String r6_Order) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "update orders set state = ? where oid = ? ";
		runner.update(sql,1,r6_Order);
		
	}

	public List<Order> findAllOrderByUID(String uid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid = ?";
		List<Order> orderList = runner.query(sql, new BeanListHandler<Order>(Order.class), uid);
		return orderList;
		
	}

	public List<Map<String, Object>> findAllOrderItemByOid(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select i.count,i.subtotal,p.pimage,p.pname,p.shop_price "
				+ "from orderitem i,product p where i.pid=p.pid and i.oid=?";
		
		List<Map<String, Object>> mapList = runner.query(sql, new MapListHandler(), oid);
		return mapList;
		
	}


}
