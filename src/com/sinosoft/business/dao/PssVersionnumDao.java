/*
 * Powered By 尹力
 * Since 2015 - 2016-54-31
 */

package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.PssVersionnum;
import com.sinosoft.business.po.query.PssVersionnumQuery;



public interface PssVersionnumDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssVersionnum(PssVersionnum PSS_VERSIONNUM);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssVersionnum(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssVersionnum(PssVersionnum PSS_VERSIONNUM);

	/**
	 * 查询方法
	 */
    public PssVersionnum selectPssVersionnumById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(PssVersionnumQuery PSS_VERSIONNUMQuery);
    
    /**
     *按条件查询
     */
    public List<PssVersionnum> getPssVersionnumInfoByQuery(PssVersionnumQuery PSS_VERSIONNUMQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<PssVersionnum> getPssVersionnumListByQuery(PssVersionnumQuery PSS_VERSIONNUMQuery);

}