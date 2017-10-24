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
	
	public List<PRBBean> getprbs(Integer daynum,String starttime,String endtime){
		return faultDao.getprb(daynum,starttime,endtime);
	}
	public List<PRBBean> getprbothers(Integer daynum,String starttime,String endtime){
		return faultDao.getprbothers(daynum,starttime,endtime);
	}
	public List<PRBBean> getswitch(Integer daynum,String starttime,String endtime){
		return faultDao.getswitch(daynum,starttime,endtime);
	}
	public List<PRBBean> getswitchothers(Integer daynum,String starttime,String endtime){
		return faultDao.getswitchothers(daynum,starttime,endtime);
	}
}
