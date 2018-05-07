package com.zeng.ocs.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeng.ocs.dao.CustomerDao;
import com.zeng.ocs.dao.HotLineDao;
import com.zeng.ocs.dao.MutilMedia981Dao;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.po.MutilMedia981;
import com.zeng.ocs.po.SmsPort;
import com.zeng.ocs.service.MutilMedia981Service;
import com.zeng.ocs.util.PaginationUtil;
/**
 * @date:2017年12月17日 下午9:05:40
 * @author Jianghai Yang
 * @version :
 */
@Service("mutilMedia981Service")
public class MutilMedia981ServiceImpl implements MutilMedia981Service
{



	@Autowired
	MutilMedia981Dao mutilMedia981Dao ;
	
	@Autowired
	HotLineDao hotLineDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Override
	public Long getCountByCondition(PaginationUtil pageCondition)
	{
		return mutilMedia981Dao.getCountByCondition(pageCondition);
	}
	//条件搜索吓一跳记录
	@Override
	public List<MutilMedia981> showMultiMedia981ByCondition(PaginationUtil page) {
		return mutilMedia981Dao.selectByCondition(page);
	}

	@Override
	public List<MutilMedia981> showMTulti981BySearch(MutilMedia981 mutilMedia981)
	{
		return mutilMedia981Dao.selectAll981BySearch(mutilMedia981);
	}

	
	@Override
	public List<MutilMedia981> exportMutilMedia981All() {
		return mutilMedia981Dao.selectAllForExport();
	}

