package com.sinosoft.security.service;

import java.util.Map;

import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.po.RowSet;
import com.sinosoft.security.po.ConstantChild;
import com.sinosoft.security.po.extend.ExtendConstant;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.ConstantChildQuery;
import java.util.List;


public interface ConstantChildServer {
	
	/**
	 * 根据查询条件获取
	* @Title: getRolsList 
	* @Description: TODO
	* @return ConstantChild    返回类型 
	* @param constantChildQuery
	* @param eUser
	* @param modelMap
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月17日 下午3:15:35 
	* @version V1.0
	 */
	public RowSet getConstantList(ConstantChildQuery constantChildQuery, ExtendUsers eUser, ActivityModelMap modelMap);
	
	
	/**
	* 获取全部常量表值
	* @Title: getAllConstValue 
	* @Description: TODO
	* @return ConstantChild    返回类型 
	* @param constantChildQuery
	* @param eUser
	* @param modelMap
	* @return
	* @throws 
	* @author zhaomin
	* @date 2014年11月11日 下午11:15:35 
	* @version V1.0
	 */
	public Map<String, List<ConstantChild>> getAllConstValue();

	
	/**
	 * 根据表名获取该表的常量值
	* @Title: getConstValueByTableName 
	* @Description: TODO
	* @return ConstantChild    返回类型 
	* @param constantChildQuery
	* @param eUser
	* @param modelMap
	* @return
	* @throws 
	* @author zhaomin
	* @date 2014年11月11日 下午11:15:35 
	* @version V1.0
	 */
	public RowSet getConstValueByTableName(ConstantChild constantChild, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 新增常量表数据
	* @Title: addConstantChild 
	* @Description: TODO
	* @return boolean    返回类型 
	* @param constantChild
	* @param eUser
	* @param modelMap
	* @throws 
	* @author zzq   
	* @date 2014年5月18日 上午10:04:48 
	* @version V1.0
	 */
	public boolean addConstantChild(ConstantChild constantChild, ExtendUsers eUser, ActivityModelMap modelMap);
	
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
	public boolean removeListInfo(String ids, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据ID查询常量表数据
	* @Title: selectByPrimaryKey 
	* @Description: TODO
	* @return ExtendConstant    返回类型 
	* @param id
	* @param eUser
	* @param modelMap
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月18日 下午5:18:48 
	* @version V1.0
	 */
	public ExtendConstant selectByPrimaryKey(String id, ExtendUsers eUser, ActivityModelMap modelMap);
	/**
	 * 修改常量表数据
	* @Title: updateListById 
	* @Description: TODO
	* @return Integer    返回类型 
	* @param constantChild
	* @param eUser
	* @param modelMap
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月18日 下午5:57:39 
	* @version V1.0
	 */
	public boolean updateListById(ConstantChild constantChild, ExtendUsers eUser, ActivityModelMap modelMap);
}
