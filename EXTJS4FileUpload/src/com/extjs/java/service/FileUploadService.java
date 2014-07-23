package com.extjs.java.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface FileUploadService {
	
		public boolean saveFiletoDB(String filename,int filesize,InputStream filecontent);
	
}
