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
	public @ResponseBody ModelAndView home(Model model) {		
		logger.info("Accessing home page");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public @ResponseBody ModelAndView homePage(Model model) {		
		logger.info("Accessing home page");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody ModelAndView login(Model model) {
		logger.info("Accessing login page");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");

		return mv;
	}
	
	@RequestMapping(value = "/layout", method = RequestMethod.GET)
	public @ResponseBody ModelAndView layout (Model model) {
		logger.info("Accessing login page");
		ModelAndView mv = new ModelAndView();
		mv.addObject("hasEntries",false);
		mv.addObject("hasCathegories",false);
		mv.addObject("hasEntryName",false);

		mv.setViewName("layout_editor");

		return mv;
	}
	
	 
}
