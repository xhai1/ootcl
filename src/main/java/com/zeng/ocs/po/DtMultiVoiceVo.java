package com.zeng.ocs.po;

import com.zeng.ocs.util.ExcelField;
/**
 * 多媒体子类添加显示属性
 * @author cxs
 *
 */
public class DtMultiVoiceVo extends DtMultiVoice{
	@ExcelField(name="公司名称",column="A")
	private String cusName;

	//由于大数据量使用分页，以下是分页条件
	private int begin;
	private int limit;
	
	private String beginTime;
	private String endTime;
	
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

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

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	
}
