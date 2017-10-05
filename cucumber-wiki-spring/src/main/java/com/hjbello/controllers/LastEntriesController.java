package com.hjbello.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.mapred.legacyjobhistory_jsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LastEntriesController {

	private static final Logger logger = LoggerFactory.getLogger(LastEntriesController.class);
	
	int EntriesPerPage=5;
		
	PageEntryProcessor entryProcessor = new PageEntryProcessor();
	
	@Autowired
	PageEntryRepository pageEntryRepository;


	@RequestMapping(value = "/entry_list/sort={sortby}&order={order}&page={page}", method = RequestMethod.GET)
	@Secured({"ROLE_USER"})
	public @ResponseBody ModelAndView entry(@PathVariable(value="page") String page,@PathVariable(value="sortby") String sortBy,
											@PathVariable(value="order") String order,Model model) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("hasEntries",false);
		int adjustedPage = Integer.parseInt(page)-1;
		if(order.equals("1")){
			Sort sort = new Sort(new Sort.Order(Direction.DESC, sortBy));

		} else{
			Sort sort = new Sort(new Sort.Order(Direction.ASC, sortBy));

		}
		Sort sort = new Sort(new Sort.Order(Direction.ASC, sortBy));
		Pageable pageable = new PageRequest(adjustedPage, 5, sort);
		List<PageEntry> entries=pageEntryRepository.findAll(pageable).getContent();
		
		logger.info("-----------------------");

		logger.info(entries.get(0).getEntryName());
		
		if (entries.size()>0){
			mv.addObject("hasEntries",true);

			mv.addObject("entries",entries);

		}
		mv.setViewName("entry_list");
		return mv;
	}


}
