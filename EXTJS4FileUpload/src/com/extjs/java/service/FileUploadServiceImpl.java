package com.extjs.java.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.extjs.java.dao.FileUploadDAO;

public class FileUploadServiceImpl implements FileUploadService {


	FileUploadDAO serviceBean;
	
	public FileUploadDAO getServiceBean() {
		return serviceBean;
	}
	public void setServiceBean(FileUploadDAO serviceBean) {
		this.serviceBean = serviceBean;
	}

	@Override
	public boolean saveFiletoDB(String filename, int filesize,
			InputStream filecontent) {
		boolean result=serviceBean.saveFiletoDB(filename, filesize, filecontent);
		return result;
	}

}
