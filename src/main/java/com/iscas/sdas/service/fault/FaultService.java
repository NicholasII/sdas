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
	
	public List<PRBBean> getprbs(String cellname,Integer daynum,String starttime,String endtime){
		return faultDao.getprb(cellname,daynum,starttime,endtime);
	}
	public List<PRBBean> getprbothers(String cellname,Integer daynum,String starttime,String endtime){
		return faultDao.getprbothers(cellname,daynum,starttime,endtime);
	}
	public List<PRBBean> getswitch(String cellname,Integer daynum,String starttime,String endtime){
		return faultDao.getswitch(cellname,daynum,starttime,endtime);
	}
	public List<PRBBean> getswitchothers(String cellname,Integer daynum,String starttime,String endtime){
		return faultDao.getswitchothers(cellname,daynum,starttime,endtime);
	}
	public List<PRBBean> getworkprb(String cellname,Integer daynum,String starttime,String endtime){
		return faultDao.getworkprb(cellname,daynum,starttime,endtime);
	}
	public List<PRBBean> getworkswitch(String cellname,Integer daynum,String starttime,String endtime){
		return faultDao.getworkswitch(cellname,daynum,starttime,endtime);
	}
}
