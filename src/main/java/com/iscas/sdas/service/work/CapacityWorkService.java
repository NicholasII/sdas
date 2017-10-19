package com.iscas.sdas.service.work;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.work.CapacityWorkDao;
import com.iscas.sdas.dto.work.CapacityWorkDto;

@Service
public class CapacityWorkService {

	@Autowired
	CapacityWorkDao capacityWorkDao;
	
	
	public List<CapacityWorkDto> getlist(CapacityWorkDto workDto,String starttime,String endtime){
		List<CapacityWorkDto> capacityWorkDto = null;
		if (starttime!=null && endtime!=null) {
			capacityWorkDto = capacityWorkDao.getlist2(workDto,starttime,endtime);
		}else {
			capacityWorkDto = capacityWorkDao.getlist(workDto);
		}
		return capacityWorkDto;
	}
	
	public List<CapacityWorkDto> getdoubtlist(){
		return capacityWorkDao.getDoubtlist();
	}
	
	public List<String> getbelongaera(){
		return capacityWorkDao.getbelongaera();
	}
	
	public List<CapacityWorkDto> getlistWhithinOneWeek(String cellname){
		return capacityWorkDao.getlistWhithinOneWeek(cellname);
	}
	public List<CapacityWorkDto> getlistWhithinOneMonth(String cellname){
		return capacityWorkDao.getlistWhithinOneMonth(cellname);
	}
}
