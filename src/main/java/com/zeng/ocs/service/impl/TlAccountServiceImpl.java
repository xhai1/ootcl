package com.zeng.ocs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeng.ocs.dao.ClientDao;
import com.zeng.ocs.dao.CusTypeDao;
import com.zeng.ocs.dao.MoneyStandardDao;
import com.zeng.ocs.dao.TlAccountDao;
import com.zeng.ocs.po.ComputeMoney;
import com.zeng.ocs.po.CustomerVo;
import com.zeng.ocs.po.DtChgOrderVo;
import com.zeng.ocs.po.DtMessageVo;
import com.zeng.ocs.po.DtMultiVoiceVo;
import com.zeng.ocs.po.DtOlServiceVo;
import com.zeng.ocs.po.DtVoiceVo;
import com.zeng.ocs.po.MoneyStandard;
import com.zeng.ocs.service.TlAccountService;
import com.zeng.ocs.util.PaginationUtil;

@Service("tlAccountService")
public class TlAccountServiceImpl implements TlAccountService {

	@Autowired
	TlAccountDao tlAccountDao;
	
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private MoneyStandardDao moneyStandardDao;
	
	@Override
	public List<ComputeMoney> getMonthTotal(PaginationUtil paginationUtil) {
		 //获取所有公司信息
		List<CustomerVo> customerVos = clientDao.findAllCustomer();
		//获取所有计费标准
		List<MoneyStandard> moneyStandards = moneyStandardDao.selectAllMoneyStandard();
		
		//获取语音月份统计
		List<DtVoiceVo> dtVoiceVos = tlAccountDao.selectTlAccountDtVoice(paginationUtil);
		//获取飞鱼营月份统计 
		List<DtMultiVoiceVo> dtMultiVoiceVos = tlAccountDao.selectTlAccountDtMultiVoice(paginationUtil);
		
		List<DtOlServiceVo>  dtOlServiceVos = tlAccountDao.selectTlAccountDtOlservice(paginationUtil);
		
		List<DtChgOrderVo> dtChgOrderVos = tlAccountDao.selectTlAccountDtchgorder(paginationUtil);
		
		List<DtMessageVo> dtMessageVos = tlAccountDao.selectTlAccountDtmessage(paginationUtil);
		 
		List<ComputeMoney> computeMoneys = new ArrayList<ComputeMoney>();
		 
		for(DtVoiceVo dv : dtVoiceVos){
			MoneyStandard ms =  getMoneyStand(moneyStandards ,  dv);
			if(ms == null)continue;
			
			//检查是否有ComputeMoney存在于computeMoneys中
			ComputeMoney computeMoney = isExist(computeMoneys, dv);
			if (computeMoney == null) {
				computeMoney = new ComputeMoney();
				computeMoney.setClazz(dv.getCusid());
				computeMoney.setType(dv.getDate());
				computeMoneys.add(computeMoney);
			}
					
			if (dv.getNtype().equals("呼入")) {
				computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()  ) + dv.getId());
				computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen()  ) + dv.getTotalseconds());
				computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal()  ) +  + (dv.getMoney() == null ? dv.getTotalseconds() * ms.getPrice() : dv.getTotalseconds() * dv.getMoney() ) );
			} else if (dv.getNtype().equals("呼出")) {
				computeMoney.setCallOutCount( (computeMoney.getCallOutCount() == null ? 0 : computeMoney.getCallOutCount()  ) + dv.getId());
				computeMoney.setCallOutTmlen( (computeMoney.getCallOutTmlen() == null ? 0.0 : computeMoney.getCallOutTmlen()  ) + dv.getTotalseconds());
				computeMoney.setCallOutTotal( (computeMoney.getCallOutTotal() == null ? 0.0 : computeMoney.getCallOutTotal()  ) +  (dv.getMoney() == null ? dv.getTotalseconds() * ms.getValue() : dv.getTotalseconds() * dv.getMoney() ) );
			}

		}
		
		for(DtMultiVoiceVo dmv : dtMultiVoiceVos){
			
			MoneyStandard ms =  getMoneyStand(moneyStandards ,  dmv);
			if(ms == null)continue;
			//检查是否有ComputeMoney存在于computeMoneys中
			ComputeMoney computeMoney = isExist(computeMoneys, dmv);
			if (computeMoney == null) {
				computeMoney = new ComputeMoney();
				computeMoney.setClazz(dmv.getCusid());
				computeMoney.setType(dmv.getDate());
				computeMoneys.add(computeMoney);
			}
			
			if (dmv.getNtype().equals("呼入")) {
				computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()  ) + dmv.getId());
				computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen()  ) + dmv.getTotalseconds());
				computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal()  ) +  + (dmv.getMoney() == null ? dmv.getTotalseconds() * ms.getPrice() : dmv.getTotalseconds() * dmv.getMoney() ) );
			} else if (dmv.getNtype().equals("呼出")) {
				computeMoney.setCallOutCount( (computeMoney.getCallOutCount() == null ? 0 : computeMoney.getCallOutCount()  ) + dmv.getId());
				computeMoney.setCallOutTmlen( (computeMoney.getCallOutTmlen() == null ? 0.0 : computeMoney.getCallOutTmlen()  ) + dmv.getTotalseconds());
				computeMoney.setCallOutTotal( (computeMoney.getCallOutTotal() == null ? 0.0 : computeMoney.getCallOutTotal()  ) +  (dmv.getMoney() == null ? dmv.getTotalseconds() * ms.getValue() : dmv.getTotalseconds() * dmv.getMoney() ) );
			}
			
		}
		
		for(DtOlServiceVo dos : dtOlServiceVos){
			MoneyStandard ms =  getMoneyStand(moneyStandards ,  dos);
			if(ms == null)continue;
			//检查是否有ComputeMoney存在于computeMoneys中
			ComputeMoney computeMoney = isExist(computeMoneys, dos);
			if (computeMoney == null) {
				computeMoney = new ComputeMoney();
				computeMoney.setClazz(dos.getCusid());
				computeMoney.setType(dos.getDate());
				computeMoneys.add(computeMoney);
			}
			
			computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()  ) + dos.getId());
			computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen()  ) + ( (dos.getValue() == null || dos.getMoney() == null) ? dos.getId() * ms.getValue() : dos.getId() * dos.getValue() )  );
			computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal()  ) +  + ( (dos.getValue() == null || dos.getMoney() == null) ? dos.getId() * ms.getPrice() * ms.getValue() : dos.getId() * dos.getMoney() * dos.getValue() ) );
			
		}
		
		for(DtChgOrderVo dc : dtChgOrderVos){
			MoneyStandard ms =  getMoneyStand(moneyStandards ,  dc);
			if(ms == null)continue;
			//检查是否有ComputeMoney存在于computeMoneys中
			ComputeMoney computeMoney = isExist(computeMoneys, dc);
			if (computeMoney == null) {
				computeMoney = new ComputeMoney();
				computeMoney.setClazz(dc.getCusid());
				computeMoney.setType(dc.getAccdate());
				computeMoneys.add(computeMoney);
			}
			
			computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()  ) + dc.getId());
			computeMoney.setCallInTmlen( (computeMoney.getCallInTmlen() == null ? 0.0 : computeMoney.getCallInTmlen()  ) + ( (dc.getValue() == null || dc.getMoney() == null) ? dc.getId() * ms.getValue() : dc.getId() * dc.getValue() )  );
			computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal()  ) +  + ( (dc.getValue() == null || dc.getMoney() == null) ? dc.getId() * ms.getPrice() * ms.getValue() : dc.getId() * dc.getMoney() * dc.getValue() ) );
		}
