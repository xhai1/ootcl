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
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.configuration.Configuration;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.DtMessageVo;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.DtMessageService;
import com.zeng.ocs.util.ExcelUtil;
import com.zeng.ocs.util.NetUtil;
import com.zeng.ocs.util.PageBean;
/**
 * 短信明细action
 * @author cxs
 *
 */
@Controller
@RequestMapping("/dtMessage")
public class DtMessageAction {
	
	@Autowired
	private DtMessageService dtMessageService;
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/showDtMessage")
	public ModelAndView showDtMessage(HttpSession session,String page,String cusname,String begintime,String endtime,String phoneno,String operator,String cusid){
		ModelAndView modelAndView=new ModelAndView();
		
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		int pageSize=Configuration.PAGE_NUM;
		
		DtMessageVo dtMessageVo=new DtMessageVo();
		dtMessageVo.setCusname(cusname);
		dtMessageVo.setBegintime(begintime);
		dtMessageVo.setEndtime(endtime);
		dtMessageVo.setPhoneno(phoneno);
		dtMessageVo.setOperator(operator);
		dtMessageVo.setCusid(cusid);
		
		//这里使用session
		List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
		if(customerVoList==null||customerVoList.size()<=50){
			customerVoList=customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}
		
		
		
		
		modelAndView.addObject("customerVoList", customerVoList);
		
		PageBean<DtMessageVo> pageBean=dtMessageService.showDtMessageByLimit(pageNo,pageSize,dtMessageVo);
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.addObject("cusname", cusname);
		modelAndView.addObject("begintime", begintime);
		modelAndView.addObject("endtime", endtime);
		modelAndView.addObject("phoneno", phoneno);
		modelAndView.addObject("operator", operator);
		modelAndView.addObject("cusid", cusid);
		modelAndView.setViewName("/WEB-INF/jsp/RightDtMessage.jsp");
		return modelAndView;
	}
	@RequestMapping("/exportDtMessage")
	public void exportDtMessage(HttpSession session,HttpServletRequest request,HttpServletResponse response,String cusname,String begintime,String endtime,String phoneno,String operator,String cusid,String page) throws IOException{
		DtMessageVo dtMessageVo=new DtMessageVo();
		dtMessageVo.setCusname(cusname);
		dtMessageVo.setBegintime(begintime);
		dtMessageVo.setEndtime(endtime);
		dtMessageVo.setPhoneno(phoneno);
		dtMessageVo.setOperator(operator);
		dtMessageVo.setCusid(cusid);
		
		List<DtMessageVo> dtMessageVoList=new ArrayList<DtMessageVo>();
		dtMessageVoList=dtMessageService.findDtMessage(session,dtMessageVo,page);
		
		response.setHeader("Content-Disposition", "attachment;filename=" +NetUtil.getFileName(request, "短信明细表.xlsx") );
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelUtil<DtMessageVo> e=new ExcelUtil<DtMessageVo>(DtMessageVo.class);
        e.exportExcel(dtMessageVoList,"短信明细表",outputStream);
        if(outputStream != null){
			 outputStream.close();
		}
	}
	
	
}
