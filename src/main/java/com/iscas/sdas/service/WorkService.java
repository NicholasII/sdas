package com.iscas.sdas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.WorkDao;

@Service
public class WorkService {
	
	@Autowired
	WorkDao performanceWorkDao;
	
	
	
}
