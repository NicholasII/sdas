package com.iscas.sdas.service.work;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.work.OutServerDao;
import com.iscas.sdas.dto.work.OutServerDto;

@Service
public class OutServerService {

	@Autowired
	OutServerDao outServerDao;
	
	public List<OutServerDto> getlist(OutServerDto outServerDto){
		return outServerDao.getlist(outServerDto);
	}
	
}