//		计算费用
			compute(computeMoneys);
		
		for(DtMessageVo dm : dtMessageVos){
			MoneyStandard ms =  getMoneyStand(moneyStandards ,  dm);
			if(ms == null)continue;
			//检查是否有ComputeMoney存在于computeMoneys中
			ComputeMoney computeMoney = isExist(computeMoneys, dm);
			if (computeMoney == null) {
				computeMoney = new ComputeMoney();
				computeMoney.setClazz(dm.getCusid());
				computeMoney.setType(dm.getUpdatetime());
				computeMoneys.add(computeMoney);
			}
			
			computeMoney.setCallInCount( (computeMoney.getCallInCount() == null ? 0 : computeMoney.getCallInCount()  ) + new Long(dm.getId()).intValue() );
			computeMoney.setCallInTotal( (computeMoney.getCallInTotal() == null ? 0.0 : computeMoney.getCallInTotal()  ) +  + ( dm.getMoney() == null  ? dm.getId() * ms.getValue() : dm.getId()  * dm.getMoney() ) );
		}

//		设置公司名称
		
		for(ComputeMoney cm  :computeMoneys){
			setCustomer(customerVos,cm);
		}
		
		return computeMoneys;
	}
	//clazz保存cusid type保存年月
	public ComputeMoney isExist(List<ComputeMoney> computeMoneys , Object dt) {
			ComputeMoney  cm = null;				
			if(dt instanceof DtVoiceVo){				
				for(ComputeMoney computeMoney :computeMoneys ){
					if(computeMoney.getClazz().equals(((DtVoiceVo) dt).getCusid()) && computeMoney.getType().equals(((DtVoiceVo) dt).getDate())){
						cm = computeMoney;
						break;
					}
				}
				return cm;
			}else if(dt instanceof DtMultiVoiceVo){
				for(ComputeMoney computeMoney :computeMoneys ){
					if(computeMoney.getClazz().equals(((DtMultiVoiceVo) dt).getCusid()) && computeMoney.getType().equals(((DtMultiVoiceVo) dt).getDate())){
						cm = computeMoney;
						break;
					}
				}
				return cm;
			}
			else if(dt instanceof DtOlServiceVo){
				for(ComputeMoney computeMoney :computeMoneys ){
					if(computeMoney.getClazz().equals(((DtOlServiceVo) dt).getCusid()) && computeMoney.getType().equals(((DtOlServiceVo) dt).getDate()) ){
						cm = computeMoney;
						break;
					}
				}
				return cm;
			}
			else if(dt instanceof DtChgOrderVo){
				for(ComputeMoney computeMoney :computeMoneys ){
					if(computeMoney.getClazz().equals(((DtChgOrderVo) dt).getCusid()) && computeMoney.getType().equals(((DtChgOrderVo) dt).getAccdate())){
						cm = computeMoney;
						break;
					}
				}
				return cm;
			}
			else if(dt instanceof DtMessageVo){
				for(ComputeMoney computeMoney :computeMoneys ){
					if(computeMoney.getClazz().equals(((DtMessageVo) dt).getCusid()) && computeMoney.getType().equals(((DtMessageVo) dt).getUpdatetime())){
						cm = computeMoney;
						break;
					}
				}
				return cm;
			}				
		return cm;		
	}
	
	public MoneyStandard getMoneyStand(List<MoneyStandard> moneyStandards , Object dt){
		MoneyStandard ms = null;
		if(dt instanceof DtVoiceVo){
			for(MoneyStandard moneyStandard : moneyStandards){
				if(  (moneyStandard.getmType() == 0) &&  ((DtVoiceVo) dt).getCusid().equals(moneyStandard.getCusId()) && moneyStandard.getioType().equals("0")  ){
					ms = moneyStandard;
					break;
				}
			}
			return ms;
		}else if(dt instanceof DtMultiVoiceVo){
			for(MoneyStandard moneyStandard : moneyStandards){
				if( (moneyStandard.getmType() == 0) &&  ((DtMultiVoiceVo) dt).getCusid().equals(moneyStandard.getCusId()) && moneyStandard.getioType().equals("0")  ){
					ms = moneyStandard;
					break;
				}
			}
			return ms;
			
		}else if(dt instanceof DtOlServiceVo){
			for(MoneyStandard moneyStandard : moneyStandards){
				if( (moneyStandard.getmType() == 1) &&  ((DtOlServiceVo) dt).getCusid().equals(moneyStandard.getCusId()) && moneyStandard.getioType().equals("1")  && moneyStandard.getClazz().equals(((DtOlServiceVo) dt).getMediatype())){
					ms = moneyStandard;
					break;
				}
			}
			return ms;
		}else if(dt instanceof DtChgOrderVo){
			for(MoneyStandard moneyStandard : moneyStandards){
				if( (moneyStandard.getmType() == 1) && ((DtChgOrderVo) dt).getCusid().equals(moneyStandard.getCusId()) && moneyStandard.getioType().equals("1") &&  moneyStandard.getClazz().equals(((DtChgOrderVo) dt).getMode())){
					ms = moneyStandard;
					break;
				}
			}
			return ms;
		}else if(dt instanceof DtMessageVo){
			for(MoneyStandard moneyStandard : moneyStandards){
				if( ((DtMessageVo) dt).getCusid().equals(moneyStandard.getCusId()) &&  (moneyStandard.getmType() == 2) ){
					ms = moneyStandard;
					break;
				}
			}
			return ms;
		}
		
		return ms;
		
	}
	
	public void setCustomer(List<CustomerVo> customerVos ,ComputeMoney  cpm){
		for(CustomerVo ct : customerVos){
			if(cpm.getClazz().equals(ct.getCusid())){
				cpm.setCusName(ct.getCusname());
			}
		}
	}

	public void compute(List<ComputeMoney> computeMoneys){
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
	}
}
