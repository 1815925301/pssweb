package com.sinosoft.security.dao;

import java.util.List;

import com.sinosoft.security.po.ResourcesSub;

/**
 * @Package com.sinosoft.security.dao
 * @ClassName: ResourcesSubDao
 * @Description: 系统子资源信息 Dao 接口类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-7 下午06:14:45
 */
public interface ResourcesSubDao {
	
	/**
	 * 根据资源id集合 获取子资源信息集合
	 * @param resourceIdList
	 * @return List<ResourcesSub>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-7 下午06:17:54
	 * @version V1.0
	 */
	public List<ResourcesSub> getSubResourcesByRIdList(List<Long> resourceIdList);

}
