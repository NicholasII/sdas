package com.iscas.sdas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.common.BaseService;
import com.iscas.sdas.common.PageDto;
import com.iscas.sdas.dao.IndexAlarmDao;
import com.iscas.sdas.dto.IndexAlarmDto;

@Service
public class IndexAlarmService extends BaseService<IndexAlarmDao, IndexAlarmDto> {
	
	@Autowired
	IndexAlarmDao indexAlarmDao;
	
	/**
	 * 最近一天预警
	 * @return
	 */
	public List<IndexAlarmDto> currentDayAlarm(){
		List<IndexAlarmDto> alarmDtos = indexAlarmDao.getPageListCurrentDay();
		for (int i=0;i<alarmDtos.size();i++) {
			if (alarmDtos.get(i).getApp_type()==0) {
				alarmDtos.get(i).setType("新PRB利用率(4次连续)");
			}else if (alarmDtos.get(i).getApp_type()==1) {
				alarmDtos.get(i).setType("新切换出成功率(4次连续)");
			}
			if (alarmDtos.get(i).getCount() ==0) {
				alarmDtos.remove(i);
			}
		}
		return alarmDtos;
	}
	/**
	 * 每天预警
	 */
	@Override
	public PageDto<IndexAlarmDto> getPageList(IndexAlarmDto dto, String num, String size) {
		PageDto<IndexAlarmDto> pageDto =  super.getPageList(dto, num, size);
		List<IndexAlarmDto> alarmDtos = pageDto.getRows();
		for (int i=0;i<alarmDtos.size();i++) {
			if (alarmDtos.get(i).getApp_type()==0) {
				alarmDtos.get(i).setType("新PRB利用率(4次连续)");
			}else if (alarmDtos.get(i).getApp_type()==1) {
				alarmDtos.get(i).setType("新切换出成功率(4次连续)");
			}
			if (alarmDtos.get(i).getCount() ==0) {
				alarmDtos.remove(i);
			}
		}
		return pageDto;
	}
	/**
	 * 指标预警页面小区预警列表
	 */
	public List<IndexAlarmDto> getLastDay(IndexAlarmDto dto) {
		 List<IndexAlarmDto> alarmDtos = indexAlarmDao.getLastDay(dto);
		 for (int i=0;i<alarmDtos.size();i++) {
				if (alarmDtos.get(i).getApp_type()==0) {
					alarmDtos.get(i).setType("新PRB利用率(4次连续)");
				}else if (alarmDtos.get(i).getApp_type()==1) {
					alarmDtos.get(i).setType("新切换出成功率(4次连续)");
				}
			}
		return alarmDtos;
	}
}
