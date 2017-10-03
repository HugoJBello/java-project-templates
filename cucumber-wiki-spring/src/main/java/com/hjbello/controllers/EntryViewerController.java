package com.hjbello.controllers;

import java.util.ArrayList;

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
public class EntryViewerController {

	private static final Logger logger = LoggerFactory.getLogger(EntryViewerController.class);

	@Autowired
	PageEntryRepository pageEntryRepository;


	@RequestMapping(value = "/entry/{entryName}", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN"})
	public @ResponseBody ModelAndView entry(@PathVariable(value="entryName") String entryName, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hasEntryName",false);
		
		PageEntry entry = pageEntryRepository.findByEntryName(entryName);
		
		if (entry!=null){
			mv.addObject("hasEntries",true);
			mv.addObject("entry", entry);
			String textInHtml = Processor.process(entry.getContents());
			mv.addObject("textInHtml",textInHtml);	


			if (entry.getCathegories()== null || entry.getCathegories().equals("")) {
				mv.addObject("hasCathegories", false);
			} else if (entry.getCathegories().contains(";")){
				String[] cathegories = entry.getCathegories().split(";");
				mv.addObject("cathegories",cathegories);
				mv.addObject("hasCathegories", true);
			} else {
				String[] cathegories = new String[1];
				cathegories[0] = entry.getCathegories();
				mv.addObject("cathegories",cathegories);
				mv.addObject("hasCathegories", true);
			}


		}
		mv.setViewName("entry_viewer");
		return mv;
	}


}
