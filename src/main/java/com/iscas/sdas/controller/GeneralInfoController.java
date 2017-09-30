package com.iscas.sdas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.util.CommonUntils;


/**
 * 总体部分：包括首页、小区首页等
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/general")
public class GeneralInfoController {
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
	 */
	@RequestMapping("/cellhome")
	public ModelAndView cellhome(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("general/cell");
		String name = request.getParameter("name");
		String stationname = request.getParameter("stationname");
		if (!CommonUntils.isempty(name)) {
			modelAndView.addObject("cellname", name);
		}
		if (!CommonUntils.isempty(stationname)) {
			modelAndView.addObject("stationname", stationname);
		}
		return modelAndView;
	}

}
