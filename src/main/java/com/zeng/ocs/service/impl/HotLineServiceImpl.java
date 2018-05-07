package com.zeng.ocs.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeng.ocs.dao.CustomerDao;
import com.zeng.ocs.dao.HotLineDao;
import com.zeng.ocs.po.CusType;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.service.HotLineService;
import com.zeng.ocs.util.PaginationUtil;
/**
 * @date:2017年12月8日 上午9:00:35
 * @author Jianghai Yang
 * @version :
 */
@Service("hotLineService")
public class HotLineServiceImpl implements HotLineService
{

	@Autowired
	private HotLineDao hotLineDAO;
	@Autowired
	private CustomerDao customerDao;
	
	//查询
	
	@Override
	public Long getCountByCondition(PaginationUtil pageCondition) {
		return hotLineDAO.selectCountByCondition(pageCondition);
	}	
	@Override
	public List<HotLine> showHotLineByContidion(PaginationUtil pageCondition)
	{
		return hotLineDAO.selectHotLineByCondition(pageCondition);
	}
	@Override
	public List<HotLine> showHotlineBusinessAndOtheresByLike(HotLine hotline)
	{
		
		return hotLineDAO.selectHotLineBusinessAndOthersByLike(hotline);
	}
	@Override
	public String addHotLine(HotLine hotLine)
	{
		/*Customer cus  = new Customer();
		cus.setCusname(hotLine.getCusName());
		cus.setTelephone(hotLine.getCusTelephone());*/
		/*List<Customer> cusList = customerDao.selectCutomerBytelephoneAndCusname(cus);
		if(cusList.size() == 1){
			hotLine.setCusId(cusList.get(0).getCusid());
			hotLineDAO.insertHotLine(hotLine);
			return "添加成功!";
		}*/
		hotLineDAO.insertHotLine(hotLine);
		return  "添加失败!,请填写正确的公司名称.";
	}
	@Override
	public HotLine showHotlineByIdAndCusid(HotLine hotline)
	{
		return hotLineDAO.selectHotLineByIdAndCusid(hotline);
	}
	@Override
	public void renewHotline(HotLine hotline) throws SQLException
	{
			/*hotLineDAO.updateCustomerByHotLine(hotline);*/
			hotLineDAO.updateHotLine(hotline);
	}
	@Override
	public void removeHotlineById(Integer id) throws SQLException
	{
		 hotLineDAO.deleteHotLineById(id);
	}
	@Override
	public void batchDelByIds(List<Integer> ids) throws SQLException
	{
		hotLineDAO.batchDelete(ids);
		
	}
	@Override
	public List<HotLine> exportHotlineAll()
	{
		return hotLineDAO.selectHotAll();
	}
	@Override
	public void exportHotlineAllDetail(List<HotLine> hotLineList, ServletOutputStream outputStream)
	{
		//导出全部
        
        try{
        	XSSFWorkbook workbook = new XSSFWorkbook();//创建Excel工作簿对象	
    		XSSFSheet sheet = workbook.createSheet();//在工作簿中创建工作表对象
    		sheet.setColumnWidth( 0, 256*14);
            sheet.setColumnWidth( 1, 256*14);  
            sheet.setColumnWidth( 2, 128*14);  
            sheet.setColumnWidth( 3, 256*14);  
            sheet.setColumnWidth( 4, 256*14);  
            sheet.setColumnWidth( 5, 256*14);
            sheet.setColumnWidth( 6, 256*14);
            sheet.setColumnWidth( 7, 600*14); 
            
            sheet.setColumnWidth( 8, 400*14);  
            sheet.setColumnWidth( 9, 256*14);
            sheet.setColumnWidth( 10, 400*14);
//    		sheet.setDefaultColumnWidth(600*14);
    		workbook.setSheetName(0, "热线号落地号");//设置工作表的名称
    		XSSFRow row1 = sheet.createRow(0);//在工作表中创建行对象	
    		sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 10));//合并第1行的第8个到第12个之间的单元格
    		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));//合并第1行的第1个到第7个之间的单元格
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
    		XSSFCell titleCell = row1.createCell(0);
    		titleCell.setCellValue("IT维护信息");
    		titleCell.setCellStyle(titleStyle);
    		XSSFCell titleCell2 = row1.createCell(6);
    		titleCell2.setCellValue("计费系统");
    		titleCell2.setCellStyle(titleStyle2);
    		
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
    		
    		
    		XSSFRow row2 = sheet.createRow(1);
    		XSSFCell businessCell = row2.createCell(0);//在第1行中创建单元格对象
    		businessCell.setCellValue("业务");
    		businessCell.setCellStyle(titleStyle);
    		
    		XSSFCell custelephoneCell = row2.createCell(1);//在行中创建单元格对象
    		custelephoneCell.setCellValue("热线号");
    		custelephoneCell.setCellStyle(titleStyle);		
    		XSSFCell ivrCell = row2.createCell(2);//在行中创建单元格对象
    		ivrCell.setCellValue("IVR");
    		ivrCell.setCellStyle(titleStyle);
    		XSSFCell telephoneCell = row2.createCell(3);//在行中创建单元格对象
    		telephoneCell.setCellValue("落地号");
    		telephoneCell.setCellStyle(titleStyle);
    		XSSFCell itSystemCell = row2.createCell(4);//在行中创建单元格对象
    		itSystemCell.setCellValue("所属系统");
    		itSystemCell.setCellStyle(titleStyle);	    		
    		XSSFCell itRemarkCell = row2.createCell(5);//在行中创建单元格对象
    		itRemarkCell.setCellValue("备注");
    		itRemarkCell.setCellStyle(titleStyle);
    		
    		XSSFCell bigTypeCell = row2.createCell(6);//在行中创建单元格对象
    		bigTypeCell.setCellValue("计时产品大类");
    		bigTypeCell.setCellStyle(titleStyle2);	
    		XSSFCell cusNameCell = row2.createCell(7);//在行中创建单元格对象
    		cusNameCell.setCellValue("计时所属公司");
    		cusNameCell.setCellStyle(titleStyle2);	
    		XSSFCell chaSystemCell = row2.createCell(8);//在行中创建单元格对象
    		chaSystemCell.setCellValue("话务系统");
    		chaSystemCell.setCellStyle(titleStyle2);	
    		XSSFCell custCell = row2.createCell(9);//在行中创建单元格对象
    		custCell.setCellValue("热线号");
    		custCell.setCellStyle(titleStyle2);	
    		XSSFCell telCell = row2.createCell(10);//在行中创建单元格对象
    		telCell.setCellValue("落地号");
    		telCell.setCellStyle(titleStyle2);	    		
    		
    		for(int i=0;i < hotLineList.size();i++){//遍历保存数据对象的集合
    			HotLine hot = (HotLine)hotLineList.get(i);//获取封装数据的对象
    			XSSFRow dataRow = sheet.createRow(i+2);//创建行
    			XSSFCell business = dataRow.createCell(0);//创建单元格
    			business.setCellValue(hot.getBusiness());//将数据添加到单元格中
    			business.setCellStyle(cellStyle);
    			XSSFCell cusTelephone = dataRow.createCell(1);
    			cusTelephone.setCellValue(hot.getCusTelephone());
    			cusTelephone.setCellStyle(cellStyle);
    			XSSFCell ivr = dataRow.createCell(2);
    			ivr.setCellValue(hot.getIvr());
    			ivr.setCellStyle(cellStyle);
    			XSSFCell telephone = dataRow.createCell(3);
    			telephone.setCellValue(hot.getTelephone());
    			telephone.setCellStyle(cellStyle);
    			XSSFCell itSystem = dataRow.createCell(4);	
    			itSystem.setCellValue(hot.getItSystem());
    			itSystem.setCellStyle(cellStyle);
    			
    			XSSFCell itRemark = dataRow.createCell(5);	
    			itRemark.setCellValue(hot.getItRemark());
    			itRemark.setCellStyle(cellStyle);
    			XSSFCell bigType = dataRow.createCell(6);	
    			bigType.setCellValue(hot.getBigType());
    			bigType.setCellStyle(cellStyle);
    			XSSFCell cusName = dataRow.createCell(7);	
    			cusName.setCellValue(hot.getCusName());
    			cusName.setCellStyle(cellStyle);
    			XSSFCell chaSystem = dataRow.createCell(8);	
    			chaSystem.setCellValue(hot.getChaSystem());
    			chaSystem.setCellStyle(cellStyle);
    			XSSFCell cust = dataRow.createCell(9);	
    			cust.setCellValue(hot.getCusTelephone());
    			cust.setCellStyle(cellStyle);
    			XSSFCell tel = dataRow.createCell(10);	
    			tel.setCellValue(hot.getTelephone());
    			tel.setCellStyle(cellStyle);
    			
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
	public List<HotLine> readHotLine(File file)
	{
		List<HotLine> list = new ArrayList<HotLine>();
		try{
			FileInputStream fis = new FileInputStream(file);
//			POIFSFileSystem fs  = new POIFSFileSystem(fis);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);//创建Excel工作簿对象	
			XSSFSheet sheet = workbook.getSheetAt(0);//获取第1个工作表
//			XSSFPatriarch patriarch = sheet.getDrawingPatriarch();
		
			for(int i=2;i<=sheet.getLastRowNum();i++){//循环Excel文件的每一行
				HotLine hot = new HotLine();
				XSSFRow row = sheet.getRow(i);//获取第i行
				XSSFCell cell1 = row.getCell(0);
				XSSFCell cell2 = row.getCell(1);
				XSSFCell cell3 = row.getCell(2);
				XSSFCell cell4 = row.getCell(3);
				XSSFCell cell5 = row.getCell(4);
				XSSFCell cell6 = row.getCell(5);
				XSSFCell cell7 = row.getCell(6);
				XSSFCell cell8 = row.getCell(7);
				XSSFCell cell9 = row.getCell(8);
//				XSSFCell cell11 = row.getCell(11);
				String business = cell1 == null ? "" : cell1.getStringCellValue();//获取第i行的第1个单元格的数据
				hot.setBusiness(business);
				cell2.setCellType(CellType.STRING);
				String cusTelephone = cell2 == null ? "" : cell2.getStringCellValue();//获取第i行的第2个单元格的数据
				hot.setCusTelephone(cusTelephone);
				String ivr =  cell3 == null ? "" : cell3.getStringCellValue();//获取第i行的第3个单元格的数据
				hot.setIvr(ivr);
				
				cell4.setCellType(CellType.STRING);
				String telephone = cell4 == null ? "" : cell4.getStringCellValue();//获取第i行的第4个单元格的数据
				hot.setTelephone(telephone);
				String itSystem = cell5 == null ? "" : cell5.getStringCellValue();//获取第i行的第5个单元格的数据
				hot.setItSystem(itSystem);
				
				
				String itRemark = cell6 == null ? "" : cell6.getStringCellValue();//获取第i行的第6个单元格的数据
				hot.setItRemark(itRemark);
				String bigType = cell7 == null ? "" : cell7.getStringCellValue();//获取第i行的第7个单元格的数据
				hot.setBigType(bigType);
				String cusName = cell8 == null ? "" : cell8.getStringCellValue();//获取第i行的第8个单元格的数据
				hot.setCusName(cusName);
				String chaSystem = cell9 == null ? "" : cell9.getStringCellValue();//获取第i行的第9个单元格的数据
				hot.setChaSystem(chaSystem);
				
//				Integer id = (int) cell11.getNumericCellValue();
//				hot.setId(id);;
				list.add(hot);
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
	@Override
	public void importHotLineToDb(List<HotLine> hots) throws SQLException
	{
		List<CusType> cusTypes = new ArrayList<CusType>();
		
		for(int i = 0 ; i < hots.size(); i++){
			
			HotLine hotLine = hots.get(i);
			HotLine hotLinet =  hotLineDAO.selectHotLineByTelephoneAndBigType(hotLine);
			
			Customer cus = customerDao.selectCustomerByCusName(hotLine.getCusName());
			//添加公司
			if( (cus == null) ){
				cus = new Customer();
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				String currentTime = dateFormat.format(calendar.getTime());
				cus.setCusid(currentTime+"."+new Random().nextInt(9999));
				cus.setCusname(hotLine.getCusName());
				customerDao.insertSelective(cus);
			}
			//查询公司产品大类，没有则添加
			hotLine.setCusId(cus.getCusid());
			CusType cusType = hotLineDAO.selectCusType(hotLine);
			if(cusType == null){
				cusType = new CusType();
				//获取数据库中最大的ID值select max(id) from custype;
				Integer dbMaxId = hotLineDAO.selectMaxIdOfCusType();
				if(dbMaxId > 60001)
					cusType.setId(dbMaxId+i+1);
				else{
					cusType.setId(60001+i+1);
				}
				cusType.setCusid(cus.getCusid());
				cusType.setTypeid("sc"+new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime())+new Random().nextInt(999));
				cusType.setType(hotLine.getBigType());
				cusType.setTypetimeing(hotLine.getBigType());
				//插入数据库
				hotLineDAO.insertSelectiveCusType(cusType);
			}
			
			if((hotLinet != null) &&( (hotLinet.getBusiness() != (hotLine.getBusiness())) 
					|| (hotLinet.getCusTelephone() != (hotLine.getCusTelephone())) 
					|| (hotLinet.getIvr() != (hotLine.getIvr())) 
					|| (hotLinet.getItSystem() != (hotLine.getItSystem())) 
					|| (hotLinet.getItRemark() != (hotLine.getItRemark())) 					
					|| (hotLinet.getChaSystem() != hotLine.getChaSystem()) 					
					)
					){
				hotLine.setId(hotLinet.getId());
				hotLine.setCusId(cus.getCusid());
				hotLineDAO.updateImportHotline(hotLine);
				hots.remove(hotLine);
				i--;
			}else{
				hotLine.setCusId(cus.getCusid());
				hotLineDAO.insertSelectiveHotLine(hotLine);
			}
				 
		}
		
	}


}
