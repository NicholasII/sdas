package com.iscas.sdas.controller.work;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.dto.PerformanceWorkDto;
import com.iscas.sdas.dto.TableInfoDto;
import com.iscas.sdas.dto.work.CapacityWorkDto;
import com.iscas.sdas.service.CommonService;
import com.iscas.sdas.service.WorkService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;
import com.iscas.sdas.util.FileImport;


@Controller
@RequestMapping("/work")
public class WorkController {
	
	@Autowired
	CommonService commonService;
	@Autowired
	WorkService workService;
	
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
	public ModelAndView fileimport(HttpServletRequest request){
		ModelAndView map = new ModelAndView("/work/capacity");
		String tablename = "t_performance_work";
		List<TableInfoDto> tableInfoDtos = commonService.tableindex(tablename);		
		List<PerformanceWorkDto> performanceWorkDtos = new ArrayList<>();
		List<String> paths = CommonUntils.MultipleFilesUpload(request);
		if (paths!=null && paths.size()>0) {
			if (tableInfoDtos!=null && tableInfoDtos.size()>0) {
				int rows = FileImport.tablerows(paths.get(0));
				for (int i = 0; i < rows; i++) {
					PerformanceWorkDto workDto = new PerformanceWorkDto();
					performanceWorkDtos.add(workDto);
				}
				try {
					FileImport.importwork(paths.get(0), performanceWorkDtos, tableInfoDtos);
					workService.clearPerformanceWork();				
					workService.insertPerformanceWork(performanceWorkDtos);
					//map.addAttribute(Constraints.RESULT_SUCCESS, true);
				} catch (Exception e) {
					//map.addAttribute(Constraints.RESULT_ROW, performanceWorkDtos);
					map.addObject(Constraints.RESULT_SUCCESS, false);
					e.printStackTrace();
				}
			}
		}else {
			map.addObject(Constraints.RESULT_SUCCESS, false);
		}		
		return map;
		
	}
	@RequestMapping("/validate")
	@ResponseBody
	public ModelMap workvalidate(){
		ModelMap map = new ModelMap();
		
		List<CapacityWorkDto> capacityWorks = workService.workValidate(workService.allvalidatelist(),0);
		if (capacityWorks.size()>0) {
			map.addAttribute(Constraints.RESULT_ROW, capacityWorks);
			map.addAttribute(Constraints.RESULT_SUCCESS, true);
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
		return map;
	}
	
	@RequestMapping("/validatetheday")
	@ResponseBody
	public ModelMap workvalidatetheday(){
		ModelMap map = new ModelMap();
		
		List<CapacityWorkDto> capacityWorks = workService.workValidate(workService.validatelisttheday(),1);
		if (capacityWorks.size()>0) {
			map.addAttribute(Constraints.RESULT_ROW, capacityWorks);
			map.addAttribute(Constraints.RESULT_SUCCESS, true);
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
		return map;
	}
	@RequestMapping("/validatedoubt")
	@ResponseBody
	public ModelMap doubtworkvalidate(){
		ModelMap map = new ModelMap();
		
		List<CapacityWorkDto> capacityWorks = workService.workValidate(workService.allvalidatelist(),2);
		if (capacityWorks.size()>0) {
			map.addAttribute(Constraints.RESULT_ROW, capacityWorks);
			map.addAttribute(Constraints.RESULT_SUCCESS, true);
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
		return map;
	}
}
