package com.zeng.ocs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zeng.ocs.dao.CusReportDao;
import com.zeng.ocs.dao.ParaDao;
import com.zeng.ocs.po.CusReportVo;
import com.zeng.ocs.po.Para;
import com.zeng.ocs.service.MonthReportService;
import com.zeng.ocs.util.NumberUtil;
import com.zeng.ocs.util.PageBean;
/**
 * 月账单service实现
 * @author cxs
 *
 */
@Service
public class MonthReportServiceImpl implements MonthReportService{

	@Autowired
	private CusReportDao cusReportDao;
	@Autowired
	private ParaDao paraDao;
	@Override
	public PageBean<CusReportVo> showMonthReportByLimit(int pageNo, int pageSize,CusReportVo cusReportVo) {
		PageBean<CusReportVo> pageBean = new PageBean<CusReportVo>();
		// 设置当前页数:
		pageBean.setPage(pageNo);
		// 设置每页显示记录数:
		pageBean.setLimit(pageSize);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = cusReportDao.findCount(cusReportVo);
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
		
		List<CusReportVo> list=cusReportDao.findAllMonthReport(cusReportVo);
		if(list.size()>0){
			for(CusReportVo cusreport:list){
				cusreport.setTotalmoney(NumberUtil.Test2(cusreport.getTotalin()+cusreport.getTotalout()+cusreport.getMsgcount()*cusreport.getMsgvalue()));
			}
		}
		
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public List<Para> getParaList() {
		return paraDao.selectParaAll();
	}
	@Override
	public PageBean<CusReportVo> showMonthReport(int pageNo, int pageSize, CusReportVo cusReportVo) {
		PageBean<CusReportVo> pageBean = new PageBean<CusReportVo>();
		// 设置当前页数:
		pageBean.setPage(pageNo);
		// 设置每页显示记录数:
		pageBean.setLimit(pageSize);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = cusReportDao.findCount(cusReportVo);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		List<CusReportVo> list=cusReportDao.findAllMonthReport(cusReportVo);
		if(list.size()>0){
			for(CusReportVo cusreport:list){
				cusreport.setTotalmoney(NumberUtil.Test2(cusreport.getTotalin()+cusreport.getTotalout()+cusreport.getMsgcount()*cusreport.getMsgvalue()));
			}
		}
		
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public List<CusReportVo> createMonthReportByIds(List<Integer> ids) {
		List<CusReportVo> list=cusReportDao.findMonthReportByIds(ids);
		if(list.size()>0){
			for(CusReportVo cusreport:list){
				cusreport.setTotalmoney(NumberUtil.Test2(cusreport.getTotalin()+cusreport.getTotalout()+cusreport.getMsgcount()*cusreport.getMsgvalue()));
			}
		}
		return list;
	}
	@Override
	public PageBean<CusReportVo> showAllMonthReport(int pageNo, int pageSize, CusReportVo cusReportVo) {
		PageBean<CusReportVo> pageBean = new PageBean<CusReportVo>();
		// 设置当前页数:
		pageBean.setPage(pageNo);
		// 设置每页显示记录数:
		pageBean.setLimit(pageSize);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = cusReportDao.findCount(cusReportVo);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		List<CusReportVo> list=cusReportDao.findAllMonthReport(cusReportVo);
		if(list.size()>0){
			for(CusReportVo cusreport:list){
				cusreport.setTotalmoney(NumberUtil.Test2(cusreport.getTotalin()+cusreport.getTotalout()+cusreport.getMsgcount()*cusreport.getMsgvalue()));
			}
		}
		
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public void updateCusReport(CusReportVo cusReportVo) {
		cusReportDao.updateCusReport(cusReportVo);
	}
	@Override
	public List<CusReportVo> accountMonthReportByIds(List<Integer> ids) {
		return cusReportDao.findMonthReportByIds(ids);
	}
	@Override
	public CusReportVo accountMonthReport(Integer id) {
		return cusReportDao.accountMonthReport(id);
	}

}
