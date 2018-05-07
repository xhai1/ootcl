package com.zeng.ocs.action;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 认证成功之后跳转的页面
 * @author cxs
 *
 */
@Controller
public class IndexAction {
	
	@RequestMapping("/index")
	public ModelAndView index(HttpSession session){
		ModelAndView modelAndView=new ModelAndView();
		//从shiro的session中取activeUser
		/*Subject subject = SecurityUtils.getSubject();
		//取身份信息
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		if(activeUser.getIsroot()==1){
			modelAndView.addObject("isroot","yes");
			modelAndView.addObject("username",activeUser.getUsername());
		}else if(activeUser.getIsroot()==0){
			modelAndView.addObject("isroot","no");
			modelAndView.addObject("cusid",activeUser.getCusid());
			modelAndView.addObject("username",activeUser.getUsername());
		}else if(activeUser.getIsroot()==2){
			modelAndView.addObject("isroot","administrator");
			modelAndView.addObject("cusid",activeUser.getCusid());
			modelAndView.addObject("username",activeUser.getUsername());
		}
		session.setAttribute("activeUser", activeUser);*/
		modelAndView.setViewName("/WEB-INF/jsp/main.jsp");
		return modelAndView;
	}
}
