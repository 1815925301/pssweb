/*
 * Powered By 尹力
 * Since 2015 - 2017-40-17
 */

package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.PssRefund;
import com.sinosoft.business.po.query.PssRefundQuery;


public interface PssRefundDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssRefund(PssRefund PSS_REFUND);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssRefund(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssRefund(PssRefund PSS_REFUND);

	/**
	 * 查询方法
	 */
    public PssRefund selectPssRefundById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(PssRefundQuery PSS_REFUNDQuery);
    
    /**
     *按条件查询
     */
    public List<PssRefund> getPssRefundInfoByQuery(PssRefundQuery PSS_REFUNDQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<PssRefund> getPssRefundListByQuery(PssRefundQuery PSS_REFUNDQuery);


	public PssRefund selectPssRefundByColl(Long collectid);

}