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
import com.iscas.sdas.dto.work.DeviceWorkDto;
import com.iscas.sdas.dto.work.OutServerDto;
import com.iscas.sdas.service.work.DeviceWorkService;
import com.iscas.sdas.service.work.OutServerService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/devicework")
public class DeviceController {

	@Autowired
	DeviceWorkService deviceWorkService;
	
	@RequestMapping("/getlist")
	@ResponseBody
	public ModelMap getlist(HttpServletRequest request){
		ModelMap map = new ModelMap();
		DeviceWorkDto deviceWorkDto = new DeviceWorkDto();
		String cellname = request.getParameter("cellname");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		if (!CommonUntils.isempty(cellname)) {
			deviceWorkDto.setCell_name(cellname);
		}
		if (!CommonUntils.isempty(starttime) && !CommonUntils.isempty(endtime)) {
			deviceWorkDto.setBuild_bill_time(CommonUntils.strToTimestap(starttime));
		}
		if (starttime=="") {
			starttime = null;
		}
		if (endtime=="") {
			endtime = null;
		}
		List<DeviceWorkDto> outServerDtos = deviceWorkService.getlist(deviceWorkDto,starttime,endtime);
		map.addAttribute(Constraints.RESULT_ROW, outServerDtos);
		return map;
	}
	
	@RequestMapping("/oneweek")
	@ResponseBody
	public ModelMap getlistWhithinOneWeek(HttpServletRequest request,HttpServletResponse response){
		ModelMap map = new ModelMap();
		String cellname = request.getParameter("cellname");
		if (cellname!=null) {
			List<DeviceWorkDto> works = deviceWorkService.getlistoneweek(cellname);
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
			List<DeviceWorkDto> works = deviceWorkService.getlistonemonth(cellname);
			map.addAttribute(Constraints.RESULT_ROW, works);
		}			
		return map;
	}
	@RequestMapping("/getlistdevice")
	@ResponseBody
	public ModelMap getlistdevice(HttpServletRequest request){
		ModelMap map = new ModelMap();
		DeviceWorkDto deviceWorkDto=new DeviceWorkDto();
		String daynum = request.getParameter("daynum");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		if (!CommonUntils.isempty(daynum)) {
			deviceWorkDto.setDaynum(daynum);
		}
		if (!CommonUntils.isempty(starttime)) {
			deviceWorkDto.setStarttime(starttime);
		}
		if (!CommonUntils.isempty(endtime)) {
			deviceWorkDto.setEndtime(endtime);
		}
		List<DeviceWorkDto> works = deviceWorkService.getlistdevice(deviceWorkDto);
		if (works!=null) {
			map.addAttribute(Constraints.RESULT_ROW, works);
		}			
		return map;
	}
}
