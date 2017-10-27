package com.iscas.sdas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iscas.sdas.dto.CellComplainDto;
import com.iscas.sdas.dto.ComplainDto;

public interface ComplainDao {
	
	List<ComplainDto> getlist();
	
	List<CellComplainDto> getcelllist(String cellname);
	
	List<CellComplainDto> getPageList();
	
	List<CellComplainDto> getfocuslist(String cellname);

	List<CellComplainDto> getcomplist(ComplainDto ComplainDto);
	/**
	 * 一周内投诉
	 * @param cellname
	 * @return
	 */
	List<CellComplainDto> complaintWithinOneWeek(String cellname);
	/**
	 * 一月内投诉
	 * @param cellname
	 * @return
	 */
	List<CellComplainDto> complaintWithinOneMonth(String cellname);
	/**
	 * 一定时间段的投诉
	 * @param cellname
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	List<CellComplainDto> complaintWithinSelect(@Param("cellname")String cellname,@Param("start")String starttime,@Param("end")String endtime);

}
