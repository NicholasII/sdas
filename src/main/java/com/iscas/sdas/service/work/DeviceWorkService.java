package com.iscas.sdas.service.work;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.work.CapacityWorkDao;
import com.iscas.sdas.dao.work.DeviceWorkDao;
import com.iscas.sdas.dto.work.CapacityWorkDto;
import com.iscas.sdas.dto.work.DeviceWorkDto;

@Service
public class DeviceWorkService {

	@Autowired
	DeviceWorkDao deviceWorkDao;
	
	
	public List<DeviceWorkDto> getlist(DeviceWorkDto workDto){
		return deviceWorkDao.getlist(workDto);
	}
}
