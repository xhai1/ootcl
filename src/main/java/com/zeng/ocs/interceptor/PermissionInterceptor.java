package com.zeng.ocs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.po.User;
/**
 * 权限信息拦截，只放行拥有权限的用户和匿名访问的页面
 * @author cxs
 *
 */

public class PermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//得到请求的url
		String url = request.getRequestURI();
		if(url.indexOf("login.action")>=0){
			//如果进行登陆提交，放行
			return true;
		}
		if(url.indexOf("index.action")>=0){
			//如果进行登陆提交，放行
			return true;
		}
		if(url.indexOf("logout.action")>=0){
			//如果进行登陆提交，放行
			return true;
		}
		//判断是否是公开 地址
		//实际开发中需要公开 地址配置在配置文件中
		//从配置中取逆名访问url
		
		/*List<String> open_urls = ResourcesUtil.gekeyList("anonymousURL");*/
		//遍历公开 地址，如果是公开 地址则放行
		/*for(String open_url:open_urls){
			if(url.indexOf(open_url)>=0){
				//如果是公开 地址则放行
				return true;
			}
		}*/
		
		//从配置文件中获取公共访问地址
		/*List<String> common_urls = ResourcesUtil.gekeyList("commonURL");*/
		//遍历公用 地址，如果是公用 地址则放行
		/*for(String common_url:common_urls){
			if(url.indexOf(common_url)>=0){
				//如果是公开 地址则放行
				return true;
			}
		}*/
		
		//获取session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user.getIsroot()==2){
			//超级管理员
			return true;
		}
		if(user.getIsroot()==1){
			if(url.indexOf("permission")>0){
				request.getRequestDispatcher("/WEB-INF/jsp/refuse.jsp").forward(request, response);
				return false;
			}
			if(url.indexOf("addUser.action")>0){
				if(request.getParameter("cusid")==""||request.getParameter("cusid")==null){
					request.getRequestDispatcher("/WEB-INF/jsp/refuse.jsp").forward(request, response);
					return false;
				}
			}
			//内部用户
			return true;
		}
		
		if(user.getIsroot() == 0){
			if(url.indexOf("staForCompany.action")>0) 
			return true;
			if(url.indexOf("SearchByCondition.action")>0) 
				return true;
			if(url.indexOf("exportByCondition.action")>0) 
				return true;
			if(url.indexOf("showALlMonthReport.action")>0) 
				return true;
			
			if(request.getParameter("cusid")==null){
				request.getRequestDispatcher("/WEB-INF/jsp/refuse.jsp").forward(request, response);
				return false;
			} 
			if(!request.getParameter("cusid").equals(user.getCusid())){
				System.out.println(request.getParameter("cusid"));
				request.getRequestDispatcher("/WEB-INF/jsp/refuse.jsp").forward(request, response);
				return false;
			}
			if(request.getParameter("cusid").equals(user.getCusid())){
				return true;
			}
		}
		
		
		
		
		//从session中取权限范围的url
		/*List<SysPermission> permissions = activeUser.getPermissions();
		for(SysPermission sysPermission:permissions){
			//权限的url
			String permission_url = sysPermission.getUrl();
			if(url.indexOf(permission_url)>=0){
				//如果是权限的url 地址则放行
				return true;
			}
		}*/
		
		//执行到这里拦截，跳转到无权访问的提示页面
		request.getRequestDispatcher("/WEB-INF/jsp/refuse.jsp").forward(request, response);
		
		//如果返回false表示拦截不继续执行handler，如果返回true表示放行
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
