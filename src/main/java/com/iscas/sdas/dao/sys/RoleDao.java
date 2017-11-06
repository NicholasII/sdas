package com.iscas.sdas.dao.sys;

import com.iscas.sdas.common.BaseDao;
import com.iscas.sdas.dto.sys.RoleDto;

public interface RoleDao extends BaseDao<RoleDto>{

    int insertSelective(RoleDto record);

}