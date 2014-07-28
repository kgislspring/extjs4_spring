package com.extjs.java.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public class DatabaseConnection {

	public DataSource getOdbcConnection() throws SQLException {

		OracleDataSource ods = new OracleDataSource();
		try {
			
			ods.setURL("jdbc:oracle:thin:@10.100.1.244:1522:AMG12C");
			ods.setUser("RHBDEV");
			ods.setPassword("system123");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ods;
	}
}
