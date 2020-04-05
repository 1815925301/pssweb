package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.ProductDao;
import com.sinosoft.business.po.PssProduct;
import com.sinosoft.business.po.query.PssProductQuery;
@Repository(value="productDao")
public class ProductDaoImpl extends BaseDao implements ProductDao{
	
	
	@Override
	//空间表的查询
	public List<PssProductQuery> getProductByQuery(PssProductQuery productquery) {
		return  getSdeSqlSession().selectList("PssProductDao.getPssProductByQuery",productquery);
	}

	@Override
	public PssProduct selectProductById(PssProductQuery productquery) {
		return (PssProduct)getReadSqlSession().selectOne("PssProductDao.selectProductById",productquery);
	}
	@Override
	public Integer getCountByQuery(PssProductQuery pssProductQuery){
		return getReadSqlSession().selectOne("PssProductDao.getCountByQuery",pssProductQuery);
	}

	@Override
	public PssProduct QuerySceneProduct(PssProductQuery productQuery) {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectOne("PssProductDao.selectSceneById",productQuery);
	}
    
}
