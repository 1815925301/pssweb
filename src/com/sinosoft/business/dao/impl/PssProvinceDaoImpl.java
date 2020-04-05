

package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssProvinceDao;
import com.sinosoft.business.po.PssProvince;
import com.sinosoft.business.po.query.PssProvinceQuery;

/**
 * @Package com.sinosoft.business.dao.impl
 * @ClassName: ProvinceDaoImpl
 * @Description: 处理省份信息的DAO 实现类
 * @author hao
 * @date 2013-10-2 下午07:15:48
 */
@Repository(value="PssProvinceDao")
public class PssProvinceDaoImpl extends BaseDao implements PssProvinceDao{
	
	/*@Override
	public int insertPssProvince(PssProvince PssProvince){
		return insert("PssProvinceDao.insertPssProvince",PssProvince);
	}*/
	
	/*@Override
	public int deletePssProvince(Long id){
		return delete("PssProvinceDao.deletePssProvince",id);
	}
	*/
	@Override
	public  int updatePssProvince(PssProvince PssProvince){
		return update("PssProvinceDao.updatePssProvince",PssProvince);
	}
	
	/*@Override
	public PssProvince selectPssProvinceById(Long id){
		return (PssProvince)getReadSqlSession().selectOne("PssProvinceDao.getPssProvinceById",id);
	}*/
	@Override
	public List<PssProvince> selectPssProvinceByCountry(Long countryCode){
		return getReadSqlSession().selectList("PssProvinceDao.getPssProvinceByCountry",countryCode);
		
	}
	/*@Override
	public Integer getCountByQuery(PssProvinceQuery PssProvinceQuery){
		return getReadSqlSession().selectOne("PssProvinceDao.getCountByQuery",PssProvinceQuery);
	}*/
	
	/*@Override
	public List<PssProvince> getPssProvinceInfoByQuery(PssProvinceQuery PssProvinceQuery){
		return getReadSqlSession().selectList("PssProvinceDao.getPssProvinceInfoByQuery",PssProvinceQuery);
	}
	*/
	/*@Override
	public List<PssProvince> getPssProvinceListByQuery(PssProvinceQuery PssProvinceQuery){
		return getReadSqlSession().selectList("PssProvinceDao.getPssProvinceListByQuery",PssProvinceQuery);
	}*/
}
