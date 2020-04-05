package com.sinosoft.common.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: ExcelSheetParser
 * @Description: 解析Excel文件 工具类
 * @author zzq
 * @Version V1.0
 * @date 2013-12-3 下午11:46:21
 */
@Service("excelSheetParser")
public class ExcelSheetParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelSheetParser.class);
	
	private static SimpleDateFormat sdfYmd = new SimpleDateFormat("yyyy-MM-dd");
	
	/** 
	 * @fields EXPORT_SHEET_RECORD_NUMBER : 文件条数SHEET存入条数
	 */ 
	public static final Integer EXPORT_SHEET_RECORD_NUMBER = 65535;
	
	/**
	 * 解析Excel文件内容的入口方法
	 * @param String filePath : 文件路径
	 * @param int sheetNumber : sheet页码
	 * @param boolean isOnlyGetColumnName : 是否只获取Excel文件的第一行的数据（第一行为标题行）
	 * @return List<String[]> : 封装着Excel文件中的数据
	 * @throws Exception
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2012-6-25 下午05:58:19
	 */
	public List<String[]> mainParseExcel(String filePath, int sheetNumber, boolean isOnlyGetColumnName) throws Exception {
		FileInputStream fs = null;
		File file = null;
		BufferedInputStream bs = null;
		List<String[]> datas = null;
		try {
			file = new File(filePath); //定位文件
			if (!file.exists()) LOGGER.error("找不到文件！");
			fs = new FileInputStream(file); //读取文件流
			bs = new BufferedInputStream(fs); //将文件流读取到缓存流中
			datas = parseExcel(bs, sheetNumber, isOnlyGetColumnName, filePath);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (bs != null) bs.close();
			if (fs != null) fs.close();
		}
		return datas;
	}
	
	/**
	 * 解析Excel文件内容的主方法
	 * @param InputStream is : 文件流
	 * @param int sheetNumber : sheet页码
	 * @param boolean isOnlyGetColumnName : 是否只获取Excel文件的第一行的数据（第一行为标题行）
	 * @return List<String[]> : 封装着Excel文件中的内容
	 * @throws Exception
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2012-6-25 上午09:00:16
	 */	 
	private List<String[]> parseExcel(InputStream is, int sheetNumber, boolean isOnlyGetColumnName, String filePath) throws Exception {
		/**
		 * Java读取Excel文件：
		 * 1、读取row数目：
		 * sheet.getFirstRowNum(); sheet.getLastRowNum();
		 * sheet.getFirstRowNum()的值为0，从0开始计数，最后的行数为 sheet.getLastRowNum()
		 * 即总行数等于sheet.getLastRowNum()+1
		 * 
		 * 2、读取cell数目
		 * row.getFirstCellNum(); row.getLastCellNum();
		 * row.getFirstCellNum()的值为0，row.getLastCellNum()的值可理解为读到当前row的该列（整列）没有数据时，则返回该列的值（该值从0开始计数）
		 * 
		 * 3、Excel的cell如果没写过值，则读出显示为null；如果写过值后删除，则显示为""（空），是null时，不计入行数(row)或列数(row.getLastCellNum();) ""则计入
		 * 超出表格 行、列 范围的数据读取可能会抛错
		 * 
		 * 4、sheet数目
		 * workbook.getNumberOfSheets(); 物理数目，即正整数
		 * 读取时，按照物理排序 读取时sheet 从 0 开始计数
		 */
		Workbook book = null;
		try {
            book = new XSSFWorkbook(is);
        } catch (Exception ex) {
            book = new HSSFWorkbook(new FileInputStream(filePath));
        }
		
		
//		HSSFWorkbook workbook = new HSSFWorkbook(is);
		List<String[]> result = new ArrayList<String[]>();
		Sheet sheet = book.getSheetAt(sheetNumber);
		//一共有多少行
		int rowCount = 1;
		if (!isOnlyGetColumnName) {
			rowCount = sheet.getLastRowNum();
			if (rowCount < 1) return result;
		}
		
		//得到一个有多少个例
		int cellCount = sheet.getRow(0).getLastCellNum();
		String[] columnValue = null;
		//遍历所有的行，从第二行开始，第一行是模块注释
		for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (null != row) {
				//首先获得第一行（标题行）的值
				columnValue = new String[cellCount];
				boolean isHaveValidValue = false;
				for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
					Cell cell = row.getCell(cellIndex);
					//得到单元格的值
					Object cellStr = this.getCellString(cell, rowIndex, cellIndex);
					cellStr = cellStr == null ? "" : cellStr;
					if (!StringUtils.isBlank(String.valueOf(cellStr))) {
						isHaveValidValue = true;
					}
					columnValue[cellIndex] = cellStr.toString().trim().replace("\n","").replace("\r", "");
				}
				if (isHaveValidValue) {
					result.add(columnValue);
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取单元格中的值
	 * @param HSSFCell cell : 单元格对象
	 * @return Object result : 单元格中的值
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2012-6-25 上午09:55:00
	 */
	private Object getCellString(Cell cell, int rowIndex, int cellIndex) throws Exception {
		Object result = null;
		if (cell != null) {
			/**
			 * 通过getCellType()获取单元格数据的类型
			 * getCellType()返回值0,1,2,3,4,5，各个数字代表不同的数据类型
			 * 0：CELL_TYPE_NUMERIC ，数值
			 * 1：CELL_TYPE_STRING ，字符串
			 * 2：CELL_TYPE_FORMULA ，公式
			 * 3、CELL_TYPE_BLANK ，空值，非null
			 * 4、CELL_TYPE_BOOLEAN ，布尔
			 * 5、CELL_TYPE_ERROR ，错误
			 */
			int cellType = cell.getCellType();
			switch (cellType) {
			case Cell.CELL_TYPE_STRING :
				result = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC : 
				if (DateUtil.isCellDateFormatted(cell)) {//日期类型
					double d = cell.getNumericCellValue();
					Date date = DateUtil.getJavaDate(d);
					result = sdfYmd.format(date);
				} else {
					Double o = cell.getNumericCellValue();
					NumberFormat formatter = NumberFormat.getNumberInstance();
					formatter.setGroupingUsed(false);
					result = formatter.format(o);
				}
				break;
			case Cell.CELL_TYPE_FORMULA : 
				result = cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN : 
				result = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_ERROR : 
				result = null;
				break;
			case Cell.CELL_TYPE_BLANK : 
				result = "";
				break;
			default : 
				LOGGER.error("数据错误！" + rowIndex + " : " + cellIndex);break;
			}
		}
		return result;
	}
	
	/**
	 * 测试专用
	 * @param args
	 * @Create at 2012-6-25 上午10:43:41
	 */
	public static void main(String[] args) throws Exception {
		FileInputStream fs = null;
		File file = null;
		BufferedInputStream bs = null;
		List<String[]> datas = null;
		try {
			file = new File("D:\\test.xls"); //定位文件
			if (!file.exists()) LOGGER.error("找不到文件！");
			ExcelSheetParser parser = new ExcelSheetParser();
			fs = new FileInputStream(file); //读取文件流
			bs = new BufferedInputStream(fs); //将文件流读取到缓存流中
			datas = parser.parseExcel(bs, 0, false, "");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (bs != null) bs.close();
			if (fs != null) fs.close();
		}
		if (datas != null) {
			LOGGER.info("一共有" + (datas.size()) + "条记录");
			Iterator<String[]> iterList = datas.listIterator();
			while (iterList.hasNext()) {
				String[] rowValue = iterList.next();
				for (int i = 0; i < rowValue.length; i++) {
					LOGGER.info("获取到的单元格的值为：" + rowValue[i]);
				}
			}
		}
	}
}
