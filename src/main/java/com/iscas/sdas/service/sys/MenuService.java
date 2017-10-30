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
		List<MenuDto> result = menuDao.getAllMenu();
		for (MenuDto menuDto : result) {
			if (menuDto.getPARENT_MENU_ID()!=0) {
				MenuDto dto = menuDao.getMenu(menuDto.getPARENT_MENU_ID());  
				if (dto!=null) {
					menuDto.setPARENT_MENU_NAME(dto.getMENU_NAME());
				}else {
					menuDto.setPARENT_MENU_NAME("无父菜单");
				}
			}else {
				menuDto.setPARENT_MENU_NAME("顶级菜单");
			}
		}
		return result;
	}
}
