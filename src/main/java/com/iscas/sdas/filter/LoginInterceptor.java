package com.iscas.sdas.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.iscas.sdas.dto.sys.UserDto;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 //获取请求的URL  
        String url = request.getRequestURI();  
        //获取Session  
        HttpSession session = request.getSession();  
        //除登录页和load=true，其它的URL都进行拦截控制  
        if(url.equals("/sdas/")){  
            return true;  
        } else if (url.equals("/sdas/main")) {
        	String load = (String) session.getAttribute("status");
        	if ("isload".equals(load)) {
				return true; 
			}   	
		} 
        
        UserDto userInfo = (UserDto)session.getAttribute("userInfo");          
        if(userInfo != null){  
            return true;  
        }  
        //不符合条件的，跳转到登录界面  
        response.sendRedirect(request.getContextPath());
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
