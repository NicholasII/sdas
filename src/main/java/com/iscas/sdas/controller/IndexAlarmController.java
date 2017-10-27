package com.iscas.sdas.controller;

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
import com.iscas.sdas.common.BaseController;
import com.iscas.sdas.dto.IndexAlarmDto;
import com.iscas.sdas.service.IndexAlarmService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/indexalarm")
public class IndexAlarmController extends BaseController<IndexAlarmDto>{

	@Autowired
	IndexAlarmService alarmService;
	
	/**
	 * 当日预警
	 * @param cellname
	 * @return
	 */
	@RequestMapping("/currentday")
	@ResponseBody
	public ModelMap currentDayAlarm(@RequestParam(value="num",required=true,defaultValue="1")String num,@RequestParam(value="size",required=true,defaultValue="10")String size){
		ModelMap map = new ModelMap();
		int pageNum = Integer.parseInt(num);
		int pageSize = Integer.parseInt(size);
		PageHelper.startPage(pageNum, pageSize); 
		List<IndexAlarmDto> alarmDtos =  alarmService.currentDayAlarm();
		PageInfo<IndexAlarmDto> pageInfo = new PageInfo<>(alarmDtos);
		map.addAttribute(Constraints.RESULT_ROW, pageInfo);
		return map;
	}
	/**
	 * 所有预警
	 * @return
	 */
	@RequestMapping("/all")
	@ResponseBody
	public ModelMap allAlarm(@RequestParam(value="num",required=true,defaultValue="1")String num,@RequestParam(value="size",required=true,defaultValue="10")String size,HttpServletRequest request){
		ModelMap map = new ModelMap();
		IndexAlarmDto alarmDto = new IndexAlarmDto();
		String cellname = request.getParameter("cellname");
		String daynum = request.getParameter("daynum");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		if (cellname!=null) {
			alarmDto.setCell_code(cellname);
		}
		if (daynum!=null) {
			alarmDto.setDaynum(Integer.parseInt(daynum));
		}
		if (starttime!=null) {
			alarmDto.setStarttime(starttime);
		}
		if (endtime!=null) {
			alarmDto.setEndtime(endtime);
		}
		int pageNum = Integer.parseInt(num);
		int pageSize = Integer.parseInt(size);
		PageHelper.startPage(pageNum, pageSize); 
		alarmService.init(null);
		List<IndexAlarmDto> alarmDtos =  alarmService.getpagelist(alarmDto);
		PageInfo<IndexAlarmDto> pageInfo = new PageInfo<>(alarmDtos);
		map.addAttribute(Constraints.RESULT_ROW, pageInfo);
		return map;
	}
	@RequestMapping("/")
	public ModelAndView page(){
		return new ModelAndView("alarm/indexalarm");
	}
	
	
}
