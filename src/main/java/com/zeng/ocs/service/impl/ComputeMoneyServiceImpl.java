package com.zeng.ocs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeng.ocs.dao.ComputeMoneyDao;
import com.zeng.ocs.po.ComputeMoney;
import com.zeng.ocs.po.CusType;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.DtChgOrder;
import com.zeng.ocs.po.DtMessage;
import com.zeng.ocs.po.DtMultiVoice;
import com.zeng.ocs.po.DtOlService;
import com.zeng.ocs.po.DtVoice;
import com.zeng.ocs.po.MoneyStandard;
import com.zeng.ocs.service.ComputeMoneyService;
import com.zeng.ocs.util.PaginationUtil;
@Service("computeMoneyService")
public class ComputeMoneyServiceImpl implements ComputeMoneyService {

	@Autowired
	ComputeMoneyDao computeMoneyDao;	
	//选择所有客户，客户不多
	@Override
	public List<Customer> getCustomer() {
		 
		return computeMoneyDao.selectAllCustomer();
	}
	//获取某个公司的计费标准
	@Override
	public List<MoneyStandard> getCustomerMoneyStandard(Customer customer) {
		return  computeMoneyDao.selectMoneyStandardForCustomer(customer);
	}
	//获取某个公司的产品大类
	@Override
	public List<CusType> getCustype(Customer customer) {
		return computeMoneyDao.selectCusType(customer);
	}
	//统计每类别的呼入呼出
	@Override
	public List<ComputeMoney> staComputeMoney(Customer customer, List<CusType> cusTypes,List<MoneyStandard> MoneyStandards,String currentMonth) {		
//		统计group by
		Map<String , String> map = new HashMap<String,String>();
		map.put("currentMonth", currentMonth);
		map.put("cusid", customer.getCusid());
//		统计所有记录
		List<ComputeMoney> returnComputeMoneys= new ArrayList<ComputeMoney>();
		
//		取出所有语音中产品大类的呼入、呼出，以及所有的历史记录（money)
		List<DtVoice>  dtVoices= computeMoneyDao.selectDtVoiceByCondition(map);
//		取出所有的多媒体语音中所有产品大类的呼入、呼出，以及所有的历史记录（money)
		List<DtMultiVoice>  dtMultiVoices= computeMoneyDao.selectDtMultiVoiceByCondition(map);
//		取出所有非语音中产品大类的呼入，以及所有的历史记录（money)
		List<DtOlService> dtOlServices =  computeMoneyDao.selectDtOlServiceByCondition(map);
//		取出所有的改单补单的呼入，以及所有的历史记录（money)
		List<DtChgOrder> dtChgOrders = computeMoneyDao.selectDtChgOrderByCondition(map);
//		取出所有短信所有的历史记录（money)
		List<DtMessage>  dtMessages = computeMoneyDao.selectDtMessage(map);
		
//		一次获取非语音计费标准
		List<MoneyStandard> moneyStandards = getMoneyStandardNo(MoneyStandards);
		
		for(CusType ct : cusTypes){
//			计算每个产品大类语音
				MoneyStandard moneyStandard = getCustomerMoneyStandard(MoneyStandards,0,"0",null);//语音当前计费标准
			
				ComputeMoney computeMoney = new ComputeMoney();
				List<DtVoice> shortDtVoice = getOneCusTypeDtVoice(dtVoices,ct);
				for(DtVoice dvs : shortDtVoice){
					if( (dvs.getNtype() != null) && dvs.getNtype().equals("呼入")){
						computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()) + dvs.getId());//id保存统计次数
						computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen()) + dvs.getTotalseconds() * 1.0 );
						computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal() ) + getOneCusTypeDtVoiceMoney(dvs,moneyStandard,true));
					}else if(dvs.getNtype() != null){
						computeMoney.setCallOutCount( (computeMoney.getCallOutCount() == null ? 0 : computeMoney.getCallOutCount()) + dvs.getId());//id保存统计次数
						computeMoney.setCallOutTmlen( (computeMoney.getCallOutTmlen() == null ? 0.0 : computeMoney.getCallOutTmlen() * 1.0) + dvs.getTotalseconds() * 1.0 );
						computeMoney.setCallOutTotal( (computeMoney.getCallOutTotal() == null ? 0.0 : computeMoney.getCallOutTotal() * 1.0 ) + getOneCusTypeDtVoiceMoney(dvs,moneyStandard,false));
					}
					
				}
			
//			计算每个产品大类多媒体语音
			List<DtMultiVoice> shortDtMultiVoice = getOneCusTypeDtMultiVoice(dtMultiVoices,ct);
			for(DtMultiVoice dmv : shortDtMultiVoice){
				if( (dmv.getNtype() != null) && dmv.getNtype().equals("呼入")){
					computeMoney.setCallInCount(computeMoney.getCallInCount() + dmv.getId());//id保存统计次数
					computeMoney.setCallInTmlen(  computeMoney.getCallInTmlen() + (dmv.getTotalseconds() ==null ? 0.0 :  dmv.getTotalseconds())* 1.0);
					computeMoney.setCallInTotal(computeMoney.getCallInTotal() + getOneCusTypeDtMultiVoiceMoney(dmv,moneyStandard,true));
				}else if(dmv.getNtype() != null){
					computeMoney.setCallOutCount( computeMoney.getCallOutCount() + dmv.getId());//id保存统计次数
					computeMoney.setCallOutTmlen( computeMoney.getCallOutTmlen() + (dmv.getTotalseconds() ==null ? 0.0 : dmv.getTotalseconds()) * 1.0 );
					computeMoney.setCallOutTotal( computeMoney.getCallOutTotal() + getOneCusTypeDtMultiVoiceMoney(dmv,moneyStandard,false));
				}
			}

			
//			语音统计数据加入集合
			computeMoney.setCusName(customer.getCusname());
			computeMoney.setType(ct.getTypetimeing());
			computeMoney.setClazz("语音");			
			returnComputeMoneys.add(computeMoney);
			
//			计算每个产品大类非语音			
			for(MoneyStandard ms : moneyStandards){
				if( (ms.getClazz() != null) && (ms.getClazz().equals("补单") || ms.getClazz().equals("改单") ))continue;
				computeMoney = new ComputeMoney();
				computeMoney.setType(ct.getTypetimeing());
				computeMoney.setClazz( ms.getClazz());
				List<DtOlService> shortdtOlService = getOneCusTypeDtOlService(dtOlServices,ct,ms);
				computeMoney.setCallInCount(getOneCusTypeDtOlServiceCallCount(shortdtOlService));
				
				computeMoney.setCallInTmlen(getOneCusTypeDtOlServiceCallTmlen(shortdtOlService,ms));
				
				computeMoney.setCallInTotal(getOneCusTypeDtOlServiceCallTotal(shortdtOlService,ms));
				returnComputeMoneys.add(computeMoney);
			}			
		
