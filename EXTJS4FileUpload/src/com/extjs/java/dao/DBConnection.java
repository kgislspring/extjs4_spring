package com.extjs.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public Connection getDBConnection()throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@10.100.1.244:1522:AMG12C","RHBDEV","system123");
		return con;
	}
	
}
