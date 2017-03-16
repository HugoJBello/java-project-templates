package config;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(Model model) {		
		return "hello";
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home2(Model model) {		
		return "hello";
	}
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String error(Model model) {		
		return "404";
	}
	
	@RequestMapping(value = "/hello/home2", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN"})
	public String hello2(Model model) {		
		return "home2";
	}

}
