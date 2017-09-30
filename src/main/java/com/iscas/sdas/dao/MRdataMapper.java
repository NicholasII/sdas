package com.iscas.sdas.dao;

import java.util.List;

import com.iscas.sdas.dto.MRdata;

public interface MRdataMapper {
    int insert(MRdata record);

    int insertSelective(MRdata record);
    
    MRdata select(String cellname);
}