package com.iscas.sdas.service.cell;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iscas.sdas.dao.StationInfoDtoMapper;
import com.iscas.sdas.dao.cell.CellDao;
import com.iscas.sdas.dto.BaseStationHealthRatio;
import com.iscas.sdas.dto.CellComplainDto;
import com.iscas.sdas.dto.DeviceWorkDto;
import com.iscas.sdas.dto.GroupIndexMeatdata;
import com.iscas.sdas.dto.OSWorkDto;
import com.iscas.sdas.dto.PerformanceWorkDto;
import com.iscas.sdas.dto.StationInfoDto;
import com.iscas.sdas.dto.TotalHealthInfoDto;
import com.iscas.sdas.dto.cell.BaseCellHealth;
import com.iscas.sdas.dto.cell.CellDto;
import com.iscas.sdas.util.CommonUntils;
import com.mysql.jdbc.log.Log;

@Service
public class CellService {

	Logger logger = Logger.getLogger(CellService.class);
	@Autowired
	CellDao cellDao;
	@Autowired
	StationInfoDtoMapper stationDto;
	
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
	/**
	 * 一周历史曲线
	 * @param cellname
	 * @return
	 */
	public List<TotalHealthInfoDto> generateCellHealthTrend(String cellname,String type,String start,String end){
		List<TotalHealthInfoDto> list = new ArrayList<>();
		try {
			List<BaseCellHealth> cellHealths;		
			if ("week".equals(type)) {
				cellHealths = cellDao.cellhealthtrend(cellname);
			}else if ("month".equals(type)) {
				cellHealths = cellDao.cellhealthtrendWithinOneMonth(cellname);
			}else {
				cellHealths = cellDao.cellhealthtrendWithinSelect(cellname, start, end);
			}
			if (cellHealths!=null && cellHealths.size()>0) {
				//List<String> perWorkCount = permanceWorkWithinCurrenttime(cellname); 
				//List<String> deviceWorkCount = deviceWorkWithinCurrenttime(cellname);
				//List<String> osWorkCount = osWorkWithinCurrenttime(cellname);
				List<String> complaints = complaintsWithinCurrenttime(cellname,type,start,end);
			
				for (int i=0;i<cellHealths.size(); i++) {
					BaseCellHealth cellHealth = cellHealths.get(i);
					Method[] methods = cellHealth.getClass().getMethods();
					for (Method method : methods) {
						if (method.getName().startsWith("getRange")) {	
							TotalHealthInfoDto infoDto  = new TotalHealthInfoDto();
							String range = (String)method.invoke(cellHealth, null);						
							int  moment = Integer.parseInt(method.getName().substring(method.getName().lastIndexOf("_")+1));
							Double ratio = parseRatio(range);
							String year  = cellHealth.getYyyyMMdd().substring(0, 4);
							String month  = cellHealth.getYyyyMMdd().substring(4, 6);
							String day  = cellHealth.getYyyyMMdd().substring(6);
							String time = year+"-"+month+"-"+day+" "+moment+"时";
							infoDto.setTime(time);
							infoDto.setRatio(ratio);
							infoDto.setDeviceworks(0);
							infoDto.setOsworks(0);
							infoDto.setPerworks(0);
							infoDto.setComplaints(0);
							/*if (perWorkCount!=null) {
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
							}*/
							if (complaints!=null) {
								for (int j = 0; j < complaints.size(); j++) {
									
									if (complaints.get(j).equals(infoDto.getTime())) {
										
										int complaint = infoDto.getComplaints()+1;
										infoDto.setComplaints(complaint);
										//System.out.println("--投诉---"+complaints.get(j)+"--------"+infoDto.getTime()+"总共"+complaint+"个");
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
	 * 小区实时健康度
	 * @param cellname
	 * @param type
	 * @param start
	 * @param end
	 * @return
	 */
	public List<TotalHealthInfoDto> generateCellRTHealth(String cellname){
		List<TotalHealthInfoDto> list = new ArrayList<>();
		try {
			BaseCellHealth cellHealth = cellDao.cellcurrenthealth(cellname);

			if (cellHealth!=null) {
				//List<String> perWorkCount = permanceWorkWithinCurrenttime(cellname); 
				//List<String> deviceWorkCount = deviceWorkWithinCurrenttime(cellname);
				//List<String> osWorkCount = osWorkWithinCurrenttime(cellname);
				//List<String> complaints = complaintsWithinCurrenttime(cellname,type,start,end);
			
					Method[] methods = cellHealth.getClass().getMethods();
					for (Method method : methods) {
						if (method.getName().startsWith("getRange")) {	
							TotalHealthInfoDto infoDto  = new TotalHealthInfoDto();
							String range = (String)method.invoke(cellHealth, null);						
							int  moment = Integer.parseInt(method.getName().substring(method.getName().lastIndexOf("_")+1));
							Double ratio = parseRatio(range);
							String year  = cellHealth.getYyyyMMdd().substring(0, 4);
							String month  = cellHealth.getYyyyMMdd().substring(4, 6);
							String day  = cellHealth.getYyyyMMdd().substring(6);
							String time = year+"-"+month+"-"+day+" "+moment+"时";
							infoDto.setTime(time);
							infoDto.setRatio(ratio);
							infoDto.setDeviceworks(0);
							infoDto.setOsworks(0);
							infoDto.setPerworks(0);
							infoDto.setComplaints(0);
							/*if (perWorkCount!=null) {
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
							}*/
							/*if (complaints!=null) {
								for (int j = 0; j < complaints.size(); j++) {
									
									if (complaints.get(j).equals(infoDto.getTime())) {
										
										int complaint = infoDto.getComplaints()+1;
										infoDto.setComplaints(complaint);
										//System.out.println("--投诉---"+complaints.get(j)+"--------"+infoDto.getTime()+"总共"+complaint+"个");
									}
								}
							}*/
							list.add(infoDto);
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
	
	/**
	 * 投诉工单数量
	 * @param cellname
	 * @return
	 */
	private List<String> complaintsWithinCurrenttime(String cellname,String type,String starttime,String endtime){
		List<String> list = new ArrayList<>();
		List<CellComplainDto> works;
		if ("week".equals(type)) {
			works =  cellDao.complaintWithinCurrTime(cellname);
		}else if ("month".equals(type)) {
			works =  cellDao.complaintWithinOneMonth(cellname);
		}else {
			works =  cellDao.complaintWithinSelect(cellname, starttime, endtime);
		}
		if (works!=null) {
			for (CellComplainDto work : works) {
				int year = work.getRecord_time().getYear()+1900;
				int month = work.getRecord_time().getMonth()+1;
				String monthstr = month>=10?""+month:"0"+month;
				int day = work.getRecord_time().getDate();
				String daystr = day>=10?""+day:"0"+day;
				int hour = work.getRecord_time().getHours();
				String occurtime = year +"-"+ monthstr+"-"+daystr+" "+hour+"时";
				list.add(occurtime);
			}
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
	
	
	/**
	 * 所有小区的最近健康度
	 * @return
	 */
	private List<BaseCellHealth> allcellhealth(){
		List<BaseCellHealth> list = new ArrayList<>();
		List<String> cells = cellDao.allcells();
		for (String cellname : cells) {
			BaseCellHealth cellHealth = cellDao.currenthealthratio(cellname);
			if (cellHealth!=null) {
				list.add(cellHealth);
			}
		}
		return list;
	}
	/**
	 * 当前所有小区基站的最小健康度集合
	 * @return
	 * @throws Exception
	 */
	public List<BaseStationHealthRatio> currentHealthGroup() throws Exception{
		int hour = Calendar.HOUR_OF_DAY;
		String str_hour = hour>10?hour+"":"0"+hour;		
		List<BaseStationHealthRatio> result = new ArrayList<>();
		List<String> stations = cellDao.allstations();//所有基站
		List<BaseCellHealth> baseCellHealths = allcellhealth();//所有小区的健康度	
		for (String station : stations) {
			double max=0;
			List<String> cells = cellDao.allcellsinstation(station);//属于这个基站的所有小区
			StationInfoDto stationInfoDto = stationDto.selectByStationName(station);
			for (int i = 0; i < cells.size(); i++) {
				for (int j = 0; j < baseCellHealths.size(); j++) {
					if (cells.get(i).equals(baseCellHealths.get(j).getCell_code())) {
						String range = (String)baseCellHealths.get(j).getClass().getMethod("getRange_"+str_hour, null).invoke(baseCellHealths.get(j), null);
						//String range = baseCellHealths.get(j).getRange_00();
						JSONArray array = JSON.parseArray(range);
						for (int k = 0; k < array.size(); k++) {
							if ("Ratio".equals(array.getJSONObject(k).getString("Key"))) {
								if (max<array.getJSONObject(k).getDoubleValue("Value")) {
									max = array.getJSONObject(k).getDoubleValue("Value");
									
								}
								break;
							}
						}
						break;
					}
				}
			}
			BaseStationHealthRatio baseStationHealthRatio = new BaseStationHealthRatio();
			if (max==0) {
				max = 100;
			}else {
				max = 1/max;
			}		
			baseStationHealthRatio.setRatio(max);
			if (stationInfoDto!=null) {
				if (!CommonUntils.isempty(stationInfoDto.getStationLatitude())) {
					//baseStationHealthRatio.setLatitude(Double.parseDouble(stationInfoDto.getStationLatitude()));
				}
				if (!CommonUntils.isempty(stationInfoDto.getStationLongitude())) {
					//baseStationHealthRatio.setLogitude(Double.parseDouble(stationInfoDto.getStationLongitude()));
				}				
			}
			result.add(baseStationHealthRatio);
		}
		logger.error("-------计算结束---------");
		return result;
	}
	
	
}
