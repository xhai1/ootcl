package com.zeng.ocs.po;

import java.io.Serializable;
import java.util.Map;

/**
 * @date:2017年12月16日 下午3:03:06
 * @author Jianghai Yang
 * @version :
 */
public class BillStandard implements Serializable
{
	private Integer cusId;//客户Id
	private String cusName;
	private Double callIn;
	private Double callOut;
	private Map<String, Double> clazz;
	private Double Message;
	public Integer getCusId()
	{
		return cusId;
	}
	public void setCusId(Integer cusId)
	{
		this.cusId = cusId;
	}
	public String getCusName()
	{
		return cusName;
	}
	public void setCusName(String cusName)
	{
		this.cusName = cusName;
	}
	public Double getCallIn()
	{
		return callIn;
	}
	public void setCallIn(Double callIn)
	{
		this.callIn = callIn;
	}
	public Double getCallOut()
	{
		return callOut;
	}
	public void setCallOut(Double callOut)
	{
		this.callOut = callOut;
	}
	public Map<String, Double> getClazz()
	{
		return clazz;
	}
	public void setClazz(Map<String,Double> clazz)
	{
		this.clazz = clazz;
	}
	public Double getMessage()
	{
		return Message;
	}
	public void setMessage(Double message)
	{
		Message = message;
	}
	@Override
	public String toString()
	{
		return "BillStandard [cusId=" + cusId + ", cusName=" + cusName + ", callIn=" + callIn + ", callOut=" + callOut
				+ ", clazz=" + clazz + ", Message=" + Message + "]";
	}
	
}
