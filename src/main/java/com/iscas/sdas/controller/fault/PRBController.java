package com.iscas.sdas.controller.fault;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.dto.data.PRBBean;
import com.iscas.sdas.service.fault.FaultService;
import com.iscas.sdas.util.CommonUntils;
import com.iscas.sdas.util.Constraints;

@Controller
@RequestMapping("/fault")
public class PRBController {
	@Autowired
	FaultService faultService;

	@RequestMapping("/page")
	public ModelAndView page(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView("fault/prb");
		String cellname =new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		//String cellname = request.getParameter("name");
		if (!CommonUntils.isempty(cellname)) {
			modelAndView.addObject("cellname", cellname);
		}
		return modelAndView;
	}
	
	@RequestMapping("/prb")
	@ResponseBody
	public ModelMap getprb(HttpServletRequest request){
		ModelMap map = new ModelMap();
		List<PRBBean> prbBeans = faultService.getprbs();
		if (prbBeans!=null) {
			map.addAttribute(Constraints.RESULT_ROW, prbBeans);
			map.addAttribute(Constraints.RESULT_SUCCESS, true);
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS,false);
		}
		return map;
	}
	@RequestMapping("/getprbothers")
	@ResponseBody
	public ModelMap getprbothers(HttpServletRequest request){
		ModelMap map = new ModelMap();
		List<PRBBean> prbothers = faultService.getprbothers();
		if (prbothers!=null) {
			map.addAttribute(Constraints.RESULT_ROW, prbothers);
			map.addAttribute(Constraints.RESULT_SUCCESS, true);
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS,false);
		}
		return map;
	}
	@RequestMapping("/switch")
	@ResponseBody
	public ModelMap getswitch(HttpServletRequest request){
		ModelMap map = new ModelMap();
		List<PRBBean> switchBeans = faultService.getswitch();
		if (switchBeans!=null) {
			map.addAttribute(Constraints.RESULT_ROW, switchBeans);
			map.addAttribute(Constraints.RESULT_SUCCESS, true);
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS,false);
		}
		return map;
	}
	@RequestMapping("/getswitchothers")
	@ResponseBody
	public ModelMap getswitchothers(HttpServletRequest request){
		ModelMap map = new ModelMap();
		List<PRBBean> switchothers = faultService.getswitchothers();
		if (switchothers!=null) {
			map.addAttribute(Constraints.RESULT_ROW, switchothers);
			map.addAttribute(Constraints.RESULT_SUCCESS, true);
		}else {
			map.addAttribute(Constraints.RESULT_SUCCESS,false);
		}
		return map;
	}
}
