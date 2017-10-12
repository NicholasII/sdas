package com.iscas.sdas.controller.cell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iscas.sdas.dto.cell.WeightSequence;
import com.iscas.sdas.service.cell.CellIndexService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;
/**
 * 小区健康模型相关
 * @author dongqun
 * 2017年10月12日上午9:43:23
 */

@Controller
@RequestMapping("/cell")
public class CellIndexController {
	@Autowired
	CellIndexService cellIndexService;
	
	@RequestMapping("/index")
	@ResponseBody
	public ModelMap cellindex(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String str_index = request.getParameter("index");
		String cellname = request.getParameter("cellname");
		if (!CommonUntils.isempty(str_index)&&!CommonUntils.isempty(cellname)) {
			Integer indexid = Integer.parseInt(str_index);
			try {
				List<Double[]> hosdata = cellIndexService.generateIndexData(indexid,cellname);
				List<Double[]> realdata = cellIndexService.generateRealTimeData(indexid);
				if (hosdata!=null) {
					map.addAttribute(Constraints.RESULT_ROW,hosdata);	
					map.addAttribute(Constraints.RESULT_SUCCESS, true);	
				}else {
					map.addAttribute(Constraints.RESULT_SUCCESS, false);	
				}
				/*if (realdata!=null) {
					map.addAttribute("real", realdata);
				}*/
							
				return map;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.addAttribute(Constraints.RESULT_SUCCESS, false);
		return map;
	}
	@RequestMapping("/weight")
	@ResponseBody
	public ModelMap cellweight(@RequestParam(value = "cellname",required = true)String cellname){
		ModelMap map =new ModelMap();
		Map<String, WeightSequence>  sequence = cellIndexService.cellweight(cellname);
		if (sequence!=null) {
			Set<String> index = sequence.keySet();
			Iterator<String> iterator = index.iterator();
			List<WeightSequence> sequences = new ArrayList<>();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				sequences.add(sequence.get(key));
			}
			map.addAttribute(Constraints.RESULT_ROW, sequences);
			map.addAttribute(Constraints.RESULT_SUCCESS, true);
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
		
		return map;
	}
	
	@RequestMapping("/groupindexcontent")
	@ResponseBody
	public ModelMap groupindex(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String grouptype = request.getParameter("grouptype");
		String indexid = request.getParameter("indexid");
		if (!CommonUntils.isempty(grouptype)&&!CommonUntils.isempty(indexid)) {
			try {
				List<Double[]> result = cellIndexService.generateGroupIndexData(grouptype, indexid);
				if (result!=null) {
					map.addAttribute(Constraints.RESULT_ROW,result);	
					map.addAttribute(Constraints.RESULT_SUCCESS, true);	
				}else {
					map.addAttribute(Constraints.RESULT_SUCCESS, false);	
				}				
				return map;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.addAttribute(Constraints.RESULT_SUCCESS, false);
		return map;
	}
}
