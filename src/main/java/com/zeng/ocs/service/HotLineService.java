package com.zeng.ocs.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.util.PaginationUtil;

/**
 * @date:2017年12月8日 上午8:59:16
 * @author Jianghai Yang
 * @version :
 */
public interface HotLineService
{
	//查询
	public  Long getCountByCondition(PaginationUtil pageCondition);
	public  List<HotLine> showHotLineByContidion(PaginationUtil pageCondition);
	
	public  List<HotLine> showHotlineBusinessAndOtheresByLike(HotLine hotline);
	public String addHotLine(HotLine hotLine);
	public  HotLine showHotlineByIdAndCusid(HotLine hotline);
	public void  renewHotline(HotLine hotline) throws SQLException;
	public void  removeHotlineById(Integer id) throws SQLException;
	public void  batchDelByIds(List<Integer> ids) throws SQLException;
	
	public  List<HotLine> exportHotlineAll();//导出全部cusphone
	public void exportHotlineAllDetail(List<HotLine> hotLineList,ServletOutputStream outputStream);
	
	
	/**
	 * 从Excel导入新增数据或者修改数据到list中 ， 
	 * @author Jianghai Yang
	 * @FileName HotLineService.java
	 * @param file 上传的文件
	 * @return
	 */
	public List<HotLine> readHotLine(File file);
	public void importHotLineToDb(List<HotLine> hots) throws SQLException;
}