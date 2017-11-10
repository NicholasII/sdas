package com.iscas.sdas.controller.log;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iscas.sdas.common.PageDto;
import com.iscas.sdas.dto.FileLogDto;
import com.iscas.sdas.dto.FileLogDto;
import com.iscas.sdas.service.log.FileLogService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/log")
public class LogController {
	@Autowired
	FileLogService fileLogService;
	
	@RequestMapping("/file/page")
	public ModelAndView fileLogPage(HttpServletRequest request){
		String type=request.getParameter("type");
		ModelAndView mv=new ModelAndView("/data/log");
		if (!CommonUntils.isempty(type)) {
			mv.addObject("type", type);
		}
		else{
			mv.addObject("type", "");
		}
		return mv;
	}
	
	@RequestMapping("/file/list")
	@ResponseBody
	public ModelMap fileLog(@RequestParam(value = "currpage", required = true, defaultValue = "1") String num,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") String size,
			HttpServletRequest request) {
		ModelMap map = new ModelMap();
		FileLogDto dto = new FileLogDto();
		String type=request.getParameter("type");
		if (!CommonUntils.isempty(type)) {
			dto.setType(type);
		}
		int pageNum = Integer.parseInt(num);
		int pageSize = Integer.parseInt(size);
		PageHelper.startPage(pageNum, pageSize);
		List<FileLogDto> FileLogDtos =  fileLogService.all(dto);	
		PageInfo<FileLogDto> pageInfo = new PageInfo<>(FileLogDtos);
		List<FileLogDto> rows = new ArrayList<>();
		for (int i = 0; i < FileLogDtos.size(); i++) {
			FileLogDto fdto = FileLogDtos.get(i);
			rows.add(fdto);
		}
		PageDto<FileLogDto> pageDto = new PageDto<>();
		pageDto.setTotal(pageInfo.getTotal());
		pageDto.setRows(rows);
		map.addAttribute(Constraints.RESULT_ROW, pageDto);
		return map;
	}
}
