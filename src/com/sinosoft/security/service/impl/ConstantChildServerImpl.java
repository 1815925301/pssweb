package com.sinosoft.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sinosoft.base.po.CheckErrorDto;
import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.po.Msg;
import com.sinosoft.po.RowSet;
import com.sinosoft.security.dao.ConstantChildDao;
import com.sinosoft.security.dao.ConstantDao;
import com.sinosoft.security.po.Constant;
import com.sinosoft.security.po.ConstantChild;
import com.sinosoft.security.po.extend.ExtendConstant;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.ConstantChildQuery;
import com.sinosoft.security.po.query.ConstantQuery;
import com.sinosoft.security.service.ConstantChildServer;

@Service("constantChildService")
public class ConstantChildServerImpl implements ConstantChildServer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantChildServerImpl.class);
	
	@Resource
	private ConstantDao constantDao;
	
	@Resource
	private ConstantChildDao constantChildDao;
	
	@Resource
	private SystemConstant systemConstant;
	
	@Override
	public RowSet getConstantList(ConstantChildQuery constantChildQuery,
			ExtendUsers eUser, ActivityModelMap modelMap) {
		
		LOGGER.debug("Service层：根据查询参数获取常量");
		//以id升序排序
		constantChildQuery.setSortBy("id");
		constantChildQuery.setSortType("1");
		if(constantChildQuery.getConstant_id()==null){
			constantChildQuery.setConstant_id(null);
		}
		Integer totalCount = constantChildDao.selectAllCountByQuery(constantChildQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, constantChildQuery.getPageSize(), 
				constantChildQuery.getPage(), constantChildQuery.getStartNum());
		List rolesList = constantChildDao.selectAllByQuery(constantChildQuery);
		RowSet rowSet = new RowSet();
		rowSet.setRows(rolesList);
		rowSet.setRecords(totalInfo.getTotalCount());
		rowSet.setPage(constantChildQuery.getPage());
		rowSet.setTotal(totalInfo.getPageTotal());
		
		return rowSet;
	}
	
	@Override
	public RowSet getConstValueByTableName(ConstantChild constantChild,
			ExtendUsers eUser, ActivityModelMap modelMap) {
		
		LOGGER.debug("Service层：根据查询参数获取常量");
		//以id升序排序
		List constList = constantChildDao.selectConstValueByTableName(constantChild);
		RowSet rowSet = new RowSet();
		rowSet.setRows(constList);
		rowSet.setRecords(constList.size());
		rowSet.setTotal(constList.size());
		
		return rowSet;
	}
	
	@Override
	public boolean addConstantChild(ConstantChild constantChild,
			ExtendUsers eUser, ActivityModelMap modelMap) {
	    boolean flag = false;
		
		if(constantChild==null){
			LOGGER.info("常量数据为空，无法新增！");
			return false;
		}
		constantChild.trim();
		if(checkConstantChildExit(constantChild,eUser,modelMap,"add")){
			Integer resultNum = constantChildDao.insertSelective(constantChild);
			if(resultNum.compareTo(new Integer(1)) == 0){
				flag = true;
				systemConstant.setConstantListByTableName();
			}
		}
		
		return flag;
	}
	
	@Override
	public boolean removeListInfo(String id, ExtendUsers eUser,
			ActivityModelMap modelMap) {
		LOGGER.debug("Service层：根据常量表id删除常量表 物理删除");
		boolean result = false;
		Integer resultNum = constantDao.deleteByPrimaryKey(Long.valueOf(id));
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
			systemConstant.setConstantListByTableName();
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}
	
   private boolean checkConstantChildExit(ConstantChild constantChild, ExtendUsers eUser,ActivityModelMap modelMap,String type){
    	List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
    	Msg msg = new Msg();
    	modelMap.put("Msg", msg);
    	msg.setSuccess("true");
    	ConstantChildQuery constantChildQuery = new ConstantChildQuery();
    	constantChildQuery.setConstant_id(constantChild.getConstant_id());
    	constantChildQuery.setCode(constantChild.getCode());
    	constantChildQuery.setTableName(constantChild.getTableName());
    	Integer resultNum = constantChildDao.selectCountByQuery(constantChildQuery);
 		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
 			msg.setSuccess("false");
 			LOGGER.info("编码已经存在");
 			errorInfoList.add(new CheckErrorDto(type+"_code", "编码已经存在"));
 			msg.setErrorData(errorInfoList);
 			return false;
 		}
 		if(constantChild.getValue().length()>=50){
 			msg.setSuccess("false");
 			LOGGER.info("描述信息不可以超过50个字符");
 			errorInfoList.add(new CheckErrorDto(type+"add_value", "描述信息不可以超过50个字符"));
 			msg.setErrorData(errorInfoList);
 			return false;
 		}
    	return true;
     }

	@Override
	public ExtendConstant selectByPrimaryKey(String id, ExtendUsers eUser,
			ActivityModelMap modelMap) {
		ExtendConstant extendConstant = null;
		if(id!=null){
			 extendConstant = constantChildDao.selectByPrimaryKey(Long.valueOf(id));
		}
		return extendConstant;
	}

	@Override
	public boolean updateListById(ConstantChild constantChild,
			ExtendUsers eUser, ActivityModelMap modelMap) {
		if(constantChild.getCode_old().equals(constantChild.getCode())){
			Integer resultNum = constantChildDao.updateByPrimaryKeySelective(constantChild);
			if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
				systemConstant.setConstantListByTableName();
				return true;
			}
		}else{
			if(checkConstantChildExit(constantChild,eUser,modelMap,"edit")){
				Integer resultNum = constantChildDao.updateByPrimaryKeySelective(constantChild);
				if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
					systemConstant.setConstantListByTableName();
					return true;
				}
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public Map<String, List<ConstantChild>> getAllConstValue() {
		/**
		 * 1、先从constant表中查出所有的常量表，将常量表名放在一个list中
		 * 2、循环list中的常量表名，查询出该常量表的常量值
		 */
		ConstantQuery constantQuery = new ConstantQuery();
		List<Constant> constantList = constantDao.selectByQuery(constantQuery);
		ConstantChild constantChild = new ConstantChild();
		List<ConstantChild> constList = null;
		Map<String, List<ConstantChild>> resultMap = new HashMap<String, List<ConstantChild>>();
		for(Constant con : constantList){
			constantChild = new ConstantChild();
			constantChild.setTableName(con.getName());
			constList = constantChildDao.selectConstValueByTableName(constantChild);
			resultMap.put(con.getName(), constList);
		}
		return resultMap;
	}
  
}
