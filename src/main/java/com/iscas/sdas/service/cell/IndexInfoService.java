package com.iscas.sdas.service.cell;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscas.sdas.dao.MRdataMapper;
import com.iscas.sdas.dto.AllData;
import com.iscas.sdas.dto.MRdata;

@Service
public class IndexInfoService {

	@Autowired
	MRdataMapper mapper;

	/**
	 * 从mysql中读取指标信息 Administrator 2017年9月26日下午5:47:46
	 * 
	 * @param cellname
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public List<AllData> indexfrommysql(String cellname)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		List<AllData> list = new ArrayList<>();
		MRdata mRdata = mapper.select(cellname);
		int count = 1;
		if (mRdata != null) {
			Method[] methods = mRdata.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (method.getName().startsWith("get")) {
					if (method.invoke(mRdata, null) != null) {
						AllData data = new AllData();
						
						String value = method.invoke(mRdata, null).toString();
						String key = method.getName().substring(3);
						data.setDataname(key);
						data.setDatavalue(value);
						data.setId(count);
						data.setType("MR数据");
						list.add(data);
						count++;
					}

				}

			}

			return list;
		}
		return null;

	}

	/**
	 * 从hdfs中读取指标信息 Administrator 2017年9月26日下午5:48:04
	 * 
	 * @param cellname
	 * @return
	 */
	/*
	 * public List<DataLine> indexfromhdfs(String cellname){ DataIO io = new
	 * NetManageDatas(); Datas data = io.out(cellname, "201708"); List<DataLine>
	 * dataLines = data.datas; return dataLines; }
	 */
}
