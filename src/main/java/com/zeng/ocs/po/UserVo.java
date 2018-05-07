package com.zeng.ocs.po;
/**
 * 用户的子类添加查询条件
 * @author cxs
 *
 */
public class UserVo extends User{
	private String cusname;

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}
	
}
