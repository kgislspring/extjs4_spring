package com.extjs.java.dao;

import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface FileUploadDAO {
public boolean saveFiletoDB(String filename,int filesize,InputStream filecontent);
}
