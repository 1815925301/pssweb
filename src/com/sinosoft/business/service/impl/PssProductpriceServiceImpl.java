
package com.sinosoft.business.service.impl;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.PssProductpriceDao;
import com.sinosoft.business.po.PssProductprice;
import com.sinosoft.business.po.query.PssProductpriceQuery;
import com.sinosoft.business.service.PssProductpriceService;
import com.sinosoft.common.web.ActivityModelMap;

/**
 * @Package com.sinosoft.business.service.impl
 * @ClassName: PssProductpriceServiceImpl
 * @Description: 产品价格信息  MVC业务层web入口
 * @author wlg
 * @Version V1.0
 * @date 2016-08-05
 */

@Service("productpriceService")
public class PssProductpriceServiceImpl implements PssProductpriceService {
	@Resource
	private PssProductpriceDao productpriceDao;
	private static final Logger LOGGER = LoggerFactory.getLogger(PssOrderInfoServiceImpl.class);
	/**
	 * 产品价格管理页面
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author wlg
	 * @date 2016-08-05 
	 * @version V1.0
	 */
	@Override
	public void PssProductpricePageInit(HttpServletRequest request,ModelMap model,String method){
		LOGGER.debug("Service层:查询订单");
		PssProductpriceQuery productpriceQuery=new PssProductpriceQuery();
		productpriceQuery.setSortBy("id");
		productpriceQuery.setSortType("-1");
		if(method.equals("POST")){
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				productpriceQuery.setPage(Integer.parseInt(pageNum));
			}
			String versionnumInput=request.getParameter("versionnumInput");
			if(! StringUtils.isBlank(versionnumInput)){
//				productpriceQuery.setVersionnum(versionnumInput);
				model.addAttribute("versionnumInput", versionnumInput);
			}
		}
		List<PssProductprice> versionnumList=productpriceDao.getversionnumList(productpriceQuery);
		model.addAttribute("versionnumList", versionnumList);
		Integer totalCount=versionnumList.size();
		TotalInfo totalInfo = new TotalInfo(totalCount,
				productpriceQuery.getPageSize(), productpriceQuery.getPage(),
				productpriceQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
	}

	/**
	 * 产品价格保存新增
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author wlg
	 * @date 2016-08-05 
	 * @version V1.0
	 */
	@Override
	public Boolean save(HttpServletRequest request,PssProductprice pssProductprice,ActivityModelMap modelMap){
		boolean result =false;
		Integer resultNum=productpriceDao.insertPssProductprice(pssProductprice);
		if(resultNum!=null){
			result=true;
			LOGGER.debug("新增成功！");
		}
		return result;
	}
	
	/**
	 * 产品价格发布
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author wlg
	 * @date 2016-08-05 
	 * @version V1.0
	 */	
	@Override
    public Boolean update(HttpServletRequest request,PssProductprice pssProductprice,ActivityModelMap modelMap){
		boolean result = false;
		Integer resultNumber=productpriceDao.updatePssProductprice(pssProductprice);
		if(resultNumber != null){
			result =true;
			LOGGER.debug("发布成功！");
		}else{
			result=false;
		}
		return result;
	
	}
    
	/**
	 * 产品价格查看
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author wlg
	 * @date 2016-08-05 
	 * @version V1.0
	 */
	@Override
	public List<PssProductprice> getProductpriceByVersion(String versionnum) {
		LOGGER.debug("Service层:查询订单");
		return productpriceDao.getProductpriceByVersion(versionnum);
	}

}
