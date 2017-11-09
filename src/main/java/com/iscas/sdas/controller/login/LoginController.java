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
import com.iscas.sdas.util.CommonUntils;

@Controller
public class LoginController {

	@Autowired
	MenuService menuService;
	@Autowired
	UserService UserService;
	
	@RequestMapping("/")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/login/login");
		request.getSession().setAttribute("status", "isload");
		String loginMsg = (String)request.getParameter("loginMsg");
		String logoutMsg = (String)request.getParameter("logoutMsg");
		if (!CommonUntils.isempty(loginMsg)) {
			modelAndView.addObject("errormsg", loginMsg);
		}
		if (!CommonUntils.isempty(logoutMsg)) {
			modelAndView.addObject("logout", logoutMsg);
		}
		return modelAndView;
	}

	@RequestMapping("/main")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "username", defaultValue = "admin", required = true) String userId,
			@RequestParam(value = "password", defaultValue = "admin", required = true) String password) {
		ModelAndView modelAndView = null;
		UserDto dto = new UserDto();
		dto.setUserId(userId);
		dto.setPassword(password);
		UserDto user = UserService.getUser(dto);
		if (user!=null) {
			request.getSession().setAttribute("userInfo", user);
			modelAndView = new ModelAndView("main/main");
			List<MenuDto> firstMenu = menuService.getFirstMenus();//获取一级菜单及其子菜单
			request.getSession().setAttribute("menuInfo", firstMenu);
			firstMenu = getMenus(firstMenu);
			modelAndView.addObject("firstMenu", firstMenu);
		} else {
			modelAndView = new ModelAndView("redirect:/");
			modelAndView.addObject("loginMsg", "请输入正确的账号或密码！");
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
	@RequestMapping("/logout")
	public ModelAndView loginout(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		request.getSession().invalidate();
		request.getSession().setAttribute("status", "unload");	
		modelAndView.addObject("logoutMsg", "已登出！");
		return modelAndView;
	}
}
