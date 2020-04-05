/*
 * Powered By J.C
 * Since 2015 - 2016-53-18
 */

package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.PssPrompt;
import com.sinosoft.business.po.query.PssPromptQuery;


public interface PssPromptDao {
	
	/**
	 * 新增方法
	 */
	public int insertPssPrompt(PssPrompt pssPrompt);
    
    
	/**
	 * 删除方法
	 */
	public int deletePssPrompt(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updatePssPrompt(PssPrompt pssPrompt);

	/**
	 * 查询方法
	 */
    public PssPrompt selectPssPromptById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(PssPromptQuery pssPromptQuery);
    
    /**
     *按条件查询
     */
    public List<PssPrompt> getPssPromptByQuery(PssPromptQuery pssPromptQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<PssPrompt> getPssPromptListByQuery(PssPromptQuery pssPromptQuery);
}