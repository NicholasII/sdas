package com.iscas.sdas.dao;

import com.iscas.sdas.dto.StationInfoDto;

public interface StationInfoDtoMapper {
    int deleteByPrimaryKey(String stationCode);

    int insert(StationInfoDto record);

    int insertSelective(StationInfoDto record);

    StationInfoDto selectByPrimaryKey(String stationCode);
    
    StationInfoDto selectByStationName(String stationName);

    int updateByPrimaryKeySelective(StationInfoDto record);

    int updateByPrimaryKey(StationInfoDto record);
}