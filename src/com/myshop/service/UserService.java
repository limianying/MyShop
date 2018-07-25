package com.myshop.service;

import java.sql.SQLException;

import com.myshop.domain.User;


public interface UserService {

	boolean registerUser(User user) throws SQLException;

	User login(String username, String password) throws SQLException;

}
