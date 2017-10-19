package com.iscas.sdas.controller.work;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iscas.sdas.dto.work.CapacityWorkDto;
import com.iscas.sdas.service.work.CapacityWorkService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/capacitywork")
public class CapacityWorkController {

	@Autowired
	CapacityWorkService capacityWorkService;
	
	@RequestMapping("/gettable")
	@ResponseBody
	public ModelMap gettable(HttpServletRequest request,HttpServletResponse response){
		ModelMap map = new ModelMap();
		String cellname = request.getParameter("cellname");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String area = request.getParameter("area");
		String content = request.getParameter("content");
		CapacityWorkDto workDto = new CapacityWorkDto();
		if (!CommonUntils.isempty(cellname)) {
			workDto.setCellid(cellname);
		}
		if (!CommonUntils.isempty(area)) {
			workDto.setBelong_area(area);
		}
		if (!CommonUntils.isempty(content)&&!"全部".equals(content)) {
			workDto.setMonitor_content(content);
		}
		if (!CommonUntils.isempty(starttime) && CommonUntils.isempty(endtime)) {
			workDto.setOccurrence_time(CommonUntils.strToTimestap(starttime));
		}
		if (starttime=="") {
			starttime = null;
		}
		if (endtime=="") {
			endtime = null;
		}
		List<CapacityWorkDto> works = capacityWorkService.getlist(workDto,starttime,endtime);
		map.addAttribute(Constraints.RESULT_ROW, works);
		return map;
	}
	/**
	 * 可以工单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getdoubttable")
	@ResponseBody
	public ModelMap getdoubttable(HttpServletRequest request,HttpServletResponse response){
		ModelMap map = new ModelMap();
		List<CapacityWorkDto> works = capacityWorkService.getdoubtlist();
		map.addAttribute(Constraints.RESULT_ROW, works);
		return map;
	}
	
	@RequestMapping("/belongare")
	@ResponseBody
	public ModelMap getbelongare(HttpServletRequest request,HttpServletResponse response){
		ModelMap map = new ModelMap();
		List<String> areas = capacityWorkService.getbelongaera();
		map.addAttribute(Constraints.RESULT_ROW, areas);
		return map;
	}
	
	@RequestMapping("/oneweek")
	@ResponseBody
	public ModelMap getlistWhithinOneWeek(HttpServletRequest request,HttpServletResponse response){
		ModelMap map = new ModelMap();
		String cellname = request.getParameter("cellname");
		if (cellname!=null) {
			List<CapacityWorkDto> works = capacityWorkService.getlistWhithinOneWeek(cellname);
			map.addAttribute(Constraints.RESULT_ROW, works);
		}
		return map;
	}
	
	@RequestMapping("/onemonth")
	@ResponseBody
	public ModelMap getlistWhithinOneMonth(HttpServletRequest request,HttpServletResponse response){
		ModelMap map = new ModelMap();
		String cellname = request.getParameter("cellname");
		if (cellname!=null) {
			List<CapacityWorkDto> works = capacityWorkService.getlistWhithinOneMonth(cellname);
			map.addAttribute(Constraints.RESULT_ROW, works);
		}			
		return map;
	}
	
}
