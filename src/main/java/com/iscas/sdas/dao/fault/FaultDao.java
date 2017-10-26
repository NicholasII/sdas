package com.iscas.sdas.dao.fault;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iscas.sdas.dto.data.PRBBean;

public interface FaultDao {

	List<PRBBean> getprb(@Param("cellname")String cellname,@Param("daynum")Integer daynum,@Param("starttime")String starttime,@Param("endtime")String endtime);
	List<PRBBean> getprbothers(@Param("cellname")String cellname,@Param("daynum")Integer daynum,@Param("starttime")String starttime,@Param("endtime")String endtime);
	List<PRBBean> getswitch(@Param("cellname")String cellname,@Param("daynum")Integer daynum,@Param("starttime")String starttime,@Param("endtime")String endtime);
	List<PRBBean> getswitchothers(@Param("cellname")String cellname,@Param("daynum")Integer daynum,@Param("starttime")String starttime,@Param("endtime")String endtime);
	List<PRBBean> getworkprb(@Param("cellname")String cellname,@Param("daynum")Integer daynum,@Param("starttime")String starttime,@Param("endtime")String endtime);
	List<PRBBean> getworkswitch(@Param("cellname")String cellname,@Param("daynum")Integer daynum,@Param("starttime")String starttime,@Param("endtime")String endtime);
}
