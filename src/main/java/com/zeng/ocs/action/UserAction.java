package com.zeng.ocs.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.configuration.Configuration;
import com.zeng.ocs.exception.CustomException;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.User;
import com.zeng.ocs.po.UserVo;
import com.zeng.ocs.service.UserService;
import com.zeng.ocs.util.PageBean;
import com.zeng.ocs.util.PaginationUtil;

/**
 * 用户控制类
 * @author cxs
 *
 */
@Controller
@RequestMapping("/user")
public class UserAction {
	
	@Autowired
	private UserService userService;
	
/*	@RequestMapping("/login")
	public String login(String username,String password){
		if("admin".equals(username)&&"123".equals(password)){
			return "/WEB-INF/jsp/main.jsp";
		}
		return "/WEB-INF/jsp/login.jsp";
	}*/
	@RequestMapping("/showUser")
	public ModelAndView showUser(String page,String username,String truename){
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
		userVo.setTruename(truename);
		
		PageBean<UserVo> pageBean=userService.showUserByLimit(pageNo,pageSize,userVo);
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.addObject("username", username);
		modelAndView.addObject("truename", truename);
		modelAndView.setViewName("/WEB-INF/jsp/RightUserInfo.jsp");
		return modelAndView;
	}
	@RequestMapping("/getUserByUsername")
	public ModelAndView getUserByUsername(String username){
		ModelAndView modelAndView=new ModelAndView();
		
		UserVo userVo=userService.getUserByUsername(username);
		modelAndView.addObject("userVo", userVo);
		modelAndView.setViewName("/WEB-INF/jsp/userbrowse.jsp");
		return modelAndView;
	}
	@RequestMapping("/getUserForUpdate")
	public ModelAndView getUserForUpdate(Integer id){
		ModelAndView modelAndView=new ModelAndView();
		
		UserVo userVo=userService.getUserById(id);
		// TODO 查询所有公司
		List<CustomerVo> customerList=userService.findAllCustomer();
		modelAndView.addObject("userVo", userVo);
		modelAndView.addObject("customerList", customerList);
		modelAndView.setViewName("/WEB-INF/jsp/userupdate.jsp");
		return modelAndView;
	}
	@RequestMapping("/updateUser")
	public String updateUser(UserVo userVo){
		userService.updateUser(userVo);
		return "redirect:/user/showUser.action";
	}
	@RequestMapping("/useraddJsp")
	public ModelAndView useraddJsp(){
		ModelAndView modelAndView=new ModelAndView();
		List<CustomerVo> customerList=userService.findAllCustomer();
		modelAndView.addObject("customerList", customerList);
		modelAndView.setViewName("/WEB-INF/jsp/useradd.jsp");
		return modelAndView;
	}
	@RequestMapping("/addUser")
	public  @ResponseBody String addUser(UserVo userVo){
		userService.addUser(userVo);
//		return "redirect:/user/showUser.action";
		return PaginationUtil.Prompt("添加成功", null);
	}
	@RequestMapping("/deleteUser")
	public String addUser(Integer id){
		userService.deleteUserById(id);
		return "redirect:/user/showUser.action";
	}
	@RequestMapping("/deleteUsers")
	public String deleteUsers(HttpServletRequest request) throws CustomException{
		try{
			String items = request.getParameter("delitems");
			String[] strs = items.split(",");  
			List<Integer> ids = new ArrayList<Integer>();
			for (int i = 0; i < strs.length; i++) {  
				int ID = Integer.parseInt(strs[i]);  
				ids.add(ID);  
			}
			userService.deleteUserByIds(ids);
		} catch (Exception e){
			throw new CustomException("删除失败！");
		}
		return "redirect:/user/showUser.action";
	}
	
	@RequestMapping("/getUserById")
	public ModelAndView getUserById(Integer id){
		ModelAndView modelAndView=new ModelAndView();
		UserVo userVo=userService.getUserById(id);
		modelAndView.addObject("userVo", userVo);
		modelAndView.setViewName("/WEB-INF/jsp/userbrowse.jsp");
		return modelAndView;
	}
	@RequestMapping("/findByUsername")
	public String findByName(HttpServletResponse response,String username) throws IOException{
		User user=userService.findUserByUsername(username);
		response.setContentType("text/html;charset=UTF-8");
		if(user!=null){
			response.getWriter().write("<font color='red'>已存在用户</font>");
		}else {
			response.getWriter().write("");
		}
		return null;
	}
}
