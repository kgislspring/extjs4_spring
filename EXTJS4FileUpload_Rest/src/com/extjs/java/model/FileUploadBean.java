package com.extjs.java.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadBean {

	private CommonsMultipartFile file1;
	private CommonsMultipartFile file2;
	private CommonsMultipartFile file3;
	private CommonsMultipartFile file4;
	

	public CommonsMultipartFile getFile1() {
		return file1;
	}

	public void setFile1(CommonsMultipartFile file1) {
		this.file1 = file1;
	}

	public CommonsMultipartFile getFile2() {
		return file2;
	}

	public void setFile2(CommonsMultipartFile file2) {
		this.file2 = file2;
	}

	public CommonsMultipartFile getFile3() {
		return file3;
	}

	public void setFile3(CommonsMultipartFile file3) {
		this.file3 = file3;
	}

	public CommonsMultipartFile getFile4() {
		return file4;
	}

	public void setFile4(CommonsMultipartFile file4) {
		this.file4 = file4;
	}
	
	
}
