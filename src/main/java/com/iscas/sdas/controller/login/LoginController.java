package com.iscas.sdas.controller.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.dto.sys.MenuDto;
import com.iscas.sdas.dto.sys.UserDto;
import com.iscas.sdas.service.sys.MenuService;
import com.iscas.sdas.service.sys.UserService;

@Controller
public class LoginController {

	@Autowired
	MenuService menuService;
	@Autowired
	UserService UserService;
	
	@RequestMapping("/")
	public ModelAndView login() {
		return new ModelAndView("/login/login");
	}

	@RequestMapping("/main")
	public ModelAndView tohome(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "username", defaultValue = "admin", required = true) String userId,
			@RequestParam(value = "password", defaultValue = "admin", required = true) String password) {
		ModelAndView modelAndView = null;
		UserDto dto = new UserDto();
		dto.setUserId(userId);
		dto.setPassword(password);
		UserDto user = UserService.getUser(dto);
		if (user!=null) {
			modelAndView = new ModelAndView("main/main");
			List<MenuDto> firstMenu = menuService.getFirstMenus();
			firstMenu = getMenus(firstMenu);
			modelAndView.addObject("firstMenu", firstMenu);
		} else {
			modelAndView = new ModelAndView("redirect:/");
		}

		return modelAndView;

	}

	/**
	 * 根据一级菜单递归获取二级菜单
	 * 
	 * @param firstMenu
	 * @return
	 */
	private List<MenuDto> getMenus(List<MenuDto> firstMenu) {
		for (int i = 0; i < firstMenu.size(); i++) {
			Integer pid = firstMenu.get(i).getMENU_ID();
			List<MenuDto> secondmenus = menuService.getSecondMenu(pid);
			firstMenu.get(i).setMenus(secondmenus);
			getMenus(firstMenu.get(i).getMenus());
		}
		return firstMenu;
	}

}
