package com.sinosoft.common.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sinosoft.business.po.ExportExcelAll;
import com.sinosoft.common.constant.MappingConstantConfig;
import com.sinosoft.common.util.ActivityStringUtils;
import com.sinosoft.common.util.DateTimeUtils;

@Service("exportExcelService")
public class ExportExcelService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExportExcelService.class);
	
	private static final Integer EXPORT_SHEET_RECORD_NUMBER = 65535;
	
	/**
	 * 通过提供excel表头模板导出excel
	 * @param List<LinkedHashMap<String, String>> list : 集合中封装了需要导出的数据信息
	 * @param String modelFileName : 表头模板文件名称
	 * @param String fileName : 导出excel开始的行数
	 * @return  fileName 导出后的文件名称
	 * @throws Exception
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2012-4-14 下午10:07:44
	 */
	public void buildExcelByModel(List<String[]> list, String modelFileName, int maxRowNum, String fileName) throws Exception {
		WritableCellFormat TITLE_CELL_FONT = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
		
		WritableCellFormat DATA_CELL_FONT = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
		
		Map<String, String> columnWidthMap = new HashMap<String, String>();
		//垂直居中
		TITLE_CELL_FONT.setVerticalAlignment(VerticalAlignment.CENTRE);
		//水平居中
		TITLE_CELL_FONT.setAlignment(Alignment.CENTRE);
		//细边框
		TITLE_CELL_FONT.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		//垂直居中
		DATA_CELL_FONT.setVerticalAlignment(VerticalAlignment.CENTRE);
		//细边框
		DATA_CELL_FONT.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		
		fileName = MappingConstantConfig.getValue("APP_BASE_PATH") + MappingConstantConfig.getValue("EXPORT_PATH") + fileName;
		LOGGER.debug("导出的文件绝对路径为：" + fileName);
		
		String modelFile =  MappingConstantConfig.getValue("APP_BASE_PATH") + MappingConstantConfig.getValue("TEMPLATE_PATH") + modelFileName;
		File file = new File(modelFile);
		WritableWorkbook book = Workbook.createWorkbook(new File(fileName), Workbook.getWorkbook(file));

		Integer pageSheet = (list.size() / (EXPORT_SHEET_RECORD_NUMBER - 1)) + 1; //excel文件中sheet分页的数量
		WritableSheet sheet = null;
		
		for (int i = 0; i < pageSheet; i++) {
			sheet = book.getSheet(0);
			int beginIndex = i * EXPORT_SHEET_RECORD_NUMBER;
			int endIndex = (i+1) * EXPORT_SHEET_RECORD_NUMBER - 1;
			
			pageShell(sheet, list, beginIndex, endIndex, maxRowNum, DATA_CELL_FONT); //开始往每个sheet页中写入内容
			for (int j = 0; j <= maxRowNum; j++) { 
				sheet.setRowView(j, 500, false); //设置行高
			}
			for (Map.Entry<String, String> entry : columnWidthMap.entrySet()) {
				sheet.setColumnView(Integer.parseInt(entry.getKey()), Integer.parseInt(entry.getValue()));
			}
		}
		book.write();
		book.close();
	}
	
	/**
	 * 导出Excel主方法
	 * @param List<LinkedHashMap<String, String>> list : 集合中封装了需要导出的数据信息
	 * @param String[] excelFieldTitle : 字段名称数组
	 * @param String fileName : 文件名称
	 * @return  fileName 导出后的文件名称
	 * @throws Exception
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2012-4-14 下午10:07:44
	 */
	public void buildExcel(List<String[]> list, List<String[]> excelFieldTitleList, 
			String fileName) throws Exception {
		WritableCellFormat TITLE_CELL_FONT = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
		
		WritableCellFormat DATA_CELL_FONT = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
		
		Map<String, String> columnWidthMap = new HashMap<String, String>();
		//垂直居中
		TITLE_CELL_FONT.setVerticalAlignment(VerticalAlignment.CENTRE);
		//水平居中
		TITLE_CELL_FONT.setAlignment(Alignment.CENTRE);
		//细边框
		TITLE_CELL_FONT.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		//垂直居中
		DATA_CELL_FONT.setVerticalAlignment(VerticalAlignment.CENTRE);
		//细边框
		DATA_CELL_FONT.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		
		fileName = MappingConstantConfig.getValue("APP_BASE_PATH") + MappingConstantConfig.getValue("EXPORT_PATH") + fileName;
		LOGGER.debug("导出的文件绝对路径为：" + fileName);
		WritableWorkbook book = Workbook.createWorkbook(new File(fileName));// 创建excel文件
		Integer pageSheet = (list.size() / (EXPORT_SHEET_RECORD_NUMBER - 1)) + 1; //excel文件中sheet分页的数量
		WritableSheet sheet = null;
		Label label = null;
		int maxRowNum = 0;
		
		for (int i = 0; i < pageSheet; i++) {
			sheet = book.createSheet("导出记录" + (i+1), i);//第一个参数为sheet页的名称，第二个参数为excel的sheet的索引
			sheet.getSettings().setDefaultColumnWidth(11);
			
			for (int m = 0; m < excelFieldTitleList.size(); m++) {
				//第一个为纵坐标，第二个为横坐标，第三个为文本信息，这里是在构造标题
				label = new Label(Integer.parseInt(excelFieldTitleList.get(m)[1]), 
						Integer.parseInt(excelFieldTitleList.get(m)[2]), excelFieldTitleList.get(m)[0], 
						TITLE_CELL_FONT);
				sheet.addCell(label);
				if (excelFieldTitleList.get(m)[5] != null && !excelFieldTitleList.get(m)[5].equals("")) {
					columnWidthMap.put(excelFieldTitleList.get(m)[1], excelFieldTitleList.get(m)[5]);
				}
				sheet.mergeCells(Integer.parseInt(excelFieldTitleList.get(m)[1]), Integer.parseInt(excelFieldTitleList.get(m)[2]), 
						Integer.parseInt(excelFieldTitleList.get(m)[3]), Integer.parseInt(excelFieldTitleList.get(m)[4]));
				if (Integer.parseInt(excelFieldTitleList.get(m)[4]) > maxRowNum) {
					maxRowNum = Integer.parseInt(excelFieldTitleList.get(m)[4]);
				}
			}
			int beginIndex = i * EXPORT_SHEET_RECORD_NUMBER;
			int endIndex = (i+1) * EXPORT_SHEET_RECORD_NUMBER - 1;
			pageShell(sheet, list, beginIndex, endIndex, maxRowNum, DATA_CELL_FONT); //开始往每个sheet页中写入内容
			for (int j = 0; j <= maxRowNum; j++) {
				sheet.setRowView(j, 500, false); //设置行高
			}
			for (Map.Entry<String, String> entry : columnWidthMap.entrySet()) {
				sheet.setColumnView(Integer.parseInt(entry.getKey()), Integer.parseInt(entry.getValue()));
			}
		}
		book.write();
		book.close();
	}
	
	
	/**
	 * 导出Excel主方法  一键导出
	 * @param List<LinkedHashMap<String, String>> list : 集合中封装了需要导出的数据信息
	 * @param String[] excelFieldTitle : 字段名称数组
	 * @param String fileName : 文件名称
	 * @return  fileName 导出后的文件名称
	 * @throws Exception
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2013-3-19 下午10:07:44
	 */
	public void buildExcelAll(List<ExportExcelAll> exportAllList) throws Exception {
		
		//List<String[]> list, List<String[]> excelFieldTitleList, 
		//String fileName
		String fileName = "Statistic_" + DateTimeUtils.getDateTime(DateTimeUtils.YMDHMS_STR) + ".xls";
		fileName = MappingConstantConfig.getValue("APP_BASE_PATH") + MappingConstantConfig.getValue("EXPORT_PATH") + fileName;
		LOGGER.debug("导出的文件绝对路径为：" + fileName);
		WritableWorkbook book = Workbook.createWorkbook(new File(fileName));// 创建excel文件
		int sheetNum = 0;
		for(int n=0;n<exportAllList.size();n++){
			List<String[]> list = exportAllList.get(n).getStatisticList();
			List<String[]> excelFieldTitleList = exportAllList.get(n).getExcelFieldTitleList();
			if(list!=null&&list.size()>0){
				WritableCellFormat TITLE_CELL_FONT = new WritableCellFormat(
						new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
				
				WritableCellFormat DATA_CELL_FONT = new WritableCellFormat(
						new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
				
				Map<String, String> columnWidthMap = new HashMap<String, String>();
				//垂直居中
				TITLE_CELL_FONT.setVerticalAlignment(VerticalAlignment.CENTRE);
				//水平居中
				TITLE_CELL_FONT.setAlignment(Alignment.CENTRE);
				//细边框
				TITLE_CELL_FONT.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				//垂直居中
				DATA_CELL_FONT.setVerticalAlignment(VerticalAlignment.CENTRE);
				//细边框
				DATA_CELL_FONT.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				
				
				Integer pageSheet = (list.size() / (EXPORT_SHEET_RECORD_NUMBER - 1)) + 1; //excel文件中sheet分页的数量
				WritableSheet sheet = null;
				Label label = null;
				int maxRowNum = 0;
				
				for (int i = 0; i < pageSheet; i++) {
					
					sheet = book.createSheet(exportAllList.get(n).getName()+"("+ (sheetNum+1)+")", sheetNum);//第一个参数为sheet页的名称，第二个参数为excel的sheet的索引
					sheetNum++;
					sheet.getSettings().setDefaultColumnWidth(11);
					
					for (int m = 0; m < excelFieldTitleList.size(); m++) {
						//第一个为纵坐标，第二个为横坐标，第三个为文本信息，这里是在构造标题
						label = new Label(Integer.parseInt(excelFieldTitleList.get(m)[1]), 
								Integer.parseInt(excelFieldTitleList.get(m)[2]), excelFieldTitleList.get(m)[0], 
								TITLE_CELL_FONT);
						sheet.addCell(label);
						if (excelFieldTitleList.get(m)[5] != null && !excelFieldTitleList.get(m)[5].equals("")) {
							columnWidthMap.put(excelFieldTitleList.get(m)[1], excelFieldTitleList.get(m)[5]);
						}
						sheet.mergeCells(Integer.parseInt(excelFieldTitleList.get(m)[1]), Integer.parseInt(excelFieldTitleList.get(m)[2]), 
								Integer.parseInt(excelFieldTitleList.get(m)[3]), Integer.parseInt(excelFieldTitleList.get(m)[4]));
						if (Integer.parseInt(excelFieldTitleList.get(m)[4]) > maxRowNum) {
							maxRowNum = Integer.parseInt(excelFieldTitleList.get(m)[4]);
						}
					}
					int beginIndex = i * EXPORT_SHEET_RECORD_NUMBER;
					int endIndex = (i+1) * EXPORT_SHEET_RECORD_NUMBER - 1;
					pageShell(sheet, list, beginIndex, endIndex, maxRowNum, DATA_CELL_FONT); //开始往每个sheet页中写入内容
					for (int j = 0; j <= maxRowNum; j++) {
						sheet.setRowView(j, 500, false); //设置行高
					}
					for (Map.Entry<String, String> entry : columnWidthMap.entrySet()) {
						sheet.setColumnView(Integer.parseInt(entry.getKey()), Integer.parseInt(entry.getValue()));
					}
				}
			}
		}
		book.write();
		book.close();
	}
	/**
	 * 向单个的excel sheet页中写入内容
	 * @param WritableSheet sheet : Excel sheet页对象
	 * @param List<String[]> list : 封装着要写入的内容 的集合
	 * @param int beginIndex : 写入的开始行数
	 * @param int endIndex : 结束行数
	 * @throws Exception
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2012-4-15 上午09:20:59
	 */
	private void pageShell(WritableSheet sheet, List<String[]> list, int beginIndex, int endIndex, int maxRowNum, WritableCellFormat DATA_CELL_FONT) throws Exception {
		if (list.size() < endIndex) endIndex = list.size();
		Label label = null; //单元格
		for (int i = beginIndex; i < endIndex; i++) {
			maxRowNum++;
			String[] valueArray = list.get(i);
			int m = 0;
			for (int j = 0; j < valueArray.length; j++, m++) {
				label = new Label(m, maxRowNum, ActivityStringUtils.replaceNullValue(valueArray[j]), DATA_CELL_FONT);
				sheet.setRowView(maxRowNum, 400, false); //设置行高
				sheet.addCell(label);
			}
		}
	}
	
	/**
	 * 测试专用
	 * @param args
	 * @Create at 2012-6-25 上午10:43:41
	 */
	public static void main(String[] args) {
		 //表头
		 List<String[]> excelFieldTitleList=new ArrayList<String[]>();
		 //表头内容  横坐标， 纵坐标，  合并到横坐标，  合并到纵坐标， 最大行数，  列宽，
		 String[] A={"测试字段","0","0","7","0","20","300"};
		 String[] B={"测试字段2","0","1","3","1","20","300"};
		 String[] C={"测试字段2","4","1","7","1","20","300"};
		 excelFieldTitleList.add(A);
		 excelFieldTitleList.add(B);
		 excelFieldTitleList.add(C);
		 //下面为数据
		 List<String[]> list=new ArrayList<String[]>();
		 String[]  list1={"测试","你","好","吗","号码","400","500","500"};
		 String[]  list2={"测试","你","好","吗","号码","400","500","500"};
		 String[]  list3={"测试","你","好","吗","号码","400","500","500"};
		 list.add(list1);
		 list.add(list2);
		 list.add(list3);
		 
		 ExportExcelService ext=new ExportExcelService();
		 try {
			ext.buildExcel(list, excelFieldTitleList, "test.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
