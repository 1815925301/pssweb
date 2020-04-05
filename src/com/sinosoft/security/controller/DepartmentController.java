package com.sinosoft.security.controller;

import java.util.List;
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
import com.sinosoft.po.RowSet;
import com.sinosoft.security.po.Department;
import com.sinosoft.security.po.extend.ExtendDepartment;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.DepartmentQuery;
import com.sinosoft.security.service.DepartmentService;

/**
 * @Package com.sinosoft.business.controller
 * @ClassName: departmentController
 * @Description: 部门管理  控制层
 * @author zhaomin
 * @Version V1.0
 * @date 2013-10-16 上午01:42:28
 */
@Controller
public class DepartmentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@Resource
	private DepartmentService departmentService;
	
	/**
	 * 部门管理页面
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-7 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/department.do")
	public String departmentManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问部门管理页面", method);
		model.addAttribute("pageHanName", "部门"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		departmentService.getDeptInfoForInitPage(model, method, request);
		
		return "manage/" + model.get("pageName");
	}
	
	/**
	 * 根据父id获取部门列表
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-15 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getDeptList.do")
	private @ResponseBody String getDeptList(
			@RequestParam(value = "pid", required = true)String pid,
			DepartmentQuery deptQuery, 
			HttpServletRequest request, HttpServletResponse response){
		LOGGER.debug("获取部门列表数据，");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		
		RowSet rowSet = departmentService.getDeptList(deptQuery, modelMap, pid);
		
		LOGGER.info("返回的角色列表数据："+JSONObject.fromObject(rowSet).toString());
		return JSONObject.fromObject(rowSet).toString();
	}

	
	/**
	 * 根据部门id获取部门信息
	 * @param deptId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 上午09:39:41
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showDeptInfo.do")
	public @ResponseBody Map<String, Object> getDeptInfo(
			@RequestParam(value = "deptId", required = true)Long deptId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定id的部门信息，deptId={}", deptId);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (deptId == null) {
				LOGGER.error("获取给定id的部门信息失败，部门id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "该部门不存在，请刷新页面重新请求!");
			} else {
				ExtendDepartment extDept = departmentService.getExtendDeptInfoByDeptId(deptId);
				if (extDept != null) {
					modelMap.put("total", 1);
					modelMap.put("status", "success");
					modelMap.put("data", extDept);
				} else {
					modelMap.put("total", 0);
					modelMap.put("status", "failure");
					modelMap.put("data", "该部门不存在，请刷新页面重新请求!");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的部门信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 获取部门树
	 * @param deptId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午19:39:41
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showDeptInfoTree.do")
	public @ResponseBody Map<String, Object> getDeptInfoTree(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("加载部门树");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			List<Department> list = departmentService.getDeptInfoTree();
			if(list.size() > 0){
				modelMap.put("status", "success");
				modelMap.put("data", list);
			}else {
				modelMap.put("status", "failure");
				modelMap.put("data", "部门树未找到相应数据!");
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的部门信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}


	
	/**
	 * 保存新的部门信息
	 * @param department
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 上午01:24:05
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewDeptInfo.do")
	public @ResponseBody Map<String, Object> addNewdepartment(Department department, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("保存新的部门信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || department == null) {
				modelMap.put("status", "exception");
			} else {
				if (departmentService.addNewDepartment(department, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "保存新的部门信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("保存新的部门信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		LOGGER.info("保存新的部门信息返回结果" + modelMap.getModelMap());
		return modelMap.getModelMap();
	}
	
	/**
	 * 修改部门信息
	 * @param department
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 下午03:41:42
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/changeDeptInfo.do")
	public @ResponseBody Map<String, Object> updatedepartment(Department department, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新部门信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || department == null) {
				modelMap.put("status", "exception");
			} else {
				if (departmentService.updateDepartment(department, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "更新部门信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新部门信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/removeDeptInfo.do")
	public @ResponseBody Map<String, Object> removedepartment(
			@RequestParam(value = "deptId", required = true)Long deptId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("删除部门信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || deptId == null) {
				modelMap.put("status", "exception");
			} else {
				if (departmentService.removeDepartment(deptId, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "删除部门信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("删除部门信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}

}
