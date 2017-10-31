package com.iscas.sdas.service.work;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.work.AllOutServerDao;
import com.iscas.sdas.dao.work.OutServerDao;
import com.iscas.sdas.dto.work.AllOutServerDto;
import com.iscas.sdas.dto.work.OutServerDto;

@Service
public class OutServerService {

	@Autowired
	OutServerDao outServerDao;
	@Autowired
	AllOutServerDao allOutServerDao;
	
	public List<OutServerDto> getlist(OutServerDto outServerDto){
		return outServerDao.getlist(outServerDto);
	}
	
	public List<OutServerDto> getlist(OutServerDto workDto,String start,String end){
		if (start!=null && end!=null) {
			return outServerDao.getlist(workDto);
		}else {
			return outServerDao.getlist2(workDto,start,end);
		}
		
	}
	
	public List<OutServerDto> getlistoneweek(String cellname){
		return outServerDao.getlistWhithinOneWeek(cellname);
	}
	
	public List<OutServerDto> getlistonemonth(String cellname){
		return outServerDao.getlistWhithinOneMonth(cellname);
	}
	public List<OutServerDto> getlistout(OutServerDto outServerDto){
		return outServerDao.getlistout(outServerDto);
	}
	
	public void insertOSWork(List<AllOutServerDto> osWorkDtos){
		for (AllOutServerDto osWorkDto : osWorkDtos) {
			allOutServerDao.insert(osWorkDto);
		}
	}
	public void clearOSWork(){
		allOutServerDao.delete();
	}
}
