package com.iscas.sdas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.AlarmDao;
import com.iscas.sdas.dto.AlarmDto;

@Service
public class AlarmService {

	@Autowired
	AlarmDao alarmDao;
	
	public List<AlarmDto> currentDayAlarm(){
		return alarmDao.currentDayAlarm();
	}
}
