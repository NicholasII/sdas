package com.iscas.sdas.dao;

import java.util.List;

import com.iscas.sdas.common.BaseDao;
import com.iscas.sdas.dto.IndexAlarmDto;

public interface IndexAlarmDao extends BaseDao<IndexAlarmDto>{
	/**
	 * 获取最近一天指标预警
	 * @return
	 */
	List<IndexAlarmDto> getPageListCurrentDay();
	/**
	 * 获取所有天指标预警
	 * @return
	 */
	List<IndexAlarmDto> allDayIndexAlarm(IndexAlarmDto alarmDto);
	
	
}
