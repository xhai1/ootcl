package com.zeng.ocs.dao;

import java.sql.SQLException;
import java.util.List;

import com.zeng.ocs.po.CusType;
import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.util.PaginationUtil;


/**
 * @date:2017年12月7日 下午2:24:46
 * @author Jianghai Yang
 * @version :
 */
public interface HotLineDao
{
	public Long  selectCountByCondition(PaginationUtil pageCondition);
    public List<HotLine>  selectHotLineByCondition(PaginationUtil pageCondition);
    public List<HotLine>  selectHotLineBusinessAndOthersByLike(HotLine hotline);
    
    
    public Boolean  selectCutomerByHotline(HotLine hotLine);
    public Boolean  insertHotLine(HotLine hotLine);
    
    
    public HotLine  selectHotLineByIdAndCusid(HotLine hotLine);
    
    
    /**
     * 更改
     * @author Jianghai Yang
     * @FileName HotLineDao.java
     * @param hotLine
     * @throws SQLException
     */
    public void  updateHotLine(HotLine hotLine) throws SQLException;
    /*public void updateCustomerByHotLine(HotLine hotLine);*/
    
    public void  deleteHotLineById(Integer id) throws SQLException;
    public void  batchDelete(List<Integer> ids)throws SQLException;
    
    public List<HotLine> selectHotAll();//导出excel，全部导出

    /**
     * 批量导入数据
     * @author Jianghai Yang
     * @FileName HotLineDao.java
     * @param hots
     * @throws SQLException
     */
    /*public void  batchInsert(List<HotLine> hots)throws SQLException;*/
    
    public void insertSelectiveHotLine(HotLine hotLine);
    public List<HotLine> selectByTelephone(HotLine hotLine);
    public void updateImportHotline(HotLine hotLine);
    //根据落地号和产品大类查询
    public HotLine selectHotLineByTelephoneAndBigType(HotLine hotLine);
    
    //查询产品大类
    public CusType selectCusType(HotLine hotLine);
    public Integer selectMaxIdOfCusType();//获取数据库中最大的ID
    public void insertSelectiveCusType(CusType cusType);
}
