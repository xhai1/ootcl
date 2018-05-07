package com.zeng.ocs.util;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.zeng.ocs.po.CusReportVo;
/**
 * 导出office的word
 * @author cxs
 *
 */
public class OfficeUtil {
	public static boolean exportOffice(String comacount, String compopacbank, String chargeman, List<CusReportVo> list, OutputStream output) throws IOException{
		
		for(int i=0;i<list.size();i++){
			XWPFDocument document= new XWPFDocument();  
	        //添加标题  
	        XWPFParagraph titleParagraph = document.createParagraph();  
	        //设置段落居中  
	        titleParagraph.setAlignment(ParagraphAlignment.CENTER);  
	        
	        
	        XWPFRun titleParagraphRun = titleParagraph.createRun();  
	        titleParagraphRun.setText("TCL统一服务热线计费系统");  
	        titleParagraphRun.setColor("000000");  
	        titleParagraphRun.setFontSize(16);  
	  
	        
	        XWPFParagraph xp2 = document.createParagraph();
			XWPFRun r2 = xp2.createRun();
			r2.setFontFamily("宋体");
			r2.setFontSize(12);
			r2.setText("运营服务计费总表");
			r2.setBold(false);
			xp2.setAlignment(ParagraphAlignment.CENTER);
			
			XWPFParagraph xp3 = document.createParagraph();
			XWPFRun r3 = xp3.createRun();
			r3.setFontFamily("宋体");
			r3.setFontSize(12);
			//TODO NO
			String month="NO."+list.get(i).getMonth().replace("-", "")+"01";
			r3.setText(month);
			r3.setBold(false);
			xp3.setAlignment(ParagraphAlignment.RIGHT);
			
	        //换行  
	        XWPFParagraph paragraph1 = document.createParagraph();  
	        XWPFRun paragraphRun1 = paragraph1.createRun();  
	        paragraphRun1.setText("\r");  
	  
	        //基本信息表格  
	        XWPFTable infoTable = document.createTable();  
	        //去表格边框  
	        //infoTable.getCTTbl().getTblPr().unsetTblBorders();  
	  
	        //列宽自动分割  
	        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();  
	        infoTableWidth.setType(STTblWidth.DXA);  
	        infoTableWidth.setW(BigInteger.valueOf(9072));  
	  
	  
	        //表格第一行  
	        XWPFTableRow infoTableRowOne = infoTable.getRow(0); 
	        //TODO 客户名服务号码
	        infoTableRowOne.setHeight(400);
	        String clientname="客戶姓名："+list.get(i).getCusname();
	        infoTableRowOne.getCell(0).setText(clientname);  
	        
	        infoTableRowOne.addNewTableCell().setText(null);
	        infoTableRowOne.addNewTableCell().setText(null);
	        infoTableRowOne.addNewTableCell().setText("服务号码："); 
	        infoTableRowOne.addNewTableCell().setText(null);
	        infoTableRowOne.addNewTableCell().setText(null);
	  
	        //表格第二行  
	        XWPFTableRow infoTableRowTwo = infoTable.createRow();
	        infoTableRowTwo.setHeight(400);
	        infoTableRowTwo.getCell(0).setText("客户地址：");  
	        infoTableRowTwo.getCell(1).setText(null); 
	        infoTableRowTwo.getCell(2).setText(null); 
	        String chargetime="计费时段："+list.get(i).getMonth().replace("-", "年")+"月";
	        infoTableRowTwo.getCell(3).setText(chargetime);
	        infoTableRowTwo.getCell(4).setText(null);
	        infoTableRowTwo.getCell(5).setText(null);
	  
	        //表格第三行  
	        XWPFTableRow infoTableRowThree = infoTable.createRow();
	        infoTableRowThree.setHeight(400);
	        String bank="开户银行:"+compopacbank;
	        infoTableRowThree.getCell(0).setText(bank);  
	        infoTableRowThree.getCell(1).setText(null);
	        infoTableRowThree.getCell(2).setText(null); 
	        String account="开户账户:"+comacount;
	        infoTableRowThree.getCell(3).setText(account);
	        infoTableRowThree.getCell(4).setText(null);
	        infoTableRowThree.getCell(5).setText(null);
	  
	        //表格第一行  
	        XWPFTableRow infoTableRowfour = infoTable.createRow();
	        infoTableRowfour.setHeight(600);
	        infoTableRowfour.getCell(0).setText("收费项目");  
	        infoTableRowfour.getCell(1).setText("项目明细");  
	        infoTableRowfour.getCell(2).setText("数量");  
	        infoTableRowfour.getCell(3).setText("收费项目");
	        infoTableRowfour.getCell(4).setText("项目明细");
	        infoTableRowfour.getCell(5).setText("数量");  
	  
	        //表格第二行  
	        XWPFTableRow infoTableRowFive = infoTable.createRow();
	        infoTableRowFive.setHeight(400);
	        infoTableRowFive.getCell(0).setText("呼入");  
	        infoTableRowFive.getCell(1).setText("通话次数");
	        String callin=list.get(i).getIncount()+"次";
	        infoTableRowFive.getCell(2).setText(callin);  
	        infoTableRowFive.getCell(3).setText("呼出");  
	        infoTableRowFive.getCell(4).setText("通话次数");
	        String callout=list.get(i).getOutcount()+"次";
	        infoTableRowFive.getCell(5).setText(callout);
	        
	        //表格第三行  
	        XWPFTableRow infoTableRowSix = infoTable.createRow();
	        infoTableRowSix.setHeight(400);
	        infoTableRowSix.getCell(0).setText("呼入");  
	        infoTableRowSix.getCell(1).setText("呼入话务时长");
	        String callintime=list.get(i).getInhour()+"小时";
	        infoTableRowSix.getCell(2).setText(callintime);  
	        infoTableRowSix.getCell(3).setText("呼出");  
	        infoTableRowSix.getCell(4).setText("呼出话务时长");
	        String callouttime=list.get(i).getOuthour()+"小时";
	        infoTableRowSix.getCell(5).setText(callouttime);  
	  
	        //表格第四行  
	        XWPFTableRow infoTableRowSeven = infoTable.createRow();
	        infoTableRowSeven.setHeight(400);
	        infoTableRowSeven.getCell(0).setText("呼入");  
	        infoTableRowSeven.getCell(1).setText("计费标准");
	        String callinstandard=list.get(i).getInvalue()+"元/小時";
	        infoTableRowSeven.getCell(2).setText(callinstandard);  
	        infoTableRowSeven.getCell(3).setText("呼出");  
	        infoTableRowSeven.getCell(4).setText("计费标准");
	        String calloutstandard=list.get(i).getOutvalue()+"元/小時";
	        infoTableRowSeven.getCell(5).setText(calloutstandard); 
	  
	        //表格第五行  
	        XWPFTableRow infoTableRowEight = infoTable.createRow();
	        infoTableRowEight.setHeight(400);
	        infoTableRowEight.getCell(0).setText("呼入");  
	        infoTableRowEight.getCell(1).setText("呼入运营费用合计");
	        String totalin=list.get(i).getTotalin()+"元";
	        infoTableRowEight.getCell(2).setText(totalin);  
	        infoTableRowEight.getCell(3).setText("呼出");  
	        infoTableRowEight.getCell(4).setText("呼出运营费用合计");
	        String totalout=list.get(i).getTotalout()+"元";
	        infoTableRowEight.getCell(5).setText(totalout);   
	  
	        //表格第六行  
	        XWPFTableRow infoTableRowNine = infoTable.createRow();
	        infoTableRowNine.setHeight(500);
	        double totalmsg=list.get(i).getMsgvalue()*list.get(i).getMsgcount();
			String message="短信："+list.get(i).getMsgcount()+"条，单价："+list.get(i).getMsgvalue()+"元/条，费用："+totalmsg+"元";
	        infoTableRowNine.getCell(0).setText(message);
	        infoTableRowNine.getCell(1).setText(null);
	        infoTableRowNine.getCell(2).setText(null);  
	        infoTableRowNine.getCell(3).setText(null);
	        infoTableRowNine.getCell(4).setText(null);
	        infoTableRowNine.getCell(5).setText(null);
	        
	        
	        //表格第七行  
	        XWPFTableRow infoTableRowTen = infoTable.createRow();
	        infoTableRowTen.setHeight(500);
	        double total=list.get(i).getTotalin()+list.get(i).getTotalout()+totalmsg;
			String yinshou="应收金额：合计人民币（大写）："+list.get(i).getTotalmoney()+"（小写）：￥"+total+"元";
	        infoTableRowTen.getCell(0).setText(yinshou); 
	        infoTableRowTen.getCell(1).setText(null);
	        infoTableRowTen.getCell(2).setText(null);  
	        infoTableRowTen.getCell(3).setText(null);
	        infoTableRowTen.getCell(4).setText(null);
	        infoTableRowTen.getCell(5).setText(null);
	        
	  
	        //表格第八行  
	        XWPFTableRow infoTableRowEleven = infoTable.createRow();
	        infoTableRowEleven.setHeight(500);
	        String shishou="实收金额：合计人民币（大写）："+list.get(i).getTotalmoney()+"（小写）：￥"+total+"元";
	        infoTableRowEleven.getCell(0).setText(shishou);  
	        infoTableRowEleven.getCell(1).setText(null);
	        infoTableRowEleven.getCell(2).setText(null);  
	        infoTableRowEleven.getCell(3).setText(null);
	        infoTableRowEleven.getCell(4).setText(null);
	        infoTableRowEleven.getCell(5).setText(null);
	        
	        //表格第九行  
	        XWPFTableRow infoTableRowTwelve = infoTable.createRow();
	        infoTableRowTwelve.setHeight(500);
	        infoTableRowTwelve.getCell(0).setText("备注");  
	        infoTableRowTwelve.getCell(1).setText(null);
	        infoTableRowTwelve.getCell(2).setText(null);  
	        infoTableRowTwelve.getCell(3).setText(null);
	        infoTableRowTwelve.getCell(4).setText(null);
	        infoTableRowTwelve.getCell(5).setText(null);
	        
	        XWPFTableRow infoTableRowThrity = infoTable.createRow();
	        infoTableRowThrity.setHeight(500);
	        String charge="收款人:"+chargeman;
	        infoTableRowThrity.getCell(0).setText(charge);  
	        infoTableRowThrity.getCell(1).setText(null);
	        infoTableRowThrity.getCell(2).setText("收款单位：（盖章）");  
	        infoTableRowThrity.getCell(3).setText(null);
	        infoTableRowThrity.getCell(4).setText("收款日期：");
	        infoTableRowThrity.getCell(5).setText(null);
	        
	        
	        XWPFParagraph xp4 = document.createParagraph();
			XWPFRun r4 = xp4.createRun();
			r4.setFontFamily("宋体");
			r4.setFontSize(10);
			r4.setText("一式四联，白色联为财务结算联，蓝色联为存根联，红色联为客户结算联，黄色联为客户对账联");
			r4.setBold(false);
			xp4.setAlignment(ParagraphAlignment.CENTER);
	        
	        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();  
	        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);  
	  
	        //添加页眉  
	        CTP ctpHeader = CTP.Factory.newInstance();  
	        CTR ctrHeader = ctpHeader.addNewR();  
	        CTText ctHeader = ctrHeader.addNewT();  
	        String headerText = "Java POI create MS word file.";  
	        ctHeader.setStringValue(headerText);  
	        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);  
	        //设置为右对齐  
	        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);  
	        XWPFParagraph[] parsHeader = new XWPFParagraph[1];  
	        parsHeader[0] = headerParagraph;  
	        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);  
	  
	  
	        //添加页脚  
	        CTP ctpFooter = CTP.Factory.newInstance();  
	        CTR ctrFooter = ctpFooter.addNewR();  
	        CTText ctFooter = ctrFooter.addNewT();  
	        String footerText = "http://blog.csdn.net/zhouseawater";  
	        ctFooter.setStringValue(footerText);  
	        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);  
	        headerParagraph.setAlignment(ParagraphAlignment.CENTER);  
	        XWPFParagraph[] parsFooter = new XWPFParagraph[1];  
	        parsFooter[0] = footerParagraph;  
	        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);  
	        
	        mergeCellsHorizontal(infoTable,0,0,2);
	        mergeCellsHorizontal(infoTable,1,0,2);
	        mergeCellsHorizontal(infoTable,2,0,2);
	        mergeCellsHorizontal(infoTable,0,3,5);
	        mergeCellsHorizontal(infoTable,1,3,5);
	        mergeCellsHorizontal(infoTable,2,3,5);
	        mergeCellsHorizontal(infoTable,8,0,5);
	        mergeCellsHorizontal(infoTable,9,0,5);
	        mergeCellsHorizontal(infoTable,10,0,5);
	        mergeCellsHorizontal(infoTable,11,0,5);
	        mergeCellsHorizontal(infoTable,12,0,1);
	        mergeCellsHorizontal(infoTable,12,4,5);
	        mergeCellsHorizontal(infoTable,12,2,3);
	        mergeCellsVertically(infoTable,0,4,7);
	        mergeCellsVertically(infoTable,3,4,7);
	  
	        try {  
				output.flush();  
				document.write(output);  
				
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
     * @Description: 跨列合并 
     */  
    public static  void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {  
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {  
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);  
            if ( cellIndex == fromCell ) {  
                // The first merged cell is set with RESTART merge value  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);  
            } else {  
                // Cells which join (merge) the first one, are set with CONTINUE  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);  
            }  
        }  
    }  
      
    /** 
     * @Description: 跨行合并 
     * @see http://stackoverflow.com/questions/24907541/row-span-with-xwpftable 
     */  
    public static  void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {  
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {  
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);  
            if ( rowIndex == fromRow ) {  
                // The first merged cell is set with RESTART merge value  
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);  
            } else {  
                // Cells which join (merge) the first one, are set with CONTINUE  
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);  
            }  
        }  
    }
}
