package com.zeng.ocs.service;

import java.util.List;

import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.util.PageBean;
/**
 * 客户管理的业务层接口
 * @author cxs
 *
 */
public interface CustomerService {

	public PageBean<CustomerVo> showCustomerByLimit(int pageNo, int pageSize);

	public CustomerVo findCustomerById(Integer id);

	public void updateCustomer(CustomerVo customerVo);

	public void deleteCustomerById(Integer id);

	public void deleteCustomerByIds(List<Integer> ids);

	public void addCustomer(CustomerVo customerVo);

	public CustomerVo findCustomerByCusid(String cusid);
	
	public List<CustomerVo> findAllCustomer();

	/**
	 * @author  Jianghai Yang
	 * @date	2018年3月27日下午3:41:57
	 */
	public PageBean<CustomerVo> showCustomerBySearch(String cusName);
	
	public CustomerVo findCustomerByCusName(String cusname);
}
