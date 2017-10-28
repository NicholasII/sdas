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
 * 小组小区健康模型相关
 * @author dongqun
 * 2017年10月12日上午9:43:23
 */

@Controller
@RequestMapping("/cell")
public class CellIndexController {
	@Autowired
	CellIndexService cellIndexService;
	/**
	 * 小区模型
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	@ResponseBody
	public ModelMap cellindex(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String indexid = request.getParameter("index");
		String cellname = request.getParameter("cellname");
		if (!CommonUntils.isempty(indexid)&&!CommonUntils.isempty(cellname)) {
			try {
				List<List<Double[]>> hosdata = cellIndexService.generateIndexData(cellname, indexid, cellIndexService.CELL);
				if (hosdata!=null) {
					map.addAttribute(Constraints.RESULT_ROW,hosdata);	
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
	/**
	 * 小组模型
	 * @param request
	 * @return
	 */
	@RequestMapping("/groupindexcontent")
	@ResponseBody
	public ModelMap groupindex(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String grouptype = request.getParameter("grouptype");
		String indexid = request.getParameter("indexid");
		if (!CommonUntils.isempty(grouptype)&&!CommonUntils.isempty(indexid)) {
			try {
				List<List<Double[]>> result = cellIndexService.generateIndexData(grouptype, indexid, CellIndexService.GROUP);
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
