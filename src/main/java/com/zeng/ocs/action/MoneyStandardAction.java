package com.zeng.ocs.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.MoneyStandard;
import com.zeng.ocs.po.MoneyStandardModel;
import com.zeng.ocs.po.MutilMedia981;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.MoneyStandardService;
import com.zeng.ocs.util.PaginationUtil;
@Controller
@RequestMapping(value="/moneyStandard")
public class MoneyStandardAction {
	
	@Autowired
	MoneyStandardService moneyStandardService;
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/showCusStandard")
	public ModelAndView showCusStandard(){

		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/CusTimeRecordshow.jsp");
		List<Customer> customers = moneyStandardService.getAllCus();
		Integer rand = (int) (customers.size() * Math.random());
		
		
		
		
		Customer customer = customers.get(rand);
		
		
		customer.setCusid(customer.getCusid());
		List<MoneyStandard> moneyStandards = moneyStandardService.showCusStandard(customer);
		
		
		mv.addObject("customers", customers);
		mv.addObject("customer", customer);
		mv.addObject("moneyStandards",moneyStandards);
		
		int classNum = moneyStandards.size();
		if(moneyStandards != null)
		for(MoneyStandard ms : moneyStandards){
			if((ms != null) && ms.getioType()=="1" && (ms.getmType() == 1))
				classNum++;
		}
		mv.addObject("classNum", classNum);
		return mv;
		
	}
	
	
	@RequestMapping("/searchCusStandard")
	public ModelAndView searchCusStandard(Customer customersea){

		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/CusTimeRecordshow.jsp");
		List<Customer> customers = moneyStandardService.getAllCus();
		
		
		
		
		for(Customer cust: customers){
			if(cust.getCusname().contains(customersea.getCusname().trim())){
				customersea.setCusid(cust.getCusid());
				customersea.setCusname(cust.getCusname());
			}
		}
		//customer.setCusid("1434666736");
		List<MoneyStandard> moneyStandards = moneyStandardService.showCusStandard(customersea);
		
		
		mv.addObject("customers", customers);
		mv.addObject("customer", customersea);
		mv.addObject("moneyStandards",moneyStandards);
		
		int classNum = moneyStandards.size();
		if(moneyStandards != null)
		for(MoneyStandard ms : moneyStandards){
			if((ms != null) && ms.getioType()=="1" && (ms.getmType() == 1))
				classNum++;
		}
		mv.addObject("classNum", classNum);
		return mv;
		
	}
	
	
	@RequestMapping(value = "/updateSearchCusStandard" , method = {RequestMethod.GET})
	 @ResponseBody
	public ModelAndView updateSearchCusStandard(Customer customer){
		
		 List<MoneyStandard> moneyStandards = moneyStandardService.comListSearchMoneyStandard(customer);
			
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/CusTimeRecordshow4.jsp");
		List<Customer> customers = moneyStandardService.getAllCus();
		
		for(Customer c: customers){
			if(c.getCusid().equals(customer.getCusid()))
				customer = c;
		}
		
		mv.addObject("customers", customers);
		mv.addObject("customer", customer);
		mv.addObject("moneyStandards",moneyStandards);
		
		int classNum = moneyStandards.size();
		mv.addObject("classNum", classNum);
		return mv;
	}
	
	
	
