package com.sinosoft.security.dao;

import java.util.List;

import com.sinosoft.security.po.Constant;
import com.sinosoft.security.po.ConstantChild;
import com.sinosoft.security.po.extend.ExtendConstant;
import com.sinosoft.security.po.query.ConstantChildQuery;




public interface ConstantChildDao {
	/**
	 * 根据ID查询常量子表数据
	* @Title: selectByPrimaryKey 
	* @Description: TODO
	* @return ConstantChild    返回类型 
	* @param id
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午4:24:58 
	* @version V1.0
	 */
	public ExtendConstant selectByPrimaryKey(Long id);
	/**
	 * 根据ID删除常量子表数据
	* @Title: deleteByPrimaryKey 
	* @Description: TODO
	* @return Integer    返回类型 
	* @param id
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午4:25:52 
	* @version V1.0
	 */
	public Integer deleteByPrimaryKey(ConstantChild constantChild);
	/**
	 * 插入常量表子表数据
	* @Title: insertSelective 
	* @Description: TODO
	* @return Integer    返回类型 
	* @param constantChild
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午4:28:05 
	* @version V1.0
	 */
	public Integer insertSelective(ConstantChild constantChild);
	/**
	 * 根据id更新常量表子表数据
	* @Title: updateByPrimaryKeySelective 
	* @Description: TODO
	* @return Integer    返回类型 
	* @param constantChild
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午4:29:50 
	* @version V1.0
	 */
	public Integer updateByPrimaryKeySelective(ConstantChild constantChild);
	
	/**
	 * 根据常量表名，查询该常量表下所有值
	* @Title: selectConstValueByTableName 
	* @Description: TODO
	* @return List<ConstantChild>    返回类型 
	* @param Constant 常量表名
	* @return
	* @throws 
	* @author zhaomin   
	* @date 2014年11月11日 下午11:31:47 
	* @version V1.0
	 */
	public List<ConstantChild> selectConstValueByTableName(ConstantChild constantChild);
	
	/**
	 * 根据查询条件查询常量表子表数据
	* @Title: selectByQuery 
	* @Description: TODO
	* @return List<ConstantChild>    返回类型 
	* @param constantChildQuery
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午4:31:47 
	* @version V1.0
	 */
	public List<ConstantChild> selectByQuery(ConstantChildQuery constantChildQuery);
	
	/**
	 * 统计总数
	* @Title: selectCountByQuery 
	* @Description: TODO
	* @return Integer    返回类型 
	* @param constantChildQuery
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月16日 下午5:14:02 
	* @version V1.0
	 */
	public Integer selectCountByQuery(ConstantChildQuery constantChildQuery);
	/**
	 * 统计总数
	* @Title: selectAllCountByQuery 
	* @Description: TODO
	* @return Integer    返回类型 
	* @param constantChildQuery
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月17日 下午3:07:13 
	* @version V1.0
	 */
	public Integer selectAllCountByQuery(ConstantChildQuery constantChildQuery);
	/**
	 * 查询所有常量
	* @Title: selectAllByQuery 
	* @Description: TODO
	* @return List<ConstantChild>    返回类型 
	* @param constantChildQuery
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月17日 下午3:07:17 
	* @version V1.0
	 */
	public List<ExtendConstant> selectAllByQuery(ConstantChildQuery constantChildQuery);
}	
