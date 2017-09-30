package com.iscas.sdas.dao.work;

import java.util.List;

import com.iscas.sdas.dto.work.OutServerDto;

public interface OutServerDao {

	List<OutServerDto> getlist(OutServerDto outServerDto);
}
