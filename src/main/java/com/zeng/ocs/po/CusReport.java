package com.zeng.ocs.po;
/**
 * 月报表实体
 * @author cxs
 *
 */
public class CusReport {
    private Integer id;

    private String cusid;

    private String month;

    private Integer incount;

    protected Integer inseconds;
    private Double invalue;
    private Integer outcount;

    protected Integer outsenconds;
    private Double outvalue;
    private Integer msgcount;
    private Double msgvalue;
    private Integer endflag;
    
    public Integer getEndflag() {
		return endflag;
	}

	public void setEndflag(Integer endflag) {
		this.endflag = endflag;
	}

	public Double getInvalue() {
		return invalue;
	}

	public void setInvalue(Double invalue) {
		this.invalue = invalue;
	}

	public Double getOutvalue() {
		return outvalue;
	}

	public void setOutvalue(Double outvalue) {
		this.outvalue = outvalue;
	}

	public Double getMsgvalue() {
		return msgvalue;
	}

	public void setMsgvalue(Double msgvalue) {
		this.msgvalue = msgvalue;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public Integer getIncount() {
        return incount;
    }

    public void setIncount(Integer incount) {
        this.incount = incount;
    }

    public Integer getInseconds() {
        return inseconds;
    }

    public void setInseconds(Integer inseconds) {
        this.inseconds = inseconds;
    }

    public Integer getOutcount() {
        return outcount;
    }

    public void setOutcount(Integer outcount) {
        this.outcount = outcount;
    }

    public Integer getOutsenconds() {
        return outsenconds;
    }

    public void setOutsenconds(Integer outsenconds) {
        this.outsenconds = outsenconds;
    }

    public Integer getMsgcount() {
        return msgcount;
    }

    public void setMsgcount(Integer msgcount) {
        this.msgcount = msgcount;
    }
}