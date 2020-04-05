/*
 * Powered By 尹力
 * Since 2015 - 2016-28-22
 */

package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.FtpuserConfig;
import com.sinosoft.business.po.query.FtpuserConfigQuery;



public interface FtpuserConfigDao {
	
	/**
	 * 新增方法
	 */
	public int insertFtpuserConfig(FtpuserConfig FTPUSER_CONFIG);
    
    
	/**
	 * 删除方法
	 */
	public int deleteFtpuserConfig(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updateFtpuserConfig(FtpuserConfig FTPUSER_CONFIG);

	/**
	 * 查询方法
	 */
    public FtpuserConfig selectFtpuserConfigById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(FtpuserConfigQuery FTPUSER_CONFIGQuery);
    
    /**
     *按条件查询
     */
    public List<FtpuserConfig> getFtpuserConfigInfoByQuery(FtpuserConfigQuery FTPUSER_CONFIGQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<FtpuserConfig> getFtpuserConfigListByQuery(FtpuserConfigQuery FTPUSER_CONFIGQuery);

}