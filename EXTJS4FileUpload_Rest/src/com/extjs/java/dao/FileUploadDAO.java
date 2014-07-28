package com.extjs.java.dao;

import java.io.InputStream;


public interface FileUploadDAO {
public boolean saveFiletoDB(String filename,int filesize,InputStream filecontent);
}