	/* 
	 * 导出全部数据
	 * 
	 * (non-Javadoc)
	 * @see com.zeng.ocs.service.MutilMedia981Service#exportAllTulti981Detail(java.util.List, javax.servlet.ServletOutputStream)
	 */
	@Override
	public void exportAllTulti981Detail(List<MutilMedia981> mutilMedia981s, ServletOutputStream outputStream)
	{
//导出全部
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		 
        
        try{
        	XSSFWorkbook workbook = new XSSFWorkbook();//创建Excel工作簿对象	
    		XSSFSheet sheet = workbook.createSheet();//在工作簿中创建工作表对象
    		sheet.setColumnWidth( 0, 256*14);
            sheet.setColumnWidth( 1, 256*14);  
            sheet.setColumnWidth( 2, 400*14);  
            sheet.setColumnWidth( 3, 256*14);  
            sheet.setColumnWidth( 4, 600*14);  
            sheet.setColumnWidth( 5, 400*14);
           //    		sheet.setDefaultColumnWidth(600*14);
    		workbook.setSheetName(0, "多媒体981租户分机号");//设置工作表的名称
    		XSSFRow row1 = sheet.createRow(0);//在工作表中创建行对象	
    		
    		XSSFFont font = workbook.createFont();//创建字体对象
    		font.setColor(HSSFColor.WHITE.index);//设置字体颜色
    		font.setFontHeightInPoints((short)12);//设置字号
    	    font.setFontName("楷体");//设置字体样式 	
    	    
    		XSSFCellStyle titleStyle = workbook.createCellStyle();
    		titleStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());// 设置背景色   
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
    		
    		
//			// 运输方式
//			String[] textList1 = new String[]
//			{ "值1", "值二", "值三" };
//			XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
//			XSSFDataValidationConstraint dvConstraint1 = (XSSFDataValidationConstraint) dvHelper
//					.createExplicitListConstraint(textList1);
//			// 操作类型
//			String[] textList2 =
//			{ "I", "U", "D" };
//			XSSFDataValidationConstraint dvConstraint2 = (XSSFDataValidationConstraint) dvHelper
//					.createExplicitListConstraint(textList2);
//
//			// 运输方式下拉 首行，末行，首列，末列
//			CellRangeAddressList addressList1 = new CellRangeAddressList(2, hotLineList.size()+1,11, 11);
//			XSSFDataValidation dataValidation1 = (XSSFDataValidation) dvHelper.createValidation(dvConstraint1, addressList1);
//
//			// 操作类型下拉 首行，末行，首列，末列
//			CellRangeAddressList addressList2 = new CellRangeAddressList(2, hotLineList.size()+1,11, 11);
//			XSSFDataValidation dataValidation2 = (XSSFDataValidation) dvHelper.createValidation(dvConstraint2, addressList2);
//			sheet.addValidationData(dataValidation1);
//			sheet.addValidationData(dataValidation2);
    		
    		
    		XSSFFont fontc = (XSSFFont) workbook.createFont();//创建字体对象  
    		fontc.setFontName("微软雅黑");  
    		XSSFCellStyle cellStyle = workbook.createCellStyle();
    		cellStyle.setAlignment(HorizontalAlignment.CENTER);//设置水平居中
    		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直居中		
    		cellStyle.setFont(fontc);
//    		cellStyle.setLocked(false);
    		XSSFCell titleCell1 = row1.createCell(0);
    		titleCell1.setCellValue("租户");
    		titleCell1.setCellStyle(titleStyle);
    		XSSFCell titleCell2 = row1.createCell(1);
    		titleCell2.setCellValue("分机号");
    		titleCell2.setCellStyle(titleStyle);
    		
    		XSSFCell titleCell3 = row1.createCell(2);
    		titleCell3.setCellValue("落地号");
    		titleCell3.setCellStyle(titleStyle);
    		XSSFCell titleCell4 = row1.createCell(3);
    		titleCell4.setCellValue("计时产品大类	");
    		titleCell4.setCellStyle(titleStyle);
    		
    		XSSFCell titleCell5 = row1.createCell(4);
    		titleCell5.setCellValue("计时所属公司");
    		titleCell5.setCellStyle(titleStyle);
    		
    		XSSFCell titleCell6 = row1.createCell(5);
    		titleCell6.setCellValue("修改日期");
    		titleCell6.setCellStyle(titleStyle);    		
    		
//    		sheet.enableLocking();//开启保护
//    		CTSheetProtection sheetProtection = sheet.getCTWorksheet().getSheetProtection();
//    		sheetProtection.setSelectLockedCells(false);
//    		sheetProtection.setSelectUnlockedCells(false);
//    		sheetProtection.setFormatCells(true);
//    		sheetProtection.setFormatColumns(true);
//    		sheetProtection.setFormatRows(true);
//    		sheetProtection.setInsertColumns(true);
//    		sheetProtection.setInsertRows(false);
//    		sheetProtection.setInsertHyperlinks(true);
//    		sheetProtection.setDeleteColumns(true);
//    		sheetProtection.setDeleteRows(true);
//    		sheetProtection.setSort(false);
//    		sheetProtection.setAutoFilter(false);
//    		sheetProtection.setPivotTables(true);
//    		sheetProtection.setObjects(true);
//    		sheetProtection.setScenarios(true);
    		

    		
    		
    		for(int i=0;i < mutilMedia981s.size();i++){//遍历保存数据对象的集合
    			MutilMedia981 mutilMedia981 = mutilMedia981s.get(i);//获取封装数据的对象
    			XSSFRow dataRow = sheet.createRow(i+1);//创建行
    			
    			XSSFCell itenantId = dataRow.createCell(0);//创建单元格
    			itenantId.setCellValue(mutilMedia981.getItenantId());//将数据添加到单元格中
    			itenantId.setCellStyle(cellStyle);
    			itenantId.setCellType(CellType.STRING);
    			
    			XSSFCell extNumber = dataRow.createCell(1);
    			extNumber.setCellValue(mutilMedia981.getExtNumber());
    			extNumber.setCellStyle(cellStyle);
    			extNumber.setCellType(CellType.STRING);
    			
    			XSSFCell telephone = dataRow.createCell(2);
    			telephone.setCellValue(mutilMedia981.getTelephone());
    			telephone.setCellStyle(cellStyle);
    			telephone.setCellType(CellType.STRING);
    			
    			XSSFCell bigType = dataRow.createCell(3);	
    			bigType.setCellValue(mutilMedia981.getBigType());
    			bigType.setCellStyle(cellStyle);
    			bigType.setCellType(CellType.STRING);
    			
    			XSSFCell cusName = dataRow.createCell(4);	
    			cusName.setCellValue(mutilMedia981.getCusName());
    			cusName.setCellStyle(cellStyle);
    			cusName.setCellType(CellType.STRING);
    			
    			XSSFCell updateTime = dataRow.createCell(5);	
    			updateTime.setCellValue(dateFormat.format(mutilMedia981.getUpdateTime()));
    			updateTime.setCellStyle(cellStyle);    
    			updateTime.setCellType(CellType.STRING);
    			
//    			XSSFCellStyle lockstyle = workbook.createCellStyle();
//    			lockstyle.setLocked(true);
//    			lockstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//    			lockstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
    			
    			//主键，隐藏，导入时使用
//    			XSSFCell id = dataRow.createCell(11);	
//    			id.setCellValue(hot.getId());
//    			id.setCellStyle(lockstyle);
//    			sheet.setColumnHidden(11, true);//隐藏第12列--excel就不显示纵列了  
    		}				
//    		File xlsFile = new File("d:\\员工信息表.xls");
//    		FileOutputStream fos = new FileOutputStream(xlsFile);
    		outputStream.flush();
    		workbook.write(outputStream);//将文档对象写入文件输出流
    		 outputStream.close();
//    		fos.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} 
		
	}



	@Override
	public String UpdateMulti981(MutilMedia981 mutilMedia981)
	{
		
		
		/*String cusId = mutilMedia981Dao.selectCustomer(mutilMedia981);
		if(cusId == null){
			return Prompt("系统中没有这个公司或是公式名称不正确!");
		}
		
		mutilMedia981.setCusId(cusId);*/
		mutilMedia981Dao.updateByPrimaryKeySelective(mutilMedia981);
		return "更改成功!";
	}


	@Override
	public void AddMulti981(MutilMedia981 mutilMedia981)
	{
		
//		mutilMedia981.setCusId(mutilMedia981Dao.selectCustomer(mutilMedia981));
		mutilMedia981Dao.insertMulti981(mutilMedia981);
	}

	
	
