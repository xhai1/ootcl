package com.zeng.ocs.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zeng.ocs.dao.DtOnlineDao;
import com.zeng.ocs.po.DtOlServiceVo;
import com.zeng.ocs.po.User;
import com.zeng.ocs.service.DtOnlineService;
import com.zeng.ocs.util.PageBean;
/**
 * 在线服务的service实现
 * @author cxs
 *
 */
@Service
public class DtOnlineServiceImpl implements DtOnlineService{
	
	@Autowired
	private DtOnlineDao dtOnlineDao;
	
	@Override
	public PageBean<DtOlServiceVo> showDtOnlineServiceByLimit(int pageNo, int pageSize,DtOlServiceVo dtOlServiceVo) {
		PageBean<DtOlServiceVo> pageBean = new PageBean<DtOlServiceVo>();
		// 设置当前页数:
		pageBean.setPage(pageNo);
		// 设置每页显示记录数:
		pageBean.setLimit(pageSize);
		// 设置总记录数:
		int totalCount = 0;
		int begin = (pageNo-1)*pageSize;
		dtOlServiceVo.setBegin(begin);
		dtOlServiceVo.setLimit(pageSize);
		
		totalCount = dtOnlineDao.findCount(dtOlServiceVo);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		pageBean.setTotalPage(totalPage);
//		PageHelper.startPage(pageNo, pageSize);
		List<DtOlServiceVo> list=dtOnlineDao.findAllDtOlService(dtOlServiceVo);
		
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public List<DtOlServiceVo> findDtOlService(HttpSession session,DtOlServiceVo dtOlServiceVo,String page) {
		int pageNo;
		User user=(User) session.getAttribute("user");
		if(user.getIsroot()==0){
			pageNo=Integer.parseInt(page);
			int begin = (pageNo-1)*50;
			dtOlServiceVo.setBegin(begin);
			dtOlServiceVo.setLimit(50);
//			PageHelper.startPage(pageNo, 20);
		}else{
			pageNo=Integer.parseInt(page);
			int begin = (pageNo-1)*50;
			dtOlServiceVo.setBegin(begin);
			dtOlServiceVo.setLimit(30000);
		}
		return dtOnlineDao.findAllDtOlService(dtOlServiceVo);
	}

}
