package com.iscas.sdas.controller.cell;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.dto.GroupIndexMeatdata;
import com.iscas.sdas.dto.TotalHealthInfoDto;
import com.iscas.sdas.dto.cell.CellDto;
import com.iscas.sdas.service.cell.CellService;
import com.iscas.sdas.util.Constraints;
/**
 * 小区有关：全部表、分组、健康度 
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/cell")
public class CellController {

	@Autowired
	CellService cellService;
	
	@RequestMapping("/celltable")
	public ModelAndView celllist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView("cell/table");
		return view;
		
	}
	@RequestMapping("/getcelllist")
	@ResponseBody
	public ModelMap getlist(HttpServletRequest request){
		ModelMap map = new ModelMap();
		CellDto cellDto = new CellDto();
		String name = request.getParameter("name");
		String scene = request.getParameter("scene");
		String type = request.getParameter("type");
		if (!"".equals(name)&&name!=null) {
			cellDto.setNetwork_name(name);
		}
		if (!"".equals(type)&&!"全部".equals(type)&&type!=null) {
			cellDto.setGroup_type(type);
		}
		if (!"".equals(scene)&&!"全部".equals(scene)&&scene!=null) {
			cellDto.setCover_scene(scene);
		}
		List<CellDto> cellDtos = cellService.getCellList(cellDto);
		if (cellDtos!=null) {
			map.addAttribute(Constraints.RESULT_ROW, cellDtos);
			map.addAttribute(Constraints.RESULT_SUCCESS, true);
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
		return map;
	}
	@RequestMapping("/group")
	@ResponseBody
	public ModelMap getgroup(){
		ModelMap map = new ModelMap();
		List<String> groups = cellService.getCellGroup();
		map.addAttribute(Constraints.RESULT_ROW, groups);
		return map;
	}
	@RequestMapping("/groupindexs")
	@ResponseBody
	public ModelMap getgroupindes(@RequestParam(value="type",defaultValue="I",required = true)String grouptype){
		ModelMap map = new ModelMap();
		List<GroupIndexMeatdata> groups = cellService.getGroupIndexs(grouptype);
		map.addAttribute(Constraints.RESULT_ROW, groups);
		return map;
	}
	/**
	 * 小区健康度历史
	 * @param request
	 * @return
	 */
	@RequestMapping("/healthtrend")
	@ResponseBody
	public ModelMap healthtrend(HttpServletRequest request,
			@RequestParam(required=true,defaultValue="week",value="type")String type){
		ModelMap map = new ModelMap();
		String cellname = request.getParameter("cellname");
		String starttime = null,endtime = null;
		if ("select".equals(type)) {
			starttime = request.getParameter("start");
			endtime = request.getParameter("end");
		}
		List<TotalHealthInfoDto> list = cellService.generateCellHealthTrend(cellname,type,starttime,endtime);
		map.addAttribute(Constraints.RESULT_ROW, list);
		return map;
	}
	/**
	 * 小区属于组别
	 * @param cellname
	 * @return
	 */
	@RequestMapping("/belonggroup")
	@ResponseBody
	public ModelMap getbelonggroup(@RequestParam(value="cellname",defaultValue="I",required = true)String cellname){
		ModelMap map = new ModelMap();
		String group = cellService.getgroup(cellname);
		map.addAttribute("group", group);
		return map;
	}
	/**
	 * 实时简况度
	 * @param request
	 * @return
	 */
	@RequestMapping("/rthealth")
	@ResponseBody
	public ModelMap rthealth(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String cellname = request.getParameter("cellname");
		List<TotalHealthInfoDto> list = cellService.generateCellRTHealth(cellname);
		map.addAttribute(Constraints.RESULT_ROW, list);
		return map;
	}
	
}
