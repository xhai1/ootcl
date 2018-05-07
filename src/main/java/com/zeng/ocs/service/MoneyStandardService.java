package com.zeng.ocs.service;

import java.io.File;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.MoneyStandard;

public interface MoneyStandardService {
	
		public List<Customer> getAllCus();
		public List<MoneyStandard> showCusStandard(Customer customer);
		
		public void updateMoneyStandard(List<MoneyStandard> MoneyStandards);
		
		public void addClass(MoneyStandard moneyStandard);
		
		
		//初始化导入
		
		public String firstAddMoneyStandard(List<MoneyStandard > MoneyStandards);
		
		
		//公司列表搜索
		
		public List<MoneyStandard> comListSearchMoneyStandard(Customer customer);
		
		
		public String FirstImporthMoneyStandard(File file);
}
