package com.hjbello.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hjbello.dao.UserDaoImpl;

@Component(value = "userService")
public class UserService {
	
	UserDaoImpl userDaoImpl = new UserDaoImpl();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	public boolean isValidUser(String username, String password) {
		System.out.println(username);
		return userDaoImpl.isValidUser(username, password);
	}

}
