package com.iscas.sdas.dao;

import java.util.List;

import com.iscas.sdas.dto.CellComplainDto;
import com.iscas.sdas.dto.ComplainDto;

public interface ComplainDao {
	
	List<ComplainDto> getlist();
	
	List<CellComplainDto> getcelllist(String cellname);
	
	List<CellComplainDto> getalllist();
	
	List<CellComplainDto> getfocuslist(String cellname);

}
