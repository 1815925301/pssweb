package com.sinosoft.business.dao;

import java.util.List;

import com.sinosoft.business.po.PssProduct;
import com.sinosoft.business.po.PssShopCar;
import com.sinosoft.business.po.query.PssProductQuery;

public interface ProductDao {
	 /**
     *按条件查询
     */
    public List<PssProductQuery> getProductByQuery(PssProductQuery productquery);
    public PssProduct selectProductById(PssProductQuery productquery);
	Integer getCountByQuery(PssProductQuery productQuery);
	public PssProduct QuerySceneProduct(PssProductQuery productQuery);
}