//			计算每个产品大类补单、改单			
			moneyStandard = getCustomerMoneyStandard(MoneyStandards,1,"1","补单");
			List<DtChgOrder> shortDtChgOrder = getOneCusTypeDtChgOrder(dtChgOrders,ct,moneyStandard);
			computeMoney = new ComputeMoney();//补单
			computeMoney.setType(ct.getTypetimeing());
			if(moneyStandard != null){
				computeMoney.setClazz(moneyStandard.getClazz());
				for(DtChgOrder dco : shortDtChgOrder){		
					if(dco.getMoney() == null && dco.getValue() == null )
					{
						computeMoney.setCallInCount( ( computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount() ) + dco.getId() );
						
						computeMoney.setCallInTmlen( ( computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen() ) + dco.getId() * moneyStandard.getValue() );
						
						computeMoney.setCallInTotal( ( computeMoney.getCallInTotal()== null  ? 0 :computeMoney.getCallInTotal() ) +  dco.getId() * moneyStandard.getPrice() * moneyStandard.getValue() );
					}else{
						computeMoney.setCallInCount( ( computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount() ) + dco.getId() );
						
						computeMoney.setCallInTmlen( ( computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen() ) + dco.getId() * (dco.getValue() == null ? moneyStandard.getValue() : dco.getValue()) );
						
						computeMoney.setCallInTotal( ( computeMoney.getCallInTotal()== null  ? 0 :computeMoney.getCallInTotal() ) +  dco.getId() *  (dco.getValue() == null ? moneyStandard.getValue() : dco.getValue()) * (dco.getValue() == null ? moneyStandard.getPrice() : dco.getMoney()) );
					}
				}
			}
			returnComputeMoneys.add(computeMoney);
			//计算改单
			moneyStandard = getCustomerMoneyStandard(MoneyStandards,1,"1","改单");
			shortDtChgOrder = getOneCusTypeDtChgOrder(dtChgOrders,ct,moneyStandard);
			computeMoney = new ComputeMoney();
			computeMoney.setType(ct.getTypetimeing());
			if(moneyStandard != null){
				computeMoney.setClazz(moneyStandard.getClazz());
				for(DtChgOrder dco : shortDtChgOrder){		
					if(dco.getMoney() == null && dco.getValue() == null )
					{
						computeMoney.setCallInCount( ( computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount() ) + dco.getId() );
						
						computeMoney.setCallInTmlen( ( computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen() ) + dco.getId() * (dco.getValue() == null ? moneyStandard.getValue() : dco.getValue()) );
						
						computeMoney.setCallInTotal( ( computeMoney.getCallInTotal()== null  ? 0 :computeMoney.getCallInTotal() ) +  dco.getId() * moneyStandard.getPrice() * moneyStandard.getValue() );
					}else{
						computeMoney.setCallInCount( ( computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount() ) + dco.getId() );
						
						computeMoney.setCallInTmlen( ( computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen() ) + dco.getId() * (dco.getValue() == null ? moneyStandard.getValue() : dco.getValue()));
						
						computeMoney.setCallInTotal( ( computeMoney.getCallInTotal()== null  ? 0 :computeMoney.getCallInTotal() ) +  dco.getId() * (dco.getValue() == null ? moneyStandard.getValue() : dco.getValue()) * (dco.getValue() == null ? moneyStandard.getPrice() : dco.getMoney()));
					}
				}
			}
			returnComputeMoneys.add(computeMoney);
		}
		
		for(ComputeMoney cm : returnComputeMoneys){
			if(cm.getCallInTmlen() != null){
				cm.setCallInTmlen(cm.getCallInTmlen() / 60.0);
			}
			if(cm.getCallInTotal() != null){
				cm.setCallInTotal(cm.getCallInTotal() / 60.0);
			}
			if(cm.getCallOutTmlen() != null){
				cm.setCallOutTmlen(cm.getCallOutTmlen() / 60.0);
			}
			if(cm.getCallOutTotal() != null){
				cm.setCallOutTotal(cm.getCallOutTotal() / 60.0);
			}
		}
		
		
