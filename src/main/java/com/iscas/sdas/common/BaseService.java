package com.iscas.sdas.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 通用Service
 * @author dongqun
 * 2017年10月23日下午4:49:12
 * @param <Dao>
 * @param <Dto>
 */
public abstract class BaseService<Dao extends BaseDao<Dto>,Dto extends BaseDto> {
	
	@Autowired
	protected Dao dao;

	/**
	 * 获取分页数据
	 * @param dto
	 * @return
	 */
	public PageDto<Dto> getPageList(Dto dto,String num,String size){
		int pageNum = Integer.parseInt(num);
		int pageSize = Integer.parseInt(size);
		PageHelper.startPage(pageNum, pageSize);
		List<Dto> dtos = dao.getPageList(dto);
		PageInfo<Dto> pageInfo = new PageInfo<>(dtos);
		List<Dto> rows = new ArrayList<>();
		for (int i = 0; i < dtos.size(); i++) {
			Dto dto1 = dtos.get(i);
			rows.add(dto1);
		}
		PageDto<Dto> pageDto = new PageDto<>();
		pageDto.setTotal(pageInfo.getTotal());
		pageDto.setRows(rows);
		return pageDto;
	}
	/**
	 * 获取不分页获取
	 * @param dto
	 * @return
	 */
	public List<Dto> getalllist(Dto dto){
		return dao.getPageList(dto);
	}
	/**
	 * 插入多项数据
	 * @param dtos
	 */
	public boolean insert(List<Dto> dtos){
		try {
			for (Dto dto : dtos) {
				dao.insert(dto);
			}
		} catch (Exception e) {		
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 更新数据
	 * @param dto
	 * @return
	 */
	public boolean update(Dto dto){
		try {
				dao.update(dto);
		} catch (Exception e) {		
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 删除数据
	 * @param dto
	 * @return
	 */
	public boolean delete(Dto dto){
		try {
			dao.delete(dto);
		} catch (Exception e) {		
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
