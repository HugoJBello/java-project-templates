package com.hjbello.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private LoginDelegate loginDelegate;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin()
	{
		ModelAndView model = new ModelAndView("login");
		LoginBean loginBean = new LoginBean();
		model.addObject("loginBean", loginBean);
		return model;
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView listNotes(@ModelAttribute("loginBean") LoginBean loginBean) {
		ModelAndView model= null;
		LoginDelegate us = new LoginDelegate();
		try
		{
			System.out.println(loginBean.getPassword());
			boolean isValidUser = us.isValidUser(loginBean.getUsername(), loginBean.getPassword());
			if(isValidUser)
			{
				System.out.println("User Login Successful");
				model = new ModelAndView("welcome");
			}
			else
			{
				model = new ModelAndView("login");
				model.addObject("loginBean", loginBean);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}


}
