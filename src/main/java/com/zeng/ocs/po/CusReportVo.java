package com.zeng.ocs.po;

/**
 * 月报表子实体类
 * 添加查询条件显示条件
 * @author cxs
 *
 */
public class CusReportVo extends CusReport{
	
	private String cusname;

	//添加查询条件
	private String begintime;
	private String endtime;

	private double totalin;
	private double totalout;
	private double inhour;
	private double outhour;
	
	private String totalmoney;
	
	private String fmtMonth;
	
	public String getFmtMonth() {
		return fmtMonth;
	}



	public void setFmtMonth(String fmtMonth) {
		this.fmtMonth = fmtMonth;
	}



	public String getTotalmoney() {
		return totalmoney;
	}



	public void setTotalmoney(String totalmoney) {
		this.totalmoney = totalmoney;
	}



	public double getInhour() {
		return inhour;
	}



	public void setInhour(double inhour) {
		this.inhour = inhour;
	}



	public double getOuthour() {
		return outhour;
	}



	public void setOuthour(double outhour) {
		this.outhour = outhour;
	}



	public double getTotalin() {
		return totalin;
	}



	public void setTotalin(double totalin) {
		this.totalin = totalin;
	}



	public double getTotalout() {
		return totalout;
	}



	public void setTotalout(double totalout) {
		this.totalout = totalout;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}
	
}
