package com.zeng.ocs.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 异常解析器
 * @author cxs
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		CustomException customException=null;
		if(ex instanceof CustomException){
			customException=(CustomException)ex;
		}else{
			customException=new CustomException("未知错误");
		}
		//错误信息
		String message=customException.getMessage();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("msg",message);
		modelAndView.setViewName("/WEB-INF/jsp/error.jsp");
		return modelAndView;
	}
	
}
