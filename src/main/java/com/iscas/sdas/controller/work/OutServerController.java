package com.iscas.sdas.controller.work;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iscas.sdas.dto.work.DeviceWorkDto;
import com.iscas.sdas.dto.work.OutServerDto;
import com.iscas.sdas.service.work.OutServerService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/outserverwork")
public class OutServerController {

	@Autowired
	OutServerService outServerService;
	@RequestMapping("/getlist")
	@ResponseBody
	public ModelMap getlist(HttpServletRequest request){
		ModelMap map = new ModelMap();
		OutServerDto outServerDto = new OutServerDto();
		String cellname = request.getParameter("cellname");
		if (!CommonUntils.isempty(cellname)) {
			outServerDto.setCell_name(cellname);
		}
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		if (!CommonUntils.isempty(cellname)) {
			outServerDto.setCell_name(cellname);
		}
		if (!CommonUntils.isempty(starttime) && CommonUntils.isempty(endtime)) {
			outServerDto.setStart_time(CommonUntils.strToTimestap(starttime));
		}
		if (starttime=="") {
			starttime = null;
		}
		if (endtime=="") {
			endtime = null;
		}
		List<OutServerDto> outServerDtos = outServerService.getlist(outServerDto,starttime,endtime);
		map.addAttribute(Constraints.RESULT_ROW
				, outServerDtos);
		return map;
	}
	@RequestMapping("/oneweek")
	@ResponseBody
	public ModelMap getlistWhithinOneWeek(HttpServletRequest request,HttpServletResponse response){
		ModelMap map = new ModelMap();
		String cellname = request.getParameter("cellname");
		if (cellname!=null) {
			List<OutServerDto> works = outServerService.getlistoneweek(cellname);
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
			List<OutServerDto> works = outServerService.getlistonemonth(cellname);
			map.addAttribute(Constraints.RESULT_ROW, works);
		}			
		return map;
	}
}
