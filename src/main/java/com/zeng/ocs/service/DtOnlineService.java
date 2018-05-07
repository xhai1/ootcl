package com.zeng.ocs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.zeng.ocs.po.DtOlServiceVo;
import com.zeng.ocs.util.PageBean;
/**
 * 在线服务的service接口
 * @author cxs
 *
 */
public interface DtOnlineService {
	public PageBean<DtOlServiceVo> showDtOnlineServiceByLimit(int pageNo, int pageSize, DtOlServiceVo dtOlServiceVo);

	public List<DtOlServiceVo> findDtOlService(HttpSession session, DtOlServiceVo dtOlServiceVo, String page);
}
