/*
 * Powered By 尹力
 * Since 2015 - 2016-58-26
 */

package com.sinosoft.business.dao;
import java.util.List;



import com.sinosoft.business.po.PssTown;
import com.sinosoft.business.po.query.PssTownQuery;


public interface PssTownDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssTown(PssTown pssTown);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssTown(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssTown(PssTown pssTown);

	/**
	 * 查询方法
	 */
    public List<PssTown> selectPssTownByCity(Long cityCode);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(PssTownQuery pssTownQuery);
    
    /**
     *按条件查询
     */
    public List<PssTown> getPssTownInfoByQuery(PssTownQuery pssTownQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<PssTown> getPssTownListByQuery(PssTownQuery pssTownQuery);

}