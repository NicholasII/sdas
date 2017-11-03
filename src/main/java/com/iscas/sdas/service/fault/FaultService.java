package com.iscas.sdas.service.fault;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.fault.FaultDao;
import com.iscas.sdas.dto.data.PRBBean;

@Service
public class FaultService {

	@Autowired
	FaultDao faultDao;
	
	public List<PRBBean> getprbs(PRBBean prb){
		return faultDao.getprb(prb);
	}
	public List<PRBBean> getprbothers(PRBBean prb){
		return faultDao.getprbothers(prb);
	}
	public List<PRBBean> getswitch(PRBBean prb){
		return faultDao.getswitch(prb);
	}
	public List<PRBBean> getswitchothers(PRBBean prb){
		return faultDao.getswitchothers(prb);
	}
	public List<PRBBean> getworkprb(PRBBean prb){
		return faultDao.getworkprb(prb);
	}
	public List<PRBBean> getworkswitch(PRBBean prb){
		return faultDao.getworkswitch(prb);
	}
	public PRBBean getlasttime(String cellname){
		return faultDao.getlasttime(cellname);
	}
}
