package com.zeng.ocs.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.configuration.Configuration;
import com.zeng.ocs.po.CusType;
import com.zeng.ocs.po.CusTypeVo;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.ProductService;
import com.zeng.ocs.util.ExcelUtil;
import com.zeng.ocs.util.NetUtil;
import com.zeng.ocs.util.PageBean;
/**
 * 产品大类控制类
 * @author cxs
 *
 */
@Controller
@RequestMapping("/product")
public class ProductAction {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/showProductClass")
	public ModelAndView showProductClass(HttpSession session,String page,String typeid,String cusid,String brand,String business){
		
		ModelAndView modelAndView=new ModelAndView();
		
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		int pageSize=Configuration.PAGE_NUM;
		
		CusTypeVo cusTypeVo=new CusTypeVo();
		cusTypeVo.setTypeid(typeid);
		cusTypeVo.setCusid(cusid);
		cusTypeVo.setBrand(brand);
		cusTypeVo.setBusiness(business);
		
		//这里使用session
				List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
				if(customerVoList==null||customerVoList.size()<=50){
					customerVoList=customerService.findAllCustomer();
					session.setAttribute("customerVoList", customerVoList);
				}
		
		PageBean<CusTypeVo> pageBean=productService.showProductClassByLimit(pageNo,pageSize,cusTypeVo);
		List<CusTypeVo> cusTypeList = new ArrayList<>();
		if(cusid == null || cusid ==""){
			cusTypeList = productService.findAllCustype();
		}else{
			cusTypeList = productService.findACustype(cusid);
		}
		
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.addObject("typeid", typeid);
		modelAndView.addObject("cusid", cusid);
		modelAndView.addObject("brand", brand);
		modelAndView.addObject("business", business);
		modelAndView.addObject("cusTypeList", cusTypeList);
		modelAndView.setViewName("/WEB-INF/jsp/RightProBigClass.jsp");
		return modelAndView;
	}
	@RequestMapping("/exportProductClass")
	public void exportAddOrder(HttpServletRequest request,HttpServletResponse response,String typeid,String cusid,String brand,String business) throws IOException{
		
		CusTypeVo cusTypeVo=new CusTypeVo();
		cusTypeVo.setType(typeid);
		cusTypeVo.setCusname(cusid);
		cusTypeVo.setBrand(brand);
		cusTypeVo.setBusiness(business);		
		
		List<CusTypeVo> productClassList=new ArrayList<CusTypeVo>();
		productClassList=productService.findProductClass(cusTypeVo);
		
		response.setHeader("Content-Disposition", "attachment;filename=" + NetUtil.getFileName(request, "产品大类及所属公司一览表.xlsx"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        ServletOutputStream outputStream = response.getOutputStream();
        productService.exportProductClass(productClassList,"产品大类及所属公司一览表",outputStream);
	}
}
