package com.zeng.ocs.po;


import com.zeng.ocs.util.ExcelField;
/**
 * 语音明细的实体类
 * 使用注解导出
 * @author cxs
 *
 */
public class DtVoice {
    private Integer id;
    private String cusid;
    
   
    
	@ExcelField(name="日期",column="C")
    protected String date;
	
	@ExcelField(name="话务ID",column="D")
    private String traid;
	
	@ExcelField(name="主叫号",column="E")
    private String callingno;
	
	@ExcelField(name="被叫号",column="F")
    private String calledno;
	
	@ExcelField(name="通话开始时间",column="G")
    private String begintime;
	
	@ExcelField(name="通话结束时间",column="H")
    private String endtime;
	
	@ExcelField(name="时长（分）",column="I")
    private Double totalseconds;
	
	
    private String typeid;
	
	@ExcelField(name="呼叫类型",column="K")
    private String ntype;
	
	@ExcelField(name="需求大类",column="L")
    private String typedemand;
	
	@ExcelField(name="系统来源",column="M")
    private String systemsrc;
	
	private Double money;//历史金额
	
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


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

    public String getCalledno() {
        return calledno;
    }

    public void setCalledno(String calledno) {
        this.calledno = calledno == null ? null : calledno.trim();
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime == null ? null : begintime.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }

    public Double getTotalseconds() {
        return totalseconds;
    }

    public void setTotalseconds(Double totalseconds) {
        this.totalseconds = totalseconds;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getNtype() {
        return ntype;
    }

    public void setNtype(String ntype) {
        this.ntype = ntype == null ? null : ntype.trim();
    }

    public String getTypedemand() {
        return typedemand;
    }

    public void setTypedemand(String typedemand) {
        this.typedemand = typedemand == null ? null : typedemand.trim();
    }

    public String getSystemsrc() {
        return systemsrc;
    }

    public void setSystemsrc(String systemsrc) {
        this.systemsrc = systemsrc == null ? null : systemsrc.trim();
    }

	
    
}