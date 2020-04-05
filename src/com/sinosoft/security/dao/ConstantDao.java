package com.sinosoft.security.dao;

import java.util.List;

import com.sinosoft.security.po.Constant;
import com.sinosoft.security.po.query.ConstantQuery;


public interface ConstantDao {
	/**
	 * 根据ID查询常量表信息
	* @Title: selectByPrimaryKey 
	* @Description: TODO
	* @return Constant    返回类型 
	* @param id
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午1:27:19 
	* @version V1.0
	 */
	public Constant selectByPrimaryKey(Long id);
	/**
	 * 根据ID删除常量表数据
	* @Title: deleteByPrimaryKey 
	* @Description: TODO
	* @return Constant    返回类型 
	* @param id
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午1:32:17 
	* @version V1.0
	 */
	public Integer deleteByPrimaryKey(Long id);
	
	/**
	 * 创建常量表
	* @Title: insertConstant 
	* @Description: TODO
	* @return Integer    返回类型 
	* @param constant
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午1:35:23 
	* @version V1.0
	 */
	public Integer creatConstantTable(Constant constant);
	
	/**
	 * 新增常量表
	* @Title: insertConstant 
	* @Description: TODO
	* @return Integer    返回类型 
	* @param constant
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午1:35:23 
	* @version V1.0
	 */
	public Integer insertConstant(Constant constant);
	
	/**
	 * 更新常量表数据
	* @Title: updateByPrimaryKeySelective 
	* @Description: TODO
	* @return Integer    返回类型 
	* @param constant
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午1:37:39 
	* @version V1.0
	 */
	public Integer updateByPrimaryKeySelective(Constant constant);
	
	/**
	 * 根据查询条件查询常量表数据
	* @Title: selectByQuery 
	* @Description: TODO
	* @return List<Constant>    返回类型 
	* @param constant
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午2:05:54 
	* @version V1.0
	 */
	public List<Constant> selectByQuery(ConstantQuery constantQuery); 
	
	/**
	 * 统计总数
	* @Title: selectCountByQuery 
	* @Description: TODO
	* @return Integer    返回类型 
	* @param constantQuery
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午5:12:58 
	* @version V1.0
	 */
	public Integer selectCountByQuery(ConstantQuery constantQuery);
	
}
