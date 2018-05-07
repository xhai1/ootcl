package com.zeng.ocs.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.zeng.ocs.po.CusType;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.DtVoiceVo;
import com.zeng.ocs.util.PageBean;
/**
 * 语音明细的service层接口
 * @author cxs
 *
 */
public interface DtVoiceService {
	public PageBean<DtVoiceVo> showDtVoiceByLimit(int page,int pageSize,DtVoiceVo dtVoiceVo);
	public List<DtVoiceVo> findDtVoice(HttpSession session, DtVoiceVo dtVoiceVo, String page);
}
