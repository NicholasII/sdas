package com.iscas.sdas.dao;

import java.util.List;

import com.iscas.sdas.common.BaseDao;
import com.iscas.sdas.dto.FileLogDto;

public interface FileLogDao extends BaseDao<FileLogDto>{
	List<FileLogDto> all(FileLogDto dto);
}