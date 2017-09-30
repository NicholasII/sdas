package com.iscas.sdas.controller.work;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/work")
public class WorkController {

	@RequestMapping("/capacity")
	public ModelAndView capacity(){
		return new ModelAndView("work/capacity");
	}
	
	@RequestMapping("/device")
	public ModelAndView device(){
		return new ModelAndView("/work/device");
	}
	
	@RequestMapping("/indexinfo")
	public ModelAndView indexinfo(){
		return new ModelAndView("/work/indexinfo");
	}
	
	@RequestMapping("/outservice")
	public ModelAndView outservice(){
		return new ModelAndView("/work/outservice");
	}
}
