/*
 * Powered By 尹力
 * Since 2015 - 2016-13-25
 */

package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.SysUserset;
import com.sinosoft.business.po.query.SysUsersetQuery;



public interface SysUsersetDao {
	
	/**
	 * 新增方法
	 */
	public int insertSysUserset(SysUserset SYS_USERSET);
    
    
	/**
	 * 删除方法
	 */
	public int deleteSysUserset(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updateSysUserset(SysUserset SYS_USERSET);

	/**
	 * 查询方法
	 */
    public SysUserset selectSysUsersetById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(SysUsersetQuery SYS_USERSETQuery);
    
    /**
     *按条件查询
     */
    public List<SysUserset> getSysUsersetInfoByQuery(SysUsersetQuery SYS_USERSETQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<SysUserset> getSysUsersetListByQuery(SysUsersetQuery SYS_USERSETQuery);


	public List<SysUserset> selectallname();


	public SysUserset getUserset();

}