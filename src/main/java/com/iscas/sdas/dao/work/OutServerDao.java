package com.iscas.sdas.dao.work;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iscas.sdas.dto.work.OutServerDto;

public interface OutServerDao {

	List<OutServerDto> getlist(OutServerDto outServerDto);
	
	List<OutServerDto> getlist2(@Param("work")OutServerDto work,@Param("start")String start,@Param("end")String end);
	
	List<OutServerDto> getlistWhithinOneWeek(String cellname);

	List<OutServerDto> getlistWhithinOneMonth(String cellname);
	
	List<OutServerDto> getlistout(OutServerDto outServerDto);
}
