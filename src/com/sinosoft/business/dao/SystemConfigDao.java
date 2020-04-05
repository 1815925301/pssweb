package com.sinosoft.business.dao;
/*
 * Powered By liran
 * Since  2016-07-04
 */
import java.util.List;
import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.business.po.query.StudentQuery;
import com.sinosoft.business.po.query.SystemConfigQuery;

public interface SystemConfigDao {
	/**
     *查询全部数据
     */
    public List<SystemConfig> getSystemConfig();
	
    /**
	 * 增加某一条记录
	 */
    public Integer saveSystemConfig(SystemConfig sysConfig);
    
    /**
	 * 删除某一条记录
	 */
	public Integer removeSystemConfig(Long id);

    /**
	 * 修改方法
	 */
	public  Integer updateSystemConfig(SystemConfig systemConfig);
	
	/**
	 * 通过一条id查询到详情
	 */
    public SystemConfig getSysConfigById(Long id);
    
    /**
	 * 通过key和描述查询到列表
	 */
    public SystemConfig getAllDataByKeyDesc(SystemConfig systemConfig);
    /**
	 * 通过一个字段查询到列表
	 * 
	 */
    public SystemConfig getSysConfigByName(String name);
    /**
     * 根据key获取value
     * @author Dylan
     * @param configKey String PSS_SYS_CONFIG表中CONFIGKEY字段
     * @return String PSS_SYS_CONFIG表中CONFIGVALUE字段 ，结果为null时返回""
     * @date 16-09-06 10:11:35
     */
    public String getConfigValueByName(String configKey);
    
    /**
     *新的框架根据分页查询到一页显示的数据
     */
    public List<SystemConfig> getSystemConfigList(SystemConfigQuery systemConfigQuery);
    
    /**
     *含有(条件查询)到一页显示的符合条件的数据
     */
    public List<SystemConfig> getSystemConfigList(int pageFirst, int pageSize,SystemConfig systemConfig);


    /**
     *按条件查询符合条件的有多少条
     */
    public Integer getPageSize(String sql);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(SystemConfigQuery systemConfigQuery);
     
     /***
      * 查询配置类型
      */
     public List getsystemtypeList();
}