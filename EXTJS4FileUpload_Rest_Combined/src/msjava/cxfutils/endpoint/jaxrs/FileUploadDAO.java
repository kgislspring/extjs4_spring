package msjava.cxfutils.endpoint.jaxrs;

import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface FileUploadDAO {
public String saveFiletoDB(String filename,int filesize,InputStream filecontent);
}
