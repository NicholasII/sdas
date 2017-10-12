package com.iscas.sdas.service.cell;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.holders.StringHolder;

import org.codehaus.jackson.map.deser.ValueInstantiators.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iscas.sdas.dao.cell.CellDao;
import com.iscas.sdas.dto.DeviceWorkDto;
import com.iscas.sdas.dto.GroupIndexMeatdata;
import com.iscas.sdas.dto.OSWorkDto;
import com.iscas.sdas.dto.PerformanceWorkDto;
import com.iscas.sdas.dto.TotalHealthInfoDto;
import com.iscas.sdas.dto.cell.BaseCellHealth;
import com.iscas.sdas.dto.cell.BaseGroupIndex;
import com.iscas.sdas.dto.cell.CellDto;

@Service
public class CellService {

	@Autowired
	CellDao cellDao;
	
	public List<CellDto> getCellList(CellDto cellDto){
		return cellDao.getcells(cellDto);
	}
	
	public List<String> getCellGroup(){
		return cellDao.getgroups();
	}
	
	public List<GroupIndexMeatdata> getGroupIndexs(String grouptype){
		return cellDao.getgroupindexs(grouptype);
	}
	
	public String getgroup(String cellname){
		return cellDao.getgroup(cellname);
	}
	
	public List<TotalHealthInfoDto> generateCellHealthTrend(String cellname){
		List<TotalHealthInfoDto> list = new ArrayList<>();
		try {
			List<BaseCellHealth> cellHealths = cellDao.cellhealthtrend(cellname);		
			if (cellHealths!=null) {
				List<String> perWorkCount = permanceWorkWithinCurrenttime(cellname); 
				List<String> deviceWorkCount = deviceWorkWithinCurrenttime(cellname);
				List<String> osWorkCount = osWorkWithinCurrenttime(cellname);
				for (int i=0;i<cellHealths.size(); i++) {
					BaseCellHealth cellHealth = cellHealths.get(i);
					Method[] methods = cellHealth.getClass().getMethods();
					for (Method method : methods) {
						if (method.getName().startsWith("getRange")) {	
							TotalHealthInfoDto infoDto  = new TotalHealthInfoDto();
							String range = (String)method.invoke(cellHealth, null);						
							int  moment = Integer.parseInt(method.getName().substring(method.getName().lastIndexOf("_")+1));
							Double ratio = parseRatio(range);
							String time = cellHealth.getYyyyMMdd()+" "+moment+"时";
							infoDto.setTime(time);
							infoDto.setRatio(ratio);
							infoDto.setDeviceworks(0);
							infoDto.setOsworks(0);
							infoDto.setPerworks(0);
							/*map[0] = moment;
							map[1] = ratio;
							map[2] = 0;//性能单数量
							map[3] = 0;//设备
							map[4] = 0;//退服*/	
							if (perWorkCount!=null) {
								for (int j = 0; j < perWorkCount.size(); j++) {
									
									if (perWorkCount.get(j).equals(infoDto.getTime())) {
										System.out.println("---性能--"+perWorkCount.get(j)+"--------"+infoDto.getTime());
										int perworks = infoDto.getPerworks()+1;
										infoDto.setPerworks(perworks);
									}
								}
							}
							if (deviceWorkCount!=null) {
								for (int j = 0; j < deviceWorkCount.size(); j++) {
									
									if (deviceWorkCount.get(j).equals(infoDto.getTime())) {
										System.out.println("--设备单---"+deviceWorkCount.get(j)+"--------"+infoDto.getTime());
										int devworks = infoDto.getPerworks()+1;
										infoDto.setPerworks(devworks);
									}
								}
							}
							if (osWorkCount!=null) {
								for (int j = 0; j < osWorkCount.size(); j++) {
									
									if (osWorkCount.get(j).equals(infoDto.getTime())) {
										System.out.println("--退服务---"+osWorkCount.get(j)+"--------"+infoDto.getTime());
										int osworks = infoDto.getPerworks()+1;
										infoDto.setPerworks(osworks);
									}
								}
							}
							list.add(infoDto);
						}		
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 当前时间性能单数量
	 * @param cellname
	 * @return
	 */
	private List<String> permanceWorkWithinCurrenttime(String cellname){
		List<String> list = new ArrayList<>();
		List<PerformanceWorkDto> works =  cellDao.performWorkWithinCurrTime(cellname);
		for (PerformanceWorkDto work : works) {
			int year = work.getOccurrence_time().getYear()+1900;
			int month = work.getOccurrence_time().getMonth()+1;
			String monthstr = month>=10?""+month:"0"+month;
			int day = work.getOccurrence_time().getDate();
			String daystr = day>=10?""+day:"0"+day;
			int hour = work.getOccurrence_time().getHours();
			String occurtime = year +"-"+ monthstr+"-"+daystr+" "+hour+"时";
			list.add(occurtime);
		}
		return list;
	}
	/**
	 * 当前时间设备工单数量
	 * @param cellname
	 * @return
	 */
	private List<String> deviceWorkWithinCurrenttime(String cellname){
		List<String> list = new ArrayList<>();
		List<DeviceWorkDto> works =  cellDao.deviceWorkWithinCurrTime(cellname);
		for (DeviceWorkDto work : works) {
			int year = work.getFaultOccusTime().getYear()+1900;
			int month = work.getFaultOccusTime().getMonth()+1;
			String monthstr = month>=10?""+month:"0"+month;
			int day = work.getFaultOccusTime().getDate();
			String daystr = day>=10?""+day:"0"+day;
			int hour = work.getFaultOccusTime().getHours();
			String occurtime = year +"-"+ monthstr+"-"+daystr+" "+hour+"时";
			list.add(occurtime);
		}
		return list;
	}
	
	/**
	 * 当前时间退服工单数量
	 * @param cellname
	 * @return
	 */
	private List<String> osWorkWithinCurrenttime(String cellname){
		List<String> list = new ArrayList<>();
		List<OSWorkDto> works =  cellDao.osWorkWithinCurrTime(cellname);
		for (OSWorkDto work : works) {
			int year = work.getStartTime().getYear()+1900;
			int month = work.getStartTime().getMonth()+1;
			String monthstr = month>=10?""+month:"0"+month;
			int day = work.getStartTime().getDate();
			String daystr = day>=10?""+day:"0"+day;
			int hour = work.getStartTime().getHours();
			String occurtime = year +"-"+ monthstr+"-"+daystr+" "+hour+"时";
			list.add(occurtime);
		}
		return list;
	}
	
	
	
	private double parseRatio(String range){
		double ratio = 0;
		if (range!=null) {
			int count = 1;
			JSONArray array = JSON.parseArray(range);
			if (array!=null) {
				for (int i = 0; i < array.size(); i++) {
					JSONObject obj = array.getJSONObject(i);
					if ("Ratio".equals(obj.getString("Key"))) {
					    ratio = Double.parseDouble(obj.get("Value").toString())*100;
						return ratio;
					}
					count++;
				}
				if (count==array.size()) {
					ratio = 0;
					return ratio;
				}
			}
		}
		return ratio;
	}
}
