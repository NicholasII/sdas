package com.iscas.sdas.dao.sys;

import com.iscas.sdas.common.BaseDao;
import com.iscas.sdas.dto.sys.UserDto;

public interface UserDao extends BaseDao<UserDto>{

    int insertSelective(UserDto record);
    
    UserDto getUser(UserDto userDto);

}