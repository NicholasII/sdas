package com.iscas.sdas.dao.work;

import java.util.List;

import com.iscas.sdas.dto.work.CapacityWorkDto;
import com.iscas.sdas.dto.work.DeviceWorkDto;

public interface DeviceWorkDao {
	
	List<DeviceWorkDto> getlist(DeviceWorkDto work);
	

}
