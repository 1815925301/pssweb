
package com.sinosoft.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssProductpriceDao;
import com.sinosoft.business.po.PssProductprice;
import com.sinosoft.business.po.query.PssProductpriceQuery;

/**
 * @Package com.sinosoft.business.dao.impl
 * @ClassName: PssProductpriceDaoImpl
 * @Description: 产品价格信息  MVC数据层web入口
 * @author wlg
 * @Version V1.0
 * @date 2016-08-05
 */

@Repository(value="productpriceDao")
public class PssProductpriceDaoImpl extends BaseDao implements PssProductpriceDao{
	
	@Override
	public int insertPssProductprice(PssProductprice PSS_PRODUCTPRICE){
		return insert("PSS_PRODUCTPRICEDao.insertPssProductprice",PSS_PRODUCTPRICE);
	}
	
	@Override
	public int deletePssProductprice(Long id){
		return delete("PSS_PRODUCTPRICEDao.deletePssProductprice",id);
	}
	
	@Override
	public  int updatePssProductprice(PssProductprice PSS_PRODUCTPRICE){
		return update("PSS_PRODUCTPRICEDao.updatePssProductprice",PSS_PRODUCTPRICE);
	}
	
	@Override
	public PssProductprice selectPssProductpriceById(Long id){
		return (PssProductprice)getReadSqlSession().selectOne("PSS_PRODUCTPRICEDao.getPssProductpriceById",id);
	}
	
	@Override
	public Integer getCountByQuery(PssProductpriceQuery PSS_PRODUCTPRICEQuery){
		return getReadSqlSession().selectOne("PSS_PRODUCTPRICEDao.getCountByQuery",PSS_PRODUCTPRICEQuery);
	}
	
	@Override
	public List<PssProductprice> getPssProductpriceInfoByQuery(PssProductpriceQuery PSS_PRODUCTPRICEQuery){
		return getReadSqlSession().selectList("PSS_PRODUCTPRICEDao.getPssProductpriceInfoByQuery",PSS_PRODUCTPRICEQuery);
	}
	
	@Override
	public List<PssProductprice> getPssProductpriceListByQuery(PssProductpriceQuery PSS_PRODUCTPRICEQuery){
		return getReadSqlSession().selectList("PSS_PRODUCTPRICEDao.getPssProductpriceListByQuery",PSS_PRODUCTPRICEQuery);
	}

	@Override
	public List<PssProductprice> getversionnumList(
			PssProductpriceQuery productpriceQuery) {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectList("PSS_PRODUCTPRICEDao.getPssProductpriceInfoByQuery",productpriceQuery);
	}

	@Override
	public List<PssProductprice> getProductpriceByVersion(String versionnum) {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectList("PSS_PRODUCTPRICEDao.getProductpriceByVersion",versionnum);
	}

	@Override
	public List<PssProductprice> getEveryPrice(
			PssProductpriceQuery pssProductpriceQuery) {
		return getReadSqlSession().selectList("PSS_PRODUCTPRICEDao.getEveryPrice",pssProductpriceQuery);
	}

	@Override
	public List<PssProductprice> getPriceThough() {
		return getReadSqlSession().selectList("PSS_PRODUCTPRICEDao.getPriceThough");
	}

	@Override
	public PssProductprice getPriceByLevel(String collection) {
		// TODO Auto-generated method stub
		return  getReadSqlSession().selectOne("PSS_PRODUCTPRICEDao.getpricebyLevel", collection);
	}
}
