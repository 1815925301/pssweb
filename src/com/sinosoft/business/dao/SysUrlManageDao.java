/*
 * Powered By J.C
 * Since 2016-08-11
 */

package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.SysUrlManage;
import com.sinosoft.business.po.query.SysUrlManageQuery;



public interface SysUrlManageDao {
	
	/**
	 * 新增方法
	 */
	public int insertSysUrlManage(SysUrlManage sysUrlManage);
    
    
	/**
	 * 删除方法
	 */
	public int deleteSysUrlManage(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updateSysUrlManage(SysUrlManage sysUrlManage);

	/**
	 * 查询方法
	 */
    public SysUrlManage selectSysUrlManageById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(SysUrlManageQuery sysUrlManageQuery);
    
    /**
     *按条件查询
     */
    public List<SysUrlManage> getSysUrlManageInfoByQuery(SysUrlManageQuery sysUrlManageQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<SysUrlManage> getSysUrlManageListByQuery(SysUrlManageQuery sysUrlManageQuery);

}