package com.zeng.ocs.util;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import com.zeng.ocs.po.CusReportVo;
/**
 * 导出WPS的word文件
 * @author cxs
 *
 */
public class WPSUtil {
	public static boolean exportWord(String comacount, String compopacbank, String chargeman, List<CusReportVo> list, OutputStream output) throws IOException{
		for(int i=0;i<list.size();i++){
			XWPFDocument xdoc = new XWPFDocument();
			XWPFParagraph xp = xdoc.createParagraph();
			xp.setSpacingBefore(0);
			XWPFRun r1 = xp.createRun();
			r1.setText("TCL统一服务热线计费系统");
			r1.addBreak(); // 换行
			r1.setFontFamily("宋体");
			r1.setFontSize(16);
			r1.setBold(true);
			xp.setAlignment(ParagraphAlignment.CENTER);
			
			XWPFParagraph xp2 = xdoc.createParagraph();
			XWPFRun r2 = xp2.createRun();
			r2.setFontFamily("宋体");
			r2.setFontSize(12);
			r2.setText("运营服务计费总表");
			r2.setBold(false);
			xp2.setAlignment(ParagraphAlignment.CENTER);
			
			XWPFParagraph xp3 = xdoc.createParagraph();
			XWPFRun r3 = xp3.createRun();
			r3.setFontFamily("宋体");
			r3.setFontSize(12);
			//TODO NO
			String month="NO."+list.get(i).getMonth().replace("-", "")+"01";
			r3.setText(month);
			r3.setBold(false);
			xp3.setAlignment(ParagraphAlignment.RIGHT);
			
			Integer col_total_count = 6; // 表格最多的列数
			XWPFTable xTable = xdoc.createTable(1, col_total_count);
			CTTbl ttbl = xTable.getCTTbl();
			CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
			CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
			tblWidth.setW(new BigInteger("8600"));
			tblWidth.setType(STTblWidth.DXA);
			// 创建表格内容第一行
			xTable.getRow(0).setHeight(400);
			String clientname="客戶姓名："+list.get(i).getCusname();
			setCellTextLeft(xdoc, xTable.getRow(0).getCell(0), clientname, "FFFFFF", 4300);
			
			setCellTextLeft(xdoc, xTable.getRow(0).getCell(1), "服务号码：", "FFFFFF", 4300);
			for (int j = 2; j <= col_total_count-1; j++) { // 创建虚拟列
				setCellTextLeft(xdoc, xTable.getRow(0).getCell(j),null, "FFFFFF", -1);
			}
			mergeCellsHorizontal(xTable, 0, 2, col_total_count-1);
			
			//第二行
			XWPFTableRow infoTableRowTwo=xTable.createRow();
			infoTableRowTwo.setHeight(450);
			
			setCellTextLeft(xdoc, xTable.getRow(1).getCell(0), "客户地址：", "FFFFFF", 4300);
			String chargetime="计费时段："+list.get(i).getMonth().replace("-", "年")+"月";
			setCellTextLeft(xdoc, xTable.getRow(1).getCell(1), chargetime, "FFFFFF", 4300);
			for (int j = 2; j <= col_total_count-1; j++) { // 创建虚拟列
				setCellTextLeft(xdoc, xTable.getRow(1).getCell(j),null, "FFFFFF", -1);
			}
			mergeCellsHorizontal(xTable, 0, 2, col_total_count-1);
			
			//第三行
			XWPFTableRow infoTableRowThree=xTable.createRow();
			infoTableRowThree.setHeight(450);
			String bank="开户银行:"+compopacbank;
			setCellTextLeft(xdoc, xTable.getRow(2).getCell(0), bank, "FFFFFF", 4300);
			String account="开户账户:"+comacount;
			setCellTextLeft(xdoc, xTable.getRow(2).getCell(1), account, "FFFFFF", 4300);
			for (int j = 2; j <= col_total_count-1; j++) { // 创建虚拟列
				setCellTextLeft(xdoc, xTable.getRow(2).getCell(j),null, "FFFFFF", -1);
			}
			mergeCellsHorizontal(xTable, 0, 2, col_total_count-1);
			
			
			//第四行
			XWPFTableRow infoTableRowFour=xTable.createRow();
			infoTableRowFour.setHeight(600);
			setCellTextCenter(xdoc, xTable.getRow(3).getCell(0), "收费项目", "FFFFFF", 800);
			setCellTextCenter(xdoc, xTable.getRow(3).getCell(1), "项目明细", "FFFFFF", 1750);
			setCellTextCenter(xdoc, xTable.getRow(3).getCell(2), "数量", "FFFFFF", 1750);
			setCellTextCenter(xdoc, xTable.getRow(3).getCell(3), "收费项目", "FFFFFF", 800);
			setCellTextCenter(xdoc, xTable.getRow(3).getCell(4), "项目明细", "FFFFFF", 1750);
			setCellTextCenter(xdoc, xTable.getRow(3).getCell(5), "数量", "FFFFFF", 1750);
			
			XWPFTableRow infoTableRowFive=xTable.createRow();
			infoTableRowFive.setHeight(400);
			setCellTextCenter(xdoc, xTable.getRow(4).getCell(0), "呼入", "FFFFFF", 800);
			setCellTextCenter(xdoc, xTable.getRow(4).getCell(1), "通话次数", "FFFFFF", 1750);
			String callin=list.get(i).getIncount()+"次";
			setCellTextCenter(xdoc, xTable.getRow(4).getCell(2), callin, "FFFFFF", 1750);
			setCellTextCenter(xdoc, xTable.getRow(4).getCell(3), "呼出", "FFFFFF", 800);
			setCellTextCenter(xdoc, xTable.getRow(4).getCell(4), "通話次数", "FFFFFF", 1750);
			String callout=list.get(i).getOutcount()+"次";
			setCellTextCenter(xdoc, xTable.getRow(4).getCell(5), callout, "FFFFFF", 1750);
			
			XWPFTableRow infoTableRowSix=xTable.createRow();
			infoTableRowSix.setHeight(400);
			setCellTextCenter(xdoc, xTable.getRow(5).getCell(0), "呼入", "FFFFFF", 800);
			setCellTextCenter(xdoc, xTable.getRow(5).getCell(1), "呼入话务时长", "FFFFFF", 1750);
			String callintime=list.get(i).getInhour()+"小时";
			setCellTextCenter(xdoc, xTable.getRow(5).getCell(2), callintime, "FFFFFF", 1750);
			setCellTextCenter(xdoc, xTable.getRow(5).getCell(3), "呼出", "FFFFFF", 800);
			setCellTextCenter(xdoc, xTable.getRow(5).getCell(4), "呼出话务时长", "FFFFFF", 1750);
			String callouttime=list.get(i).getOuthour()+"小时";
			setCellTextCenter(xdoc, xTable.getRow(5).getCell(5), callouttime, "FFFFFF", 1750);
			
			XWPFTableRow infoTableRowSeven=xTable.createRow();
			infoTableRowSeven.setHeight(400);
			setCellTextCenter(xdoc, xTable.getRow(6).getCell(0), "呼入", "FFFFFF", 800);
			setCellTextCenter(xdoc, xTable.getRow(6).getCell(1), "计费标准", "FFFFFF", 1750);
			String callinstandard=list.get(i).getInvalue()+"元/小時";
			setCellTextCenter(xdoc, xTable.getRow(6).getCell(2), callinstandard, "FFFFFF", 1750);
			setCellTextCenter(xdoc, xTable.getRow(6).getCell(3), "呼出", "FFFFFF", 800);
			setCellTextCenter(xdoc, xTable.getRow(6).getCell(4), "计费标准", "FFFFFF", 1750);
			String calloutstandard=list.get(i).getOutvalue()+"元/小時";
			setCellTextCenter(xdoc, xTable.getRow(6).getCell(5), calloutstandard, "FFFFFF", 1750);
			
			XWPFTableRow infoTableRowEight=xTable.createRow();
			infoTableRowEight.setHeight(400);
			setCellTextCenter(xdoc, xTable.getRow(7).getCell(0), "呼入", "FFFFFF", 800);
			setCellTextCenter(xdoc, xTable.getRow(7).getCell(1), "呼入运营费用合计", "FFFFFF", 1750);
			String totalin=list.get(i).getTotalin()+"元";
			setCellTextCenter(xdoc, xTable.getRow(7).getCell(2), totalin, "FFFFFF", 1750);
			setCellTextCenter(xdoc, xTable.getRow(7).getCell(3), "呼出", "FFFFFF", 800);
			setCellTextCenter(xdoc, xTable.getRow(7).getCell(4), "呼出运营费用合计", "FFFFFF", 1750);
			String totalout=list.get(i).getTotalout()+"元";
			setCellTextCenter(xdoc, xTable.getRow(7).getCell(5), totalout, "FFFFFF", 1750);
			
			XWPFTableRow infoTableRowNine=xTable.createRow();
			infoTableRowNine.setHeight(400);
			double totalmsg=list.get(i).getMsgvalue()*list.get(i).getMsgcount();
			String message="短信："+list.get(i).getMsgcount()+"条，单价："+list.get(i).getMsgvalue()+"元/条，费用："+totalmsg+"元";
			setCellTextCenter(xdoc, xTable.getRow(8).getCell(0), message, "FFFFFF", 8600);
			for (int j = 1; j <= col_total_count-1; j++) { // 创建虚拟列
				setCellTextLeft(xdoc, xTable.getRow(8).getCell(j),null, "FFFFFF", -1);
			}
			mergeCellsHorizontal(xTable, 0, 1, col_total_count-1);
			
			XWPFTableRow infoTableRowTen=xTable.createRow();
			infoTableRowTen.setHeight(400);
			double total=list.get(i).getTotalin()+list.get(i).getTotalout()+totalmsg;
			String yinshou="应收金额：合计人民币（大写）："+list.get(i).getTotalmoney()+"（小写）：￥"+total+"元";
			setCellTextCenter(xdoc, xTable.getRow(9).getCell(0), yinshou, "FFFFFF", 8600);
			for (int j = 1; j <= col_total_count-1; j++) { // 创建虚拟列
				setCellTextLeft(xdoc, xTable.getRow(9).getCell(j),null, "FFFFFF", -1);
			}
			mergeCellsHorizontal(xTable, 0, 1, col_total_count-1);
			
			XWPFTableRow infoTableRowElven=xTable.createRow();
			infoTableRowElven.setHeight(400);
			String shishou="实收金额：合计人民币（大写）："+list.get(i).getTotalmoney()+"（小写）：￥"+total+"元";
			setCellTextCenter(xdoc, xTable.getRow(10).getCell(0), shishou, "FFFFFF", 8600);
			for (int j = 1; j <= col_total_count-1; j++) { // 创建虚拟列
				setCellTextLeft(xdoc, xTable.getRow(10).getCell(j),null, "FFFFFF", -1);
			}
			mergeCellsHorizontal(xTable, 0, 1, col_total_count-1);
			
			XWPFTableRow infoTableRowTwelve=xTable.createRow();
			infoTableRowTwelve.setHeight(400);
			setCellTextLeft(xdoc, xTable.getRow(11).getCell(0), "备注:", "FFFFFF", 8600);
			for (int j = 1; j <= col_total_count-1; j++) { // 创建虚拟列
				setCellTextLeft(xdoc, xTable.getRow(11).getCell(j),null, "FFFFFF", -1);
			}
			mergeCellsHorizontal(xTable, 0, 1, col_total_count-1);
			
			XWPFTableRow infoTableRowThrity=xTable.createRow();
			infoTableRowThrity.setHeight(500);
			String charge="收款人:"+chargeman;
			setCellTextLeft(xdoc, xTable.getRow(12).getCell(0), charge, "FFFFFF", 2800);
			for (int j = 1; j <= 1; j++) { // 创建虚拟列
				setCellTextLeft(xdoc, xTable.getRow(12).getCell(j),null, "FFFFFF", -1);
			}
			mergeCellsHorizontal(xTable, 0, 1, 1);
			setCellTextLeft(xdoc, xTable.getRow(12).getCell(2), "收款单位：（盖章）:", "FFFFFF", 3000);
			for (int j = 3; j <= 3; j++) { // 创建虚拟列
				setCellTextLeft(xdoc, xTable.getRow(12).getCell(j),null, "FFFFFF", -1);
			}
			mergeCellsHorizontal(xTable, 0, 3, 3);
			setCellTextLeft(xdoc, xTable.getRow(12).getCell(4), "收款日期:", "FFFFFF", 2800);
			for (int j = 5; j <= 5; j++) { // 创建虚拟列
				setCellTextLeft(xdoc, xTable.getRow(12).getCell(j),null, "FFFFFF", -1);
			}
			mergeCellsHorizontal(xTable, 0, 5, 5);
			
			
			XWPFParagraph xp4 = xdoc.createParagraph();
			XWPFRun r4 = xp4.createRun();
			r4.setFontFamily("宋体");
			r4.setFontSize(9);
			r4.setText("一式四联，白色联为财务结算联，蓝色联为存根联，红色联为客户结算联，黄色联为客户对账联");
			r4.setBold(false);
			xp4.setAlignment(ParagraphAlignment.CENTER);
			mergeCellsVertically(xTable, 0, 4,7);
			mergeCellsVertically(xTable, 3, 4,7);
			try {  
				output.flush();  
				xdoc.write(output);  
				
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}
		if(output!=null){
			output.close();
			return true;
		}
		return false;
	}
	
	/**
	 * 设置列宽
	 * @param index
	 * @return
	 */
	private static int getCellWidth(int index) {
		int cwidth = 0;
		if (index == 0) {
			cwidth = 900;
		} else if (index == 1) {
			cwidth = 3600;
		} else if (index == 2) {
			cwidth = 1300;
		} else if (index == 3) {
			cwidth = 1400;
		} else if (index == 4) {
			cwidth = 1400;
		} else if (index == 7) {
			cwidth = 8600;
		} else if (index == 8) {
			cwidth = 4300;
		} else if (index == 9) {
			cwidth = 1500;
		}
		return cwidth;
	}
	/**
	 * @param tbRow
	 * @param xTable
	 * @param row
	 *            行数
	 * @param col
	 *            需要添加的列数
	 * @param fromCol
	 *            合并那几列 开始列
	 * @param endCol
	 *            结束列
	 */
	private static void formatAddCell(XWPFTableRow tbRow, XWPFTable xTable, int row, int col, int fromCol, int endCol) {
		for (int i = 0; i < col; i++) {
			XWPFTableCell cell = tbRow.addNewTableCell();
			CTTc cttc = cell.getCTTc();
			CTTcPr cellPr = cttc.addNewTcPr();
			cellPr.addNewTcW().setW(BigInteger.valueOf(-1));
		}
		mergeCellsHorizontal(xTable, row, fromCol, endCol);
	}
	/**
	 * 跨列合并 不支持wps 有跨列的行请手工创建
	 * @param table
	 * @param row
	 * @param fromCell
	 * @param toCell
	 */
	public static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
		for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
			XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
			if (cellIndex == fromCell) {
				// The first merged cell is set with RESTART merge value
				cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE
				cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
			}
		}
	}
	/**
	 * 跨行合并
	 * @param table
	 * @param col
	 * @param fromRow
	 * @param toRow
	 */
	public static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
		for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
			XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
			if (rowIndex == fromRow) {
				// The first merged cell is set with RESTART merge value
				cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE
				cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
			}
		}
	}
	
	/**
	 * 设置表头内容
	 * @param xDocument
	 * @param cell
	 * @param text
	 * @param bgcolor
	 * @param width
	 */
	private static void setCellTextLeft(XWPFDocument xDocument, XWPFTableCell cell, String text, String bgcolor,
			int width) {
		CTTc cttc = cell.getCTTc();
		CTTcPr cellPr = cttc.addNewTcPr();
		cellPr.addNewTcW().setW(BigInteger.valueOf(width));
		cell.setColor(bgcolor);
		cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		CTTcPr ctPr = cttc.addNewTcPr();
		ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
		cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.LEFT);
		cell.setText(text);
	}
	
	private static void setCellTextCenter(XWPFDocument xDocument, XWPFTableCell cell, String text, String bgcolor,
			int width) {
		CTTc cttc = cell.getCTTc();
		CTTcPr cellPr = cttc.addNewTcPr();
		cellPr.addNewTcW().setW(BigInteger.valueOf(width));
		cell.setColor(bgcolor);
		cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		CTTcPr ctPr = cttc.addNewTcPr();
		ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
		cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
		cell.setText(text);
	}
}
