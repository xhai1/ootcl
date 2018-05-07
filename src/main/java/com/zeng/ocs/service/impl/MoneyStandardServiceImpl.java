package com.zeng.ocs.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zeng.ocs.dao.CustomerDao;
import com.zeng.ocs.dao.MoneyStandardDao;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.po.MoneyStandard;
import com.zeng.ocs.service.MoneyStandardService;
@Service("moneyStandardService")
public class MoneyStandardServiceImpl implements MoneyStandardService {

	@Autowired
	public CustomerDao customerDao;
	

	@Autowired
	public MoneyStandardDao moneyStandardDao;
	
	@Override
	public List<Customer> getAllCus() {
		return customerDao.selectAll();
	}
	
	@Override
	public List<MoneyStandard> showCusStandard(Customer customer) {
		
		List<MoneyStandard> moneyStandars = moneyStandardDao.showCusStardardByCustomer(customer);
		
		
		return moneyStandars;
	}

	@Override
	public void updateMoneyStandard(List<MoneyStandard> MoneyStandards) {
		for(MoneyStandard moneyStandard : MoneyStandards){
			moneyStandardDao.updateByPrimaryKeySelective(moneyStandard);
		}
	}

	@Override
	public void addClass(MoneyStandard moneyStandard) {
		moneyStandardDao.insertSelective(moneyStandard);
		
	}

	@Override
	public String firstAddMoneyStandard(List<MoneyStandard> MoneyStandards) {
		List<MoneyStandard> msf = moneyStandardDao.firstSelectByCusid(MoneyStandards.get(0));
		
		System.out.println(MoneyStandards.get(0)+"\r\n");
		if(msf.size() != 0) return "存在此公司，不可再次导入！";
		for(MoneyStandard ms : MoneyStandards)
			moneyStandardDao.insertSelective(ms);
		
		
		return "添加成功！";
		
	}

	@Override
	public List<MoneyStandard> comListSearchMoneyStandard(Customer customer) {
		
		List<MoneyStandard>  moneyStandard = moneyStandardDao.showCusStardardByCustomer(customer);		
		
		return moneyStandard;
	}

	@Override
	public String FirstImporthMoneyStandard(File file) {
		String errCusName = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook;

			workbook = new XSSFWorkbook(fis);// 创建Excel工作簿对象

			Integer sheettotal = workbook.getNumberOfSheets();
			for (int i = 0; i < sheettotal; i++) {

				String cusName = ReadFileName(workbook, i);
				if(cusName == null) continue;
				List<MoneyStandard> moneyStandards = FirstImporthReadMoneyStandard(workbook, i);
				Customer cus = moneyStandardDao.selectCustomerForImport(cusName.trim());
				if (cus != null) {
					MoneyStandard moneyStandard = moneyStandardDao.selectMoneyStandarForImport(cus);
					if (moneyStandard == null) {
						for (MoneyStandard ms : moneyStandards) {
							ms.setCusId(cus.getCusid());
							moneyStandardDao.insertSelective(ms);
						}
					} else {
						errCusName += "系统已初始过公司:" + cusName + "\\n";
					}
				} else {
					errCusName += "系统中没有此公司,需添加:" + cusName + "\\n";
				}

			}
			workbook.close();
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		file.delete();
		return errCusName;
	}

	
	public 	String ReadFileName(XSSFWorkbook workbook,Integer sheetno){
				XSSFSheet sheet = workbook.getSheetAt(sheetno);//获取第1个工作表
				String result = "";
				XSSFRow row = sheet.getRow(0);
				XSSFCell cell = row == null ? null :row.getCell(1);
				result = cell == null ? null : cell.getStringCellValue();
				return result;
	}
	
	public 	List<MoneyStandard>  FirstImporthReadMoneyStandard(XSSFWorkbook workbook,Integer sheetno){

		List<MoneyStandard> list = new ArrayList<MoneyStandard>();
		try{
			XSSFSheet sheet = workbook.getSheetAt(sheetno);//获取第1个工作表
			
			MoneyStandard moneyStandard = new MoneyStandard();
			XSSFRow row = sheet.getRow(2);//获取第3行
			XSSFCell cell3 = row.getCell(2);
			XSSFCell cell4 = row.getCell(3);
			
			moneyStandard.setioType("0");
			moneyStandard.setmType(0);
			moneyStandard.setTimes(60);
			moneyStandard.setNumber(1);
			
			cell3.setCellType(CellType.NUMERIC);
			Double value = cell3 == null ? null : cell3.getNumericCellValue();//获取第i行的第2个单元格的数据
			cell4.setCellType(CellType.NUMERIC);
			Double price = cell4 == null ? null : cell4.getNumericCellValue();//获取第i行的第3个单元格的数据
			
			moneyStandard.setValue(value);
			moneyStandard.setPrice(price);
			
			list.add(moneyStandard);
			
			
			
			for(int i=4;i<=sheet.getLastRowNum()-1;i++){//循环Excel文件的每一行
				moneyStandard = new MoneyStandard();
				row = sheet.getRow(i);//获取第i行
				XSSFCell cell2 = row.getCell(1);
				cell3 = row.getCell(2);
				cell4 = row.getCell(3);
				
				moneyStandard.setioType("1");
				moneyStandard.setmType(1);
				
				String clazz = cell2 == null ? null : cell2.getStringCellValue();//获取第i行的第2个单元格的数据
				
				cell3.setCellType(CellType.NUMERIC);
				 value = cell3 == null ? null : cell3.getNumericCellValue();//获取第i行的第2个单元格的数据
				cell4.setCellType(CellType.NUMERIC);
				 price = cell4 == null ? null : cell4.getNumericCellValue();//获取第i行的第3个单元格的数据
				
				 moneyStandard.setClazz(clazz);
				 moneyStandard.setValue(value);
				 moneyStandard.setPrice(price);
				
				 moneyStandard.setTimes(60);
				moneyStandard.setNumber(1);
				 
				list.add(moneyStandard);
			}
			
			moneyStandard = new MoneyStandard();
			row = sheet.getRow(sheet.getLastRowNum());//获取第mo行
			cell3 = row.getCell(2);
			
			moneyStandard.setmType(2);
			moneyStandard.setTimes(1);
			moneyStandard.setNumber(1);
			
			cell3.setCellType(CellType.NUMERIC);
			value = cell3 == null ? null : cell3.getNumericCellValue();//获取第i行的第2个单元格的数据
			
			
			
			moneyStandard.setValue(value);
			list.add(moneyStandard);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		
	}

}
