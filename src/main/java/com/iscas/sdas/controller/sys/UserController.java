package com.iscas.sdas.controller.sys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.common.BaseController;
import com.iscas.sdas.common.PageDto;
import com.iscas.sdas.dto.sys.UserDto;
import com.iscas.sdas.service.sys.UserService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController<UserDto>{
	
	@Autowired
	UserService userService;
	/**
	 * 用户管理界面
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView page(HttpServletRequest request){
		UserDto userInfo = (UserDto)request.getSession().getAttribute("userInfo");
		ModelAndView modelAndView = new ModelAndView("sys/user/user");
		if (userInfo!=null) {
			modelAndView.addObject("role", userInfo.getRolename());
		}
		return modelAndView;
	}
	/**
	 * 获取用户列表
	 * @param num
	 * @param size
	 * @param userid
	 * @param username
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ModelMap userList(@RequestParam(value = "currpage", required = true, defaultValue = "1") String num,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") String size,
			@RequestParam(value="userId")String userid,@RequestParam(value="userName")String username,HttpServletRequest request){
		ModelMap map = new ModelMap();
		UserDto userDto = new UserDto();
		UserDto userInfo = (UserDto)request.getSession().getAttribute("userInfo");
		if (userInfo!=null) {
			if (Constraints.ROLE_ADMIN.equals(userInfo.getRolename())) {
				if (!CommonUntils.isempty(userid)) {
					userDto.setUserId(userid);
				}
				if (!CommonUntils.isempty(username)) {
					userDto.setUsername(username);
				}
			}else if (Constraints.ROLE_USER.equals(userInfo.getRolename())) {
				userDto.setUserId(userInfo.getUserId());
			}		
		}	
		PageDto<UserDto> pageDto = userService.getPageList(userDto, num, size);
		map.addAttribute(Constraints.RESULT_ROW, pageDto);
		return map;
	}
	/**
	 * 添加/更新用户
	 * @param request
	 * @param isNew '0'为更新；'1'为新建
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public ModelMap insert(HttpServletRequest request,@RequestParam(value="isNew",required=true)String isNew){
		ModelMap map = new ModelMap();
		UserDto dto = new UserDto();
		String address = request.getParameter("address");
		String brithday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		String rolename = request.getParameter("rolename");
		String tel = request.getParameter("tel");
		String userId = request.getParameter("userId");
		String userLocked = request.getParameter("userLocked");
		String username = request.getParameter("username");
		if (!CommonUntils.isempty(address)) {
			dto.setAddress(address);
		}
		if (!CommonUntils.isempty(brithday)) {
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
			Date date = null;
			try {
				date = format.parse(brithday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (date!=null) {
				dto.setBirthday(date);
			}			
		}
		if (!CommonUntils.isempty(email)) {
			dto.setEmail(email);
		}
		if (!CommonUntils.isempty(mobile)) {
			dto.setMobile(mobile);
		}
		if (!CommonUntils.isempty(password)) {
			dto.setPassword(password);
		}
		if (!CommonUntils.isempty(tel)) {
			dto.setTel(tel);
		}
		if (!CommonUntils.isempty(userId)) {
			dto.setUserId(userId);
		}
		if (!CommonUntils.isempty(userLocked)) {
			dto.setUserLocked(Integer.parseInt(userLocked));
		}
		if (!CommonUntils.isempty(username)) {
			dto.setUsername(username);
		}
		if ("0".equals(isNew)) {
			if (userService.updateUser(dto,rolename)) {
				map.addAttribute(Constraints.RESULT_SUCCESS, true);
			}
		}else {
			if (userService.insert(dto,rolename)) {
				map.addAttribute(Constraints.RESULT_SUCCESS, true);
			}
		}
				
		return map;
	}
	/**
	 * 由userid查询user
	 * @param userId
	 * @return
	 */
	@RequestMapping("/one")
	@ResponseBody
	public ModelMap getUser(@RequestParam(value="userId",required=true)String userId){
		ModelMap map = new ModelMap();
		UserDto user = new UserDto();
		user.setUserId(userId);
		UserDto userDto = userService.getUser(user);
		map.addAttribute(Constraints.RESULT_ROW, userDto);
		return map;
	}
	/**
	 * 删除用户及角色
	 * @param userId
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ModelMap deleteUser(HttpServletRequest request){
		String userIds = request.getParameter("userId");
		String[] users = userIds.split(",");
		ModelMap map = new ModelMap();
		for (int i = 0; i < users.length; i++) {
			UserDto dto = new UserDto();
			dto.setUserId(users[i]);
			userService.delete(dto);
			userService.deleteUserRole(users[i]);
		}		
		map.addAttribute(Constraints.RESULT_SUCCESS, true);
		return map;
	}
}
