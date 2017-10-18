package com.iscas.sdas.service.fault;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.fault.FaultDao;
import com.iscas.sdas.dto.data.PRBBean;

@Service
public class FaultService {

	@Autowired
	FaultDao faultDao;
	
	public List<PRBBean> getprbs(){
		return faultDao.getprb();
	}
	public List<PRBBean> getprbothers(){
		return faultDao.getprbothers();
	}
	public List<PRBBean> getswitch(){
		return faultDao.getswitch();
	}
	public List<PRBBean> getswitchothers(){
		return faultDao.getswitchothers();
	}
}
