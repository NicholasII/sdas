package com.iscas.sdas.dao.work;

import com.iscas.sdas.dto.work.AllDeviceWorkDto;

public interface AllDeviceWorkDao {
    int insert(AllDeviceWorkDto record);

    int insertSelective(AllDeviceWorkDto record);
}