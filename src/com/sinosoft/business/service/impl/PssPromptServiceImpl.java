/*
 * Powered By 由忠禹
 * Since 2015 - 2016-07-19
 */

package com.sinosoft.business.service.impl;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.PssPromptDao;
import com.sinosoft.business.po.PssPrompt;
import com.sinosoft.business.po.query.PssPromptQuery;
import com.sinosoft.business.service.PssPromptService;
import com.sinosoft.common.web.ActivityModelMap;

@Service("pssPromptService")
public class PssPromptServiceImpl implements PssPromptService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PssPromptServiceImpl.class);
	
	@Resource
	private PssPromptDao pssPromptDao;
	/**
	 * 页面初始化
	 */
	@Override
	public void pssPromptPageInit(HttpServletRequest request,ModelMap model,String method){
		
		LOGGER.debug("Service层：根据查询参数获取信息 用于采集单管理页面");
		PssPromptQuery pssPromptQuery = new PssPromptQuery();
		//以id升序排序
		pssPromptQuery.setSortBy("id");
		pssPromptQuery.setSortType("2");
		if (method.equals("POST")) {
					String pageNum = request.getParameter("pageNumInput");
					if (! StringUtils.isBlank(pageNum)) {
						pssPromptQuery.setPage(Integer.parseInt(pageNum));
						model.addAttribute("pageNumInput", pageNum);
					}
					String desSearch=request.getParameter("desSearch");
					if(!StringUtils.isBlank(desSearch)){
						pssPromptQuery.setDes(desSearch);
						model.addAttribute("desSearch", desSearch);
					}
				}
		Integer totalCount = pssPromptDao.getCountByQuery(pssPromptQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, pssPromptQuery.getPageSize(), 
				pssPromptQuery.getPage(), pssPromptQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<PssPrompt> promptList = pssPromptDao.getPssPromptByQuery(pssPromptQuery);
		
		model.addAttribute("promptList", promptList);
	}
	

	/** 
	 * 创建友好提示
	 **/
	@Override
	public Boolean savePrompt(HttpServletRequest request,PssPrompt pssPrompt,ActivityModelMap modelMap){
		boolean result = false;
		Integer resultNum = pssPromptDao.insertPssPrompt(pssPrompt);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}
	
	/**
	 * 通过一条id查询到详情
	 */
	@Override
	public void getPromptById(ActivityModelMap modelMap,
			HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			Long id = Long.parseLong(request.getParameter("id"));
			PssPromptQuery PssPromptQuery = new PssPromptQuery();
			PssPrompt pssPrompt = pssPromptDao.selectPssPromptById(id);
			modelMap.put("pssPrompt", pssPrompt);
		}
	}
	/** 
	 * 更新PssPrompt
	 **/	
	@Override
    public Boolean updatePrompt(HttpServletRequest request,PssPrompt pssPrompt,ActivityModelMap modelMap){
		boolean result = false;
		Integer resultNum = pssPromptDao.updatePssPrompt(pssPrompt);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}

		return result;
	}
    
	/** 
	 * 删除PssPrompt
	 **/
	@Override
    public boolean remove(Long id,HttpServletRequest request,ActivityModelMap modelMap){
		boolean result = false;
		Integer resultNum = pssPromptDao.deletePssPrompt(id);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return true;
		
	}

}
