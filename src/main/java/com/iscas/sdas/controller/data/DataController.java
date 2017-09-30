package com.iscas.sdas.controller.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

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
		 long  startTime=System.currentTimeMillis();
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest mutiRequest = (MultipartHttpServletRequest) request;
			Iterator it = mutiRequest.getFileNames();
			while (it.hasNext()) {
				String filename = (String) it.next();
				System.out.println(filename);
				MultipartFile file = mutiRequest.getFile(filename);
				if (file!=null) {
					String file1 = CommonUntils.generatePath() + file.getOriginalFilename();
					try {
						file.transferTo(new File(file1));
						map.addAttribute(Constraints.RESULT_SUCCESS, true);
					} catch (IllegalStateException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						map.addAttribute(Constraints.RESULT_SUCCESS, false);
					}
					
				}
			}
		}
		long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        try {
			executeShell();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	private void executeShell() throws IOException, InterruptedException{
		String shell = "D:\\shell\\shelltest.sh";
		
		Process process =  Runtime.getRuntime().exec("D:\\shell\\shelltest.sh zzz ccc");
		int exitValue = process.waitFor();
		if (0!=exitValue) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line=reader.readLine())!=null){
				System.out.println(line);
			}
		}
	}
}
