package com.hjbello.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.rjeschke.txtmark.Processor;
import com.hjbello.controllers.utils.PageEntryProcessor;
import com.hjbello.dao.PageEntry;
import com.hjbello.dao.PageEntryRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
@EnableGlobalMethodSecurity(securedEnabled = true)
public class EntryEditorController {

	private static final Logger logger = LoggerFactory.getLogger(EntryEditorController.class);

	PageEntryProcessor entryProcessor = new PageEntryProcessor();
	
	@Autowired
	PageEntryRepository pageEntryRepository;


	@RequestMapping(value = "/entry_editor/{entryName}", method = RequestMethod.GET)
	@Secured({"ROLE_USER"})
	public @ResponseBody ModelAndView entry(@PathVariable(value="entryName") String entryName, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hasEntryName",false);
		
		PageEntry entry = pageEntryRepository.findByEntryName(entryName);
		
		mv = entryProcessor.processEntry(mv, entry);
		
		mv.setViewName("entry_editor");
		return mv;
	}


	
}
