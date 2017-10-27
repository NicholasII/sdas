package com.iscas.sdas.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.common.PageDto;
import com.iscas.sdas.dao.ComplainDao;
import com.iscas.sdas.dto.CellComplainDto;
import com.iscas.sdas.dto.ComplainDto;
import com.iscas.sdas.util.CommonUntils;

@Service
public class ComplainService {
	
	@Autowired
	ComplainDao complainDao;
	
	private static final Logger loggger = LoggerFactory.getLogger(ComplainService.class);  
	/**
	 * 集中投诉
	 * @return
	 */
	public List<ComplainDto> getlist(){
		return complainDao.getlist();
	}
	/**
	 * 小区投诉
	 * @param cellname
	 * @return
	 */
	public List<CellComplainDto> getcelllist(String cellname){
		return encryption(complainDao.getcelllist(cellname));
	}
	/**
	 * 所有投诉
	 * @return
	 */
	public List<CellComplainDto> getalllist(){		
		return encryption(complainDao.getPageList());
	}
	
	
	public List<CellComplainDto> getfocuslist(String cellname){
		return encryption(complainDao.getfocuslist(cellname));
	}
	/**
	 * 将投诉工单中的电话号码加密
	 * @param list
	 * @return
	 */
	private List<CellComplainDto> encryption(List<CellComplainDto> list){
		if (list!=null) {
			for (CellComplainDto cellComplainDto : list) {
				String phone = cellComplainDto.getPhone_number();
				if (!CommonUntils.isempty(phone)) {
					phone = CommonUntils.idCardReplaceWithStar(phone);
					cellComplainDto.setPhone_number(phone);
				}
			}
		}
		return list;
	}
	
	public List<CellComplainDto> getcomplist(ComplainDto ComplainDto){
		return encryption(complainDao.getcomplist(ComplainDto));
	}
	
}
