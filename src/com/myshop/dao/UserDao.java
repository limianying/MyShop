package com.myshop.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.myshop.domain.User;
import com.myshop.utils.DataSourceUtils;

public class UserDao {

	public int registerUser(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		
		return runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
				user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(),
				user.getCode());
	}

	public User login(String username, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ?";
		User user = runner.query(sql, new BeanHandler<User>(User.class),username,password);
		return user;
	}

}
