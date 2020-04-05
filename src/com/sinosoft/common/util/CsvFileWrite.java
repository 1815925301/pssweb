package com.sinosoft.common.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.demo.Write;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.sinosoft.business.po.PssOrderInfo;

@Service("exportCsv")
public class CsvFileWrite {
	private static final String NEW_LINE_SEPARATOR="\n";
	private static final Object [] FILE_HEADER={ "主订单号", "订购时间", "订购人", "订单名称", "备注", "订单状态",
		"主订单完成时间"};
	/*@SuppressWarnings("unchecked")
	public void exporOrderCsv(String title,
			List<PssOrderInfo> orderList, OutputStream out,
			String pattern) {
		
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
		
			exporOrderCsv(title, orderList, out, pattern, orderHead, orderBody, sonOrderHead, sonOrderBody);
		
		
	}*/
	
	@SuppressWarnings("unchecked")
	public void exporOrderCsv(String title,
			List<PssOrderInfo> orderList, OutputStream out,
			String pattern) {
		FileWriter fileWriter=null;
		CSVPrinter csvPrinter=null;
		CSVFormat csvFormat=CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
		//初始化FileWrite
		// 声明一个工作薄
				HSSFWorkbook workbook = new HSSFWorkbook();
				// 生成一个页签
				HSSFSheet sheet = workbook.createSheet(title);
				// 设置表格默认宽度为15个字节
				sheet.setDefaultColumnWidth(15);
				HSSFRow row;// 声明行
				HSSFCell cell;// 声明列（单元格）
		try {
			fileWriter=new FileWriter(pattern);
			csvPrinter=new CSVPrinter(fileWriter, csvFormat);
			csvPrinter.printRecord(FILE_HEADER);
			for(PssOrderInfo orderInfo : orderList){
				csvPrinter.printRecord(orderList);
				List<String> order=new ArrayList<String>();
				order.add(String.valueOf(orderInfo.getOrderid()));
				order.add(String.valueOf(orderInfo.getTasktype()));
				order.add(orderInfo.getTasktime());
				order.add(orderInfo.getFinishtime());
				order.add(orderInfo.getOperatorname());
				order.add(String.valueOf(orderInfo.getPriority()));
				order.add(orderInfo.getUsername());
				csvPrinter.printRecord(order);
			}
			System.out.println("csv文件创建成功");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fileWriter.flush();
				fileWriter.close();
				csvPrinter.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
		}
		

		

	
	}

}
