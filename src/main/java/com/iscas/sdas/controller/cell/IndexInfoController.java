package com.iscas.sdas.controller.cell;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iscas.sdas.dto.AllData;
import com.iscas.sdas.service.cell.IndexInfoService;
import com.iscas.sdas.util.Constraints;



@Controller
@RequestMapping("/cellindex")
public class IndexInfoController {

	@Autowired
	IndexInfoService indexInfoService;
	
	@RequestMapping("/hdfsinfo")
	@ResponseBody
	public ModelMap indexlist(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String cellname = request.getParameter("cellname");
		//List<DataLine> lines = indexInfoService.indexfromhdfs(cellname);
		//map.addAttribute(Constraints.RESULT_ROW, lines);
		return map;
	}
	
	@RequestMapping("/mrinfo")
	@ResponseBody
	public ModelMap mrinfo(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String cellname = request.getParameter("cellname");
		List<AllData> lines;
		try {
			lines = indexInfoService.indexfrommysql(cellname);
			map.addAttribute(Constraints.RESULT_ROW, lines);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}	
		return map;
	}
}