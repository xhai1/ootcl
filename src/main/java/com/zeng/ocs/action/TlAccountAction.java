package com.zeng.ocs.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.po.ComputeMoney;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.service.TlAccountService;
import com.zeng.ocs.util.DateUtil;
import com.zeng.ocs.util.PaginationUtil;

/**
 * @ClassName: TlAccount.java 
 * @author: Jianghai Yang 
 * @date: 2018年3月26日 下午8:35:46
 *
 */
@Controller
@RequestMapping("/tlAccount")
public class TlAccountAction { 
	@Autowired
	TlAccountService tlAccountService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/show")
	public ModelAndView show(HttpSession session,PaginationUtil paginationUtil){
//	
		// 这里使用session
		List<CustomerVo> customerVoList = (List<CustomerVo>) session.getAttribute("customerVoList");
		if (customerVoList == null || customerVoList.size() <= 50) {
			customerVoList = customerService.findAllCustomer();
			session.setAttribute("customerVoList", customerVoList);
		}
		
		ModelAndView mv = new ModelAndView("/WEB-INF/jsp/tlaccount.jsp");
		List<ComputeMoney> computeMoneys  = tlAccountService.getMonthTotal(paginationUtil);
		
		Collections.sort(
				computeMoneys,new Comparator(){

					@Override
					public int compare(Object o1, Object o2) {
						ComputeMoney com1 = (ComputeMoney) o1;
						ComputeMoney com2 = (ComputeMoney) o2;
						if(  com1.getClazz().compareTo(com2.getClazz()) == 0){
							com1.getType().compareTo(com2.getType());
						}else {
							return  com1.getClazz().compareTo(com2.getClazz());
						}
						return 0;
					}
					
				});
		
		mv.addObject("computeMoneys", computeMoneys);
		mv.addObject("paginationUtil", paginationUtil);
		return mv;
	}
	
	@RequestMapping("/dtShow")
	public String dtShow(String month ,PaginationUtil paginationUtil){
		String [] splits = month.split("-");
		Integer year = Integer.parseInt(splits[0]);
		Integer mon =  Integer.parseInt(splits[1]);
		paginationUtil.setCondition3(DateUtil.getFirstDayOfMonth(year, mon));
		paginationUtil.setCondition4(DateUtil.getLastDayOfMonth(year, mon));
		
		return "redirect:/ComputeMoney/SearchByCondition.action?condition1="+paginationUtil.getCondition1()
		+"&condition3="+paginationUtil.getCondition3()+"&condition4="+paginationUtil.getCondition4();
		
	}

}
