package com.zeng.ocs.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeng.ocs.po.User;
import com.zeng.ocs.service.UserService;
/**
 * 退出登录
 * @author cxs
 *
 */
@Controller
public class LogoutAction {
	@Autowired
	protected HttpServletRequest request;
	
	@RequestMapping("/logout")
	public String logout(){
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		ServletContext application = request.getSession().getServletContext();
		Map<String, String> LOGIN_USER_MAP =  (Map<String, String>) application.getAttribute("LOGIN_USER_MAP");
		User user = (User) session.getAttribute("user");
		if(LOGIN_USER_MAP != null && LOGIN_USER_MAP.containsKey(user.getCusid()) )
			LOGIN_USER_MAP.remove(user.getCusid());
		application.setAttribute("LOGIN_USER_MAP", LOGIN_USER_MAP);
		//session失效
		session.invalidate();
		return "redirect:/index.action";
	}
}
