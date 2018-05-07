package com.zeng.ocs.po;


import com.zeng.ocs.util.ExcelField;
/**
 * 语音明细的实体类，集成DtVoice添加两个显示的字段
 * @author cxs
 *
 */
public class DtVoiceVo extends DtVoice{
	//继承DtVoice然后添加cusname公司名称
	@ExcelField(name="公司名称",column="A")
	private String cusName;
	@ExcelField(name="月份",column="B")
	private String tmonth;
	@ExcelField(name="计时产品大类",column="J")
	private String typetimeing;
	
	private String type;
	
	//由于大数据量使用分页，以下是分页条件
	private int begin;
	private int limit;
	
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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


	public String getTypetimeing() {
		return typetimeing;
	}

	public void setTypetimeing(String typetimeing) {
		this.typetimeing = typetimeing;
	}

	public String getTmonth() {
		return tmonth;
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
