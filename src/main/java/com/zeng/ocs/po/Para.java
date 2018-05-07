package com.zeng.ocs.po;

import java.io.Serializable;

/**
 * @date:2017年12月17日 上午1:01:29
 * @author Jianghai Yang
 * @version :
 */
public class Para implements Serializable
{

	private Integer id;
	private String pkey;
	private String pvalue;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getPkey()
	{
		return pkey;
	}
	public void setPkey(String pkey)
	{
		this.pkey = pkey;
	}
	public String getPvalue()
	{
		return pvalue;
	}
	public void setPvalue(String pvalue)
	{
		this.pvalue = pvalue;
	}
	@Override
	public String toString()
	{
		return "Para [id=" + id + ", pkey=" + pkey + ", pvalue=" + pvalue + "]";
	}
	
}
