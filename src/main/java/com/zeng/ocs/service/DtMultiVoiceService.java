package com.zeng.ocs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.zeng.ocs.po.DtMultiVoiceVo;
import com.zeng.ocs.util.PageBean;
/**
 * 多媒体的service接口
 * @author cxs
 *
 */
public interface DtMultiVoiceService {

	public PageBean<DtMultiVoiceVo> showDtMultiVoiceByLimit(int pageNo, int pageSize, DtMultiVoiceVo dtMultiVoiceVo);

	public List<DtMultiVoiceVo> findDtMultiVoice(HttpSession session, DtMultiVoiceVo dtMultiVoiceVo, String page);

}
