package com.iscas.sdas.service.cell;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iscas.sdas.dao.cell.CellDao;
import com.iscas.sdas.dto.GroupIndexMeatdata;
import com.iscas.sdas.dto.cell.BaseCellHealth;
import com.iscas.sdas.dto.cell.BaseGroupIndex;
import com.iscas.sdas.dto.cell.CellDto;

@Service
public class CellService {

	@Autowired
	CellDao cellDao;
	
	public List<CellDto> getCellList(CellDto cellDto){
		return cellDao.getcells(cellDto);
	}
	
	public List<String> getCellGroup(){
		return cellDao.getgroups();
	}
	
	public List<GroupIndexMeatdata> getGroupIndexs(String grouptype){
		return cellDao.getgroupindexs(grouptype);
	}
	
	public String getgroup(String cellname){
		return cellDao.getgroup(cellname);
	}
	
	public List<double[]> generateCellHealthTrend(String cellname){
		List<double[]> list = new ArrayList<>();
		try {
			BaseCellHealth cellHealth = cellDao.cellhealthtrend(cellname);
			if (cellHealth!=null) {
				Method[] methods = cellHealth.getClass().getMethods();
				for (Method method : methods) {
					if (method.getName().startsWith("getRange")) {	
						double[] map = new double[2];
						String range = (String)method.invoke(cellHealth, null);						
						int  moment = Integer.parseInt(method.getName().substring(method.getName().lastIndexOf("_")+1));
						Double ratio = parseRatio(range);
						map[0] = moment;
						map[1] = ratio;
						list.add(map);
					}
					
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private double parseRatio(String range){
		double ratio = 0;
		if (range!=null) {
			int count = 1;
			JSONArray array = JSON.parseArray(range);
			if (array!=null) {
				for (int i = 0; i < array.size(); i++) {
					JSONObject obj = array.getJSONObject(i);
					if (obj.containsKey("Ratio")) {
					    ratio = (Double)obj.get("Ratio");
						return ratio;
					}
					count++;
				}
				if (count==array.size()) {
					ratio = 0;
					return ratio;
				}
			}
		}
		return ratio;
	}
}
