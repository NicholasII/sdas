package com.iscas.sdas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.ComplainDao;
import com.iscas.sdas.dto.CellComplainDto;
import com.iscas.sdas.dto.ComplainDto;

@Service
public class ComplainService {
	
	@Autowired
	ComplainDao complainDao;
	
	
	
	public List<ComplainDto> getlist(){
		return complainDao.getlist();
	}
	
	public List<CellComplainDto> getcelllist(String cellname){
		return complainDao.getcelllist(cellname);
	}
	
	public List<CellComplainDto> getalllist(){
		return complainDao.getalllist();
	}
	
	public List<CellComplainDto> getfocuslist(String cellname){
		return complainDao.getfocuslist(cellname);
	}
	
	
}
