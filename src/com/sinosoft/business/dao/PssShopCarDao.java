package com.sinosoft.business.dao;
import java.util.List;
import java.util.Map;

import com.sinosoft.business.po.PssShopCar;
import com.sinosoft.business.po.query.PssShopCarQuery;

/**
 * 购物车Dao接口
 * 
 * @author
 * @version 1.0
 * @since 1.0
 * */
public interface PssShopCarDao {

	/**
	 * 新增方法
	 */
	public int insertPssShopCar(PssShopCar pssshopcar);

	/**
	 * 删除方法
	 */
	public int deletePssShopCar(PssShopCar pssshopcar);

	/**
	 * 根据id删除购物车信息
	 * 
	 * @param orderIds
	 *            String 订单id，多个id请以“,”拼接
	 * @return
	 */
	public int deletePssShopCarByIdS(String orderIds);

	/**
	 * 修改方法
	 */
	public int updatePssShopCar(PssShopCar pssshopcar);

	/**
	 * 查询方法
	 */
	public PssShopCar selectPssShopCarById(PssShopCar pssshopcar);

	/**
	 * 查询数量
	 */
	public Integer getCountByQuery(PssShopCarQuery pssshopcarQuery);

	public Integer geCountcar();


	/**
	 * 根据id查询
	 * 
	 * @author Dylan
	 * @param Ids
	 *            String 以“,”拼接的id字符串
	 * @return
	 */
	public List<Map<String, Object>> findPssShopCarInfoById(String orderIdS);

	/**
	 * 按条件查询
	 */
	public List<PssShopCar> getPssShopCarInfoByQuery(
			PssShopCarQuery pssshopcarQuery);

	/**
	 * 按条件查询 不带分页
	 */
	public List<PssShopCar> getPssShopCarListByQuery(
			PssShopCarQuery pssshopcarQuery);
	
	

	/**
	 * 按时间分组查询
	 */
	public List<Map<String, String>> getPssShopCarListByOrderTime(
			PssShopCarQuery pssshopcarQuery);
	/***
	 * 查询出所有时间
	 * @param pssshopcarQuery
	 * @return
	 */
	public List getordertime(PssShopCarQuery pssshopcarQuery);
	
	/**
	 * 按条件查询购物车
	 */
	public List<PssShopCar> getPssShopCarListall(
			PssShopCarQuery pssshopcarQuery);

}
