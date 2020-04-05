package com.sinosoft.business.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.PssProductpriceDao;
import com.sinosoft.business.dao.PssShopCarDao;
import com.sinosoft.business.po.PssCollectInfo;
import com.sinosoft.business.po.PssProductprice;
import com.sinosoft.business.po.PssShopCar;
import com.sinosoft.business.po.PssShopCarOrdertime;
import com.sinosoft.business.po.query.PssCollectInfoQuery;
import com.sinosoft.business.po.query.PssProductpriceQuery;
import com.sinosoft.business.po.query.PssShopCarQuery;
import com.sinosoft.business.service.PssShopCarService;
import com.sinosoft.common.util.DateTimeUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;




@Service("shopCarService")
public class PssShopCarServiceImpl implements PssShopCarService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PssOrderInfoServiceImpl.class);
	@Resource
	private PssShopCarDao pssshopcarDao;
	@Resource
	private PssProductpriceDao productpriceDao;
	public static String date_car;
	@Override
	public void PssShopCarPageInit(ModelMap model, String method,
			HttpServletRequest request) {
		LOGGER.debug("Service层:查询订单");
		PssShopCarQuery pssshopcarQuery = new PssShopCarQuery();
		// 以id升序排序
		pssshopcarQuery.setSortBy("orderid");
		pssshopcarQuery.setSortType("2");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				pssshopcarQuery.setPage(Integer.parseInt(pageNum));
			}
		}
		//获得登录人
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		pssshopcarQuery.setUsername(username);
		Integer totalCount = pssshopcarDao.getCountByQuery(pssshopcarQuery);
		
		TotalInfo totalInfo = new TotalInfo(totalCount,
				pssshopcarQuery.getPageSize(), pssshopcarQuery.getPage(),
				pssshopcarQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		//创建map集合
		Map<String,List<PssShopCar>> dateofonetime = new HashMap<String, List<PssShopCar>>();//("2016-01-02",list<pssshocat>)
		//获得此登录人在购物车所有的订单
		List<PssShopCar> orderList = pssshopcarDao.getPssShopCarListByQuery(pssshopcarQuery);
		//获得什么级别对应什么价格计算价格的公式
		List<PssProductprice> list= productpriceDao.getPriceThough();
		//此循环是取价格表里的价格放进购物车
		for(int j = 0;j<orderList.size();j++){
			//获得购物车里面的数据里的价格
			String productlevle= orderList.get(j).getProductlevel();
			String productType= orderList.get(j).getProducttype();
			for(int i=0;i<list.size();i++){
				String levle = list.get(i).getProductlevel();
				//进行比对，如果购物车里数据的级别和价格表里面的级别一样，把价格取出来放进购物车里价格字段里显示该价格。
				if(levle.equals(productlevle)){
					Integer price = Integer.parseInt(list.get(i).getPrice().toString());
					String unit = list.get(i).getUnit();
					orderList.get(j).setPrice(price);
					orderList.get(j).setUuit(unit);
					PssShopCar pssshopcar=new PssShopCar();
					pssshopcar.setOrderid(orderList.get(j).getOrderid());
					pssshopcar.setPrice(price);
					pssshopcar.setUuit(unit);
					pssshopcarDao.updatePssShopCar(pssshopcar);
				}
				if(levle.equals(productType)){
					Integer price = Integer.parseInt(list.get(i).getPrice().toString());
					String unit = list.get(i).getUnit();
					orderList.get(j).setPrice(price);
					orderList.get(j).setUuit(unit);
					PssShopCar pssshopcar=new PssShopCar();
					pssshopcar.setOrderid(orderList.get(j).getOrderid());
					pssshopcar.setPrice(price);
					pssshopcar.setUuit(unit);
					pssshopcarDao.updatePssShopCar(pssshopcar);
				}
			}
		}
		dateofonetime =this.getMapByList(orderList);
		 model.addAttribute("dateofonetime", dateofonetime);
		allsumprice=0;
		list.removeAll(list);
		model.addAttribute("ordertime", date_car);
		
	}
	
	@Override
	public List<PssShopCar> selectShopCar(HttpServletRequest request,
			ActivityModelMap modelMap) {
		
		PssShopCarQuery pssshopcarQuery = new PssShopCarQuery();
		// 以id升序排序
		pssshopcarQuery.setSortBy("orderid");
		pssshopcarQuery.setSortType("2");
		//获得登录人
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		pssshopcarQuery.setUsername(username);
		//获得此登录人在购物车所有的订单
		List<PssShopCar> orderList = pssshopcarDao.getPssShopCarListall(pssshopcarQuery);
		return orderList;
	}
	
	
	
	private Map<String, List<PssShopCar>> getMapByList(
			 List<PssShopCar> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		Map<String, List<PssShopCar>> map = new LinkedHashMap<String, List<PssShopCar>>();
		for (PssShopCar shopinfo : list) {
			//取出购物车里的时间，把时间一样的所有数据放在一个集合里面
			Date ordertimes = shopinfo.getOrderdate();
			String ordertime =DateTimeUtils.DateToString(ordertimes, "yyyy-MM-dd");
			if (map.containsKey(ordertime)) {
				//获取集合里的时间把value的list取出来
				List<PssShopCar> items = map.get(ordertime);
				//在原来的list里面增加新遍历出来的一样时间下的数据
				items.add(shopinfo);
			} else {
				List<PssShopCar> items = new ArrayList<PssShopCar>();
				items.add(shopinfo);
				map.put(ordertime, items);
			}
		}
		return map;
	}
	/***
	 * 通过日期查询有多少个id
	 */
	@Override
	public void selevtIdOfDate(HttpServletRequest request,ActivityModelMap modelMap,String date) {
		PssShopCarQuery pssshopcarQuery =new PssShopCarQuery();
		DateTimeUtils DateTimeUtils =new DateTimeUtils();
		Date times = null;
		try {
			times = DateTimeUtils.str2Date(date,"yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List dateidlist = new ArrayList();
//		String ordertimes = DateTimeUtils.DateToString(date, "yyyy-MM-dd");
		pssshopcarQuery.setOrderdate(times);
		
		List<PssShopCar> listdate = pssshopcarDao.getPssShopCarListByQuery(pssshopcarQuery);
		for(int i=0;i<listdate.size();i++){
			dateidlist.add(listdate.get(i).getOrderid());
		}
		modelMap.put("dateidlist", dateidlist);
	}

	
	List<PssShopCar> lis;
	@Override
	public Boolean save(HttpServletRequest request, PssShopCar pssshopcar,
			ActivityModelMap modelMap) {
		int result=0;
		boolean flay = false;
		try {
			result=pssshopcarDao.insertPssShopCar(pssshopcar);
		} catch (Exception e) {
			result=0;
			LOGGER.error("save===="+e.getMessage());
		}
		if(result>0){
			flay=true;
		}
		
	
		return flay;
	}
	//更新修改
	@Override
	public Boolean update(HttpServletRequest request, PssShopCar pssshopcar,
			ActivityModelMap modelMap) {
		boolean result = false;
		Integer resultNum = pssshopcarDao.updatePssShopCar(pssshopcar);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}

		return result;
	}

	@Override
	public boolean remove(HttpServletRequest request, ActivityModelMap modelMap) {
		PssShopCar pssshopcar=new PssShopCar();
		String orderid=request.getParameter("orderid");
		pssshopcar.setOrderid(Long.parseLong(orderid));
		boolean faly=false;
		int fal=pssshopcarDao.deletePssShopCar(pssshopcar);
		if(fal>0){
			faly=true;
		}
		return faly;
	}
	
	@Override
	public boolean removed(HttpServletRequest request, ActivityModelMap modelMap,String id) {
		PssShopCar pssshopcar=new PssShopCar();
		if(id!=""){
			
			pssshopcar.setOrderid(Long.parseLong(id));
		}
		boolean faly=false;
		int fal=pssshopcarDao.deletePssShopCar(pssshopcar);
		if(fal>0){
			faly=true;
		}
		return faly;
	}

	/**
	 * 通过一条id查询到详情
	 */
	@Override
	public void getPssShopCar(HttpServletRequest request,
			ActivityModelMap modelMap) {
		// TODO Auto-generated method stub
		PssShopCar pssshopcar=new PssShopCar();
		String orderid=request.getParameter("orderid");
		if(null!=orderid&&!"".equals(orderid)){
		pssshopcar.setOrderid(Long.parseLong(orderid));
		PssShopCar shopCar=pssshopcarDao.selectPssShopCarById(pssshopcar);
		modelMap.put("PssShopCar", shopCar);
		}
	}

	@Override
	public void getPssShopCarForInitPage(ModelMap model, String method,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		LOGGER.debug("Service层:查询订单");
		PssShopCarQuery pssshopcarQuery = new PssShopCarQuery();
		// 以id升序排序
		pssshopcarQuery.setSortBy("orderid");
		pssshopcarQuery.setSortType("-1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			
			if (!StringUtil.isBlank(pageNum)) {
				pssshopcarQuery.setPage(Integer.parseInt(pageNum));
			}
		}

		Integer totalCount = pssshopcarDao.getCountByQuery(pssshopcarQuery);
		System.out.println(totalCount);
		TotalInfo totalInfo = new TotalInfo(totalCount,
				pssshopcarQuery.getPageSize(), pssshopcarQuery.getPage(),
				pssshopcarQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<PssShopCar> orderList = pssshopcarDao.getPssShopCarListByQuery(pssshopcarQuery);
		model.addAttribute("orderList", orderList);
	}

	@Override
	public void getPssShopCarinfo(HttpServletRequest request,
			ModelMap model) {
		PssShopCar pssshopcar=new PssShopCar();
		String orderid=request.getParameter("orderid");
		if(null!=orderid&&!"".equals(orderid)){
		pssshopcar.setOrderid(Long.parseLong(orderid));
		PssShopCar shopCar=pssshopcarDao.selectPssShopCarById(pssshopcar);
		model.addAttribute("shopCar", shopCar);
	
		
	}
}


	@Override
	public Integer getShopcarcontol(ModelMap model, String method,
			HttpServletRequest request) {
		PssShopCarQuery pssshopcarQuery=new PssShopCarQuery();
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		if(eUser!=null){//登录之后的用户
			String username= eUser.getUserName();
			pssshopcarQuery.setUsername(username);
			return pssshopcarDao.getCountByQuery(pssshopcarQuery);
		}else{
			return 0;
		}
		
	}

	long allsumprice=0;
	List<Long> list = new ArrayList<Long>();
	
	
	
	
	
	@Override
	public void selectShopCarOrderPrice(HttpServletRequest request,
			ActivityModelMap modelMap){
		//获得是否是全选
		String key = request.getParameter("key");
		//把每个订单的价格查询出来并显示
		int priceinit=0;
		String shopcarorderid =request.getParameter("scorderid");
		//是否添加价格
		int isaddprvenu=0;
		Long sshoreder = Long.parseLong(shopcarorderid);
		PssShopCar pssShopCar = new PssShopCar();
		pssShopCar.setOrderid(sshoreder);
		//通过id查询购物车里的订单的详情获取两个字段，
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		pssShopCar.setUsername(username);
		PssShopCar shopCar=pssshopcarDao.selectPssShopCarById(pssShopCar);
		if(shopCar==null){
			modelMap.put("theDate", "0");
		}else{
		int yes=0;
		//把id放进一个数组里面，如果点击取消这个chexbox就把这个价格减去
		if(list.size()==0){
			list.add(shopCar.getOrderid());
		}else{
		//循环遍历id，如果有id就不在存放id
		for(int k=0;k<list.size();k++){
			long ids = list.get(k);
			long getordreid =shopCar.getOrderid();
			if("-18".equals(key)){
				if(getordreid == ids){
					list.remove(ids);
//					list.add(getordreid);
					yes++;
					isaddprvenu=1;
				}else{
					
				}
			}else{
			
			if(getordreid == ids){
				list.remove(ids);
				yes++;
				
			}else{
				
			}
			}
			
			
		}
		if(yes==1){
			//如果等于1就说明已经有这个id，要减去这个价格了
			
		}else{
			String valuess = request.getParameter("veuel");
			if("-15".equals(valuess)){
				
			}else{
			list.add(shopCar.getOrderid());
			}
		}	
		}
		
		//设置查询到的两个字段作为查询价格的条件
		String level = shopCar.getProductlevel();
		String type = shopCar.getProducttype();
		//通过两个字段查询此订单的价格
		PssProductpriceQuery pssProductpriceQuery = new PssProductpriceQuery();
		pssProductpriceQuery.setProductlevel(level);
		pssProductpriceQuery.setProducttype(type);
		List<PssProductprice> productprice = productpriceDao.getEveryPrice(pssProductpriceQuery);
		long prive = productprice.get(0).getPrice();
		if(prive==0){
			modelMap.put("theDate", "0");
			modelMap.put("Itemcounts", "0");
		}
		if(yes==1){
			allsumprice-=prive;
			if(allsumprice==0){
				list.clear();
			}
			int length = list.size();
			if(length==0){
				allsumprice=0;
			}
			modelMap.put("Itemcounts", length);
			modelMap.put("theDates", allsumprice);
		}else{
			if("-18".equals(key)){
				
			
//			int isnotaddprice=0;
//			for(int id = 0;id<list.size();id++){
//				if(shopCar.getOrderid()==list.get(id)){
//					isnotaddprice++;
//				}else{
//				}
//				
//			}
//			if(isnotaddprice!=0){
//				
//			}else{
//				allsumprice+=prive;
//			}
			if(isaddprvenu==1){
			}else{
				allsumprice+=prive;
			}
			
			
			
			}else{
				allsumprice+=prive;
			}
		if(allsumprice==0){
			list.remove(list);
		}
		int lengthgg = 0;
		lengthgg=list.size();
		modelMap.put("Itemcounts", lengthgg);
		modelMap.put("theDates", allsumprice);
		}
		
		}
//		PssShopCar.set
//		PssShopCar shopCar=pssshopcarDao.selectPssShopCarById(shopcarid);
//		if(lis.size()>0){
//			for(int i=0;i<lis.size();i++){
//				lis.get(i).getProductlevel();
//				PssProductpriceQuery pssProductpriceQuery = new PssProductpriceQuery();
//				pssProductpriceQuery.setProducttype(lis.get(i).getProducttype());
//				pssProductpriceQuery.setProductlevel(lis.get(i).getProductlevel());
//				List<PssProductprice> productprice = productpriceDao.getEveryPrice(pssProductpriceQuery);
//				if(productprice==null){
//					modelMap.put("theDate", "0");
//				}else{
//					long sunprice=0;
//					for(int io=0;io<productprice.size();io++){
//						Long price = productprice.get(i).getPrice();
//						sunprice +=price;
//					}
//					modelMap.put("theDates", priceinit);
//				}
//			}
//		}else{
//			modelMap.put("theDate", "0");
//		}
		//先过去传过来的id
		
		//遍历id
		
		//得到一个id的元数据，通过元数据查询一个价格
	}




}
