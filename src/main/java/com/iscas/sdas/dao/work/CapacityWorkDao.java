package com.iscas.sdas.dao.work;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iscas.sdas.dto.work.CapacityWorkDto;

public interface CapacityWorkDao {
	
	List<CapacityWorkDto> getlist(CapacityWorkDto work);
	
	List<CapacityWorkDto> getlist2(@Param("work")CapacityWorkDto work,@Param("starttime")String starttime,@Param("endtime")String endtime);
	
	List<CapacityWorkDto> getDoubtlist();
	/**
	 * 从可疑工单表中获取最近一天的数据
	 * @return
	 */
	List<CapacityWorkDto> getListTheDay();
	
	List<String> getbelongaera();
	
	List<CapacityWorkDto> getlistWhithinOneWeek(String cellname);

	List<CapacityWorkDto> getlistWhithinOneMonth(String cellname);
	
	List<CapacityWorkDto> getvalidatelist();
	/**
	 * 更新确定工单
	 * @param work
	 * @return
	 */
	int update(CapacityWorkDto work);
	/**
	 * 更新可疑工单
	 * @param work
	 * @return
	 */
	int updatedoubtwork(CapacityWorkDto work);
	/**
	 * 更新预警工单
	 * @param work
	 * @return
	 */
	int updatealarmwork(CapacityWorkDto work);
	/**
	 * 临时演示用
	 * @return
	 */
	List<CapacityWorkDto> getAllDoubtWorks();
	/**
	 * 临时演示用
	 * @return
	 */
	List<CapacityWorkDto> getAllWorks(CapacityWorkDto capacityWorkDto);
}
