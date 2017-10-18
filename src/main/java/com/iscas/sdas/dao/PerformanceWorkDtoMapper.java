package com.iscas.sdas.dao;

import com.iscas.sdas.dto.PerformanceWorkDto;

public interface PerformanceWorkDtoMapper {
	
	int delete();
	
    int deleteByPrimaryKey(Integer alarmId);

    int insert(PerformanceWorkDto record);

    int insertSelective(PerformanceWorkDto record);

    PerformanceWorkDto selectByPrimaryKey(Integer alarmId);

    int updateByPrimaryKeySelective(PerformanceWorkDto record);

    int updateByPrimaryKey(PerformanceWorkDto record);
}