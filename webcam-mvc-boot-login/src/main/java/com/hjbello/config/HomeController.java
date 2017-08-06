package com.hjbello.config;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hjbello.config.DetectForm;
import com.hjbello.restfulws.CapturedMovement;
import com.hjbello.restfulws.RequestCapture;
import com.hjbello.restfulws.SystemInfo;
import com.hjbello.restfulws.WebcamRestWS;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {		
		logger.info(".....");
		return "home";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {		
		return "login";
	}
	@RequestMapping(value = "/webcam", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN"})
	public String hello(Model model) {		
		return "webcam";
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home2(Model model) {		
		return "home";
	}
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String error(Model model) {		
		return "404";
	}
	
	@RequestMapping(value = "/webcam/home2", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN"})
	public String hello2(Model model) {		
		return "home2";
	}
	
	@RequestMapping(value = "/webcam/about", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN"})
	public ModelAndView about(Model model) {		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("about");
		return mv;
	}
	@RequestMapping(value = "/cam-movement", method=RequestMethod.GET)
	@Secured({"ROLE_ADMIN"})
	public ModelAndView camMovement() throws JsonProcessingException {
 	    ModelAndView mv = new ModelAndView();
 	    WebcamRestWS wcRest = new WebcamRestWS();
 	    SystemInfo sysInvo = wcRest.obtainWebcamInfoRestWS();
 	    mv.addObject("operativeSystem",sysInvo.getOperativeSystem());
	    mv.addObject("webcamInfo",sysInvo.getCamInfo());
	    mv.addObject("webcamResolution",sysInvo.getResolution());
	    mv.addObject("user",sysInvo.getSystemCamUser());
	    
	    
 	    mv.addObject("seconds","null");
		mv.setViewName("control_cam");
		return mv;
	    }
	
	@RequestMapping(value = "/cam-movement", method=RequestMethod.POST)
	@Secured({"ROLE_ADMIN"})
	public ModelAndView camMovement2(@ModelAttribute("detectForm") DetectForm detectForm) throws JsonProcessingException {
 	    ModelAndView mv = new ModelAndView();
 	    WebcamRestWS wcRest = new WebcamRestWS();
	    SystemInfo sysInvo = wcRest.obtainWebcamInfoRestWS();
	    mv.addObject("operativeSystem",sysInvo.getOperativeSystem());
	    mv.addObject("webcamInfo",sysInvo.getCamInfo());
	    mv.addObject("webcamResolution",sysInvo.getResolution());
	    mv.addObject("user",sysInvo.getSystemCamUser());
	    
//	    DetectMotion dm = new DetectMotion();
//	    dm.setStopInSeconds(detectForm.getSeconds());
//	    dm.record();
//	    
//	    mv.addObject("todaysFolder",dm.getTodaysFolder().split("Users")[1]);
 	    mv.addObject("seconds",detectForm.getSeconds());
		mv.setViewName("control_cam");
		return mv;
	    }

	 
}
