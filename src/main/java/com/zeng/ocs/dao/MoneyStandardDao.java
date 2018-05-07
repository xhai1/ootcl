package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.MoneyStandard;

public interface MoneyStandardDao {
	public List<MoneyStandard> showCusStardardByCustomer(Customer customer) ;
	public void updateByPrimaryKeySelective(MoneyStandard moneyStandard);
	public void insertSelective(MoneyStandard moneyStandard);
	
	
	public  List<MoneyStandard> firstSelectByCusid(MoneyStandard moneyStandard);
	
	public  Customer selectCustomerForImport(String cusName);
	
	public MoneyStandard selectMoneyStandarForImport(Customer customer);
	
	
	public List<MoneyStandard> selectAllMoneyStandard();
}
