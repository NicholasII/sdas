package com.iscas.sdas.dao.cell;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iscas.sdas.dto.GroupIndexMeatdata;
import com.iscas.sdas.dto.cell.BaseCellHealth;
import com.iscas.sdas.dto.cell.CellDto;

public interface CellDao {
	
	List<CellDto> getcells(CellDto cell);

	List<String> getgroups();
	
	List<GroupIndexMeatdata> getgroupindexs(String grouptype);
	
	String getgroup(String cellname);
	/**
	 * 一个周的健康度
	 * @param cellname
	 * @return
	 */
	List<BaseCellHealth> cellhealthtrend(String cellname);
	/**
	 * 一个月的健康度
	 * @param cellname
	 * @return
	 */
	List<BaseCellHealth> cellhealthtrendWithinOneMonth(String cellname);
	/**
	 * 一定时间段的健康度
	 * @param cellname
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	List<BaseCellHealth> cellhealthtrendWithinSelect(@Param("cellname")String cellname,@Param("start")String starttime,@Param("end")String endtime);
	/**
	 * 该小区最近健康度
	 * @param cellname
	 * @return
	 */
	BaseCellHealth currenthealthratio(String cellname);
	/**
	 * 健康度表中所有的小区
	 * @return
	 */
	List<String> allcells();
	
	List<String> allstations();
	
	List<String> allcellsinstation(String stationname);
	/**
	 * 当前健康度
	 * @param cellname
	 * @return
	 */
	BaseCellHealth cellcurrenthealth(String cellname);
	/**
	 * 健康度异常预警
	 * @param cellname
	 * @return
	 */
	List<BaseCellHealth> alarmhealthtrend(String cellname);
	/**
	 * 异常预警指标名称
	 * @param key
	 * @return String
	 */
	String getalarmname(String key);
	/**
	 * 某一时刻的健康度
	 * @param cellname
	 * @param yyyymmdd
	 * @param hour
	 * @return
	 */
	Integer getHealthRatio(@Param("cellname")String cellname,@Param("yyyymmdd")String yyyymmdd,@Param("hour")String hour);
}
