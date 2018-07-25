package com.myshop.service.impl;

import java.sql.SQLException;

import com.myshop.dao.UserDao;
import com.myshop.domain.User;
import com.myshop.service.UserService;


public class UserServiceImpl implements UserService {

	@Override
	public boolean registerUser(User user) throws SQLException {
		UserDao dao = new UserDao();
		int i =	dao.registerUser(user);
		if(i > 0) {
			return true;
		}
		return true;
	}

	@Override
	public User login(String username, String password) throws SQLException {
		UserDao dao = new UserDao();
		User user = dao.login(username,password);
		return user;
	}

}
