package com.sinosoft.business.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.business.po.OrderMain;
import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.PssOrderPay;
import com.sinosoft.business.po.PssRefund;
import com.sinosoft.business.service.OrderMainService;
import com.sinosoft.business.service.PssOrderPayService;
import com.sinosoft.business.service.PssRefundService;
import com.sinosoft.business.uilt.GenerateShapeFile;
import com.sinosoft.business.uilt.ZipTools;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.CSVUtils;
import com.sinosoft.common.util.CsvFileWrite;
import com.sinosoft.common.util.DateTimeUtils;
import com.sinosoft.common.util.ExportExcel;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.util.SystemConfigUtil;
import com.sinosoft.common.util.XmlUtil;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * 订单Controller层
 * 
 * @author Dylan
 * @date 16-08-24 17:25:59
 */
@Controller
public class OrderMainController {

	@Resource
	private OrderMainService orderMainService;// 订单Service层接口
	@Resource
	private ExportExcel exportExcel;//
	@Resource
	private CsvFileWrite exportCsv;//
	@Resource
	private PssRefundService refundService;
	@Resource
	private PssOrderPayService orderPayService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderMainController.class);

	/**
	 * 订单页面初始化
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/orderMain.do")
	public String orderMainManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		//个人中心需要的一个判断赋值，传入一个值如果是1就把方法改成post提交
		String meth=request.getParameter("methodmove");
		if("1".equals(meth)){
			method="POST";
		}
		try {
			LOGGER.debug(
					"Access management page{OrderMainController, orderMainManage}",
					method);
			model.addAttribute("pageHanName", "底层子任务"); // 页面名称
			orderMainService.initOrderPage(model, method, request);
			ActivityWebUtils.WrapperModle(request, model);
		} catch (Exception e) {
			LOGGER.error(
					"OrderMainController.class method = orderMainManage throws Exception",
					e);
		}
		return "manage/" + model.get("pageName");
	}

	/**
	 * 传感器下拉框初始化（二级联动）
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param satelliteId
	 *            String 卫星id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/sensorSelcect.do")
	public Map<String, Object> sensorSelcect(HttpServletRequest request,
			String satelliteId, ModelMap model) {
		LOGGER.debug("Access management page{OrderMainController, sensorSelcect}");
		@SuppressWarnings("unchecked")
		Map<String, Object> modelMap = new HashedMap();
		try {
			if (null != satelliteId && !"".equals(satelliteId.trim())) {
				String[] valueArray = SystemConfigUtil.getSystemValue(
						satelliteId, request.getServletContext());
				model.put("collectSatellite", valueArray);// 传感器
				modelMap.put("status", "success");
				modelMap.put("valArray", valueArray);
				model.put("chuanganqivalues", valueArray);
			}

		} catch (Exception e) {
			LOGGER.error(
					"OrderMainController.class method = sensorSelcect throws Exception",
					e);
			modelMap.put("status", "exception");
		}
		return modelMap;
	}

	/**
	 * 导出excel（订单）
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/exporXls.do")
	public void exporXls(HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.debug("Access management page{OrderMainController, exporXls}");
		@SuppressWarnings("unused")
		ActivityModelMap modelMap = new ActivityModelMap(request);

		try {
			String sonOrder = request.getParameter("sonOrderIds");// 获取选择的订单id
			StringBuffer buffer=new StringBuffer();
			String lig[]=sonOrder.split(",");
			for (int i = 0; i < lig.length; i++) {
				buffer.append(lig[i]).append("_");
			}
			String son=buffer.toString();
			String str[]=son.split("_");
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < str.length; i+=2) {
				sb.append(str[i]).append(",");
			
			}
			String sonOrderIds=sb.deleteCharAt(sb.length()-1).toString();
			String flag = request.getParameter("flag");// 获取标识
			if (null != sonOrderIds && !"".equals(flag)) {// 判断id是否为null
				LOGGER.error(sonOrderIds);
				OutputStream out = response.getOutputStream();// 打开输出流
				response.setContentType("octets/stream");
				response.addHeader(
						"Content-Disposition",
						"attachment;filename="
								+ DateTimeUtils.getNowStrTimeStemp() + ".xls");
				@SuppressWarnings("unchecked")
				Map<String, Object> condMap = new HashedMap();
				condMap.put("sonOrderIds", sonOrderIds);
				List<Map<String, Object>> orderList;
				boolean flagB = true;
				if ("1".equals(flag)) {// 1 订单 2 购物车
					flagB = true;
				} else if ("2".equals(flag)) {// 1 订单 2 购物车
					flagB = false;
				}
				condMap.put("flag", flag);
				orderList = orderMainService.findOrderByCond(condMap);
				exportExcel.exporOrderXls("orderInfo", orderList, out,
						DateTimeUtils.YMDHMS, flagB);
				out.flush();
				out.close();
			}
			
		} catch (Exception e) {
			LOGGER.error(
					"OrderMainController.class method = exporXls throws Exception",
					e);
		}
		

	}
	/**
	 * 导出excsv（订单）
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/exportCsv.do")
	public void exportCsv(HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.debug("Access management page{OrderMainController, exportCsv}");
		@SuppressWarnings("unused")
		ActivityModelMap modelMap = new ActivityModelMap(request);

		try {
			String sonOrder = request.getParameter("sonOrderIds");// 获取选择的订单id
			StringBuffer buffer=new StringBuffer();
			String lig[]=sonOrder.split(",");
			for (int i = 0; i < lig.length; i++) {
				buffer.append(lig[i]).append("_");
			}
			String son=buffer.toString();
			String str[]=son.split("_");
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < str.length; i+=2) {
				sb.append(str[i]).append(",");
			
			}
			String Csvname=DateTimeUtils.getNowStrTimeStemp();
			String sonOrderIds=sb.deleteCharAt(sb.length()-1).toString();
			String[] orderids=sonOrderIds.split(",");
			
				LOGGER.error(sonOrderIds);
				OutputStream out = response.getOutputStream();// 打开输出流
				response.setContentType("octets/stream");
				response.addHeader(
						"Content-Disposition",
						"attachment;filename="
								+ DateTimeUtils.getNowStrTimeStemp() + ".csv");
				
				@SuppressWarnings("unchecked")
				Map<String, Object> condMap = new HashedMap();
				condMap.put("sonOrderIds", sonOrderIds);
				List<PssOrderInfo> orderList=new ArrayList<PssOrderInfo>();
				FileInputStream in = null;
				// 生成shape文件的路径
				String shppath = request.getSession().getServletContext()
						.getRealPath("/")
						+ "ext\\disastershp\\";
				String newpathdown = request.getSession().getServletContext()
						.getRealPath("/")
						+ "ext/disastershp\\";
				for (int i = 0; i < orderids.length; i++) {
					PssOrderInfo info=orderMainService.getOrderInfo(Long.parseLong(orderids[i]));
					orderList.add(info);
				}
				
				CSVUtils.createCSV(orderList, shppath, Csvname);
				
				// 读取要下载的文件，保存到文件输入流
				
				in = new FileInputStream(newpathdown + Csvname+".csv");
				// 创建缓冲区
				byte gt[] = new byte[1024];
				int len = 0;
				// 循环将输入流中的内容读取到缓冲区当中
				while ((len = in.read(gt)) > 0) {
					// 输出缓冲区的内容到浏览器，实现文件下载
					out.write(gt, 0, len);

				}
				in.close();
				out.flush();
				out.close();

			
			
		} catch (Exception e) {
			LOGGER.error(
					"OrderMainController.class method = exporcsv throws Exception",
					e);
		}
		

	}
	/**
	 * 导出xml（订单）
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/exportXml.do")
	public void exportXml(HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.debug("Access management page{OrderMainController, exportCsv}");
		@SuppressWarnings("unused")
		ActivityModelMap modelMap = new ActivityModelMap(request);

		try {
			String sonOrder = request.getParameter("sonOrderIds");// 获取选择的订单id
			StringBuffer buffer=new StringBuffer();
			String lig[]=sonOrder.split(",");
			for (int i = 0; i < lig.length; i++) {
				buffer.append(lig[i]).append("_");
			}
			String son=buffer.toString();
			String str[]=son.split("_");
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < str.length; i+=2) {
				sb.append(str[i]).append(",");
			
			}
			String xmlname=DateTimeUtils.getNowStrTimeStemp()+ ".xml";
			String sonOrderIds=sb.deleteCharAt(sb.length()-1).toString();
			String[] orderids=sonOrderIds.split(",");
			
				LOGGER.error(sonOrderIds);
				OutputStream out = response.getOutputStream();// 打开输出流
				response.setContentType("octets/stream");
				response.addHeader(
						"Content-Disposition",
						"attachment;filename="
								+ DateTimeUtils.getNowStrTimeStemp() + ".xml");
				
				@SuppressWarnings("unchecked")
				Map<String, Object> condMap = new HashedMap();
				condMap.put("sonOrderIds", sonOrderIds);
				List<PssOrderInfo> orderList=new ArrayList<PssOrderInfo>();
				FileInputStream in = null;
				// 生成shape文件的路径
				String shppath = request.getSession().getServletContext()
						.getRealPath("/")
						+ "ext\\disastershp\\";
				String newpathdown = request.getSession().getServletContext()
						.getRealPath("/")
						+ "ext/disastershp\\";
				for (int i = 0; i < orderids.length; i++) {
					PssOrderInfo info=orderMainService.getOrderInfo(Long.parseLong(orderids[i]));
					orderList.add(info);
				}
				String pathname=shppath+xmlname;
				XmlUtil.BuildXmlDoc(orderList, pathname);
				
				
				
				// 读取要下载的文件，保存到文件输入流
				
				in = new FileInputStream(newpathdown + xmlname);
				// 创建缓冲区
				byte gt[] = new byte[1024];
				int len = 0;
				// 循环将输入流中的内容读取到缓冲区当中
				while ((len = in.read(gt)) > 0) {
					// 输出缓冲区的内容到浏览器，实现文件下载
					out.write(gt, 0, len);

				}
				in.close();
				out.flush();
				out.close();

			
			
		} catch (Exception e) {
			LOGGER.error(
					"OrderMainController.class method = exporcsv throws Exception",
					e);
		}
		

	}
	
	
	/**
	 * 导出shp（订单）
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/exportShp.do")
	public void exportShp(HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.debug("Access management page{OrderMainController, exportShp}");
		@SuppressWarnings("unused")
		ActivityModelMap modelMap = new ActivityModelMap(request);

		try {
			String sonOrderIds = request.getParameter("sonOrderIds");// 获取选择的订单id
			String flag = request.getParameter("flag");// 获取标识
			if (null != sonOrderIds && !"".equals(flag)) {// 判断id是否为null
				LOGGER.error(sonOrderIds);
				OutputStream out = response.getOutputStream();// 打开输出流
				response.setContentType("octets/stream");
				String shpName = DateTimeUtils.getNowStrTimeStemp();// 生成shape文件的文件名
				response.addHeader("Content-Disposition",
						"attachment;filename=" + shpName + ".zip");
				@SuppressWarnings("unchecked")
				Map<String, Object> condMap = new HashedMap();
				condMap.put("sonOrderIds", sonOrderIds);

				// 生成shape文件的路径
				String shppath = request.getSession().getServletContext()
						.getRealPath("/")
						+ "ext\\disastershp\\";
				String newpathdown = request.getSession().getServletContext()
						.getRealPath("/")
						+ "ext\\zip/downshp\\";

				Map<String, Object> dateMap = orderMainService
						.getShpDate(sonOrderIds);
				String attrPiont = (String) dateMap.get("spaceInfo");
				String attributes = (String) dateMap.get("orderInfo");
				GenerateShapeFile.createShape(shppath, shpName, attrPiont,
						attributes);

				// 压缩文件
				File fileshp = new File(shppath);
				if (!fileshp.exists() && !fileshp.isDirectory()) {
					fileshp.mkdirs();
				}
				File filedown = new File(newpathdown);
				if (!filedown.exists() && !filedown.isDirectory()) {
					filedown.mkdirs();
				}

				String filenamezip = "";
				FileInputStream in = null;
				try {

					filenamezip = shpName + ".zip";
					ZipTools.zipFile(shppath, newpathdown + filenamezip,
							shpName);
					File filedir = new File(shppath);
					if (filedir.isDirectory()) {
						File[] fileList = filedir.listFiles();
						for (int i = 0; i < fileList.length; i++) {
							fileList[i].delete();// 删除
						}
					}
					
					// 读取要下载的文件，保存到文件输入流
					in = new FileInputStream(newpathdown + filenamezip);
					// 创建缓冲区
					byte buffer[] = new byte[1024];
					int len = 0;
					// 循环将输入流中的内容读取到缓冲区当中
					while ((len = in.read(buffer)) > 0) {
						// 输出缓冲区的内容到浏览器，实现文件下载
						out.write(buffer, 0, len);

					}

				} catch (Exception e) {
					LOGGER.error(
							"OrderMainController.class method = exporXls throws Exception",
							e);
					e.printStackTrace();
				} finally {
					// 关闭流
					in.close();
					out.flush();
					out.close();
				}
			}
		} catch (Exception e) {
			LOGGER.error(
					"OrderMainController.class method = exporXls throws Exception",
					e);
			e.printStackTrace();
		}

	}
	/**
	 * 跳转订单支付页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/orderpayinfo.do")
	public String orderpayinfo(HttpServletRequest request, ModelMap model) {
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		model.addAttribute("usercountry", eUser.getCountry());
		Long orderMainId=Long.parseLong(request.getParameter("orderMainid"));
		model.addAttribute("orderMainId", orderMainId);
		OrderMain orderMain=orderMainService.getorderMainInfo(orderMainId);
		model.addAttribute("orderMain", orderMain);
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		String userbank=PropertiesUtils.key("pssuserbank",locale);
		String ubank[]=userbank.split(",");
		model.addAttribute("userbank", ubank);//用户银行
		String abaebank=PropertiesUtils.key("ABAEBANK",locale);
		String abank[]=abaebank.split(",");
		model.addAttribute("abaebank", abank);//ABAE银行
		return "manage/orderpay";
	}
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/showorderpayinfo.do")
	public String showOrderpayinfo(HttpServletRequest request, ModelMap model) {
		//主订单号
		Long orderMainId=Long.parseLong(request.getParameter("orderMainid"));
		
		PssOrderPay pssOrderPay=orderPayService.getPayinfoByMainid(orderMainId);
		model.addAttribute("pssOrderPay", pssOrderPay);
		return "manage/orderpayinfo";
	}
	/**
	 * 查看退款信息
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/showRefundInfo.do")
	public String showRefundInfo(HttpServletRequest request, ModelMap model) {
		//主订单号

			if(request.getParameter("orderMainid")!=null && !"".equals(request.getParameter("orderMainid"))){
				Long orderMainid=Long.parseLong(request.getParameter("orderMainid"));
				PssRefund refund=refundService.getPssRefund(orderMainid);
				model.addAttribute("refund", refund);
			}
			if(request.getParameter("collectid")!=null && !"".equals(request.getParameter("collectid"))){
				Long collectid=Long.parseLong(request.getParameter("collectid"));
				PssRefund refund=refundService.getPssRefundBycollectid(collectid);
				model.addAttribute("refund", refund);
			}
		return "manage/refundinfo";
	}
}
