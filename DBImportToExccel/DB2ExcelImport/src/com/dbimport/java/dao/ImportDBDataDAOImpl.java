package com.dbimport.java.dao;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbimport.java.model.Student;

@Service
@Transactional
public class ImportDBDataDAOImpl implements ImportDBDataDAO{

	@Autowired
	DataSource dataSource;
	
	@SuppressWarnings("deprecation")
	@Override
	public String importDBDataToExcel() {
		String result=null;
		try
		{
			String filename="c:/users/asaithambi/desktop/data.xls" ;
			HSSFWorkbook hwb=new HSSFWorkbook();
			HSSFSheet sheet =  hwb.createSheet("new sheet");
			int rownum=0;
			HSSFRow rowhead=   sheet.createRow(rownum);
			rowhead.createCell((short) 0).setCellValue("SNo");
			rowhead.createCell((short) 1).setCellValue("First Name");
			rowhead.createCell((short) 2).setCellValue("Last Name");
			rowhead.createCell((short) 3).setCellValue("Mobile Number");
			rowhead.createCell((short) 4).setCellValue("E-mail");
	
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		String sql="select num_sno,vch_first_name,vch_last_name,vch_city,vch_mobile from eis_mst_fileimport";
		
		List<Student> stdList=jdbcTemplate.query(sql,new RowMapper<Student>(){

			@Override
			public Student mapRow(ResultSet rst, int rownum) throws SQLException {
				Student student=new Student();
				student.setSno(rst.getInt("num_sno"));
				student.setFname(rst.getString("vch_first_name"));
				student.setLname(rst.getString("vch_last_name"));
				student.setCity(rst.getString("vch_city"));
				student.setMobile(rst.getString("vch_mobile"));
				return student;
			}
			
		});
		
		rownum++;
		for(Student student : stdList)
		{
			HSSFRow row=   sheet.createRow(rownum);
			row.createCell(0).setCellValue(student.getSno());
			row.createCell(1).setCellValue(student.getFname());
			row.createCell(2).setCellValue(student.getLname());
			row.createCell(3).setCellValue(student.getCity());
			row.createCell(4).setCellValue(student.getMobile());
			rownum++;
		}
		FileOutputStream fileOut =  new FileOutputStream(filename);
		hwb.write(fileOut);
		fileOut.close();
		result="Your excel file has been generated!,Check your file Location";
		
		}//try
		catch(Exception exp)
		{
			result=exp.toString();
			
		}
		
		return result;
	}

}
