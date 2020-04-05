package com.sinosoft.security.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.security.dao.ConstantDao;
import com.sinosoft.security.po.Constant;
import com.sinosoft.security.po.ConstantChild;
import com.sinosoft.security.po.query.ConstantQuery;

/**
 * 常量表维护
* @ClassName: ConstantDao 
* @Description: TODO
* @author zzq
* @mail zzq1229@126.com 
* @date 2014年5月16日 下午4:05:49 
*
 */
@Repository(value="constantDao")
public class ConstantDaoImpl extends BaseDao implements ConstantDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantDaoImpl.class);
	
	@Override
	public Constant selectByPrimaryKey(Long id) {
		LOGGER.debug("Dao层：根据id获取常量主表信息");
		return getReadSqlSession().selectOne("constantDao.selectByPrimaryKey", id);
	}
	
	@Override
	public Integer creatConstantTable(Constant constant) {
		LOGGER.debug("Dao层：创建常量表");
		return (Integer)insert("constantDao.creatConstantTable", constant);
	}

	@Override
	public Integer deleteByPrimaryKey(Long id) {
		LOGGER.debug("Dao层：删除常量主表信息");
		return (Integer)delete("constantDao.deleteByPrimaryKey", id);
	}

	@Override
	public Integer insertConstant(Constant constant) {
		LOGGER.debug("Dao层：新增常量主表信息");
		return (Integer)insert("constantDao.insertSelective", constant);
	}

	@Override
	public Integer updateByPrimaryKeySelective(Constant constant) {
		LOGGER.debug("Dao层：更新常量表信息");
		return (Integer)update("constantDao.updateByPrimaryKeySelective", constant);
	}

	@Override
	public List<Constant> selectByQuery(ConstantQuery constantQuery) {
		LOGGER.debug("Dao层：根据条件查询常量表数据");
		return getReadSqlSession().selectList("constantDao.selectByQuery", constantQuery);
	}

	@Override
	public Integer selectCountByQuery(ConstantQuery constantQuery) {
		LOGGER.debug("Dao层：根据条件查询常量表数量");
		return getReadSqlSession().selectOne("constantDao.selectCountByQuery", constantQuery);
	}

}
