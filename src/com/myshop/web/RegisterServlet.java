package com.myshop.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.myshop.domain.User;
import com.myshop.service.UserService;
import com.myshop.service.impl.UserServiceImpl;
import com.myshop.utils.CommonUtils;


public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> properties = request.getParameterMap();
		
		User user = new User();
		
		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(Class clazz, Object value) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parse = null;
				try {
					parse = sdf.parse(value.toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return parse;
			}
		}, Date.class);
		try {
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//开始封装未被封装的属性
		user.setUid(CommonUtils.getUUID());
		user.setTelephone(null);
		user.setState(1);
		user.setCode(CommonUtils.getUUID());
		UserService service = new UserServiceImpl();
		boolean isRegister = false;
		try {
			isRegister = service.registerUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(isRegister == true) {
			//注册成功
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else {
			//注册失败
			response.sendRedirect(request.getContextPath()+"/register.jsp");
		}
		
	
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}