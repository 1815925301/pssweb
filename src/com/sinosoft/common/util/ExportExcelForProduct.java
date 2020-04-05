package com.sinosoft.common.util;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sinosoft.business.po.PssTasMetadata;

/**
 * 导出excel(产品)
 * 
 * @author Dylan
 * @date 16-08-30 13:48:42
 */
@Service("exportExcele")
public class ExportExcelForProduct {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExportExcelForProduct.class);

	/**
	 * 导出订单信息(包含主订单)
	 * 
	 * @param title
	 *            String 页签名
	 * @param orderList
	 *            List<Map<String, Object>> 订单信息（包含子订单）
	 * @param out
	 *            OutputStream 输出流
	 * @param pattern
	 *            String 日期格式
	 * @param flag boolean true 订单 false 购物车
	 */
	@SuppressWarnings("unchecked")
	public void exporsOrderXls(String title,
			List<Map<String, Object>> orderList, OutputStream out,
			String pattern, boolean flag) {
		
//		String orderHead[] = { "主订单号", "订购时间", "订购人", "订单名称", "备注", "订单状态",
//		"主订单完成时间" };// 主订单标题
		
//		String orderBody[] = { "productid", "productname", "producttype",
//				"satelliteid", "sensorid", "stationid", "pixeltype" };// 主订单字段名
		
		// 子订单标题
		String sonOrderHead[] = { "产品序列号", "产品名称", "产品类型", "卫星标识", "载荷标识",
				"接收站标识", "像素值类型", "影像的宽", "影像的高", "产品波段数目", "成像开始时间", "成像结束时间", "太阳方位角",
				"太阳高度角", "地球模型", "地图投影", "像素单位", "影像X方向分辨率", "影像Y方向分辨率", "景中心纬度", "景中心经度",
				"图像左上角纬度", "图像左上角经度", "图像右上角纬度", "图像右上角经度", "图像左下角纬度", "图像左下角经度", "图像右下角纬度",
				"图像右下角经度", "连续处理次数", "处理信息标签1", "处理时间", "处理模块", "处理输入数据名",
				"产品浏览图文件名称" };
		
		// 子订单字段
		String sonOrderBody[] = { "PRODUCTID", "PRODUCTNAME", "PRODUCTTYPE",
				"SATELLITEID", "SENSORID", "STATIONID", "PIXELTYPE",
				"IMAGEWIDTH", "IMAGEHEIGHT", "IMAGEBANDNUM", "IMAGINGSTARTTIME", "IMAGINGSTOPTIME",
				"SUNAZIMUTH", "SUNELEVATION", "EARTHMODEL",
				"MAPPROJECTION", "UNIT", "RESOLUTIONX",
				"RESOLUTIONY", "SCENECENTERLAT", "SCENECENTERLONG", "DATAUPPERLEFTLAT",
				"DATAUPPERLEFTLONG", "DATAUPPERRIGHTLAT", "DATAUPPERRIGHTLONG", "DATALOWERLEFTLAT",
				"DATALOWERLEFTLONG", "DATALOWERRIGHTLAT", "DATALOWERRIGHTLONG",
				"PROCESSNUM", "PROCESSINFO", "PROCESSTIME",
				"PROCESSMODULE", "INPUTDATANAME", "BROWSENAME", "THUMBNAME"
				 };// 子订单字段
		if(flag){//true 订单 false 购物车
			exporOrderXls(title, orderList, out, pattern,sonOrderHead, sonOrderBody);
		}else{
			String[] str = {};
//			exporOrderXls(title, orderList, out, pattern, sonOrderHead, sonOrderBody, str, str);
		}
	}
	
	/**
	 * 导出订单信息
	 * @param title String 页签名
	 * @param orderList List<Map<String, Object>> 订单信息（包含子订单）
	 * @param out OutputStream 输出流
	 * @param pattern String 日期格式
	 * @param orderHead String[] 主订单标题
	 * @param orderBody String[] 主订单字段名
	 * @param sonOrderHead String[] 子订单标题
	 * @param sonOrderBody String[] 子订单字段
	 */
	@SuppressWarnings("unchecked")
	public void exporOrderXls(String title,
			List<Map<String, Object>> orderList, OutputStream out,
			String pattern,String sonOrderHead[], String sonOrderBody[]) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个页签
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		HSSFRow row;// 声明行
		HSSFCell cell;// 声明列（单元格）
		List<Map<String, Object>> sonOrderList;// 子订单
		int rowIndext = 0;// 行索引 调用后自增
		String orderId = "";// 主订单id
		for (int i = 0; i < orderList.size(); i++) {
				row = sheet.createRow(rowIndext++);// 行索引调用后自增
				for (int j = 0; j < sonOrderHead.length; j++) {// 设置子订单标题
					cell = row.createCell(j);// 创建单元格
					cell.setCellValue(new HSSFRichTextString(sonOrderHead[j]));
				}
				for (int k = 0; k < orderList.size(); k++) {
					row = sheet.createRow(rowIndext++);// 行索引调用后自增
					Map<String, Object> sonOrderMap = orderList.get(i);// 获取当前子订单对象
					for (int j = 0; j < sonOrderBody.length; j++) {// 子订单内容填充
					cell = row.createCell(j);// 创建单元格
						String key = sonOrderBody[j];// 根据子订单字段名获取对应信息
						Object value = sonOrderMap.get(key);
						value = getValue(key, value);
						switchVal(sheet, row, cell, value, pattern);
					}
				}

//			}
		}

		try {
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断值的类型后进行强制类型转换，并将结构加入到单元个中
	 * 
	 * @param sheet
	 *            HSSFSheet 页签对象
	 * @param row
	 *            HSSFRow 行对象
	 * @param cell
	 *            HSSFCell 列（单元格）对象
	 * @param value
	 *            Object 字段值
	 * @param pattern
	 *            String 日期格式
	 */
	private void switchVal(HSSFSheet sheet, HSSFRow row, HSSFCell cell,
			Object value, String pattern) {
		String textValue = null;
		if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			textValue = "true";
			if (!bValue) {
				textValue = "false";
			}
		} else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			textValue = sdf.format(date);

		}
		// else if (value instanceof byte[]) {
		// // 有图片时，设置行高为60px
		// row.setHeightInPoints(60);
		// // 设置图片所在列宽度为80px，注意这里单位的一个
		// sheet.setColumnWidth((short) i, (short) (35.7 * 80));
		// @SuppressWarnings("unused")
		// byte[] bsValue = (byte[]) value;
		// HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
		// 1023, 255, (short) 6, index, (short) 6, index);
		// anchor.setAnchorType(2);
		// // patriarch.createPicture(anchor, workbook.addPicture(
		// // bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
		// }
		else {
			// 其他数据类型都当作字符串简单处理
			if (value != null) {
				textValue = value.toString();
			}

		}
		// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
		if (textValue != null) {
			Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
			Matcher matcher = p.matcher(textValue);
			if (matcher.matches()) {
				// 是数字当作double处理
				cell.setCellValue(Double.parseDouble(textValue));

			} else {
				HSSFRichTextString richString = new HSSFRichTextString(
						textValue);
				// HSSFFont _font = workbook.createFont();
				// _font.setColor(HSSFColor.BLUE.index);
				// richString.applyFont(_font);
				cell.setCellValue(richString);
			}
		}
	}

	/**
	 * 获取数字段描述（中文）
	 * 
	 * @param key
	 *            String 数据库字段名
	 * @param value
	 *            Object 值
	 * @return Object 如果为null则返回""
	 */
	private Object getValue(String key, Object value) {
		if (null == value)
			return "";
		Object ojb = value;
		try {
			if ("TASKTYPE".equals(key)) {//订单类型
				if ("1".equals(value.toString())) {
					ojb = "生产订单";
				}
				if ("2".equals(value.toString())) {
					ojb = "普通订单";
				}
			}
			if ("ORDERSTATE".equals(key)) {//订单状态
				if ("1".equals(value.toString())) {
					ojb = "完成";
				}
				if ("2".equals(value.toString())) {
					ojb = "失败";
				}
				if ("3".equals(value.toString())) {
					ojb = "取消";
				}
				if ("4".equals(value.toString())) {
					ojb = "等待";
				}
				if ("5".equals(value.toString())) {
					ojb = "处理中";
				}
			}
			if ("DELFLAG".equals(key)) {//删除标识
				if ("1".equals(value.toString())) {
					ojb = "已删除";
				}
				if ("0".equals(value.toString())) {
					ojb = "未删除";
				}
			}
			if ("AREADYSTATE".equals(key)) {//完成状态
				if ("1".equals(value.toString())) {
					ojb = "等待审核";
				}
				if ("2".equals(value.toString())) {
					ojb = "审核通过";
				}
				if ("3".equals(value.toString())) {
					ojb = "审核失败";
				}
			}
			if ("ISDOWNLOAD".equals(key)) {//是否下载完成
				if ("0".equals(value.toString())) {
					ojb = "未下载完成";
				}
				if ("1".equals(value.toString())) {
					ojb = "下载完成";
				}
				if ("2".equals(value.toString())) {
					ojb = "过期";
				}
			}
//			if ("ISSEND".equals(key)) {//未找到
//				if ("0".equals(value.toString())) {
//					ojb = "未同步";
//				}
//				if ("1".equals(value.toString())) {
//					ojb = "已同步";
//				}
//			}
//			if ("APSCOISSEND".equals(key)) {//未找到
//				if ("0".equals(value.toString())) {
//					ojb = "需要同步订单状态";
//				}
//				if ("1".equals(value.toString())) {
//					ojb = "不需要同步订单状态";
//				}
//			}

		} catch (Exception e) {
			ojb = value;
			LOGGER.error("exporXls throws Exception", e);
		}

		return ojb;
	}
}