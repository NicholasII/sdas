package com.iscas.sdas.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.util.CommonUntils;

@Controller
@RequestMapping("/timer")
public class TimerController {
	@RequestMapping("/task")
	public ModelAndView getdispplay(){
		return new ModelAndView("/task/task");
	}
	@RequestMapping("/section")
	public ModelAndView section(HttpServletRequest request){
		String  source=request.getParameter("source");
		String  output=request.getParameter("output");
		String  ifdel=request.getParameter("ifdel");
		Boolean ifdeleteOut=false;
		if(!CommonUntils.isempty(ifdel)){
			if(ifdel.equals("1")){
				ifdeleteOut=true;
			}else{
				ifdeleteOut=false;
			}
		}
		if(!CommonUntils.isempty(source)&&!CommonUntils.isempty(output)&&!CommonUntils.isempty(ifdel)){
		}
		return new ModelAndView("/task/task");
	}
}
