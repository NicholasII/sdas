package com.iscas.sdas.dao;

import com.iscas.sdas.dto.cell.BaseGroupWeight;

/**
 * 小组和小区的权重
 * 对应表：t_group_weight_info、
 * @author Administrator
 *
 */
public interface IndexWeightDao {

	String indexname(Integer index);
	/**
	 * 小组的权重
	 * Administrator
	 * 2017年9月26日下午2:05:51
	 * @param type
	 * @return
	 */
	BaseGroupWeight groupweight(String type);

}
