package com.iscas.sdas.controller.cell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iscas.sdas.dto.cell.WeightSequence;
import com.iscas.sdas.service.cell.CellIndexService;
import com.iscas.sdas.util.Constraints;
@Controller
@RequestMapping("/group")
public class GroupController {

	@Autowired
	CellIndexService cellIndexService;
	
	@RequestMapping("/weight")
	@ResponseBody
	public ModelMap cellweight(@RequestParam(value = "type",required = true)String type){
		ModelMap map =new ModelMap();
		Map<String, WeightSequence>  sequence = cellIndexService.groupweight(type);
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
}
