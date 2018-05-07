package com.zeng.ocs.dao;

import java.util.List;

import com.zeng.ocs.po.DtChgOrderVo;
import com.zeng.ocs.po.DtMessageVo;
import com.zeng.ocs.po.DtMultiVoiceVo;
import com.zeng.ocs.po.DtOlServiceVo;
import com.zeng.ocs.po.DtVoiceVo;
import com.zeng.ocs.util.PaginationUtil;

public interface TlAccountDao {
	/**
	 * @author  Jianghai Yang
	 * @date	2018年3月26日下午9:47:56
	 */
	public List<DtVoiceVo> selectTlAccountDtVoice(PaginationUtil paginationUtil);
	
	public List<DtMultiVoiceVo> selectTlAccountDtMultiVoice(PaginationUtil paginationUtil);
	
	public List<DtOlServiceVo> selectTlAccountDtOlservice(PaginationUtil paginationUtil);
	
	public List<DtChgOrderVo> selectTlAccountDtchgorder(PaginationUtil paginationUtil);
	
	public List<DtMessageVo> selectTlAccountDtmessage(PaginationUtil paginationUtil);
	
	
	
	
}
