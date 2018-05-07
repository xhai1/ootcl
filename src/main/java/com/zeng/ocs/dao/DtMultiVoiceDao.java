package com.zeng.ocs.dao;

import java.math.BigDecimal;
import java.util.List;

import com.zeng.ocs.po.DtMultiVoiceVo;
/**
 * 多媒体的dao层接口
 * @author cxs
 *
 */
public interface DtMultiVoiceDao {

	public int findCount(DtMultiVoiceVo dtMultiVoiceVo);

	public List<DtMultiVoiceVo> findAllDtMultiVoice(DtMultiVoiceVo dtMultiVoiceVo);
	
	public BigDecimal selectTotalSecond(DtMultiVoiceVo dtMultiVoiceVo);

}
