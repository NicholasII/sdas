package com.iscas.sdas.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.datas.ratio.HealthDegree;
import com.iscas.sdas.dao.WorkDao;
import com.iscas.sdas.dao.work.AllCapacityWorkDao;
import com.iscas.sdas.dao.work.CapacityWorkDao;
import com.iscas.sdas.dto.work.AllCapacityWorkDto;
import com.iscas.sdas.dto.work.CapacityWorkDto;


@Service
public class WorkService {
	
	@Autowired
	WorkDao performanceWorkDao;
	
	@Autowired
	AllCapacityWorkDao performanceWorkMapper;
	
	@Autowired
	CapacityWorkDao capacityWorkDao;
	/**
	 * 插入性能工单
	 * @param performanceWorkDtos
	 */
	public void insertPerformanceWork(List<AllCapacityWorkDto> performanceWorkDtos){
		for (AllCapacityWorkDto performanceWorkDto : performanceWorkDtos) {
			if (performanceWorkDto.getAlarm_id()!=null) {
				performanceWorkMapper.insert(performanceWorkDto);
			}		
		}		
	}
	
	public void clearPerformanceWork(){
		performanceWorkMapper.delete();
	}
	
	/**
	 * 所有可疑工单
	 * @return
	 */
	public List<CapacityWorkDto> allvalidatelist(){
		return capacityWorkDao.getvalidatelist();
	}
	/**
	 * 最近一天的可疑工单
	 * @return
	 */
	public List<CapacityWorkDto> validatelisttheday(){
		return capacityWorkDao.getListTheDay();
	}
	/**
	 * 
	 * @param capacityWorkDtos
	 * @param flag 0为全部；1为最近一天的可疑工单；2为全部的可疑工单
	 * @return
	 */
	public List<CapacityWorkDto> workValidate(List<CapacityWorkDto> capacityWorkDtos,int flag){ 
		List<CapacityWorkDto> result = new ArrayList<>();
		List<CapacityWorkDto> faultlist = new ArrayList<>();//
		List<CapacityWorkDto> normallist = new ArrayList<>();//可疑工单
		List<CapacityWorkDto> others = new ArrayList<>();
		long start = System.currentTimeMillis();
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
				    	int faults = 0,normals = 0;
				    	while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (map.get(key).equals(HealthDegree.Degree.Fault)) {
								faults++;
							}else if (map.get(key).equals(HealthDegree.Degree.Normal)) {
								normals++;
							}
						}
				    	if (faults>=1) {
				    		capacityWorkDto.setQuestionflag(1);//绿
							faultlist.add(capacityWorkDto);
						}else if (normals>=16) {
							capacityWorkDto.setQuestionflag(2);//红
							normallist.add(capacityWorkDto);
						}else {
							capacityWorkDto.setQuestionflag(3);//黄
							others.add(capacityWorkDto);
						}
				    	result.add(capacityWorkDto);
					}
				}
			}
			long end = System.currentTimeMillis();
			System.out.print("计算时长");
			System.out.print(end-start);
			System.out.println("秒");
			System.out.println("总数："+result.size()+"，确定："+faultlist.size()+"，可疑："+normallist.size()+"，其他:"+others.size());
			updateState(faultlist);
			updateDoubtWork(normallist);
			updateAlarmWork(others);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag==0){
			return result;
		}else {
			return normallist;
		}
		
	}
	/**
	 * 工单验证，即从t_performance_work_rt表中读取questionfalg为0,1,2的值
	 * questionflag = 0 or questionflag = 1 or questionflag = 2
	 * @return
	 */
	public List<CapacityWorkDto> workValidate2(){ 
		CapacityWorkDto capacityWorkDto = new CapacityWorkDto();
		return capacityWorkDao.getAllWorks(capacityWorkDto);
	}
	
	
	
	
	private void updateState(List<CapacityWorkDto> works){
		for (CapacityWorkDto capacityWorkDto : works) {
			capacityWorkDao.update(capacityWorkDto);
		}
	}
	private void updateDoubtWork(List<CapacityWorkDto> works){
		for (CapacityWorkDto capacityWorkDto : works) {
			capacityWorkDao.updatedoubtwork(capacityWorkDto);
		}
	}
	private void updateAlarmWork(List<CapacityWorkDto> works){
		for (CapacityWorkDto capacityWorkDto : works) {
			capacityWorkDao.updatealarmwork(capacityWorkDto);
		}
	}
	/*
	@Test
	public void test1(){
		String cellcode ="广州天河区石牌复建房南区1E-ZLW-1";
		String yyyyMM = "201708";
    	String yyyyMMdd = "20170804";
    	cellcode = utils.CharUtils.cellCode(cellcode);
    	HealthDegree hd = new HealthDegree(cellcode, yyyyMM, yyyyMMdd);
    	Map<String, HealthDegree.Degree> map = hd.getDegrees();
    	System.out.println(map);
	}//*/
	/*@Test
	public void test2(){
		Date sheetTime = DateUtils.convert2Date("2017-08-03 13:00:00", "yyyy-MM-dd HH:mm:ss");
        String sheetTypeString = "新PRB利用率(4次连续)";
        String cellid = "广州五山科技街F-ZLH-1";
        double ss = SparkValidator.validate(sheetTime, sheetTypeString, cellid);
        System.out.println(ss);
	}*/
	
	public List<CapacityWorkDto> getAllDoubtWorks(){
		return capacityWorkDao.getAllDoubtWorks();
	}
	public List<CapacityWorkDto> getAllWorks(CapacityWorkDto capacityWorkDto){
		return capacityWorkDao.getAllWorks(capacityWorkDto);
	}
}
