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
import com.zeng.ocs.po.CusTypeVo;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.DtChgOrderVo;
import com.zeng.ocs.po.User;
import com.zeng.ocs.service.ChangeOrderService;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.ProductService;
import com.zeng.ocs.util.ExcelUtil;
import com.zeng.ocs.util.NetUtil;
import com.zeng.ocs.util.PageBean;
/**
 * 改单明细action
 * @author cxs
 *
 */
@Controller
@RequestMapping("/changeOrder")
public class ChangeOrderAction {
	
	@Autowired
	private ChangeOrderService changeOrderService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/showChangeOrder")
	public ModelAndView showChangeOrder(HttpSession session,String page,String cusname,String begintime,String endtime,String cusid,String typeid,String type){
		
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
		dtChgOrderVo.setMode("改单");
		dtChgOrderVo.setCusid(cusid);
		dtChgOrderVo.setTypeid(typeid);
		dtChgOrderVo.setType(type);
		
		PageBean<DtChgOrderVo> pageBean=changeOrderService.showDtChgOrderByLimit(pageNo,pageSize,dtChgOrderVo);
		//这里使用session
		List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
		if(customerVoList==null||customerVoList.size()<=50){
			customerVoList=customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}
		
		List<CusTypeVo> cusTypeList = new ArrayList<>();
		if(cusid == null || cusid ==""){
			cusTypeList = productService.findAllCustype();
		}else{
			cusTypeList = productService.findACustype(cusid);
		}
		
		modelAndView.addObject("cusTypeList", cusTypeList);
		modelAndView.addObject("customerVoList", customerVoList);
		
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.addObject("cusname", cusname);
		modelAndView.addObject("begintime", begintime);
		modelAndView.addObject("endtime", endtime);
		modelAndView.addObject("cusid", cusid);
		modelAndView.addObject("typeid", typeid);
		modelAndView.addObject("type", type);
		modelAndView.setViewName("/WEB-INF/jsp/RightUpdateOrderDetail.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/exportChangeOrder")
	public void exportChangeOrder(HttpSession session,HttpServletRequest request,HttpServletResponse response,String cusname,String begintime,String endtime,String cusid,String typeid,String type,String page) throws IOException{
		
		DtChgOrderVo dtChgOrderVo=new DtChgOrderVo();
		dtChgOrderVo.setCusname(cusname);
		dtChgOrderVo.setBegintime(begintime);
		dtChgOrderVo.setEndtime(endtime);
		dtChgOrderVo.setMode("改单");
		dtChgOrderVo.setCusid(cusid);
		dtChgOrderVo.setTypeid(typeid);
		dtChgOrderVo.setType(type);
		
		List<DtChgOrderVo> dtChgOrderVoList=new ArrayList<DtChgOrderVo>();
		dtChgOrderVoList=changeOrderService.findDtChgOrder(session,dtChgOrderVo,page);
		
		User user=(User)session.getAttribute("user");
		if(user!=null&&user.getIsroot()==0){
			for(int i=0;i<dtChgOrderVoList.size();i++){
				String acceptid=dtChgOrderVoList.get(i).getAcceptid();
				String caseorder=dtChgOrderVoList.get(i).getCaseorder();
				dtChgOrderVoList.get(i).setAcceptid(acceptid.substring(0, 7)+"*****"+acceptid.substring(acceptid.length()-4, acceptid.length()));
				dtChgOrderVoList.get(i).setCaseorder(caseorder.substring(0, 9)+"*****"+caseorder.substring(caseorder.length()-4, caseorder.length()));
			}
		}
		
		
		response.setHeader("Content-Disposition", "attachment;filename=" +NetUtil.getFileName(request, "改单明细表.xlsx"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelUtil<DtChgOrderVo> e=new ExcelUtil<DtChgOrderVo>(DtChgOrderVo.class);
                
        e.exportExcel(dtChgOrderVoList,"改单明细表",outputStream);
        if(outputStream != null){
			 outputStream.close();
		}
	}
	

	
}
