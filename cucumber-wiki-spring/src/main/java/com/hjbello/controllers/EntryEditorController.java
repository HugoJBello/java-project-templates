package com.hjbello.controllers;

import java.util.Date;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.github.rjeschke.txtmark.Processor;
import com.hjbello.controllers.utils.FilenameUtils;
import com.hjbello.controllers.utils.NewEntryForm;
import com.hjbello.controllers.utils.PageEntryProcessor;
import com.hjbello.dao.Cathegory;
import com.hjbello.dao.CathegoryReferenced;
import com.hjbello.dao.CathegoryReferencedRepository;
import com.hjbello.dao.CathegoryRepository;
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
	FilenameUtils renamingUtils = new FilenameUtils();
	@Autowired
	PageEntryRepository pageEntryRepository;

	@Autowired
	CathegoryRepository cathegoryRepository;

	@Autowired
	CathegoryReferencedRepository cathegoryReferencedRepository;


	@RequestMapping(value = "/entry_editor/{entryName}", method = RequestMethod.GET)
	@Secured({"ROLE_USER"})
	public @ResponseBody ModelAndView entry(@PathVariable(value="entryName") String entryName, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hasEntryName",false);

		PageEntry entry = pageEntryRepository.findByEntryName(entryName);
		NewEntryForm entryForm = new NewEntryForm();
		entryForm.setContents(entry.getContents());
		entryForm.setEntryName(entry.getEntryName());
		entryForm.setCathegories(entry.getCathegories());
		entryForm.setTitle(entry.getTitle());
		model.addAttribute(entryForm);

		mv = entryProcessor.processEntry(mv, entry);

		mv.setViewName("entry_editor");
		return mv;
	}

	@RequestMapping(value = "/entry_editor", method = RequestMethod.GET)
	@Secured({"ROLE_USER"})
	public @ResponseBody ModelAndView newEntry(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hasEntryName",false);
		mv.addObject("hasEntries",false);

		NewEntryForm entryForm = new NewEntryForm();
		model.addAttribute(entryForm);

		mv.setViewName("entry_editor");
		return mv;
	}

	//	@RequestMapping(value = "/entry_editor_new", method = RequestMethod.POST)
	//	@Secured({"ROLE_USER"})
	//	public @ResponseBody RedirectView entryEditorNew(@ModelAttribute(value="newEntryForm") NewEntryForm newEntry, Model model) {
	//		ModelAndView mv = new ModelAndView();
	//		mv.addObject("hasEntryName",false);
	//
	//		PageEntry entry=processForm(newEntry);
	//
	//		pageEntryRepository.insert(entry);
	//
	//		return new RedirectView("/entry/" + entry.getEntryName());
	//	}




	@RequestMapping(value = "/entry_editor", method = RequestMethod.POST)
	@Secured({"ROLE_USER"})
	public @ResponseBody RedirectView entrySave(@ModelAttribute(value="newEntryForm") NewEntryForm newEntry, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hasEntryName",false);

		PageEntry entry=processForm(newEntry);


		pageEntryRepository.save(entry);

		updateAndDeleteCathegories(entry);

		return new RedirectView("/entry/" + entry.getEntryName());
	}


	private PageEntry processForm(NewEntryForm newEntry){
		PageEntry entry = new PageEntry();
		entry.setEntryName(newEntry.getEntryName());
		entry.setTitle(newEntry.getTitle());
		entry.setContents(newEntry.getContents());
		entry.setCathegories(newEntry.getCathegories());
		Date date = new Date();
		entry.setUpdatedAt(date);
		entry.setCreatedAt(date);

		return entry;
	}

	private void updateAndDeleteCathegories (PageEntry entry){
		ArrayList<CathegoryReferenced> cathegories = processCathegoriesIntroduced(entry);
		Date lastUpdateDate = cathegories.get(0).getUpdatedAt();
		
		logger.info("removing old references to cathegories of " + entry.getEntryName() );
		//cathegoryReferencedRepository.removeByEntryNameReferenced(entry.getEntryName());
		
		for (CathegoryReferenced cathegoryReferenced : cathegories){
			saveCathegoryFromReferece(cathegoryReferenced);
			logger.info("updating reference ("+ cathegoryReferenced.getCathegoryName() + "," +cathegoryReferenced.getEntryNameReferenced() +"," + cathegoryReferenced.getUpdatedAt() +")");
			cathegoryReferencedRepository.save(cathegoryReferenced);	
		}

		deleteDeadReferences(entry.getEntryName(), lastUpdateDate);
	}

	private void saveCathegoryFromReferece(CathegoryReferenced cathegoryReferenced){
		logger.info("saving and updating references to category "+ cathegoryReferenced.getCathegoryName());
		Cathegory cathegory = cathegoryRepository.findByCathegoryName(cathegoryReferenced.getCathegoryName());
		if (cathegory==null){
			cathegory = new Cathegory();
		}
		cathegory.setTitle(cathegoryReferenced.getCathegoryTitle());
		cathegory.setCathegoryName(cathegoryReferenced.getCathegoryName());
		cathegory.setUpdatedAt(cathegoryReferenced.getUpdatedAt());
		cathegoryRepository.save(cathegory);
	}

	private void deleteDeadReferences(String entryName, Date date){
		logger.info("deleting references out of date");
		ArrayList<CathegoryReferenced> references = cathegoryReferencedRepository.findByentryNameReferenced(entryName);
		if (references!= null){
			for (CathegoryReferenced reference: references){
				if(reference!=null){
					if (reference.getUpdatedAt().before(date)){
						logger.info("deleting old reference to " + reference.getCathegoryName());
						try{
						cathegoryReferencedRepository.delete(reference);
						} catch (Error e){
							logger.info("imposible to delete referece ("+ reference.getCathegoryName() + "," +reference.getEntryNameReferenced() +"," + reference.getUpdatedAt() +")");
						}
					}
				}
			}
		}
	}

	private ArrayList<CathegoryReferenced> processCathegoriesIntroduced(PageEntry entry){
		logger.info("processing cathegories introduced by user separated by ';'");
		String cathegoriesStr = entry.getCathegories();
		Date date = new Date();
		ArrayList<CathegoryReferenced> output = new ArrayList<>();
		if (cathegoriesStr.contains(";")){
			String[] cathegories = cathegoriesStr.split(";");
			for (String str:cathegories){
				str = str.trim();
				CathegoryReferenced catRef = convertIntoReference(str,entry,date);
				output.add(catRef);
			}
		} else if (!((cathegoriesStr==null) || (cathegoriesStr.equals("")))){
			CathegoryReferenced catRef = convertIntoReference(cathegoriesStr,entry,date);
			output.add(catRef);
		}		
		return output;
	}

	private CathegoryReferenced convertIntoReference(String cathegoryName,PageEntry entry, Date date){
		CathegoryReferenced catRef = new CathegoryReferenced();
		catRef.setCathegoryTitle(cathegoryName);
		catRef.setCathegoryName(renamingUtils.cleanFilename(cathegoryName));
		catRef.setEntryNameReferenced(entry.getEntryName());
		catRef.setUpdatedAt(date);
		return catRef;
	}
}
