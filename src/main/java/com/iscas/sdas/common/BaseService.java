package com.iscas.sdas.common;

import java.util.List;
/**
 * 通用Service
 * @author dongqun
 * 2017年10月23日下午4:49:12
 * @param <Dao>
 * @param <Dto>
 */
public abstract class BaseService<Dao extends BaseDao<Dto>,Dto extends BaseDto> {
	
	protected Dao dao;
			
	protected void init(Dao dao){
		this.dao = dao;
	}
	/**
	 * 获取分页数据
	 * @param dto
	 * @return
	 */
	public List<Dto> getpagelist(Dto dto){
		return dao.getPageList(dto);
	}
	/**
	 * 获取不分页获取
	 * @param dto
	 * @return
	 */
	public List<Dto> getalllist(Dto dto){
		return dao.getAllList(dto);
	}
}
