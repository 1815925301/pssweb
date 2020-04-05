

package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.UserUpdateDao;
import com.sinosoft.business.po.UserUpdate;
import com.sinosoft.business.po.query.UserUpdateQuery;

@Repository(value="userUpdateDao")
public class UserUpdateDaoImpl extends BaseDao implements UserUpdateDao{
	
	 // 新增记录
	@Override
	public int insertUserUpdate(UserUpdate userUpdate){
		return insert("userUpdateDao.insertUserUpdate",userUpdate);
	}
	
	// 删除
	@Override
	public int deleteUserUpdate(Long id){
		return delete("userUpdateDao.deletePssUsermessageUpdate",id);
	}

	// 更新
	@Override
	public  int updateUser(UserUpdate userUpdate){
		return update("userUpdateDao.updateUser",userUpdate);
	}
	
	// 通过id查询记录
	@Override
	public UserUpdate selectMessageById(Long id){
		return (UserUpdate)getReadSqlSession().selectOne("userUpdateDao.getUserUpdateById",id);
	}
	
	/*// 查询数量
	@Override
	public Integer getCountByQuery(UserUpdateQuery userUpdateQuery){
		return getReadSqlSession().selectOne("userUpdateDao.getCountByQuery",userUpdateQuery);
	}
	
	// 按条件查询
	@Override
	public List<UserUpdate> getPssUsermessageUpdateInfoByQuery(UserUpdateQuery userUpdateQuery){
		return getReadSqlSession().selectList("userUpdateDao.getPssUsermessageUpdateInfoByQuery",userUpdateQuery);
	}
	
	// 按条件查询 不带分页
	@Override
	public List<UserUpdate> getPssUsermessageUpdateListByQuery(UserUpdateQuery userUpdateQuery){
		return getReadSqlSession().selectList("userUpdateDao.getPssUsermessageUpdateListByQuery",userUpdateQuery);
	}*/
}
