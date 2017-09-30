package com.iscas.sdas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.TableInfoDtoMapper;
import com.iscas.sdas.dto.TableInfoDto;

@Service
public class CommonService {
	@Autowired
	TableInfoDtoMapper tableInfoDtoMapper;
	/**
	 * 获取某张表中所有字段信息
	 * @param tablename 表名
	 * @return
	 */
	public List<TableInfoDto> tableindex(String tablename){
		return tableInfoDtoMapper.select(tablename);
	}
}
