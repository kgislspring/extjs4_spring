package msjava.cxfutils.endpoint.jaxrs;

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
public class FileUploadDAOImpl implements FileUploadDAO {


	@Autowired
	DataSource dataSource;

	@Override
	public String saveFiletoDB(String filename, int filesize,
			InputStream filecontent) {
		String result = "";
		try {
			InputStream in = filecontent;
			HttpEntity<byte[]> entity = new HttpEntity<>(
					IOUtils.toByteArray(in));
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(
					new StringHttpMessageConverter());
			result = restTemplate.postForObject(
					"http://localhost:8090/EXTJS4FileUpload/rest/fileUpload/send/"
							+ filename + "/" + filesize, entity, String.class);
		} catch (Exception exp) {
			System.err.println(exp);
		}
		return result;
	}

}