//			计算短信
		MoneyStandard moneyStandard = getCustomerMoneyStandard(MoneyStandards,2,null,null);
		ComputeMoney computeMoney = new ComputeMoney();
		for(DtMessage dm : dtMessages){
			if(dm.getMoney() == null){
				computeMoney.setCallInCount(new Long(  (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()) + dm.getId() ).intValue() );
				computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0 :computeMoney.getCallInTotal()) + dm.getId() * moneyStandard.getValue() );
			}else{
				computeMoney.setCallInCount(new Long(  (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()) + dm.getId()  ).intValue() );
				computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0 :computeMoney.getCallInTotal()) + dm.getId() * dm.getMoney() );
			}
		}
		computeMoney.setCallInCount(new Long(getDtMessageCount(dtMessages)).intValue());
		returnComputeMoneys.add(computeMoney);
		
		return returnComputeMoneys;
		
	}
	
	public Integer getDtVoiceCallCount(List<DtVoice> dtVoices,Boolean flag){//true 计算呼入次数 false 计算呼出次数
		
		Integer callCount = 0;
		if(flag == true)
			for(DtVoice dt : dtVoices){
				if( (dt.getNtype() != null) && dt.getNtype().equals(" 呼入"))
					callCount += dt.getId();
			}
		else
			for(DtVoice dt : dtVoices){
				if( (dt.getNtype() != null) && dt.getNtype().equals(" 呼出"))
					callCount += dt.getId();
			}
		return callCount;
	}
	
	public Integer getDtMultiVoiceCallCount(List<DtMultiVoice>  dtMultiVoices,Boolean flag){//true 计算呼入次数 false 计算呼出次数
		Integer callCount = 0;
		if(flag == true)
			for(DtMultiVoice dt : dtMultiVoices){
				if( (dt.getNtype() != null) && dt.getNtype().equals(" 呼入"))
					callCount += dt.getId();
			}
		else
			for(DtMultiVoice dt : dtMultiVoices){
				if( (dt.getNtype() != null) && dt.getNtype().equals(" 呼出"))
					callCount += dt.getId();
			}
		return callCount;
	}
	
	//计算短信数量
	public Long getDtMessageCount(List<DtMessage>  dtMessages){
		Long mesCount = 0L;
		for(DtMessage dm : dtMessages){
			mesCount += dm.getId();
		}
		return mesCount;
	}
	
	//计算短信
	public Double computeTotalForDtMessage(List<DtMessage>  dtMessages, MoneyStandard moneyStandard ){
		Double totalMoney = 0.0;
		for(int i = 0 ;i < dtMessages.size() ; i++ ){
			if(dtMessages.get(i).getMoney() == null){
				totalMoney+= dtMessages.get(i).getId() * moneyStandard.getValue();
			}else{
				totalMoney+=  dtMessages.get(i).getId() *  dtMessages.get(i).getMoney();
			}
		}
		return totalMoney;
	}
	
	//计算改单、补单呼入次数
	
	public Integer getDtChgOrderCallCount(List<DtChgOrder> dtChgOrders,MoneyStandard moneyStandard){
		Integer callCount = 0;
		
		for(DtChgOrder dco : dtChgOrders){
			if( (dco.getMode() != null) && dco.getMode().equals(moneyStandard.getClazz()) )
					callCount += dco.getId();
		}
		return callCount;
	}
	
	//计算改单，补单
	public Double computeTotalForDtOrder(List<DtChgOrder> dtChgOrders, MoneyStandard moneyStandard ){
		Double totalMoney = 0.0;
		Double per = moneyStandard.getValue() * moneyStandard.getPrice()/moneyStandard.getmType();//补单改单一单多少钱
		for(DtChgOrder dco : dtChgOrders){
			if(( dco.getMode() != null) && dco.getMode().equals(moneyStandard.getClazz())){
				if(dco.getMoney() == null )
					totalMoney += per;
				else 
					totalMoney +=  dco.getId() * dco.getMoney();
			}
		}
		return totalMoney / moneyStandard.getTimes();
	}
	
	
	//计算呢非语音
	public Double computeTotalForDtOlService(List<DtOlService> dtOlService, MoneyStandard moneyStandard ){
		Double totalMoney = 0.0;
		Double per = moneyStandard.getValue() * moneyStandard.getPrice() /moneyStandard.getNumber();//非语音一单多少钱
		for(int i = 0 ;i < dtOlService.size() ; i++ ){
			if( (dtOlService.get(i).getMediatype() != null) && dtOlService.get(i).getMediatype().equals(moneyStandard.getClazz()) ){
				if (dtOlService.get(i).getMoney() == null)
					totalMoney += per;
				else
					totalMoney += dtOlService.get(i).getId() * dtOlService.get(i).getMoney() ;
			}
		}
		return totalMoney / moneyStandard.getTimes();
	}
	
	
	//语音金额计算
	public Double computeTotalForDtVoice(List<DtVoice> dtVoices, MoneyStandard moneyStandard ,Boolean flag){//true 计算输入  false 计算输出
		Double totalMoney = 0.0;
		if(flag == true){
			for(DtVoice dt : dtVoices){
				if((dt.getNtype() != null) && dt.getNtype().equals("呼入")){
					if( dt.getMoney() == null )
						totalMoney += moneyStandard.getValue() * dt.getTotalseconds() / moneyStandard.getNumber();
					else 
						totalMoney +=  dt.getTotalseconds() * dt.getMoney();
				}
			}
		}else{
			for(DtVoice dt : dtVoices){
				if((dt.getNtype() != null) && dt.getNtype().equals("呼出")){
					if( dt.getMoney() == null )
						totalMoney+= moneyStandard.getPrice() * dt.getTotalseconds() /moneyStandard.getTimes();
					else 
						totalMoney += dt.getTotalseconds() * dt.getMoney();
				}
			}
		}
		return totalMoney / 60.0;
	}
	//多媒体语音金额计算
	public Double computeTotalForDtMultiVoice(List<DtMultiVoice> dtMultiVoices, MoneyStandard moneyStandard,Boolean flag){//同语音
		Double totalMoney = 0.0;
		if(flag == true){
			for( DtMultiVoice dtm : dtMultiVoices ){
				if( (dtm.getNtype() != null) && dtm.getNtype().equals("呼入") ){
					if(dtm.getMoney() == null)
						totalMoney += moneyStandard.getValue() * dtm.getTotalseconds() /moneyStandard.getNumber();
					else
						totalMoney += dtm.getTotalseconds() * dtm.getMoney();
				}
			}
		}else{
			for(DtMultiVoice dtm : dtMultiVoices ){
				if( (dtm.getNtype() != null) && dtm.getNtype().equals("呼出") ){
					if(dtm.getMoney() == null)
						totalMoney += moneyStandard.getPrice() * dtm.getTotalseconds() /moneyStandard.getTimes();
					else
						totalMoney += dtm.getTotalseconds() * dtm.getMoney();
				} 
			}
		}
		
		return totalMoney / 60.0;
	}
	
	//计算非语音呼入次数
	public Integer getDtOlCallCount(List<DtOlService> dtOlServices,String type){
		Integer callCount = 0 ;
		for(DtOlService dos : dtOlServices){
			if( (dos.getMediatype() != null) && dos.getMediatype().equals(type) )
				callCount += dos.getId();
		}
		return callCount;
	}
	
	//获取非语音时长
