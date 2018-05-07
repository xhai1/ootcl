package com.zeng.ocs.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zeng.ocs.dao.DtChgOrderDao;
import com.zeng.ocs.po.DtChgOrderVo;
import com.zeng.ocs.po.User;
import com.zeng.ocs.service.AddOrderService;
import com.zeng.ocs.util.PageBean;
/**
 * 补单业务实现
 * @author cxs
 *
 */
@Service
public class AddOrderServiceImpl implements AddOrderService{

	@Autowired
	private DtChgOrderDao dtChgOrderDao;
	
	@Override
	public PageBean<DtChgOrderVo> showDtChgOrderByLimit(int pageNo, int pageSize,DtChgOrderVo dtChgOrderVo) {
		
		PageBean<DtChgOrderVo> pageBean = new PageBean<DtChgOrderVo>();
		// 设置当前页数:
		pageBean.setPage(pageNo);
		// 设置每页显示记录数:
		pageBean.setLimit(pageSize);
		// 设置总记录数:
		int totalCount = 0;
		int begin = (pageNo-1)*pageSize;
		dtChgOrderVo.setBegin(begin);
		dtChgOrderVo.setLimit(pageSize);
		
		totalCount = dtChgOrderDao.findCount(dtChgOrderVo);
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
		List<DtChgOrderVo> list=dtChgOrderDao.findAllDtChgOrder(dtChgOrderVo);
		
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public List<DtChgOrderVo> findDtChgOrder(HttpSession session,DtChgOrderVo dtChgOrderVo,String page) {
		int pageNo;
		User user=(User) session.getAttribute("user");
		if(user.getIsroot()==0){
			pageNo=Integer.parseInt(page);
			int begin = (pageNo-1)*50;
			dtChgOrderVo.setBegin(begin);
			dtChgOrderVo.setLimit(50);
//			PageHelper.startPage(pageNo, 20);
		}else{
			pageNo=Integer.parseInt(page);
			int begin = (pageNo-1)*50;
			dtChgOrderVo.setBegin(begin);
			dtChgOrderVo.setLimit(30000);
		}
		return dtChgOrderDao.findAllDtChgOrder(dtChgOrderVo);
	}

}
