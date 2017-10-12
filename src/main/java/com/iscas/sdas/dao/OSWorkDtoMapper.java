package com.iscas.sdas.dao;

import com.iscas.sdas.dto.OSWorkDto;

public interface OSWorkDtoMapper {
    int insert(OSWorkDto record);

    int insertSelective(OSWorkDto record);
}