/*
 * Powered By 尹力
 * Since 2015 - 2016-06-19
 */

package com.sinosoft.business.dao;
import java.util.List;

import com.sinosoft.business.po.UserUpdate;
import com.sinosoft.business.po.query.UserUpdateQuery;



public interface UserUpdateDao {
	
	/**
	 * 新增方法
	 */
	public int insertUserUpdate(UserUpdate PSS_USERMESSAGE_UPDATE);
    
    
	/**
	 * 删除方法
	 */
	public int deleteUserUpdate(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updateUser(UserUpdate PSS_USERMESSAGE_UPDATE);

	/**
	 * 查询方法
	 */
    public UserUpdate selectMessageById(Long id);
    
    /**
     * 查询数量
     *//*
     public Integer getCountByQuery(UserUpdateQuery PSS_USERMESSAGE_UPDATEQuery);
    
    *//**
     *按条件查询
     *//*
    public List<UserUpdate> getPssUsermessageUpdateInfoByQuery(UserUpdateQuery PSS_USERMESSAGE_UPDATEQuery);
    
    *//**
     *按条件查询 不带分页
     *//*
    public List<UserUpdate> getPssUsermessageUpdateListByQuery(UserUpdateQuery PSS_USERMESSAGE_UPDATEQuery);*/

}