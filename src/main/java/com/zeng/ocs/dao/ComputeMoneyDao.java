package com.zeng.ocs.dao;

import java.util.List;
import java.util.Map;

import com.zeng.ocs.po.CusType;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.DtChgOrder;
import com.zeng.ocs.po.DtMessage;
import com.zeng.ocs.po.DtMultiVoice;
import com.zeng.ocs.po.DtOlService;
import com.zeng.ocs.po.DtVoice;
import com.zeng.ocs.po.MoneyStandard;
import com.zeng.ocs.util.PaginationUtil;

public interface ComputeMoneyDao {
	List<Customer> selectAllCustomer();//查询公司信息
	List<MoneyStandard> selectMoneyStandardForCustomer(Customer customer);//查询公司的计费标准 
	List<CusType> selectCusType(Customer customer);//查询公司的产品大类
	
	List<DtVoice> selectDtVoiceByCondition(Map map);//获取公司的语音明细
	
	List<DtOlService> selectDtOlServiceByCondition(Map map);//查询公司的非语音明细
	List<DtChgOrder> selectDtChgOrderByCondition(Map map);//查询公司的非语音明细
	List<DtMessage> selectDtMessage(Map map);//查询公司的短信明细
	List<DtMultiVoice>  selectDtMultiVoiceByCondition(Map map);
	
	
	List<DtVoice> selectDtVoiceForComputMoney(Customer customer);//查询公司的语音明细
	
	
	//2 搜索，条件搜索客户
	Customer selectCustomerForSearch(PaginationUtil paginationUtil);
	List<CusType> selectCusTypeForSearch(Map<String,String> mapCondition);
	
	List<DtVoice> selectDtVoiceForSea(Map map);//获取公司语音时长
	List<DtMultiVoice> selectDtMultiVoiceForSea(Map map);//获取公司非语音时长
	List<DtOlService> selectDtOlServiceForSea(Map map);//获取公司 非语音信息
	
	List<DtChgOrder>  selectDtChgOrderForSea(Map map);//获取公司 改单时长
	
	List<DtMessage> selectDtMessageForSea(Map map);//查询公司的短信条数
	
//	3.统计group by
	List<DtVoice> selectDtVoiceForGroup(Map map);//获取公司语音时长
	List<DtMultiVoice> selectDtMultiVoiceForGroup(Map map);//获取公司非语音时长
	List<DtOlService> selectDtOlServiceForGroup(Map map);//获取公司 非语音信息
	List<DtChgOrder>  selectDtChgOrderForGroup(Map map);//获取公司 改单时长
	List<DtMessage> selectDtMessageForGroup(Map map);//查询公司的短信条数
	
	//统计所有公司语音	
	List<DtVoice> selectAllDtVoice(Map  map);
//	统计所有公司多媒体语音
	List<DtMultiVoice> selectAllDtMultiVoice(Map  map);
	//统计所有公司非语音数据
	List<DtOlService> selectAllDtOlService(Map  map);
	//统计所有公司补单改单书籍
	List<DtChgOrder>  selectAllDtChgOrder(Map  map);
	//统计所有公司短信
	List<DtMessage> selectAllDtMessage(Map  map);
	
	//获取所有公司计费标准
	List<MoneyStandard> selectAllMoneyStandard();
}
