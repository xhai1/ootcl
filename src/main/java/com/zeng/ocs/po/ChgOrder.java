package com.zeng.ocs.po;

import com.zeng.ocs.util.ExcelField;
/**
 * 改单实体类
 * @author cxs
 *
 */
public class ChgOrder {
	private int no;
	@ExcelField(name="公司",column="A")
	private String companyName;
	@ExcelField(name="月份",column="B")
	private String month;
	@ExcelField(name="日期",column="C")
	private String date;
	@ExcelField(name="备注",column="D")
	private String remark;
	@ExcelField(name="业务技能",column="E")
	private String skill;
	@ExcelField(name="受理编号",column="F")
	private String acceptno;
	@ExcelField(name="CASE单号",column="G")
	private String CASE;
	@ExcelField(name="产品大类ID",column="H")
	private String productId;
	@ExcelField(name="产品大类",column="I")
	private String product;
	@ExcelField(name="产品小类",column="J")
	private String productType;
	@ExcelField(name="需求大类",column="K")
	private String remandType;
	@ExcelField(name="需求小类",column="L")
	private String remand;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getAcceptno() {
		return acceptno;
	}
	public void setAcceptno(String acceptno) {
		this.acceptno = acceptno;
	}
	public String getCASE() {
		return CASE;
	}
	public void setCASE(String cASE) {
		CASE = cASE;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getRemandType() {
		return remandType;
	}
	public void setRemandType(String remandType) {
		this.remandType = remandType;
	}
	public String getRemand() {
		return remand;
	}
	public void setRemand(String remand) {
		this.remand = remand;
	}
	
}
