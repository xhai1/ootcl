package com.zeng.ocs.po;

import com.zeng.ocs.util.ExcelField;
/**
 * 产品大类子类
 * @author cxs
 *
 */
public class CusTypeVo extends CusType{
	@ExcelField(name="计时所属公司",column="F")
	private String cusname;

	//数量限制
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

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}
	
}
