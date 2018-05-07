package com.zeng.ocs.po;

import com.zeng.ocs.util.ExcelField;
/**
 * 短信明细子类添加查询条件和导出显示属性
 * @author cxs
 *
 */
public class DtMessageVo extends DtMessage{
	@ExcelField(name="公司名称",column="A")
	private String cusname;
	@ExcelField(name="月份",column="B")
	private String tmonth;
	
	//添加查询条件
	private String begintime;
	private String endtime;
	
	//由于大数据量使用分页，以下是分页条件
	private int begin;
	private int limit;
	
	
	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	
	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTmonth() {
		return tmonth;
	}

	public void setTmonth(String tmonth) {
		this.tmonth = tmonth;
	}

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}
	
}