	@Override
	public List<MutilMedia981> readMultiMedia981(File file)
	{
		List<MutilMedia981> list = new ArrayList<MutilMedia981>();
		try{
			FileInputStream fis = new FileInputStream(file);
//			POIFSFileSystem fs  = new POIFSFileSystem(fis);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);//创建Excel工作簿对象	
			XSSFSheet sheet = workbook.getSheetAt(0);//获取第1个工作表
//			XSSFPatriarch patriarch = sheet.getDrawingPatriarch();
		
			for(int i=1;i<=sheet.getLastRowNum();i++){//循环Excel文件的每一行
				MutilMedia981 mutilMedia981 = new MutilMedia981();
				XSSFRow row = sheet.getRow(i);//获取第i行
				XSSFCell cell1 = row.getCell(0);
				XSSFCell cell2 = row.getCell(1);
				XSSFCell cell3 = row.getCell(2);
				XSSFCell cell4 = row.getCell(3);
				XSSFCell cell5 = row.getCell(4);
//				XSSFCell cell11 = row.getCell(11);
				
				cell1.setCellType(CellType.STRING);
				String itenantId = cell1 == null ? null : cell1.getStringCellValue();//获取第i行的第1个单元格的数据
				mutilMedia981.setItenantId(itenantId);
				
				cell2.setCellType(CellType.STRING);
				String extNumber =  cell2 == null ? null :  cell2.getStringCellValue();//获取第i行的第2个单元格的数据				
				mutilMedia981.setExtNumber(extNumber);;
				
				cell3.setCellType(CellType.STRING);
				String telephone  =   cell3 == null ? null :  cell3.getStringCellValue();//获取第i行的第3个单元格的数据
				mutilMedia981.setTelephone(telephone);;
				
				cell4.setCellType(CellType.STRING);
				String bigType =   cell4 == null ? null :  cell4.getStringCellValue();//获取第i行的第4个单元格的数据
				mutilMedia981.setBigType(bigType);
				
				cell5.setCellType(CellType.STRING);
				String cusName =  cell5 == null ? null :  cell5.getStringCellValue();//获取第i行的第5个单元格的数据
				mutilMedia981.setCusName(cusName);
				
				
//				Integer id = (int) cell11.getNumericCellValue();
//				hot.setId(id);;
				list.add(mutilMedia981);
			}
			workbook.close();
			fis.close();
			file.delete();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	
	}

	/*
	 * 981导入
	 *  (non-Javadoc)
	 * @see com.zeng.ocs.service.MutilMedia981Service#importMutilMedia981ToDb(java.util.List)
	 */
	@Override
	public List<MutilMedia981> importMutilMedia981ToDb(List<MutilMedia981> mutilMedia981s)
	{
		
		List<MutilMedia981> errList = new ArrayList<MutilMedia981>();
		
		for(int i = 0 ; i < mutilMedia981s.size(); i++){
			MutilMedia981 mutilMedia981 = mutilMedia981s.get(i);
			MutilMedia981 mutilMedia981n =  mutilMedia981Dao.selectByItenantId(mutilMedia981);
			
			String cusId = mutilMedia981Dao.selectCustomer(mutilMedia981);
			
			if( cusId == null){
				errList.add(mutilMedia981);
				mutilMedia981s.remove(mutilMedia981);
				i--;
				continue;
			}
			
			if((mutilMedia981n != null) &&( (mutilMedia981n.getItenantId() != (mutilMedia981.getItenantId())) 
					|| (mutilMedia981n.getExtNumber() != (mutilMedia981.getExtNumber()))
					|| (mutilMedia981n.getTelephone() != (mutilMedia981.getTelephone())) 	
					|| (mutilMedia981n.getBigType() != (mutilMedia981.getBigType())) 
					)
					){
						mutilMedia981.setId(mutilMedia981n.getId());
						mutilMedia981.setCusId(cusId);
						mutilMedia981Dao.updateByPrimaryKeySelective(mutilMedia981);
						mutilMedia981s.remove(mutilMedia981);
						i--;
			}else {		
				mutilMedia981.setCusId(cusId);
				mutilMedia981Dao.insertSelectiveMulti981(mutilMedia981);
			}
				 
		}
		return errList;	
	}

	@Override
	public String autoShowAddMultiMedia981(String telephone) {
		MutilMedia981 mutilMedia981 = new MutilMedia981();
		mutilMedia981.setTelephone(telephone);
		
			List<MutilMedia981> mutilMedia981s = mutilMedia981Dao.autoselectByTelephone(mutilMedia981);
			if(mutilMedia981s.size() == 1){
				return mutilMedia981s.get(0).getBigType()+","+mutilMedia981s.get(0).getCusName();
			}	
			
			 return "没有对应产品大类,没有对应所属公司";	
	}
	@Override
	public Boolean delMultiMedia981(MutilMedia981 mutilMedia981) {
		mutilMedia981Dao.deleteMulti981(mutilMedia981);
		return true;
	}


	
}
