package com.sinosoft.base.dao;

import java.util.List;

/**
 * @Package com.sinosoft.base.dao
 * @ClassName: Dao
 * @Description: 最基准的Dao接口定义
 * @author zzq
 * @Version V1.0
 * @date 2013-10-2 下午06:54:43
 */
public interface Dao {
	
	/**
	 * 插入记录
	 * @param myBatis
	 * @param object
	 * @return int
	 * @throws
	 * @author zzq
	 * @date 2013-10-2 下午06:55:05
	 * @version V1.0
	 */
	public int insert(String myBatis, Object object);
	
	/**
	 * 单条查询
	 * @param myBatis
	 * @param object
	 * @return Object
	 * @throws
	 * @author zzq
	 * @date 2013-10-2 下午06:55:23
	 * @version V1.0
	 */
	public Object select(String myBatis, Object object);
	
	/**
	 * 更新记录
	 * @param myBatis
	 * @param object
	 * @return int
	 * @throws
	 * @author zzq
	 * @date 2013-10-2 下午06:55:26
	 * @version V1.0
	 */
	public int update(String myBatis, Object object);
	
	/**
	 * 删除记录
	 * @param myBatis
	 * @param object
	 * @return int
	 * @throws
	 * @author zzq
	 * @date 2013-10-2 下午06:55:28
	 * @version V1.0
	 */
	public int delete(String myBatis, Object object);
	
	/**
	 * 多条查询
	 * @param myBatis
	 * @param object
	 * @return List
	 * @throws
	 * @author zzq
	 * @date 2013-10-2 下午06:55:32
	 * @version V1.0
	 */
	@SuppressWarnings("rawtypes")
	public List selectList(String myBatis, Object object);

}
