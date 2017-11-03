package com.iscas.sdas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iscas.sdas.common.PageDto;
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
	public ModelMap allAlarm(@RequestParam(value = "currpage", required = true, defaultValue = "1") String num,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") String size,HttpServletRequest request){
		ModelMap map = new ModelMap();
		AlarmDto alarmDto = new AlarmDto();
		String cellname = request.getParameter("cellname");
		String daynum = request.getParameter("daynum");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		if (!CommonUntils.isempty(cellname)) {
			alarmDto.setCell_code(cellname);
		}
		if (!CommonUntils.isempty(daynum)) {
			alarmDto.setDaynum(daynum);
		}
		if (!CommonUntils.isempty(starttime)) {
			alarmDto.setStarttime(starttime);
		}
		if (!CommonUntils.isempty(endtime)) {
			alarmDto.setEndtime(endtime);
		}
		int pageNum = Integer.parseInt(num);
		int pageSize = Integer.parseInt(size);
		PageHelper.startPage(pageNum, pageSize);
		List<AlarmDto> alarmDtos =  alarmService.allAlarm(alarmDto);	
		PageInfo<AlarmDto> pageInfo = new PageInfo<>(alarmDtos);
		List<AlarmDto> rows = new ArrayList<>();
		for (int i = 0; i < alarmDtos.size(); i++) {
			AlarmDto dto = alarmDtos.get(i);
			rows.add(dto);
		}
		PageDto<AlarmDto> pageDto = new PageDto<>();
		pageDto.setTotal(pageInfo.getTotal());
		pageDto.setRows(rows);
		map.addAttribute(Constraints.RESULT_ROW, pageDto);
		return map;
	}
	@RequestMapping("/")
	public ModelAndView page(){
		return new ModelAndView("alarm/alarm");
	}
}
