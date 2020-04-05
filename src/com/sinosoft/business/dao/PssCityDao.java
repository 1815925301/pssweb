
package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.PssCity;
import com.sinosoft.business.po.PssProvince;
import com.sinosoft.business.po.query.PssCityQuery;
/**
 * @Package com.sinosoft.business.dao
 * @ClassName: PssCityDao
 * @Description: 城市信息的DAO 接口类
 * @author hao
 * @date 2016-8-24 
 */


public interface PssCityDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssCity(PssCity pssCity);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssCity(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssCity(PssCity pssCity);

	/**
	 * 查询方法
	 */
   /* public PssCity selectPssCityById(Long id);*/
    
    /**
     * 查询数量
     */
     /*public Integer getCountByQuery(PssCityQuery pssCityQuery);*/
     
     /**
 	 * 查询方法,通过省份名查询城市
 	 */
     public List<PssCity> selectPssCityByProvince(Long provinceCode);
    
   /**
     *按条件查询
     */
 /*   public List<PssCity> getPssCityInfoByQuery(PssCityQuery pssCityQuery);*/
    
    /**
     *按条件查询 不带分页
     */
   /* public List<PssCity> getPssCityListByQuery(PssCityQuery pssCityQuery);*/

}