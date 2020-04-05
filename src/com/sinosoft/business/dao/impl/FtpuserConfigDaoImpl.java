
package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.FtpuserConfigDao;
import com.sinosoft.business.po.FtpuserConfig;
import com.sinosoft.business.po.query.FtpuserConfigQuery;


@Repository(value="ftpuserConfigDao")
public class FtpuserConfigDaoImpl extends BaseDao implements FtpuserConfigDao{
	
	@Override
	public int insertFtpuserConfig(FtpuserConfig FTPUSER_CONFIG){
		return insert("FTPUSER_CONFIGDao.insertFtpuserConfig",FTPUSER_CONFIG);
	}
	
	@Override
	public int deleteFtpuserConfig(Long id){
		return delete("FTPUSER_CONFIGDao.deleteFtpuserConfig",id);
	}
	
	@Override
	public  int updateFtpuserConfig(FtpuserConfig FTPUSER_CONFIG){
		return update("FTPUSER_CONFIGDao.updateFtpuserConfig",FTPUSER_CONFIG);
	}
	
	@Override
	public FtpuserConfig selectFtpuserConfigById(Long id){
		return (FtpuserConfig)getReadSqlSession().selectOne("FTPUSER_CONFIGDao.getFtpuserConfigById",id);
	}
	
	@Override
	public Integer getCountByQuery(FtpuserConfigQuery FTPUSER_CONFIGQuery){
		return getReadSqlSession().selectOne("FTPUSER_CONFIGDao.getCountByQuery",FTPUSER_CONFIGQuery);
	}
	
	@Override
	public List<FtpuserConfig> getFtpuserConfigInfoByQuery(FtpuserConfigQuery FTPUSER_CONFIGQuery){
		return getReadSqlSession().selectList("FTPUSER_CONFIGDao.getFtpuserConfigInfoByQuery",FTPUSER_CONFIGQuery);
	}
	
	@Override
	public List<FtpuserConfig> getFtpuserConfigListByQuery(FtpuserConfigQuery FTPUSER_CONFIGQuery){
		return getReadSqlSession().selectList("FTPUSER_CONFIGDao.getFtpuserConfigListByQuery",FTPUSER_CONFIGQuery);
	}
}
