package com.iscas.sdas.controller.sys;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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
		return new ModelAndView("/sys/menu");
	}
	@RequestMapping("/getPagedList")
	@ResponseBody
	public ModelMap getPagedList(@RequestParam(value="currpage",defaultValue="1",required=true)String currPage,
			@RequestParam(value="pageSize",defaultValue="10",required=true)String size) {
		ModelMap map = new ModelMap();
		int pageNum = Integer.parseInt(currPage);
		int pageSize = Integer.parseInt(size);
		PageHelper.startPage(pageNum, pageSize);
		List<MenuDto> menus = menuService.getAllMenu();
		PageInfo<MenuDto> pageInfo = new PageInfo<>(menus);
		List<MenuDto> rows = new ArrayList<>();
		for (int i = 0; i < menus.size(); i++) {
			MenuDto dto = menus.get(i);
			rows.add(dto);
		}
		PageDto<MenuDto> page = new PageDto<>();
		page.setTotal(pageInfo.getTotal());
		page.setRows(rows);
		map.addAttribute(Constraints.RESULT_ROW, page);
		return map;
	}
}
