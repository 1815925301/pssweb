package com.sinosoft.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;



























import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.CheckErrorDto;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.po.Msg;
import com.sinosoft.security.dao.ConstantChildDao;
import com.sinosoft.security.dao.ConstantDao;
import com.sinosoft.security.po.Constant;
import com.sinosoft.security.po.ConstantChild;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.ConstantChildQuery;
import com.sinosoft.security.po.query.ConstantQuery;
import com.sinosoft.security.service.ConstantServer;

@Service("constantService")
public class ConstantServerImpl implements ConstantServer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantServerImpl.class);
	
	@Resource
	private ConstantDao constantDao;
	
	@Resource
	private ConstantChildDao constantChildDao;
	
	@Resource
	private SystemConstant systemConstant;
	
	@Override
	public void getConstantInfoForInitPage(ModelMap model, String method,
			HttpServletRequest request) {
		
		ConstantQuery constantQuery = new ConstantQuery();
		List<Constant> constantList = constantDao.selectByQuery(constantQuery);
		if(constantList.size()>0){
			model.addAttribute("constantList", constantList);
		}
	}
	
	@Override
	public boolean creatConstantTable(Constant constant, ExtendUsers eUser,
			ActivityModelMap modelMap) {
		boolean flag = false;
		
		if(constant==null){
			LOGGER.info("常量数据为空，无法新增！");
			return false;
		}
		constant.trim();
		Integer resultNum = constantDao.creatConstantTable(constant);
		//改为存储过程新建表，返回-1
		if(resultNum.compareTo(new Integer(0)) == -1){
			flag = true;
			// systemConstant.setConstantListByTableName(); //该处不需刷新内存，在往constant表中插入数据成功后刷新
		}
		return flag;
	}

	@Override
	@Transactional(value = "sinosoftWriteTxManager", isolation = Isolation.READ_COMMITTED, 
	propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean addConstant(Constant constant, ExtendUsers eUser,
			ActivityModelMap modelMap) {
		boolean flag = false;
		
		if(constant==null){
			LOGGER.info("常量数据为空，无法新增！");
			return false;
		}
		constant.trim();
		if(checkConstantExit(constant,eUser,modelMap)){
			Integer resultNum = constantDao.insertConstant(constant);
			if(resultNum.compareTo(new Integer(1)) == 0){
				flag = true;
				systemConstant.setConstantListByTableName();
			}
		}
		return flag;
	}
	/**
	 * 检查表名是否重复
	* @Title: checkConstantExit 
	* @Description: TODO
	* @return boolean    返回类型 
	* @param constant
	* @param eUser
	* @param modelMap
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年5月18日 上午10:30:13 
	* @version V1.0
	 */
     private boolean checkConstantExit(Constant constant, ExtendUsers eUser,ActivityModelMap modelMap){
    	List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
    	Msg msg = new Msg();
    	modelMap.put("Msg", msg);
    	msg.setSuccess("true");
    	ConstantQuery constantQuery = new ConstantQuery();
    	constantQuery.setName(constant.getName());
    	Integer resultNum = constantDao.selectCountByQuery(constantQuery);
 		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
 			msg.setSuccess("false");
 			LOGGER.info("常量表已经存在");
 			errorInfoList.add(new CheckErrorDto("add_name", "常量表已经存在"));
 			msg.setErrorData(errorInfoList);
 			return false;
 		}
 		if(constant.getDescription().length()>=50){
 			msg.setSuccess("false");
 			LOGGER.info("描述信息不可以超过50个字符");
 			errorInfoList.add(new CheckErrorDto("add_description", "描述信息不可以超过50个字符"));
 			msg.setErrorData(errorInfoList);
 			return false;
 		}
    	return true;
     }

	@Override
	public List<Constant> getAllConstantList() {
		ConstantQuery constantQuery = new ConstantQuery();
		List<Constant> constantList = constantDao.selectByQuery(constantQuery);
		ConstantChildQuery constantChildQuery = new  ConstantChildQuery();
		for(Constant con:constantList){
			constantChildQuery.setConstant_id(con.getId());
			List<ConstantChild> constantChild = constantChildDao.selectByQuery(constantChildQuery);
			con.setConstantChildList(constantChild);
		}
		return constantList;
	}
	
	@Override
	public boolean removeConstValue(ConstantChild constantChild, ExtendUsers eUser,
			ActivityModelMap modelMap) {
		LOGGER.debug("Service层：根据常量表id删除常量表 物理删除");
		boolean result = false;
		Integer resultNum = constantChildDao.deleteByPrimaryKey(constantChild);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
			systemConstant.setConstantListByTableName();
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}
}
