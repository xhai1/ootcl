package com.zeng.ocs.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zeng.ocs.dao.DtMessageDao;
import com.zeng.ocs.po.DtMessageVo;
import com.zeng.ocs.po.User;
import com.zeng.ocs.service.DtMessageService;
import com.zeng.ocs.util.PageBean;
/**
 * 语音明细的service实现
 * @author cxs
 *
 */
@Service
public class DtMessageServiceImpl implements DtMessageService{
	@Autowired
	private DtMessageDao dtMessageDao;

	@Override
	public PageBean<DtMessageVo> showDtMessageByLimit(int pageNo, int pageSize,DtMessageVo dtMessageVo) {
		PageBean<DtMessageVo> pageBean = new PageBean<DtMessageVo>();
		// 设置当前页数:
		pageBean.setPage(pageNo);
		// 设置每页显示记录数:
		pageBean.setLimit(pageSize);
		// 设置总记录数:
		int totalCount = 0;
		
		int begin = (pageNo-1)*pageSize;
		dtMessageVo.setBegin(begin);
		dtMessageVo.setLimit(pageSize);
		
		totalCount = dtMessageDao.findCount(dtMessageVo);
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
		List<DtMessageVo> list=dtMessageDao.findAllDtMessage(dtMessageVo);
		
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public List<DtMessageVo> findDtMessage(HttpSession session,DtMessageVo dtMessageVo,String page) {
		int pageNo;
		User user=(User) session.getAttribute("user");
		if(user.getIsroot()==0){
			pageNo=Integer.parseInt(page);
			int begin = (pageNo-1)*50;
			dtMessageVo.setBegin(begin);
			dtMessageVo.setLimit(50);
//			PageHelper.startPage(pageNo, 20);
		}else{
			pageNo=Integer.parseInt(page);
			int begin = (pageNo-1)*50;
			dtMessageVo.setBegin(begin);
			dtMessageVo.setLimit(30000);
		}
		return dtMessageDao.findAllDtMessage(dtMessageVo);
	}
	
}
