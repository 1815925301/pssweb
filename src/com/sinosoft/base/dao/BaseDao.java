package com.sinosoft.base.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

/**
 * @Package com.sinosoft.base.dao
 * @ClassName: BaseDao
 * @Description: MyBatis Dao基类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-2 下午07:04:04
 */
public class BaseDao implements Dao {

	@Resource(name = "sinosoftWriteSqlSession")
	private SqlSession writeSqlSession;
	
	@Resource(name = "sinosoftReadSqlSession")
	private SqlSession readSqlSession;
	
	@Resource(name = "sinosoftSdeSqlSession")
	private SqlSession sdeSqlSession;
	
	public SqlSession getReadSqlSession() {
		return readSqlSession;
	}
	
	
	public SqlSession getSdeSqlSession() {
		return sdeSqlSession;
	}

	@Override
	public int insert(String myBatis, Object object) {
		return writeSqlSession.insert(myBatis, object);
	}

	@Override
	public Object select(String myBatis, Object object) {
		return readSqlSession.selectOne(myBatis, object);
	}

	@Override
	public int update(String myBatis, Object object) {
		return writeSqlSession.update(myBatis, object);
	}

	@Override
	public int delete(String myBatis, Object object) {
		return writeSqlSession.delete(myBatis, object);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List selectList(String myBatis, Object object) {
		return readSqlSession.selectList(myBatis, object);
	}

}
