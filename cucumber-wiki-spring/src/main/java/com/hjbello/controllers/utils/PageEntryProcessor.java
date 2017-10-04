package com.hjbello.controllers.utils;

import org.springframework.web.servlet.ModelAndView;

import com.hjbello.dao.PageEntry;

public class PageEntryProcessor {

	public PageEntryProcessor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ModelAndView processEntry (ModelAndView mv, PageEntry entry) {
		if (entry!=null){
			mv.addObject("hasEntries",true);
			mv.addObject("entry", entry);

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
		return mv;
	}
}
