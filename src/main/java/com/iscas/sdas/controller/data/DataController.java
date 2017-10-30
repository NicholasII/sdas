package com.iscas.sdas.controller.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;



@Controller
@RequestMapping("/data")
public class DataController {

	@RequestMapping("/online")
	public ModelAndView online(){
		return new ModelAndView("/data/online");
	}
	@RequestMapping("/offline")
	public ModelAndView offline(){
		return new ModelAndView("/data/offline");
	}
	@RequestMapping("/upload")
	@ResponseBody
	public ModelMap upload(HttpServletRequest request){
		ModelMap map = new ModelMap();
		List<String> files = CommonUntils.MultipleFilesUpload(request);
		if (files!=null && files.size()>0) {
			map.addAttribute(Constraints.RESULT_ROW, files);
			map.addAttribute(Constraints.RESULT_SUCCESS, true);
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS, false);
		}
        try {
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	

}
