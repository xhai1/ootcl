package com.zeng.ocs.action;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
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

import com.zeng.ocs.exception.CustomException;
import com.zeng.ocs.po.CusReport;
import com.zeng.ocs.po.CusReportVo;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.Para;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.MonthReportService;
import com.zeng.ocs.util.JavaDoubleCompute;
import com.zeng.ocs.util.OfficeUtil;
import com.zeng.ocs.util.PageBean;
import com.zeng.ocs.util.WPSUtil;
/**
 * tcl月报表账单接口
 * @author cxs
 *
 */
@Controller
@RequestMapping("/monthReport")
public class MonthReportAction {
	
	private static final String DRIVER_CLASS="com.mysql.jdbc.Driver"; 
	private static final String URL="jdbc:mysql://localhost:3306/ootcl"; 
	private static final String USERNAME="root";
	private static final String PASSWORD="xy123456";
//	private static final String PASSWORD="642496";
	
	@Autowired
	private MonthReportService monthReportService;
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 修改
	 * @param page
	 * @param begintime
	 * @param endtime
	 * @param cusid
	 * @return
	 */
	@RequestMapping("/showMonthReportDetail")
	public ModelAndView showMonthReportDetail(String page,String begintime,String endtime,String cusid){
		
		
		ModelAndView modelAndView=new ModelAndView();
		
		
		
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		int pageSize=50;
		//传入参数不为空情况下才调用存储过程
		if(begintime!=null&&!"".equals(begintime)&&endtime!=null&&!"".equals(endtime)&&cusid!=null&&!"".equals(cusid)){
			try{
				Class.forName(DRIVER_CLASS);
				Connection connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
				String sql="{call b01cusreport(?,?,?)}";
				CallableStatement cstm=connection.prepareCall(sql);
				cstm.setString(1, begintime);
				cstm.setString(2, endtime);
				cstm.setString(3, cusid);
				cstm.execute();
				cstm.close();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		CusReportVo cusReportVo=new CusReportVo();
//		cusReportVo.setCusname(cusname);
		cusReportVo.setBegintime(begintime);
		cusReportVo.setEndtime(endtime);
		cusReportVo.setCusid(cusid);
		
		PageBean<CusReportVo> pageBean=monthReportService.showMonthReportByLimit(pageNo,pageSize,cusReportVo);
		
		List<CustomerVo> customerVoList=customerService.findAllCustomer();
		modelAndView.addObject("customerVoList", customerVoList);
		modelAndView.addObject("pageBean", pageBean);
//		modelAndView.addObject("cusname", cusname);
		modelAndView.addObject("begintime", begintime);
		modelAndView.addObject("endtime", endtime);
		modelAndView.addObject("cusid", cusid);
		
		modelAndView.setViewName("/WEB-INF/jsp/summarydetail.jsp");
		return modelAndView;
	}
	
	/**
	 * 这部分不改变
	 * @param page
	 * @param cusname
	 * @param begintime
	 * @param endtime
	 * @param cusid
	 * @return
	 */
	@RequestMapping("/showALlMonthReport")
	public ModelAndView showALlMonthReport(HttpSession session,String page,String cusname,String begintime,String endtime,String cusid){
		ModelAndView modelAndView=new ModelAndView();
		
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		int pageSize=20;
		
		CusReportVo cusReportVo=new CusReportVo();
		cusReportVo.setCusname(cusname);
		cusReportVo.setBegintime(begintime);
		cusReportVo.setEndtime(endtime);
		cusReportVo.setCusid(cusid);
		cusReportVo.setEndflag(1);
		
		List<Para> paraList=monthReportService.getParaList();
		PageBean<CusReportVo> pageBean=monthReportService.showAllMonthReport(pageNo,pageSize,cusReportVo);
		
		for(CusReportVo rp :  pageBean.getList()){
			rp.setFmtMonth(fmtMonth(rp));
		}
		
		List<CustomerVo> customerVoList=(List<CustomerVo>)session.getAttribute("customerVoList");
		if(customerVoList==null||customerVoList.size()<=50){
			customerVoList=customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}
		
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.addObject("cusname", cusname);
		modelAndView.addObject("begintime", begintime);
		modelAndView.addObject("endtime", endtime);
		modelAndView.addObject("paraList", paraList);
		modelAndView.addObject("cusid", cusid);
		modelAndView.setViewName("/WEB-INF/jsp/RightMonAccount.jsp");
		return modelAndView;
	}
	
	/**
	 * 预览多张账单或者一张
	 * @param page
	 * @param cusname
	 * @param begintime
	 * @param endtime
	 * @param id
	 * @param cusid
	 * @return
	 */
	@RequestMapping("/createMonthReport")
	public ModelAndView createMonthReport(String page,String cusname,String begintime,String endtime,Integer id,String cusid){
		ModelAndView modelAndView=new ModelAndView();
		//service层去掉pagehelper
		int pageNo;
		if(page==null){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(page);
		}
		int pageSize=20;
		
		CusReportVo cusReportVo=new CusReportVo();
		cusReportVo.setCusname(cusname);
		cusReportVo.setBegintime(begintime);
		cusReportVo.setEndtime(endtime);
		cusReportVo.setId(id);
		cusReportVo.setCusid(cusid);
		
		List<Para> paraList=monthReportService.getParaList();
		PageBean<CusReportVo> pageBean=monthReportService.showMonthReport(pageNo,pageSize,cusReportVo);
		
		for(CusReportVo rp :  pageBean.getList()){
			rp.setFmtMonth(fmtMonth(rp));
		}
		modelAndView.addObject("pageBean", pageBean);
		modelAndView.addObject("cusname", cusname);
		modelAndView.addObject("begintime", begintime);
		modelAndView.addObject("endtime", endtime);
		modelAndView.addObject("paraList", paraList);
		modelAndView.addObject("id", id);
		modelAndView.addObject("cusid", cusid);
		modelAndView.setViewName("/WEB-INF/jsp/CreateMonAccount.jsp");
		return modelAndView;
	}
	
	
	/**
	 * 结算一张账单
	 * @param id
	 * @return
	 */
	@RequestMapping("/accountMonthReport")
	public ModelAndView accountMonthReport(Integer id){
		ModelAndView modelAndView=new ModelAndView();
		
		List<Para> paraList=monthReportService.getParaList();
		CusReportVo cusReportVo=monthReportService.accountMonthReport(id);
		if(cusReportVo!=null){
			cusReportVo.setEndflag(1);
			monthReportService.updateCusReport(cusReportVo);
		}
		modelAndView.addObject("fmtMonth", fmtMonth(cusReportVo));
		modelAndView.addObject("paraList", paraList);
		modelAndView.addObject("cusReportVo", cusReportVo);
		modelAndView.setViewName("/WEB-INF/jsp/AccountOneMonReport.jsp");
		return modelAndView;
	}
	
	public String fmtMonth(CusReportVo cusReportVo){
		String fmtMonth = cusReportVo.getMonth().substring(0, 4);
		fmtMonth += cusReportVo.getCusid().substring(cusReportVo.getCusid().length()-2,cusReportVo.getCusid().length());
		String idt = String.valueOf(cusReportVo.getId());
		if(idt.length() < 6){
			for(int i = 0 ; i < 6 - idt.length() ; i++){
				fmtMonth += "0";
			}
			fmtMonth += idt;
		}else if(idt.length() == 6){
			fmtMonth += idt;
		}else{
			fmtMonth += idt.substring(idt.length() - 2, idt.length());
		}
		return fmtMonth;
	}
	
	@RequestMapping("/accountMonthReportByIds")
	public ModelAndView createMonthReportByIds(HttpServletRequest request) throws CustomException{
		ModelAndView modelAndView=new ModelAndView();
		List<CusReportVo> list=null;
		try{
			String items = request.getParameter("delitems");
			String[] strs = items.split(",");  
			List<Integer> ids = new ArrayList<Integer>();
			for (int i = 0; i < strs.length; i++) {  
				int ID = Integer.parseInt(strs[i]);  
				ids.add(ID);  
			}
			list=monthReportService.accountMonthReportByIds(ids);
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					CusReportVo CusReportVo=list.get(i);
					CusReportVo.setEndflag(1);
					monthReportService.updateCusReport(CusReportVo);
				}
			}
		} catch (Exception e){
			throw new CustomException("创建月报表失败！");
		}
		
		
		List<Para> paraList=monthReportService.getParaList();
		modelAndView.addObject("paraList", paraList);
		modelAndView.addObject("list", list);
		modelAndView.setViewName("/WEB-INF/jsp/AccountMonReportByIds.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/exportMonthReportWps")
	public void exportMonthReportWps(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<CusReportVo> list=null;
		String company=null;
		try{
			String items = request.getParameter("id");
			String[] strs = items.split(",");  
			List<Integer> ids = new ArrayList<Integer>();
			for (int i = 0; i < strs.length; i++) {  
				int ID = Integer.parseInt(strs[i]);  
				ids.add(ID);  
			}
			list=monthReportService.createMonthReportByIds(ids);
			if(list.size()>0){
				company="月报表_"+list.get(0).getCusname()+""+list.get(0).getMonth()+".docx";
			}
		} catch (Exception e){
			throw new CustomException("创建月报表失败！");
		}
		
		String chargeman=null;
		String comacount=null;
		String compopacbank=null;
		List<Para> paraList=monthReportService.getParaList();
		for(int i=0;i<paraList.size();i++){
			if(paraList.get(i).getPkey().equals("chargeman")){
				chargeman=paraList.get(i).getPvalue();
			}
			if(paraList.get(i).getPkey().equals("comacount")){
				comacount=paraList.get(i).getPvalue();
			}
			if(paraList.get(i).getPkey().equals("compopacbank")){
				compopacbank=paraList.get(i).getPvalue();
			}
		}
		
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(company.getBytes("utf-8"), "ISO-8859-1"));
		response.setContentType("application/msword"); // word格式  
        ServletOutputStream outputStream = response.getOutputStream();
        WPSUtil.exportWord(comacount,compopacbank,chargeman,list,outputStream);
        if(outputStream != null){
			 outputStream.close();
		}
	}
	@RequestMapping("/exportMonthReportOffice")
	public void exportMonthReportOffice(HttpServletRequest request,HttpServletResponse response) throws IOException, CustomException{
		List<CusReportVo> list=null;
		String company=null;
		try{
			String items = request.getParameter("id");
			String[] strs = items.split(",");  
			List<Integer> ids = new ArrayList<Integer>();
			for (int i = 0; i < strs.length; i++) {  
				int ID = Integer.parseInt(strs[i]);  
				ids.add(ID);  
			}
			list=monthReportService.createMonthReportByIds(ids);
			if(list.size()>0){
				company="月报表_"+list.get(0).getCusname()+""+list.get(0).getMonth()+".docx";
			}
		} catch (Exception e){
			throw new CustomException("创建月报表失败！");
		}
		
		String chargeman=null;
		String comacount=null;
		String compopacbank=null;
		List<Para> paraList=monthReportService.getParaList();
		for(int i=0;i<paraList.size();i++){
			if(paraList.get(i).getPkey().equals("chargeman")){
				chargeman=paraList.get(i).getPvalue();
			}
			if(paraList.get(i).getPkey().equals("comacount")){
				comacount=paraList.get(i).getPvalue();
			}
			if(paraList.get(i).getPkey().equals("compopacbank")){
				compopacbank=paraList.get(i).getPvalue();
			}
		}
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(company.getBytes("utf-8"), "ISO-8859-1"));
		response.setContentType("application/msword"); // word格式  
        ServletOutputStream outputStream =response.getOutputStream();
        OfficeUtil.exportOffice(comacount,compopacbank,chargeman,list,outputStream);
        if(outputStream != null){
			 outputStream.close();
		}
	}
	
	
	
}
