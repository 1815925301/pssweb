/*
 * Powered By 尹力
 * Since 2015 - 2016-45-13
 */

package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.PssTask;



public interface PssTaskDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssTask(PssTask PSS_TASK);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssTask(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssTask(PssTask PSS_TASK);

	/**
	 * 查询方法
	 */
    public PssTask selectPssTaskById(Long id);

    


}