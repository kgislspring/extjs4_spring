package com.extjs.java.controller;

import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.extjs.java.model.ExtJSFormResult;
import com.extjs.java.model.FileUploadBean;
import com.extjs.java.service.FileUploadService;

@Controller
public class FileUploadController {
	
	@Autowired
	FileUploadService fileUploadREST;
	

	@RequestMapping(value="/displayUploadForm",method=RequestMethod.GET)
	public ModelAndView testGridForm()
	{
		return new ModelAndView("fileUpload");
	}
	
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public @ResponseBody String create(final FileUploadBean uploadItem, BindingResult result)throws Exception{
	ExtJSFormResult extjsFormResult = new ExtJSFormResult();
		if (result.hasErrors()){
			for(ObjectError error : result.getAllErrors()){
				System.err.println("Error: " + error.getCode() +  " - " + error.getDefaultMessage());
			}
			extjsFormResult.setResult(false);		
			return extjsFormResult.toString();
		}

		final String filename=uploadItem.getFile().getOriginalFilename();
		int filesize=(int)uploadItem.getFile().getSize();
		final InputStream filecontent = uploadItem.getFile().getInputStream();
		String fileExtension=FilenameUtils.getExtension(filename);
		//File format checking
		if(fileExtension.equals("xls"))
		{
		if(fileUploadREST.saveFiletoDB(filename, filesize, filecontent))
			extjsFormResult.setResult(true);
		else
			extjsFormResult.setResult(false);
		}
		else
			extjsFormResult.setResult(false);
		
		return extjsFormResult.toString();
	}

}
