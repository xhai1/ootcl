package session;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.zeng.ocs.po.User;

public class SessionHelp implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		try {
			// 监听session的失效和销毁
			User user = (User) se.getSession().getAttribute("user");
			ServletContext application = se.getSession().getServletContext();
			Map<String, String> LOGIN_USER_MAP = (Map<String, String>) application.getAttribute("LOGIN_USER_MAP");
			if (LOGIN_USER_MAP.containsKey(user.getCusid()))
				LOGIN_USER_MAP.remove(user.getCusid());
			application.setAttribute("LOGIN_USER_MAP", LOGIN_USER_MAP);
			System.out.println("session:" + se.getSession().getId() + "已失效");
			System.out.println("user:" +user.getId()+":"+ user.getCusid()+":"+user.getUsername() + "已清除");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
