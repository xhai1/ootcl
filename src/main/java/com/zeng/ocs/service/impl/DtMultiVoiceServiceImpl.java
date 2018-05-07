package com.zeng.ocs.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zeng.ocs.dao.DtMultiVoiceDao;
import com.zeng.ocs.po.DtMultiVoiceVo;
import com.zeng.ocs.po.DtVoiceVo;
import com.zeng.ocs.po.User;
import com.zeng.ocs.service.DtMultiVoiceService;
import com.zeng.ocs.util.PageBean;
/**
 * 多媒体语音的service实现
 * @author cxs
 *
 */
@Service
public class DtMultiVoiceServiceImpl implements DtMultiVoiceService{
	
	@Autowired
	private DtMultiVoiceDao dtMultiVoiceDao;
	@Override
	public PageBean<DtMultiVoiceVo> showDtMultiVoiceByLimit(int pageNo, int pageSize,DtMultiVoiceVo dtMultiVoiceVo) {
		PageBean<DtMultiVoiceVo> pageBean = new PageBean<DtMultiVoiceVo>();
		// 设置当前页数:
		pageBean.setPage(pageNo);
		// 设置每页显示记录数:
		pageBean.setLimit(pageSize);
		// 设置总记录数:
		int totalCount = 0;
		int begin = (pageNo-1)*pageSize;
		dtMultiVoiceVo.setBegin(begin);
		dtMultiVoiceVo.setLimit(pageSize);
		
		totalCount =dtMultiVoiceDao.findCount(dtMultiVoiceVo);
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
		List<DtMultiVoiceVo> list=dtMultiVoiceDao.findAllDtMultiVoice(dtMultiVoiceVo);
		
		pageBean.setSubtotal(0.0);
		for(DtMultiVoiceVo dt : list ){
			pageBean.setSubtotal(pageBean.getSubtotal() + ( dt.getTotalseconds() == null ? 0.0 : dt.getTotalseconds() ));
		}
		//保留4位小数
		double   sub   =   pageBean.getSubtotal()/60.0;    
		BigDecimal   b   =   new   BigDecimal(sub);    
		double   f1   =   b.setScale(4,   BigDecimal.ROUND_HALF_UP).doubleValue();   
		pageBean.setSubtotal(f1);
		
		BigDecimal div = new BigDecimal("60.0000");
		pageBean.setTotal(dtMultiVoiceDao.selectTotalSecond(dtMultiVoiceVo).subtract( div ));		
		
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public List<DtMultiVoiceVo> findDtMultiVoice(HttpSession session,DtMultiVoiceVo dtMultiVoiceVo,String page) {
		int pageNo;
		User user=(User) session.getAttribute("user");
		if(user.getIsroot()==0){
			pageNo=Integer.parseInt(page);
			int begin = (pageNo-1)*50;
			dtMultiVoiceVo.setBegin(begin);
			dtMultiVoiceVo.setLimit(50);
//			PageHelper.startPage(pageNo, 20);
		}else{
			pageNo=Integer.parseInt(page);
			int begin = (pageNo-1)*50;
			dtMultiVoiceVo.setBegin(begin);
			dtMultiVoiceVo.setLimit(30000);
		}
		return dtMultiVoiceDao.findAllDtMultiVoice(dtMultiVoiceVo);
	}

}
