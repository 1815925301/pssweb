package com.sinosoft.security.service;

import java.util.List;

import com.sinosoft.security.po.ResourcesSub;

/**
 * @Package com.sinosoft.security.service
 * @ClassName: ResourcesSubService
 * @Description: 系统子资源信息 服务层接口类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-7 下午06:13:18
 */
public interface ResourcesSubService {
	
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
