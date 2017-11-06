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

import com.iscas.sdas.common.BaseController;
import com.iscas.sdas.common.PageDto;
import com.iscas.sdas.dto.IndexAlarmDto;
import com.iscas.sdas.service.IndexAlarmService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/indexalarm")
public class IndexAlarmController extends BaseController<IndexAlarmDto> {

	@Autowired
	IndexAlarmService alarmService;

	/**
	 * 当日预警
	 * 
	 * @param cellname
	 * @return
	 */
	@RequestMapping("/currentday")
	@ResponseBody
	public ModelMap currentDayAlarm() {
		ModelMap map = new ModelMap();
		List<IndexAlarmDto> alarmDtos = alarmService.currentDayAlarm();
		map.addAttribute(Constraints.RESULT_ROW, alarmDtos);
		return map;
	}

	/**
	 * 所有预警
	 * 
	 * @return
	 */
	@RequestMapping("/all")
	@ResponseBody
	public ModelMap allAlarm(@RequestParam(value = "currpage", required = true, defaultValue = "1") String num,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") String size,
			HttpServletRequest request) {
		ModelMap map = new ModelMap();
		IndexAlarmDto alarmDto = new IndexAlarmDto();
		String cellname = request.getParameter("cellname");
		String daynum = request.getParameter("daynum");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String type=request.getParameter("type");
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
		if (!CommonUntils.isempty(type)) {
			alarmDto.setType(type);
		}
		/*int pageNum = Integer.parseInt(num);
		int pageSize = Integer.parseInt(size);
		PageHelper.startPage(pageNum, pageSize);
		List<IndexAlarmDto> alarmDtos = alarmService.getPageList(alarmDto);
		PageInfo<IndexAlarmDto> pageInfo = new PageInfo<>(alarmDtos);
		List<IndexAlarmDto> rows = new ArrayList<>();
		for (int i = 0; i < alarmDtos.size(); i++) {
			IndexAlarmDto dto = alarmDtos.get(i);
			rows.add(dto);
		}
		PageDto<IndexAlarmDto> pageDto = new PageDto<>();
		pageDto.setTotal(pageInfo.getTotal());
		pageDto.setRows(rows);
		map.addAttribute(Constraints.RESULT_ROW, pageDto);*/
		PageDto<IndexAlarmDto> pageDto = alarmService.getPageList(alarmDto, num, size);
		map.addAttribute(Constraints.RESULT_ROW, pageDto);
		return map;
	}
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		String teString = "{\"rows\":{\"pageNum\":\"1\",\"total\":\"44\",\"pages\":\"5\",\"list\":[{\"cell_code\":\"广州番禺区广船宿舍F-ZLH-4\",\"yyyyMM\":null,\"yyyyMMdd\":\"20170803\",\"count\":1,\"app_hour\":null,\"app_result\":0,\"app_code\":0,\"app_type\":1,\"type\":\"新切换出成功率(4次连续)\",\"daynum\":null,\"starttime\":null,\"endtime\":null},{\"cell_code\":\"广州五山科技街F-ZLH-1\",\"yyyyMM\":null,\"yyyyMMdd\":\"20170803\",\"count\":1,\"app_hour\":null,\"app_result\":0,\"app_code\":0,\"app_type\":0,\"type\":\"新PRB利用率(4次连续)\",\"daynum\":null,\"starttime\":null,\"endtime\":null},{\"cell_code\":\"广州番禺区番禺得意精密电子厂F-ZLW-1\",\"yyyyMM\":null,\"yyyyMMdd\":\"20170803\",\"count\":1,\"app_hour\":null,\"app_result\":0,\"app_code\":0,\"app_type\":0,\"type\":\"新PRB利用率(4次连续)\",\"daynum\":null,\"starttime\":null,\"endtime\":null},{\"cell_code\":\"广州白云区沙涌北D-ZLH-1\",\"yyyyMM\":null,\"yyyyMMdd\":\"20170804\",\"count\":1,\"app_hour\":null,\"app_result\":0,\"app_code\":0,\"app_type\":0,\"type\":\"新PRB利用率(4次连续)\",\"daynum\":null,\"starttime\":null,\"endtime\":null},{\"cell_code\":\"广州天河路D-ZLH-2\",\"yyyyMM\":null,\"yyyyMMdd\":\"20170804\",\"count\":1,\"app_hour\":null,\"app_result\":0,\"app_code\":0,\"app_type\":0,\"type\":\"新PRB利用率(4次连续)\",\"daynum\":null,\"starttime\":null,\"endtime\":null},{\"cell_code\":\"广州西区赤岗D-ZLH-103\",\"yyyyMM\":null,\"yyyyMMdd\":\"20170804\",\"count\":1,\"app_hour\":null,\"app_result\":0,\"app_code\":0,\"app_type\":0,\"type\":\"新PRB利用率(4次连续)\",\"daynum\":null,\"starttime\":null,\"endtime\":null},{\"cell_code\":\"广州白云区白云邮区中心局F-ZLH-1\",\"yyyyMM\":null,\"yyyyMMdd\":\"20170804\",\"count\":1,\"app_hour\":null,\"app_result\":0,\"app_code\":0,\"app_type\":1,\"type\":\"新切换出成功率(4次连续)\",\"daynum\":null,\"starttime\":null,\"endtime\":null},{\"cell_code\":\"广州白云区大朗朗环路F-ZLH-3\",\"yyyyMM\":null,\"yyyyMMdd\":\"20170805\",\"count\":1,\"app_hour\":null,\"app_result\":0,\"app_code\":0,\"app_type\":0,\"type\":\"新PRB利用率(4次连续)\",\"daynum\":null,\"starttime\":null,\"endtime\":null},{\"cell_code\":\"广州白云区江夏村村委D-ZLH-2\",\"yyyyMM\":null,\"yyyyMMdd\":\"20170805\",\"count\":1,\"app_hour\":null,\"app_result\":0,\"app_code\":0,\"app_type\":0,\"type\":\"新PRB利用率(4次连续)\",\"daynum\":null,\"starttime\":null,\"endtime\":null},{\"cell_code\":\"广州天河区东站西F-ZLH-2\",\"yyyyMM\":null,\"yyyyMMdd\":\"20170805\",\"count\":1,\"app_hour\":null,\"app_result\":0,\"app_code\":0,\"app_type\":0,\"type\":\"新PRB利用率(4次连续)\",\"daynum\":null,\"starttime\":null,\"endtime\":null}]}}";
		return teString;
	}

	@RequestMapping("/")
	public ModelAndView page() {
		return new ModelAndView("alarm/indexalarm");
	}
	/**
	 *指标预警页面小区预警列表
	 * 
	 * @return
	 */
	@RequestMapping("/getLastDay")
	@ResponseBody
	public ModelMap getLastDay(HttpServletRequest request) {
		ModelMap map = new ModelMap();
		IndexAlarmDto alarmDto = new IndexAlarmDto();
		String cell_code = request.getParameter("cell_code");
		
		String type = request.getParameter("type");
		if (!CommonUntils.isempty(cell_code)) {
			alarmDto.setCell_code(cell_code);
		}
		if (!CommonUntils.isempty(type)) {
			alarmDto.setType(type);
		}
		List<IndexAlarmDto> dtos = alarmService.getLastDay(alarmDto);
		map.addAttribute(Constraints.RESULT_ROW, dtos);
		return map;
	}
}
