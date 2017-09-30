package com.iscas.sdas.controller.sys;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.dto.sys.MenuDto;
import com.iscas.sdas.service.sys.MenuService;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/system/menu")
public class MenuController {

	@Autowired
	MenuService menuService;
	
	Logger logger = Logger.getLogger(MenuController.class);
	@RequestMapping("/")
	public ModelAndView page(){
		/*logger.error("我是老大！");*/
		return new ModelAndView("/sys/menu");
	}
	@RequestMapping("/getPagedList")
	@ResponseBody
	public ModelMap getPagedList() {
		ModelMap map = new ModelMap();
		List<MenuDto> menus = menuService.getAllMenu();
		map.addAttribute(Constraints.RESULT_ROW, menus);
		return map;
	}
}
