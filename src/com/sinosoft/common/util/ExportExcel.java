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

/**
 * 导出excel(订单)
 * 
 * @author Dylan
 * @date 16-08-30 13:48:42
 */
@Service("exportExcel")
public class ExportExcel {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExportExcel.class);

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
	public void exporOrderXls(String title,
			List<Map<String, Object>> orderList, OutputStream out,
			String pattern, boolean flag) {
		
		String orderHead[] = { "主订单号", "订购时间", "订购人", "订单名称", "备注", "订单状态",
		"主订单完成时间" };// 主订单标题
		
		String orderBody[] = { "ORDER_MAIN_ID", "ORDERTIME", "ORDERER",
				"ORDERNAME", "REMARK", "ORDERSTATE", "FINISHTIME" };// 主订单字段名
		
		// 子订单标题
		String sonOrderHead[] = { "子订单号", "任务类型", "任务时间", "完成时间", "操作员姓名",
				"用户类型", "优先级", "用户名", "卫星标识", "传感器标识", "产品标识", "景号", "产品号",
				"产品级别", "分发介质", "产品上传目录", "产品格式", "产品类型", "景数目", "景漂移", "轨道类型",
				"姿态类型", "产品波段", "产品分辨率", "投影方式", "椭球模型", "重采样方法", "高程模型",
				"星历数据类型", "姿态数据类型", "辐射校正方法", "是否做MTF", "接收站代号", "间隔采样",
				"是否去噪", "是否做瑞利散射校正", "订单状态", "左上经度", "左上纬度", "右上经度", "右上纬度",
				"左下经度", "左下纬度", "右下经度", "右下纬度", "删除标识", "审核状态", "文件大小",
				"采集单标识", "审核人", "审核时间", "备注", "价格", "结算方式", "像元间距", "正射校正方法",
				"几何校正方法", "多顶式模型阶数", "地图指向", "地球模型", "MTF补偿", "DEM数据类型",
				"图像数据类型", "坐标系", "成像算法", "多普勒质心计算方法", "方位向视数", "距离向视数",
				"距离向加权窗", "降斑处理算法", "天线方向图数据来源", "切趾方法", "相位校正方法", "光谱复原方法",
				"订购波段数", "是否下载完成", "多普勒频率计算方法", "方位向加权窗", "是否已同步", "任务步骤",
				"是否结算", "产品标识序列", "图幅名", "投影分带方式", "失败原因", "条带号", "自适应生产方式",
				"波段配准", "片间均衡", "DEM来源", "控制点来源", "TLC类型 （DLC、NAD）", "亚太同步标识",
				"外部的订单号", "浏览图路径", "", "主订单ID", "步骤时间" };
		
		// 子订单字段
		String sonOrderBody[] = { "ORDERID", "TASKTYPE", "TASKTIME",
				"FINISHTIME", "OPERATORNAME", "USERTYPE", "PRIORITY",
				"USERNAME", "SATELLITEID", "SENSORID", "PRODUCT", "SCENEID",
				"PRODUCTID", "PRODUCTLEVEL", "ORDERMEDIUMID",
				"PRODUCTUPLOADDIR", "PGPRODUCTFORMAT", "PRODUCTTYPE",
				"SCENECOUNT", "SCENESHIFT", "ORBITTYPE", "ATTTYPE",
				"BANDNUMBER", "IMAGEGSD", "MAPPROJECTION", "EARTHELLIPSOID",
				"RESAMPLINGKERNEL", "HEIGHTMODE", "EPHEMERISDATA",
				"ATTITUDEDATA", "RADIOMETRICMETHOD", "MTFCORRECTION",
				"RECEIVESTATIONID", "GSD", "DENOISE", "RAYLEIGHCORRECTION",
				"ORDERSTATE", "UPPERLEFTLONG", "UPPERLEFTLAT",
				"UPPERRIGHTLONG", "UPPERRIGHTLAT", "LOWERLEFTLONG",
				"LOWERLEFTLAT", "LOWERRIGHTLONG", "LOWERRIGHTLAT", "DELFLAG",
				"AREADYSTATE", "PRODUCTSIZE", "PHOTOODRDER", "CHECKUSERNAME",
				"CHECKTIME", "NOTE", "PRICE", "CLEARFORM", "PIXELSPACING",
				"ORTHOGRAPHMETHOD", "GEODETICMETHOD", "EQUATIONORDER",
				"PRODUCTORIENTATION", "EARTHMODEL", "MTFCPROMODE", "DEMTYPE",
				"DATATYPE", "COORDTYPE", "IMAGINGALGOR", "FDCMETHOD",
				"AZIMUTHLOOKS", "RANGELOOKS", "WEIGHTRANGE", "DESPECKLEMETHOD",
				"ANTENNAPATTERNSOURCE", "ADDWINDOW", "CORRECTPHASE",
				"RECONSTRUCTPROCSS", "BANDSORDERED", "ISDOWNLOAD", "FDRMETHOD",
				"WEIGHTAZIMUTH", "ISSEND", "TASKPROCSTEP", "ISCOUNTCLOSE",
				"PRODUCTIDSTRING", "MAPSHEETNAME", "MAPZONETYPE", "FAILREASON",
				"STRIPID", "AUTOPROCESSING", "BANDREGISTER", "CCDBALANCE",
				"DEMSOURCE", "GCPSOURCE", "TLCTYPE", "APSCOISSEND",
				"EXTERORDERID", "BROWSEFILELOCATION", "INSTRUMENTMODE",
				"ORDER_MAIN_ID", "TASKSTEPTIME" };// 子订单字段
		if(flag){//true 订单 false 购物车
			exporOrderXls(title, orderList, out, pattern, orderHead, orderBody, sonOrderHead, sonOrderBody);
		}else{
			String[] str = {};
			exporOrderXls(title, orderList, out, pattern, sonOrderHead, sonOrderBody, str, str);
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
			String pattern, String orderHead[], String orderBody[], 
			String sonOrderHead[], String sonOrderBody[]) {
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
			for (int j = 0; j < orderHead.length; j++) {// 设置主订单标题
				cell = row.createCell(j);// 创建单元格
				cell.setCellValue(new HSSFRichTextString(orderHead[j]));
			}

			row = sheet.createRow(rowIndext++);// 行索引调用后自增
			Map<String, Object> orderMap = orderList.get(i);// 取出当前行数据对象并自增下标值
			for (int j = 0; j < orderBody.length; j++) {// 主订单内容填充
				cell = row.createCell(j);// 创建单元格
				String key = orderBody[j];
				Object value = orderMap.get(key);
				value = getValue(key, value);
				if ("ORDER_MAIN_ID".equals(key)) {
					orderId = String.valueOf(value);
				}
				switchVal(sheet, row, cell, value, pattern);
			}
			if (null != orderId && !"".equals(orderId.trim())) {// 判断是否能获取到主订单id
				sonOrderList = (List<Map<String, Object>>) orderMap
						.get(orderId);

				row = sheet.createRow(rowIndext++);// 行索引调用后自增
				for (int j = 0; j < sonOrderHead.length; j++) {// 设置子订单标题
					cell = row.createCell(j);// 创建单元格
					cell.setCellValue(new HSSFRichTextString(sonOrderHead[j]));
				}
				for (int k = 0; k < sonOrderList.size(); k++) {
					row = sheet.createRow(rowIndext++);// 行索引调用后自增
					Map<String, Object> sonOrderMap = sonOrderList.get(k);// 获取当前子订单对象
					for (int j = 0; j < sonOrderBody.length; j++) {// 子订单内容填充
						cell = row.createCell(j);// 创建单元格
						String key = sonOrderBody[j];// 根据子订单字段名获取对应信息
						Object value = sonOrderMap.get(key);
						value = getValue(key, value);
						switchVal(sheet, row, cell, value, pattern);
					}

				}

			}
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