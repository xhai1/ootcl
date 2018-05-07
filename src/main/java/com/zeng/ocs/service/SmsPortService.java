package com.zeng.ocs.service;


import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.zeng.ocs.po.SmsPort;
import com.zeng.ocs.util.PaginationUtil;

public interface SmsPortService {
	
	public  List<SmsPort>  showSmsPortByContidion(PaginationUtil page);
	
	public Long getCountByCondition(PaginationUtil page);
	
	
	public String addSmsPort(SmsPort smsPort);
	
	public String updateSmsPort(SmsPort smsPort);
	
	/**
	 * 导出excel，全部导出
	 * @param outputStream
	 */
	public void exportAllSmsPort(ServletOutputStream outputStream);
	
	/**
	 *导入excel，全部导入
	 * @param outputStream
	 */
	public List<SmsPort> importAllSmsPort(File file);
	
	public Boolean delSmsport(SmsPort smsPort);
}
