package com.iscas.sdas.dao.fault;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iscas.sdas.dto.data.PRBBean;

public interface FaultDao {

	List<PRBBean> getprb(PRBBean prb);
	List<PRBBean> getprbothers(PRBBean prb);
	List<PRBBean> getswitch(PRBBean prb);
	List<PRBBean> getswitchothers(PRBBean prb);
	List<PRBBean> getworkprb(PRBBean prb);
	List<PRBBean> getworkswitch(PRBBean prb);
	PRBBean getlasttime(String cellname);
}
