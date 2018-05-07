package com.zeng.ocs.po;

import com.zeng.ocs.util.ExcelField;
/**
 * 多媒体语音
 * @author cxs
 *
 */
public class DtMultiVoice {
	
    public String getBigtype() {
		return bigtype;
	}

	public void setBigtype(String bigtype) {
		this.bigtype = bigtype == null ? null : bigtype.trim();
	}

	private Integer id;
	
    private String cusid;
    
	@ExcelField(name="话务ID",column="B")
    private String traid;
	
	@ExcelField(name="主叫号",column="C")
    private String callingno;
	
	@ExcelField(name="落地号",column="D")
    private String callno;
	
	@ExcelField(name="被叫号",column="E")
    private String calledno;
	
	@ExcelField(name="时长",column="F")
    private Integer totalseconds;
	
	@ExcelField(name="开始时间",column="G")
    private String date;
	
	@ExcelField(name="通话类型",column="H")
    private String ntype;
	
	@ExcelField(name="话务平台",column="I")
    private String systemsrc;

	@ExcelField(name="结束时间",column="J")
    private String tend;

	
	private Double money;
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	private String bigtype;//产品大类
	
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

    public String getTraid() {
        return traid;
    }

    public void setTraid(String traid) {
        this.traid = traid == null ? null : traid.trim();
    }

    public String getCallingno() {
        return callingno;
    }

    public void setCallingno(String callingno) {
        this.callingno = callingno == null ? null : callingno.trim();
    }

    public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno == null ? null : callno.trim();
    }

    public String getCalledno() {
        return calledno;
    }

    public void setCalledno(String calledno) {
        this.calledno = calledno == null ? null : calledno.trim();
    }

    public Integer getTotalseconds() {
        return totalseconds;
    }

    public void setTotalseconds(Integer totalseconds) {
        this.totalseconds = totalseconds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getNtype() {
        return ntype;
    }

    public void setNtype(String ntype) {
        this.ntype = ntype == null ? null : ntype.trim();
    }

    public String getSystemsrc() {
        return systemsrc;
    }

    public void setSystemsrc(String systemsrc) {
        this.systemsrc = systemsrc == null ? null : systemsrc.trim();
    }
    
    public String getTend() {
        return tend;
    }

    public void setTend(String tend) {
        this.tend = tend == null ? null : tend.trim();
    }

    
}