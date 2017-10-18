package com.iscas.sdas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.dto.AlarmDto;
import com.iscas.sdas.service.AlarmService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/alarm")
public class AlarmController {
	@Autowired
	AlarmService alarmService;
	/**
	 * 当日预警
	 * @param cellname
	 * @return
	 */
	@RequestMapping("/currentday")
	@ResponseBody
	public ModelMap currentDayAlarm(){
		ModelMap map = new ModelMap();
		List<AlarmDto> alarmDtos =  alarmService.currentDayAlarm();
		map.addAttribute(Constraints.RESULT_ROW, alarmDtos);
		return map;
	}
	/**
	 * 所有预警
	 * @return
	 */
	@RequestMapping("/all")
	@ResponseBody
	public ModelMap allAlarm(HttpServletRequest request){
		ModelMap map = new ModelMap();
		AlarmDto alarmDto = new AlarmDto();
		String cellname = request.getParameter("cellname");
		String time = request.getParameter("time");
		if (!CommonUntils.isempty(cellname)) {
			alarmDto.setCell_code(cellname);
		}
		if (!CommonUntils.isempty(time)) {
			alarmDto.setYyyyMMdd(time);
		}
		List<AlarmDto> alarmDtos =  alarmService.allAlarm(alarmDto);
		map.addAttribute(Constraints.RESULT_ROW
					, alarmDtos);
		return map;
	}
	@RequestMapping("/")
	public ModelAndView page(){
		return new ModelAndView("alarm/alarm");
	}
}
