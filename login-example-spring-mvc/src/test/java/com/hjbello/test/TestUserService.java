package com.hjbello.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hjbello.controllers.UserService;

public class TestUserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void getUsServ(){
		UserService us = new UserService();
		logger.info("User hjbello/1234 " + us.isValidUser("hjbello", "1234"));
		logger.info("User hjbello2/1234 " + us.isValidUser("hjbello2", "1234"));

	}
}
