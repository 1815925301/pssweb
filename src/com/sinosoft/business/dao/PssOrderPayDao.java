/*
 * Powered By 尹力
 * Since 2015 - 2016-16-29
 */

package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.PssOrderPay;
import com.sinosoft.business.po.query.PssOrderPayQuery;


public interface PssOrderPayDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssOrderPay(PssOrderPay PSS_ORDER_PAY);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssOrderPay(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssOrderPay(PssOrderPay PSS_ORDER_PAY);

	/**
	 * 查询方法
	 */
    public PssOrderPay selectPssOrderPayById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(PssOrderPayQuery PSS_ORDER_PAYQuery);
    
    /**
     *按条件查询
     */
    public List<PssOrderPay> getPssOrderPayInfoByQuery(PssOrderPayQuery PSS_ORDER_PAYQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<PssOrderPay> getPssOrderPayListByQuery(PssOrderPayQuery PSS_ORDER_PAYQuery);


	public PssOrderPay selectPssOrderPayByCollId(Long collectid);

}