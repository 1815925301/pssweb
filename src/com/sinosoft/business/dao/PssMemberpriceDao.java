/*
 * Powered By 尹力
 * Since 2015 - 2016-01-10
 */

package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.PssMemberprice;
import com.sinosoft.business.po.query.PssMemberpriceQuery;


public interface PssMemberpriceDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssMemberprice(PssMemberprice PSS_MEMBERPRICE);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssMemberprice(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssMemberprice(PssMemberprice PSS_MEMBERPRICE);

	/**
	 * 查询方法
	 */
    public PssMemberprice selectPssMemberpriceById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(PssMemberpriceQuery PSS_MEMBERPRICEQuery);
    
    /**
     *按条件查询
     */
    public List<PssMemberprice> getPssMemberpriceInfoByQuery(PssMemberpriceQuery PSS_MEMBERPRICEQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<PssMemberprice> getPssMemberpriceListByQuery(PssMemberpriceQuery PSS_MEMBERPRICEQuery);

    /**
     * 查询所有,没有条件
     * @return
     */
	public List<PssMemberprice> getPssMemberpriceInfo();

}