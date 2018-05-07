package com.zeng.ocs.service;

import java.util.List;

import com.zeng.ocs.po.CusReportVo;
import com.zeng.ocs.po.Para;
import com.zeng.ocs.util.PageBean;
/**
 * 月报表的service层接口
 * @author cxs
 *
 */
public interface MonthReportService {

	public PageBean<CusReportVo> showMonthReportByLimit(int pageNo, int pageSize, CusReportVo cusReportVo);

	public List<Para> getParaList();

	public PageBean<CusReportVo> showMonthReport(int pageNo, int pageSize, CusReportVo cusReportVo);

	public List<CusReportVo> createMonthReportByIds(List<Integer> ids);

	public PageBean<CusReportVo> showAllMonthReport(int pageNo, int pageSize, CusReportVo cusReportVo);

	public List<CusReportVo> accountMonthReportByIds(List<Integer> ids);

	public CusReportVo accountMonthReport(Integer id);

	public void updateCusReport(CusReportVo cusReportVo);


}
