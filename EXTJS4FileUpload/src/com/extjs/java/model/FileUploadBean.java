package com.extjs.java.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadBean {

	private CommonsMultipartFile file;
private String filepath;

	public String getFilepath() {
	return filepath;
}

public void setFilepath(String filepath) {
	this.filepath = filepath;
}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
	
}
