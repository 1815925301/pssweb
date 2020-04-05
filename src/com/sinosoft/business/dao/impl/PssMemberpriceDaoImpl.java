/*
 * Powered By 尹力
 * Since 2015 - 2016-01-10
 */

package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssMemberpriceDao;
import com.sinosoft.business.po.PssMemberprice;
import com.sinosoft.business.po.query.PssMemberpriceQuery;



@Repository(value="PSS_MEMBERPRICEDao")
public class PssMemberpriceDaoImpl extends BaseDao implements PssMemberpriceDao{
	
	@Override
	public int insertPssMemberprice(PssMemberprice PSS_MEMBERPRICE){
		return insert("PSS_MEMBERPRICEDao.insertPssMemberprice",PSS_MEMBERPRICE);
	}
	
	@Override
	public int deletePssMemberprice(Long id){
		return delete("PSS_MEMBERPRICEDao.deletePssMemberprice",id);
	}
	
	@Override
	public  int updatePssMemberprice(PssMemberprice PSS_MEMBERPRICE){
		return update("PSS_MEMBERPRICEDao.updatePssMemberprice",PSS_MEMBERPRICE);
	}
	
	@Override
	public PssMemberprice selectPssMemberpriceById(Long id){
		return (PssMemberprice)getReadSqlSession().selectOne("PSS_MEMBERPRICEDao.getPssMemberpriceById",id);
	}
	
	@Override
	public Integer getCountByQuery(PssMemberpriceQuery PSS_MEMBERPRICEQuery){
		return getReadSqlSession().selectOne("PSS_MEMBERPRICEDao.getCountByQuery",PSS_MEMBERPRICEQuery);
	}
	
	@Override
	public List<PssMemberprice> getPssMemberpriceInfoByQuery(PssMemberpriceQuery PSS_MEMBERPRICEQuery){
		return getReadSqlSession().selectList("PSS_MEMBERPRICEDao.getPssMemberpriceInfoByQuery",PSS_MEMBERPRICEQuery);
	}
	
	@Override
	public List<PssMemberprice> getPssMemberpriceListByQuery(PssMemberpriceQuery PSS_MEMBERPRICEQuery){
		return getReadSqlSession().selectList("PSS_MEMBERPRICEDao.getPssMemberpriceListByQuery",PSS_MEMBERPRICEQuery);
	}

	@Override
	public List<PssMemberprice> getPssMemberpriceInfo() {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectList("PSS_MEMBERPRICEDao.getPssMemberpriceList");
	}
}
