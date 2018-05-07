package com.zeng.ocs.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeng.ocs.po.ChgOrder;
import com.zeng.ocs.util.ExcelUtil;
/**
 * 测试导出excel
 * @author cxs
 *
 */
@Controller
@RequestMapping("/excel")
public class ExcelAction {
	
	
	@RequestMapping("/export")
	public void export(HttpServletResponse response) throws IOException{
		List<ChgOrder> chgOrderList=new ArrayList<ChgOrder>();
		for(int i=0;i<50;i++){
			ChgOrder chgOrder=new ChgOrder();
			chgOrder.setNo(i+1);
			chgOrder.setCompanyName("xxx公司");
			chgOrder.setMonth("2017-10");
			chgOrder.setDate("2017-10-01");
			chgOrder.setRemark("改单");
			chgOrder.setSkill("彩电");
			chgOrder.setAcceptno("1710011947021094");
			chgOrder.setCASE("CS1710010827000675");
			chgOrder.setProductId("4960");
			chgOrder.setProduct("东芝彩电");
			chgOrder.setProductType("东芝液晶");
			chgOrder.setRemand("座装");
			chgOrder.setRemandType("要求座装");
			chgOrderList.add(chgOrder);
		}
		
		//response.setContentType("application/x-excel");
        //response.setHeader("Content-Disposition", "attachment;filename=" + new String("改单明细表.xlsx".getBytes(), "ISO-8859-1"));
        //ServletOutputStream outputStream = response.getOutputStream();
        //excelService.exportExcel(chgOrderList, outputStream);
		//excelService.exportExcel(chgOrderList, outputStream);
		response.setHeader("Content-Disposition", "attachment;filename=" + new String("改单明细表.xlsx".getBytes("utf-8"), "ISO-8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelUtil<ChgOrder> e=new ExcelUtil<ChgOrder>(ChgOrder.class);
        e.exportExcel(chgOrderList,"改单明细表",outputStream);
        if(outputStream != null){
			 outputStream.close();
		}
	}
	
}
