package com.extjs.grid.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.extjs.grid.dao.DataGridDAO;

@Controller
public class GridController {

@Autowired
DataGridDAO dataGridDAO;
	
@RequestMapping(value="/displayGrid",method=RequestMethod.GET)
public String displayGrid()
{
	return "datagrid";
}

@ResponseBody
@RequestMapping(value="/griddata",method=RequestMethod.GET)
public ModelAndView getGridData(HttpServletResponse response) throws Exception
{	
	JSONObject gridData=new JSONObject();
	gridData.put("users", dataGridDAO.getGridData(response));
	
	response.setContentType("application/json");
	PrintWriter out=response.getWriter();
	out.println(gridData);
	out.flush();
	
	return null;
}
	

}
