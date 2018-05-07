package com.zeng.ocs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeng.ocs.dao.ParaDao;
import com.zeng.ocs.po.Para;
import com.zeng.ocs.service.SysParaService;
@Service("SysParaService")
public class SysParaServiceImpl implements SysParaService {

	@Autowired
	ParaDao paraDao;
	
	@Override
	public void updateSystemParam(List<Para> paras) {
		Para parat = null;
		for(Para para : paras){
			parat = paraDao.selectByPkey(para.getPkey().trim());
			if(parat == null){
				paraDao.insertSelective(para);
			}else
			{
				para.setId(parat.getId());
				paraDao.updateByPrimaryKey(para);
				
			}
		}
		
	}

	@Override
	public List<Para> showSystemParam() {
		return paraDao.selectParaAll();
	}

}
