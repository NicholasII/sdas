package com.iscas.sdas.service.cell;

import java.lang.reflect.InvocationTargetException;
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
import com.iscas.sdas.dao.IndexWeightDao;
import com.iscas.sdas.dao.cell.CellDao;
import com.iscas.sdas.dao.cell.CellIndexDao;
import com.iscas.sdas.dto.cell.Base;
import com.iscas.sdas.dto.cell.BaseGroupIndex;
import com.iscas.sdas.dto.cell.BaseGroupWeight;
import com.iscas.sdas.dto.cell.BaseIndex;
import com.iscas.sdas.dto.cell.WeightSequence;
import com.iscas.sdas.dto.cell.BaseWeight;
/**
 *小区的指标和权重；分组的指标和权重
 * @author Administrator
 *
 */
@Service
public class CellIndexService {
	@Autowired
	CellIndexDao cellIndexDao;
	@Autowired
	IndexWeightDao weightDao;
	
	public static String GROUP = "group";
	public static String CELL = "cell";
	
	/**
	 * 小区
	 * @param indexid
	 * @param cellname
	 * @return
	 */
	/*public List<List<Double[]>> generateIndexData(String indexid,String cellname){
		List<List<Double[]>> map = new ArrayList<>();
		try {
			BaseIndex cellIndex = cellIndexDao.getOriginIndex(indexid,cellname);
			if (cellIndex!=null) {
				Method[] methods = cellIndex.getClass().getMethods();
				for (Method method : methods) {
					if (method.getName().contains("getRange")) {	
						String range = (String)method.invoke(cellIndex, null);						
						String key = method.getName().substring(method.getName().lastIndexOf("_")+1);
						List<Double[]> value = getconv(key,range);
						if (value!=null) {
							map.add(value);
						}
					}
					
				}
				int max=0;
				List<List<Double[]>> result = new ArrayList<>();
				for (int i = 0; i < map.size(); i++) {
					List<Double[]> ratios = map.get(i);
					if (max<ratios.size()) {
						max=ratios.size();
					}
				}
				for (int i = 0; i < max; i++) {
					List<Double[]> ratiolist = new ArrayList<>();
					result.add(ratiolist);
				}
				for (int i = 0; i < map.size(); i++) {
					List<Double[]> ratios = map.get(i);
					for(int j=0; j<max; j++){
						if (j<ratios.size()) {
							result.get(j).add(ratios.get(j));
						}else {
							Double[] point = new Double[5];
							point[0] = ratios.get(0)[0];
							point[1] = 0.0;
							point[2] = 0.0;
							point[3] = 0.0;
							point[4] = 0.0;
							result.get(j).add(point);
						}
						
					}
				}
				return result;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	/**
	 * 小组he小区
	 * @param grouptype
	 * @param indexcode
	 * @return
	 */
	public List<List<Double[]>> generateIndexData(String cellname,String indexcode,String type){
		List<List<Double[]>> map = new ArrayList<>();
		try {
			Base baseindex = null;
			if (GROUP.equals(type)) {
				baseindex = cellIndexDao.getGroupIndexContent(cellname, indexcode);
			}else if (CELL.equals(type)){
				baseindex = cellIndexDao.getCellIndexContent(cellname, indexcode);
			}
			//BaseGroupIndex groupIndex = cellIndexDao.getgroupindexcontent(grouptype, indexcode);
			if (baseindex!=null) {
				Method[] methods = baseindex.getClass().getMethods();
				for (Method method : methods) {
					if (method.getName().contains("getRange")) {	
						String range = (String)method.invoke(baseindex, null);						
						String key = method.getName().substring(method.getName().lastIndexOf("_")+1);
						List<Double[]> value = getconv(key,range);
						if (value!=null) {
							map.add(value);						
						}
					}				
				}
				int max=0;
				List<List<Double[]>> result = new ArrayList<>();
				for (int i = 0; i < map.size(); i++) {
					List<Double[]> ratios = map.get(i);
					if (max<ratios.size()) {
						max=ratios.size();
					}
				}
				for (int i = 0; i < max; i++) {
					List<Double[]> ratiolist = new ArrayList<>();
					result.add(ratiolist);
				}
				for (int i = 0; i < map.size(); i++) {
					List<Double[]> ratios = map.get(i);
					for(int j=0; j<max; j++){
						if (j<ratios.size()) {
							result.get(j).add(ratios.get(j));
						}else {
							Double[] point = new Double[5];
							point[0] = ratios.get(0)[0];
							point[1] = 0.0;
							point[2] = 0.0;
							point[3] = 0.0;
							point[4] = 0.0;
							result.get(j).add(point);
						}
						
					}
				}
				return result;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 生成多个簇心
	 * @param key
	 * @param content
	 * @return
	 */
	private List<Double[]> getconv(String key,String content) {
		List<Double[]> result = new ArrayList<>();
		
		double temp = Double.valueOf(key);
		double max,min,pre,last;
		try {
			JSONArray array = JSON.parseArray(content);
				for (int i = 0; i < array.size(); i++) {
					JSONObject obj = array.getJSONObject(i);					
					if (obj.containsKey("is_valid")) {
						boolean is_valid = obj.getBoolean("is_valid");
						if (is_valid) {
							Double[] point = new Double[5];
							point[0] =  temp;
							if (obj.containsKey("max")) {
								max = obj.getDouble("max");
								point[4] = max;
							}else {
								point[4] = 0.0;
							}
							if (obj.containsKey("min")) {
								min = obj.getDouble("min");
								point[3] = min;
							}else {
								point[3] = 0.0;
							}
							if (obj.containsKey("pre")) {
								pre = obj.getDouble("pre");
								point[1] = pre;
							}else{
								point[1] = 0.0;
							}
							if (obj.containsKey("last")) {
								last = obj.getDouble("last");
								point[2] = last;
							}else {
								point[2] = 0.0;
							}
							result.add(point);
						}
					}				
				}
				if (result.size()==0) {
					Double[] point = new Double[5];
					point[0] = temp;
					point[1] = 0.0;
					point[2] = 0.0;
					point[3] = 0.0;
					point[4] = 0.0;
					result.add(point);
				}		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public List<Double[]> generateRealTimeData(Integer index){
		List<Double[]> datalist = new ArrayList<>();
		List<String> datas = cellIndexDao.getRealTimeData(index);
		if (datas!=null) {
			for (int i = 0; i < datas.size(); i++) {
				Double[] data = new Double[2];
				data[0] = 0.25 *(i+1);
				if (datas.get(i).contains("%")) {
					int t =  datas.get(i).lastIndexOf("%");
					data[1] = Double.parseDouble(datas.get(i).substring(0, t));	
				}else {
					data[1] = Double.parseDouble(datas.get(i));
				}
				
				datalist.add(data);
			}
		}
		return datalist;
	}
	
	
	public Map<String, WeightSequence> cellweight(String cellname){
		Map<String, WeightSequence> map = new HashMap<>();
		try {
			BaseWeight origindata = cellIndexDao.getCellWeight(cellname);
			if (origindata!=null) {
				List<WeightSequence> sequences = new ArrayList<>();	
				Method[] methods = origindata.getClass().getMethods();
				for (Method method : methods) {
					if (method.getName().contains("getRange")) {	
						String str_weight = (String)method.invoke(origindata, null);						
						int time = Integer.parseInt(method.getName().substring(method.getName().lastIndexOf("_")+1));
						List<WeightSequence> sequence = getweight(time, str_weight);
						for (WeightSequence weightSequence : sequence) {
							sequences.add(weightSequence);
						}
					}	
				}
				for (WeightSequence weight_1 : sequences) {
					if (!map.containsKey(weight_1.getIndeicator_name())) {
						map.put(weight_1.getIndeicator_name(), weight_1);
					}else {
						WeightSequence weight_end = map.get(weight_1.getIndeicator_name());
						Method[] methods2 = weight_end.getClass().getMethods();
						String methodname = "setRange" + weight_1.getFlag();
						String valuename = "getRange" + weight_1.getFlag();
						Method getvaluemethod = weight_1.getClass().getMethod(valuename, null);
						String value = (String)getvaluemethod.invoke(weight_1, null);
						for (Method m : methods2) {
							if (methodname.equals(m.getName())) {	
								m.invoke(weight_end, value);						
								break;
							}	
						}
					}
				}
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private List<WeightSequence> getweight(int time,String weight) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<WeightSequence> baseIndex2s = new ArrayList<>();
		if (weight!=null) {
			JSONArray array = JSON.parseArray(weight);
			for (int i = 0; i < array.size(); i++) {			
				JSONObject obj = array.getJSONObject(i);
				if (obj.containsKey("value")&&obj.get("value")!=null) {
					WeightSequence baseIndex2 = new WeightSequence();
					String index = obj.getString("name");//还需要将V_XX转换为指标名
					int tip = index.lastIndexOf("_");
					if (tip>0) {
						index = index.substring(tip+1);
						index = weightDao.indexname(Integer.valueOf(index));
						String value = obj.getBigDecimal("value").toString();
						baseIndex2.setIndeicator_name(index);
						baseIndex2.setFlag(time);
						Method[] methods = baseIndex2.getClass().getMethods();
						for (Method method : methods) {
							if (method.getName().contains("setRange")) {	
								int key = Integer.parseInt(method.getName().substring(method.getName().lastIndexOf("e")+1));
								if (key==time) {
									method.invoke(baseIndex2, value);	
									break;
								}							
							}	
						}
						baseIndex2s.add(baseIndex2);
					}
					
				}
			}		
		}
		return baseIndex2s;
	}
	
	public Map<String, WeightSequence> groupweight(String type){
		Map<String, WeightSequence> map = new HashMap<>();
		try {
			BaseGroupWeight origindata = weightDao.groupweight(type);
			if (origindata!=null) {
				List<WeightSequence> sequences = new ArrayList<>();	
				Method[] methods = origindata.getClass().getMethods();
				for (Method method : methods) {
					if (method.getName().contains("getRange")) {	
						String str_weight = (String)method.invoke(origindata, null);						
						int time = Integer.parseInt(method.getName().substring(method.getName().lastIndexOf("_")+1));
						List<WeightSequence> sequence = getweight(time, str_weight);
						for (WeightSequence weightSequence : sequence) {
							sequences.add(weightSequence);
						}
					}	
				}
				for (WeightSequence weight_1 : sequences) {
					if (!map.containsKey(weight_1.getIndeicator_name())) {
						map.put(weight_1.getIndeicator_name(), weight_1);
					}else {
						WeightSequence weight_end = map.get(weight_1.getIndeicator_name());
						Method[] methods2 = weight_end.getClass().getMethods();
						String methodname = "setRange" + weight_1.getFlag();
						String valuename = "getRange" + weight_1.getFlag();
						Method getvaluemethod = weight_1.getClass().getMethod(valuename, null);
						String value = (String)getvaluemethod.invoke(weight_1, null);
						for (Method m : methods2) {
							if (methodname.equals(m.getName())) {	
								m.invoke(weight_end, value);						
								break;
							}	
						}
					}
				}
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
