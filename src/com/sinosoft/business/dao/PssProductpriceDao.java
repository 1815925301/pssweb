/*
 * Powered By 尹力
 * Since 2015 - 2016-55-28
 */

package com.sinosoft.business.dao;
import java.util.List;
import java.util.Map;

import com.sinosoft.business.po.PssProductprice;
import com.sinosoft.business.po.query.PssProductpriceQuery;



public interface PssProductpriceDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssProductprice(PssProductprice PSS_PRODUCTPRICE);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssProductprice(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssProductprice(PssProductprice PSS_PRODUCTPRICE);

	/**
	 * 查询方法
	 */
    public PssProductprice selectPssProductpriceById(Long id);
	
	/**
	 * 通过版本号通过的来查询价格
	 */
	public List<PssProductprice> getPriceThough();
    /**
     * 查询数量
     */
     public Integer getCountByQuery(PssProductpriceQuery PSS_PRODUCTPRICEQuery);
    
    /**
     *按条件查询
     */
    public List<PssProductprice> getPssProductpriceInfoByQuery(PssProductpriceQuery PSS_PRODUCTPRICEQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<PssProductprice> getPssProductpriceListByQuery(PssProductpriceQuery PSS_PRODUCTPRICEQuery);
    /**
     *查询版本数
     */
	public List<PssProductprice> getversionnumList(
			PssProductpriceQuery productpriceQuery);


	public List<PssProductprice> getProductpriceByVersion(String versionnum);
	/***
	 * 通过产品类型和产品级别查询价格
	 * @param pssProductpriceQuery
	 * @return
	 */
	public  List<PssProductprice> getEveryPrice(PssProductpriceQuery pssProductpriceQuery);


	public PssProductprice getPriceByLevel(String collection);
}