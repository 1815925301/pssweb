/*
 * Powered By 尹力
 * Since 2015 - 2016-56-25
 */

package com.sinosoft.business.service.impl;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.business.dao.PssMetadata0sceneDao;
import com.sinosoft.business.po.PssMetadata0scene;
import com.sinosoft.business.service.PssMetadata0sceneService;

@Service("PSS_METADATA_0_SCENEService")
public class PssMetadata0sceneServiceImpl implements PssMetadata0sceneService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PssMetadata0sceneServiceImpl.class);
	@Resource
	private PssMetadata0sceneDao pssMetadata0sceneDao;

	/**
	 * 页面初始化
	 */
	@Override
	public void PssMetadata0scenePageInit(HttpServletRequest request,ModelMap model,String method){
		
	}
	
    
	/** 
	 * 根据ID得到PssMetadata0scene
	 **/
	@Override
	public PssMetadata0scene getPssMetadata0scene(HttpServletRequest request,ModelMap model, String string) {
		PssMetadata0scene pssMetadata0scene = new PssMetadata0scene();
		//点击拇指图查看Level0Scene详情
		String sceneid=request.getParameter("sceneid");
		pssMetadata0scene.setSceneid(sceneid);
		pssMetadata0scene = pssMetadata0sceneDao.selectPssMetadata0sceneQueryById(pssMetadata0scene);
		return pssMetadata0scene;
	}

}
