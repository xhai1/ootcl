package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.Customer;

public interface CustomerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
//    hotline添加查询
    List<Customer> selectCutomerBytelephoneAndCusname(Customer record);
    
    
    public void updateBycusId(Customer record);//导入更新
    
    
    public List<Customer> selectAll();//计费标准显示公司列表
    
    
    
    //更具公司名称查询公司是否存在
    public Customer selectCustomerByCusName(String cusname);
    
}