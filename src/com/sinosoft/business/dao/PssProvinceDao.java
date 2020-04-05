
package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.PssProvince;
import com.sinosoft.business.po.query.PssProvinceQuery;
/**
 * @Package com.sinosoft.business.dao
 * @ClassName: PssProvinceDao
 * @Description: 省份信息的DAO 接口类
 * @author hao
 * @date 2016-8-24 
 */


public interface PssProvinceDao {
	
	/**
	 * 新增方法
	 *//*
	public int insertPssProvince(PssProvince pssProvince);
    
    
	*//**
	 * 删除方法
	 *//*
	public int deletePssProvince(Long id);*/
    
    /**
	 * 修改方法
	 */
	public  int updatePssProvince(PssProvince pssProvince);

	/**
	 * 查询方法
	 *//*
    public PssProvince selectPssProvinceById(Long id);*/
    
    /**
	 * 查询方法
	 */
    public List<PssProvince> selectPssProvinceByCountry(Long countryCode);
    
    /**
     * 查询数量
     *//*
     public Integer getCountByQuery(PssProvinceQuery pssProvinceQuery);
    
    *//**
     *按条件查询
     *//*
    public List<PssProvince> getPssProvinceInfoByQuery(PssProvinceQuery pssProvinceQuery);
    
    *//**
     *按条件查询 不带分页
     *//*
    public List<PssProvince> getPssProvinceListByQuery(PssProvinceQuery pssProvinceQuery);*/

}