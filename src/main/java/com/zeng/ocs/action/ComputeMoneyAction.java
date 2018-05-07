package com.zeng.ocs.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.exception.CustomException;
import com.zeng.ocs.po.ComputeMoney;
import com.zeng.ocs.po.CusType;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.po.MoneyStandard;
import com.zeng.ocs.service.ComputeMoneyService;
import com.zeng.ocs.util.NetUtil;
import com.zeng.ocs.util.PaginationUtil;

@Controller
@RequestMapping("/ComputeMoney")
public class ComputeMoneyAction {

	@Autowired
	private ComputeMoneyService computeMoneyService;
	
	@RequestMapping("/staForCompany")
	public ModelAndView SearchForCompany(Customer ctm){
		
		ModelAndView mv ;
		List<Customer> customers = computeMoneyService.getCustomer();
		Customer customer = null;
		if (ctm.getCusid() != null){//外部用户
			mv = new ModelAndView("/WEB-INF/jsp/RightTotalAccount3.jsp");
			for(int i = 0 ; i < customers.size() ; i++){
				if(ctm.getCusid().equals(customers.get(i).getCusid())){
					customer = customers.get(i);
					break;
				}
			}
		}else{
			mv = new ModelAndView("/WEB-INF/jsp/RightTotalAccount.jsp");
//			customer = getCustomer(customers);//随机获取
			customer = customers.get(0);
		}
//		Calendar cal = Calendar.getInstance();
//		int day1 = cal.get(Calendar.DATE);
//		int month1 = cal.get(Calendar.MONTH)+1;
//		int year1 = cal.get(Calendar.YEAR);

		//当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String currentMonth = df.format(new Date());// new Date()为获取当前系统时间
		
		   //获取昨天时间		
		  Calendar   cal   =   Calendar.getInstance();
		  cal.add(Calendar.DATE,   -1);
		  String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
		
		List<MoneyStandard> MoneyStandards = computeMoneyService.getCustomerMoneyStandard(customer);
		List<CusType> cusTypes = computeMoneyService.getCustype(customer);

		List<CusType> selectCusType = cusTypes;//公司所有产品大类
		
		List<ComputeMoney> computeMoneys = computeMoneyService.staComputeMoney(customer, cusTypes, MoneyStandards,currentMonth.substring(0,currentMonth.lastIndexOf("-")));
		ComputeMoney totalacc = new ComputeMoney();
		for(int j = 0 ; j< computeMoneys.size() ; j++){
			totalacc.setCallInCount( (totalacc.getCallInCount()==null ? 0 : totalacc.getCallInCount()) + (computeMoneys.get(j).getCallInCount()==null ? 0 : computeMoneys.get(j).getCallInCount()) );
			totalacc.setCallInTmlen( (totalacc.getCallInTmlen()==null ? 0 : totalacc.getCallInTmlen())  + (computeMoneys.get(j).getCallInTmlen()==null ? 0 : computeMoneys.get(j).getCallInTmlen()) );
			totalacc.setCallInTotal( (totalacc.getCallInTotal()==null ? 0 : totalacc.getCallInTotal()) +  (computeMoneys.get(j).getCallInTotal()==null ? 0 : computeMoneys.get(j).getCallInTotal()) );
			
			totalacc.setCallOutCount( (totalacc.getCallOutCount()==null ? 0 : totalacc.getCallOutCount()) + (computeMoneys.get(j).getCallOutCount()==null ? 0 : computeMoneys.get(j).getCallOutCount()) );
			totalacc.setCallOutTmlen( (totalacc.getCallOutTmlen()==null ? 0 : totalacc.getCallOutTmlen()) + (computeMoneys.get(j).getCallOutTmlen()==null ? 0 : computeMoneys.get(j).getCallOutTmlen()) );
			totalacc.setCallOutTotal( (totalacc.getCallOutTotal()==null ? 0 : totalacc.getCallOutTotal()) + (computeMoneys.get(j).getCallOutTotal()==null ? 0 : computeMoneys.get(j).getCallOutTotal()) );
		}
		
		//总计
		List<ComputeMoney> cmList = new ArrayList<ComputeMoney>();
		
		for (int i = 0; i < MoneyStandards.size(); i++) {
			MoneyStandard ms = MoneyStandards.get(i);
			if(ms.getmType() == 2)continue;
			ComputeMoney cm = new ComputeMoney();
			
			cm.setType("合计");
			cm.setClazz(ms.getClazz()==null ? "语音":ms.getClazz());
			


				for(int j = 0 ; j< computeMoneys.size() ; j++){
					if(  ( computeMoneys.get(j).getClazz() != null)  &&  (computeMoneys.get(j).getClazz().equals(cm.getClazz())) ){
						cm.setCallInCount( (cm.getCallInCount()==null ? 0 : cm.getCallInCount()) + (computeMoneys.get(j).getCallInCount()==null ? 0 : computeMoneys.get(j).getCallInCount()) );
						cm.setCallInTmlen( (cm.getCallInTmlen()==null ? 0 : cm.getCallInTmlen())  + (computeMoneys.get(j).getCallInTmlen()==null ? 0 : computeMoneys.get(j).getCallInTmlen()) );
						cm.setCallInTotal( (cm.getCallInTotal()==null ? 0 : cm.getCallInTotal()) +  (computeMoneys.get(j).getCallInTotal()==null ? 0 : computeMoneys.get(j).getCallInTotal()) );
						
						cm.setCallOutCount( (cm.getCallOutCount()==null ? 0 : cm.getCallOutCount()) + (computeMoneys.get(j).getCallOutCount()==null ? 0 : computeMoneys.get(j).getCallOutCount()) );
						cm.setCallOutTmlen( (cm.getCallOutTmlen()==null ? 0 : cm.getCallOutTmlen()) + (computeMoneys.get(j).getCallOutTmlen()==null ? 0 : computeMoneys.get(j).getCallOutTmlen()) );
						cm.setCallOutTotal( (cm.getCallOutTotal()==null ? 0 : cm.getCallOutTotal()) + (computeMoneys.get(j).getCallOutTotal()==null ? 0 : computeMoneys.get(j).getCallOutTotal()) );
						
						
					}
				}
				cmList.add(cm);			
		}
		//非语音合计
		ComputeMoney cm = new ComputeMoney();
		cm.setClazz("非语音小计");
		for(int j = 0 ; j< computeMoneys.size() ; j++){
			if(  ( computeMoneys.get(j).getClazz() != null)  &&  (!computeMoneys.get(j).getClazz().equals("语音")) ){
				cm.setCallInCount( (cm.getCallInCount()==null ? 0 : cm.getCallInCount()) + (computeMoneys.get(j).getCallInCount()==null ? 0 : computeMoneys.get(j).getCallInCount()) );
				cm.setCallInTmlen( (cm.getCallInTmlen()==null ? 0 : cm.getCallInTmlen())  + (computeMoneys.get(j).getCallInTmlen()==null ? 0 : computeMoneys.get(j).getCallInTmlen()) );
				cm.setCallInTotal( (cm.getCallInTotal()==null ? 0 : cm.getCallInTotal()) +  (computeMoneys.get(j).getCallInTotal()==null ? 0 : computeMoneys.get(j).getCallInTotal()) );
				
				cm.setCallOutCount( (cm.getCallOutCount()==null ? 0 : cm.getCallOutCount()) + (computeMoneys.get(j).getCallOutCount()==null ? 0 : computeMoneys.get(j).getCallOutCount()) );
				cm.setCallOutTmlen( (cm.getCallOutTmlen()==null ? 0 : cm.getCallOutTmlen()) + (computeMoneys.get(j).getCallOutTmlen()==null ? 0 : computeMoneys.get(j).getCallOutTmlen()) );
				cm.setCallOutTotal( (cm.getCallOutTotal()==null ? 0 : cm.getCallOutTotal()) + (computeMoneys.get(j).getCallOutTotal()==null ? 0 : computeMoneys.get(j).getCallOutTotal()) );
			}
		}
		cmList.add(cm);	
		
		//分类
		Map<String,List<ComputeMoney>> map = new HashMap<String,List<ComputeMoney>>();
		for(CusType ct : cusTypes){
			String type = ct.getTypetimeing();
			List<ComputeMoney> computeMoneys2 = new ArrayList<ComputeMoney>();
			
			ComputeMoney ComputeMoneyt = new ComputeMoney();
			ComputeMoneyt.setCusName(customer.getCusname());
			
			ComputeMoneyt.setClazz("合计");
			
			ComputeMoney ComputeMoneyn = new ComputeMoney();
			ComputeMoneyn.setType(type);
			ComputeMoneyn.setClazz("非语音小计");
			
			for(int i = 0 ; i < computeMoneys.size() ; i++){
				//合计
				
				if(type!=null && computeMoneys.get(i).getType() != null &&  type.equals(computeMoneys.get(i).getType())){//统计语音和非语音
					computeMoneys2.add(computeMoneys.get(i));
					
					ComputeMoneyt.setCallInCount(  (ComputeMoneyt.getCallInCount()==null ? 0 : ComputeMoneyt.getCallInCount())  +  ( computeMoneys.get(i).getCallInCount()==null ? 0 :  computeMoneys.get(i).getCallInCount())   );
					ComputeMoneyt.setCallInTmlen(  (ComputeMoneyt.getCallInTmlen()==null ? 0 : ComputeMoneyt.getCallInTmlen()) +   (computeMoneys.get(i).getCallInTmlen()==null ? 0 : computeMoneys.get(i).getCallInTmlen())  );
					ComputeMoneyt.setCallInTotal( (ComputeMoneyt.getCallInTotal()==null ? 0 : ComputeMoneyt.getCallInTotal()) +  (computeMoneys.get(i).getCallInTotal() ==null ? 0 : computeMoneys.get(i).getCallInTotal() ) );
					
					ComputeMoneyt.setCallOutCount( (ComputeMoneyt.getCallOutCount() ==null ? 0 : ComputeMoneyt.getCallOutCount() ) +   (computeMoneys.get(i).getCallOutCount()==null ? 0 : computeMoneys.get(i).getCallOutCount())  );
					ComputeMoneyt.setCallOutTmlen(  (ComputeMoneyt.getCallOutTmlen()==null ? 0 : ComputeMoneyt.getCallOutTmlen()) +   (computeMoneys.get(i).getCallOutTmlen()==null ? 0 : computeMoneys.get(i).getCallOutTmlen())  );
					ComputeMoneyt.setCallOutTotal(  (ComputeMoneyt.getCallOutTotal()==null ? 0 : ComputeMoneyt.getCallOutTotal())+  (computeMoneys.get(i).getCallOutTotal() ==null ? 0 : computeMoneys.get(i).getCallOutTotal() )  );
					
					if(computeMoneys.get(i).getClazz() != null && (!computeMoneys.get(i).getClazz().contains("语音"))){
						ComputeMoneyn.setCallInCount( (ComputeMoneyn.getCallInCount()==null ? 0 : ComputeMoneyn.getCallInCount()) +   (computeMoneys.get(i).getCallInCount()==null ? 0 : computeMoneys.get(i).getCallInCount())  );
						ComputeMoneyn.setCallInTmlen( (ComputeMoneyn.getCallInTmlen()==null ? 0 : ComputeMoneyn.getCallInTmlen()) +  (computeMoneys.get(i).getCallInTmlen() ==null ? 0 : computeMoneys.get(i).getCallInTmlen() )  );
						ComputeMoneyn.setCallInTotal( (ComputeMoneyn.getCallInTotal()==null ? 0 : ComputeMoneyn.getCallInTotal()) +  (computeMoneys.get(i).getCallInTotal() ==null ? 0 : computeMoneys.get(i).getCallInTotal() )  );
						
						ComputeMoneyn.setCallOutCount( (ComputeMoneyn.getCallOutCount()==null ? 0 : ComputeMoneyn.getCallOutCount()) +   (computeMoneys.get(i).getCallOutCount()==null ? 0 : computeMoneys.get(i).getCallOutCount())  );
						ComputeMoneyn.setCallOutTmlen( (ComputeMoneyn.getCallOutTmlen()==null ? 0 : ComputeMoneyn.getCallOutTmlen()) +   (computeMoneys.get(i).getCallOutTmlen()==null ? 0 : computeMoneys.get(i).getCallOutTmlen())  );
						ComputeMoneyn.setCallOutCount( (ComputeMoneyn.getCallOutCount()==null ? 0 : ComputeMoneyn.getCallOutCount()) +  ( computeMoneys.get(i).getCallOutCount()==null ? 0 : computeMoneys.get(i).getCallOutCount())  );
					}
					
					computeMoneys.remove(computeMoneys.get(i));
					i--;
				}
				
			}
			computeMoneys2.add(ComputeMoneyn);
			computeMoneys2.add(ComputeMoneyt);
			map.put(type, computeMoneys2);
		}
		
		/*mv.addObject("year1", year1);
		mv.addObject("month1", month1);
		mv.addObject("day1", day1);*/
		mv.addObject("currentMonth", currentMonth);
		mv.addObject("customer", customer);
		mv.addObject("customers", customers);
		mv.addObject("cusTypes", cusTypes);
		mv.addObject("map", map);
		mv.addObject("sms", computeMoneys.get(0));
		
		mv.addObject("cmList", cmList);
		mv.addObject("totalacc", totalacc);
		mv.addObject("cuslen",  (cusTypes.size()+1)*(MoneyStandards.size()+2)+1 );
		
		mv.addObject("cusTypes", cusTypes);		
		mv.addObject("selectCusType", selectCusType);	
		mv.addObject("yesterday", yesterday);	
		
		return mv;
	}
	
	
	public Customer getCustomer(List<Customer> customers){
		int rand =(int) (Math.random() * customers.size());
		return  customers.get(rand);
	}
	
	
	
	
	//按条件统计
	@RequestMapping("/SearchByCondition")
	public ModelAndView SearchByCondition(PaginationUtil paginationUtil){
		ModelAndView mv;
		if(paginationUtil.getCondition5() == null)
			mv = new ModelAndView("/WEB-INF/jsp/RightTotalAccount.jsp");
		else
			mv = new ModelAndView("/WEB-INF/jsp/RightTotalAccount3.jsp");
		List<Customer> customers = computeMoneyService.getCustomer();
		Customer customer;
		List<CusType> cusTypes = null;
		Map<String,String> mapCondition = new HashMap<String,String>();
		List<MoneyStandard> MoneyStandards =null;
		
		if( (paginationUtil.getCondition1() != null) && (paginationUtil.getCondition1() != "") ){
			customer = computeMoneyService.getCustomer(paginationUtil);
			if( customer != null && customer.getCusid() != null )
				mapCondition.put("cusid", customer.getCusid());
			if(customer == null || customer.getCusid() == null ){
				
				 return new ModelAndView("redirect:/ComputeMoney/Prompt.action","mes","公司名称不正确!");
			}
			 
		}else{
			return new ModelAndView("redirect:/ComputeMoney/Prompt.action","mes","请输入公司名称!");
		}
		//产品大类
		if( (paginationUtil.getCondition2()!=null)  && !paginationUtil.getCondition2().equals("1" )   ){
			mapCondition.put("typetimeing",  paginationUtil.getCondition2());
			cusTypes = computeMoneyService.getCustypeByMap(mapCondition);
			if(cusTypes.size() == 0){
				return new ModelAndView("redirect:/ComputeMoney/Prompt.action","mes","公司无产品大类记录!");
			}
		}else{
			cusTypes = computeMoneyService.getCustype(customer);
			if(cusTypes.size() == 0){
				CusType cusType = new CusType();
				cusType.setCusid(customer.getCusid());
				cusType.setTypeid("emptyTypeid");
				cusType.setTypetimeing("<b>["+customer.getCusname()+"]</b>无产品大类");
				cusTypes.add(cusType);
			}
		}
		
		List<CusType> selectCusType  = computeMoneyService.getCustype(customer);//公司所有产品大类

		String startTime ;
		
		//当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String endTime = df.format(new Date());// new Date()为获取当前系统时间
		
		if( (paginationUtil.getCondition3() != null)  && (paginationUtil.getCondition3() != "") && (paginationUtil.getCondition4() != null ) && ( paginationUtil.getCondition4() != "" ) ){//
			startTime = paginationUtil.getCondition3();
			endTime = paginationUtil.getCondition4();
		}else if( (paginationUtil.getCondition3() == null || paginationUtil.getCondition3() == "" )&& (paginationUtil.getCondition4() != null)  &&( paginationUtil.getCondition4() != "" ) ){
			return new ModelAndView("redirect:/ComputeMoney/Prompt.action","mes","请输入开始时间!");
		}else if((paginationUtil.getCondition3() != null)  && (paginationUtil.getCondition3() != "") && (paginationUtil.getCondition4() == null ||  paginationUtil.getCondition4() == "") ){
			startTime = paginationUtil.getCondition3();
			endTime = endTime;
		}else{//都为空，
			return new ModelAndView("redirect:/ComputeMoney/Prompt.action","mes","请输入开始时间!");
		}
		
		MoneyStandards = computeMoneyService.getCustomerMoneyStandard(customer);
		
		List<ComputeMoney> computeMoneys = computeMoneyService.staComputeMoneyForSea(customer, cusTypes, MoneyStandards,startTime,endTime);
		
		ComputeMoney totalacc = new ComputeMoney();
		for(int j = 0 ; j< computeMoneys.size() ; j++){
			totalacc.setCallInCount( (totalacc.getCallInCount()==null ? 0 : totalacc.getCallInCount()) + (computeMoneys.get(j).getCallInCount()==null ? 0 : computeMoneys.get(j).getCallInCount()) );
			totalacc.setCallInTmlen( (totalacc.getCallInTmlen()==null ? 0 : totalacc.getCallInTmlen())  + (computeMoneys.get(j).getCallInTmlen()==null ? 0 : computeMoneys.get(j).getCallInTmlen()) );
			totalacc.setCallInTotal( (totalacc.getCallInTotal()==null ? 0 : totalacc.getCallInTotal()) +  (computeMoneys.get(j).getCallInTotal()==null ? 0 : computeMoneys.get(j).getCallInTotal()) );
			
			totalacc.setCallOutCount( (totalacc.getCallOutCount()==null ? 0 : totalacc.getCallOutCount()) + (computeMoneys.get(j).getCallOutCount()==null ? 0 : computeMoneys.get(j).getCallOutCount()) );
			totalacc.setCallOutTmlen( (totalacc.getCallOutTmlen()==null ? 0 : totalacc.getCallOutTmlen()) + (computeMoneys.get(j).getCallOutTmlen()==null ? 0 : computeMoneys.get(j).getCallOutTmlen()) );
			totalacc.setCallOutTotal( (totalacc.getCallOutTotal()==null ? 0 : totalacc.getCallOutTotal()) + (computeMoneys.get(j).getCallOutTotal()==null ? 0 : computeMoneys.get(j).getCallOutTotal()) );
		}
		
		//总计
		List<ComputeMoney> cmList = new ArrayList<ComputeMoney>();
		
		for (int i = 0; i < MoneyStandards.size(); i++) {
			MoneyStandard ms = MoneyStandards.get(i);
			if(ms.getmType() == 2)continue;
			ComputeMoney cm = new ComputeMoney();
			
			cm.setType("合计");
			cm.setClazz(ms.getClazz()==null ? "语音":ms.getClazz());
			


				for(int j = 0 ; j< computeMoneys.size() ; j++){
					if(  ( computeMoneys.get(j).getClazz() != null)  &&  (computeMoneys.get(j).getClazz().equals(cm.getClazz())) ){
						cm.setCallInCount( (cm.getCallInCount()==null ? 0 : cm.getCallInCount()) + (computeMoneys.get(j).getCallInCount()==null ? 0 : computeMoneys.get(j).getCallInCount()) );
						cm.setCallInTmlen( (cm.getCallInTmlen()==null ? 0 : cm.getCallInTmlen())  + (computeMoneys.get(j).getCallInTmlen()==null ? 0 : computeMoneys.get(j).getCallInTmlen()) );
						cm.setCallInTotal( (cm.getCallInTotal()==null ? 0 : cm.getCallInTotal()) +  (computeMoneys.get(j).getCallInTotal()==null ? 0 : computeMoneys.get(j).getCallInTotal()) );
						
						cm.setCallOutCount( (cm.getCallOutCount()==null ? 0 : cm.getCallOutCount()) + (computeMoneys.get(j).getCallOutCount()==null ? 0 : computeMoneys.get(j).getCallOutCount()) );
						cm.setCallOutTmlen( (cm.getCallOutTmlen()==null ? 0 : cm.getCallOutTmlen()) + (computeMoneys.get(j).getCallOutTmlen()==null ? 0 : computeMoneys.get(j).getCallOutTmlen()) );
						cm.setCallOutTotal( (cm.getCallOutTotal()==null ? 0 : cm.getCallOutTotal()) + (computeMoneys.get(j).getCallOutTotal()==null ? 0 : computeMoneys.get(j).getCallOutTotal()) );
						
						
					}
				}
				cmList.add(cm);			
		}
		//非语音合计
		ComputeMoney cm = new ComputeMoney();
		cm.setClazz("非语音小计");
		for(int j = 0 ; j< computeMoneys.size() ; j++){
			if(  ( computeMoneys.get(j).getClazz() != null)  &&  (!computeMoneys.get(j).getClazz().equals("语音")) ){
				cm.setCallInCount( (cm.getCallInCount()==null ? 0 : cm.getCallInCount()) + (computeMoneys.get(j).getCallInCount()==null ? 0 : computeMoneys.get(j).getCallInCount()) );
				cm.setCallInTmlen( (cm.getCallInTmlen()==null ? 0 : cm.getCallInTmlen())  + (computeMoneys.get(j).getCallInTmlen()==null ? 0 : computeMoneys.get(j).getCallInTmlen()) );
				cm.setCallInTotal( (cm.getCallInTotal()==null ? 0 : cm.getCallInTotal()) +  (computeMoneys.get(j).getCallInTotal()==null ? 0 : computeMoneys.get(j).getCallInTotal()) );
				
				cm.setCallOutCount( (cm.getCallOutCount()==null ? 0 : cm.getCallOutCount()) + (computeMoneys.get(j).getCallOutCount()==null ? 0 : computeMoneys.get(j).getCallOutCount()) );
				cm.setCallOutTmlen( (cm.getCallOutTmlen()==null ? 0 : cm.getCallOutTmlen()) + (computeMoneys.get(j).getCallOutTmlen()==null ? 0 : computeMoneys.get(j).getCallOutTmlen()) );
				cm.setCallOutTotal( (cm.getCallOutTotal()==null ? 0 : cm.getCallOutTotal()) + (computeMoneys.get(j).getCallOutTotal()==null ? 0 : computeMoneys.get(j).getCallOutTotal()) );
			}
		}
		cmList.add(cm);	
		
		//分类
		Map<String,List<ComputeMoney>> map = new HashMap<String,List<ComputeMoney>>();
		for(CusType ct : cusTypes){
			String type = ct.getTypetimeing();
			List<ComputeMoney> computeMoneys2 = new ArrayList<ComputeMoney>();
			
			ComputeMoney ComputeMoneyt = new ComputeMoney();
			ComputeMoneyt.setCusName(customer.getCusname());
			
			ComputeMoneyt.setClazz("合计");
			
			ComputeMoney ComputeMoneyn = new ComputeMoney();
			ComputeMoneyn.setType(type);
			ComputeMoneyn.setClazz("非语音小计");
			
			for(int i = 0 ; i < computeMoneys.size() ; i++){
				//合计
				
				if( type!=null && computeMoneys.get(i).getType() != null &&  type.equals(computeMoneys.get(i).getType())){//统计语音和非语音
					computeMoneys2.add(computeMoneys.get(i));
					
					ComputeMoneyt.setCallInCount(  (ComputeMoneyt.getCallInCount()==null ? 0 : ComputeMoneyt.getCallInCount())  +  ( computeMoneys.get(i).getCallInCount()==null ? 0 :  computeMoneys.get(i).getCallInCount())   );
					ComputeMoneyt.setCallInTmlen(  (ComputeMoneyt.getCallInTmlen()==null ? 0 : ComputeMoneyt.getCallInTmlen()) +   (computeMoneys.get(i).getCallInTmlen()==null ? 0 : computeMoneys.get(i).getCallInTmlen())  );
					ComputeMoneyt.setCallInTotal( (ComputeMoneyt.getCallInTotal()==null ? 0 : ComputeMoneyt.getCallInTotal()) +  (computeMoneys.get(i).getCallInTotal() ==null ? 0 : computeMoneys.get(i).getCallInTotal() ) );
					
					ComputeMoneyt.setCallOutCount( (ComputeMoneyt.getCallOutCount() ==null ? 0 : ComputeMoneyt.getCallOutCount() ) +   (computeMoneys.get(i).getCallOutCount()==null ? 0 : computeMoneys.get(i).getCallOutCount())  );
					ComputeMoneyt.setCallOutTmlen(  (ComputeMoneyt.getCallOutTmlen()==null ? 0 : ComputeMoneyt.getCallOutTmlen()) +   (computeMoneys.get(i).getCallOutTmlen()==null ? 0 : computeMoneys.get(i).getCallOutTmlen())  );
					ComputeMoneyt.setCallOutTotal(  (ComputeMoneyt.getCallOutTotal()==null ? 0 : ComputeMoneyt.getCallOutTotal())+  (computeMoneys.get(i).getCallOutTotal() ==null ? 0 : computeMoneys.get(i).getCallOutTotal() )  );
					
					if(computeMoneys.get(i).getClazz() != null && (!computeMoneys.get(i).getClazz().contains("语音"))){
						ComputeMoneyn.setCallInCount( (ComputeMoneyn.getCallInCount()==null ? 0 : ComputeMoneyn.getCallInCount()) +   (computeMoneys.get(i).getCallInCount()==null ? 0 : computeMoneys.get(i).getCallInCount())  );
						ComputeMoneyn.setCallInTmlen( (ComputeMoneyn.getCallInTmlen()==null ? 0 : ComputeMoneyn.getCallInTmlen()) +  (computeMoneys.get(i).getCallInTmlen() ==null ? 0 : computeMoneys.get(i).getCallInTmlen() )  );
						ComputeMoneyn.setCallInTotal( (ComputeMoneyn.getCallInTotal()==null ? 0 : ComputeMoneyn.getCallInTotal()) +  (computeMoneys.get(i).getCallInTotal() ==null ? 0 : computeMoneys.get(i).getCallInTotal() )  );
						
						ComputeMoneyn.setCallOutCount( (ComputeMoneyn.getCallOutCount()==null ? 0 : ComputeMoneyn.getCallOutCount()) +   (computeMoneys.get(i).getCallOutCount()==null ? 0 : computeMoneys.get(i).getCallOutCount())  );
						ComputeMoneyn.setCallOutTmlen( (ComputeMoneyn.getCallOutTmlen()==null ? 0 : ComputeMoneyn.getCallOutTmlen()) +   (computeMoneys.get(i).getCallOutTmlen()==null ? 0 : computeMoneys.get(i).getCallOutTmlen())  );
						ComputeMoneyn.setCallOutCount( (ComputeMoneyn.getCallOutCount()==null ? 0 : ComputeMoneyn.getCallOutCount()) +  ( computeMoneys.get(i).getCallOutCount()==null ? 0 : computeMoneys.get(i).getCallOutCount())  );
					}
					
					computeMoneys.remove(computeMoneys.get(i));
					i--;
				}
				
			}
			computeMoneys2.add(ComputeMoneyn);
			computeMoneys2.add(ComputeMoneyt);
			map.put(type, computeMoneys2);
		}
		
		mv.addObject("startTime", startTime);
		mv.addObject("endTime", endTime);
		
		mv.addObject("customer", customer);
		mv.addObject("customers", customers);
		mv.addObject("cusTypes", cusTypes);
		mv.addObject("map", map);
		mv.addObject("sms", computeMoneys.get(0));
		
		mv.addObject("cmList", cmList);
		mv.addObject("totalacc", totalacc);
		mv.addObject("cuslen",  (cusTypes.size()+1)*(MoneyStandards.size()+2)+1 );
		mv.addObject("paginationUtil", paginationUtil);
		
		
		mv.addObject("selectCusType", selectCusType);
		
		return mv;
	}
	@RequestMapping("/Prompt")
	@ResponseBody
	public String Prompt(String mes){
		 String result = "";
		 result = "<script>alert('"+mes+"');"
					+ "history.go(-1);"
					+ "</script>";
		 return result;
	}
	
	
	
	
	@RequestMapping("/exportByCondition")
	@ResponseBody
	public String exportByCondition(HttpServletResponse response,HttpServletRequest request,PaginationUtil paginationUtil) {
		ModelAndView mv = new ModelAndView("redirect:/ComputeMoney/exportExcel.action");
		Customer customer;
		List<CusType> cusTypes = null;
		Map<String,String> mapCondition = new HashMap<String,String>();
		List<MoneyStandard> MoneyStandards =null;
		
		if( (paginationUtil.getCondition1() != null) && (paginationUtil.getCondition1() != "") ){
			customer = computeMoneyService.getCustomer(paginationUtil);
			if( customer != null && customer.getCusid() != null )
			mapCondition.put("cusid", customer.getCusid());
			if( customer == null || customer.getCusid() == null ){
				 return Prompt("您输入公司名称不正确!");
			}
			 
		}else{
			return Prompt("请输入公司名称!");
		}
		if( !paginationUtil.getCondition2().equals("1") ){
			mapCondition.put("typetimeing",  paginationUtil.getCondition2());
			cusTypes = computeMoneyService.getCustypeByMap(mapCondition);
			if(cusTypes.size() == 0){
				CusType cusType = new CusType();
				cusType.setType("无产品大类");
				cusType.setTypeid("bigTypeNull");
				cusTypes.add(cusType);
			}
		}else{
			cusTypes = computeMoneyService.getCustype(customer);
			if(cusTypes == null || cusTypes.size() == 0){
				CusType cusType = new CusType();
				cusType.setType("无产品大类");
				cusType.setTypeid("bigTypeNull");
				cusTypes.add(cusType);
			}
		}
		
		
		String startTime ;
		
		//当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String endTime = df.format(new Date());// new Date()为获取当前系统时间
		
		if( (paginationUtil.getCondition3() != null)  && (paginationUtil.getCondition3() != "") && (paginationUtil.getCondition4() != null ) && ( paginationUtil.getCondition4() != "" ) ){//
			startTime = paginationUtil.getCondition3();//+" 00:00:00";
			endTime = paginationUtil.getCondition4()+" 23:59:59";
		}else if( (paginationUtil.getCondition3() == null || paginationUtil.getCondition3() == "" )&& (paginationUtil.getCondition4() != null)  &&( paginationUtil.getCondition4() != "" ) ){
			return Prompt("请输入开始时间!");
		}else if((paginationUtil.getCondition3() != null)  && (paginationUtil.getCondition3() != "") && (paginationUtil.getCondition4() == null ||  paginationUtil.getCondition4() == "") ){
			startTime = paginationUtil.getCondition3();//+" 00:00:00";
			endTime = endTime+" 23:59:59";
		}else{//都为空，
			return Prompt("请输入开始时间!");
		}
		
		MoneyStandards = computeMoneyService.getCustomerMoneyStandard(customer);
		
		List<ComputeMoney> computeMoneys = computeMoneyService.staComputeMoneyForSea(customer, cusTypes, MoneyStandards,startTime,endTime);
		
		ComputeMoney totalacc = new ComputeMoney();
		for(int j = 0 ; j< computeMoneys.size() ; j++){
			totalacc.setCallInCount( (totalacc.getCallInCount()==null ? 0 : totalacc.getCallInCount()) + (computeMoneys.get(j).getCallInCount()==null ? 0 : computeMoneys.get(j).getCallInCount()) );
			totalacc.setCallInTmlen( (totalacc.getCallInTmlen()==null ? 0 : totalacc.getCallInTmlen())  + (computeMoneys.get(j).getCallInTmlen()==null ? 0 : computeMoneys.get(j).getCallInTmlen()) );
			totalacc.setCallInTotal( (totalacc.getCallInTotal()==null ? 0 : totalacc.getCallInTotal()) +  (computeMoneys.get(j).getCallInTotal()==null ? 0 : computeMoneys.get(j).getCallInTotal()) );
			
			totalacc.setCallOutCount( (totalacc.getCallOutCount()==null ? 0 : totalacc.getCallOutCount()) + (computeMoneys.get(j).getCallOutCount()==null ? 0 : computeMoneys.get(j).getCallOutCount()) );
			totalacc.setCallOutTmlen( (totalacc.getCallOutTmlen()==null ? 0 : totalacc.getCallOutTmlen()) + (computeMoneys.get(j).getCallOutTmlen()==null ? 0 : computeMoneys.get(j).getCallOutTmlen()) );
			totalacc.setCallOutTotal( (totalacc.getCallOutTotal()==null ? 0 : totalacc.getCallOutTotal()) + (computeMoneys.get(j).getCallOutTotal()==null ? 0 : computeMoneys.get(j).getCallOutTotal()) );
		}
		
		//总计
		List<ComputeMoney> cmList = new ArrayList<ComputeMoney>();
		
		for (int i = 0; i < MoneyStandards.size(); i++) {
			MoneyStandard ms = MoneyStandards.get(i);
			if(ms.getmType() == 2)continue;
			ComputeMoney cm = new ComputeMoney();
			
			cm.setType("合计");
			cm.setClazz(ms.getClazz()==null ? "语音":ms.getClazz());
			


				for(int j = 0 ; j< computeMoneys.size() ; j++){
					if(  ( computeMoneys.get(j).getClazz() != null)  &&  (computeMoneys.get(j).getClazz().equals(cm.getClazz())) ){
						cm.setCallInCount( (cm.getCallInCount()==null ? 0 : cm.getCallInCount()) + (computeMoneys.get(j).getCallInCount()==null ? 0 : computeMoneys.get(j).getCallInCount()) );
						cm.setCallInTmlen( (cm.getCallInTmlen()==null ? 0 : cm.getCallInTmlen())  + (computeMoneys.get(j).getCallInTmlen()==null ? 0 : computeMoneys.get(j).getCallInTmlen()) );
						cm.setCallInTotal( (cm.getCallInTotal()==null ? 0 : cm.getCallInTotal()) +  (computeMoneys.get(j).getCallInTotal()==null ? 0 : computeMoneys.get(j).getCallInTotal()) );
						
						cm.setCallOutCount( (cm.getCallOutCount()==null ? 0 : cm.getCallOutCount()) + (computeMoneys.get(j).getCallOutCount()==null ? 0 : computeMoneys.get(j).getCallOutCount()) );
						cm.setCallOutTmlen( (cm.getCallOutTmlen()==null ? 0 : cm.getCallOutTmlen()) + (computeMoneys.get(j).getCallOutTmlen()==null ? 0 : computeMoneys.get(j).getCallOutTmlen()) );
						cm.setCallOutTotal( (cm.getCallOutTotal()==null ? 0 : cm.getCallOutTotal()) + (computeMoneys.get(j).getCallOutTotal()==null ? 0 : computeMoneys.get(j).getCallOutTotal()) );
						
						
					}
				}
				cmList.add(cm);			
		}
		//非语音合计
		ComputeMoney cm = new ComputeMoney();
		cm.setClazz("非语音小计");
		for(int j = 0 ; j< computeMoneys.size() ; j++){
			if(  ( computeMoneys.get(j).getClazz() != null)  &&  (!computeMoneys.get(j).getClazz().equals("语音")) ){
				cm.setCallInCount( (cm.getCallInCount()==null ? 0 : cm.getCallInCount()) + (computeMoneys.get(j).getCallInCount()==null ? 0 : computeMoneys.get(j).getCallInCount()) );
				cm.setCallInTmlen( (cm.getCallInTmlen()==null ? 0 : cm.getCallInTmlen())  + (computeMoneys.get(j).getCallInTmlen()==null ? 0 : computeMoneys.get(j).getCallInTmlen()) );
				cm.setCallInTotal( (cm.getCallInTotal()==null ? 0 : cm.getCallInTotal()) +  (computeMoneys.get(j).getCallInTotal()==null ? 0 : computeMoneys.get(j).getCallInTotal()) );
				
				cm.setCallOutCount( (cm.getCallOutCount()==null ? 0 : cm.getCallOutCount()) + (computeMoneys.get(j).getCallOutCount()==null ? 0 : computeMoneys.get(j).getCallOutCount()) );
				cm.setCallOutTmlen( (cm.getCallOutTmlen()==null ? 0 : cm.getCallOutTmlen()) + (computeMoneys.get(j).getCallOutTmlen()==null ? 0 : computeMoneys.get(j).getCallOutTmlen()) );
				cm.setCallOutTotal( (cm.getCallOutTotal()==null ? 0 : cm.getCallOutTotal()) + (computeMoneys.get(j).getCallOutTotal()==null ? 0 : computeMoneys.get(j).getCallOutTotal()) );
			}
		}
		cmList.add(cm);	
		
		//分类
		Map<String,List<ComputeMoney>> map = new HashMap<String,List<ComputeMoney>>();
		for(CusType ct : cusTypes){
			String type = ct.getTypetimeing();
			List<ComputeMoney> computeMoneys2 = new ArrayList<ComputeMoney>();
			
			ComputeMoney ComputeMoneyt = new ComputeMoney();
			ComputeMoneyt.setCusName(customer.getCusname());
			
			ComputeMoneyt.setClazz("合计");
			
			ComputeMoney ComputeMoneyn = new ComputeMoney();
			ComputeMoneyn.setType(type);
			ComputeMoneyn.setClazz("非语音小计");
			
			for(int i = 0 ; i < computeMoneys.size() ; i++){
				//合计
				
				if( type!=null && computeMoneys.get(i).getType() != null && type.equals(computeMoneys.get(i).getType()) ){//统计语音和非语音
					computeMoneys2.add(computeMoneys.get(i));
					
					ComputeMoneyt.setCallInCount(  (ComputeMoneyt.getCallInCount()==null ? 0 : ComputeMoneyt.getCallInCount())  +  ( computeMoneys.get(i).getCallInCount()==null ? 0 :  computeMoneys.get(i).getCallInCount())   );
					ComputeMoneyt.setCallInTmlen(  (ComputeMoneyt.getCallInTmlen()==null ? 0 : ComputeMoneyt.getCallInTmlen()) +   (computeMoneys.get(i).getCallInTmlen()==null ? 0 : computeMoneys.get(i).getCallInTmlen())  );
					ComputeMoneyt.setCallInTotal( (ComputeMoneyt.getCallInTotal()==null ? 0 : ComputeMoneyt.getCallInTotal()) +  (computeMoneys.get(i).getCallInTotal() ==null ? 0 : computeMoneys.get(i).getCallInTotal() ) );
					
					ComputeMoneyt.setCallOutCount( (ComputeMoneyt.getCallOutCount() ==null ? 0 : ComputeMoneyt.getCallOutCount() ) +   (computeMoneys.get(i).getCallOutCount()==null ? 0 : computeMoneys.get(i).getCallOutCount())  );
					ComputeMoneyt.setCallOutTmlen(  (ComputeMoneyt.getCallOutTmlen()==null ? 0 : ComputeMoneyt.getCallOutTmlen()) +   (computeMoneys.get(i).getCallOutTmlen()==null ? 0 : computeMoneys.get(i).getCallOutTmlen())  );
					ComputeMoneyt.setCallOutTotal(  (ComputeMoneyt.getCallOutTotal()==null ? 0 : ComputeMoneyt.getCallOutTotal())+  (computeMoneys.get(i).getCallOutTotal() ==null ? 0 : computeMoneys.get(i).getCallOutTotal() )  );
					
					if(computeMoneys.get(i).getClazz() != null && (!computeMoneys.get(i).getClazz().contains("语音"))){
						ComputeMoneyn.setCallInCount( (ComputeMoneyn.getCallInCount()==null ? 0 : ComputeMoneyn.getCallInCount()) +   (computeMoneys.get(i).getCallInCount()==null ? 0 : computeMoneys.get(i).getCallInCount())  );
						ComputeMoneyn.setCallInTmlen( (ComputeMoneyn.getCallInTmlen()==null ? 0 : ComputeMoneyn.getCallInTmlen()) +  (computeMoneys.get(i).getCallInTmlen() ==null ? 0 : computeMoneys.get(i).getCallInTmlen() )  );
						ComputeMoneyn.setCallInTotal( (ComputeMoneyn.getCallInTotal()==null ? 0 : ComputeMoneyn.getCallInTotal()) +  (computeMoneys.get(i).getCallInTotal() ==null ? 0 : computeMoneys.get(i).getCallInTotal() )  );
						
						ComputeMoneyn.setCallOutCount( (ComputeMoneyn.getCallOutCount()==null ? 0 : ComputeMoneyn.getCallOutCount()) +   (computeMoneys.get(i).getCallOutCount()==null ? 0 : computeMoneys.get(i).getCallOutCount())  );
						ComputeMoneyn.setCallOutTmlen( (ComputeMoneyn.getCallOutTmlen()==null ? 0 : ComputeMoneyn.getCallOutTmlen()) +   (computeMoneys.get(i).getCallOutTmlen()==null ? 0 : computeMoneys.get(i).getCallOutTmlen())  );
						ComputeMoneyn.setCallOutCount( (ComputeMoneyn.getCallOutCount()==null ? 0 : ComputeMoneyn.getCallOutCount()) +  ( computeMoneys.get(i).getCallOutCount()==null ? 0 : computeMoneys.get(i).getCallOutCount())  );
					}
					
					computeMoneys.remove(computeMoneys.get(i));
					i--;
				}
				
			}
			computeMoneys2.add(ComputeMoneyn);
			computeMoneys2.add(ComputeMoneyt);
			map.put(type, computeMoneys2);
		}

		
		try {			
			response.setHeader("Content-Disposition", "attachment;filename=" + NetUtil.getFileName(request, "计费总量表.xlsx"));			
			response.setContentType("application/ynd.ms-excel;charset=UTF-8");
			response.setCharacterEncoding("UTF-8"); 
			
			ServletOutputStream outputStream;
				outputStream = response.getOutputStream();
			//具体导出
				if(paginationUtil.getCondition5() == null)
					exportExcel(customer,cusTypes, map,cmList,computeMoneys.get(0),totalacc,(cusTypes.size()+1)*(MoneyStandards.size()+1)+1,startTime, endTime,outputStream);
				else
					exportExcelForOther(customer,cusTypes, map,cmList,computeMoneys.get(0),totalacc,(cusTypes.size()+1)*(MoneyStandards.size()+1)+1,startTime, endTime,outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//内部用户导出
	public void exportExcel(Customer customer, List<CusType>  cusTypes, Map<String,List<ComputeMoney>> map,List<ComputeMoney> cmList,ComputeMoney  sms,ComputeMoney totalacc,Integer cuslen,String startTime,String endTime,ServletOutputStream outputStream){

		try {
			XSSFWorkbook workbook = new XSSFWorkbook();// 创建Excel工作簿对象
			XSSFSheet sheet = workbook.createSheet();// 在工作簿中创建工作表对象
			sheet.setColumnWidth(0, 600 * 14);
			sheet.setColumnWidth(1, 256 * 14);
			sheet.setColumnWidth(2, 256 * 14);
			sheet.setColumnWidth(3, 256 * 14);
			sheet.setColumnWidth(4, 256 * 14);
			sheet.setColumnWidth(5, 256 * 14);
			sheet.setColumnWidth(6, 256 * 14);
			sheet.setColumnWidth(7, 256 * 14);

			sheet.setColumnWidth(8, 256 * 14);
			sheet.setColumnWidth(9, 600 * 14);
			// sheet.setDefaultColumnWidth(600*14);
			workbook.setSheetName(0, "计 费 总 量 表");// 设置工作表的名称

			XSSFFont font = workbook.createFont();// 创建字体对象
			font.setColor(new XSSFColor(java.awt.Color.BLACK));// 设置字体颜色
			font.setFontHeightInPoints((short) 12);// 设置字号
			font.setFontName("楷体");// 设置字体样式
			font.setBold(true);

			XSSFCellStyle titleStyle = workbook.createCellStyle();
			titleStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());// 设置背景色
			titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			titleStyle.setBorderTop(BorderStyle.THIN);
			titleStyle.setBorderLeft(BorderStyle.THIN);
			titleStyle.setBorderRight(BorderStyle.THIN);
			titleStyle.setBorderBottom(BorderStyle.THIN);
			titleStyle.setTopBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setBottomBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setLeftBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setRightBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setFont(font);
			titleStyle.setAlignment(HorizontalAlignment.CENTER);// 设置水平居中
			titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直居中

			XSSFFont fontc = (XSSFFont) workbook.createFont();// 创建字体对象
			fontc.setFontName("微软雅黑");
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);// 设置水平居中
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直居中
			cellStyle.setFont(fontc);
			
			
			XSSFCellStyle totalStyle = workbook.createCellStyle();
			totalStyle.setAlignment(HorizontalAlignment.CENTER);// 设置水平居中
			totalStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直居中
			totalStyle.setFont(fontc);
			
			// cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());//
			// 设置背景色
			// cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			/*
			 * cellStyle.setBorderTop(BorderStyle.THIN);
			 * cellStyle.setBorderLeft(BorderStyle.THIN);
			 * cellStyle.setBorderRight(BorderStyle.THIN);
			 * cellStyle.setBorderBottom(BorderStyle.THIN);
			 * cellStyle.setTopBorderColor(new XSSFColor(java.awt.Color.BLACK));
			 * cellStyle.setBottomBorderColor(new
			 * XSSFColor(java.awt.Color.BLACK));
			 * cellStyle.setLeftBorderColor(new
			 * XSSFColor(java.awt.Color.BLACK));
			 * cellStyle.setRightBorderColor(new
			 * XSSFColor(java.awt.Color.BLACK));
			 */
			cellStyle.setAlignment(HorizontalAlignment.CENTER);// 设置水平居中
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直居中

			XSSFRow row1 = sheet.createRow(0);// 在工作表中创建行对象

			XSSFCell cusName = row1.createCell(0);// 在第1行中创建单元格对象
			cusName.setCellValue("客户名称");
			cusName.setCellStyle(titleStyle);

			XSSFCell bigType = row1.createCell(1);// 在行中创建单元格对象
			bigType.setCellValue("产品类别");
			bigType.setCellStyle(titleStyle);
			XSSFCell clazz = row1.createCell(2);// 在行中创建单元格对象
			clazz.setCellValue("业务类别");
			clazz.setCellStyle(titleStyle);
			XSSFCell callInCount = row1.createCell(3);// 在行中创建单元格对象
			callInCount.setCellValue("呼入次数");
			callInCount.setCellStyle(titleStyle);
			XSSFCell callInTmLen = row1.createCell(4);// 在行中创建单元格对象
			callInTmLen.setCellValue("呼入时长");
			callInTmLen.setCellStyle(titleStyle);
			XSSFCell callInTotal = row1.createCell(5);// 在行中创建单元格对象
			callInTotal.setCellValue("呼入费用");
			callInTotal.setCellStyle(titleStyle);

			XSSFCell callOutCount = row1.createCell(6);// 在行中创建单元格对象
			callOutCount.setCellValue("呼出次数");
			callOutCount.setCellStyle(titleStyle);
			XSSFCell callOutTmLen = row1.createCell(7);// 在行中创建单元格对象
			callOutTmLen.setCellValue("呼出时长");
			callOutTmLen.setCellStyle(titleStyle);
			XSSFCell callOutTotal = row1.createCell(8);// 在行中创建单元格对象
			callOutTotal.setCellValue("呼出费用");
			callOutTotal.setCellStyle(titleStyle);
			XSSFCell staTime = row1.createCell(9);// 在行中创建单元格对象
			staTime.setCellValue("统计期间");
			staTime.setCellStyle(titleStyle);

			sheet.addMergedRegion(new CellRangeAddress(1, cuslen , 0, 0));// 合并第1列的第1个到第cuslen个之间的单元格
			// sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
			// 5));//合并第1行的第1个到第7个之间的单元格

			XSSFRow row2 = sheet.createRow(1);// 在工作表中创建行对象

			XSSFCell cus = row2.createCell(0);// 在第1行中创建单元格对象
			if(customer.getCusname() != null){
				cus.setCellValue(customer.getCusname());
				cus.setCellStyle(cellStyle);
			}

			int nextrow = 1;

			for (int i = 0; i < cusTypes.size(); i++) {// 遍历保存数据对象的集合
				/*
				 * if(cusTypes.get(i).getId() == null){ cusTypes.remove(i); i--;
				 * continue; }
				 */
				List<ComputeMoney> big = map.get(cusTypes.get(i).getTypetimeing());
				if(big != null && big.size() > 2)
					sheet.addMergedRegion(new CellRangeAddress(big.size() * i + 1, big.size() * (i + 1) - 1, 1, 1));// 合并第1列的第1个到第big.size()-1个之间的单元格
				else{
					nextrow += big.size();
					continue;
				}
				XSSFRow rowb;// 在工作表中创建行对象
				if (i == 0) {
					rowb = row2;// 在工作表中创建行对象
				} else {
					rowb = sheet.createRow(big.size() * i + 1);// 在工作表中创建行对象
				}
				XSSFCell bigName = rowb.createCell(1);
				if(big.get(0).getType() != null){
					bigName.setCellValue(big.get(0).getType());
					bigName.setCellStyle(cellStyle);
				}

				for (int j = 0; j < big.size(); j++) {
					/*if (big.get(j).getClazz() == null) {
						big.remove(j);
						j--;
						continue;
					}*/
					XSSFRow row3;
					if (j == 0) {
						row3 = rowb;// 在工作表中创建行对象
					} else {
						row3 = sheet.createRow(big.size() * i + 1 + j);// 在工作表中创建行对象
					}
					if (!big.get(j).getClazz().equals("合计")) {
						XSSFCell cla = row3.createCell(2);
						if(big.get(j).getClazz() != null){
							cla.setCellValue(big.get(j).getClazz());
							cla.setCellStyle(cellStyle);
						}

						XSSFCell intimes = row3.createCell(3);
						if(big.get(j).getCallInCount() != null && big.get(j).getCallInCount()!= 0){
							intimes.setCellValue( big.get(j).getCallInCount());
							intimes.setCellStyle(cellStyle);
						}

						XSSFCell inlen = row3.createCell(4);
						if(big.get(j).getCallInTmlen() != null && big.get(j).getCallInTmlen()!= 0){
							inlen.setCellValue(new   BigDecimal(big.get(j).getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue()  );
							inlen.setCellStyle(cellStyle);
						}

						XSSFCell intotal = row3.createCell(5);
						if( big.get(j).getCallInTotal() != null && big.get(j).getCallInTotal()!= 0 ){
							intotal.setCellValue(new   BigDecimal(big.get(j).getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
							intotal.setCellStyle(cellStyle);
						}

						XSSFCell outtimes = row3.createCell(6);
						if(big.get(j).getCallOutCount() != null && big.get(j).getCallOutCount()!= 0){
							outtimes.setCellValue(big.get(j).getCallOutCount());
							outtimes.setCellStyle(cellStyle);
						}

						XSSFCell outlen = row3.createCell(7);
						if(big.get(j).getCallOutTmlen() != null && big.get(j).getCallOutTmlen()!= 0){
							outlen.setCellValue(new   BigDecimal(big.get(j).getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
							outlen.setCellStyle(cellStyle);
						}

						XSSFCell outtotal = row3.createCell(8);
						if(big.get(j).getCallOutTotal() != null && big.get(j).getCallOutTotal()!= 0){
							outtotal.setCellValue(new   BigDecimal(big.get(j).getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
							outtotal.setCellStyle(cellStyle);
						}

						XSSFCell statime = row3.createCell(9);
						statime.setCellValue(startTime + "/" + endTime);
						statime.setCellStyle(cellStyle);
						nextrow++;
					} else {
						sheet.addMergedRegion(new CellRangeAddress(big.size() * (i + 1), big.size() * (i + 1), 1, 2));// 合并第1列的第1个到第cuslen个之间的单元格
						row3 = sheet.createRow(big.size() * (i + 1));// 在工作表中创建行对象
						XSSFCell sum = row3.createCell(1);
						if(big.get(j).getClazz() != null){
							sum.setCellValue(big.get(j).getClazz());
							sum.setCellStyle(cellStyle);
						}

						XSSFCell cla = row3.createCell(3);
						if(big.get(j).getCallInCount()  != null && big.get(j).getCallInCount()!= 0){
							cla.setCellValue(big.get(j).getCallInCount());
							cla.setCellStyle(cellStyle);
						}

						XSSFCell intimes = row3.createCell(4);
						if(big.get(j).getCallInTmlen() != null && big.get(j).getCallInTmlen()!= 0){
							intimes.setCellValue(new   BigDecimal(big.get(j).getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
							intimes.setCellStyle(cellStyle);
						}

						XSSFCell inlen = row3.createCell(5);
						if(big.get(j).getCallInTotal() != null && big.get(j).getCallInTotal()!= 0){
							inlen.setCellValue(new   BigDecimal(big.get(j).getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
							inlen.setCellStyle(cellStyle);
						}

						XSSFCell intotal = row3.createCell(6);
						if(big.get(j).getCallOutCount() != null && big.get(j).getCallOutCount()!= 0){
							intotal.setCellValue(big.get(j).getCallOutCount());
							intotal.setCellStyle(cellStyle);
						}

						XSSFCell outtimes = row3.createCell(7);
						if(big.get(j).getCallOutTmlen() != null && big.get(j).getCallOutTmlen()!= 0){
							outtimes.setCellValue(new   BigDecimal(big.get(j).getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
							outtimes.setCellStyle(cellStyle);
						}

						XSSFCell outlen = row3.createCell(8);
						if(big.get(j).getCallOutTotal() != null && big.get(j).getCallOutTotal()!= 0){
							outlen.setCellValue(new   BigDecimal(big.get(j).getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
							outlen.setCellStyle(cellStyle);
						}

						XSSFCell statime = row3.createCell(9);
						statime.setCellValue(startTime + "/" + endTime);
						statime.setCellStyle(cellStyle);
						nextrow++;
					}

				}
			}
				sheet.addMergedRegion(new CellRangeAddress(nextrow, nextrow + cmList.size() - 1, 1, 1));// 合并单元格
				XSSFRow rowh = sheet.createRow(nextrow);// 在工作表中创建行对象
				XSSFCell hcell = rowh.createCell(1);
				if(cmList.get(0).getType()!=null){
					hcell.setCellValue(cmList.get(0).getType());
					hcell.setCellStyle(cellStyle);
				}

				for (int k = 0; k < cmList.size(); k++) {
					XSSFRow rowj;
					if (k == 0) {
						rowj = rowh;
					} else {
						rowj = sheet.createRow(nextrow);
					}

					XSSFCell cla = rowj.createCell(2);
					if(cmList.get(k).getClazz()!=null){
						cla.setCellValue(cmList.get(k).getClazz());
						cla.setCellStyle(cellStyle);
					}

					XSSFCell intimes = rowj.createCell(3);
					if(cmList.get(k).getCallInCount() != null && cmList.get(k).getCallInCount()!= 0){
						intimes.setCellValue(cmList.get(k).getCallInCount());
						intimes.setCellStyle(cellStyle);
					}

					XSSFCell inlen = rowj.createCell(4);
					if(cmList.get(k).getCallInTmlen() != null && cmList.get(k).getCallInTmlen()!= 0){
						inlen.setCellValue(new   BigDecimal(cmList.get(k).getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
						inlen.setCellStyle(cellStyle);
					}

					XSSFCell intotal = rowj.createCell(5);
					if(cmList.get(k).getCallInTotal() != null && cmList.get(k).getCallInTotal()!= 0){
						intotal.setCellValue(new   BigDecimal(cmList.get(k).getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
						intotal.setCellStyle(cellStyle);
					}

					XSSFCell outtimes = rowj.createCell(6);
					if(cmList.get(k).getCallOutCount() != null && cmList.get(k).getCallOutCount()!= 0){
						outtimes.setCellValue(cmList.get(k).getCallOutCount());
						outtimes.setCellStyle(cellStyle);
					}

					XSSFCell outlen = rowj.createCell(7);
					if(cmList.get(k).getCallOutTmlen() != null && cmList.get(k).getCallOutTmlen()!= 0){
						outlen.setCellValue(new   BigDecimal(cmList.get(k).getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
						outlen.setCellStyle(cellStyle);
					}

					XSSFCell outtotal = rowj.createCell(8);
					if(cmList.get(k).getCallOutTotal() != null && cmList.get(k).getCallOutTotal()!= 0){
						outtotal.setCellValue(new   BigDecimal(cmList.get(k).getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
						outtotal.setCellStyle(cellStyle);
					}

					XSSFCell statime = rowj.createCell(9);
					statime.setCellValue(startTime + "/" + endTime);
					statime.setCellStyle(cellStyle);
					nextrow++;

				}

				XSSFRow rowsms = sheet.createRow(nextrow);// 在工作表中创建行对象
				sheet.addMergedRegion(new CellRangeAddress(nextrow, nextrow, 1, 2));// 合并单元格
				XSSFCell smscell = rowsms.createCell(1);
				smscell.setCellValue("短信");
				smscell.setCellStyle(cellStyle);

				XSSFCell intimes = rowsms.createCell(3);
				if(sms.getCallInCount() != null && sms.getCallInCount()!= 0){
					intimes.setCellValue(sms.getCallInCount());
					intimes.setCellStyle(cellStyle);
				}

				XSSFCell inlen = rowsms.createCell(4);
				if(sms.getCallInTmlen() != null && sms.getCallInTmlen()!= 0){
					inlen.setCellValue(new   BigDecimal(sms.getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					inlen.setCellStyle(cellStyle);
				}

				XSSFCell intotal = rowsms.createCell(5);
				if(sms.getCallInTotal() != null && sms.getCallInTotal()!= 0){
					intotal.setCellValue(new   BigDecimal(sms.getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					intotal.setCellStyle(cellStyle);
				}

				XSSFCell outtimes = rowsms.createCell(6);
				if(sms.getCallOutCount() != null && sms.getCallOutCount()!= 0){
					outtimes.setCellValue(sms.getCallOutCount());
					outtimes.setCellStyle(cellStyle);
				}

				XSSFCell outlen = rowsms.createCell(7);
				if(sms.getCallOutTmlen() != null && sms.getCallOutTmlen()!= 0)
				outlen.setCellValue(new   BigDecimal(sms.getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
				outlen.setCellStyle(cellStyle);

				XSSFCell outtotal = rowsms.createCell(8);
				if(sms.getCallOutTotal() != null && sms.getCallOutTotal()!= 0){
					outtotal.setCellValue(new   BigDecimal(sms.getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					outtotal.setCellStyle(cellStyle);
				}

				XSSFCell statime = rowsms.createCell(9);
				statime.setCellValue(startTime + "/" + endTime);
				statime.setCellStyle(cellStyle);
				nextrow++;

				XSSFRow rowtot = sheet.createRow(nextrow);// 在工作表中创建行对象
				sheet.addMergedRegion(new CellRangeAddress(nextrow, nextrow, 1, 2));// 合并单元格
				XSSFCell totcell = rowtot.createCell(1);
				totcell.setCellValue("总计");
				totcell.setCellStyle(totalStyle);

				intimes = rowtot.createCell(3);
				if(totalacc.getCallInCount() != null && totalacc.getCallInCount()!= 0){
					intimes.setCellValue(totalacc.getCallInCount());
					intimes.setCellStyle(totalStyle);
				}

				inlen = rowtot.createCell(4);
				if(totalacc.getCallInTmlen() != null && totalacc.getCallInTmlen()!= 0){
					inlen.setCellValue(new   BigDecimal(totalacc.getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					inlen.setCellStyle(totalStyle);
				}

				intotal = rowtot.createCell(5);
				if(totalacc.getCallInTotal() != null && totalacc.getCallInTotal()!= 0){
					intotal.setCellValue(new   BigDecimal(totalacc.getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					intotal.setCellStyle(totalStyle);
				}

				outtimes = rowtot.createCell(6);
				if(totalacc.getCallOutCount() != null && totalacc.getCallOutCount()!= 0){
					outtimes.setCellValue(totalacc.getCallOutCount());
					outtimes.setCellStyle(totalStyle);
				}

				outlen = rowtot.createCell(7);
				if(totalacc.getCallOutTmlen() != null && totalacc.getCallOutTmlen()!= 0){
					outlen.setCellValue(new   BigDecimal(totalacc.getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					outlen.setCellStyle(totalStyle);
				}

				outtotal = rowtot.createCell(8);
				if(totalacc.getCallOutTotal() != null && totalacc.getCallOutTotal()!= 0){
					outtotal.setCellValue(new   BigDecimal(totalacc.getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					outtotal.setCellStyle(totalStyle);
				}

				statime = rowtot.createCell(9);
				statime.setCellValue(startTime + "/" + endTime);
				statime.setCellStyle(totalStyle);
				nextrow++;

				outputStream.flush();
				workbook.write(outputStream);// 将文档对象写入文件输出流
				outputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	//外部用户导出
	public void exportExcelForOther(Customer customer, List<CusType>  cusTypes, Map<String,List<ComputeMoney>> map,List<ComputeMoney> cmList,ComputeMoney  sms,ComputeMoney totalacc,Integer cuslen,String startTime,String endTime,ServletOutputStream outputStream){

		try {
			XSSFWorkbook workbook = new XSSFWorkbook();// 创建Excel工作簿对象
			XSSFSheet sheet = workbook.createSheet();// 在工作簿中创建工作表对象
			sheet.setColumnWidth(0, 600 * 14);
			sheet.setColumnWidth(1, 256 * 14);
			sheet.setColumnWidth(2, 256 * 14);
			sheet.setColumnWidth(3, 256 * 14);
			sheet.setColumnWidth(4, 256 * 14);
			sheet.setColumnWidth(5, 256 * 14);
			sheet.setColumnWidth(6, 256 * 14);
			sheet.setColumnWidth(7, 256 * 14);

			sheet.setColumnWidth(8, 256 * 14);
			sheet.setColumnWidth(9, 600 * 14);
			// sheet.setDefaultColumnWidth(600*14);
			workbook.setSheetName(0, "计 费 总 量 表");// 设置工作表的名称

			XSSFFont font = workbook.createFont();// 创建字体对象
			font.setColor(new XSSFColor(java.awt.Color.BLACK));// 设置字体颜色
			font.setFontHeightInPoints((short) 12);// 设置字号
			font.setFontName("楷体");// 设置字体样式
			font.setBold(true);

			XSSFCellStyle titleStyle = workbook.createCellStyle();
			titleStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());// 设置背景色
			titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			titleStyle.setBorderTop(BorderStyle.THIN);
			titleStyle.setBorderLeft(BorderStyle.THIN);
			titleStyle.setBorderRight(BorderStyle.THIN);
			titleStyle.setBorderBottom(BorderStyle.THIN);
			titleStyle.setTopBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setBottomBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setLeftBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setRightBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setFont(font);
			titleStyle.setAlignment(HorizontalAlignment.CENTER);// 设置水平居中
			titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直居中

			XSSFFont fontc = (XSSFFont) workbook.createFont();// 创建字体对象
			fontc.setFontName("微软雅黑");
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);// 设置水平居中
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直居中
			cellStyle.setFont(fontc);
		
			cellStyle.setAlignment(HorizontalAlignment.CENTER);// 设置水平居中
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直居中

			XSSFRow row1 = sheet.createRow(0);// 在工作表中创建行对象

			XSSFCell cusName = row1.createCell(0);// 在第1行中创建单元格对象
			cusName.setCellValue("客户名称");
			cusName.setCellStyle(titleStyle);

			XSSFCell bigType = row1.createCell(1);// 在行中创建单元格对象
			bigType.setCellValue("产品类别");
			bigType.setCellStyle(titleStyle);
			XSSFCell clazz = row1.createCell(2);// 在行中创建单元格对象
			clazz.setCellValue("业务类别");
			clazz.setCellStyle(titleStyle);
			XSSFCell callInCount = row1.createCell(3);// 在行中创建单元格对象
			callInCount.setCellValue("呼入次数");
			callInCount.setCellStyle(titleStyle);
			XSSFCell callInTmLen = row1.createCell(4);// 在行中创建单元格对象
			callInTmLen.setCellValue("呼入时长");
			callInTmLen.setCellStyle(titleStyle);
			XSSFCell callInTotal = row1.createCell(5);// 在行中创建单元格对象
			callInTotal.setCellValue("呼入费用");
			callInTotal.setCellStyle(titleStyle);

			XSSFCell callOutCount = row1.createCell(6);// 在行中创建单元格对象
			callOutCount.setCellValue("呼出次数");
			callOutCount.setCellStyle(titleStyle);
			XSSFCell callOutTmLen = row1.createCell(7);// 在行中创建单元格对象
			callOutTmLen.setCellValue("呼出时长");
			callOutTmLen.setCellStyle(titleStyle);
			XSSFCell callOutTotal = row1.createCell(8);// 在行中创建单元格对象
			callOutTotal.setCellValue("呼出费用");
			callOutTotal.setCellStyle(titleStyle);
			XSSFCell staTime = row1.createCell(9);// 在行中创建单元格对象
			staTime.setCellValue("统计期间");
			staTime.setCellStyle(titleStyle);

			sheet.addMergedRegion(new CellRangeAddress(1, 2 * (cusTypes.size() + 1) + 1 , 0, 0));// 合并第1列的第1个到第  2 * cusTypes.size() + 1  个之间的单元格
			// sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
			// 5));//合并第1行的第1个到第7个之间的单元格

			XSSFRow row2 = sheet.createRow(1);// 在工作表中创建行对象

			XSSFCell cus = row2.createCell(0);// 在第1行中创建单元格对象
			if( customer.getCusname() != null)
				cus.setCellValue( customer.getCusname());
			cus.setCellStyle(cellStyle);

			int nextrow = 1;

			for (int i = 0; i < cusTypes.size(); i++) {// 遍历保存数据对象的集合
				/*
				 * if(cusTypes.get(i).getId() == null){ cusTypes.remove(i); i--;
				 * continue; }
				 */
				List<ComputeMoney> big = map.get(cusTypes.get(i).getTypetimeing());
				sheet.addMergedRegion(new CellRangeAddress(2 * i + 1, 2 * i + 2, 1, 1));// 合并第1列的第1个到第big.size()-1个之间的单元格
				XSSFRow rowb;// 在工作表中创建行对象
				if (i == 0) {
					rowb = row2;// 在工作表中创建行对象
				} else {
					rowb = sheet.createRow( 2 * i + 1 );// 在工作表中创建行对象
				}
				XSSFCell bigName = rowb.createCell(1);
				if(big.get(0).getType() != null)
					bigName.setCellValue( big.get(0).getType());
				bigName.setCellStyle(cellStyle);

				Boolean flag = false;
				for (int j = 0; j < big.size(); j++) {
					XSSFRow row3;
					if (flag == false) {
						row3 = rowb;// 在工作表中创建行对象
					} else {
						row3 = sheet.createRow( 2 * i + 2);// 在工作表中创建行对象
					}
					if ( big.get(j).getClazz().equals("非语音小计") || big.get(j).getClazz().equals("合计")) {
						XSSFCell cla = row3.createCell(2);
						if(big.get(j).getClazz()!=null)
							cla.setCellValue(big.get(j).getClazz());
						cla.setCellStyle(cellStyle);

						XSSFCell intimes = row3.createCell(3);
						if(big.get(j).getCallInCount() != null && big.get(j).getCallInCount() != 0)
						intimes.setCellValue(big.get(j).getCallInCount());
						intimes.setCellStyle(cellStyle);

						XSSFCell inlen = row3.createCell(4);
						if(big.get(j).getCallInTmlen() != null && big.get(j).getCallInTmlen() != 0)
						inlen.setCellValue(new   BigDecimal(big.get(j).getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
						inlen.setCellStyle(cellStyle);

						XSSFCell intotal = row3.createCell(5);
						if(big.get(j).getCallInTotal() != null && big.get(j).getCallInTotal() != 0)
						intotal.setCellValue(new   BigDecimal(big.get(j).getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
						intotal.setCellStyle(cellStyle);

						XSSFCell outtimes = row3.createCell(6);
						if(big.get(j).getCallOutCount() != null && big.get(j).getCallOutCount() != 0)
						outtimes.setCellValue(big.get(j).getCallOutCount());
						outtimes.setCellStyle(cellStyle);

						XSSFCell outlen = row3.createCell(7);
						if(big.get(j).getCallOutTmlen() != null && big.get(j).getCallOutTmlen() != 0)
						outlen.setCellValue( new   BigDecimal(big.get(j).getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
						outlen.setCellStyle(cellStyle);

						XSSFCell outtotal = row3.createCell(8);
						if(big.get(j).getCallOutTotal() != null && big.get(j).getCallOutTotal() != 0)
						outtotal.setCellValue(new   BigDecimal(big.get(j).getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
						outtotal.setCellStyle(cellStyle);

						XSSFCell statime = row3.createCell(9);
						statime.setCellValue(startTime + "/" + endTime);
						statime.setCellStyle(cellStyle);
						nextrow++;
						flag = true;
					} 
				}
			}
//				sheet.addMergedRegion(new CellRangeAddress(nextrow, nextrow + cmList.size() - 1, 1, 1));// 合并单元格
				XSSFRow rowh = sheet.createRow(nextrow);// 在工作表中创建行对象
				XSSFCell hcell = rowh.createCell(1);
				hcell.setCellValue(cmList.get(0).getType());
				hcell.setCellStyle(cellStyle);

				for (int k = 0; k < cmList.size(); k++) {
					XSSFRow rowj  = rowh;
					if(cmList.get(k).getClazz().equals("非语音小计")){
						XSSFCell cla = rowj.createCell(2);
						if(cmList.get(k).getClazz() 
								!=null)
							cla.setCellValue(cmList.get(k).getClazz());
						cla.setCellStyle(cellStyle);
						
						XSSFCell intimes = rowj.createCell(3);
						if( cmList.get(k).getCallInCount() != null && cmList.get(k).getCallInCount() != 0)
						intimes.setCellValue(cmList.get(k).getCallInCount());
						intimes.setCellStyle(cellStyle);
						
						XSSFCell inlen = rowj.createCell(4);
						if( cmList.get(k).getCallInTmlen() != null && cmList.get(k).getCallInTmlen() != 0)
						inlen.setCellValue(new   BigDecimal(cmList.get(k).getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
						inlen.setCellStyle(cellStyle);
						
						XSSFCell intotal = rowj.createCell(5);
						if(cmList.get(k).getCallInTotal() != null && cmList.get(k).getCallInTotal() != 0)
						intotal.setCellValue(new   BigDecimal(cmList.get(k).getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
						intotal.setCellStyle(cellStyle);
						
						XSSFCell outtimes = rowj.createCell(6);
						if(cmList.get(k).getCallOutCount() != null && cmList.get(k).getCallOutCount() != 0)
						outtimes.setCellValue(cmList.get(k).getCallOutCount());
						outtimes.setCellStyle(cellStyle);
						
						XSSFCell outlen = rowj.createCell(7);
						if(cmList.get(k).getCallOutTmlen() != null && cmList.get(k).getCallOutTmlen() != 0)
						outlen.setCellValue(new   BigDecimal(cmList.get(k).getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
						outlen.setCellStyle(cellStyle);
						
						XSSFCell outtotal = rowj.createCell(8);
						if(cmList.get(k).getCallOutTotal() != null && cmList.get(k).getCallOutTotal() != 0)
						outtotal.setCellValue(new   BigDecimal(cmList.get(k).getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
						outtotal.setCellStyle(cellStyle);
						
						XSSFCell statime = rowj.createCell(9);
						statime.setCellValue(startTime + "/" + endTime);
						statime.setCellStyle(cellStyle);
						nextrow++;
					}

				}

				XSSFRow rowsms = sheet.createRow(nextrow);// 在工作表中创建行对象
				sheet.addMergedRegion(new CellRangeAddress(nextrow, nextrow, 1, 2));// 合并单元格
				XSSFCell smscell = rowsms.createCell(1);
				smscell.setCellValue("短信");
				smscell.setCellStyle(cellStyle);

				XSSFCell intimes = rowsms.createCell(3);
				if(sms.getCallInCount() != null && sms.getCallInCount() != 0)
				intimes.setCellValue(sms.getCallInCount());
				intimes.setCellStyle(cellStyle);

				XSSFCell inlen = rowsms.createCell(4);
				if(sms.getCallInTmlen() != null && sms.getCallInTmlen() != 0)
				inlen.setCellValue(new   BigDecimal(sms.getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				inlen.setCellStyle(cellStyle);

				XSSFCell intotal = rowsms.createCell(5);
				if(sms.getCallInTotal() != null && sms.getCallInTotal()!= 0)
				intotal.setCellValue(new   BigDecimal(sms.getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				intotal.setCellStyle(cellStyle);

				XSSFCell outtimes = rowsms.createCell(6);
				if( sms.getCallOutCount() != null && sms.getCallOutCount() != 0)
				outtimes.setCellValue(sms.getCallOutCount());
				outtimes.setCellStyle(cellStyle);

				XSSFCell outlen = rowsms.createCell(7);
				if(sms.getCallOutTmlen() != null && sms.getCallOutTmlen() != 0)
				outlen.setCellValue(new   BigDecimal(sms.getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				outlen.setCellStyle(cellStyle);

				XSSFCell outtotal = rowsms.createCell(8);
				if(sms.getCallOutTotal() != null && sms.getCallOutTotal() != 0)
				outtotal.setCellValue(new   BigDecimal(sms.getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				outtotal.setCellStyle(cellStyle);

				XSSFCell statime = rowsms.createCell(9);
				statime.setCellValue(startTime + "/" + endTime);
				statime.setCellStyle(cellStyle);
				nextrow++;

				XSSFRow rowtot = sheet.createRow(nextrow);// 在工作表中创建行对象
				sheet.addMergedRegion(new CellRangeAddress(nextrow, nextrow, 1, 2));// 合并单元格
				XSSFCell totcell = rowtot.createCell(1);
				totcell.setCellValue("总计");
				totcell.setCellStyle(cellStyle);

				intimes = rowtot.createCell(3);
				if(totalacc.getCallInCount() != null && totalacc.getCallInCount() != 0)
				intimes.setCellValue(totalacc.getCallInCount());
				intimes.setCellStyle(cellStyle);

				inlen = rowtot.createCell(4);
				if(totalacc.getCallInTmlen() != null && totalacc.getCallInTmlen() != 0)
				inlen.setCellValue(new   BigDecimal(totalacc.getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				inlen.setCellStyle(cellStyle);

				intotal = rowtot.createCell(5);
				if(totalacc.getCallInTotal() != null && totalacc.getCallInTotal() != 0)
				intotal.setCellValue(new   BigDecimal(totalacc.getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				intotal.setCellStyle(cellStyle);

				outtimes = rowtot.createCell(6);
				if( totalacc.getCallOutCount() != null &&  totalacc.getCallOutCount() != 0)
				outtimes.setCellValue( totalacc.getCallOutCount());
				outtimes.setCellStyle(cellStyle);

				outlen = rowtot.createCell(7);
				if(totalacc.getCallOutTmlen() != null && totalacc.getCallOutTmlen() != 0)
				outlen.setCellValue(new   BigDecimal(totalacc.getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				outlen.setCellStyle(cellStyle);

				outtotal = rowtot.createCell(8);
				if(totalacc.getCallOutTotal() != null && totalacc.getCallOutTotal() != 0)
				outtotal.setCellValue(new   BigDecimal(totalacc.getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				outtotal.setCellStyle(cellStyle);

				statime = rowtot.createCell(9);
				statime.setCellValue(startTime + "/" + endTime);
				statime.setCellStyle(cellStyle);
				nextrow++;

				outputStream.flush();
				workbook.write(outputStream);// 将文档对象写入文件输出流
				outputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
//	ajax动态获取公司产品大类
	@RequestMapping(value = "/dynamicGetCustype",method = RequestMethod.POST)
	@ResponseBody
	public String dynamicGetCustype(Customer customer){
		System.out.println(customer);
		String response = "<option value='1'>全部</option>";
		List<CusType> cusTypes = computeMoneyService.getCustype(customer);
		for(CusType ct: cusTypes){
			response += "<option >"+ct.getTypetimeing()+"</option>";
		}
		return response;
	}
	
	//统计全部公司
	@RequestMapping(value = "/staAllCompanyForSearch")
	public ModelAndView staAllCompanyForSearch(PaginationUtil paginationUtil){
		Map<String,String> map = new HashMap<String,String>(); 
		if(paginationUtil.getCondition3().compareTo(paginationUtil.getCondition4()) > 0){
			map.put("condition3", paginationUtil.getCondition4());//+" 00:00:00");
			map.put("condition4", paginationUtil.getCondition3());
		}else{
			map.put("condition3", paginationUtil.getCondition3());//+" 00:00:00");
			map.put("condition4", paginationUtil.getCondition4());
		}
		String startTime  = map.get("condition3");
		String endTime = map.get("condition4");
		
		List<Customer> customers = computeMoneyService.getCustomer();
		List<ComputeMoney> computeMoneys = new ArrayList<ComputeMoney>();
		//获取所有公司总计
		computeMoneys = computeMoneyService.staAllComputeMoney(map,customers);
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/RightTotalAccount.jsp");
		mv.addObject("paginationUtil", paginationUtil);
		mv.addObject("computeMoneys", computeMoneys);
		mv.addObject("customers", customers);
		
		mv.addObject("startTime", startTime);
		mv.addObject("endTime", endTime);
		mv.addObject("showStaAll", 1);
		return mv;
	}
	
	@RequestMapping(value = "/exportStaCustemer")
	public void   exportStaCustemer(HttpServletResponse response,HttpServletRequest request ,PaginationUtil paginationUtil){
		Map<String,String> map = new HashMap<String,String>(); 
		if(paginationUtil.getCondition3().compareTo(paginationUtil.getCondition4()) > 0){
			map.put("condition3", paginationUtil.getCondition4());//+" 00:00:00");
			map.put("condition4", paginationUtil.getCondition3()+" 23:59:59");
		}else{
			map.put("condition3", paginationUtil.getCondition3());//+" 00:00:00");
			map.put("condition4", paginationUtil.getCondition4()+" 23:59:59");
		}
		String startTime  = map.get("condition3");
		String endTime = map.get("condition4");
		
		List<Customer> customers = computeMoneyService.getCustomer();
		List<ComputeMoney> computeMoneys = new ArrayList<ComputeMoney>();
		//获取所有公司总计
		computeMoneys = computeMoneyService.staAllComputeMoney(map,customers);
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + NetUtil.getFileName(request, "计费总量表总计.xlsx"));
			response.setContentType("application/ynd.ms-excel;charset=UTF-8");
			ServletOutputStream outputStream;
				
					outputStream = response.getOutputStream();
					exportAllCustomer( outputStream, computeMoneys , startTime ,  endTime);
				} catch (IOException e) {
					
					e.printStackTrace();
			}
		
	}
	public void exportAllCustomer(ServletOutputStream outputStream,List<ComputeMoney> computeMoneys ,String startTime ,String  endTime){

		try {
			XSSFWorkbook workbook = new XSSFWorkbook();// 创建Excel工作簿对象
			XSSFSheet sheet = workbook.createSheet();// 在工作簿中创建工作表对象
			sheet.setColumnWidth(0, 600 * 14);
			sheet.setColumnWidth(1, 256 * 14);
			sheet.setColumnWidth(2, 256 * 14);
			sheet.setColumnWidth(3, 256 * 14);
			sheet.setColumnWidth(4, 256 * 14);
			sheet.setColumnWidth(5, 256 * 14);
			sheet.setColumnWidth(6, 256 * 14);
			sheet.setColumnWidth(7, 256 * 14);

			sheet.setColumnWidth(8, 256 * 14);
			sheet.setColumnWidth(9, 600 * 14);
			// sheet.setDefaultColumnWidth(600*14);
			workbook.setSheetName(0, "计 费 总 量 表总计");// 设置工作表的名称

			XSSFFont font = workbook.createFont();// 创建字体对象
			font.setColor(new XSSFColor(java.awt.Color.BLACK));// 设置字体颜色
			font.setFontHeightInPoints((short) 12);// 设置字号
			font.setFontName("楷体");// 设置字体样式
			font.setBold(true);

			XSSFCellStyle titleStyle = workbook.createCellStyle();
			titleStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());// 设置背景色
			titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			titleStyle.setBorderTop(BorderStyle.THIN);
			titleStyle.setBorderLeft(BorderStyle.THIN);
			titleStyle.setBorderRight(BorderStyle.THIN);
			titleStyle.setBorderBottom(BorderStyle.THIN);
			titleStyle.setTopBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setBottomBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setLeftBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setRightBorderColor(new XSSFColor(java.awt.Color.BLACK));
			titleStyle.setFont(font);
			titleStyle.setAlignment(HorizontalAlignment.CENTER);// 设置水平居中
			titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直居中

			XSSFFont fontc = (XSSFFont) workbook.createFont();// 创建字体对象
			fontc.setFontName("微软雅黑");
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);// 设置水平居中
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直居中
			cellStyle.setFont(fontc);
		
			cellStyle.setAlignment(HorizontalAlignment.CENTER);// 设置水平居中
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直居中

			XSSFRow row1 = sheet.createRow(0);// 在工作表中创建行对象

			XSSFCell cusName = row1.createCell(0);// 在第1行中创建单元格对象
			cusName.setCellValue("客户名称");
			cusName.setCellStyle(titleStyle);

			XSSFCell bigType = row1.createCell(1);// 在行中创建单元格对象
			bigType.setCellValue("产品类别");
			bigType.setCellStyle(titleStyle);
			XSSFCell clazz = row1.createCell(2);// 在行中创建单元格对象
			clazz.setCellValue("业务类别");
			clazz.setCellStyle(titleStyle);
			XSSFCell callInCount = row1.createCell(3);// 在行中创建单元格对象
			callInCount.setCellValue("呼入次数");
			callInCount.setCellStyle(titleStyle);
			XSSFCell callInTmLen = row1.createCell(4);// 在行中创建单元格对象
			callInTmLen.setCellValue("呼入时长");
			callInTmLen.setCellStyle(titleStyle);
			XSSFCell callInTotal = row1.createCell(5);// 在行中创建单元格对象
			callInTotal.setCellValue("呼入费用");
			callInTotal.setCellStyle(titleStyle);

			XSSFCell callOutCount = row1.createCell(6);// 在行中创建单元格对象
			callOutCount.setCellValue("呼出次数");
			callOutCount.setCellStyle(titleStyle);
			XSSFCell callOutTmLen = row1.createCell(7);// 在行中创建单元格对象
			callOutTmLen.setCellValue("呼出时长");
			callOutTmLen.setCellStyle(titleStyle);
			XSSFCell callOutTotal = row1.createCell(8);// 在行中创建单元格对象
			callOutTotal.setCellValue("呼出费用");
			callOutTotal.setCellStyle(titleStyle);
			XSSFCell staTime = row1.createCell(9);// 在行中创建单元格对象
			staTime.setCellValue("统计期间");
			staTime.setCellStyle(titleStyle);

			sheet.addMergedRegion(new CellRangeAddress(1, computeMoneys.size() , 0, 0));// 合并第1列的第1个到第 computeMoneys.size()个之间的单元格
			XSSFRow row2 = sheet.createRow(1);// 在工作表中创建行对象

			XSSFCell cus = row2.createCell(0);// 在第1行中创建单元格对象
			
			cus.setCellValue("总计");
			cus.setCellStyle(cellStyle);

			XSSFCell bigtype = row2.createCell(1);
			sheet.addMergedRegion(new CellRangeAddress(1, computeMoneys.size()-2 , 1, 1));// 合并第1列的第1个到第 computeMoneys.size()个之间的单元格
			bigtype.setCellValue("合计");
			bigtype.setCellStyle(cellStyle);
			int nextrow = 1;
			for(int i = 0 ; i < computeMoneys.size() ; i++){
				if(computeMoneys.get(i).getClazz().equals("总计")||computeMoneys.get(i).getClazz().equals("短信") )continue;
				XSSFRow row3 = null;
				if(i == 0){
					row3 = row2;
				}else{
					row3 = sheet.createRow(nextrow);// 在工作表中创建行对象
				}
				XSSFCell cell2 = row3.createCell(2);
				cell2.setCellValue(computeMoneys.get(i).getClazz());
				cell2.setCellStyle(cellStyle);
				
				XSSFCell cell3 = row3.createCell(3);
				if(computeMoneys.get(i).getCallInCount() != null && computeMoneys.get(i).getCallInCount() != 0)
				cell3.setCellValue(computeMoneys.get(i).getCallInCount());
				cell3.setCellStyle(cellStyle);
				
				XSSFCell cell4 = row3.createCell(4);
				if(computeMoneys.get(i).getCallInTmlen() != null && computeMoneys.get(i).getCallInTmlen() != 0)
				cell4.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				cell4.setCellStyle(cellStyle);
				
				XSSFCell cell5= row3.createCell(5);
				if(computeMoneys.get(i).getCallInTotal() != null && computeMoneys.get(i).getCallInTotal() != 0)
				cell5.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				cell5.setCellStyle(cellStyle);
				
				XSSFCell cell6 = row3.createCell(6);
				if(computeMoneys.get(i).getCallOutCount() != null && computeMoneys.get(i).getCallOutCount() != 0)
				cell6.setCellValue(computeMoneys.get(i).getCallOutCount());
				cell6.setCellStyle(cellStyle);
				
				XSSFCell cell7 = row3.createCell(7);
				if(computeMoneys.get(i).getCallOutTmlen() != null && computeMoneys.get(i).getCallOutTmlen() != 0)
				cell7.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				cell7.setCellStyle(cellStyle);
				
				XSSFCell cell8 = row3.createCell(8);
				if(computeMoneys.get(i).getCallOutTotal() != null && computeMoneys.get(i).getCallOutTotal() != 0)
				cell8.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
				cell8.setCellStyle(cellStyle);
				
				XSSFCell cell9 = row3.createCell(9);
				cell9.setCellValue(startTime+"/"+endTime);
				cell9.setCellStyle(cellStyle);
				nextrow++;
			}
			
			
			for(int i = 0 ; i < computeMoneys.size() ; i++){
				if(computeMoneys.get(i).getClazz().equals("短信") ){
					XSSFRow rowsms = sheet.createRow(nextrow);// 在工作表中创建行对象
					sheet.addMergedRegion(new CellRangeAddress(nextrow, nextrow, 1, 2));// 合并单元格
					XSSFCell smscell = rowsms.createCell(1);
					smscell.setCellValue("短信");
					smscell.setCellStyle(cellStyle);
					
					XSSFCell intimes = rowsms.createCell(3);
					if(computeMoneys.get(i).getCallInCount() != null && computeMoneys.get(i).getCallInCount()!= 0)
					intimes.setCellValue(computeMoneys.get(i).getCallInCount());
					intimes.setCellStyle(cellStyle);

					XSSFCell inlen = rowsms.createCell(4);
					if(computeMoneys.get(i).getCallInTmlen() != null && computeMoneys.get(i).getCallInTmlen()!= 0)
					inlen.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					inlen.setCellStyle(cellStyle);

					XSSFCell intotal = rowsms.createCell(5);
					if(computeMoneys.get(i).getCallInTotal() != null && computeMoneys.get(i).getCallInTotal()!= 0)
					intotal.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					intotal.setCellStyle(cellStyle);

					XSSFCell outtimes = rowsms.createCell(6);
					if(computeMoneys.get(i).getCallOutCount() != null && computeMoneys.get(i).getCallOutCount()!= 0)
					outtimes.setCellValue(computeMoneys.get(i).getCallOutCount());
					outtimes.setCellStyle(cellStyle);

					XSSFCell outlen = rowsms.createCell(7);
					if(computeMoneys.get(i).getCallOutTmlen() != null && computeMoneys.get(i).getCallOutTmlen()!= 0)
					outlen.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					outlen.setCellStyle(cellStyle);

					XSSFCell outtotal = rowsms.createCell(8);
					if(computeMoneys.get(i).getCallOutTotal() != null && computeMoneys.get(i).getCallOutTotal()!= 0)
					outtotal.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					outtotal.setCellStyle(cellStyle);

					XSSFCell statime = rowsms.createCell(9);
					statime.setCellValue(startTime + "/" + endTime);
					statime.setCellStyle(cellStyle);
					nextrow++;
				}
			}
			
				

			

			for(int i = 0 ; i < computeMoneys.size() ; i++){
				if(computeMoneys.get(i).getClazz().equals("总计") ){
					XSSFRow rowtot = sheet.createRow(nextrow);// 在工作表中创建行对象
					sheet.addMergedRegion(new CellRangeAddress(nextrow, nextrow, 1, 2));// 合并单元格
					XSSFCell totcell = rowtot.createCell(1);
					totcell.setCellValue("总计");
					totcell.setCellStyle(cellStyle);

					XSSFCell intimes = rowtot.createCell(3);
					if(computeMoneys.get(i).getCallInCount() != null && computeMoneys.get(i).getCallInCount()!= 0)
					intimes.setCellValue(computeMoneys.get(i).getCallInCount());
					intimes.setCellStyle(cellStyle);

					XSSFCell inlen = rowtot.createCell(4);
					if(computeMoneys.get(i).getCallInTmlen() != null && computeMoneys.get(i).getCallInTmlen()!= 0)
					inlen.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallInTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					inlen.setCellStyle(cellStyle);

					XSSFCell intotal = rowtot.createCell(5);
					if(computeMoneys.get(i).getCallInTotal() != null && computeMoneys.get(i).getCallInTotal()!= 0)
					intotal.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallInTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					intotal.setCellStyle(cellStyle);

					XSSFCell outtimes = rowtot.createCell(6);
					if(computeMoneys.get(i).getCallOutCount() != null && computeMoneys.get(i).getCallOutCount()!= 0)
					outtimes.setCellValue(computeMoneys.get(i).getCallOutCount());
					outtimes.setCellStyle(cellStyle);

					XSSFCell outlen = rowtot.createCell(7);
					if(computeMoneys.get(i).getCallOutTmlen() != null && computeMoneys.get(i).getCallOutTmlen()!= 0)
					outlen.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallOutTmlen()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					outlen.setCellStyle(cellStyle);

					XSSFCell outtotal = rowtot.createCell(8);
					if(computeMoneys.get(i).getCallOutTotal() != null && computeMoneys.get(i).getCallOutTotal()!= 0)
					outtotal.setCellValue(new   BigDecimal(computeMoneys.get(i).getCallOutTotal()).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );
					outtotal.setCellStyle(cellStyle);

					XSSFCell statime = rowtot.createCell(9);
					statime.setCellValue(startTime + "/" + endTime);
					statime.setCellStyle(cellStyle);
					nextrow++;
				}
			}
				


				outputStream.flush();
				workbook.write(outputStream);// 将文档对象写入文件输出流
				outputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}