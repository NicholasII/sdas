package com.iscas.sdas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.common.BaseService;
import com.iscas.sdas.dao.IndexAlarmDao;
import com.iscas.sdas.dto.IndexAlarmDto;

@Service
public class IndexAlarmService extends BaseService<IndexAlarmDao, IndexAlarmDto> {
	
	@Autowired
	IndexAlarmDao indexAlarmDao;

	@Override
	public void init(IndexAlarmDao dao) {
		super.init(indexAlarmDao);
	}
	
	/**
	 * 最近一天预警
	 * @return
	 */
	public List<IndexAlarmDto> currentDayAlarm(){
		List<IndexAlarmDto> alarmDtos = indexAlarmDao.getPageListCurrentDay();
		for (int i=0;i<alarmDtos.size();i++) {
			if (alarmDtos.get(i).getCount() ==0) {
				alarmDtos.remove(i);
			}
		}
		return alarmDtos;
	}

	
	

}
