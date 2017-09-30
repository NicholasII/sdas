package com.iscas.sdas.dao;

import java.util.List;

import com.iscas.sdas.dto.WorkDto;

public interface WorkDao {
	
	List<WorkDto> getCountRecentOneWeek();
	
}
