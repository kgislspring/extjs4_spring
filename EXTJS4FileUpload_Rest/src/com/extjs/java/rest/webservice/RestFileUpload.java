package com.extjs.java.rest.webservice;

import java.io.ByteArrayInputStream;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.commons.io.FilenameUtils;

import com.extjs.java.dao.FileUploadDAOImpl;
import com.extjs.java.model.ExtJSFormResult;

@Path("/fileUpload")
public class RestFileUpload {

	@POST
	@Path("/send/{fileName}/{fileSize}")
	public String consumeJSON(byte[] entity,
			@PathParam("fileName") String fileName,
			@PathParam("fileSize") int fileSize) throws Exception {
		
		ByteArrayInputStream in = new ByteArrayInputStream(entity);
		ExtJSFormResult extjsFormResult = new ExtJSFormResult();
		String fileExtension = FilenameUtils.getExtension(fileName);
		// File format checking
		if (fileExtension.equals("xls")) {
			if (new FileUploadDAOImpl().saveFiletoDB(fileName, fileSize, in))
				extjsFormResult.setResult(true);
			else
				extjsFormResult.setResult(false);
		} else
			extjsFormResult.setResult(false);
		
		return extjsFormResult.toString();

	}

}
