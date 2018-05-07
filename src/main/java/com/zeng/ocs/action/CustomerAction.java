package com.zeng.ocs.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.util.PageBean;
import com.zeng.ocs.util.PaginationUtil;
/**
 * 客户管理接口
 * @author cxs
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerAction {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/showCustomer")
	public ModelAndView showCustomer(String page){
		
		ModelAndView modelAndView=new ModelAndView();
		
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		int pageSize=Configuration.PAGE_NUM;
		
		PageBean<CustomerVo> pageBean=customerService.showCustomerByLimit(pageNo,pageSize);
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.setViewName("/WEB-INF/jsp/RightCustomerInfo.jsp");
		return modelAndView;
	}
	@RequestMapping("/searchCustomer")
	public ModelAndView searchCustomer(String cusName){
		ModelAndView mv =new ModelAndView("/WEB-INF/jsp/RightCustomerInfo.jsp");
		PageBean<CustomerVo> pageBean=customerService.showCustomerBySearch(cusName);
		mv.addObject("pageBean", pageBean);
		return mv;
	}
	
	@RequestMapping("/getCustomerForUpdate")
	public ModelAndView getCustomerForUpdate(Integer id , Integer page){
		
		ModelAndView modelAndView=new ModelAndView();
		
		CustomerVo customerVo=customerService.findCustomerById(id);
		modelAndView.addObject("customerVo", customerVo);
		modelAndView.addObject("page", page);
		modelAndView.setViewName("/WEB-INF/jsp/customerUpdate.jsp");
		
		return modelAndView;
	}
	@RequestMapping("/updateCustomer")
	public String updateCustomer(CustomerVo customerVo,Integer page){
		
		customerService.updateCustomer(customerVo);
		return "redirect:/customer/showCustomer.action?page="+page;
	}
	@RequestMapping("/deleteCustomer")
	public String deleteCustomer(Integer id){
		
		customerService.deleteCustomerById(id);
		return "redirect:/customer/showCustomer.action";
	}
	@RequestMapping("/customeraddJsp")
	public String customeraddJsp(){
		return "/WEB-INF/jsp/customeradd.jsp";
	}
	@RequestMapping("/deleteCustomers")
	public String deleteCustomers(HttpServletRequest request) throws CustomException{
		try{
			String items = request.getParameter("delitems");
			String[] strs = items.split(",");  
			List<Integer> ids = new ArrayList<Integer>();
			for (int i = 0; i < strs.length; i++) {  
				int ID = Integer.parseInt(strs[i]);  
				ids.add(ID);  
			}
			customerService.deleteCustomerByIds(ids);
		} catch (Exception e){
			throw new CustomException("删除失败！");
		}
		return "redirect:/customer/showCustomer.action";
	}
	@RequestMapping("/addCustomer")
	@ResponseBody
	public String addCustomer(CustomerVo customerVo){
		Random ra =new Random();
		System.out.println(ra.nextInt(10)+1);
		customerVo.setCusid(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"."+(ra.nextInt(10)+1)+""+(ra.nextInt(10)+1)+""+(ra.nextInt(10)+1));
		customerService.addCustomer(customerVo);
		return PaginationUtil.Prompt("添加成功!",null);
//		return "redirect:/customer/showCustomer.action";
	}
	@RequestMapping("/findByCusid")
	public String findByCusid(HttpServletResponse response,String cusname) throws IOException{
		CustomerVo customerVo=customerService.findCustomerByCusName(cusname);
		response.setContentType("text/html;charset=UTF-8");
		if(customerVo!=null){
			response.getWriter().write("<font color='red'>已存在客户</font>");
		}else {
			response.getWriter().write("");
		}
		return null;
	}
}
