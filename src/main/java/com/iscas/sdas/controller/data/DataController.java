package com.iscas.sdas.controller.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.dto.FileLogDto;
import com.iscas.sdas.dto.TableInfoDto;
import com.iscas.sdas.dto.work.AllCapacityWorkDto;
import com.iscas.sdas.dto.work.AllOutServerDto;
import com.iscas.sdas.service.CommonService;
import com.iscas.sdas.service.WorkService;
import com.iscas.sdas.service.log.FileLogService;
import com.iscas.sdas.service.work.OutServerService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;
import com.iscas.sdas.util.FileImport;

import tasks.realtime.CellUploadFileOfExpertTask;
import tasks.realtime.CellUploadFileTask;

@Controller
@RequestMapping("/data")
public class DataController{
	@Autowired
	WorkService workService;
	@Autowired
	CommonService commonService;
	@Autowired
	OutServerService outServerService;
	@Autowired
	FileLogService fileLogService;
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
	
	@RequestMapping("/test")
	public ModelAndView test(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("data/offline");
		String path = "/home/hadoop/systempdata/test_rtdata_net.csv";
		System.err.println(path);
		CellUploadFileTask.doUploadFileWork(path);
		System.err.println("task over!");
		return modelAndView;
	}
	/**
	 * 文件上传
	 * @param request
	 * @return
	 */
	@RequestMapping("/upload")
	public ModelAndView upload(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("data/offline");
		String type = request.getParameter("type");
		if ("network".equals(type)) {
			String time = request.getParameter("time");
			String path = null;
			FileLogDto fileLogDto = new FileLogDto();
			long starttime = System.currentTimeMillis();
			fileLogDto.setStarttime(new Date());
			fileLogDto.setType("中兴网管指标数据");
			try {
				path = CommonUntils.FileImprot(request, fileLogDto);
			} catch (Exception e1) {
				e1.printStackTrace();
				fileLogDto.setResult(0);
				modelAndView.addObject("success", Constraints.RESULT_FAIL+ ":上传失败！");
			}
			if (path!=null) {
				try {
					String[] args = new String[2];
					args[0] = path;
					args[1] = time;
					fileLogDto.setMethodstart(new Date());
					new CellUploadFileTask().runTask(args);
					new CellUploadFileOfExpertTask().runTask(args);	
					fileLogDto.setMethodend(new Date());
					modelAndView.addObject("success", Constraints.RESULT_SUCCESS);
					fileLogDto.setResult(1);
				} catch (Exception e) {
					e.printStackTrace();
					fileLogDto.setResult(0);
					modelAndView.addObject("success", Constraints.RESULT_FAIL+ ":调用后台方法失败！");
				}
			}else {
				fileLogDto.setResult(0);
			}
			long endtime = System.currentTimeMillis();
			fileLogDto.setEndtime(new Date());
			long alltime = endtime - starttime;
			fileLogDto.setAlltime(alltime);
			List<FileLogDto> fileLogDtos = new ArrayList<>();
			fileLogDtos.add(fileLogDto);
			fileLogService.insert(fileLogDtos);
		} else if ("capacity".equals(type)) {
			String tablename = "t_performance_work";
			List<TableInfoDto> tableInfoDtos = commonService.tableindex(tablename);
			List<AllCapacityWorkDto> performanceWorkDtos = new ArrayList<>();
			String path = null;
			FileLogDto fileLogDto = new FileLogDto();
			long starttime = System.currentTimeMillis();
			fileLogDto.setStarttime(new Date());
			fileLogDto.setType("性能工单数据");
			try {
				path = CommonUntils.FileImprot(request, fileLogDto);			
			} catch (Exception e1) {
				e1.printStackTrace();
				fileLogDto.setResult(0);
				modelAndView.addObject("success", Constraints.RESULT_FAIL+ ":上传失败！");
			}
			if (path != null) {
				if (tableInfoDtos != null && tableInfoDtos.size() > 0) {
					int rows = FileImport.tablerows(path);
					for (int i = 0; i < rows; i++) {
						AllCapacityWorkDto workDto = new AllCapacityWorkDto();
						performanceWorkDtos.add(workDto);
					}
					try {
						FileImport.importwork(path, performanceWorkDtos, tableInfoDtos);// 将excel映射为对象
						try {						
							workService.clearPerformanceWork(); // 清空表
							workService.insertPerformanceWork(performanceWorkDtos);// 插入表并将questionflag置为-1
							modelAndView.addObject("success", Constraints.RESULT_SUCCESS);
							fileLogDto.setResult(1);
						} catch (Exception e) {
							e.printStackTrace();
							fileLogDto.setResult(0);
							modelAndView.addObject("success", Constraints.RESULT_FAIL + ":文件导入失败！");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
						fileLogDto.setResult(0);
						modelAndView.addObject("success", Constraints.RESULT_FAIL + ":文件损坏！");
					}			
				}
			}else {
				fileLogDto.setResult(0);
			}
			long endtime = System.currentTimeMillis();
			fileLogDto.setEndtime(new Date());
			long alltime = endtime - starttime;
			fileLogDto.setAlltime(alltime);
			List<FileLogDto> fileLogDtos = new ArrayList<>();
			fileLogDtos.add(fileLogDto);
			fileLogService.insert(fileLogDtos);
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
					try {
						FileImport.importwork(paths.get(0), osWorkDtos, tableInfoDtos);// 将excel映射为对象
						outServerService.clearOSWork(); // 清空表
						outServerService.insertOSWork(osWorkDtos);
						modelAndView.addObject("success", Constraints.RESULT_SUCCESS);
					} catch (Exception e) {
						e.printStackTrace();
						modelAndView.addObject("success", Constraints.RESULT_FAIL);
					}

				}
			}
		}else if ("file".equals(type)) {
			try {	
				//String filepath = "E:/";
				String filepath = "/home/hadoop/systempdata/";
				CommonUntils.MultipleFileImport(fileLogService,request, filepath,"中兴网管指标原始数据");
				modelAndView.addObject("success", Constraints.RESULT_SUCCESS);														
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				modelAndView.addObject("success", Constraints.RESULT_FAIL+ ":上传失败！");
			}
		}
		return modelAndView;
	}

}
