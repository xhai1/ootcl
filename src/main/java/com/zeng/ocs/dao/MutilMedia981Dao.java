package com.zeng.ocs.dao;

import java.util.List;

import org.mybatis.spring.MyBatisSystemException;

import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.po.MutilMedia981;
import com.zeng.ocs.util.PaginationUtil;

/**
 * @date:2017年12月17日 下午9:03:22
 * @author Jianghai Yang
 * @version :
 */
public interface MutilMedia981Dao
{
	public Long getCountByCondition(PaginationUtil pageCondition);//
	public List<MutilMedia981> selectByCondition(PaginationUtil page) ;//条件搜索
	
	//条件搜索
	public List<MutilMedia981> selectAll981BySearch(MutilMedia981 mutilMedia981);
	
	
	public List<MutilMedia981> selectAllForExport();
	/**
	 * 修改查询
	 * @author Jianghai Yang
	 * @FileName MutilMedia981Dao.java
	 * @param mutilMedia981
	 * @return
	 */
	
	public void  updateByPrimaryKeySelective(MutilMedia981 mutilMedia981);
	public void UpdateHotLineFromMulti981(MutilMedia981 mutilMedia981);
	public void UpdateCustomerFromMulti981(MutilMedia981 mutilMedia981);
	
	public void insertMulti981(MutilMedia981 mutilMedia981);
	
	public MutilMedia981 selectByItenantId(MutilMedia981 mutilMedia981);
	public List<HotLine> selectByTelephone(MutilMedia981 mutilMedia981);//导入查询cusphone and customer
	
	//批量导入
	/*public void batchInsert(List<MutilMedia981> mutilMedia981s);*/
	public void insertSelectiveMulti981(MutilMedia981 mutilMedia981);
	
	
	public List<MutilMedia981> autoselectByTelephone(MutilMedia981 mutilMedia981) throws MyBatisSystemException;
	
	//删除多媒体租户信息
	public void deleteMulti981(MutilMedia981 mutilMedia981);
	
	public String selectCustomer(MutilMedia981 mutilMedia981);
}