/*	public Integer getMoneyStandardNoLen(List<DtOlService> dtOlService,MoneyStandard moneyStandard){
		Integer NoLen = 0;
		for(DtOlService dos : dtOlService ){
			if( (dos.getMediatype() != null) && dos.getMediatype().equals(moneyStandard.getClazz()) )
				NoLen += dos.getTotalseconds();
		}
		return NoLen ;
	}*/
	
	//获取非语音计费标准
	public List<MoneyStandard> getMoneyStandardNo(List<MoneyStandard> MoneyStandards){
		List<MoneyStandard> MoneyStandards2 = new ArrayList<MoneyStandard>();
		for(int i = 0 ; i< MoneyStandards.size() ; i++ ){
			if( (MoneyStandards.get(i).getmType() == 1)  &&  (MoneyStandards.get(i).getioType().equals("1")) ){
				MoneyStandards2.add(MoneyStandards.get(i));
			}
		}
		return MoneyStandards2;
	}
	
	public Integer getDtMultiVoiceCallTm(List<DtMultiVoice>  dtMultiVoices ,Boolean flag){//true计算呼入时长 false 计算呼出时长
		Integer callInTm = 0;
		if(flag == true)
			for(DtMultiVoice dtm : dtMultiVoices){
				if(dtm.getNtype().equals("呼入"))
					callInTm += dtm.getTotalseconds();//0 时长
			}
		else
			for(DtMultiVoice dtm : dtMultiVoices){
				if(dtm.getNtype().equals("呼出"))
				callInTm += dtm.getTotalseconds();//0 时长
			}
			
		return callInTm;
	}
	
	public Double getCallTm(List<DtVoice>  dtVoices, Boolean flag){ //true 计算呼入时长 flase 计算呼出时长
		Double callInTm = 0.0;
		if(flag == true)
		for(DtVoice dt : dtVoices ){
				if( (dt.getNtype() != null) && dt.getNtype().equals("呼入"))
					callInTm += dt.getTotalseconds();//0 时长
		}
		else
			for(DtVoice dt : dtVoices ){
				if( (dt.getNtype() != null) && dt.getNtype().equals("呼出"))
					callInTm += dt.getTotalseconds();//0 时长
		}
		return callInTm;
	}
	
	//获取语音计费标准
	public MoneyStandard getCustomerMoneyStandard(List<MoneyStandard> MoneyStandards,Integer con1, String con2 ,String con3){
		MoneyStandard moneyStandard = null;
		for(MoneyStandard ms : MoneyStandards){
			if(  (ms.getmType() == con1)  && ( ms.getioType()==con2 || ms.getioType().equals(con2)) && ( ms.getClazz() == con3 || ms.getClazz().equals(con3))  ){
				moneyStandard = ms;
				break;
			}
		}
		return moneyStandard;
	}
	
	@Override
	public ComputeMoney showCompanyComputeMoney() {
		
		List<Customer> customers = computeMoneyDao.selectAllCustomer();
		Customer customer = getRandCustomer(customers);
		
		List<CusType>  cusTypes  = computeMoneyDao.selectCusType(customer);
		List<MoneyStandard>  moneyStandards = computeMoneyDao.selectMoneyStandardForCustomer(customer);
		List<DtVoice> dtVoices =  computeMoneyDao.selectDtVoiceForComputMoney(customer);
		
		
		return null;
	}
	
	Customer getRandCustomer(List<Customer> customers){
		int rand = (int) (Math.random() * customers.size());
		return customers.get(rand);
	}
	
	
	//条件搜索开始
	//搜索指定客户
	@Override
	public Customer getCustomer(PaginationUtil paginationUtil) {

		return computeMoneyDao.selectCustomerForSearch(paginationUtil);
	}
	@Override
	public List<CusType>  getCustypeByMap(Map<String,String> mapCondition) {
		return computeMoneyDao.selectCusTypeForSearch(mapCondition);
	}
	@Override
	public List<ComputeMoney> staComputeMoneyForSea(Customer customer, List<CusType> cusTypes,
			List<MoneyStandard> MoneyStandards, String startTime,String endTime) {
		
//		统计group by
		Map<String , String> map = new HashMap<String,String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("cusid", customer.getCusid());
//		统计所有记录
		List<ComputeMoney> returnComputeMoneys= new ArrayList<ComputeMoney>();
		
//		取出所有语音中产品大类的呼入、呼出，以及所有的历史记录（money)
		List<DtVoice>  dtVoices= computeMoneyDao.selectDtVoiceForGroup(map);
//		取出所有的多媒体语音中所有产品大类的呼入、呼出，以及所有的历史记录（money)
		List<DtMultiVoice>  dtMultiVoices= computeMoneyDao.selectDtMultiVoiceForGroup(map);
//		取出所有非语音中产品大类的呼入，以及所有的历史记录（money)
		List<DtOlService> dtOlServices =  computeMoneyDao.selectDtOlServiceForGroup(map);
//		取出所有的改单补单的呼入，以及所有的历史记录（money)
		List<DtChgOrder> dtChgOrders = computeMoneyDao.selectDtChgOrderForGroup(map);
//		取出所有短信所有的历史记录（money)
		List<DtMessage>  dtMessages = computeMoneyDao.selectDtMessageForGroup(map);
		
//		一次获取非语音计费标准
		List<MoneyStandard> moneyStandards = getMoneyStandardNo(MoneyStandards);
		
		for(CusType ct : cusTypes){
//			计算每个产品大类语音
				MoneyStandard moneyStandard = getCustomerMoneyStandard(MoneyStandards,0,"0",null);//语音当前计费标准
			
				ComputeMoney computeMoney = new ComputeMoney();
				List<DtVoice> shortDtVoice = getOneCusTypeDtVoice(dtVoices,ct);//根据
				for(DtVoice dvs : shortDtVoice){
					if( (dvs.getNtype() != null) && dvs.getNtype().equals("呼入")){
						computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()) + dvs.getId());//id保存统计次数
						computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen()) + dvs.getTotalseconds() * 1.0 );
						computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal() ) + getOneCusTypeDtVoiceMoney(dvs,moneyStandard,true));
					}else if(dvs.getNtype() != null){
						computeMoney.setCallOutCount( (computeMoney.getCallOutCount() == null ? 0 : computeMoney.getCallOutCount()) + dvs.getId());//id保存统计次数
						computeMoney.setCallOutTmlen( (computeMoney.getCallOutTmlen() == null ? 0.0 : computeMoney.getCallOutTmlen() * 1.0) + dvs.getTotalseconds() * 1.0 );
						computeMoney.setCallOutTotal( (computeMoney.getCallOutTotal() == null ? 0.0 : computeMoney.getCallOutTotal() * 1.0 ) + getOneCusTypeDtVoiceMoney(dvs,moneyStandard,false));
					}
					
				}
			
//			计算每个产品大类多媒体语音
			List<DtMultiVoice> shortDtMultiVoice = getOneCusTypeDtMultiVoice(dtMultiVoices,ct);
			for(DtMultiVoice dmv : shortDtMultiVoice){
				if( (dmv.getNtype() != null) && dmv.getNtype().equals("呼入")){
					computeMoney.setCallInCount((computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()) + dmv.getId());//id保存统计次数
					computeMoney.setCallInTmlen(  (computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen()) + (dmv.getTotalseconds() ==null ? 0.0 :  dmv.getTotalseconds())* 1.0);
					computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal() ) + getOneCusTypeDtMultiVoiceMoney(dmv,moneyStandard,true));
				}else if(dmv.getNtype() != null){
					computeMoney.setCallOutCount( (computeMoney.getCallOutCount() == null ? 0 : computeMoney.getCallOutCount()) + dmv.getId());//id保存统计次数
					computeMoney.setCallOutTmlen( (computeMoney.getCallOutTmlen() == null ? 0.0 : computeMoney.getCallOutTmlen() * 1.0) + (dmv.getTotalseconds() ==null ? 0.0 : dmv.getTotalseconds()) * 1.0 );
					computeMoney.setCallOutTotal( (computeMoney.getCallOutTotal() == null ? 0.0 : computeMoney.getCallOutTotal() * 1.0 ) + getOneCusTypeDtMultiVoiceMoney(dmv,moneyStandard,false));
				}
			}

