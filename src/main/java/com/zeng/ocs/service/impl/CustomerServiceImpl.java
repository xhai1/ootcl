package com.zeng.ocs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zeng.ocs.dao.ClientDao;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.service.CustomerService;
import com.zeng.ocs.util.PageBean;
/**
 * 客户管理的service的实现
 * @author cxs
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private ClientDao clientDao;
	
	@Override
	public PageBean<CustomerVo> showCustomerByLimit(int pageNo, int pageSize) {
		
		PageBean<CustomerVo> pageBean = new PageBean<CustomerVo>();
		// 设置当前页数:
		pageBean.setPage(pageNo);
		// 设置每页显示记录数:
		pageBean.setLimit(pageSize);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = clientDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		PageHelper.startPage(pageNo, pageSize);
		
		List<CustomerVo> list=clientDao.findAllCustomer();
		
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<CustomerVo> showCustomerBySearch(String cusName){
		PageBean<CustomerVo> pageBean = new PageBean<CustomerVo>();
		
		List<CustomerVo> list=clientDao.selectForSearch(cusName);
		
		pageBean.setList(list);
		
		return pageBean;
	}
	
	@Override
	public CustomerVo findCustomerById(Integer id) {
		return clientDao.findCustomerById(id);
	}

	@Override
	public void updateCustomer(CustomerVo customerVo) {
		clientDao.updateCustomer(customerVo);
	}

	@Override
	public void deleteCustomerById(Integer id) {
		clientDao.deleteCustomerById(id);
	}

	@Override
	public void deleteCustomerByIds(List<Integer> ids) {
		clientDao.deleteCustomers(ids);
	}

	@Override
	public void addCustomer(CustomerVo customerVo) {
		clientDao.addCustomer(customerVo);
	}

	@Override
	public CustomerVo findCustomerByCusid(String cusid) {
		List<CustomerVo> customerList=clientDao.findCustomerByCusid(cusid);
		if(customerList.size()>0){
			return customerList.get(0);
		}
		return null;
	}

	@Override
	public List<CustomerVo> findAllCustomer() {
		return clientDao.findAllCustomer();
	}

	
	/*
	 *  动态获取公司信息，为null是表示可以添加
	 *  (non-Javadoc)
	 * @see com.zeng.ocs.service.CustomerService#findCustomerByCusName(java.lang.String)
	 */
	public CustomerVo findCustomerByCusName(String cusname){
		List<CustomerVo> customerList=clientDao.findCustomerByCusName(cusname);
		if(customerList.size()>0){
			return customerList.get(0);
		}
		return null;
	}
}
