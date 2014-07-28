package com.extjs.java.controller;

import java.io.InputStream;

import msjava.cxfutils.endpoint.jaxrs.FileUploadDAO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.extjs.java.model.ExtJSFormResult;
import com.extjs.java.model.FileUploadBean;

@Controller
public class FileUploadController {

	@Autowired
	FileUploadDAO FileUploadRESTEndpoint;

	@RequestMapping(value = "/displayUploadForm", method = RequestMethod.GET)
	public ModelAndView testGridForm() {
		return new ModelAndView("fileUpload");
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	String create(final FileUploadBean uploadItem, BindingResult result)
			throws Exception {
		
ExtJSFormResult extJSFormResult=new ExtJSFormResult();
		
		String fnames[]=
			{
				uploadItem.getFile1().getOriginalFilename(),
				uploadItem.getFile2().getOriginalFilename(),
				uploadItem.getFile3().getOriginalFilename(),
				uploadItem.getFile4().getOriginalFilename()
			};

		int fsizes[]=
			{
				(int)uploadItem.getFile1().getSize(),
				(int)uploadItem.getFile2().getSize(),
				(int)uploadItem.getFile3().getSize(),
				(int)uploadItem.getFile4().getSize()
			};

		InputStream files[]=
			{
				uploadItem.getFile1().getInputStream(),
				uploadItem.getFile2().getInputStream(),
				uploadItem.getFile3().getInputStream(),
				uploadItem.getFile4().getInputStream()
			};
		
		int j=0;
		boolean testStatus=true;
		for(int i=0;i<fnames.length;i++)
		{
		if(j==0)
		{
			for(int k=0;k<fnames.length;k++)
			{
				String fileExtension = FilenameUtils.getExtension(fnames[k]);
				String fname="";
				fname=fnames[k].toString();
				if(!fileExtension.equals("xls") && fname!="")
				{
					System.out.println("AAAA:"+fnames[k]);
					extJSFormResult.setResult(false);
					testStatus=false;
					break;
				}
			}
			j=1;
		}
		if(testStatus)
		{
		if(fnames[i]!=null )
			FileUploadRESTEndpoint.saveFiletoDB(fnames[i],fsizes[i],files[i]);
		extJSFormResult.setResult(true);
		}
		else
			break;
		}
		return extJSFormResult.toString();
	}

}
