package com.iscas.sdas.service.sys;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.common.BaseService;
import com.iscas.sdas.dao.sys.RoleDao;
import com.iscas.sdas.dao.sys.UserDao;
import com.iscas.sdas.dto.sys.RoleDto;
import com.iscas.sdas.dto.sys.UserDto;
@Service
public class UserService extends BaseService<UserDao, UserDto>{
	
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	/**
	 * 新增用户，并创建角色
	 * @param dto
	 * @param rolename
	 * @return
	 */
	public boolean insert(UserDto dto,String rolename) {
		try {
			userDao.insert(dto);
			if (createUserRole(dto.getUserId(), rolename)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	/**
	 * 创建角色
	 * @param userid
	 * @param rolename
	 * @return
	 */
	private boolean createUserRole(String userid,String rolename){
		try {
			RoleDto dto = new RoleDto();
			dto.setAddtime(new Date());
			dto.setRolename(rolename);
			dto.setUserid(userid);
			roleDao.insert(dto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 获取用户
	 * @param userid
	 * @return
	 */
	public UserDto getUser(UserDto user){
		return userDao.getUser(user);
	}
	/**
	 * 更新用户信息
	 * @param dto
	 * @param rolename
	 * @return
	 */
	public boolean updateUser(UserDto dto,String rolename){
		try {
			userDao.update(dto);
			if (updateUserRole(dto.getUserId(), rolename)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	/**
	 * 更新用户的角色信息
	 * @param userid
	 * @param rolename
	 * @return
	 */
	private boolean updateUserRole(String userid,String rolename){
		try {
			RoleDto dto = new RoleDto();
			dto.setAddtime(new Date());
			dto.setRolename(rolename);
			dto.setUserid(userid);
			roleDao.update(dto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 删除用户的角色信息
	 * @param userid
	 * @param rolename
	 * @return
	 */
	public boolean deleteUserRole(String userid){
		try {
			RoleDto dto = new RoleDto();
			dto.setAddtime(new Date());
			dto.setUserid(userid);
			roleDao.delete(dto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
