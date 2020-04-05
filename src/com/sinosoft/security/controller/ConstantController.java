package com.sinosoft.security.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.po.Msg;
import com.sinosoft.po.RowSet;
import com.sinosoft.security.po.Constant;
import com.sinosoft.security.po.ConstantChild;
import com.sinosoft.security.po.extend.ExtendConstant;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.ConstantChildQuery;
import com.sinosoft.security.service.ConstantChildServer;
import com.sinosoft.security.service.ConstantServer;

/**
 * 常量维护控制层
* @ClassName: ConstantController 
* @Description: TODO
* @author zzq
* @mail zzq1229@126.com 
* @date 2014年5月16日 下午5:35:51 
 */
@Controller
public class ConstantController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantController.class);
	
	@Resource
	private ConstantChildServer constantChildService;
	
	@Resource
	private ConstantServer constantService;
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/constant.do")
	public String constantManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问角色管理页面", method);
		model.addAttribute("pageHanName", "常量"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		constantService.getConstantInfoForInitPage(model, method, request);
		return "manage/" + model.get("pageName");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getConstantList.do")
	private @ResponseBody String getConstantList(ConstantChildQuery constantChildQuery, 
			HttpServletRequest request, HttpServletResponse response){
		LOGGER.debug("获取常量列表数据，");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		RowSet rowSet = constantChildService.getConstantList(constantChildQuery, eUser, modelMap);
		
		LOGGER.info("返回的角色列表数据："+JSONObject.fromObject(rowSet).toString());
		return JSONObject.fromObject(rowSet).toString();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addConstant.do")
	private @ResponseBody String addConstant(Constant constant,HttpServletRequest request, HttpServletResponse response){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		Msg msg = null;
		/**
		 * 添加新的常量表操作步骤
		 * 1、往constant表中插入一条数据，包括 常量表名name，常量表描述description
		 * 2、创建表名为constant_name的一张常量表，字段包含(id, constant_id, code, value, update_time)
		 */
		if( constantService.creatConstantTable(constant,eUser,modelMap) && constantService.addConstant(constant,eUser,modelMap)){
			msg = new Msg();
			msg.setSuccess("true");
			msg.setInfo("添加常量表成功！");
		}else{
			msg = (Msg) modelMap.get("Msg");
		}
		return JSONObject.fromObject(msg).toString();
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/addConstantChild.do")
	private @ResponseBody String addConstantChild(ConstantChild constantChild,HttpServletRequest request, HttpServletResponse response){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		Msg msg = null;
		if(constantChildService.addConstantChild(constantChild,eUser,modelMap)){
			msg = new Msg();
			msg.setSuccess("true");
			msg.setInfo("添加常量表成功！");
		}else{
			msg = (Msg) modelMap.get("Msg");
		}
		return JSONObject.fromObject(msg).toString();
	}
	
    @RequestMapping(method = RequestMethod.POST, value = "/removeList.do")
	public @ResponseBody Map<String, Object> removeList(@RequestParam(value = "id", required = true)String id, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("删除常量信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || id == null) {
				modelMap.put("status", "exception");
			} else {
				if (constantChildService.removeListInfo(id, eUser, modelMap)) {
					//此时更新成功，将改后的信息更新到Session中
					modelMap.put("status", "success");
					modelMap.put("data", "删除常量数据成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("删除常量数据的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
    
    @RequestMapping(method = RequestMethod.POST, value = "/removeConstValue.do")
	public @ResponseBody Map<String, Object> removeConstValue(ConstantChild constantChild, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("删除常量信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || constantChild == null) {
				modelMap.put("status", "exception");
			} else {
				if (constantService.removeConstValue(constantChild, eUser, modelMap)) {
					//此时更新成功，将改后的信息更新到Session中
					modelMap.put("status", "success");
					modelMap.put("data", "删除常量数据成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("删除常量数据的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
    
    @RequestMapping(method = RequestMethod.POST, value = "/showList.do")
	public @ResponseBody Map<String, Object> showList(@RequestParam(value = "id", required = true)String id, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("查询常量基本信息！");
		LOGGER.debug("获取给定id的常量信息，id={}", id);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (id == null) {
				LOGGER.error("获取给定id的常量信息失败，常量id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "该角色不存在，请刷新页面重新请求!");
			} else {
				ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
				ExtendConstant extCon = constantChildService.selectByPrimaryKey(id,eUser,modelMap);
				if (extCon != null) {
					modelMap.put("total", 1);
					modelMap.put("status", "success");
					modelMap.put("data", extCon);
				} else {
					modelMap.put("total", 0);
					modelMap.put("status", "failure");
					modelMap.put("data", "该常量不存在，请刷新页面重新请求!");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的常量信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
    
    @RequestMapping(method = RequestMethod.POST, value = "/showConstValue.do")
	public @ResponseBody String getConstValueByTableName(@RequestParam(value = "tableName", required = true)String tableName, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("查询常量基本信息！");
		LOGGER.debug("获取给定表名，tableName={}", tableName);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		RowSet rowSet = new RowSet();
		try {
			if (tableName == null) {
				LOGGER.error("获取给定tableName表名，表名为空");
				modelMap.put("status", "error");
				modelMap.put("data", "表名获取失败，请刷新页面重新请求!");
			} else {
				ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
				ConstantChild constantChild = new ConstantChild();
				constantChild.setTableName(tableName);
				rowSet = constantChildService.getConstValueByTableName(constantChild, eUser, modelMap);
				if (rowSet != null) {
					modelMap.put("total", 1);
					modelMap.put("status", "success");
					modelMap.put("data", rowSet);
				} else {
					modelMap.put("total", 0);
					modelMap.put("status", "failure");
					modelMap.put("data", "该常量不存在，请刷新页面重新请求!");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的常量信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		LOGGER.info("返回常量数据模板："+JSONObject.fromObject(rowSet).toString());
		return JSONObject.fromObject(rowSet).toString();
	}
    
	@RequestMapping(method = RequestMethod.POST, value = "/changeList.do")
	public @ResponseBody Map<String, Object> udpateList(ConstantChild constantChild, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新常量表信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || constantChild == null) {
				modelMap.put("status", "exception");
			} else {
				if (constantChildService.updateListById(constantChild, eUser, modelMap)) {
					//此时更新成功，将改后的角色信息更新到Session中updateListById
					modelMap.put("status", "success");
					modelMap.put("data", "更新常量表信息成功！");
				}else{
					Msg msg  = (Msg) modelMap.get("Msg");
					modelMap.put("status", "error");
					modelMap.put("data", msg.getErrorData());
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新常量表信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
}
