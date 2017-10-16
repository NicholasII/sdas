package com.iscas.sdas.dao.cell;

import java.util.List;

import com.iscas.sdas.dto.CellComplainDto;
import com.iscas.sdas.dto.DeviceWorkDto;
import com.iscas.sdas.dto.GroupIndexMeatdata;
import com.iscas.sdas.dto.OSWorkDto;
import com.iscas.sdas.dto.PerformanceWorkDto;
import com.iscas.sdas.dto.cell.BaseCellHealth;
import com.iscas.sdas.dto.cell.BaseGroupIndex;
import com.iscas.sdas.dto.cell.CellDto;

public interface CellDao {
	
	List<CellDto> getcells(CellDto cell);

	List<String> getgroups();
	
	List<GroupIndexMeatdata> getgroupindexs(String grouptype);
	
	BaseGroupIndex getgroupindexcontent(String grouptype,String indexcode);
	
	String getgroup(String cellname);
	/**
	 * 一个周的健康度
	 * @param cellname
	 * @return
	 */
	List<BaseCellHealth> cellhealthtrend(String cellname);
	
	List<PerformanceWorkDto> performWorkWithinCurrTime(String cellname);
	
	List<DeviceWorkDto> deviceWorkWithinCurrTime(String cellname);
	
	List<OSWorkDto> osWorkWithinCurrTime(String cellname);
	
	List<CellComplainDto> complaintWithinCurrTime(String cellname);
	/**
	 * 一个月的健康度
	 * @param cellname
	 * @return
	 */
	List<BaseCellHealth> cellhealthtrendWithinOneMonth(String cellname);
	
	List<CellComplainDto> complaintWithinOneMonth(String cellname);
	/**
	 * 一定时间段的健康度
	 * @param cellname
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	List<BaseCellHealth> cellhealthtrendWithinSelect(String cellname,String starttime,String endtime);
	
	List<CellComplainDto> complaintWithinSelect(String cellname,String starttime,String endtime);
}
