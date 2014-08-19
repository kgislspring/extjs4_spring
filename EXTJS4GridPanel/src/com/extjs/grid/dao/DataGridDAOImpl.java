package com.extjs.grid.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class DataGridDAOImpl implements DataGridDAO {

	@Autowired
	DataSource dataSource;
	
	@Override
	public List<JSONObject> getGridData(HttpServletResponse response)throws Exception {
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		
		String sql="select * from tb_extjs_grid";
		List<JSONObject> jsonobj=jdbcTemplate.query(sql, new RowMapper<JSONObject>()
				{
					@Override
					public JSONObject mapRow(ResultSet rst, int rownum)throws SQLException {
						JSONObject tempobj=new JSONObject();
						try
						{
						tempobj.put("sno", rst.getInt("sno"));
						tempobj.put("name", rst.getString("name"));
						tempobj.put("address", rst.getString("address"));
						tempobj.put("mobile", rst.getString("mobile"));
						tempobj.put("email", rst.getString("email"));
						}
						catch(Exception exp){}
						return tempobj;
					}
			
				});
		
		return jsonobj;
	}

}
