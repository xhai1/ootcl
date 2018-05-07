package com.zeng.ocs.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.configuration.Configuration;
import com.zeng.ocs.po.UserVo;
import com.zeng.ocs.service.UserService;
import com.zeng.ocs.util.PageBean;
/**
 * 分配权限控制
 * @author cxs
 *
 */
@Controller
@RequestMapping("/permission")
public class PermissionAction {
	
	@Autowired
	protected HttpServletRequest request;
	 
	@Autowired
	private UserService userService;
	
	@RequestMapping("/showAllUserInfo")
	public ModelAndView showAllUserInfo(String page,String username){
		ModelAndView modelAndView=new ModelAndView();
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		int pageSize=Configuration.PAGE_NUM;
		
		UserVo userVo=new UserVo();
		userVo.setUsername(username);
		
		PageBean<UserVo> pageBean=userService.showUserByLimit(pageNo,pageSize,userVo);
		
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.addObject("username", username);
		modelAndView.setViewName("/WEB-INF/jsp/UserPermissionInfo.jsp");
		return modelAndView;
	}
	@RequestMapping("/getUserForUpdatePermission")
	public ModelAndView getUserForUpdatePermission(Integer id){
		ModelAndView modelAndView=new ModelAndView();
		UserVo userVo=userService.getUserById(id);
		modelAndView.addObject("userVo", userVo);
		modelAndView.setViewName("/WEB-INF/jsp/UpdatePermissionInfo.jsp");
		return modelAndView;
	}
	@RequestMapping("/updatePermissionForUser")
	public String updatePermissionForUser(UserVo userVo){
		userService.updatePermissionForUser(userVo);
		return "redirect:/permission/showAllUserInfo.action";
	}
	
	/**
	 * @author  Jianghai Yang
	 * @date	2018年5月4日下午12:15:42
	 */
	@RequestMapping("/acl")
	public String acl(){
		Map map = System.getenv();  
		Iterator it = map.entrySet().iterator();  
		while(it.hasNext())  
		{  
		    @SuppressWarnings("rawtypes")
			Entry entry = (Entry)it.next();  
		    System.out.print(entry.getKey()+"=");  
		    System.out.println(entry.getValue());  
		} 
		ServletContext application = request.getSession().getServletContext();
		ApplicationContext appc = (ApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		System.out.println(appc);
//		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext(),attrName);
		return "/WEB-INF/jsp/acl.jsp";
	}
}
