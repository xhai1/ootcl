package com.zeng.ocs.po;

import java.util.Date;

import com.zeng.ocs.util.ExcelField;

public class SmsPort {


	private Long id;

	private String cusid;
	@ExcelField(name="计时所属公司",column="C")
    private String cusname;
	
	@ExcelField(name="短信端口",column="A")
    private String port;
	
	@ExcelField(name="短信用途",column="B")
    private String purpose;
    private Date updatetime;
	@ExcelField(name="运营商",column="D")
    private String caroprator;
	
	
	public String getCaroprator() {
		return caroprator;
	}

	public void setCaroprator(String caroprator) {
		this.caroprator = caroprator;
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

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname == null ? null : cusname.trim();
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

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
    
    
}