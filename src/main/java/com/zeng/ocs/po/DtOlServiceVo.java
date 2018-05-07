package com.zeng.ocs.po;

import com.zeng.ocs.util.ExcelField;
/**
 * 在线服务的子类添加查询条件和导出属性
 * @author cxs
 *
 */
public class DtOlServiceVo extends DtOlService{
	@ExcelField(name="公司名称",column="A")
	private String cusName;
	@ExcelField(name="月份",column="B")
	private String tmonth;
	
	public String getTmonth() {
		return tmonth;
	}

	//由于大数据量不使用mybatis的分页插件，以下是分页条件
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

	public void setTmonth(String tmonth) {
		this.tmonth = tmonth;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	
}
