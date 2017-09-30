package com.iscas.sdas.controller.work;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iscas.sdas.dto.work.DeviceWorkDto;
import com.iscas.sdas.dto.work.OutServerDto;
import com.iscas.sdas.service.work.DeviceWorkService;
import com.iscas.sdas.service.work.OutServerService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/devicework")
public class DeviceController {

	@Autowired
	DeviceWorkService deviceWorkService;
	@RequestMapping("/getlist")
	@ResponseBody
	public ModelMap getlist(HttpServletRequest request){
		ModelMap map = new ModelMap();
		DeviceWorkDto deviceWorkDto = new DeviceWorkDto();
		String cellname = request.getParameter("cellname");
		if (!CommonUntils.isempty(cellname)) {
			deviceWorkDto.setCell_name(cellname);
		}
		List<DeviceWorkDto> outServerDtos = deviceWorkService.getlist(deviceWorkDto);
		map.addAttribute(Constraints.RESULT_ROW, outServerDtos);
		return map;
	}
}
