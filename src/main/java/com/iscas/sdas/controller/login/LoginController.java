package com.iscas.sdas.controller.login;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;
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
	
	private Producer captchaProducer = null;  
	  
    public void setCaptchaProducer(Producer captchaProducer){  
        this.captchaProducer = captchaProducer;  
    } 
	/**
	 * 登录页面
	 * @param request
	 * @return
	 */
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
	/**
	 * 登录验证
	 * @param request
	 * @param response
	 * @param userId
	 * @param password
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "username", defaultValue = "admin", required = true) String userId,
			@RequestParam(value = "password", defaultValue = "admin", required = true) String password) {
		ModelAndView modelAndView = null;
		String kaptcha = (String) request.getParameter("kaptcha");
		//从session中取出servlet生成的验证码text值  
		String capText = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		UserDto dto = new UserDto();
		dto.setUserId(userId);
		dto.setPassword(password);
		UserDto user = UserService.getUser(dto);
		if (kaptcha.equalsIgnoreCase(capText) || kaptcha.equals(capText)
				|| kaptcha == capText) {
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
		}else {
			modelAndView = new ModelAndView("redirect:/");
			modelAndView.addObject("loginMsg", "请输入正确的验证码！");
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
	/**
	 * 生成页面验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ModelAndView getKaptchaImage1(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		//String code = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//System.out.println("VerfiyCode:"+code);
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		String capText = captchaProducer.createText();
		// store the text in the session
		session.setAttribute("kaptcha", capText);
		// create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
}
