package com.iscas.sdas.dao;

import java.util.List;

import com.iscas.sdas.dto.TableInfoDto;



public interface TableInfoDtoMapper {
    int insert(TableInfoDto record);

    int insertSelective(TableInfoDto record);
    
    List<TableInfoDto> select(String tablename);
}