	@RequestMapping(value = "/saveCusStandard" , method = {RequestMethod.POST})
	 @ResponseBody
	public String saveCusStandard(MoneyStandardModel moneyStandardModel){
		String result = "";
		if(moneyStandardModel == null)return result;
		List<MoneyStandard > MoneyStandards =  moneyStandardModel.getMoneyStandard();
		if(MoneyStandards != null){
			System.out.println(MoneyStandards.size());
			for(int i = 0 ; i < MoneyStandards.size(); i++){
				if(MoneyStandards.get(i).getId() == null) {
					MoneyStandards.remove(i);
					i--;
				}
			}
		}
		
		moneyStandardService.updateMoneyStandard(MoneyStandards);
		result += "<script>alert('修改成功！');location.href='comListSearchCusStandard.action?cusid="+MoneyStandards.get(0).getCusId()+"';</script>";
		String cusid=MoneyStandards.get(0).getCusId();
		CustomerVo customerVo=customerService.findCustomerByCusid(cusid);
		customerVo.setLastmodify(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		customerService.updateCustomer(customerVo);
	    return result;		
	}
	/*public String showMoneyStandardList(List<MoneyStandard >  MoneyStandards ){
		String str = "";
		for(MoneyStandard m : MoneyStandards ){
			
			str += m.toString()+"\r\n";
		}
		return str;
	} */
	
	
	
	/**
	 * 添加类别控制器
	 * @param moneyStandard
	 * @return
	 */
	@RequestMapping(value = "/addCusStandardClass" , method = {RequestMethod.GET})
	 @ResponseBody
	public ModelAndView addCusStandardClass(MoneyStandard moneyStandard){
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/moneystandardadd.jsp");
		mv.addObject("moneyStandardadd", moneyStandard);
	    return mv;		
	}
	
	@RequestMapping(value = "/addCusStandardClassDetail" , method = {RequestMethod.POST})
	 @ResponseBody
	public String addCusStandardClassDetail(MoneyStandard moneyStandard){
		moneyStandardService.addClass(moneyStandard);
		return PaginationUtil.Prompt("添加成功!",null);
//		ModelAndView mv = new ModelAndView("redirect:/moneyStandard/showCusStandard.action");
//	    return mv;
	}
	
	
	
	/**
	 * 初始化导入
	 * @param moneyStandard
	 * @return
	 */

	@RequestMapping(value = "/firstSaveCusStandard" , method = {RequestMethod.POST})
	 @ResponseBody
	public String firstSaveCusStandard(Customer customer,MoneyStandardModel moneyStandardModel){
		List<Customer> customers = moneyStandardService.getAllCus();
		for(Customer c : customers){
			if(c.getCusname().contains(customer.getCusname()))
				customer.setCusid(c.getCusid());
		}
		
		
		String result = "";
		if(moneyStandardModel == null)return result;
		List<MoneyStandard > MoneyStandards =  moneyStandardModel.getMoneyStandard();
		if(MoneyStandards != null){
			System.out.println(MoneyStandards.size());
			for(int i = 0 ; i < MoneyStandards.size(); i++){
				if((MoneyStandards.get(i).getId() == null)  &&
						(MoneyStandards.get(i).getValue() == null)  &&
						(MoneyStandards.get(i).getPrice() == null)  
						) {
					MoneyStandards.remove(i);
					i--;
				}
			}
		}
		
		for(MoneyStandard ms : MoneyStandards){
				ms.setId(null);
				ms.setCusId(customer.getCusid());
		}
		
		
		result += "<script>alert('"+moneyStandardService.firstAddMoneyStandard(MoneyStandards)+"');history.go(-1);</script>"; 
	    return result;		
	}
	
	
	
	@RequestMapping(value = "/comListSearchCusStandard" , method = {RequestMethod.GET})
	 @ResponseBody
	public ModelAndView comListSearchCusStandard(Customer customer){
		
		 List<MoneyStandard> moneyStandards = moneyStandardService.comListSearchMoneyStandard(customer);
			
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/CusTimeRecordshow.jsp");
		List<Customer> customers = moneyStandardService.getAllCus();
		
		for(Customer c: customers){
			if(c.getCusid().equals(customer.getCusid()))
				customer = c;
		}
		
		mv.addObject("customers", customers);
		mv.addObject("customer", customer);
		mv.addObject("moneyStandards",moneyStandards);
		
		int classNum = moneyStandards.size();
		mv.addObject("classNum", classNum);
		return mv;
	}
	
	@RequestMapping(value = "/ImportCusStandard" , method = {RequestMethod.GET})
	public ModelAndView  ImportCusStandard( ){
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/MoneyStdFirstimport.jsp");
		return mv;
	}
	
	@RequestMapping(value = "/FirstImportCusStandard" , method = {RequestMethod.POST})
	 @ResponseBody
	public String FirstImportCusStandard(HttpServletRequest request) throws IllegalStateException, IOException{
		File dist = null;
		String result = "";
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request))
		{
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext())
			{
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null)
				{
					File dir = new File("./upload/");
					if (!dir.exists())
						dir.mkdirs();
					// 上传
					dist = new File(dir, file.getOriginalFilename());
					if (!file.getOriginalFilename().contains("计费标准")){						
						
						 result = "<script>alert('计费标准!');"
									+ "window.location.href='showCusStandard.action'"
									+ "</script>";
							return  result;
					}
					file.transferTo(dist);
				}

			}

		}



		
		String errCusName =  moneyStandardService.FirstImporthMoneyStandard(dist);
		
		if(errCusName != ""){
			 result = "<script>alert('以下公司已初始化，\\n"+result+errCusName+"');"
						+ "window.location.href='showCusStandard.action';"
						+ "</script>";
			return  result;
		}
		return  PaginationUtil.Prompt("导入成功!","showCusStandard.action");
		
		
	}
}
