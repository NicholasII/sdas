package com.iscas.sdas.dao;

import com.iscas.sdas.dto.DeviceWorkDto;

public interface DeviceWorkDtoMapper {
    int insert(DeviceWorkDto record);

    int insertSelective(DeviceWorkDto record);
}