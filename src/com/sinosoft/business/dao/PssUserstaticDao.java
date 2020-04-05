/*
 * Powered By 尹力
 * Since 2015 - 2016-34-20
 */

package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.PssUserstatic;
import com.sinosoft.business.po.query.PssUserstaticQuery;



public interface PssUserstaticDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssUserstatic(PssUserstatic PSS_USERSTATIC);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssUserstatic(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssUserstatic(PssUserstatic PSS_USERSTATIC);

	/**
	 * 查询方法
	 */
    public PssUserstatic selectPssUserstaticById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(PssUserstaticQuery PSS_USERSTATICQuery);
    
    /**
     *按条件查询
     */
    public List<PssUserstatic> getPssUserstaticInfoByQuery(PssUserstaticQuery PSS_USERSTATICQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<PssUserstatic> getPssUserstaticListByQuery(PssUserstaticQuery PSS_USERSTATICQuery);

}