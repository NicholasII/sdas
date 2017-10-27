package com.iscas.sdas.dao.work;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iscas.sdas.dto.work.DeviceWorkDto;

public interface DeviceWorkDao {
	
	List<DeviceWorkDto> getlist(DeviceWorkDto work);
	
	List<DeviceWorkDto> getlist2(@Param("work")DeviceWorkDto work,@Param("start")String start,@Param("end")String end);
	
	List<DeviceWorkDto> getlistWhithinOneWeek(String cellname);

	List<DeviceWorkDto> getlistWhithinOneMonth(String cellname);
	
	List<DeviceWorkDto> getlistdevice(DeviceWorkDto deviceWorkDto);
}
