package com.sinosoft.business.dao;

import java.util.List;

import com.sinosoft.business.po.Province;
import com.sinosoft.business.po.query.ProvinceQuery;

/**
 * @Package com.sinosoft.business.dao
 * @ClassName: ProvinceDao
 * @Description: 省份信息的DAO 接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-19 下午03:05:12
 */
public interface ProvinceDao {
	
	/**
	 * 根据省份id获取省份信息
	 * @param id
	 * @return Province
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午01:56:12
	 * @version V1.0
	 */
	public Province getProvinceById(Long id);
	
	/**
	 * 根据省份名称获取省份信息
	 * @param provinceName
	 * @return Province
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:28:40
	 * @version V1.0
	 */
	public Province getProvinceByName(String provinceName);
	
	/**
	 * 根据检索条件获取省份数量信息
	 * @param provinceQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-20 下午10:04:54
	 * @version V1.0
	 */
	public Integer getProvinceCountByQuery(ProvinceQuery provinceQuery);
	
	/**
	 * 根据检索条件获取省份的记录信息
	 * @param provinceQuery
	 * @return List<Province>
	 * @throws
	 * @author zzq
	 * @date 2013-10-20 下午10:04:57
	 * @version V1.0
	 */
	public List<Province> getProvinceInfoByQuery(ProvinceQuery provinceQuery);
	
	/**
	 * 新增省份信息
	 * @param province
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:20:30
	 * @version V1.0
	 */
	public Integer addNewProvince(Province province);
	
	
	/**
	 * 根据省份id和省份名称精确查找符合条件的省份信息
	 * @param provinceQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午10:50:23
	 * @version V1.0
	 */
	public Integer getProvinceCountByName(ProvinceQuery provinceQuery);
	
	/**
	 * 根据省份id更新省份信息
	 * @param province
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午10:54:59
	 * @version V1.0
	 */
	public Integer updateProvinceById(Province province);
	
	/**
	 * 根据省份id删除省份信息
	 * @param provinceId
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午11:00:07
	 * @version V1.0
	 */
	public Integer deleteProvinceById(Long provinceId);
	
	/**
	 * 获取全部的省份信息
	 * @return List<Province>
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 上午09:21:56
	 * @version V1.0
	 */
	public List<Province> getAllProvince();

}
