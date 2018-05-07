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
import com.zeng.ocs.po.DtMultiVoiceVo;
import com.zeng.ocs.po.DtOlServiceVo;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.DtMultiVoiceService;
import com.zeng.ocs.util.ExcelUtil;
import com.zeng.ocs.util.NetUtil;
import com.zeng.ocs.util.PageBean;
/**
 * 多媒体控制
 * @author cxs
 *
 */
@Controller
@RequestMapping("/multiVoice")
public class MultiVoiceAction {
	@Autowired
	private DtMultiVoiceService dtMultiVoiceService;
	
	@Autowired
	private CustomerService customerService;
	@RequestMapping("/showMultiVoice")
	public  ModelAndView showMultiVoice(HttpSession session,String page,String cusname,String callingno,String callno,String calledno,String ntype,String cusid,String begintime, String endtime){
		ModelAndView modelAndView=new ModelAndView();
		
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		int pageSize=Configuration.PAGE_NUM;

		DtMultiVoiceVo dtMultiVoiceVo=new DtMultiVoiceVo();
		dtMultiVoiceVo.setCusName(cusname);
		dtMultiVoiceVo.setCallingno(callingno);
		dtMultiVoiceVo.setCallno(callno);
		dtMultiVoiceVo.setCalledno(calledno);
		dtMultiVoiceVo.setNtype(ntype);
		dtMultiVoiceVo.setCusid(cusid);
		dtMultiVoiceVo.setBeginTime(begintime);
		dtMultiVoiceVo.setEndTime(endtime);
		//这里使用session
		List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
		if(customerVoList==null||customerVoList.size()==0){
			customerVoList=customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}
        if(customerVoList!=null&&customerVoList.size()!=0){
			int size = customerVoList.size();
	        for(int i = 0; i < size; ){
	        	CustomerVo icv = customerVoList.get(i);
	            if(!("20180103120747.133".equals(icv.getCusid())||"20180108151322.964".equals(icv.getCusid())||"20180103120747.535".equals(icv.getCusid())
	               ||"20180108151322.453".equals(icv.getCusid())||"20180103120747.64".equals(icv.getCusid())/*||"20180103120746.613".equals(icv.getCusid())*/)){
	            	customerVoList.remove(icv);
	            	size--;
	            }else{
	              i++;
	            }
	        }
		
		}
		
		modelAndView.addObject("customerVoList", customerVoList);
		
		PageBean<DtMultiVoiceVo> pageBean=dtMultiVoiceService.showDtMultiVoiceByLimit(pageNo,pageSize,dtMultiVoiceVo);
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.addObject("cusname", cusname);
		modelAndView.addObject("callingno", callingno);
		modelAndView.addObject("callno", callno);
		modelAndView.addObject("calledno", calledno);
		modelAndView.addObject("ntype", ntype);
		modelAndView.addObject("cusid", cusid);
		modelAndView.addObject("begintime", begintime);
		modelAndView.addObject("endtime", endtime);
		modelAndView.setViewName("/WEB-INF/jsp/multimddt.jsp");
		return modelAndView;
	}
	@RequestMapping("/exportMultiVoice")
	public void exportMultiVoice(HttpSession session,HttpServletRequest request,HttpServletResponse response,String cusname,String callingno,String callno,String calledno,String ntype,String cusid,String page,String begintime, String endtime) throws IOException{
		
		
		DtMultiVoiceVo dtMultiVoiceVo=new DtMultiVoiceVo();
		dtMultiVoiceVo.setCusName(cusname);
		dtMultiVoiceVo.setCallingno(callingno);
		dtMultiVoiceVo.setCallno(callno);
		dtMultiVoiceVo.setCalledno(calledno);
		dtMultiVoiceVo.setNtype(ntype);
		dtMultiVoiceVo.setCusid(cusid);
		dtMultiVoiceVo.setBeginTime(begintime);
		dtMultiVoiceVo.setEndTime(endtime);
		
		List<DtMultiVoiceVo> dtMultiVoiceVoList=new ArrayList<DtMultiVoiceVo>();
		dtMultiVoiceVoList=dtMultiVoiceService.findDtMultiVoice(session,dtMultiVoiceVo,page);
		
		response.setHeader("Content-Disposition", "attachment;filename=" + NetUtil.getFileName(request, "多媒体语音明细表.xlsx"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelUtil<DtMultiVoiceVo> e=new ExcelUtil<DtMultiVoiceVo>(DtMultiVoiceVo.class);
        e.exportExcel(dtMultiVoiceVoList,"多媒体语音明细表",outputStream);
        if(outputStream != null){
			 outputStream.close();
		}
	}
}
