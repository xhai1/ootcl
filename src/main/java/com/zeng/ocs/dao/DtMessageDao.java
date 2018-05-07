package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.DtMessageVo;
/**
 * 短信的dao层接口
 * @author cxs
 *
 */
public interface DtMessageDao {

	public int findCount(DtMessageVo dtMessageVo);

	public List<DtMessageVo> findAllDtMessage(DtMessageVo dtMessageVo);
}