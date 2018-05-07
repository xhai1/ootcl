package com.zeng.ocs.po;

import com.zeng.ocs.util.ExcelField;
/**
 * 产品大类实体
 * @author cxs
 *
 */
public class CusType {
	
    private Integer id;

    private String cusid;
    
    @ExcelField(name="产品大类码",column="C")
    private String typeid;
    @ExcelField(name="产品大类",column="D")
    private String type;
    @ExcelField(name="计时产品大类",column="E")
    private String typetimeing;
    @ExcelField(name="业务类别",column="A")
    private String business;
    @ExcelField(name="品牌",column="B")
    private String brand;

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

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTypetimeing() {
        return typetimeing;
    }

    public void setTypetimeing(String typetimeing) {
        this.typetimeing = typetimeing == null ? null : typetimeing.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }
}