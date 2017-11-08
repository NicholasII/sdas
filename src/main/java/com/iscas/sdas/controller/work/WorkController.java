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

import com.iscas.sdas.dto.TableInfoDto;
import com.iscas.sdas.dto.work.CapacityWorkDto;
import com.iscas.sdas.dto.work.AllCapacityWorkDto;
import com.iscas.sdas.service.CommonService;
import com.iscas.sdas.service.WorkService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;
import com.iscas.sdas.util.FileImport;

import tasks.BGTask;
import tasks.sheet.NewGDCheckTask;


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
	/**
	 * 导入性能工单
	 * @param request
	 * @return
	 */
	@RequestMapping("/import/capacity")
	public ModelAndView fileimport(HttpServletRequest request){
		ModelAndView map = new ModelAndView("/work/capacity");
		String tablename = "t_performance_work";
		List<TableInfoDto> tableInfoDtos = commonService.tableindex(tablename);		
		List<AllCapacityWorkDto> performanceWorkDtos = new ArrayList<>();
		List<String> paths = CommonUntils.MultipleFilesUpload(request);
		if (paths!=null && paths.size()>0) {
			if (tableInfoDtos!=null && tableInfoDtos.size()>0) {
				int rows = FileImport.tablerows(paths.get(0));
				for (int i = 0; i < rows; i++) {
					AllCapacityWorkDto workDto = new AllCapacityWorkDto();
					performanceWorkDtos.add(workDto);
				}
				try {
					FileImport.importwork(paths.get(0), performanceWorkDtos, tableInfoDtos);//将excel映射为对象
					workService.clearPerformanceWork();	//清空表
					workService.insertPerformanceWork(performanceWorkDtos);//插入表并将questionflag置为-1
				} catch (Exception e) {
					map.addObject(Constraints.RESULT_SUCCESS, false);
					e.printStackTrace();
				}
			}
		}else {
			map.addObject(Constraints.RESULT_SUCCESS, false);
		}		
		return map;
		
	}

	/**
	 * 工单验证
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/validate")
	@ResponseBody
	public ModelMap workvalidate() throws Exception{
		ModelMap map = new ModelMap();
		BGTask task = new NewGDCheckTask();//XXX 调用spark跑工单验证
		task.doTask();//阻塞
		//task.runTaskWithThread();//新的线程
		//List<CapacityWorkDto> capacityWorks = workService.workValidate(workService.allvalidatelist(),0);
		List<CapacityWorkDto> capacityWorks =  workService.workValidate2();
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
	/**
	 * 获取rt表中所有可疑工单（演示用）
	 * @return
	 */
	@RequestMapping("/test")
	@ResponseBody
	public ModelMap test(){
		ModelMap map = new ModelMap();
		
		List<CapacityWorkDto> capacityWorks = workService.getAllDoubtWorks();
		if (capacityWorks.size()>0) {
			map.addAttribute(Constraints.RESULT_ROW, capacityWorks);
			map.addAttribute(Constraints.RESULT_SUCCESS, true);
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
		return map;
	}
	/**
	 * 获取工单验证表中所有工单
	 * @return
	 */
	@RequestMapping("/allrtworks")
	@ResponseBody
	public ModelMap alltest(HttpServletRequest request){
		ModelMap map = new ModelMap();
		CapacityWorkDto cwdto=new CapacityWorkDto();
		String cellid = request.getParameter("cellid");
		String daynum = request.getParameter("daynum");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String questionflag = request.getParameter("questionflag");
		if (!CommonUntils.isempty(cellid)) {
			cwdto.setCellid(cellid);
		}
		if (!CommonUntils.isempty(daynum)) {
			cwdto.setDaynum(daynum);
		}
		if (!CommonUntils.isempty(starttime)) {
			cwdto.setStarttime(starttime);
		}
		if (!CommonUntils.isempty(endtime)) {
			cwdto.setEndtime(endtime);
		}
		if (!CommonUntils.isempty(questionflag) && !"3".equals(questionflag)) {
			cwdto.setQuestionflag(Integer.parseInt(questionflag));
		}
		List<CapacityWorkDto> capacityWorks = workService.getAllWorks(cwdto);
		map.addAttribute(Constraints.RESULT_ROW, capacityWorks);
		return map;
	}
}
