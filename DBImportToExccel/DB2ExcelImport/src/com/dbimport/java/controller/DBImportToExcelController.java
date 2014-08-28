package com.dbimport.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dbimport.java.dao.ImportDBDataDAO;

@Controller
public class DBImportToExcelController {

	@Autowired
	ImportDBDataDAO importDBDataDAO;
	
	
	@RequestMapping(value="display",method=RequestMethod.GET)
	public String displayForm()
	{
		return "display";
	}
	
	@RequestMapping(value="importData",method=RequestMethod.GET)
	public ModelAndView importDBDataToExcel()
	{
		String result=importDBDataDAO.importDBDataToExcel();
		
		return new ModelAndView("display","message",result);
	}
	
}
