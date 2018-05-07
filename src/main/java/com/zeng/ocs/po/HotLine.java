package com.zeng.ocs.po;

import java.io.Serializable;

import com.zeng.ocs.util.ExcelField;

/**
 * @date:2017年12月7日 下午1:46:33
 * @author Jianghai Yang
 * @version :
 */
public class HotLine implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2991435973305083713L;
	private Integer id;//主键
	private String cusId;// 客户ID
	private String business;//业务
	private String cusTelephone;//热线号
	private String ivr;//IVR 如：按1、按2
	private String telephone;//落地号
	private String itSystem; //所属系统
	private String itRemark; //备注
	private String bigType; //计时产品大类
	private String cusName;  //计时所属公司
	private String chaSystem; //话务系统	
	private Integer orderId; //顺序号
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getCusId()
	{
		return cusId;
	}
	public void setCusId(String cusId)
	{
		this.cusId = cusId == null ? null : cusId.trim();
	}
	public String getBusiness()
	{
		return business;
	}
	public void setBusiness(String business)
	{
		this.business = business == null ? null : business.trim();
	}
	public String getCusTelephone()
	{
		return cusTelephone;
	}
	public void setCusTelephone(String cusTelephone)
	{
		this.cusTelephone = cusTelephone == null ? null : cusTelephone.trim();
	}
	public String getIvr()
	{
		return ivr;
	}
	public void setIvr(String ivr)
	{
		this.ivr = ivr == null ? null : ivr.trim();
	}
	public String getTelephone()
	{
		return telephone;
	}
	public void setTelephone(String telephone)
	{
		this.telephone = telephone == null ? null : telephone.trim();
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
	public String getItSystem()
	{
		return itSystem;
	}
	public void setItSystem(String itSystem)
	{
		this.itSystem = itSystem == null ? null : itSystem.trim();
	}
	public String getItRemark()
	{
		return itRemark;
	}
	public void setItRemark(String itRemark)
	{
		this.itRemark = itRemark == null ? null : itRemark.trim();
	}
	public String getChaSystem()
	{
		return chaSystem;
	}
	public void setChaSystem(String chaSystem)
	{
		this.chaSystem = chaSystem == null ? null : chaSystem.trim();
	}
	public Integer getOrderId()
	{
		return orderId;
	}
	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "HotLine [id=" + id + ", cusId=" + cusId + ", business=" + business + ", cusTelephone=" + cusTelephone
				+ ", ivr=" + ivr + ", telephone=" + telephone + ", itSystem=" + itSystem + ", itRemark=" + itRemark
				+ ", bigType=" + bigType + ", cusName=" + cusName + ", chaSystem=" + chaSystem + ", orderId=" + orderId
				+ "]";
	}
	
	
}
