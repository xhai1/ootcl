package com.zeng.ocs.configuration;
/**
 * @date:2017年12月8日 下午12:21:15
 * @author Jianghai Yang
 * @version :
 */
public class Configuration
{
	/**
	 * 默认每页显示50条记录
	 */
	public  static final Integer PAGE_NUM = 50;
	
	public  static final String USER_NAME = "root";
	
//	public  static final String PASSWORD =  "xy123456";
	public  static final String PASSWORD =  "hzu6666";
	
	public  static final String DATABASE = "ocs";
	
	/**
	 * 每页显示的记录
	 */
	private  Integer perPageNum;
	
	private  String userName;
	
	private  String password;
	
	private  String database;
	
	public Configuration()
	{
		this.perPageNum = PAGE_NUM;
		this.userName = USER_NAME;
		this.password = PASSWORD;
		this.database = DATABASE;
	}
	public Integer getPerPageNum()
	{
		return perPageNum;
	}

	public void setPerPageNum(Integer perPageNum)
	{
		this.perPageNum = perPageNum;
	}
	

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getDatabase()
	{
		return database;
	}

	public void setDatabase(String database)
	{
		this.database = database;
	}

}
