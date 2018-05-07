package com.zeng.ocs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.zeng.ocs.po.DtMessageVo;
import com.zeng.ocs.util.PageBean;
/**
 * 短信明细的service层接口
 * @author cxs
 *
 */
public interface DtMessageService {

	public PageBean<DtMessageVo> showDtMessageByLimit(int pageNo, int pageSize, DtMessageVo dtMessageVo);

	public List<DtMessageVo> findDtMessage(HttpSession session, DtMessageVo dtMessageVo, String page);

}
