package com.extjs.java.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Repository;

@Repository
public class FileUploadDAOImpl implements FileUploadDAO{

	@Override
	public boolean saveFiletoDB(String filename,int filesize, InputStream filecontent) {
		boolean result=false;

		String query="insert into eis_mst_fileimport (num_sno,vch_first_name,vch_last_name,vch_city,vch_mobile) values(?,?,?,?,?)";
		String data[]=new String[5];
		
		try
		{
			Connection con=new DBConnection().getDBConnection();
			//Create Workbook instance holding reference to .xlsx file
			HSSFWorkbook workbook = new HSSFWorkbook(filecontent);

			//Get first/desired sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int count=0;
			//To check the column size of the excel file
			while(rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					count++;
				}
				break;
			}
			
			if(count==5)
			{
			rowIterator = sheet.iterator();
			if(rowIterator.hasNext()) rowIterator.next();
			while (rowIterator.hasNext()) 
			{
				Row row = rowIterator.next();
				//For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				int i=0;
				while (cellIterator.hasNext()) 
				{
					Cell cell = cellIterator.next();
					cell.setCellType(Cell.CELL_TYPE_STRING);
					data[i]=new String(cell.getStringCellValue());
					i++;
				}//cell's while
//				jdbcTemplate.update(query,data);
				try
				{
				
				PreparedStatement pstm=con.prepareStatement(query);
				pstm.setString(1,data[0]);
				pstm.setString(2,data[1]);
				pstm.setString(3,data[2]);
				pstm.setString(4,data[3]);
				pstm.setString(5,data[4]);
				pstm.executeUpdate();	
				}
				catch(Exception exp){System.err.println(exp);}
				
			}//row's while
			filecontent.close();
			result=true;
		}//count if
			else
			{
				result=false;
			}
		} //try
		catch (Exception e) 
		{
			System.err.println(e);
		}
		return result;
	}
	
	

}
