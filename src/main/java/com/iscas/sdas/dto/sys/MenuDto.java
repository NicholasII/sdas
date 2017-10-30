package com.iscas.sdas.dto.sys;

import java.util.List;

public class MenuDto {
	
	    // 菜单id
		private Integer MENU_ID;
		// 父菜单id
		private Integer PARENT_MENU_ID;
		// 菜单url
		private String MENU_URL;
		// 菜单名称
		private String MENU_NAME;
		// 父菜单名称
		private String PARENT_MENU_NAME;
		// 菜单顺序
		private Integer ORDER_NUM;
		// 注释
		private String MENU_NOTICE;	
		//子菜单
		private List<MenuDto> menus;
		
		
		
		public Integer getMENU_ID() {
			return MENU_ID;
		}
		public void setMENU_ID(Integer mENU_ID) {
			MENU_ID = mENU_ID;
		}
		public Integer getPARENT_MENU_ID() {
			return PARENT_MENU_ID;
		}
		public void setPARENT_MENU_ID(Integer pARENT_MENU_ID) {
			PARENT_MENU_ID = pARENT_MENU_ID;
		}
		public String getMENU_URL() {
			return MENU_URL;
		}
		public void setMENU_URL(String mENU_URL) {
			MENU_URL = mENU_URL;
		}
		public String getMENU_NAME() {
			return MENU_NAME;
		}
		public void setMENU_NAME(String mENU_NAME) {
			MENU_NAME = mENU_NAME;
		}
		public Integer getORDER_NUM() {
			return ORDER_NUM;
		}
		public void setORDER_NUM(Integer oRDER_NUM) {
			ORDER_NUM = oRDER_NUM;
		}
		public String getMENU_NOTICE() {
			return MENU_NOTICE;
		}
		public void setMENU_NOTICE(String mENU_NOTICE) {
			MENU_NOTICE = mENU_NOTICE;
		}
		public List<MenuDto> getMenus() {
			return menus;
		}
		public void setMenus(List<MenuDto> menus) {
			this.menus = menus;
		}
		public String getPARENT_MENU_NAME() {
			return PARENT_MENU_NAME;
		}
		public void setPARENT_MENU_NAME(String pARENT_MENU_NAME) {
			PARENT_MENU_NAME = pARENT_MENU_NAME;
		}	
}	