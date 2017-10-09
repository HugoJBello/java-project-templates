package com.hjbello.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.mapred.legacyjobhistory_jsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
public class CathegoriesListController {

	private static final Logger logger = LoggerFactory.getLogger(CathegoriesListController.class);
	
	int entriesPerPage=5;
		
	PageEntryProcessor entryProcessor = new PageEntryProcessor();
	
	@Autowired
	PageEntryRepository pageEntryRepository;


	@RequestMapping(value = "/cathegories", method = RequestMethod.GET)
	@Secured({"ROLE_USER"})
	public @ResponseBody ModelAndView cathegoriesList(@RequestParam(value="page") String page,@RequestParam(value="sort") String sortBy,
			@RequestParam(value="order") String order,Model model) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("page",page);
		mv.addObject("sortBy",sortBy);
		mv.addObject("order",order);

		mv.addObject("hasEntries",false);
		int adjustedPage = Integer.parseInt(page)-1;
		Sort sort;
		if(order.equals("-1")){
			sort = new Sort(new Sort.Order(Direction.DESC, sortBy));
			mv.addObject("orderDescending",true);
		} else{
			sort = new Sort(new Sort.Order(Direction.ASC, sortBy));
		}
		
		
 		Pageable pageable = new PageRequest(adjustedPage, entriesPerPage, sort);
		PageImpl<PageEntry> result = pageEntryRepository.findAll(pageable);

		List<PageEntry> entries=result.getContent();
		int pages= result.getTotalPages();
		
		mv.addObject("pages",pages);
		
		if (entries.size()>0){
			mv.addObject("hasEntries",true);

			mv.addObject("entries",entries);

		}
		mv.setViewName("entry_list");
		return mv;
	}


}
