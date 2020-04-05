package com.sinosoft.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.Province;
import com.sinosoft.business.po.extend.ExtendProvince;
import com.sinosoft.common.web.SinosoftModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * @Package com.sinosoft.business.service
 * @ClassName: ProvinceService
 * @Description: 省份信息 服务层接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-19 下午03:43:18
 */
public interface ProvinceService {
	
	/**
	 * 根据省份id获取省份信息
	 * @param id
	 * @return Province
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午01:55:20
	 * @version V1.0
	 */
	public Province getProvinceById(Long id);
	
	/**
	 * 根据省份名称获取省份信息
	 * @param provinceName
	 * @return Province
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:27:36
	 * @version V1.0
	 */
	public Province getProvinceByName(String provinceName);
	
	/**
	 * 根据查询参数获取省份信息 用于省份管理页面
	 * @param model
	 * @param method
	 * @param request
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-20 下午09:55:39
	 * @version V1.0
	 */
	public void getProvinceInfoForInitPage(ModelMap model, String method, HttpServletRequest request);
	
	/**
	 * 根据省份id获取省份的扩展信息
	 * @param provinceId
	 * @return ExtendProvince
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午01:50:40
	 * @version V1.0
	 */
	public ExtendProvince getExtendProvinceById(Long provinceId);
	
	/**
	 * 保存新的省份信息
	 * @param province
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:18:07
	 * @version V1.0
	 */
	public boolean addNewProvince(Province province, ExtendUsers eUser, SinosoftModelMap modelMap);
	
	/**
	 * 更新省份信息
	 * @param province
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午10:42:57
	 * @version V1.0
	 */
	public boolean updateProvince(Province province, ExtendUsers eUser, SinosoftModelMap modelMap);
	
	/**
	 * 根据id删除省份信息
	 * @param ProvinceService
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午10:57:56
	 * @version V1.0
	 */
	public boolean removeProvince(Long provinceId, ExtendUsers eUser, SinosoftModelMap modelMap);
	
	/**
	 * 获取全部的省份信息
	 * @return List<Province>
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 上午09:19:11
	 * @version V1.0
	 */
	public List<Province> getAllProvince();

}
