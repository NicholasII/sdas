package com.iscas.sdas.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.sys.MenuDao;
import com.iscas.sdas.dto.sys.MenuDto;

@Service
public class MenuService {

	@Autowired
	MenuDao menuDao;
	
	public List<MenuDto> getFirstMenus(){
		return menuDao.getFristMenu();
	}
	
	public List<MenuDto> getSecondMenu(Integer pid) {
		return menuDao.getSecondMenu(pid);
	}
	
	public List<MenuDto> getAllMenu(){
		return menuDao.getAllMenu();
	}
}
