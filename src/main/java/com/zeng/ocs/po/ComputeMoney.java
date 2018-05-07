package com.zeng.ocs.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ComputeMoney implements Serializable {

	private String cusName;
	private String type;//产品类别
	private String clazz;//业务类别
	private Integer callInCount;//呼入次数
	private Double callInTmlen;//呼入时长
	private Double callInTotal;//呼入费用
	
	private Integer callOutCount;//呼出次数
	private Double callOutTmlen; //呼出时长
	private Double callOutTotal;//呼入费用
	
	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public Integer getCallInCount() {
		return callInCount;
	}

	public void setCallInCount(Integer callInCount) {
		this.callInCount = callInCount;
	}

	public Double getCallInTmlen() {
		return callInTmlen;
	}

	public void setCallInTmlen(Double callInTmlen) {
		this.callInTmlen = callInTmlen;
	}

	public Double getCallInTotal() {
		return callInTotal;
	}

	public void setCallInTotal(Double callInTotal) {
		this.callInTotal = callInTotal;
	}

	public Integer getCallOutCount() {
		return callOutCount;
	}

	public void setCallOutCount(Integer callOutCount) {
		this.callOutCount = callOutCount;
	}

	public Double getCallOutTmlen() {
		return callOutTmlen;
	}

	public void setCallOutTmlen(Double callOutTmlen) {
		this.callOutTmlen = callOutTmlen;
	}

	public Double getCallOutTotal() {
		return callOutTotal;
	}

	public void setCallOutTotal(Double callOutTotal) {
		this.callOutTotal = callOutTotal;
	}

}
