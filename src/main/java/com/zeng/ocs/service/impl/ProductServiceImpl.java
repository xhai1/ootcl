package com.zeng.ocs.service.impl;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zeng.ocs.dao.CusTypeDao;
import com.zeng.ocs.po.CusTypeVo;
import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.service.ProductService;
import com.zeng.ocs.util.PageBean;
/**
 * 产品大类的service实现
 * @author cxs
 *
 */
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private CusTypeDao cusTypeDao;
	@Override
	public PageBean<CusTypeVo> showProductClassByLimit(int pageNo, int pageSize,CusTypeVo cusTypeVo) {
		
		PageBean<CusTypeVo> pageBean = new PageBean<CusTypeVo>();
		// 设置当前页数:
		pageBean.setPage(pageNo);
		cusTypeVo.setBegin((pageNo-1)*pageSize);
		cusTypeVo.setLimit(pageSize);
		// 设置每页显示记录数:
		pageBean.setLimit(pageSize);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = cusTypeDao.findCount(cusTypeVo);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}
		pageBean.setTotalPage(totalPage);
		/*PageHelper.startPage(pageNo, pageSize);*/
		List<CusTypeVo> list=cusTypeDao.findAllProductClass(cusTypeVo);
		
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public List<CusTypeVo> findProductClass(CusTypeVo cusTypeVo) {
		return cusTypeDao.findAllProductClass(cusTypeVo);
	}
	@Override
	public void exportProductClass(List<CusTypeVo> productClassList, String string, ServletOutputStream outputStream) {
//导出全部
        
        try{
        	XSSFWorkbook workbook = new XSSFWorkbook();//创建Excel工作簿对象	
    		XSSFSheet sheet = workbook.createSheet();//在工作簿中创建工作表对象
    		sheet.setColumnWidth( 0, 256*14);
            sheet.setColumnWidth( 1, 256*14);  
            sheet.setColumnWidth( 2, 128*14);  
            sheet.setColumnWidth( 3, 256*14);  
            sheet.setColumnWidth( 4, 256*14);  
            sheet.setColumnWidth( 5, 600*14);
    		workbook.setSheetName(0, string);//设置工作表的名称
    		XSSFRow row1 = sheet.createRow(0);//在工作表中创建行对象	
    		sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 5));//合并第1行的第8个到第12个之间的单元格
    		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));//合并第1行的第1个到第7个之间的单元格
    		XSSFFont font = workbook.createFont();//创建字体对象
    		font.setColor(new XSSFColor(java.awt.Color.BLACK));//设置字体颜色
    		font.setFontHeightInPoints((short)12);//设置字号
    	    font.setFontName("楷体");//设置字体样式 	  
    	    
    		XSSFCellStyle titleStyle = workbook.createCellStyle();
    		titleStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色   
    		titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
    		titleStyle.setBorderTop(BorderStyle.THIN);
    		titleStyle.setBorderLeft(BorderStyle.THIN);
    		titleStyle.setBorderRight(BorderStyle.THIN);
    		titleStyle.setBorderBottom(BorderStyle.THIN);
    		titleStyle.setTopBorderColor(new XSSFColor(java.awt.Color.BLACK));
    		titleStyle.setBottomBorderColor(new XSSFColor(java.awt.Color.BLACK));
    		titleStyle.setLeftBorderColor(new XSSFColor(java.awt.Color.BLACK));
    		titleStyle.setRightBorderColor(new XSSFColor(java.awt.Color.BLACK));
    		titleStyle.setFont(font);
    		titleStyle.setAlignment( HorizontalAlignment.CENTER);//设置水平居中
    		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直居中	
    		
    		
    		XSSFCellStyle titleStyle2 = workbook.createCellStyle();
    		titleStyle2.setFillForegroundColor(IndexedColors.PINK.getIndex());// 设置背景色   
    		titleStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
    		titleStyle2.setBorderTop(BorderStyle.THIN);
    		titleStyle2.setBorderLeft(BorderStyle.THIN);
    		titleStyle2.setBorderRight(BorderStyle.THIN);
    		titleStyle2.setBorderBottom(BorderStyle.THIN);
    		titleStyle2.setTopBorderColor(new XSSFColor(java.awt.Color.BLACK));
    		titleStyle2.setBottomBorderColor(new XSSFColor(java.awt.Color.BLACK));
    		titleStyle2.setLeftBorderColor(new XSSFColor(java.awt.Color.BLACK));
    		titleStyle2.setRightBorderColor(new XSSFColor(java.awt.Color.BLACK));
    		titleStyle2.setFont(font);
    		titleStyle2.setAlignment( HorizontalAlignment.CENTER);//设置水平居中
    		titleStyle2.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直居中	
    		
    		
    		
    		XSSFFont fontc = (XSSFFont) workbook.createFont();//创建字体对象  
    		fontc.setFontName("微软雅黑");  
    		XSSFCellStyle cellStyle = workbook.createCellStyle();
    		cellStyle.setAlignment(HorizontalAlignment.CENTER);//设置水平居中
    		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直居中		
    		cellStyle.setFont(fontc);
    		

    		
    		XSSFCell titleCell = row1.createCell(0);
    		titleCell.setCellValue("业务系统");
    		titleCell.setCellStyle(titleStyle);
    		XSSFCell titleCell2 = row1.createCell(4);
    		titleCell2.setCellValue("计费系统");
    		titleCell2.setCellStyle(titleStyle2);
 
    		
    		
    		XSSFRow row2 = sheet.createRow(1);
    		XSSFCell businessCell = row2.createCell(0);//在第1行中创建单元格对象
    		businessCell.setCellValue("业务类别");
    		businessCell.setCellStyle(titleStyle);
    		
    		XSSFCell custelephoneCell = row2.createCell(1);//在行中创建单元格对象
    		custelephoneCell.setCellValue("品牌");
    		custelephoneCell.setCellStyle(titleStyle);		
    		XSSFCell ivrCell = row2.createCell(2);//在行中创建单元格对象
    		ivrCell.setCellValue("产品大类码");
    		ivrCell.setCellStyle(titleStyle);
    		XSSFCell telephoneCell = row2.createCell(3);//在行中创建单元格对象
    		telephoneCell.setCellValue("产品大类");
    		telephoneCell.setCellStyle(titleStyle);
    		
    		XSSFCell itSystemCell = row2.createCell(4);//在行中创建单元格对象
    		itSystemCell.setCellValue("计时产品大类");
    		itSystemCell.setCellStyle(titleStyle2);	    		
    		XSSFCell itRemarkCell = row2.createCell(5);//在行中创建单元格对象
    		itRemarkCell.setCellValue("计时所属公司");
    		itRemarkCell.setCellStyle(titleStyle2);   		
    		    		
    		
    		for(int i=0;i < productClassList.size();i++){//遍历保存数据对象的集合
    			CusTypeVo cusTypeVo = productClassList.get(i);//获取封装数据的对象
    			XSSFRow dataRow = sheet.createRow(i+2);//创建行
    			
    			XSSFCell business = dataRow.createCell(0);//创建单元格
    			business.setCellValue(cusTypeVo.getBusiness());//将数据添加到单元格中
    			business.setCellStyle(cellStyle);
    			
    			XSSFCell cusTelephone = dataRow.createCell(1);
    			cusTelephone.setCellValue(cusTypeVo.getBrand());
    			cusTelephone.setCellStyle(cellStyle);
    			
    			XSSFCell ivr = dataRow.createCell(2);
    			ivr.setCellType(CellType.STRING);
    			ivr.setCellValue(cusTypeVo.getTypeid());
    			ivr.setCellStyle(cellStyle);
    			
    			XSSFCell telephone = dataRow.createCell(3);
    			telephone.setCellValue(cusTypeVo.getType());
    			telephone.setCellStyle(cellStyle);
    			
    			XSSFCell itSystem = dataRow.createCell(4);	
    			itSystem.setCellValue(cusTypeVo.getTypetimeing());
    			itSystem.setCellStyle(cellStyle);
    			
    			XSSFCell itRemark = dataRow.createCell(5);	
    			itRemark.setCellValue(cusTypeVo.getCusname());
    			itRemark.setCellStyle(cellStyle);
    			
    		}
    		outputStream.flush();
    		workbook.write(outputStream);//将文档对象写入文件输出流
    		outputStream.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} 
	}
	@Override
	public List<CusTypeVo> findAllCustype() {
		return cusTypeDao.findAllCustype();
	}
	
	public List<CusTypeVo> findACustype(String cusid){
		return  cusTypeDao.findACustype(cusid);
	}
}
