package com.hjbello.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component 
public class LoginDelegate
{
	//@Autowired
	private UserService userService = new UserService();

	public UserService getUserService()
	{
		return this.userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public boolean isValidUser(String username, String password) throws SQLException
	{
	    return userService.isValidUser(username, password);
	}
}