//			语音统计数据加入集合
			computeMoney.setCusName(customer.getCusname());
			computeMoney.setType(ct.getTypetimeing());
			computeMoney.setClazz("语音");			
			returnComputeMoneys.add(computeMoney);
			
//			计算每个产品大类非语音			
			for(MoneyStandard ms : moneyStandards){
				if( (ms.getClazz() != null) && (ms.getClazz().equals("补单") || ms.getClazz().equals("改单") ))continue;
				computeMoney = new ComputeMoney();
				computeMoney.setType(ct.getTypetimeing());
				computeMoney.setClazz( ms.getClazz());
				List<DtOlService> shortdtOlService = getOneCusTypeDtOlService(dtOlServices,ct,ms);
				computeMoney.setCallInCount(getOneCusTypeDtOlServiceCallCount(shortdtOlService));
				
				computeMoney.setCallInTmlen(getOneCusTypeDtOlServiceCallTmlen(shortdtOlService,ms));
				
				computeMoney.setCallInTotal(getOneCusTypeDtOlServiceCallTotal(shortdtOlService,ms));
				returnComputeMoneys.add(computeMoney);
			}			
		
//			计算每个产品大类补单、改单			
			moneyStandard = getCustomerMoneyStandard(MoneyStandards,1,"1","补单");
			List<DtChgOrder> shortDtChgOrder = getOneCusTypeDtChgOrder(dtChgOrders,ct,moneyStandard);
			computeMoney = new ComputeMoney();//补单
			computeMoney.setType(ct.getTypetimeing());
			if(moneyStandard != null){
				computeMoney.setClazz(moneyStandard.getClazz());
				for(DtChgOrder dco : shortDtChgOrder){		
					if(dco.getMoney() == null && dco.getValue() == null)
					{
						computeMoney.setCallInCount( ( computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount() ) + dco.getId() );
						computeMoney.setCallInTmlen( ( computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen() ) + dco.getId() * moneyStandard.getValue() );
						computeMoney.setCallInTotal( ( computeMoney.getCallInTotal()== null  ? 0.0 :computeMoney.getCallInTotal() ) +  dco.getId() * moneyStandard.getPrice() * moneyStandard.getValue() );
					}else{
						computeMoney.setCallInCount( ( computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount() ) + dco.getId() );
						computeMoney.setCallInTmlen( ( computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen() ) + dco.getId() * dco.getValue() );
						computeMoney.setCallInTotal( ( computeMoney.getCallInTotal()== null  ? 0.0 :computeMoney.getCallInTotal() ) +  dco.getId() * dco.getValue() * dco.getMoney() );
					}
				}
			}
					
			returnComputeMoneys.add(computeMoney);
			//计算改单
			moneyStandard = getCustomerMoneyStandard(MoneyStandards,1,"1","改单");
			shortDtChgOrder = getOneCusTypeDtChgOrder(dtChgOrders,ct,moneyStandard);
			computeMoney = new ComputeMoney();
			computeMoney.setType(ct.getTypetimeing());
			if(moneyStandard != null){
				computeMoney.setClazz(moneyStandard.getClazz());
				for(DtChgOrder dco : shortDtChgOrder){		
					if(dco.getMoney() == null && dco.getValue() == null)
					{
						computeMoney.setCallInCount( ( computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount() ) + dco.getId() );
						computeMoney.setCallInTmlen( ( computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen() ) + dco.getId() * moneyStandard.getValue() );
						computeMoney.setCallInTotal( ( computeMoney.getCallInTotal()== null  ? 0.0 :computeMoney.getCallInTotal() ) +  dco.getId() * moneyStandard.getPrice() * moneyStandard.getValue());
					}else{
						computeMoney.setCallInCount( ( computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount() ) + dco.getId() );
						computeMoney.setCallInTmlen( ( computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen() ) + dco.getId() * dco.getValue() );
						computeMoney.setCallInTotal( ( computeMoney.getCallInTotal()== null  ? 0.0 :computeMoney.getCallInTotal() ) +  dco.getId() * dco.getValue() * dco.getMoney() );
					}
				}
			}
			
			returnComputeMoneys.add(computeMoney);
		}
		//减小误差
		for(ComputeMoney cm : returnComputeMoneys){
					if(cm.getCallInTmlen() != null){
						cm.setCallInTmlen(cm.getCallInTmlen() / 60.0);
					}
					if(cm.getCallInTotal() != null){
						cm.setCallInTotal(cm.getCallInTotal() / 60.0);
					}
					if(cm.getCallOutTmlen() != null){
						cm.setCallOutTmlen(cm.getCallOutTmlen() / 60.0);
					}
					if(cm.getCallOutTotal() != null){
						cm.setCallOutTotal(cm.getCallOutTotal() / 60.0);
					}
		}		
		
		
//			计算短信
		MoneyStandard moneyStandard = getCustomerMoneyStandard(MoneyStandards,2,null,null);
		ComputeMoney computeMoney = new ComputeMoney();
		for(DtMessage dm : dtMessages){
			if(dm.getMoney() == null){
				computeMoney.setCallInCount(new Long(  (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()) + dm.getId() ).intValue() );
				if(moneyStandard != null)
					computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 :computeMoney.getCallInTotal()) + dm.getId() * moneyStandard.getValue() );
			}else{
				computeMoney.setCallInCount(new Long(  (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()) + dm.getId()  ).intValue() );
				if(moneyStandard != null)
					computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 :computeMoney.getCallInTotal()) + dm.getId() * dm.getMoney() );
			}
		}
		computeMoney.setCallInCount(new Long(getDtMessageCount(dtMessages)).intValue());
		returnComputeMoneys.add(computeMoney);
		
		return returnComputeMoneys;
	}
// 从语音统计数据中取出一个产品大类的记录
	public List<DtVoice> getOneCusTypeDtVoice(List<DtVoice> dtVoices,CusType ct){
		List<DtVoice> shortDtVoice = new ArrayList<DtVoice>();
		for(DtVoice dv : dtVoices){
			if( (dv.getTypeid()!=null) && dv.getTypeid().equals(ct.getTypeid())){
				shortDtVoice.add(dv);
			}
		}
		return shortDtVoice;
	}
//计算一个产品大类语音的费用
	public Double getOneCusTypeDtVoiceMoney(DtVoice dvs,MoneyStandard moneyStandard,Boolean flag){//true 计算呼入 false 计算呼出
		Double total = 0.0;
		if(flag == true){
				if(dvs.getMoney() == null){
					total += dvs.getTotalseconds() * moneyStandard.getValue();
				}else{
					total += dvs.getTotalseconds() * dvs.getMoney() ;
				}
		}else{
			if(dvs.getMoney() == null){
				total += dvs.getTotalseconds() * moneyStandard.getPrice();
			}else{
				total += dvs.getTotalseconds() * dvs.getMoney() ;
			}
		}
			
		return total;
	}
	
