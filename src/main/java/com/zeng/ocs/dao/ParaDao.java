package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.Para;

/**
 * @date:2017年12月17日 上午1:03:51
 * @author Jianghai Yang
 * @version :
 */
public interface ParaDao
{
    int deleteByPrimaryKey(Integer id);

    int insert(Para record);

    int insertSelective(Para record);

    Para selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Para record);

    int updateByPrimaryKey(Para record);

    
    List<Para> selectParaAll();
	public Para selectByPkey(String pkey);
	
	
	
	
}
