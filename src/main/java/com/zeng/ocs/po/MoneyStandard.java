package com.zeng.ocs.po;

import java.io.Serializable;

public class MoneyStandard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6425036619848678309L;
	private Integer id;
	private String cusId;
	private Integer mType;
	private String ioType;
	private Integer times;
	private Double price ;          // #单价（元/小时），54.09
	private Integer  number; //  #数量（单数或条数），默认为1
	private Double value ; // #价值（元/条, 分钟/单） 
	private String clazz; // #类别
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public Integer getmType() {
		return mType;
	}
	public void setmType(Integer mType) {
		this.mType = mType;
	}
	public String getioType() {
		return ioType;
	}
	public void setioType(String ioType) {
		this.ioType = ioType;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	@Override
	public String toString() {
		return "MoneyStandard [id=" + id + ", cusId=" + cusId + ", mType=" + mType + ", ioType=" + ioType + ", times="
				+ times + ", price=" + price + ", number=" + number + ", value=" + value + ", clazz=" + clazz + "]";
	}
	
	
}
