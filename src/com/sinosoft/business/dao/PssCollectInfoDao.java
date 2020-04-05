/*
 * Powered By 尹力
 * Since 2015 - 2016-53-18
 */

package com.sinosoft.business.dao;
import java.util.List;
import com.sinosoft.business.po.PssCollectInfo;
import com.sinosoft.business.po.query.PssCollectInfoQuery;


public interface PssCollectInfoDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssCollectInfo(PssCollectInfo PSS_COLLECT_INFO);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssCollectInfo(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssCollectInfo(PssCollectInfo PSS_COLLECT_INFO);

	/**
	 * 查询方法
	 */
    public PssCollectInfo selectPssCollectInfoById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(PssCollectInfoQuery PSS_COLLECT_INFOQuery);
    
    /**
     *按条件查询
     */
    public List<PssCollectInfo> getPssCollectInfoInfoByQuery(PssCollectInfoQuery PSS_COLLECT_INFOQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<PssCollectInfo> getPssCollectInfoListByQuery(PssCollectInfoQuery PSS_COLLECT_INFOQuery);

    /**
     *查询卫星种类
     */

	public List getSatelliteList();
	public List getStatusList();
	public List getSensoridList();


	public PssCollectInfo getCollectInfoBytaskid(Long taskid);
}