/*
 * Powered By 尹力
 * Since 2015 - 2016-54-31
 */

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
import com.sinosoft.business.dao.PssVersionnumDao;
import com.sinosoft.business.po.PssVersionnum;
import com.sinosoft.business.po.query.PssVersionnumQuery;
import com.sinosoft.business.service.PssVersionnumService;
import com.sinosoft.common.web.ActivityModelMap;


;

@Service("versionnumService")
public class PssVersionnumServiceImpl implements PssVersionnumService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PssOrderInfoServiceImpl.class);
	@Resource
	private PssVersionnumDao versionnumDao;

	/**
	 * 页面初始化
	 */
	@Override
	public void PssVersionnumPageInit(HttpServletRequest request,ModelMap model,String method){
		LOGGER.debug("Service层:查询价格版本");
		PssVersionnumQuery versionnumQuery=new PssVersionnumQuery();
		versionnumQuery.setSortBy("id");
		versionnumQuery.setSortType("2");
		if(method.equals("POST")){
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				versionnumQuery.setPage(Integer.parseInt(pageNum));
			}
			String versionnumInput=request.getParameter("versionnumInput");
			if(!StringUtils.isBlank(versionnumInput)){
				versionnumQuery.setVersionnum(versionnumInput);
			}
		}
		List<PssVersionnum> vlist=versionnumDao.getPssVersionnumInfoByQuery(versionnumQuery);
		model.addAttribute("vlist", vlist);
		Integer totalCount=versionnumDao.getCountByQuery(versionnumQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount,
				versionnumQuery.getPageSize(), versionnumQuery.getPage(),
				versionnumQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
	}

	/** 
	 * 创建PssVersionnum
	 **/
	@Override
	public Boolean save(HttpServletRequest request,PssVersionnum pssVersionnum,ActivityModelMap modelMap){
		boolean result =false;
		Integer resultNum=versionnumDao.insertPssVersionnum(pssVersionnum);
		if(resultNum!=null){
			result=true;
			LOGGER.debug("新增成功！");
		}
		return result;
	}
	
	/** 
	 * 更新PssVersionnum
	 **/	
	@Override
    public Boolean update(HttpServletRequest request,PssVersionnum pssVersionnum,ActivityModelMap modelMap){
		boolean result =false;
		Integer resultNum=versionnumDao.updatePssVersionnum(pssVersionnum);
		if(resultNum!=null){
			result=true;
			LOGGER.debug("新增成功！");
		}
		return result;
	}
    
	/** 
	 * 删除PssVersionnum
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
    
	/** 
	 * 根据ID得到PssVersionnum
	 **/
	@Override   
    public PssVersionnum getPssVersionnum(HttpServletRequest request,ActivityModelMap modelMap){
    	
    	return null;
    }
}
