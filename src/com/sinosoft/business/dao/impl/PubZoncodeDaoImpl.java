package com.sinosoft.business.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PubZoncodeDao;
import com.sinosoft.business.po.PubZoncode;
import com.sinosoft.business.po.query.PubZoncodeQuery;
/**
 * 查询地区编码表数据
* @ClassName: PubZoncodeDaoImpl 
* @Description: TODO
* @author zzq
* @mail zzq1229@126.com 
* @date 2014年8月7日 下午5:22:27 
*
 */
@Repository(value="pubZoncodeDao")
public class PubZoncodeDaoImpl extends BaseDao  implements PubZoncodeDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(PubZoncodeDaoImpl.class);
	
	@Override
	public int deleteByPrimaryKey(String record) {
		LOGGER.debug("Dao层：删除地区编码");
		return (Integer)delete("pubZoncodeDao.deleteByPrimaryKey", record);
	}

	@Override
	public int insertSelective(PubZoncode record) {
		LOGGER.debug("Dao层：新增地区编码");
		return (Integer)insert("pubZoncodeDao.insertSelective", record);
	}

	@Override
	public PubZoncode selectByPrimaryKey(String record) {
		LOGGER.debug("Dao层：根据条件查询地区编码数据");
		return getReadSqlSession().selectOne("pubZoncodeDao.selectByPrimaryKey",  record);
	}

	@Override
	public int updateByPrimaryKeySelective(PubZoncode record) {
		LOGGER.debug("Dao层：更新地区编码数据");
		return (Integer)update("pubZoncodeDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public List<PubZoncode> selectByPrimaryQuery(PubZoncodeQuery record) {
		LOGGER.debug("Dao层：根据条件查询地区编码数据");
		return getReadSqlSession().selectList("pubZoncodeDao.selectPubZoncodeByQuery", record);
	}

}
