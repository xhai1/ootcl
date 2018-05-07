package com.zeng.ocs.service;

import java.util.List;
import java.util.Map;

import com.zeng.ocs.po.ComputeMoney;
import com.zeng.ocs.po.CusType;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.MoneyStandard;
import com.zeng.ocs.util.PaginationUtil;

public interface ComputeMoneyService {
	
	public List<Customer> getCustomer();
	
	public List<MoneyStandard> getCustomerMoneyStandard(Customer customer);
	
	public List<CusType> getCustype(Customer customer);
	
	public List<ComputeMoney> staComputeMoney(Customer customer,List<CusType>  cusTypes,List<MoneyStandard> MoneyStandards ,String currentMonth);
	
	//统计某个公司某个月份的计费总量
	public ComputeMoney showCompanyComputeMoney();
	
	
	public Customer getCustomer(PaginationUtil paginationUtil);//更具搜索条件搜索公司
	
	public List<CusType> getCustypeByMap(Map<String,String> mapCondition);//更具搜索条件搜索公司
	//搜索统计
	public List<ComputeMoney> staComputeMoneyForSea(Customer customer,List<CusType>  cusTypes,List<MoneyStandard> MoneyStandards ,String startTime,String endTime);
	
	//统计所有公司
	
	public List<ComputeMoney> staAllComputeMoney(Map  map,List<Customer> customers);
}
