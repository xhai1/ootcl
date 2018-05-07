package com.zeng.ocs.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zeng.ocs.po.ActiveUser;
import com.zeng.ocs.po.User;
import com.zeng.ocs.service.UserService;
/**
 * 自定义的realm，注入到shiro
 * 用户的认证和授权
 * @author cxs
 *
 */
public class CustomRealm extends AuthorizingRealm{
	
	
	//注入service
	@Autowired
	private UserService userService;

	// 设置realm的名称
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}
	
	
	/**
	 * 认证的方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// token是用户输入的用户名和密码 
		// 第一步从token中取出用户名
		String username=(String)token.getPrincipal();

		// 第二步：根据用户输入的username从数据库查询
		User user = null;
		try {
			user = userService.findUserByUsername(username);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 如果查询不到返回null
		if(user==null){//
			return null;
		}
		// 从数据库查询到密码
		String password = user.getPassword();
		

		// 如果查询到返回认证信息AuthenticationInfo
		//activeUser就是用户身份信息
		ActiveUser activeUser = new ActiveUser();
		
		activeUser.setId(user.getId());
		activeUser.setUsername(user.getUsername());
		activeUser.setTruename(user.getTruename());
		activeUser.setCusid(user.getCusid());
		activeUser.setIsroot(user.getIsroot());
		

		//将activeUser设置simpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				activeUser, password, this.getName());
		
		return simpleAuthenticationInfo;
	}
	/**
	 * 授权的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
}
