package com.iscas.sdas.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sound.midi.VoiceStatus;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.datas.ratio.HealthDegree;
import com.iscas.sdas.dao.PerformanceWorkDtoMapper;
import com.iscas.sdas.dao.WorkDao;
import com.iscas.sdas.dao.work.CapacityWorkDao;
import com.iscas.sdas.dto.PerformanceWorkDto;
import com.iscas.sdas.dto.work.CapacityWorkDto;
import com.iscas.utils.DateUtils;
import com.iscas.validation.SparkValidator;

@Service
public class WorkService {
	
	@Autowired
	WorkDao performanceWorkDao;
	
	@Autowired
	PerformanceWorkDtoMapper performanceWorkMapper;
	
	@Autowired
	CapacityWorkDao capacityWorkDao;
	/**
	 * 插入性能工单
	 * @param performanceWorkDtos
	 */
	public void insertPerformanceWork(List<PerformanceWorkDto> performanceWorkDtos){
		for (PerformanceWorkDto performanceWorkDto : performanceWorkDtos) {
			if (performanceWorkDto.getAlarm_id()!=null) {
				performanceWorkMapper.insert(performanceWorkDto);
			}		
		}		
	}
	
	public void clearPerformanceWork(){
		performanceWorkMapper.delete();
	}
	
	public List<CapacityWorkDto> workValidate(){
		List<CapacityWorkDto> capacityWorkDtos = capacityWorkDao.getvalidatelist();
		List<CapacityWorkDto> result = new ArrayList<>();
		try {
			for (int i = 0; i < capacityWorkDtos.size(); i++) {
				CapacityWorkDto capacityWorkDto = capacityWorkDtos.get(i);
				if (capacityWorkDto.getMonitor_content().contains("新PRB利用率(4次连续)") || capacityWorkDto.getMonitor_content().contains("新切换出成功率(4次连续)")) {
					String cellcode = capacityWorkDto.getCellid();
					Timestamp timestamp = capacityWorkDto.getOccurrence_time();
					int year = timestamp.getYear()+1900;
					int month = timestamp.getMonth()+1;
					int day = timestamp.getDate();
					String str_month = month>10?month+"":"0"+month; 
					String str_day = day>10?day+"":"0"+day;
					String yyyyMM = year+str_month;//"201708";
					String yyyyMMdd = year+str_month+str_day;//"20170804";
					cellcode = utils.CharUtils.cellCode(cellcode);
					HealthDegree hd = new HealthDegree(cellcode, yyyyMM, yyyyMMdd);
					Map<String, HealthDegree.Degree> map = hd.getDegrees();
					if (map!=null) {
						Set<String> set =  map.keySet();
				    	Iterator<String> iterator =  set.iterator();
				    	int count = 0;
				    	while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (map.get(key).equals(HealthDegree.Degree.Fault)) {
								count++;
							}
						}
				    	if (count>1) {
							result.add(capacityWorkDto);
						}
				    	
					}
				}
			}
			updateState(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	private void updateState(List<CapacityWorkDto> works){
		for (CapacityWorkDto capacityWorkDto : works) {
			capacityWorkDao.update(capacityWorkDto);
		}
	}
	
	
	@Test
	public void test1(){
		String cellcode ="广州天河区石牌复建房南区1E-ZLW-1";
		String yyyyMM = "201708";
    	String yyyyMMdd = "20170804";
    	cellcode = utils.CharUtils.cellCode(cellcode);
    	HealthDegree hd = new HealthDegree(cellcode, yyyyMM, yyyyMMdd);
    	Map<String, HealthDegree.Degree> map = hd.getDegrees();
    	System.out.println(map);
	}
	@Test
	public void test2(){
		Date sheetTime = DateUtils.convert2Date("2017-08-03 13:00:00", "yyyy-MM-dd HH:mm:ss");
        String sheetTypeString = "新PRB利用率(4次连续)";
        String cellid = "广州五山科技街F-ZLH-1";
        double ss = SparkValidator.validate(sheetTime, sheetTypeString, cellid);
        System.out.println(ss);
	}
}
