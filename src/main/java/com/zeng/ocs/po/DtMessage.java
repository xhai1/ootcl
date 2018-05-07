package com.zeng.ocs.po;

import com.zeng.ocs.util.ExcelField;
/**
 * 短信明细
 * @author cxs
 *
 */
public class DtMessage {
	
    private Long id;

    private String cusid;
    @ExcelField(name="短信端口",column="F")
    private String port;
    @ExcelField(name="短信用途",column="G")
    private String purpose;
    @ExcelField(name="日期",column="C")
    private String updatetime;
    @ExcelField(name="短信ID",column="D")
    private String messageid;
    @ExcelField(name="手机号码",column="E")
    private String phoneno;
    @ExcelField(name="运营商",column="H")
    private String operator;
    @ExcelField(name="短信内容",column="I")
    private String message;

    private Double money;
    
    public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid == null ? null : cusid.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid == null ? null : messageid.trim();
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
    
}