/*
 * Powered By 由忠禹
 * Since 2015 - 2016-07-19
 */

package com.sinosoft.business.service.impl;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.PssCollectInfoDao;
import com.sinosoft.business.dao.PssProductpriceDao;
import com.sinosoft.business.po.PssCollectInfo;
import com.sinosoft.business.po.PssProductprice;
import com.sinosoft.business.po.query.PssCollectInfoQuery;
import com.sinosoft.business.po.query.PssProductpriceQuery;
import com.sinosoft.business.service.PssCollectInfoService;
import com.sinosoft.business.uilt.GetTaskId;
import com.sinosoft.business.uilt.SetSystemProperty;
import com.sinosoft.common.constant.Constant;
import com.sinosoft.common.util.DateTimeUtils;
import com.sinosoft.common.util.SystemConfigUtil;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

@Service("pssCollectInfoService")
public class PssCollectInfoServiceImpl implements PssCollectInfoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PssCollectInfoServiceImpl.class);
	
	@Resource
	private PssCollectInfoDao pssCollectInfoDao;
	@Resource
	private PssProductpriceDao productpriceDao;
	/**
	 * 页面初始化
	 * @param Id
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author JC
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 */
	@Override
	public void pssCollectInfoPageInit(HttpServletRequest request,ModelMap model,String method){
		
		LOGGER.debug("Service层：根据查询参数获取信息 用于采集单管理页面");
		PssCollectInfoQuery pssCollectInfoQuery = new PssCollectInfoQuery();
		//以id升序排序
		pssCollectInfoQuery.setSortBy("id");
//		“2”为降序
		pssCollectInfoQuery.setSortType("2");
		if (method.equals("POST")) {
			//创建时间开始时间
			String createStarttime = request.getParameter("createStarttime");
//			创建时间结束时间
			String createEndtime = request.getParameter("createEndtime");
			pssCollectInfoQuery.setCreateStarttime(createStarttime);
			pssCollectInfoQuery.setCreateEndtime(createEndtime);
			model.addAttribute("createStarttime", createStarttime);
			model.addAttribute("createEndtime", createEndtime);
			//实施最早时间开始时间
			String beginStarttime = request.getParameter("beginStarttime");
//			实施最早时间结束时间
			String beginEndtime = request.getParameter("beginEndtime");
			pssCollectInfoQuery.setBeginStarttime(beginStarttime);
			pssCollectInfoQuery.setBeginEndtime(beginEndtime);
			model.addAttribute("beginStarttime", beginStarttime);
			model.addAttribute("beginEndtime", beginEndtime);
			//实施最晚时间开始时间
			String endStarttime = request.getParameter("endStarttime");
//			实施最晚时间结束时间
			String endEndtime = request.getParameter("endEndtime");
			pssCollectInfoQuery.setEndStarttime(endStarttime);
			pssCollectInfoQuery.setEndEndtime(endEndtime);
			model.addAttribute("endStarttime", endStarttime);
			model.addAttribute("endEndtime", endEndtime);
//			页数
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				pssCollectInfoQuery.setPage(Integer.parseInt(pageNum));
				model.addAttribute("pageNumInput", pageNum);
			}
			//卫星下拉列表
			String satelliteSearch = request.getParameter("_satelliteId");
			if(!StringUtils.isBlank(satelliteSearch) && !satelliteSearch.equals("-1") ){
				pssCollectInfoQuery.setSatelliteid(satelliteSearch);
				model.addAttribute("_satelliteId", satelliteSearch);
			}
			//传感器下拉列表
			String sensoridSearch = request.getParameter("_sensorIdswhat");
			if(!StringUtils.isBlank(sensoridSearch) && !sensoridSearch.equals("-1") ){
				pssCollectInfoQuery.setSensorid(sensoridSearch);
				model.addAttribute("_sensorIds", sensoridSearch);
				String[] val = SystemConfigUtil.getSystemValue(satelliteSearch,
						request.getServletContext());
				model.addAttribute("sensorIdS", val);// 传感器
			}
			//采集单编号
			String taskidSearch=request.getParameter("taskidSearch");
			if(!StringUtils.isBlank(taskidSearch)){
				pssCollectInfoQuery.setTaskid(taskidSearch);
				model.addAttribute("taskidSearch", taskidSearch);
			}
			//采集单状态下拉列表
			String statusSearch = request.getParameter("statusSearch");
			if(!StringUtils.isBlank(statusSearch) && !statusSearch.equals("-1") ){
				pssCollectInfoQuery.setStatus(statusSearch);
				model.addAttribute("statusSearchhuixian", statusSearch);
			}
		}
		//卫星集合
