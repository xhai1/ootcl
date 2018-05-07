package com.zeng.ocs.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeng.ocs.exception.CustomException;
import com.zeng.ocs.po.User;
import com.zeng.ocs.service.UserService;
import com.zeng.ocs.util.MD5;

/**
 * 认证登录
 * @author cxs
 *
 */
@Controller
public class LoginAction {
	@Autowired
	protected HttpServletRequest request;
	
	/*public ModelAndView login(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView=new ModelAndView();
		//如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		//根据shiro返回的异常类路径判断，抛出指定异常信息
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				//最终会抛给异常处理器
				throw new CustomException("账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				throw new CustomException("用户名/密码错误");
			}else {
				throw new Exception();//最终在异常处理器生成未知错误
			}
		}
		//此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		//登陆失败还到login页面
		modelAndView.setViewName("/WEB-INF/jsp/login.jsp");
		return modelAndView;
	}*/
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(HttpSession session,String username,String password) throws Exception{
		System.out.println("request.getSession().getId()-"+request.getSession().getId());
		String sessionId = request.getSession().getId();
		ServletContext application = request.getSession().getServletContext();
		Map<String, String> LOGIN_USER_MAP =  (Map<String, String>) application.getAttribute("LOGIN_USER_MAP");
		if(LOGIN_USER_MAP == null)
			LOGIN_USER_MAP = new HashMap<String, String>();  
		
//		application.setAttribute("user", username);
		Boolean isloginexists = false;
		User user=null;
		user=userService.login(username, password);
		if(user==null){
			throw new CustomException("账号不存在");
		}
		
		if(!new MD5().getMD5ofStr(password).equals(user.getPassword())){
			throw new CustomException("密码错误");
		}
		
		for (String userid  : LOGIN_USER_MAP.keySet()) {  
			//当集合中没有此用户和用户登录session时，未登录，允许登录！
		    if (!userid.equals(user.getCusid()) && !LOGIN_USER_MAP.containsValue(sessionId)) {  
		        continue;  
		    }  
		    //已存在用户，但从其他浏览器登录，登录失败！
		    if(userid.equals(user.getCusid())&&!LOGIN_USER_MAP.containsValue(sessionId)){  
		    	request.getSession().invalidate();  
		    }  
		    isloginexists = true;  
		    break;  
		}  
		
		if (isloginexists) {
//				request.getSession(false).invalidate();
				throw new CustomException("您已在别处登录...");
		} else {

			LOGIN_USER_MAP.put(user.getCusid(), sessionId);
			application.setAttribute("LOGIN_USER_MAP", LOGIN_USER_MAP);
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(1800);
			return "/WEB-INF/jsp/main.jsp";
		}	  
		
//		return "redirect:/monthReport/showMonthReportDetail.action?page=1";
	}
}
