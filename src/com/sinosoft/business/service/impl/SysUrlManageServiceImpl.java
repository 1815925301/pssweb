/*
 * Powered By 尹力
 * Since 2015 - 2016-18-08
 */

package com.sinosoft.business.service.impl;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.SysUrlManageDao;
import com.sinosoft.business.po.PssCollectInfo;
import com.sinosoft.business.po.SysUrlManage;
import com.sinosoft.business.po.query.SysUrlManageQuery;
import com.sinosoft.business.service.SysUrlManageService;
import com.sinosoft.common.web.ActivityModelMap;

@Service("SysUrlManageService")
public class SysUrlManageServiceImpl implements SysUrlManageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysUrlManageServiceImpl.class);
	
	@Resource
	private SysUrlManageDao sysUrlManageDao;
	/**
	 * 页面初始化
	 */
	@Override
	public void SysUrlManagePageInit(HttpServletRequest request,ModelMap model,String method){
		LOGGER.debug("Service层：根据查询参数获取信息 用于URL管理页面");
		SysUrlManageQuery sysUrlManageQuery = new SysUrlManageQuery();
		sysUrlManageQuery.setSortBy("id");
		sysUrlManageQuery.setSortType("1");
		String QTDQY=request.getParameter("pageNumInput");
		if (QTDQY == null){
			System.out.println(1);
			sysUrlManageQuery.setPage(1);
		}else{
			
			int DQY =Integer.parseInt(QTDQY);
			sysUrlManageQuery.setPage(DQY);
		}
		Integer totalCount = sysUrlManageDao.getCountByQuery(sysUrlManageQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, sysUrlManageQuery.getPageSize(), 
				sysUrlManageQuery.getPage(), sysUrlManageQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<SysUrlManage> urlList = sysUrlManageDao.getSysUrlManageInfoByQuery(sysUrlManageQuery);
		
		model.addAttribute("urlList", urlList);
	}

	/** 
	 * 创建SysUrlManage
	 **/
	@Override
	public Boolean save(HttpServletRequest request,SysUrlManage sysUrlManage,ActivityModelMap modelMap){
		
		return false;
	}
	
	/** 
	 * 更新SysUrlManage
	 **/	
	@Override
    public Boolean update(HttpServletRequest request,SysUrlManage sysUrlManage,ActivityModelMap modelMap){
		
		return false;
	}
    
	/** 
	 * 删除SysUrlManage
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
    
	/** 
	 * 根据ID得到SysUrlManage
	 **/
	@Override   
    public SysUrlManage getSysUrlManage(HttpServletRequest request,ActivityModelMap modelMap){
    	
    	return null;
    }
}
