package com.zeng.ocs.po;

import com.zeng.ocs.util.ExcelField;
/**
 * 改单实体
 * @author cxs
 *
 */
public class DtChgOrder {
    private Integer id;
	
    private String cusid;
    @ExcelField(name="受理日期",column="C")
    private String accdate;
    
    @ExcelField(name="来电备注",column="E")
    private String mode;
    
    @ExcelField(name="业务技能",column="F")
    private String businesstype;
    
    @ExcelField(name="受理编号",column="G")
    private String acceptid;
    
    @ExcelField(name="CASE单号",column="H")
    private String caseorder;
    
    @ExcelField(name="产品大类ID",column="J")
    private String typeid;
    
    @ExcelField(name="产品小类",column="K")
    private String dttype;
    
    @ExcelField(name="需求大类",column="L")
    private String typedemand;
    
    @ExcelField(name="需求小类",column="M")
    private String dtdemand;

    private Double money;
    
    private Double value;
    
    public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid == null ? null : cusid.trim();
    }

    public String getAccdate() {
        return accdate;
    }

    public void setAccdate(String accdate) {
        this.accdate = accdate == null ? null : accdate.trim();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype == null ? null : businesstype.trim();
    }

    public String getAcceptid() {
        return acceptid;
    }

    public void setAcceptid(String acceptid) {
        this.acceptid = acceptid == null ? null : acceptid.trim();
    }

    public String getCaseorder() {
        return caseorder;
    }

    public void setCaseorder(String caseorder) {
        this.caseorder = caseorder == null ? null : caseorder.trim();
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getDttype() {
        return dttype;
    }

    public void setDttype(String dttype) {
        this.dttype = dttype == null ? null : dttype.trim();
    }

    public String getTypedemand() {
        return typedemand;
    }

    public void setTypedemand(String typedemand) {
        this.typedemand = typedemand == null ? null : typedemand.trim();
    }

    public String getDtdemand() {
        return dtdemand;
    }

    public void setDtdemand(String dtdemand) {
        this.dtdemand = dtdemand == null ? null : dtdemand.trim();
    }
}