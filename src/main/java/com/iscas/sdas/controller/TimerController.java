package com.iscas.sdas.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/timer")
public class TimerController {
	@RequestMapping("/task")
	public ModelAndView getdispplay(){
		return new ModelAndView("/task/task");
	}
}
