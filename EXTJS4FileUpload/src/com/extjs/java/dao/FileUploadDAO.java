package com.extjs.java.dao;

import java.io.InputStream;

import org.springframework.stereotype.Repository;
@Repository
public interface FileUploadDAO {
public boolean saveFiletoDB(String filename,int filesize,InputStream filecontent);
}
