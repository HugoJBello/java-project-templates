package com.hjbello.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.rjeschke.txtmark.Processor;


import com.hjbello.dao.PageEntry;
import com.hjbello.dao.PageEntryRepository;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	PageEntryRepository pageEntryRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {		
		logger.info("Accessing home page");
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(Model model) {		
		logger.info("Accessing home page");
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		logger.info("Accessing login page");
		return "login";
	}
	
	 
}
