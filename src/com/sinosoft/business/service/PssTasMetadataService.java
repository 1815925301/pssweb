

package com.sinosoft.business.service;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;




import com.sinosoft.business.po.PssTasMetadata;
import com.sinosoft.common.web.ActivityModelMap;


/**
 * @Package com.sinosoft.business.service
 * @ClassName: PssTasMetadataService
 * @Description: 专题产品 服务层接口类
 * @author hao
 * @date 2016-9-8 
 */
// 初始化专题产品查询页面
public interface PssTasMetadataService {
	
	
    public void getTasDataForInitPage(ActivityModelMap modelMap, String method,
			HttpServletRequest request);

	public void getTopproduct(HttpServletRequest request, ModelMap model,
			String productid);

	public void addTopicproduct(HttpServletRequest request, ModelMap model,
			String productid);

	public void saveshopCar(String method, ModelMap model,
			HttpServletRequest request);

	public PssTasMetadata getPssTasMetadata(HttpServletRequest request,
			ModelMap model, String string);
	
	/**
	 * 根据条件查询订单信息
	 * 
	 * @param condMap
	 *            Map<String, Object> 
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> findProductByCond(Map<String, Object> condMap);
	
	
}
