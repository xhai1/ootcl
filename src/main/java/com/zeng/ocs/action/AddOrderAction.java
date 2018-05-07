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
import com.zeng.ocs.po.DtChgOrderVo;
import com.zeng.ocs.po.User;
import com.zeng.ocs.service.AddOrderService;
import com.zeng.ocs.service.ComputeMoneyService;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.ProductService;
import com.zeng.ocs.util.ExcelUtil;
import com.zeng.ocs.util.NetUtil;
import com.zeng.ocs.util.PageBean;
/**
 * 补单明细action
 * @author cxs
 *
 */
@Controller
@RequestMapping("/addOrder")
public class AddOrderAction {
	
	@Autowired
	private AddOrderService addOrderService;
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/showAddOrder")
	public ModelAndView showAddOrder(HttpSession session,String page,String cusname,String begintime,String endtime,String cusid,String typeid,String type){
		ModelAndView modelAndView=new ModelAndView();
		
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		int pageSize=Configuration.PAGE_NUM;
		
		DtChgOrderVo dtChgOrderVo=new DtChgOrderVo();
		dtChgOrderVo.setCusname(cusname);
		dtChgOrderVo.setBegintime(begintime);
		dtChgOrderVo.setEndtime(endtime);
		dtChgOrderVo.setMode("补单");
		dtChgOrderVo.setCusid(cusid);
		dtChgOrderVo.setTypeid(typeid);
		dtChgOrderVo.setType(type);
		
		PageBean<DtChgOrderVo> pageBean=addOrderService.showDtChgOrderByLimit(pageNo,pageSize,dtChgOrderVo);
		
		//这里使用session
		List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
		if(customerVoList==null||customerVoList.size()<=50){
			customerVoList=customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}
		
		
		
		
		modelAndView.addObject("customerVoList", customerVoList);
		
		
		
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.addObject("cusname", cusname);
		modelAndView.addObject("begintime", begintime);
		modelAndView.addObject("endtime", endtime);
		modelAndView.addObject("cusid", cusid);
		modelAndView.addObject("typeid", typeid);
		modelAndView.addObject("type", type);
		
		
		List<CusTypeVo> cusTypeList = new ArrayList<>();
		if(cusid == null || cusid ==""){
			cusTypeList = productService.findAllCustype();
		}else{
			cusTypeList = productService.findACustype(cusid);
		}
		
		modelAndView.addObject("cusTypeList", cusTypeList);
		
		modelAndView.setViewName("/WEB-INF/jsp/RightAddOrderDetail.jsp");
		return modelAndView;
	}
	@RequestMapping("/exportAddOrder")
	public void exportAddOrder(HttpSession session,HttpServletRequest request,HttpServletResponse response,String cusname,String begintime,String endtime,String cusid,String typeid,String type,String page) throws IOException{
		
		DtChgOrderVo dtChgOrderVo=new DtChgOrderVo();
		dtChgOrderVo.setCusname(cusname);
		dtChgOrderVo.setBegintime(begintime);
		dtChgOrderVo.setEndtime(endtime);
		dtChgOrderVo.setMode("补单");
		dtChgOrderVo.setCusid(cusid);
		dtChgOrderVo.setTypeid(typeid);
		dtChgOrderVo.setType(type);
		
		List<DtChgOrderVo> dtChgOrderVoList=new ArrayList<DtChgOrderVo>();
		dtChgOrderVoList=addOrderService.findDtChgOrder(session,dtChgOrderVo,page);
		
		User user=(User)session.getAttribute("user");
		if(user!=null&&user.getIsroot()==0){
			for(int i=0;i<dtChgOrderVoList.size();i++){
				String acceptid=dtChgOrderVoList.get(i).getAcceptid();
				String caseorder=dtChgOrderVoList.get(i).getCaseorder();
				dtChgOrderVoList.get(i).setAcceptid(acceptid.substring(0, 7)+"*****"+acceptid.substring(acceptid.length()-4, acceptid.length()));
				dtChgOrderVoList.get(i).setCaseorder(caseorder.substring(0, 9)+"*****"+caseorder.substring(caseorder.length()-4, caseorder.length()));
			}
		}
		
		response.setHeader("Content-Disposition", "attachment;filename=" + NetUtil.getFileName(request, "补单明细表.xlsx"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelUtil<DtChgOrderVo> e=new ExcelUtil<DtChgOrderVo>(DtChgOrderVo.class);
        
        
        e.exportExcel(dtChgOrderVoList,"补单明细表",outputStream);
        if(outputStream != null){
			 outputStream.close();
		}
	}
	
	//获取产品大类id
	@Autowired
	private ComputeMoneyService computeMoneyService;
	
	@RequestMapping(value = "/dGetTypeID",method = RequestMethod.POST)
	@ResponseBody
	public String dGetTypeID(Customer customer){
		if( customer.getCusid().equals("cusidall") ){
			 customer.setCusid(null);
		}
		String response = "<option value='all' >请选择产品大类</option>";
		List<CusType> cusTypes = computeMoneyService.getCustype(customer);
		for(CusType ct: cusTypes){
			response += "<option value='"+ct.getTypeid()+"' >"+ct.getTypetimeing()+"</option>";
		}
		return response;
	}
}
