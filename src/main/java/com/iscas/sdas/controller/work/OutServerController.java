package com.iscas.sdas.controller.work;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iscas.sdas.dto.work.OutServerDto;
import com.iscas.sdas.service.work.OutServerService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/outserverwork")
public class OutServerController {

	@Autowired
	OutServerService outServerService;
	@RequestMapping("/getlist")
	@ResponseBody
	public ModelMap getlist(HttpServletRequest request){
		ModelMap map = new ModelMap();
		OutServerDto outServerDto = new OutServerDto();
		String cellname = request.getParameter("cellname");
		if (!CommonUntils.isempty(cellname)) {
			outServerDto.setCell_name(cellname);
		}
		List<OutServerDto> outServerDtos = outServerService.getlist(outServerDto);
		map.addAttribute(Constraints.RESULT_ROW
				, outServerDtos);
		return map;
	}
}
