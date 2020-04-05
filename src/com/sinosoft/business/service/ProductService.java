package com.sinosoft.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssCollectInfo;
import com.sinosoft.business.po.PssProduct;
import com.sinosoft.business.po.PssShopCar;
import com.sinosoft.business.po.query.PssProductQuery;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface ProductService {
	
	/**
	 * 页面初始化
	 */
	public void ProductPageInit(ModelMap model, String method, HttpServletRequest request);

	/** 
	 * 根据ID得到PssShopCar
	 **/    
    public void getProduct(HttpServletRequest request, ModelMap model,String stats);

	public void getProductForInitPage(ActivityModelMap modelMap, String method,
			HttpServletRequest request);

	public void getProductinfo(HttpServletRequest request, ModelMap model);

	public void addSceneshopcar(HttpServletRequest request, ModelMap model);

	public void contProduct(HttpServletRequest request, ModelMap model);
		

    
}
