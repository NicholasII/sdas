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
		List<AlarmDto> alarmDtos = alarmDao.currentDayAlarm();
		for (int i=0;i<alarmDtos.size();i++) {
			AlarmDto dto = alarmDtos.get(i);
			String time = dto.getYyyyMMdd() +" " + dto.getApp_hour()+"时";
			dto.setYyyyMMdd(time);
			if (alarmDtos.get(i).getCount() ==0) {
				alarmDtos.remove(i);
			}
		}
		return alarmDtos;
	}
	
	public List<AlarmDto> allAlarm(AlarmDto alarmDto){
		return alarmDao.allAlarm(alarmDto);
	}
}
