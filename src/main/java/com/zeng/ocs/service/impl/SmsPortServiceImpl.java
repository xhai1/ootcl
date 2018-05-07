package com.zeng.ocs.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeng.ocs.dao.SmsPortDao;
import com.zeng.ocs.po.Customer;
import com.zeng.ocs.po.DtChgOrderVo;
import com.zeng.ocs.po.HotLine;
import com.zeng.ocs.po.SmsPort;
import com.zeng.ocs.service.SmsPortService;
import com.zeng.ocs.util.ExcelUtil;
import com.zeng.ocs.util.PaginationUtil;
@Service("smsPortService")
public class SmsPortServiceImpl implements SmsPortService {

	@Autowired 
	SmsPortDao smsPortdao;
	


	/* 
	 * 点击分页查询
	 * (non-Javadoc)
	 * @see com.zeng.ocs.service.SmsPortService#showSmsPortByContidion(com.zeng.ocs.util.PaginationUtil)
	 */
	@Override
	public List<SmsPort> showSmsPortByContidion(PaginationUtil page) {
		
		return smsPortdao.selectSmsPortByCondition(page);
	}

	/* 
	 * 获取记录总数
	 * (non-Javadoc)
	 * @see com.zeng.ocs.service.SmsPortService#getCountByCondition(com.zeng.ocs.util.PaginationUtil)
	 */
	@Override
	public Long getCountByCondition(PaginationUtil page) {
		return smsPortdao.selectCountByCondition(page);
	}

	@Override
	public String addSmsPort(SmsPort smsPort) {
		/*List<String> cusids = smsPortdao.insertSelectSmsPort(smsPort);
		if(cusids == null) return "没有公司记录，请先添加此公司!";
		if(cusids.size() > 1) return "搜索到多个公司,改变计时所属公司再次尝试！";
		smsPort.setCusid(cusids.get(0));*/
		smsPortdao.insertSmsPort(smsPort);
		return "添加成功！";
	}

	@Override
	public String updateSmsPort(SmsPort smsPort) {
		
//		smsPortdao.updateCustomer(smsPort);
		/*smsPort.setCusid(smsPortdao.selectCusId(smsPort));*/
		smsPortdao.updateSmsPort(smsPort);
		return "更新成功！";
	}

	@Override
	public void exportAllSmsPort(ServletOutputStream outputStream) {
		
		List<SmsPort> smsPorts=new ArrayList<SmsPort>();
		smsPorts = smsPortdao.selectSmsPortForExport();
        ExcelUtil<SmsPort> e=new ExcelUtil<SmsPort>(SmsPort.class);
        e.exportExcel(smsPorts,"短信端口归属",outputStream);
	}

	@Override
	public List<SmsPort> importAllSmsPort(File file) {
		List<SmsPort> list = readSmsPort(file);
		List<SmsPort> errList = new ArrayList<SmsPort>();
		
		for(int i = 0 ; i < list.size(); i++){
			SmsPort smsPort = list.get(i);
			List<SmsPort> tempSmsPorts =  smsPortdao.selectSmsPortForImport(smsPort);
			
			List<String> cusIds = smsPortdao.selectCusidByCusName(smsPort);
			
			if((tempSmsPorts.size() == 1) &&( (tempSmsPorts.get(0).getPort() != (smsPort.getPort())) 
					|| (tempSmsPorts.get(0).getPort() != (smsPort.getPort())) 
					|| (tempSmsPorts.get(0).getPurpose() != (smsPort.getPurpose())) 
					|| (tempSmsPorts.get(0).getCusname() != (smsPort.getCusname())) 
					|| (tempSmsPorts.get(0).getCaroprator() != smsPort.getCaroprator()) 
					)
					){
				smsPort.setId(tempSmsPorts.get(0).getId());
				if(cusIds.size() == 1)smsPort.setCusid(cusIds.get(0));
				smsPortdao.updateSmsPortForImport(smsPort);
				list.remove(smsPort);
				i--;
			}else if(tempSmsPorts.size() > 1){
				errList.add(smsPort);
				list.remove(smsPort);
				i--;
			}else{
				
				if(cusIds.size() == 1){
					System.out.println(cusIds.get(0));
					smsPort.setCusid(cusIds.get(0));
					smsPortdao.insertSelectiveMulti981(smsPort);
				}else if( (cusIds.size() > 1) || (cusIds.size() == 0) ){
					errList.add(smsPort);
					list.remove(smsPort);
					i--;
				}
			}
				 
		}
		return errList;		
	}

	public List<SmsPort> readSmsPort(File file)
	{
		List<SmsPort> list = new ArrayList<SmsPort>();
		try{
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);//创建Excel工作簿对象	
			XSSFSheet sheet = workbook.getSheetAt(0);//获取第1个工作表
		
			for(int i = 1;i <= sheet.getLastRowNum();i++){//循环Excel文件的每一行
				SmsPort smsPort = new SmsPort();
				XSSFRow row = sheet.getRow(i);//获取第i行
				XSSFCell cell1 = row.getCell(0);
				XSSFCell cell2 = row.getCell(1);
				XSSFCell cell3 = row.getCell(2);
				XSSFCell cell4 = row.getCell(3);
				
			
				
				cell1.setCellType(CellType.STRING);
				String port = cell1 == null ? null : cell1.getStringCellValue();//获取第i行的第1个单元格的数据
				smsPort.setPort(port);
				cell2.setCellType(CellType.STRING);
				String purpose = cell2 == null ? null :  cell2.getStringCellValue();//获取第i行的第2个单元格的数据
				smsPort.setPurpose(purpose);
				cell3.setCellType(CellType.STRING);
				String cusname = cell3 == null ? null :  cell3.getStringCellValue();//获取第i行的第3个单元格的数据
				smsPort.setCusname(cusname);
				
//				cell4.setCellType(CellType.NUMERIC);
				String Caroprator = cell1 == null ? null :  cell4.getStringCellValue();  
				smsPort.setCaroprator(Caroprator);
				
				list.add(smsPort);
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
	public Boolean delSmsport(SmsPort smsPort) {
		smsPortdao.deleteSmsPort(smsPort);
		return true;
	}

	
}
