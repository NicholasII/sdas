package com.iscas.sdas.controller.arithmetic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/arithmetic")
public class DisplayController {

	@RequestMapping("/display")
	public ModelAndView getdispplay(){
		return new ModelAndView("/arithmetic/list");
	}
}
