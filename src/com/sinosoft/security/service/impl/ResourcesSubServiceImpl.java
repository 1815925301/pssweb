package com.sinosoft.security.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sinosoft.security.dao.ResourcesSubDao;
import com.sinosoft.security.po.ResourcesSub;
import com.sinosoft.security.service.ResourcesSubService;

/**
 * @Package com.sinosoft.security.service.impl
 * @ClassName: ResourcesSubServiceImpl
 * @Description: 系统子资源信息 服务层实现类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-7 下午06:13:59
 */
@Service("resourcesSubService")
public class ResourcesSubServiceImpl implements ResourcesSubService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesSubServiceImpl.class);
	
	@Resource
	private ResourcesSubDao resourcesSubDao;
	
	public List<ResourcesSub> getSubResourcesByRIdList(List<Long> resourceIdList) {
		LOGGER.debug("Service层：根据资源id集合 获取子资源信息集合");
		return resourcesSubDao.getSubResourcesByRIdList(resourceIdList);
	}

}
