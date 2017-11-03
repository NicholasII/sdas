package com.iscas.sdas.controller.cell;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iscas.sdas.common.PageDto;
import com.iscas.sdas.dto.GroupIndexMeatdata;
import com.iscas.sdas.dto.TotalHealthInfoDto;
import com.iscas.sdas.dto.cell.BaseCellHealth;
import com.iscas.sdas.dto.cell.CellDto;
import com.iscas.sdas.dto.cell.CellHealthTableDto;
import com.iscas.sdas.service.cell.CellService;
import com.iscas.sdas.util.CommonUntils;
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
	/**
	 * 分组首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/celltable")
	public ModelAndView celllist(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView("cell/group");
		return view;
		
	}
	/**
	 * 小区列表（加查询条件）--加分页
	 * @param request
	 * @return
	 */
	@RequestMapping("/getcelllist")
	@ResponseBody
	public ModelMap getlist(@RequestParam(value = "currpage", required = true, defaultValue = "1") String num,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") String size,HttpServletRequest request){
		ModelMap map = new ModelMap();
		CellDto cellDto = new CellDto();
		String name = request.getParameter("name");
		String scene = request.getParameter("scene");
		String type = request.getParameter("type");
		if (!CommonUntils.isempty(name)) {
			cellDto.setNetwork_name(name);
		}
		if (!CommonUntils.isempty(type)&&!"全部".equals(type)) {
			cellDto.setGroup_type(type);
		}
		if (!CommonUntils.isempty(scene)&&!"全部".equals(scene)) {
			cellDto.setCover_scene(scene);
		}
		int pageNum = Integer.parseInt(num);
		int pageSize = Integer.parseInt(size);
		PageHelper.startPage(pageNum, pageSize);
		List<CellDto> cellDtos = cellService.getCellList(cellDto);
		PageInfo<CellDto> pageInfo = new PageInfo<>(cellDtos);
		List<CellDto> rows = new ArrayList<>();
		for (int i = 0; i < cellDtos.size(); i++) {
			CellDto dto = cellDtos.get(i);
			rows.add(dto);
		}
		PageDto<CellDto> pageDto = new PageDto<>();
		pageDto.setTotal(pageInfo.getTotal());
		pageDto.setRows(rows);
		map.addAttribute(Constraints.RESULT_ROW, pageDto);
	
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
	/**
	 * 小组模型
	 * @param grouptype
	 * @return
	 */
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
	 * @throws UnsupportedEncodingException 
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
	 * 小区健康度历史表格
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/healthtable")
	@ResponseBody
	public ModelMap healthtable(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String cellname = request.getParameter("cellname");
		List<CellHealthTableDto> list = cellService.generateCellHealthTable(cellname);
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
	 * 实时健康度
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
	/**
	 * 小区首页异常指标预警
	 * @param request
	 * @return
	 */
	@RequestMapping("/alarm_healthtrend")
	@ResponseBody
	public ModelMap alarm_healthtrend(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String cellname = request.getParameter("cellname");	
		List<TotalHealthInfoDto> list = cellService.getalarmhealthtrend(cellname);
		map.addAttribute(Constraints.RESULT_ROW, list);
		return map;
	}
	/**
	 * 当前时刻健康度
	 * @param request
	 * @return
	 */
	@RequestMapping("/rtratio")
	@ResponseBody
	public ModelMap rtratio(HttpServletRequest request){
		ModelMap map = new ModelMap();
		try {
			String cellname = request.getParameter("cellname");
			String count = request.getParameter("count");
			BaseCellHealth baseCellHealth = cellService.newestHealth(cellname);
			if (baseCellHealth!=null) {
				/*String str_hour;
				int hour;
				if (CommonUntils.isempty(count)) {
					hour = Calendar.HOUR_OF_DAY;
					str_hour = hour>=10?hour+"":"0"+hour;
				}else {
					hour = Integer.parseInt(count);
					str_hour = hour>=10?hour+"":"0"+hour;
				}				
				String range  = (String)baseCellHealth.getClass().getMethod("getRange_"+str_hour, null).invoke(baseCellHealth, null);
				if (range!=null) {
					JSONArray array = JSON.parseArray(range);
					if (array!=null) {
						for (int i = 0; i < array.size(); i++) {
								JSONObject obj = array.getJSONObject(i);
								if ("Ratio".equals(obj.getString("Key"))) {
								    double ratio = Double.parseDouble(obj.get("Value").toString())*100;
								    map.addAttribute("ratio", ratio);
								    break;
								}
						}
					}
				}*/
				/////////////
				Method[] methods = baseCellHealth.getClass().getMethods();
				for (int i=0;i<methods.length;i++) {
					if (methods[i].getName().startsWith("getRange")) {
						String rangestr = (String)methods[i].invoke(baseCellHealth, null);						
						int  moment = Integer.parseInt(methods[i].getName().substring(methods[i].getName().lastIndexOf("_")+1));
						if (rangestr!=null) {
							JSONArray array = JSON.parseArray(rangestr);
							if (array.size()<2) {
								String str_hour = (moment-1)>=10?moment+"":"0"+moment;
								String newrange  = (String)baseCellHealth.getClass().getMethod("getRange_"+str_hour, null).invoke(baseCellHealth, null);
								JSONArray newarray = JSON.parseArray(newrange);
								if (newarray!=null) {
									for (int j = 0; j < newarray.size(); j++) {
											JSONObject obj = newarray.getJSONObject(j);
											if ("Ratio".equals(obj.getString("Key"))) {
											    double ratio = Double.parseDouble(obj.get("Value").toString())*100;
											    
											    map.addAttribute("ratio", ratio);
											}
									}
									break;
								}
							}
							if(moment==23){
								String str_hour = (moment-1)>=10?(moment-1)+"":"0"+(moment-1);
								String newrange  = (String)baseCellHealth.getClass().getMethod("getRange_"+str_hour, null).invoke(baseCellHealth, null);
								JSONArray newarray = JSON.parseArray(newrange);
								if (newarray!=null) {
									for (int j = 0; j < newarray.size(); j++) {
											JSONObject obj = newarray.getJSONObject(j);
											if ("Ratio".equals(obj.getString("Key"))) {
											    double ratio = Double.parseDouble(obj.get("Value").toString())*100;
											    DecimalFormat  df = new DecimalFormat("######0.00");
											    ratio = Double.parseDouble(df.format(ratio));
											    map.addAttribute("ratio", ratio);
											}
									}
									break;
								}
							}
						}
					}	
					
				}
				
			}
		} catch (Exception e) {	
			e.printStackTrace();
		}		
		return map;
	}
}