//		List satelliteList=pssCollectInfoDao.getSatelliteList();
//		model.addAttribute("satelliteList", satelliteList);
//		//传感器集合
//		List sensoridList=pssCollectInfoDao.getSensoridList();
//		model.addAttribute("sensoridList", sensoridList);
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		pssCollectInfoQuery.setUsername(eUser.getUserName());
		String[] valueArray = SystemConfigUtil.getSystemValue(
				"collectSatellite", request.getServletContext());
		model.put("collectSatellite", valueArray);
		
		
		
		//采集单状态集合
		List statusList=pssCollectInfoDao.getStatusList();
		model.addAttribute("statusList", statusList);
		
		Integer totalCount = pssCollectInfoDao.getCountByQuery(pssCollectInfoQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, pssCollectInfoQuery.getPageSize(), 
				pssCollectInfoQuery.getPage(), pssCollectInfoQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<PssCollectInfo> collectInfoList = pssCollectInfoDao.getPssCollectInfoInfoByQuery(pssCollectInfoQuery);
		
		model.addAttribute("collectInfoList", collectInfoList);
	}
	

	/** 
	 * 创建PssCollectInfo
	 * @param Id
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author JC
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 **/
	@Override
	public Boolean saveCollectInfoInfo(HttpServletRequest request,PssCollectInfo pssCollectInfo,ActivityModelMap modelMap){
		boolean result = false;
//		定义一个调用生成的采集单编号
		Long CJDHNum=(long) 0L;
//		定义最终获得的采集单编号
		Long taskid1=(long) 0L;
//		文件路径
//		String profilepath="..\\webapps\\pssweb\\WEB-INF\\classes\\Taskid.properties";
		String profilepath = System.getProperty("user.dir").replace("bin", "webapps")+"\\pssweb\\WEB-INF\\classes\\Taskid.properties";
//		System.out.println(path);
//		获得当前日期并转换成Integer
		String sdate=new java.sql.Date(new java.util.Date().getTime()).toString().replace("-", "");
		Integer sysdate=Integer.valueOf(sdate).intValue();
//		获取properties中的日期
		String pdate=SetSystemProperty.getKeyValue("taskid").substring(0,8);
		Integer prodate=Integer.valueOf(pdate).intValue();
//		获得properties中的Value
		CJDHNum=Long.parseLong(SetSystemProperty.getKeyValue("taskid"));
//		当前日期与properties文件日期进行比较
		if (sysdate>prodate) {
//			如果是新的日期则调用生成单号的方法
			GetTaskId taskid=new GetTaskId();
//			Constant.CAINUM采集单标号的常量：03
			String LSH=taskid.getNum(Constant.CAINUM);
//			生成的采集但编号存到数据库字段中
			pssCollectInfo.setTaskid(Long.valueOf(LSH));
//			把生成的采集单填入properties文件中
			SetSystemProperty.writeProperties("taskid", LSH.toString());
		}else{
//			如果是该日期的时间就在properties文件的值的基础上加1
			taskid1=CJDHNum+1L;
//			生成的采集但编号存到数据库字段中
			pssCollectInfo.setTaskid(Long.valueOf(taskid1));
			SetSystemProperty.writeProperties("taskid", taskid1.toString());
		}
//		获得当前用户名
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
//		向数据库中插入当前用户名
		pssCollectInfo.setUsername(username);
		pssCollectInfo.setCheckstate(1);
		//新增字段开始-----------
		if(pssCollectInfo.getUsertype()==null){
			pssCollectInfo.setUsertype(2);//用户优先级
		}
		if(pssCollectInfo.getUrgencylevel()==null){
			
			pssCollectInfo.setUrgencylevel(0);//紧急程度 String 一般：0；紧急：1
		}
		
		if(pssCollectInfo.getSatelliteid()==null){//<!-- 卫星标识 String  -->
			pssCollectInfo.setSatelliteid("VRSS-1");
		}
		if(pssCollectInfo.getSensorid()==null){
			
			pssCollectInfo.setSensorid("PMC");//相机标识
		}else{
			String sensorid = pssCollectInfo.getSensorid();
			String  aa[] = sensorid.split(" ");
			if(aa.length>1){
				String sensoridNew = sensorid.replace(" ", ",");
				pssCollectInfo.setSensorid(sensoridNew);
			}else{
				pssCollectInfo.setSensorid(sensorid);
			}
		}
		if(pssCollectInfo.getStationid()==null){
			
			pssCollectInfo.setStationid("FGS-1");//接收站标识 String 
		}
		if(pssCollectInfo.getAveragecloud()==null){
			
			pssCollectInfo.setAveragecloud(50);// 云量限制 
		}
		if(pssCollectInfo.getSideangle()==null){
			
			pssCollectInfo.setSideangle(30);// 最大侧摆角
		}
		if(pssCollectInfo.getObservationinterval()==null){
			
			pssCollectInfo.setObservationinterval("45");//数据采集时间长度 String 
		}
		if(pssCollectInfo.getFirstprinciple()==null){
			
			pssCollectInfo.setFirstprinciple("time first");//优先规划选项
		}
		if(pssCollectInfo.getEarthsurfacename()==null){
			pssCollectInfo.setEarthsurfacename("Neve");//地表属性 String 内容
		}
		if(pssCollectInfo.getSurfacereflectivity()==null){
			
			pssCollectInfo.setSurfacereflectivity("2");//地表反射率Reflection String
		}
		//新增字段结束-----------
		
		
		PssProductprice productprice=productpriceDao.getPriceByLevel("collection");
		DecimalFormat df=new DecimalFormat("######0");
		pssCollectInfo.setPrice(Integer.parseInt(df.format(productprice.getPrice()*pssCollectInfo.getArea())));
		pssCollectInfo.setUnit(productprice.getUnit());
		pssCollectInfo.setIspay(0);
		pssCollectInfo.setCheckispay(0);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pssCollectInfo.setCreatetime(sdf.format(new Date()));
		Integer resultNum = pssCollectInfoDao.insertPssCollectInfo(pssCollectInfo);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		
		return result;
	}
	
	/**
	 * 通过一条id查询到详情
	 * * 根据指定的采集单id 获取采集单信息
	 * @param Id
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author JC
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 */
	@Override
	public void getCollectInfoById(ActivityModelMap modelMap,
			HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			Long id = Long.parseLong(request.getParameter("id"));
			PssCollectInfoQuery pssCollectInfoQuery = new PssCollectInfoQuery();
			PssCollectInfo pssCollectInfo = pssCollectInfoDao.selectPssCollectInfoById(id);
			modelMap.put("pssCollectInfo", pssCollectInfo);
		}
	}
	/**
	 * 通过一条id查询到详情
	 * @return * 根据指定的采集单id 获取采集单信息
	 * @param Id
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author JC
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 */
	@Override
	public PssCollectInfo getCollectInfoById(Long id) {
		PssCollectInfo pssCollectInfo = pssCollectInfoDao.selectPssCollectInfoById(id);
		return pssCollectInfo;
	}
	/** 
	 * 更新PssCollectInfo
	 * 根据指定的采集单id 获取采集单信息
	 * 并且将获得到的采集单信息展示在修改页面
	 * @param Id
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author JC
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 **/	
	@Override
    public Boolean updateCollectInfoInfo(HttpServletRequest request,PssCollectInfo pssCollectInfo,ActivityModelMap modelMap){
		boolean result = false;
		Integer resultNum = pssCollectInfoDao.updatePssCollectInfo(pssCollectInfo);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}

		return result;
	}
    
	/** 
	 * 删除PssCollectInfo
	 * 根据指定的采集单id 获取采集单信息
	 * 并且将其删除
	 * @param Id
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author JC
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 **/
	@Override
    public boolean removeCollectInfoInfo(Long id,HttpServletRequest request,ActivityModelMap modelMap){
		boolean result = false;
		Integer resultNum = pssCollectInfoDao.deletePssCollectInfo(id);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return true;
	}

	/** 
	 * 查询PssCollectInfo
	 * @param taskid
	 * @param request
	 * @param response
	 * @return PssCollectInfo
	 * @throws
	 * @author wlg
	 * @date 
	 * @version V1.0
	 **/
	@Override
	public PssCollectInfo getCollectInfoByTaskId(Long taskid) {
		// TODO Auto-generated method stub
		return pssCollectInfoDao.getCollectInfoBytaskid(taskid);
	}
	
}
