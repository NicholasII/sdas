package com.iscas.sdas.dao;

import java.util.List;

import com.iscas.sdas.dto.AlarmDto;

public interface AlarmDao {

	List<AlarmDto> currentDayAlarm();
	
	List<AlarmDto> allDayAlarm(AlarmDto alarmDto);
}
