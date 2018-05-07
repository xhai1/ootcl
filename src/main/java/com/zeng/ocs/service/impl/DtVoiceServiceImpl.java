package com.zeng.ocs.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zeng.ocs.dao.DtVoiceDao;
import com.zeng.ocs.po.CusType;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.DtVoiceVo;
import com.zeng.ocs.po.User;
import com.zeng.ocs.service.ComputeMoneyService;
import com.zeng.ocs.service.DtVoiceService;
import com.zeng.ocs.util.PageBean;
/**
 * 语音明细的service实现类
 * @author cxs
 *
 */
@Service
public class DtVoiceServiceImpl implements DtVoiceService{
	
	@Autowired
	private DtVoiceDao dtVoiceDao;
	
	

	/*
	@Override
	public PageBean<DtVoiceVo> showDtVoiceByLimit(int page) {
		PageBean<DtVoiceVo> pageBean = new PageBean<DtVoiceVo>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 2;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = dtVoiceDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		//int begin = (page-1)*limit;
		List<DtVoiceVo> list = dtVoiceDao.findDtVoiceList(pageBean);
		pageBean.setList(list);
		return pageBean;
	}
	*/
	@Override
	public PageBean<DtVoiceVo> showDtVoiceByLimit(int page,int pageSize,DtVoiceVo dtVoiceVo) {
		PageBean<DtVoiceVo> pageBean = new PageBean<DtVoiceVo>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		pageBean.setLimit(pageSize);
		// 设置总记录数:
		int totalCount = 0;
		
		int begin = (page-1)*pageSize;
		dtVoiceVo.setBegin(begin);
		dtVoiceVo.setLimit(pageSize);
		
		totalCount = dtVoiceDao.findCount(dtVoiceVo);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		pageBean.setTotalPage(totalPage);
		
//		PageHelper.startPage(page, pageSize);
		List<DtVoiceVo> list=dtVoiceDao.findAllDtVoice(dtVoiceVo);
		
		pageBean.setSubtotal(0.0);
		for(DtVoiceVo dt : list ){
			pageBean.setSubtotal(pageBean.getSubtotal() + ( dt.getTotalseconds() == null ? 0.0 : dt.getTotalseconds() ));
		}
		//保留4位小数
		double   sub   =   pageBean.getSubtotal()/60.0;    
		BigDecimal   b   =   new   BigDecimal(sub);    
		double   f1   =   b.setScale(4,   BigDecimal.ROUND_HALF_UP).doubleValue();   
		pageBean.setSubtotal(f1);
		
		
		BigDecimal div = new BigDecimal("60.0000");
		pageBean.setTotal(dtVoiceDao.selectTotalSecond(dtVoiceVo).subtract( div ));		 
				
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public List<DtVoiceVo> findDtVoice(HttpSession session,DtVoiceVo dtVoiceVo,String page) {
		int pageNo;
		User user=(User) session.getAttribute("user");
		if(user.getIsroot()==0){
			pageNo=Integer.parseInt(page);
			int begin = (pageNo-1)*50;
			dtVoiceVo.setBegin(begin);
			dtVoiceVo.setLimit(50);
//			PageHelper.startPage(pageNo, 20);
		}else{
			pageNo=Integer.parseInt(page);
			int begin = (pageNo-1)*50;
			dtVoiceVo.setBegin(begin);
			dtVoiceVo.setLimit(30000);
		}
		return dtVoiceDao.findAllDtVoice(dtVoiceVo);
	}
}
