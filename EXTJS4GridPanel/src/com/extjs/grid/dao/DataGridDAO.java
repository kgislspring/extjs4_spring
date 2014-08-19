package com.extjs.grid.dao;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public interface DataGridDAO {
	public List<JSONObject> getGridData(HttpServletResponse response)throws Exception;
}
