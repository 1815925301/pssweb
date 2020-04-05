package com.sinosoft.security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.Constant;
import com.sinosoft.security.po.ConstantChild;
import com.sinosoft.security.po.extend.ExtendUsers;


/**
 * 常量表服务类
* @ClassName: ConstantServer 
* @Description: TODO
* @author zzq
* @mail zzq1229@126.com 
* @date 2014年5月16日 下午5:01:35 
*
 */
public interface ConstantServer {
	/**
	 * 常量表初始化
	* @Title: getRolesInfoForInitPage 
	* @Description: TODO
	* @return void    返回类型 
	* @param model
	* @param method
	* @param request
	* @throws 
	* @author zzq   
	* @date 2014年5月17日 下午3:19:18 
	* @version V1.0
	 */
	public void getConstantInfoForInitPage(ModelMap model, String method, HttpServletRequest request);

	/**
	 * 常见常量表
	* @Title: addConstant 
	* @Description: TODO
	* @return boolean    返回类型 
	* @param constant
	* @param eUser
	* @param modelMap
	* @throws 
	* @author zzq   
	* @date 2014年5月18日 上午10:04:15 
	* @version V1.0
	 */
	public boolean creatConstantTable(Constant constant, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 新增常量表
	* @Title: addConstant 
	* @Description: TODO
	* @return boolean    返回类型 
	* @param constant
	* @param eUser
	* @param modelMap
	* @throws 
	* @author zzq   
	* @date 2014年5月18日 上午10:04:15 
	* @version V1.0
	 */
	public boolean addConstant(Constant constant, ExtendUsers eUser, ActivityModelMap modelMap);
	/**
	 * 获取所有常量数据
	* @Title: getAllConstantList 
	* @Description: TODO
	* @return List<Constant>    返回类型 
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月18日 下午10:01:25 
	* @version V1.0
	 */
	public List<Constant> getAllConstantList();
	
	/**
	 * 删除常量表
	* @Title: removeListInfo 
	* @Description: TODO
	* @return boolean    返回类型 
	* @param ids
	* @param eUser
	* @param modelMap
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月18日 下午4:23:29 
	* @version V1.0
	 */
	public boolean removeConstValue(ConstantChild constantChild, ExtendUsers eUser, ActivityModelMap modelMap);

}