//	从多媒体语音统计数据中取出一个产品的记录
	public List<DtMultiVoice> getOneCusTypeDtMultiVoice(List<DtMultiVoice> dtMultiVoices,CusType ct){
		List<DtMultiVoice> shortDtMultiVoice = new ArrayList<DtMultiVoice>();
		for( DtMultiVoice dmv  : dtMultiVoices){
			if( (dmv.getBigtype() != null ) && dmv.getBigtype().equals(ct.getTypetimeing())){
				shortDtMultiVoice.add(dmv);
			}
		}
		return shortDtMultiVoice;
	}
// 	计算一个产品大类多媒体语音的费用	
	public Double getOneCusTypeDtMultiVoiceMoney(DtMultiVoice dmv,MoneyStandard moneyStandard,Boolean flag){//true 计算呼入 false 计算呼出
		Double total  = 0.0;
		if(flag == true){
			if( dmv.getMoney() == null ){
				total += dmv.getTotalseconds() * moneyStandard.getValue() ;
			}else{
				total += dmv.getTotalseconds() * dmv.getMoney() ;
			}
		}else{
			if( dmv.getMoney() == null ){
				total += dmv.getTotalseconds() * moneyStandard.getPrice();
			}else{
				total += dmv.getTotalseconds() * dmv.getMoney();
			}
		}
		return total;
	}
// 从非语音统计数据中取出一个产品的记录
	public List<DtOlService> getOneCusTypeDtOlService(List<DtOlService> dtOlServices , CusType ct ,MoneyStandard ms){
		List<DtOlService> shortdtOlService = new ArrayList<DtOlService>();
		for( DtOlService dos : dtOlServices){
			if( (dos.getTypeid() != null) && dos.getTypeid().equals(ct.getTypeid()) && (dos.getMediatype() !=null) && dos.getMediatype().equals(ms.getClazz()) ){
				shortdtOlService.add(dos);
			}
		}
		return shortdtOlService;
	}

//	计算呼入次数
	public Integer getOneCusTypeDtOlServiceCallCount(List<DtOlService> shortdtOlService){
		Integer callCount = 0 ;
		for( DtOlService dos: shortdtOlService ){
				callCount += dos.getId();
		}
		return callCount;
	}
// 	计算非语音呼入时长
	public Double getOneCusTypeDtOlServiceCallTmlen(List<DtOlService> shortdtOlService ,MoneyStandard ms){
		Double total = 0.0;
		for(DtOlService dos : shortdtOlService){
			if(dos.getMoney() == null && dos.getValue() == null){
				total += dos.getId()  * ms.getValue();
			}
			else{
				total += dos.getId() * (dos.getValue() == null ? ms.getValue() : dos.getValue());
			}
		}		
		return total;
	}
	
	
//	计算非语音呼入费用
	public Double getOneCusTypeDtOlServiceCallTotal(List<DtOlService> shortdtOlService ,MoneyStandard ms){
		Double total = 0.0;
		for(DtOlService dos : shortdtOlService){
			if(dos.getMoney() == null){
				total += dos.getId() * ms.getPrice() * ms.getValue();
			}
			else{
				total += dos.getId() * (dos.getValue() == null ? ms.getPrice() : dos.getMoney()) * (dos.getValue() == null ? ms.getValue() : dos.getValue());
			}
		}
		return total;
	}

