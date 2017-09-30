package com.iscas.sdas.controller.work;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.dto.PerformanceWorkDto;
import com.iscas.sdas.dto.TableInfoDto;
import com.iscas.sdas.service.CommonService;
import com.iscas.sdas.util.Constraints;
import com.iscas.sdas.util.FileImport;

@Controller
@RequestMapping("/work")
public class WorkController {
	
	@Autowired
	CommonService commonService;
	
	@RequestMapping("/capacity")
	public ModelAndView capacity(){
		return new ModelAndView("work/capacity");
	}
	
	@RequestMapping("/device")
	public ModelAndView device(){
		return new ModelAndView("/work/device");
	}
	
	@RequestMapping("/indexinfo")
	public ModelAndView indexinfo(){
		return new ModelAndView("/work/indexinfo");
	}
	
	@RequestMapping("/outservice")
	public ModelAndView outservice(){
		return new ModelAndView("/work/outservice");
	}
	@RequestMapping("/import")
	@ResponseBody
	public ModelMap fileimport(){
		ModelMap map = new ModelMap();
		String tablename = "t_performance_work";
		List<TableInfoDto> tableInfoDtos = commonService.tableindex(tablename);
		String path = "C:\\Users\\liuyufeng\\Desktop\\work.xls";
		List<PerformanceWorkDto> performanceWorkDtos = new ArrayList<>();
		if (tableInfoDtos!=null && tableInfoDtos.size()>0) {
			int rows = FileImport.tablerows(path);
			for (int i = 0; i < rows; i++) {
				PerformanceWorkDto workDto = new PerformanceWorkDto();
				performanceWorkDtos.add(workDto);
			}
			try {
				FileImport.importwork(path, performanceWorkDtos, tableInfoDtos);
				map.addAttribute(Constraints.RESULT_ROW, performanceWorkDtos);
				map.addAttribute(Constraints.RESULT_SUCCESS, true);
			} catch (Exception e) {
				map.addAttribute(Constraints.RESULT_SUCCESS, false);
				e.printStackTrace();
			}
		}	
		return map;
		
	}
}
