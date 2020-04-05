package com.sinosoft.security.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.security.dao.ResourcesSubDao;
import com.sinosoft.security.po.ResourcesSub;

/**
 * @Package com.sinosoft.security.dao.impl
 * @ClassName: ResourcesSubDaoImpl
 * @Description: 系统子资源信息 Dao 实现类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-7 下午06:15:57
 */
@Repository(value="resourcesSubDao")
public class ResourcesSubDaoImpl extends BaseDao implements ResourcesSubDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesSubDaoImpl.class);
	
	@Override
	public List<ResourcesSub> getSubResourcesByRIdList(List<Long> resourceIdList) {
		LOGGER.debug("Dao层：根据资源id集合 获取子资源信息集合");
		return getReadSqlSession().selectList("resourcesSubDao.selectSubResourcesByRIdList", resourceIdList);
	}
}
