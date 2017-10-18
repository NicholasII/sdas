package com.iscas.sdas.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.dto.BaseStationHealthRatio;
import com.iscas.sdas.service.cell.CellService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;


/**
 * 总体部分：包括首页、小区首页等
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/general")
public class GeneralInfoController {
	
	@Autowired
	CellService cellService;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/home")
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView("/general/home");
		return modelAndView;
	}
	/**
	 * 小区首页
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/cellhome")
	public ModelAndView cellhome(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView("general/cell");
		String name = request.getParameter("name");
		String stationname = request.getParameter("stationname");
		String coverscene = request.getParameter("cover_scene");
		String usedband = request.getParameter("used_band");
		if (!CommonUntils.isempty(name)) {
			modelAndView.addObject("cellname", name);
		}
		if (!CommonUntils.isempty(stationname)) {
			modelAndView.addObject("stationname", stationname);
		}
		if (!CommonUntils.isempty(coverscene)) {
			modelAndView.addObject("coverscene", coverscene);
		}
		if (!CommonUntils.isempty(usedband)) {
			modelAndView.addObject("usedband", usedband);
		}
		return modelAndView;
	}
	@RequestMapping("/healthgroup")
	@ResponseBody
	public ModelMap healthgroup(){
		ModelMap map = new ModelMap();
		try {
			List<BaseStationHealthRatio> ratiogroup = cellService.currentHealthGroup();
			map.addAttribute(Constraints.RESULT_ROW, ratiogroup);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