//	从改单补单中获取一个获取一个产品大类记录
	public List<DtChgOrder> getOneCusTypeDtChgOrder(List<DtChgOrder> dtChgOrders , CusType ct ,MoneyStandard moneyStandard){
		List<DtChgOrder> shortDtChgOrder = new ArrayList<DtChgOrder>();
		for(DtChgOrder dco : dtChgOrders){
			if( (dco.getTypeid() != null) && dco.getTypeid().equals(ct.getTypeid()) && (dco.getMode()!=null) && dco.getMode().equals(moneyStandard.getClazz())){
				shortDtChgOrder.add(dco);
			}
		}
		return shortDtChgOrder;
	}
	
	
	
	
	//统计所有公司
	public List<ComputeMoney> staAllComputeMoney(Map  map , List<Customer> customers){
		List<ComputeMoney> computeMoneys = new ArrayList<ComputeMoney>();
		//获取所有公司语音数据
		List<DtVoice> dtVoices  = computeMoneyDao.selectAllDtVoice(map);
		//获取所有公司多媒体语音数据
		List<DtMultiVoice> DtMultiVoices = computeMoneyDao.selectAllDtMultiVoice(map);
		//统计所有公司非语音数据
		List<DtOlService> dtOlServices = computeMoneyDao.selectAllDtOlService(map);
		//统计所有公司补单改单书籍
		List<DtChgOrder> dtChgOrders =  computeMoneyDao.selectAllDtChgOrder( map);
		//统计所有公司短信
		List<DtMessage> dtMessages =  computeMoneyDao.selectAllDtMessage(map);
		//获取所有用户的计费标准
		List<MoneyStandard> moneyStandards = computeMoneyDao.selectAllMoneyStandard();
		//语音计算
		ComputeMoney computeMoney = new ComputeMoney();
		MoneyStandard moneyStandard = null;
		computeMoney.setClazz("语音");
		for(DtVoice dt : dtVoices){
			if(!isExistCustomer( dt.getCusid() , customers))continue;//忽略不合作的公司
			moneyStandard = oneDtVoiceMoneyStandard(dt,moneyStandards);
			if(moneyStandard == null) continue;//无计费标准，忽略
			if(dt.getNtype().equals("呼入")){
				computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount() ) +dt.getId());
				computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0: computeMoney.getCallInTmlen()) +dt.getTotalseconds()*1.0);
				computeMoney.setCallInTotal((computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal() )+computeDtVoice(dt,moneyStandard,true));
			}else{//计算呼出
				computeMoney.setCallOutCount((computeMoney.getCallOutCount() == null ? 0 : computeMoney.getCallOutCount() ) +dt.getId());
				computeMoney.setCallOutTmlen((computeMoney.getCallOutTmlen() == null ? 0.0: computeMoney.getCallOutTmlen()) +dt.getTotalseconds()*1.0);
				computeMoney.setCallOutTotal((computeMoney.getCallOutTotal() == null ? 0.0 : computeMoney.getCallOutTotal() )+computeDtVoice(dt,moneyStandard,false));
			}
		}
		//计算多媒体语音
		for(DtMultiVoice dmv : DtMultiVoices){
			if(!isExistCustomer( dmv.getCusid() , customers))continue;//忽略不合作的公司
			moneyStandard = oneDtMultiVoiceMoneyStandard(dmv,moneyStandards);
			if(moneyStandard == null) continue;//无计费标准，忽略
			if(dmv.getNtype().equals("呼入")){
				computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount() ) +dmv.getId());
				computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0: computeMoney.getCallInTmlen()) +dmv.getTotalseconds()*1.0);
				computeMoney.setCallInTotal((computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal() )+computeDtMultiVoice(dmv,moneyStandard,true));
			}else{//计算呼出
				computeMoney.setCallOutCount((computeMoney.getCallOutCount() == null ? 0 : computeMoney.getCallOutCount() ) +dmv.getId());
				computeMoney.setCallOutTmlen((computeMoney.getCallOutTmlen() == null ? 0.0: computeMoney.getCallOutTmlen()) +dmv.getTotalseconds()*1.0);
				computeMoney.setCallOutTotal((computeMoney.getCallOutTotal() == null ? 0.0 : computeMoney.getCallOutTotal() )+computeDtMultiVoice(dmv,moneyStandard,false));
			}
		}		
		computeMoneys.add(computeMoney);
		//计算非语音
		for(DtOlService dos :  dtOlServices){
			if(!isExistCustomer( dos.getCusid() , customers))continue;//忽略不合作的公司
			moneyStandard = oneDtOlServiceMoneyStandard(dos,moneyStandards);
			for(ComputeMoney cm: computeMoneys){//找已经添加到集合中的某类别合计
				if(cm.getClazz().equals(dos.getMediatype())){
					computeMoney = cm;
					break;
				}
			}
			if( !computeMoney.getClazz().equals(dos.getMediatype()) ){
				computeMoney = new ComputeMoney();
				computeMoney.setClazz(dos.getMediatype());
				computeMoneys.add(computeMoney);
			}
			
			computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()) + dos.getId());
			computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen()) + computeDtOlServiceTmlen(dos,moneyStandard));
			computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal()) + computeDtOlServiceTotal(dos,moneyStandard));
		}
		
		//计算补单改单
		for(DtChgOrder dco :   dtChgOrders){
			if(!isExistCustomer( dco.getCusid() , customers))continue;//忽略不合作的公司
			moneyStandard = oneDtChgOrderMoneyStandard(dco,moneyStandards);
			for(ComputeMoney cm: computeMoneys){//找已经添加到集合中的某类别合计
				if(cm.getClazz().equals(dco.getMode())){
					computeMoney = cm;
					break;
				}
			}
			if( !computeMoney.getClazz().equals(dco.getMode()) ){
				computeMoney = new ComputeMoney();
				computeMoney.setClazz(dco.getMode());
				computeMoneys.add(computeMoney);
			}
			computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 :computeMoney.getCallInCount()) + dco.getId());
			if(moneyStandard != null)
				computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0 :computeMoney.getCallInTmlen()) + computeDtChgOrderTmlen(dco,moneyStandard));
			if(moneyStandard != null)
				computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 :computeMoney.getCallInTotal()) + computeDtChgOrderTotal( dco, moneyStandard) );
		}
		
		//减小误差
				for(ComputeMoney cm : computeMoneys){
					if(cm.getCallInTmlen() != null){
						cm.setCallInTmlen(cm.getCallInTmlen() / 60.0);
					}
					if(cm.getCallInTotal() != null){
						cm.setCallInTotal(cm.getCallInTotal() / 60.0);
					}
					if(cm.getCallOutTmlen() != null){
						cm.setCallOutTmlen(cm.getCallOutTmlen() / 60.0);
					}
					if(cm.getCallOutTotal() != null){
						cm.setCallOutTotal(cm.getCallOutTotal() / 60.0);
					}
				}
		
//		计算短信
		computeMoney = new ComputeMoney();
		computeMoney.setClazz("短信");
		computeMoneys.add(computeMoney);
		for(DtMessage dm : dtMessages){
			if(!isExistCustomer( dm.getCusid() , customers))continue;//忽略不合作的公司
			moneyStandard = oneDtMessageMoneyStandard(dm,moneyStandards);
			if(dm.getMoney() == null){
				if(moneyStandard != null){
					computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()) +new Long(dm.getId()).intValue());
					computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal()) + moneyStandard.getValue() * dm.getId());
				}
				
			}else{
				if(moneyStandard != null){
					computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()) +new Long(dm.getId()).intValue());
					computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal()) + dm.getMoney() * dm.getId());
				}
				
			}
		}
		
//		非语音小计
		computeMoney = new ComputeMoney();
		computeMoney.setClazz("非语音小计");
		
		for( ComputeMoney cm : computeMoneys){
			if( !cm.getClazz().equals("短信") && !cm.getClazz().equals("语音") ){
				
				if(cm.getCallInCount() != null){
					computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 :computeMoney.getCallInCount()) + cm.getCallInCount() );
				}
				if(cm.getCallInTmlen() != null){
					computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0 :computeMoney.getCallInTmlen()) + cm.getCallInTmlen() );			
				}
				if(cm.getCallInTotal() != null){
					computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 :computeMoney.getCallInTotal()) + cm.getCallInTotal() );
				}
				if(cm.getCallOutCount() != null){
					computeMoney.setCallOutCount( (computeMoney.getCallOutCount() == null ? 0 :computeMoney.getCallOutCount()) + cm.getCallOutCount() );
				}
				if(cm.getCallOutTmlen() != null){
					computeMoney.setCallOutTmlen( (computeMoney.getCallOutTmlen() == null ? 0.0 :computeMoney.getCallOutTmlen()) + cm.getCallOutTmlen() );
				}
				if(cm.getCallOutTotal() != null){
					computeMoney.setCallOutTotal( (computeMoney.getCallOutTotal() == null ? 0.0 :computeMoney.getCallOutTotal()) + cm.getCallOutTotal() );
				}
			}
		}
		computeMoneys.add(computeMoney);
		
		computeMoney = new ComputeMoney();
		computeMoney.setClazz("总计");
		
		for( ComputeMoney cm : computeMoneys){
			if( ! cm.getClazz().equals("非语音小计")){
				if(cm.getCallInCount() != null){
					computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 :computeMoney.getCallInCount()) + cm.getCallInCount() );
				}
				if(cm.getCallInTmlen() != null){
					computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0 :computeMoney.getCallInTmlen()) + cm.getCallInTmlen() );			
				}
				if(cm.getCallInTotal() != null){
					computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 :computeMoney.getCallInTotal()) + cm.getCallInTotal() );
				}
				if(cm.getCallOutCount() != null){
					computeMoney.setCallOutCount( (computeMoney.getCallOutCount() == null ? 0 :computeMoney.getCallOutCount()) + cm.getCallOutCount() );
				}
				if(cm.getCallOutTmlen() != null){
					computeMoney.setCallOutTmlen( (computeMoney.getCallOutTmlen() == null ? 0.0 :computeMoney.getCallOutTmlen()) + cm.getCallOutTmlen() );
				}
				if(cm.getCallOutTotal() != null){
					computeMoney.setCallOutTotal( (computeMoney.getCallOutTotal() == null ? 0.0 :computeMoney.getCallOutTotal()) + cm.getCallOutTotal() );
				}
			}
		}
		computeMoneys.add(computeMoney);
		return computeMoneys;
	}
