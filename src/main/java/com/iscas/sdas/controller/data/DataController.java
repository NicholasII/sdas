package com.iscas.sdas.controller.data;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.dto.TableInfoDto;
import com.iscas.sdas.dto.work.AllCapacityWorkDto;
import com.iscas.sdas.dto.work.AllOutServerDto;
import com.iscas.sdas.service.CommonService;
import com.iscas.sdas.service.WorkService;
import com.iscas.sdas.service.work.OutServerService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;
import com.iscas.sdas.util.FileImport;

import tasks.realtime.CellUploadFileTask;



@Controller
@RequestMapping("/data")
public class DataController {
	@Autowired
	WorkService workService;
	@Autowired
	CommonService commonService;
	@Autowired
	OutServerService outServerService;
	
	@RequestMapping("/online")
	public ModelAndView online(){
		return new ModelAndView("/data/online");
	}
	@RequestMapping("/offline")
	public ModelAndView offline(){
		ModelAndView modelAndView  = new ModelAndView("/data/offline");
		modelAndView.addObject("success", Constraints.RESULT_UNKNOWN);
		return modelAndView;
	}
	@RequestMapping("/upload")
	public ModelAndView upload(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("data/offline");
		try {
			String type = request.getParameter("type");
			if ("network".equals(type)) {
				List<String> paths = CommonUntils.MultipleFilesUpload(request);
				if (paths.size() > 0) {
					CellUploadFileTask.doUploadFileWork(paths.get(0));
				}
			} else if ("capacity".equals(type)) {
				String tablename = "t_performance_work";
				List<TableInfoDto> tableInfoDtos = commonService.tableindex(tablename);
				List<AllCapacityWorkDto> performanceWorkDtos = new ArrayList<>();
				List<String> paths = CommonUntils.MultipleFilesUpload(request);
				if (paths != null && paths.size() > 0) {
					if (tableInfoDtos != null && tableInfoDtos.size() > 0) {
						int rows = FileImport.tablerows(paths.get(0));
						for (int i = 0; i < rows; i++) {
							AllCapacityWorkDto workDto = new AllCapacityWorkDto();
							performanceWorkDtos.add(workDto);
						}

						FileImport.importwork(paths.get(0), performanceWorkDtos, tableInfoDtos);// 将excel映射为对象
						workService.clearPerformanceWork(); // 清空表
						workService.insertPerformanceWork(performanceWorkDtos);// 插入表并将questionflag置为-1
					}
				}
			} else if ("fault".equals(type)) {

			} else if ("complaint".equals(type)) {

			} else if ("outservice".equals(type)) {
				String tablename = "t_wireless_retirement";
				List<TableInfoDto> tableInfoDtos = commonService.tableindex(tablename);
				List<AllOutServerDto> osWorkDtos = new ArrayList<>();
				List<String> paths = CommonUntils.MultipleFilesUpload(request);
				if (paths != null && paths.size() > 0) {
					if (tableInfoDtos != null && tableInfoDtos.size() > 0) {
						int rows = FileImport.tablerows(paths.get(0));
						for (int i = 0; i < rows; i++) {
							AllOutServerDto workDto = new AllOutServerDto();
							osWorkDtos.add(workDto);
						}
						FileImport.importwork(paths.get(0), osWorkDtos, tableInfoDtos);// 将excel映射为对象
						outServerService.clearOSWork(); // 清空表
						outServerService.insertOSWork(osWorkDtos);

					}
				}
			}
			modelAndView.addObject("success", Constraints.RESULT_SUCCESS);
		} catch (Exception e) {
			modelAndView.addObject("success", Constraints.RESULT_FAIL);
			e.printStackTrace();
		}
		return modelAndView;
	}
	

}
