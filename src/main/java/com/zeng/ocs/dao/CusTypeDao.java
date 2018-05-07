package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.CusTypeVo;
/**
 * 产品大类的dao层接口
 * @author cxs
 *
 */
public interface CusTypeDao {

	public int findCount(CusTypeVo cusTypeVo);

	public List<CusTypeVo> findAllProductClass(CusTypeVo cusTypeVo);

	public List<CusTypeVo> findAllCustype();
	public List<CusTypeVo> findACustype(String cusid);
}