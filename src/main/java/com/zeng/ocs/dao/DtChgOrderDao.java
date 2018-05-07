package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.DtChgOrderVo;
/**
 * 改单明细的dao层接口
 * @author cxs
 *
 */
public interface DtChgOrderDao {

	public int findCount(DtChgOrderVo dtChgOrderVo);

	public List<DtChgOrderVo> findAllDtChgOrder(DtChgOrderVo dtChgOrderVo);
}