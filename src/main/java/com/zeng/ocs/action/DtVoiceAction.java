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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.configuration.Configuration;
import com.zeng.ocs.po.CusType;
import com.zeng.ocs.po.CusTypeVo;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.DtVoiceVo;
import com.zeng.ocs.service.ComputeMoneyService;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.DtVoiceService;
import com.zeng.ocs.service.ProductService;
import com.zeng.ocs.util.ExcelUtil;
import com.zeng.ocs.util.NetUtil;
import com.zeng.ocs.util.PageBean;

	
	
/**
 * 语音明细的action
 * @author cxs
 *
 */

@Controller
@RequestMapping("/dtvoice")
public class DtVoiceAction {
	
	@Autowired
	private DtVoiceService dtVoiceService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ComputeMoneyService computeMoneyService;
	
	/**
	 * 使用自己编写的pagebean分页
	 */
	/*
	@RequestMapping("/showDtVoice")
	public ModelAndView showDtVoice(String page){
		ModelAndView modelAndView=new ModelAndView();
		
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		PageBean<DtVoiceVo> pageBean=dtVoiceService.showDtVoiceByLimit(pageNo);
		
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.setViewName("/WEB-INF/jsp/RightPhoneticDetail.jsp");
		return modelAndView;
	}
	*/
	/**
	 * bu使用mybatis的分页插件pageHelper
	 * @param page
	 * @return
	 */
	@RequestMapping("/showDtVoice")
	public ModelAndView showDtVoice(HttpSession session,String page,String calltype,String callingno,String calledno,String begintime,String endtime,String cusid,String cusname,String typedemand,String type,String typeid){
		
		ModelAndView modelAndView=new ModelAndView();
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		int pageSize=Configuration.PAGE_NUM;
		
		
		
		DtVoiceVo dtVoiceVo=new DtVoiceVo();
		dtVoiceVo.setNtype(calltype);
		dtVoiceVo.setCallingno(callingno);
		dtVoiceVo.setCalledno(calledno);
		dtVoiceVo.setBegintime(begintime);
		dtVoiceVo.setEndtime(endtime);
		dtVoiceVo.setCusid(cusid);
		dtVoiceVo.setCusName(cusname);
		dtVoiceVo.setTypedemand(typedemand);
		dtVoiceVo.setTypeid(typeid);
		
		PageBean<DtVoiceVo> pageBean=dtVoiceService.showDtVoiceByLimit(pageNo,pageSize,dtVoiceVo);
		
		List<CusTypeVo> cusTypeList = new ArrayList<>();
		if(cusid == null || cusid ==""){
			cusTypeList = productService.findAllCustype();
		}else{
			cusTypeList = productService.findACustype(cusid);
		}
		modelAndView.addObject("cusTypeList", cusTypeList);
		
		//这里使用session
		List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
		if(customerVoList==null||customerVoList.size()<=50){
			customerVoList=customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}
		
		
		
		
		modelAndView.addObject("customerVoList", customerVoList);
		
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.addObject("calltype", calltype);
		modelAndView.addObject("callingno", callingno);
		modelAndView.addObject("calledno", calledno);
		modelAndView.addObject("begintime", begintime);
		modelAndView.addObject("endtime", endtime);
		modelAndView.addObject("cusid", cusid);
		modelAndView.addObject("cusname", cusname);
		modelAndView.addObject("typedemand", typedemand);
		modelAndView.addObject("type", type);
		modelAndView.addObject("typeid", typeid);
		
		modelAndView.setViewName("/WEB-INF/jsp/RightPhoneticDetail.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/exportDtVoice")
	public void exportDtVoice(HttpSession session,HttpServletRequest request,HttpServletResponse response,String calltype,String callingno,String calledno,String begintime,String endtime,String cusid,String cusname,String typedemand,String type,String page,String typeid) throws IOException{
		
		
		DtVoiceVo dtVoiceVo=new DtVoiceVo();
		dtVoiceVo.setNtype(calltype);
		dtVoiceVo.setCallingno(callingno);
		dtVoiceVo.setCalledno(calledno);
		dtVoiceVo.setBegintime(begintime);
		dtVoiceVo.setEndtime(endtime);
		dtVoiceVo.setCusid(cusid);
		dtVoiceVo.setCusName(cusname);
		dtVoiceVo.setTypedemand(typedemand);
//		dtVoiceVo.setType(type);
		dtVoiceVo.setTypeid(typeid);
		List<DtVoiceVo> dtVoiceVoList=new ArrayList<DtVoiceVo>();
		dtVoiceVoList=dtVoiceService.findDtVoice(session,dtVoiceVo,page);
		
		response.setHeader("Content-Disposition", "attachment;filename=" + NetUtil.getFileName(request, "语音明细表.xlsx"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelUtil<DtVoiceVo> e=new ExcelUtil<DtVoiceVo>(DtVoiceVo.class);
        e.exportExcel(dtVoiceVoList,"语音明细表",outputStream);
        if(outputStream != null){
			 outputStream.close();
		}
	}
	/*@RequestMapping("/findCustypeByTypeId")
	public String findCustypeByCusid(Model model,String cusid){
		List<CusTypeVo> cusTypeList=productService.findAllCustype();
		model.addAttribute("cusTypeList", cusTypeList);
		return "/WEB-INF/jsp/Custype.jsp";
	}*/
		
	@RequestMapping(value = "/dChgCustype",method = RequestMethod.POST)
	@ResponseBody
	public String dChgCustype(Customer customer){
		if(customer.getCusid().equals("cusidall")){
			customer.setCusid(null);
		}
		String response = "<option value='typeidall' >请选择产品大类</option>";
		List<CusType> cusTypes = computeMoneyService.getCustype(customer);
		for(CusType ct: cusTypes){
			response += "<option value='"+ct.getTypeid()+"'>"+ct.getTypetimeing()+"</option>";
		}
		return response;
	}
	
}
