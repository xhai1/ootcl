package com.zeng.ocs.po;

import com.zeng.ocs.util.ExcelField;
/**
 * 在线服务模板
 * @author cxs
 *
 */
public class DtOlService {
    private Integer id;

    private String cusid;
    @ExcelField(name="日期",column="C")
    private String date;
    public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid == null ? null : typeid.trim();
	}

	@ExcelField(name="媒体ID",column="D")
    private String mediaid;
    @ExcelField(name="媒体类别",column="E")
    private String mediatype;
    @ExcelField(name="媒体来源",column="F")
    private String mediasrc;
    @ExcelField(name="业务类别",column="G")
    private String businesstype;
    @ExcelField(name="开始时间",column="H")
    private String begintime;
    @ExcelField(name="结束时间",column="I")
    private String endtime;
    @ExcelField(name="时长",column="J")
    private Integer totalseconds;
    
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

	private String typeid;//产品大类码

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
        this.date = date == null ? null : date.trim();
    }

    public String getMediaid() {
        return mediaid;
    }

    public void setMediaid(String mediaid) {
        this.mediaid = mediaid == null ? null : mediaid.trim();
    }

    public String getMediatype() {
        return mediatype;
    }

    public void setMediatype(String mediatype) {
        this.mediatype = mediatype == null ? null : mediatype.trim();
    }

    public String getMediasrc() {
        return mediasrc;
    }

    public void setMediasrc(String mediasrc) {
        this.mediasrc = mediasrc == null ? null : mediasrc.trim();
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype == null ? null : businesstype.trim();
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

    public Integer getTotalseconds() {
        return totalseconds;
    }

    public void setTotalseconds(Integer totalseconds) {
        this.totalseconds = totalseconds;
    }
}