package com.zeng.ocs.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 导出excel的工具类
 * 全部采用string类型，如果需要加入其它类型需要修改工具方法  
 */
public class ExcelUtil<T> {  
	Class<T> clazz;  
	public ExcelUtil(Class<T> clazz) {  
		this.clazz = clazz;  
	}
	/**
	 * 批量导入
	 * 这里的批量导入指的是一个excel文件中有多个相同类型的sheet页
	 */
	public List<T> importBatch(InputStream input) throws Exception {
		List<T> newList = new ArrayList<T>();
		HSSFWorkbook workbook=new HSSFWorkbook(input);
		if(null != workbook){
			int sheets = workbook.getNumberOfSheets();
			if(sheets>0){
				for(int i=0;i<sheets;i++){
					HSSFSheet sheet = workbook.getSheetAt(i);
					if(null != sheet){
						List<T> importProcessor = importProcessor(sheet);
						newList.addAll(importProcessor);
					}
				}
			}
		}
		return newList;
	}
	/**
	 * 指定sheet页导入，如果不指定默认会选第一个sheet
	 */
	public List<T> importExcel(String sheetName, InputStream input) throws Exception {  
		HSSFWorkbook workbook = new HSSFWorkbook(input);  
		HSSFSheet sheet = workbook.getSheet(sheetName);  
		if (!sheetName.trim().equals("")) {  
			sheet = workbook.getSheet(sheetName);// 如果指定sheet名,则取指定sheet中的内容.  
		}  
		if (sheet == null) {  
			sheet = workbook.getSheetAt(0); // 如果传入的sheet名不存在则默认指向第1个sheet.  
		}  
		return importProcessor(sheet);
	}  
	/**
	 * 具体处理导入
	 */
	private List<T> importProcessor(HSSFSheet sheet) throws Exception {  
		int maxCol = 0;  
		List<T> list = new ArrayList<T>();  
		int rows = sheet.getPhysicalNumberOfRows();  
		if (rows > 0) {// 有数据时才处理  
			// Field[] allFields = clazz.getDeclaredFields();// 得到类的所有field.  
			List<Field> allFields = getMappedFiled(clazz, null);  
			Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();// 定义一个map用于存放列的序号和field.  
			for (Field field : allFields) {  
				// 将有注解的field存放到map中.  
				if (field.isAnnotationPresent(ExcelField.class)) {  
					ExcelField attr = field  
							.getAnnotation(ExcelField.class);  
					int col = getExcelCol(attr.column());// 获得列号  
					maxCol = Math.max(col, maxCol);  
					// System.out.println(col + "====" + field.getName());  
					field.setAccessible(true);// 设置类的私有字段属性可访问.  
					fieldsMap.put(col, field);  
				}  
			}  
			for (int i = 1; i < rows; i++) {// 从第2行开始取数据,默认第一行是表头.  
				HSSFRow row = sheet.getRow(i);  
				// int cellNum = row.getPhysicalNumberOfCells();  
				// int cellNum = row.getLastCellNum();  
				int cellNum = maxCol;  
				T entity = null;  
				for (int j = 0; j <= cellNum; j++) { 
					HSSFCell cell = row.getCell(j);  
					if (cell == null) {  
						continue;  
					}  
					int cellType = cell.getCellType();  
					String c = "";  
					if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {  
						c = String.valueOf(cell.getNumericCellValue());  
					} else if (cellType == HSSFCell.CELL_TYPE_BOOLEAN) {  
						c = String.valueOf(cell.getBooleanCellValue());  
					} else {  
						c = cell.getStringCellValue();  
					}  
					if (c == null || c.equals("")) {  
						continue;  
					}  
					entity = (entity == null ? clazz.newInstance() : entity);// 如果不存在实例则新建.  
					// System.out.println(cells[j].getContents());  
					Field field = fieldsMap.get(j);// 从map中得到对应列的field.  
					if (field==null) {  
						continue;  
					}  
					// 取得类型,并根据对象类型设置值.  
					Class<?> fieldType = field.getType();  
					if (String.class == fieldType) {  
						field.set(entity, String.valueOf(c));  
					} else if ((Integer.TYPE == fieldType)  
							|| (Integer.class == fieldType)) {  
						field.set(entity, Integer.parseInt(c));  
					} else if ((Long.TYPE == fieldType)  
							|| (Long.class == fieldType)) {  
						field.set(entity, Long.valueOf(c));  
					} else if ((Float.TYPE == fieldType)  
							|| (Float.class == fieldType)) {  
						field.set(entity, Float.valueOf(c));  
					} else if ((Short.TYPE == fieldType)  
							|| (Short.class == fieldType)) {  
						field.set(entity, Short.valueOf(c));  
					} else if ((Double.TYPE == fieldType)  
							|| (Double.class == fieldType)) {  
						field.set(entity, Double.valueOf(c));  
					} else if (Character.TYPE == fieldType) {  
						if ((c != null) && (c.length() > 0)) {  
							field.set(entity, Character  
									.valueOf(c.charAt(0)));  
						}  
					}  
				}  
				if (entity != null) {  
					list.add(entity);  
				}  
			}  
		}  
		return list;  
	}  
	/** 
	 * 对list数据源将其里面的数据导出到excel表单 
	 */  
	public boolean exportExcel(List<T> lists[], String sheetNames[], OutputStream output) {  
		if (lists.length != sheetNames.length) {  
			return false;  
		}  
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();// 产生工作薄对象  
		//XSSFSheet sheet = workbook.createSheet("sheet1");
		for (int ii = 0; ii < lists.length; ii++) {  
			List<T> list = lists[ii];  
			String sheetName = sheetNames[ii];  
			List<Field> fields = getMappedFiled(clazz, null);  
			XSSFSheet sheet = workbook.createSheet();// 产生工作表对象  
			workbook.setSheetName(ii, sheetName);
			XSSFRow row;  
			XSSFCell cell;// 产生单元格  
			XSSFCellStyle style = workbook.createCellStyle();  
			style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
			style.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);
			row = sheet.createRow(0);// 产生一行  
			
			for (int i = 0; i < fields.size(); i++) {  
				Field field = fields.get(i);  
				ExcelField attr = field.getAnnotation(ExcelField.class);  
				int col = getExcelCol(attr.column());// 获得列号  
				cell = row.createCell(col);// 创建列  
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列中写入内容为String类型  
				cell.setCellValue(attr.name());// 写入列名  
				//cell.getSheet().setColumnWidth(i, attr.width());
				
				//cell.getSheet().autoSizeColumn(i);
				if (!attr.prompt().trim().equals("")) {  
					setXSSFPrompt(sheet, "", attr.prompt(), 1, 100, col, col);// 这里默认设了2-101列提示.  
				}  
				if (attr.combo().length > 0) {  
					setXSSFValidation(sheet, attr.combo(), 1, 100, col, col);// 这里默认设了2-101列只能选择不能输入.  
				}  
				cell.setCellStyle(style);  
			}  
			int startNo = 0;  
			int endNo = list.size();  
			for (int i = startNo; i < endNo; i++) {  
				row = sheet.createRow(i + 1 - startNo);  
				T vo = (T) list.get(i);
				for (int j = 0; j < fields.size(); j++) {  
					Field field = fields.get(j);
					field.setAccessible(true);
					ExcelField attr = field.getAnnotation(ExcelField.class);  
					try {  
						if (attr.isExport()) {  
							cell = row.createCell(getExcelCol(attr.column()));
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
							cell.setCellValue(field.get(vo) == null ? ""  
									: String.valueOf(field.get(vo))); 
						}  
					} catch (IllegalArgumentException e) {  
						e.printStackTrace();  
					} catch (IllegalAccessException e) {  
						e.printStackTrace();  
					}  
				}  
			}  
		}  
		try {  
			output.flush();  
			workbook.write(output);  
			output.close();  
			return true;  
		} catch (IOException e) {  
			e.printStackTrace();  
			return false;  
		}  
	}  
	/** 
	 * 对list数据源将其里面的数据导出到excel表单
	 */  
	@SuppressWarnings("unchecked")
	public boolean exportExcel(List<T> list, String sheetName,  
			OutputStream output) {
		//此处 对类型进行转换
		List<T> ilist = new ArrayList<>();
		for (T t : list) {
			ilist.add(t);
		}
		List<T>[] lists = new ArrayList[1];  
		lists[0] = ilist;  
		String[] sheetNames = new String[1];  
		sheetNames[0] = sheetName;  
		return exportExcel(lists, sheetNames, output);  
	}  
	/** 
	 * 将EXCEL中A,B,C,D,E列映射成0,1,2,3,4 
	 * @param col 
	 */  
	public static int getExcelCol(String col) {  
		col = col.toUpperCase();  
		// 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。  
		int count = -1;  
		char[] cs = col.toCharArray();  
		for (int i = 0; i < cs.length; i++) {  
			count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);  
		}  
		return count;  
	}  
	/** 
	 * 设置单元格上提示 
	 */  
	public static XSSFSheet setXSSFPrompt(XSSFSheet sheet, String promptTitle,  
			String promptContent, int firstRow, int endRow, int firstCol,  
			int endCol) {
		//TODO change
		DVConstraint constraint = DVConstraint  
				.createCustomFormulaConstraint("DD1");  
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,  
				endRow, firstCol, endCol);  
		HSSFDataValidation data_validation_view = new HSSFDataValidation(  
				regions, constraint);  
		data_validation_view.createPromptBox(promptTitle, promptContent);  
		sheet.addValidationData(data_validation_view);  
		return sheet;  
	}  
	/** 
	 * 设置某些列的值只能输入预制的数据,显示下拉框. 
	 */  
	public static XSSFSheet setXSSFValidation(XSSFSheet sheet,  
			String[] textlist, int firstRow, int endRow, int firstCol,  
			int endCol) {  
		DVConstraint constraint = DVConstraint  
				.createExplicitListConstraint(textlist);  
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,  
				endRow, firstCol, endCol);  
		HSSFDataValidation data_validation_list = new HSSFDataValidation(  
				regions, constraint);  
		sheet.addValidationData(data_validation_list);  
		return sheet;  
	}  
	/** 
	 * 得到实体类所有通过注解映射了数据表的字段 
	 *  递归调用
	 */  
	private List<Field> getMappedFiled(Class clazz, List<Field> fields) {  
		if (fields == null) {  
			fields = new ArrayList<Field>();  
		}  
		Field[] allFields = clazz.getDeclaredFields();// 得到所有定义字段  
		for (Field field : allFields) {  
			if (field.isAnnotationPresent(ExcelField.class)) {  
				fields.add(field);  
			}  
		}  
		if (clazz.getSuperclass() != null  
				&& !clazz.getSuperclass().equals(Object.class)) {  
			getMappedFiled(clazz.getSuperclass(), fields);  
		}  
		return fields;  
	}  
}