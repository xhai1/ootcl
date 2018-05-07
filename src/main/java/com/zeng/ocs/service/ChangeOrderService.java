package com.zeng.ocs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.zeng.ocs.po.DtChgOrderVo;
import com.zeng.ocs.util.PageBean;
/**
 * 改单业务接口
 * @author cxs
 *
 */
public interface ChangeOrderService {

	public PageBean<DtChgOrderVo> showDtChgOrderByLimit(int pageNo, int pageSize, DtChgOrderVo dtChgOrderVo);

	public List<DtChgOrderVo> findDtChgOrder(HttpSession session, DtChgOrderVo dtChgOrderVo, String page);

}
