package com.iscas.sdas.dao.work;

import com.iscas.sdas.dto.work.AllOutServerDto;

public interface AllOutServerDao {
	
    int insert(AllOutServerDto record);

    int insertSelective(AllOutServerDto record);
    
    int delete();
}