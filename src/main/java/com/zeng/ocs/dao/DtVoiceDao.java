package com.zeng.ocs.dao;

import java.math.BigDecimal;
import java.util.List;

import com.zeng.ocs.po.CusType;
import com.zeng.ocs.po.DtVoiceVo;
import com.zeng.ocs.util.PageBean;
/**
 * 语音明细的Dao层接口
 * @author cxs
 *
 */
public interface DtVoiceDao {
	
	public int findCount(DtVoiceVo dtVoiceVo);
	
	//该方法不用
	public List<DtVoiceVo> findDtVoiceList(PageBean<DtVoiceVo> pageBean);
	
	public List<DtVoiceVo> findAllDtVoice(DtVoiceVo dtVoiceVo);
	
	//查询总时长
	public BigDecimal selectTotalSecond(DtVoiceVo dtVoiceVo);
	
}