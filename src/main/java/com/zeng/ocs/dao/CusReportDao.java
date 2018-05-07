package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.CusReportVo;
/**
 * 月账单的dao层接口
 * @author cxs
 *
 */
public interface CusReportDao {

	public int findCount(CusReportVo cusReportVo);

	public List<CusReportVo> findAllMonthReport(CusReportVo cusReportVo);

	public double findMsgPrice(String cusid);

	public List<CusReportVo> findMonthReportByIds(List<Integer> ids);

	public void updateCusReport(CusReportVo cusReportVo);

	public CusReportVo accountMonthReport(Integer id);
}