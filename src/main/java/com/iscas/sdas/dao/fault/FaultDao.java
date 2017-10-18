package com.iscas.sdas.dao.fault;

import java.util.List;

import com.iscas.sdas.dto.data.PRBBean;

public interface FaultDao {

	List<PRBBean> getprb();
	List<PRBBean> getprbothers();
	List<PRBBean> getswitch();
	List<PRBBean> getswitchothers();
	
}
