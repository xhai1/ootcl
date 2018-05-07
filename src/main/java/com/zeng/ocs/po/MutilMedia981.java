package com.zeng.ocs.po;

import java.io.Serializable;
import java.util.Date;

import com.zeng.ocs.util.ExcelField;

/**
 * @date:2017年12月17日 下午8:06:16
 * @author Jianghai Yang
 * @version :
 */
public class MutilMedia981 implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2294499773318324370L;
	private Integer id;
	private String cusId;//客户ID
	private String itenantId;//租户ID
	private String extNumber;//分机号
	private String telephone;//落地号
	private String bigType;//产品大类
	private String cusName;//所属公司
	private Date updateTime;//修改日期
	
	public String getCusId()
	{
		return cusId;
	}
	public void setCusId(String cusId)
	{
		this.cusId = cusId == null ? null : cusId.trim();
	}
	public String getBigType()
	{
		return bigType;
	}
	public void setBigType(String bigType)
	{
		this.bigType = bigType == null ? null : bigType.trim();
	}
	public String getCusName()
	{
		return cusName;
	}
	public void setCusName(String cusName)
	{
		this.cusName = cusName == null ? null : cusName.trim();
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getItenantId()
	{
		return itenantId;
	}
	public void setItenantId(String itenantId)
	{
		this.itenantId = itenantId == null ? null : itenantId.trim();
	}
	public String getExtNumber()
	{
		return extNumber;
	}
	public void setExtNumber(String extNumber)
	{
		this.extNumber = extNumber == null ? null : extNumber.trim();
	}
	public String getTelephone()
	{
		return telephone;
	}
	public void setTelephone(String telephone)
	{
		this.telephone = telephone == null ? null : telephone.trim();
	}
	public Date getUpdateTime()
	{
		return updateTime;
	}
	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}
	@Override
	public String toString()
	{
		return "MutilMedia981 [id=" + id + ", cusId=" + cusId + ", itenantId=" + itenantId + ", extNumber=" + extNumber
				+ ", telephone=" + telephone + ", bigType=" + bigType + ", cusName=" + cusName + ", updateTime="
				+ updateTime + "]";
	}
	
	
}
