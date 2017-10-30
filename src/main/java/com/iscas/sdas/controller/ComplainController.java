package com.iscas.sdas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iscas.sdas.common.PageDto;
import com.iscas.sdas.dto.CellComplainDto;
import com.iscas.sdas.dto.ComplainDto;
import com.iscas.sdas.service.ComplainService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/complain")
public class ComplainController {

	@Autowired
	ComplainService complainService;
	
	@RequestMapping("/page")
	public ModelAndView complain(HttpServletRequest request,HttpServletResponse response){
		String cellname;
		cellname = request.getParameter("cellname");
		ModelAndView modelAndView = new ModelAndView("/general/complaint");
		if (cellname!=null) {//从其他页转入
			try {
				List<CellComplainDto> cellComplainDtos = complainService.getfocuslist(cellname);
				String row = JSON.toJSONString(cellComplainDtos);
				modelAndView.addObject("rows", row);
				modelAndView.getModel().put("cellname", cellname);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {//从首页侧边进入
			/*modelAndView.addObject("rows", null);
			modelAndView.getModelMap().put("cellname", null);*/
		}
		return modelAndView;
	}
	
	@RequestMapping("/getlist")
	@ResponseBody
	public ModelMap getlist(){
		ModelMap map = new ModelMap();
		try {
			List<ComplainDto> complainDtos = complainService.getlist();
			map.addAttribute(Constraints.RESULT_ROW, complainDtos);
		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
		return map;
	}
	
	/**
	 * 小区投诉工单
	 * @param request
	 * @return
	 */
	@RequestMapping("/getcelllist")
	@ResponseBody
	public ModelMap getcelllist(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String cellname;
		cellname = request.getParameter("cellname");
		if (cellname!=null) {
			try {
				List<CellComplainDto> cellComplainDtos = complainService.getcelllist(cellname);
				map.addAttribute(Constraints.RESULT_ROW, cellComplainDtos);
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute(Constraints.RESULT_SUCCESS, false);
			}
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
		
		return map;
	}
	
	
	@RequestMapping("/getalllist")
	@ResponseBody
	public ModelMap getpagelist(){
		ModelMap map = new ModelMap();
		try {
			//int pageNum = Integer.parseInt(num);
			//int pageSize = Integer.parseInt(size);
			//PageHelper.startPage(pageNum, pageSize); 
			List<CellComplainDto> cellComplainDtos = complainService.getalllist();
			//PageInfo<CellComplainDto> pageInfo = new PageInfo<>(cellComplainDtos);			
			map.addAttribute(Constraints.RESULT_ROW, cellComplainDtos);
		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
		return map;
	}

	@RequestMapping("/getpagelist")
	@ResponseBody
	public ModelMap getpagelist(@RequestParam(value="num",required=true,defaultValue="1")String num,@RequestParam(value="size",required=true,defaultValue="10")String size){
		ModelMap map = new ModelMap();
		try {
			int pageNum = Integer.parseInt(num);
			int pageSize = Integer.parseInt(size);
			PageHelper.startPage(pageNum, pageSize); 
			List<CellComplainDto> cellComplainDtos = complainService.getalllist();
			PageInfo<CellComplainDto> pageInfo = new PageInfo<>(cellComplainDtos);
			map.addAttribute(Constraints.RESULT_ROW, pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
		return map;
	}
	@RequestMapping("/getcomplist")
	@ResponseBody
	public ModelMap getcomplist(HttpServletRequest request){
		ModelMap map = new ModelMap();
		CellComplainDto cellComplainDto=new CellComplainDto();
		ComplainDto ComplainDto =new ComplainDto();
		try {
			String daynum = request.getParameter("daynum");
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			if (!CommonUntils.isempty(daynum)) {
				ComplainDto.setDaynum(daynum);
			}
			if (!CommonUntils.isempty(starttime)) {
				ComplainDto.setStarttime(starttime);
			}
			if (!CommonUntils.isempty(endtime)) {
				ComplainDto.setEndtime(endtime);
			}
			List<CellComplainDto> cellComplainDtos = complainService.getcomplist(ComplainDto);
			map.addAttribute(Constraints.RESULT_ROW, cellComplainDtos);
		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
		return map;
	}
	
}
