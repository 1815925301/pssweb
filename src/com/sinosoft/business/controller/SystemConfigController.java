package com.sinosoft.business.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.business.service.AttachmentService;
import com.sinosoft.business.service.SystemConfigManager;
import com.sinosoft.common.constant.Constant;
import com.sinosoft.common.constant.Constant.PAGINATION_DEFAULT;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * @Package com.sinosoft.business.controller
 * @ClassName: SystemConfigController
 * @Description: 系统设置  MVC控制层web入口
 * @author liran 
 * @Version V1.0
 * @date 2013-10-26 上午06:58:54
 */
@Controller
public class SystemConfigController {
	@Resource
	private SystemConfigManager mgr;

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemConfigController.class);
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/SystemConfig.do")
	public String select(ModelMap model, HttpServletRequest request) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问SystemConfig管理页面", method);
		model.addAttribute("pageHanName", "SystemConfig"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		mgr.SystemConfigPageInit( request, model, method);
		return "manage/" + model.get("pageName");
	}
	
	/***
	 * 通过id查询系统配置详细数据
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/selectdatabyid.do")
	public @ResponseBody Map<String, Object> selectdatabyid(ModelMap model, HttpServletRequest request) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		mgr.getSysConfigById(modelMap, request);
		return modelMap.getModelMap();
	}
	
	/***
	 * 删除系统配置数据
	 * @param dataId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/deletedatabyid.do")
	public @ResponseBody Map<String, Object> removedatabyid(
			@RequestParam(value = "dataId", required = true)Long dataId, 
			HttpServletRequest request, HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
		if (mgr.removeSystemConfig(dataId,modelMap, request)) {
			modelMap.put("status", "success");
			modelMap.put("data", "删除用户信息成功！");
		}
		}catch(Exception e){
			LOGGER.error("删除用户信息的操作出现异常：{}", e);
			modelMap.put("status", "error");
			modelMap.put("data", "系统中存在该用户的操作数据，如上报活动等！<BR/>无法删除！");
		}
		return modelMap.getModelMap();
	}
	
	
	/***
	 * 修改系统配置数据
	 * @param systemConfig
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/modifysystem.do")
	public @ResponseBody Map<String, Object> modifysystem(SystemConfig systemConfig, 
			HttpServletRequest request, HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			String id = request.getParameter("id");
			SystemConfig systemConfigs = new SystemConfig();
					systemConfigs = mgr.updateSystemConfig(systemConfig, request, modelMap,id);
		} catch(Exception e) {
			LOGGER.error("修改当前用户的基本信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/***
	 * 修改页面的确认
	 * @param systemConfig
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveSystemConfigConfirm.do")
	public @ResponseBody Map<String, Object> modifysystemconfirm(SystemConfig systemConfig, 
			HttpServletRequest request, HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
				if (mgr.updateSystemConfigconfirm(systemConfig, request, modelMap)) {
//					//此时更新成功，将改后的用户信息更新到Session中
					modelMap.put("status", "success");
					modelMap.put("data", "用户信息更新成功！");
				}
			  request.getServletContext().setAttribute(Constant.SYSTEM_CONFIG, mgr.getSystemConfig());
		} catch(Exception e) {
			LOGGER.error("修改当前用户的基本信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	/***
	 * 新增加保存系统配置--加上传文件
	 * @param systemConfig
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewSystemConfig.do")
	public String  addsystem(@RequestParam(value="file",required=false) MultipartFile files,SystemConfig systemConfig, 
			HttpServletRequest request) throws IOException {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		ModelMap model = new ModelMap();
		model.addAttribute("pageHanName", "SystemConfig"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		//获取传过来的文件的名字
		String fileName= files.getOriginalFilename(); 
		//获取tomcat项目根目录
		File file2 = new File( request.getSession().getServletContext().getRealPath("/")+"upload"); 
		String pathshuju = request.getSession().getServletContext().getRealPath("/")+"upload";
		if(!file2.exists()){
			file2.mkdirs();
		}
		File uploadFile = new File(file2+"/"+fileName);
		//拷贝文件到tomcat目录下
		FileCopyUtils.copy(files.getBytes(), uploadFile);
		systemConfig.setImagelocation(pathshuju);
		mgr.saveSystemConfig(systemConfig, request,modelMap);
		request.getServletContext().setAttribute(Constant.SYSTEM_CONFIG, mgr.getSystemConfig());
		return "redirect:/SystemConfig.do";
	}

	/***
	 * 跳转到新增页面
	 * @param systemConfig
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/SystemConfigAdd.do")
	public  String  addyStemConfig(ModelMap model,HttpServletRequest request) {
		model.addAttribute("pageHanName", "SystemConfigAdd"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		return "manage/" + model.get("pageName");
	}
}
