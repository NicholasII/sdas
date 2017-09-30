package com.iscas.sdas.dao.sys;

import java.util.List;

import com.iscas.sdas.dto.sys.MenuDto;

public interface MenuDao {

	/**
	 * 获取一级菜单
	 * @return
	 */
	public List<MenuDto> getFristMenu();
	/**
	 * 获取二级菜单
	 * @param menuDto
	 * @return
	 */
	public List<MenuDto> getSecondMenu(Integer pid);
	
	
	public List<MenuDto> getAllMenu();
}
