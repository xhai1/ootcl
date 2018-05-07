package com.zeng.ocs.po;
/**
 * 用户认证通过后的用户信息
 * @author cxs
 *
 */
public class ActiveUser {
	private int id;
	private String cusid;
	private String username;
	private String truename;
	private int isroot;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCusid() {
		return cusid;
	}
	public void setCusid(String cusid) {
		this.cusid = cusid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public int getIsroot() {
		return isroot;
	}
	public void setIsroot(int isroot) {
		this.isroot = isroot;
	}
	
}
