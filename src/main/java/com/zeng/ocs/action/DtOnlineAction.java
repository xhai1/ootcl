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
import com.zeng.ocs.po.DtOlServiceVo;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.DtOnlineService;
import com.zeng.ocs.util.ExcelUtil;
import com.zeng.ocs.util.NetUtil;
import com.zeng.ocs.util.PageBean;

/**
 * 在线服务明细的action
 * @author cxs
 *
 */
@Controller
@RequestMapping("/dtOnline")
public class DtOnlineAction {
	
	@Autowired
	private DtOnlineService onlineService;
	@Autowired
	private CustomerService customerService;
	@RequestMapping("/showOnlineService")
	public ModelAndView showOnlineService(HttpSession session,String page,String mediatype,String businesstype,String begintime,String endtime,String cusid,String cusname,String mediasrc){
		
		ModelAndView modelAndView=new ModelAndView();
		
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		int pageSize=Configuration.PAGE_NUM;
		
		DtOlServiceVo dtOlServiceVo=new DtOlServiceVo();
		dtOlServiceVo.setMediatype(mediatype);
		dtOlServiceVo.setBusinesstype(businesstype);
		dtOlServiceVo.setBegintime(begintime);
		dtOlServiceVo.setEndtime(endtime);
		dtOlServiceVo.setCusid(cusid);
		dtOlServiceVo.setMediasrc(mediasrc);
		dtOlServiceVo.setCusName(cusname);
		
		//这里使用session
		List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
		if(customerVoList==null||customerVoList.size()<=50){
			customerVoList=customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}
		
		
		
		
		modelAndView.addObject("customerVoList", customerVoList);
		
		PageBean<DtOlServiceVo> pageBean=onlineService.showDtOnlineServiceByLimit(pageNo,pageSize,dtOlServiceVo);
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.addObject("mediatype", mediatype);
		modelAndView.addObject("businesstype", businesstype);
		modelAndView.addObject("begintime", begintime);
		modelAndView.addObject("endtime", endtime);
		modelAndView.addObject("cusid", cusid);
		modelAndView.addObject("mediasrc", mediasrc);
		modelAndView.addObject("cusname", cusname);
		modelAndView.setViewName("/WEB-INF/jsp/RightOnlineServiceDetail.jsp");
		return modelAndView;
	}
	@RequestMapping("/exportOnlineService")
	public void exportOnlineService(HttpSession session,HttpServletRequest request,HttpServletResponse response,String mediatype,String businesstype,String begintime,String endtime,String cusid,String cusname,String mediasrc,String page) throws IOException{
		
		DtOlServiceVo dtOlServiceVo=new DtOlServiceVo();
		dtOlServiceVo.setMediatype(mediatype);
		dtOlServiceVo.setBusinesstype(businesstype);
		dtOlServiceVo.setBegintime(begintime);
		dtOlServiceVo.setEndtime(endtime);
		dtOlServiceVo.setCusid(cusid);
		dtOlServiceVo.setMediasrc(mediasrc);
		dtOlServiceVo.setCusName(cusname);
		
		List<DtOlServiceVo> dtOlServiceVoList=new ArrayList<DtOlServiceVo>();
		dtOlServiceVoList=onlineService.findDtOlService(session,dtOlServiceVo,page);
		
		response.setHeader("Content-Disposition", "attachment;filename=" + NetUtil.getFileName(request, "非语音明细表.xlsx"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelUtil<DtOlServiceVo> e=new ExcelUtil<DtOlServiceVo>(DtOlServiceVo.class);
        e.exportExcel(dtOlServiceVoList,"非语音明细表",outputStream);
        if(outputStream != null){
			 outputStream.close();
		}
	}
}
