package com.zeng.ocs.service;

import java.util.List;

import javax.servlet.ServletOutputStream;

import com.zeng.ocs.po.CusTypeVo;
import com.zeng.ocs.util.PageBean;
/**
 * 产品大类的service层接口
 * @author cxs
 *
 */
public interface ProductService {

	public PageBean<CusTypeVo> showProductClassByLimit(int pageNo, int pageSize, CusTypeVo cusTypeVo);

	public List<CusTypeVo> findProductClass(CusTypeVo cusTypeVo);

	public void exportProductClass(List<CusTypeVo> productClassList, String string, ServletOutputStream outputStream);

	public List<CusTypeVo> findAllCustype();

	/*public List<CusTypeVo> findAllCustype();*/
	
	public List<CusTypeVo> findACustype(String cusid);

}