//	判断系统中是否还用公司存在，不存在则属于不合作公司
	public Boolean isExistCustomer(String cusid , List<Customer> customers){
		Boolean exist = false;
		for(Customer ct : customers){
			if(ct.getCusid().equals(cusid))
				exist = true;
		}
		
		return exist;
	}
	
	
	//获取公司语音计费标准
	public MoneyStandard oneDtVoiceMoneyStandard(DtVoice dt,List<MoneyStandard> moneyStandards){
		MoneyStandard moneyStandard = null;
		for(MoneyStandard ms : moneyStandards){
			if(ms.getCusId().equals(dt.getCusid()) && ms.getmType() == 0  && ms.getioType().equals("0")){
				moneyStandard = ms;
				break;
			}
		}
		return moneyStandard;
	}
	//计算语音呼入呼出
	public Double computeDtVoice(DtVoice dt,MoneyStandard moneyStandard,Boolean flag){//true 计算呼入 false 计算呼出
		Double total = 0.0;
		if(flag){
			if(dt.getMoney() == null ){
				if(moneyStandard != null)
					total += dt.getTotalseconds() * moneyStandard.getValue();
			}else{
				total += dt.getTotalseconds() * dt.getMoney();
			}
		}else{
			if(dt.getMoney() == null  ){
				if(moneyStandard != null)
					total += dt.getTotalseconds() * moneyStandard.getPrice();
			}else{
				total += dt.getTotalseconds() * dt.getMoney();
			}
		}
		return total;
	}
	
	//获取多媒体语音计费标准
	public MoneyStandard oneDtMultiVoiceMoneyStandard(DtMultiVoice dmv,List<MoneyStandard> moneyStandards){
		MoneyStandard moneyStandard = null;
		for(MoneyStandard ms : moneyStandards){
			if(ms.getCusId().equals(dmv.getCusid()) && ms.getmType() == 0 && ms.getioType().equals("0")){
				moneyStandard = ms;
				break;
			}
		}
		return moneyStandard;
	}
	
	//计算多媒体语音呼入呼出
	public Double computeDtMultiVoice(DtMultiVoice dmv,MoneyStandard moneyStandard,Boolean flag){
		Double total = 0.0;
		if(flag){
			if(dmv.getMoney() == null ){
				if(moneyStandard != null)
					total += dmv.getTotalseconds() * moneyStandard.getValue();
			}else{
				total += dmv.getTotalseconds() * dmv.getMoney();
			}
		}else{
			if(dmv.getMoney() == null ){
				if(moneyStandard != null)
					total += dmv.getTotalseconds() * moneyStandard.getPrice();
			}else{
				total += dmv.getTotalseconds() * dmv.getMoney();
			}
		}
		return total;
	}
	
	//获取非语音计费标准
	public MoneyStandard oneDtOlServiceMoneyStandard(DtOlService dos , List<MoneyStandard> moneyStandards){
		MoneyStandard moneyStandard = null;
		for(MoneyStandard ms : moneyStandards){
			if(ms.getCusId().equals(dos.getCusid()) && ms.getClazz() != null && ms.getClazz().equals(dos.getMediatype()) ){
				moneyStandard = ms;
				break;
			}
		}
		return moneyStandard;
	}
	
	//计算非语音时长
	public Double computeDtOlServiceTmlen( DtOlService dos,MoneyStandard moneyStandard){
		Double tmlen = 0.0;
		if(dos.getMoney()==null && dos.getValue() == null){
			if(moneyStandard != null)
				tmlen += dos.getId() * moneyStandard.getValue();
		}else{
			tmlen += dos.getId() * dos.getValue();
		}
		return tmlen;
	}
	//计算非语音费用
	public Double computeDtOlServiceTotal(DtOlService dos,MoneyStandard moneyStandard){
		Double total = 0.0;
		if(dos.getMoney() == null && dos.getValue() == null){
			if(moneyStandard != null)
				total += dos.getId() * moneyStandard.getValue() * moneyStandard.getPrice();
		}else{
			total += dos.getId() * dos.getValue() * dos.getMoney();
		}
		return total;
	}
	
//	获取补单 改单计费标准
	public MoneyStandard oneDtChgOrderMoneyStandard(DtChgOrder dco,List<MoneyStandard> moneyStandards){
		MoneyStandard moneyStandard = null;
		for(MoneyStandard ms : moneyStandards){
			if(ms.getCusId().equals(dco.getCusid()) && ms.getClazz() != null && ms.getClazz().equals(dco.getMode())){
				moneyStandard = ms;
				break;
			}
		}
		return moneyStandard;
	}
	
//	计算非语音时长
	public Double computeDtChgOrderTmlen(DtChgOrder dco,MoneyStandard moneyStandard){
		Double tmlen = 0.0;
		if(dco.getMoney() == null && dco.getValue() == null){
			if(moneyStandard != null)
				tmlen = dco.getId() * moneyStandard.getValue();
		}else{
			tmlen = dco.getId() * dco.getValue() ;
		}
		return tmlen;
	}
	
//	计算非语音费用
	public Double computeDtChgOrderTotal(DtChgOrder dco,MoneyStandard moneyStandard){
		Double total = 0.0;
		if(dco.getMoney() == null && dco.getValue() == null){
			if(moneyStandard != null)
				total = dco.getId() * moneyStandard.getValue() * moneyStandard.getPrice();
		}else{
			total = dco.getId() * dco.getValue() * dco.getMoney();
		}
		return total;
	}
	
//	获取短信计费标准
	public MoneyStandard oneDtMessageMoneyStandard(DtMessage dm,List<MoneyStandard> moneyStandards){
		MoneyStandard moneyStandard = null;
		for(MoneyStandard ms : moneyStandards){
			if(ms.getCusId().equals(dm.getCusid()) && ms.getmType() == 2){
				moneyStandard = ms;
				break;
			}
		}
		return moneyStandard;
	}

	
	
}
