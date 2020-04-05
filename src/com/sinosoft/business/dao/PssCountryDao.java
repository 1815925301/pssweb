
package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.PssCountry;
import com.sinosoft.business.po.query.PssCountryQuery;

/**
 * @Package com.sinosoft.business.dao
 * @ClassName: PssCountryDao
 * @Description: 国家信息的DAO 接口类
 * @author hao
 * @date 2016-8-24 
 */

public interface PssCountryDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssCountry(PssCountry pssCountry);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssCountry(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssCountry(PssCountry pssCountry);

	/**
	 * 查询方法
	 */
    public PssCountry selectPssCountryById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(PssCountryQuery pssCountryQuery);
    
    /**
     *按条件查询
     */
    public List<PssCountry> getPssCountryInfoByQuery(PssCountryQuery pssCountryQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<PssCountry> getPssCountryListByQuery(PssCountryQuery pssCountryQuery);

    
}