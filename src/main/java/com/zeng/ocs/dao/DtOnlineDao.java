package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.DtOlServiceVo;
/**
 * 在线服务dao层接口
 * @author cxs
 *
 */
public interface DtOnlineDao {

	public int findCount(DtOlServiceVo dtOlServiceVo);

	public List<DtOlServiceVo> findAllDtOlService(DtOlServiceVo dtOlServiceVo);

}
