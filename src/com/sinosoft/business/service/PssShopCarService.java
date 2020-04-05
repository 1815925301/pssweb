package com.sinosoft.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssShopCar;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssShopCarService {
	
	/**
	 * 页面初始化
	 */
	public void PssShopCarPageInit(ModelMap model, String method, HttpServletRequest request);

	/** 
	 * 创建PssShopCar
	 **/
	public Boolean save(HttpServletRequest request,PssShopCar pssshopcar,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssShopCar
	 **/	
    public Boolean update(HttpServletRequest request,PssShopCar pssshopcar,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssShopCar
	 * @return 
	 **/
    public boolean remove(HttpServletRequest request,ActivityModelMap modelMap);
    
    /** 
	 * 清空购物车
	 * @return 
	 **/
    public boolean removed(HttpServletRequest request,ActivityModelMap modelMap,String df);
    
	/** 
	 * 根据ID得到PssShopCar
	 **/    
    public void getPssShopCar(HttpServletRequest request,ActivityModelMap modelMap);

	public void getPssShopCarForInitPage(ModelMap model, String method,
			HttpServletRequest request);

	public void getPssShopCarinfo(HttpServletRequest request,
			ModelMap model);
	
	/***
	 * 通过选择多选框的条数显示总价多少
	 * @param request
	 * @param modelMap
	 * @return
	 */
	public void selectShopCarOrderPrice(HttpServletRequest request,
			ActivityModelMap modelMap);

	public List<PssShopCar> selectShopCar(HttpServletRequest request,
			ActivityModelMap modelMap);
	
	public Integer getShopcarcontol(ModelMap model, String method,
			HttpServletRequest request);
	
	public void selevtIdOfDate(HttpServletRequest request, ActivityModelMap actmap, String date);

    
}
