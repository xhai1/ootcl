package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.CustomerVo;
/**
 * 客户管理的dao层接口
 * @author cxs
 *
 */
public interface ClientDao {

	public int findCount();

	public List<CustomerVo> findAllCustomer();

	public CustomerVo findCustomerById(Integer id);

	public void updateCustomer(CustomerVo customerVo);

	public void deleteCustomerById(Integer id);

	public void deleteCustomers(List<Integer> ids);

	public void addCustomer(CustomerVo customerVo);

	public List<CustomerVo> findCustomerByCusid(String cusid);
	
	
	public List<CustomerVo> selectForSearch(String cusName);
	
	public List<CustomerVo> findCustomerByCusName(String cusname);
}