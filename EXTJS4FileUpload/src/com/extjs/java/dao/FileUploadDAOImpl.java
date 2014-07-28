package com.extjs.java.dao;

import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class FileUploadDAOImpl implements FileUploadDAO{

	

	@Override
	public boolean saveFiletoDB(String filename,int filesize, InputStream filecontent) {
		String result="";
		try
		{
			InputStream in = filecontent;
			HttpEntity<byte[]> entity = new HttpEntity<>(IOUtils.toByteArray(in));
			RestTemplate restTemplate=new RestTemplate();
	           restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
			result=restTemplate.postForObject("http://localhost:9070/EXTJS4FileUpload_Rest/rest/fileUpload/send/"+filename+"/"+filesize,entity,String.class);
		}
		catch(Exception exp){System.err.println(exp);}
		return true;
	
	}
	

}
