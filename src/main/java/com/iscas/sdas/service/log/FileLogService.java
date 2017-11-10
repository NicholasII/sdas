package com.iscas.sdas.service.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.common.BaseService;
import com.iscas.sdas.dao.FileLogDao;
import com.iscas.sdas.dto.FileLogDto;
@Service
public class FileLogService extends BaseService<FileLogDao, FileLogDto> {
	@Autowired
	FileLogDao fileLogDao;
	public List<FileLogDto> all(FileLogDto dto){
		return fileLogDao.all(dto);
	}
}
