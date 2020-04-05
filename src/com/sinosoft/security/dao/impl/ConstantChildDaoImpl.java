package com.sinosoft.security.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.security.dao.ConstantChildDao;
import com.sinosoft.security.po.Constant;
import com.sinosoft.security.po.ConstantChild;
import com.sinosoft.security.po.extend.ExtendConstant;
import com.sinosoft.security.po.query.ConstantChildQuery;


/**
 * 常量子表维护
* @ClassName: ConstantChild 
* @Description: TODO
* @author zzq
* @mail zzq1229@126.com 
* @date 2014年5月16日 下午4:07:16 
*
 */
@Repository(value="constantChildDao")
public class ConstantChildDaoImpl extends BaseDao implements ConstantChildDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantChildDaoImpl.class);
	
	@Override
	public ExtendConstant selectByPrimaryKey(Long id) {
		LOGGER.debug("Dao层：根据id获取常量主表信息");
		return getReadSqlSession().selectOne("constantChildDao.selectByPrimaryKey", id);
	}

	@Override
	public Integer deleteByPrimaryKey(ConstantChild constantChild) {
		LOGGER.debug("Dao层：删除常量主表信息");
		return (Integer)delete("constantChildDao.deleteByPrimaryKey", constantChild);
	}

	@Override
	public Integer insertSelective(ConstantChild constantChild) {
		LOGGER.debug("Dao层：新增常量主表信息");
		return (Integer)insert("constantChildDao.insertSelective", constantChild);
	}

	@Override
	public Integer updateByPrimaryKeySelective(ConstantChild constantChild) {
		LOGGER.debug("Dao层：更新常量表信息");
		return (Integer)update("constantChildDao.updateByPrimaryKeySelective", constantChild);
	}

	@Override
	public List<ConstantChild> selectByQuery(ConstantChildQuery constantChildQuery) {
		LOGGER.debug("Dao层：根据条件查询常量表数据");
		return getReadSqlSession().selectList("constantChildDao.selectByQuery", constantChildQuery);
	}

	@Override
	public Integer selectCountByQuery(ConstantChildQuery constantChildQuery) {
		LOGGER.debug("Dao层：根据条件查询常量表数量");
		return getReadSqlSession().selectOne("constantChildDao.selectCountByQuery", constantChildQuery);
	}

	@Override
	public Integer selectAllCountByQuery(ConstantChildQuery constantChildQuery) {
		LOGGER.debug("Dao层：查询常量总数");
		return getReadSqlSession().selectOne("constantChildDao.selectAllCountByQuery", constantChildQuery);
	}

	@Override
	public List<ExtendConstant> selectAllByQuery(
			ConstantChildQuery constantChildQuery) {
		LOGGER.debug("Dao层：根据条件查询常量表数据");
		return getReadSqlSession().selectList("constantChildDao.selectAllByQuery", constantChildQuery);
	}

	@Override
	public List<ConstantChild> selectConstValueByTableName(ConstantChild constantChild) {
		LOGGER.debug("Dao层：根据条件查询常量表数据");
		return getReadSqlSession().selectList("constantChildDao.selectConstValueByTableName", constantChild);
	}

}
