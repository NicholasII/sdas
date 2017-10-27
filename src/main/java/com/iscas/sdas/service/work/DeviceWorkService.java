package com.iscas.sdas.service.work;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.work.DeviceWorkDao;
import com.iscas.sdas.dto.work.DeviceWorkDto;

@Service
public class DeviceWorkService {

	@Autowired
	DeviceWorkDao deviceWorkDao;
	
	
	public List<DeviceWorkDto> getlist(DeviceWorkDto workDto){
		return deviceWorkDao.getlist(workDto);
	}
	
	public List<DeviceWorkDto> getlist(DeviceWorkDto workDto,String start,String end){
		if (start!=null && end!=null) {
			return deviceWorkDao.getlist(workDto);
		}else {
			return deviceWorkDao.getlist2(workDto,start,end);
		}
		
	}
	
	public List<DeviceWorkDto> getlistoneweek(String cellname){
		return deviceWorkDao.getlistWhithinOneWeek(cellname);
	}
	
	public List<DeviceWorkDto> getlistonemonth(String cellname){
		return deviceWorkDao.getlistWhithinOneMonth(cellname);
	}
	
	public List<DeviceWorkDto> getlistdevice(DeviceWorkDto deviceWorkDto){
		return deviceWorkDao.getlistdevice(deviceWorkDto);
	}
}
