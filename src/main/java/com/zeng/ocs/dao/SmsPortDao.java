package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.SmsPort;
import com.zeng.ocs.util.PaginationUtil;

public interface SmsPortDao{
    int deleteByPrimaryKey(Long id);

    int insert(SmsPort record);

    int insertSelective(SmsPort record);

    SmsPort selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsPort record);

    int updateByPrimaryKey(SmsPort record);
    
    
    
    //按条件查询
    List<SmsPort> selectSmsPortByCondition(PaginationUtil page);
     
    /**
     * 条件查询或无条件查询，计数
     * @param page
     * @return
     */
    Long selectCountByCondition(PaginationUtil page);
    
    /**
     * 添加归属
     * @param smsPort
     * @return
     */
    List<String> insertSelectSmsPort(SmsPort smsPort);
    void insertSmsPort(SmsPort smsPort);
    //更新查询
    String selectCusId(SmsPort smsPort);
//    void updateCustomer(SmsPort smsPort);
    void updateSmsPort(SmsPort smsPort);
    
    List<SmsPort> selectSmsPortForExport();
    
    List<SmsPort> selectSmsPortForImport(SmsPort smsPort);//导入查询
    void updateSmsPortForImport(SmsPort smsPort);//导入更新
//    void batchInsert(List<SmsPort> smsPorts);//批量导入
    List<String> selectCusidByCusName(SmsPort smsPort);
    
    void deleteSmsPort(SmsPort smsPort);
    
    void insertSelectiveMulti981(SmsPort smsPort);
    
}