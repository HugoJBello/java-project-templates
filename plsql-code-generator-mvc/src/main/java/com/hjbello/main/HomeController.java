package com.hjbello.main;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("codeForm", new CodeForm());

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}

	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public String home2(Locale locale, Model model) {
		logger.info("--------.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("codeForm",new CodeForm());
		model.addAttribute("serverTime", formattedDate );

		return "home";
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ModelAndView listNotes(@ModelAttribute("codeForm") CodeForm inOutForm) {
		String text = "the text is " + inOutForm.getInputText();
		inOutForm.setOutputText("bla bla" + inOutForm.getInputText());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		mv.addObject("codeForm", inOutForm);
		//mv.addAtribute("Input",id);
		System.out.println(inOutForm.getInputText());    
		return mv;
	}
//	@RequestMapping(value = "/send", method = RequestMethod.POST)
//	public ModelAndView listNotes(@RequestParam("id") String id) {
//		String text = "the text is " + id;
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("result");
//		mv.addObject("text", text);
//		System.out.println(id);    
//		return mv;
//	}

}
