package com.iscas.sdas.dao.cell;

import java.util.List;

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
	
	List<BaseCellHealth> cellhealthtrend(String cellname);
	
	List<PerformanceWorkDto> performWorkWithinCurrTime(String cellname);
	
	List<DeviceWorkDto> deviceWorkWithinCurrTime(String cellname);
	
	List<OSWorkDto> osWorkWithinCurrTime(String cellname);
}
