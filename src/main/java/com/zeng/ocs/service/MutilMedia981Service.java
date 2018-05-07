package com.zeng.ocs.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.zeng.ocs.po.MutilMedia981;
import com.zeng.ocs.util.PaginationUtil;

/**
 * @date:2017年12月17日 下午9:04:54
 * @author Jianghai Yang
 * @version :
 */
public interface MutilMedia981Service
{
	public Long getCountByCondition(PaginationUtil pageCondition);
	public List<MutilMedia981> showMultiMedia981ByCondition(PaginationUtil page);
	
	public List<MutilMedia981> showMTulti981BySearch(MutilMedia981 mutilMedia981); 

	public List<MutilMedia981> exportMutilMedia981All();
	public void exportAllTulti981Detail(List<MutilMedia981>  mutilMedia981s , ServletOutputStream OutputStream);
	
	
	/**
	 * 修改查询
	 * @author Jianghai Yang
	 * @FileName MutilMedia981Service.java
	 * @param mutilMedia981
	 * @return
	 */
	public String UpdateMulti981(MutilMedia981 mutilMedia981);
	public void AddMulti981(MutilMedia981 mutilMedia981);
	
	public List<MutilMedia981> readMultiMedia981(File file);
	public List<MutilMedia981> importMutilMedia981ToDb( List<MutilMedia981> mutilMedia981s );
	public String autoShowAddMultiMedia981(String telephone);
	
	public Boolean delMultiMedia981(MutilMedia981 mutilMedia981);
}
