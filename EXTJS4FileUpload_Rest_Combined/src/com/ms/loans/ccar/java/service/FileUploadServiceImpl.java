package com.ms.loans.ccar.java.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import msjava.cxfutils.endpoint.jaxrs.DBConnection;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.extjs.java.model.ExtJSFormResult;

@Path("/fileUpload")
public class FileUploadServiceImpl {

	@POST
	@Path("/send/{fileName}/{fileSize}")
	public String saveFiletoDB(byte[] entity,
			@PathParam("fileName") String fileName,
			@PathParam("fileSize") int fileSize) {
		ByteArrayInputStream in = new ByteArrayInputStream(entity);
		ExtJSFormResult extjsFormResult = new ExtJSFormResult();
		String fileExtension = FilenameUtils.getExtension(fileName);
		// File format checking
		if (fileExtension.equals("xls")) {
			System.out.println("Controller");
			if (savequery(fileName, fileSize, in))
				extjsFormResult.setResult(true);
			else
				extjsFormResult.setResult(false);
		} else
			extjsFormResult.setResult(false);
		return extjsFormResult.toString();

	}

	public boolean savequery(String filename, int filesize,
			InputStream filecontent) {
		boolean result = false;
		String query = "insert into eis_mst_fileimport (num_sno,vch_first_name,vch_last_name,vch_city,vch_mobile) values(?,?,?,?,?)";
		String data[] = new String[5];

		try {

			// Create Workbook instance holding reference to .xlsx file
			HSSFWorkbook workbook = new HSSFWorkbook(filecontent);

			// Get first/desired sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int count = 0;
			// To check the column size of the excel file
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					count++;
				}
				break;
			}

			if (count == 5) {
				rowIterator = sheet.iterator();
				if (rowIterator.hasNext())
					rowIterator.next();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
					int i = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						cell.setCellType(Cell.CELL_TYPE_STRING);
						data[i] = new String(cell.getStringCellValue());
						System.out.println("DATA :" + data[i]);
						i++;
					}// cell's while
						// jdbcTemplate.update(query,data);
					try {
						Connection con = new DBConnection().getDBConnection();
						PreparedStatement pstm = con.prepareStatement(query);
						pstm.setString(1, data[0]);
						pstm.setString(2, data[1]);
						pstm.setString(3, data[2]);
						pstm.setString(4, data[3]);
						pstm.setString(5, data[4]);
						pstm.executeUpdate();
					} catch (Exception exp) {
						System.err.println(exp);
					}

				}// row's while
				filecontent.close();
				result = true;
			}// count if
			else {
				result = false;
			}
		} // try
		catch (Exception e) {
			System.err.println(e);
		}
		return result;
	}

}
