package com.sinosoft.business.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sinosoft.business.po.Attachment;
import com.sinosoft.business.service.AttachmentService;
import com.sinosoft.common.constant.Constant;
import com.sinosoft.common.constant.EnumOrganizationType;
import com.sinosoft.common.constant.MappingConstantConfig;
import com.sinosoft.common.constant.MappingInputLengthConfig;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.util.ActivityStringUtils;
import com.sinosoft.common.util.CreateGUID;
import com.sinosoft.common.util.FileUtil;
import com.sinosoft.common.util.FolderUtils;
import com.sinosoft.common.web.SinosoftModelMap;
import com.sinosoft.security.po.Organization;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * @Package com.sinosoft.business.controller
 * @ClassName: FileUploadController
 * @Description: 附件上传 下载 MVC控制层web入口
 * @author zzq
 * @Version V1.0
 * @date 2013-11-3 下午05:19:03
 */
@Controller
public class AttachmentController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AttachmentController.class);

	@Resource
	private AttachmentService attachmentService;

	@Resource
	private SystemConstant systemConstant;

	/**
	 * 上传附件
	 * 
	 * @param AttachmentController
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-11-4 下午11:33:04
	 * @version V1.0
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = "/fileUpload.do")
	public void fileUpload(MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		ExtendUsers eUser = (ExtendUsers) request.getSession().getAttribute(
				"CURRENT_USER_INFO");
		if (multipartRequest == null || multipartRequest.getFileNames() == null
				|| eUser == null) {
			LOGGER.error("上传附件操作异常，附件为空或者用户信息为空");
			modelMap.put("status", "exception");
			modelMap.put("data", "上传附件操作异常，附件为空，请重新上传！");
		} else {
			try {
				LOGGER.info("开始上传附件");
				for (Iterator<String> it = multipartRequest.getFileNames(); it
						.hasNext();) {
					String key = (String) it.next();
					MultipartFile file = multipartRequest.getFile(key);
					// 校验附件名称
					if (file.getOriginalFilename().length() > 0) {
						String originalFilename = file.getOriginalFilename();
						String fileExtName = FileUtil.getFileExtName(
								originalFilename, "SUFFIX_PATH");
						// 校验附件后缀名
						if (fileExtName != null && !fileExtName.equals("")) {
							// 校验附件大小
							// if (this.checkImportFileSize(file)) {

							String path = MappingConstantConfig
									.getValue("APP_BASE_PATH")
									+ MappingConstantConfig
											.getValue("UPLOAD_PATH");
							FolderUtils.createFolder(path);
							LOGGER.info("附件上传路径：{}", path);

							LOGGER.info("原文件名称：{}", originalFilename);

							String fileName = CreateGUID.getGUID() + "."
									+ fileExtName;
							LOGGER.info("新文件名称：{}", fileName);
							File targetFile = new File(path, fileName);
							if (!targetFile.exists()) {
								targetFile.mkdirs();
							}
							// 保存
							file.transferTo(targetFile);
							// 附件信息保存到数据库
							Attachment attachment = new Attachment();
							attachment.setFileName(ActivityStringUtils.cutStr(
									fileName, MappingInputLengthConfig
											.getValue("FILE_NAME_LENGTH")));
							attachment.setFilePath(ActivityStringUtils.cutStr(
									MappingConstantConfig
											.getValue("UPLOAD_PATH"),
									MappingInputLengthConfig
											.getValue("FILE_PATH_LENGTH")));
							attachment.setFileType(ActivityStringUtils.cutStr(
									file.getContentType(),
									MappingInputLengthConfig
											.getValue("FILE_TYPE_LENGTH")));
							attachment.setFileSize(file.getSize());
							attachment
									.setOriginalFileName(ActivityStringUtils
											.cutStr(originalFilename,
													MappingInputLengthConfig
															.getValue("ORIGINAL_FILE_NAME_LENGTH")));
							attachment.setCreateUserId(eUser.getCreateUserId());
							attachmentService.addNewAttachment(attachment);
							modelMap.put("status", "success");
							modelMap.put("data", attachment.getId());
							modelMap.put("fileName", originalFilename);
							modelMap.put("fileExtName", fileExtName);
							// } else {
							// LOGGER.error("所上传的附件大小超过限制：{}M，附件大小为：{}M",
							// systemConstant.getMaxImportFileSize(),
							// file.getSize() / 1024 / 1024);
							// modelMap.put("status", "error");
							// modelMap.put("data", "所上传的附件大小超过限制：" +
							// systemConstant.getMaxImportFileSize() + "M");
							// }
						} else {
							LOGGER.error("附件的后缀名不在允许范围之内：{}", originalFilename);
							modelMap.put("status", "error");
							modelMap.put("data", "附件的后缀名不在允许范围之内！");
						}

					} else {
						LOGGER.error("所上传的附件原始名称为空");
						modelMap.put("status", "error");
						modelMap.put("data", "所上传的附件原始名称为空，请重新上传");
					}
				}
			} catch (Exception e) {
				LOGGER.error("上传附件的操作出现异常：{}", e);
				modelMap.put("status", "exception");
				modelMap.put("data", "系统异常，请刷新页面重新请求！");
			}
		}
		response.setCharacterEncoding(Constant.DEFAULT_ENCODE.STRING_UTF8);
		response.setContentType(Constant.DEFAULT_CONTENT_TYPE.CONTENT_TYPE_UTF8);
		response.getWriter().write(this.reverMapToJson(modelMap.getModelMap()));
		return;
	}

	/**
	 * 导入活动计划
	 * 
	 * @param AttachmentController
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-11-4 下午11:33:04
	 * @version V1.0
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = "/activityFileUpload.do")
	public void activityFileUpload(
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		ExtendUsers eUser = (ExtendUsers) request.getSession().getAttribute(
				"CURRENT_USER_INFO");
		if (multipartRequest == null || multipartRequest.getFileNames() == null
				|| eUser == null) {
			LOGGER.error("导入活动计划操作异常，附件为空或者用户信息为空");
			modelMap.put("status", "exception");
			modelMap.put("data", "导入活动计划操作异常，附件为空，请重新上传！");
		} else {
			try {
				boolean userIsArea = false;
				List<Organization> areaList = systemConstant.getAreaList();
				for (Organization org : areaList) {
					if (eUser.getOrgId().compareTo(org.getId()) == 0) {
						if (org.getType().compareTo(
								EnumOrganizationType.ORGANIZATION_IS_AREA
										.getStatus()) == 0) {
							LOGGER.debug("当前用户是区域用户！");
							userIsArea = true;
							break;
						}
					}
				}
				LOGGER.info("开始导入活动计划");
				for (Iterator<String> it = multipartRequest.getFileNames(); it
						.hasNext();) {
					String key = (String) it.next();
					MultipartFile file = multipartRequest.getFile(key);
					// 校验附件名称
					if (file.getOriginalFilename().length() > 0) {
						String originalFilename = file.getOriginalFilename();

						String fileExtName = null;
						if (userIsArea) {
							fileExtName = FileUtil.getFileExtName(
									originalFilename,
									"COMPRESS_ACTIVITY_SUFFIX_PATH");
						} else {
							fileExtName = FileUtil.getFileExtName(
									originalFilename,
									"COMPRESS_ACTIVITY_SUFFIX_PATH");
						}

						// 校验附件后缀名
						if (fileExtName != null && !fileExtName.equals("")) {
							// 校验附件大小
							// if (this.checkActivityFileSize(file)) {

							String path = MappingConstantConfig
									.getValue("APP_BASE_PATH")
									+ MappingConstantConfig
											.getValue("ACTIVITY_UPLOAD_PATH");
							FolderUtils.createFolder(path);
							LOGGER.info("活动计划上传路径：{}", path);

							LOGGER.info("原文件名称：{}", originalFilename);

							String fileName = CreateGUID.getGUID() + "."
									+ fileExtName;
							LOGGER.info("新文件名称：{}", fileName);
							File targetFile = new File(path, fileName);
							if (!targetFile.exists()) {
								targetFile.mkdirs();
							}
							// 保存
							file.transferTo(targetFile);
							// 附件信息保存到数据库
							Attachment attachment = new Attachment();
							attachment.setFileName(ActivityStringUtils.cutStr(
									fileName, MappingInputLengthConfig
											.getValue("FILE_NAME_LENGTH")));
							attachment.setFilePath(ActivityStringUtils.cutStr(
									MappingConstantConfig
											.getValue("ACTIVITY_UPLOAD_PATH"),
									MappingInputLengthConfig
											.getValue("FILE_PATH_LENGTH")));
							attachment.setFileType(ActivityStringUtils.cutStr(
									file.getContentType(),
									MappingInputLengthConfig
											.getValue("FILE_TYPE_LENGTH")));
							attachment.setFileSize(file.getSize());
							attachment
									.setOriginalFileName(ActivityStringUtils
											.cutStr(originalFilename,
													MappingInputLengthConfig
															.getValue("ORIGINAL_FILE_NAME_LENGTH")));
							attachment.setCreateUserId(eUser.getCreateUserId());
							attachment.setProfile("导入活动计划");
							attachmentService.addNewAttachment(attachment);
							modelMap.put("status", "success");
							modelMap.put("data", attachment.getId());
							modelMap.put("fileName", originalFilename);
						} else {
							LOGGER.error("附件的后缀名不在允许范围之内：{}", originalFilename);
							modelMap.put("status", "error");
							modelMap.put("data",
									"附件的后缀名不在允许范围之内！<br/>系统只支持“.xls”类型模板文件！");
						}

					} else {
						LOGGER.error("所上传的附件原始名称为空");
						modelMap.put("status", "error");
						modelMap.put("data", "所上传的活动计划文件原始名称为空，请重新上传");
					}
				}
			} catch (Exception e) {
				LOGGER.error("上传附件的操作出现异常：{}", e);
				modelMap.put("status", "exception");
				modelMap.put("data", "系统异常，请刷新页面重新请求！");
			}
		}
		response.setCharacterEncoding(Constant.DEFAULT_ENCODE.STRING_UTF8);
		response.setContentType(Constant.DEFAULT_CONTENT_TYPE.CONTENT_TYPE_UTF8);
		response.getWriter().write(this.reverMapToJson(modelMap.getModelMap()));
		return;
	}

	/**
	 * 下载用户所上传的附件
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-11-8 上午01:06:13
	 * @version V1.0
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = "/fileDownload.do")
	public void downloadAttachment(Long fileId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		OutputStream os = response.getOutputStream();
		try {
			if (fileId == null) {
				throw new Exception("传递来的附件参数有误！");
			}
			Attachment attachment = attachmentService.getAttachmentById(fileId);
			if (attachment == null) {
				throw new Exception("附件被删除或者参数有误！");
			}
			// 获取文件信息
			File downFile = new File(
					MappingConstantConfig.getValue("APP_BASE_PATH")
							+ attachment.getFilePath()
							+ attachment.getFileName());
			String originalFileName = attachment.getOriginalFileName();
			// 判断文件是否存在
			if (!downFile.exists()) {
				originalFileName = "您所下载的文件不存在.txt";
				downFile = new File(
						MappingConstantConfig.getValue("APP_BASE_PATH")
								+ attachment.getFilePath() + originalFileName);
			}
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(originalFileName.getBytes(), "ISO_8859_1")
					+ "");
			InputStream fis = new BufferedInputStream(new FileInputStream(
					downFile));
			response.setContentLength(fis.available());
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			os.write(buffer);
			os.flush();
		} catch (Exception e) {
			Map<String, Object> message = new HashMap<String, Object>(2);
			message.put("success", false);
			message.put("msg", e.getMessage());
			JSONObject jsonObject = JSONObject.fromObject(message);
			response.setCharacterEncoding(Constant.DEFAULT_ENCODE.STRING_UTF8);
			response.setContentType(Constant.DEFAULT_CONTENT_TYPE.CONTENT_TYPE_UTF8);
			os.write(jsonObject.toString().getBytes(
					Constant.DEFAULT_ENCODE.STRING_UTF8));
		} finally {
			if (os == null)
				os = response.getOutputStream();
			os.close();
		}
	}

	/**
	 * 下载导出文件
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-11-8 上午01:06:13
	 * @version V1.0
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = "/exportFileDownload.do")
	public void downLoadExportFile(String fileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		OutputStream os = response.getOutputStream();
		try {
			if (fileName == null) {
				throw new Exception("传递来的附件参数有误！");
			}
			String filePath = MappingConstantConfig.getValue("APP_BASE_PATH")
					+ MappingConstantConfig.getValue("EXPORT_PATH") + fileName;
			LOGGER.debug("导出文件的路径为：{}", filePath);

			// 获取文件信息
			File downFile = new File(filePath);
			// 判断文件是否存在
			if (!downFile.exists()) {
				fileName = "您所下载的文件不存在.txt";
				downFile = new File(
						MappingConstantConfig.getValue("APP_BASE_PATH")
								+ MappingConstantConfig.getValue("EXPORT_PATH")
								+ fileName);
			}
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(fileName.getBytes(), "ISO_8859_1") + "");
			InputStream fis = new BufferedInputStream(new FileInputStream(
					downFile));
			response.setContentLength(fis.available());
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			os.write(buffer);
			os.flush();
		} catch (Exception e) {
			Map<String, Object> message = new HashMap<String, Object>(2);
			message.put("success", false);
			message.put("msg", e.getMessage());
			JSONObject jsonObject = JSONObject.fromObject(message);
			response.setCharacterEncoding(Constant.DEFAULT_ENCODE.STRING_UTF8);
			response.setContentType(Constant.DEFAULT_CONTENT_TYPE.CONTENT_TYPE_UTF8);
			os.write(jsonObject.toString().getBytes(
					Constant.DEFAULT_ENCODE.STRING_UTF8));
		} finally {
			if (os == null)
				os = response.getOutputStream();
			os.close();
		}
	}

	/**
	 * 生成json字符串
	 * 
	 * @param map
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2013-11-8 上午12:48:28
	 * @version V1.0
	 */
	private String reverMapToJson(Map<String, Object> map) {
		JSONObject jsonObject = new JSONObject();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			jsonObject.put(entry.getKey(), entry.getValue());
		}
		return jsonObject.toString();
	}

	/**
	 * 判断附件大小是否超过限制
	 * 
	 * @param file
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-11-8 上午12:29:24
	 * @version V1.0
	 */
	private boolean checkImportFileSize(MultipartFile file) {
		// 获取表单项中的文件信息
		if (Integer.parseInt(String.valueOf(file.getSize())) > systemConstant
				.getMaxImportFileSize() * 1024 * 1024)
			return false;
		return true;
	}

	/**
	 * 判断活动计划文件大小是否超过限制
	 * 
	 * @param file
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-11-8 上午12:29:24
	 * @version V1.0
	 */
	private boolean checkActivityFileSize(MultipartFile file) {
		// 获取表单项中的文件信息
		if (Integer.parseInt(String.valueOf(file.getSize())) > systemConstant
				.getMaxActivityFileSize() * 1024 * 1024)
			return false;
		return true;
	}

	/**
	 * 上传图片
	 * 
	 * @param multipartRequest
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = "/fileImageUpload.do")
	public void fileImageUpload(MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		ExtendUsers eUser = (ExtendUsers) request.getSession().getAttribute(
				"CURRENT_USER_INFO");
		if (multipartRequest == null || multipartRequest.getFileNames() == null
				|| eUser == null) {
			LOGGER.error("上传附件操作异常，附件为空或者用户信息为空");
			modelMap.put("status", "exception");
			modelMap.put("data", "上传附件操作异常，附件为空，请重新上传！");
		} else {
			try {
				LOGGER.info("开始上传附件");
				for (Iterator<String> it = multipartRequest.getFileNames(); it
						.hasNext();) {
					String key = (String) it.next();
					MultipartFile file = multipartRequest.getFile(key);
					// 校验附件名称
					if (file.getOriginalFilename().length() > 0) {
						String originalFilename = file.getOriginalFilename();
						String fileExtName = FileUtil.getFileExtName(
								originalFilename, "SUFFIXIMAGE_PATH");
						// 校验附件后缀名
						if (fileExtName != null && !fileExtName.equals("")) {
							// 校验附件大小
							// if (this.checkImportFileSize(file)) {

							String path = MappingConstantConfig
									.getValue("APP_BASE_PATH")
									+ MappingConstantConfig
											.getValue("IMAGE_PATH");
							FolderUtils.createFolder(path);
							LOGGER.info("附件上传路径：{}", path);

							LOGGER.info("原文件名称：{}", originalFilename);

							String fileName = CreateGUID.getGUID() + "."
									+ fileExtName;
							LOGGER.info("新文件名称：{}", fileName);
							File targetFile = new File(path, fileName);
							if (!targetFile.exists()) {
								targetFile.mkdirs();
							}
							// 保存
							file.transferTo(targetFile);
							// 附件信息保存到数据库
							Attachment attachment = new Attachment();
							attachment.setFileName(ActivityStringUtils.cutStr(
									fileName, MappingInputLengthConfig
											.getValue("FILE_NAME_LENGTH")));
							attachment.setFilePath(ActivityStringUtils.cutStr(
									MappingConstantConfig
											.getValue("IMAGE_PATH"),
									MappingInputLengthConfig
											.getValue("FILE_PATH_LENGTH")));
							attachment.setFileType(ActivityStringUtils.cutStr(
									file.getContentType(),
									MappingInputLengthConfig
											.getValue("FILE_TYPE_LENGTH")));
							attachment.setFileSize(file.getSize());
							attachment
									.setOriginalFileName(ActivityStringUtils
											.cutStr(originalFilename,
													MappingInputLengthConfig
															.getValue("ORIGINAL_FILE_NAME_LENGTH")));
							attachment.setCreateUserId(eUser.getCreateUserId());
							attachmentService.addNewAttachment(attachment);
							modelMap.put("status", "success");
							modelMap.put("data", attachment.getId());
							modelMap.put("fileName", originalFilename);
							modelMap.put("fileResultName", fileName);
							// } else {
							// LOGGER.error("所上传的附件大小超过限制：{}M，附件大小为：{}M",
							// systemConstant.getMaxImportFileSize(),
							// file.getSize() / 1024 / 1024);
							// modelMap.put("status", "error");
							// modelMap.put("data", "所上传的附件大小超过限制：" +
							// systemConstant.getMaxImportFileSize() + "M");
							// }
						} else {
							LOGGER.error("附件的后缀名不在允许范围之内：{}", originalFilename);
							modelMap.put("status", "error");
							modelMap.put("data", "附件的后缀名不在允许范围之内！");
						}

					} else {
						LOGGER.error("所上传的附件原始名称为空");
						modelMap.put("status", "error");
						modelMap.put("data", "所上传的附件原始名称为空，请重新上传");
					}
				}
			} catch (Exception e) {
				LOGGER.error("上传附件的操作出现异常：{}", e);
				modelMap.put("status", "exception");
				modelMap.put("data", "系统异常，请刷新页面重新请求！");
			}
		}
		response.setCharacterEncoding(Constant.DEFAULT_ENCODE.STRING_UTF8);
		response.setContentType(Constant.DEFAULT_CONTENT_TYPE.CONTENT_TYPE_UTF8);
		response.getWriter().write(this.reverMapToJson(modelMap.getModelMap()));
		return;
	}

	/**
	 * 下载图片
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-11-8 上午01:06:13
	 * @version V1.0
	 */
	@RequestMapping(method = { RequestMethod.GET }, value = "/downPicFile.do")
	public void downPicFile(String fileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			if (fileName == null) {
				throw new Exception("传递来的附件参数有误！");
			}
			String filePath = MappingConstantConfig.getValue("APP_BASE_PATH")
					+ MappingConstantConfig.getValue("IMAGE_PATH") + fileName;
			LOGGER.debug("导出文件的路径为：{}", filePath);

			// 获取文件信息
			File downFile = new File(filePath);
			// 判断文件是否存在
			if (!downFile.exists()) {
				fileName = "您所下载的文件不存在.txt";
				downFile = new File(
						MappingConstantConfig.getValue("APP_BASE_PATH")
								+ MappingConstantConfig.getValue("IMAGE_PATH")
								+ fileName);
			}
			response.setContentType("text/image");
			response.addHeader("Content-Disposition", "inline; filename=\""
					+ fileName + "\";");
			response.setContentLength((int) downFile.length());
			response.setCharacterEncoding("UTF-8");
			writeFile(response, downFile);

		} catch (Exception e) {
			Map<String, Object> message = new HashMap<String, Object>(2);
			message.put("success", false);
			message.put("msg", e.getMessage());
			response.setCharacterEncoding(Constant.DEFAULT_ENCODE.STRING_UTF8);
			response.setContentType(Constant.DEFAULT_CONTENT_TYPE.CONTENT_TYPE_UTF8);
		}
	}

	/**
	 * 编辑器截图 存储到服务器
	 * 
	 * @Title: fileUploadKindeditor
	 * @Description: TODO
	 * @return void 返回类型
	 * @param multipartRequest
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws
	 * @author zzq
	 * @date 2014年10月10日 下午4:56:25
	 * @version V1.0
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/fileUploadKindeditor.do")
	public void fileUploadKindeditor(
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		if (multipartRequest == null || multipartRequest.getFileNames() == null) {
			LOGGER.error("上传附件操作异常，附件为空或者用户信息为空");
			modelMap.put("status", "exception");
			modelMap.put("data", "上传附件操作异常，附件为空，请重新上传！");
		} else {
			try {
				LOGGER.info("开始上传附件");
				for (Iterator<String> it = multipartRequest.getFileNames(); it
						.hasNext();) {
					String key = (String) it.next();
					MultipartFile file = multipartRequest.getFile(key);
					// 校验附件名称
					if (file.getOriginalFilename().length() > 0) {
						String originalFilename = file.getOriginalFilename();
						String fileExtName = FileUtil.getFileExtName(
								originalFilename, "SUFFIX_PATH");
						// 校验附件后缀名
						if (fileExtName != null && !fileExtName.equals("")) {
							// 校验附件大小
							// if (this.checkImportFileSize(file)) {

							String path = MappingConstantConfig
									.getValue("APP_BASE_PATH")
									+ MappingConstantConfig
											.getValue("UPLOAD_PATH");
							FolderUtils.createFolder(path);
							LOGGER.info("附件上传路径：{}", path);

							LOGGER.info("原文件名称：{}", originalFilename);

							String fileName = CreateGUID.getGUID() + "."
									+ fileExtName;
							LOGGER.info("新文件名称：{}", fileName);
							File targetFile = new File(path, fileName);
							if (!targetFile.exists()) {
								targetFile.mkdirs();
							}
							// 保存
							file.transferTo(targetFile);
							// 附件信息保存到数据库
							// Attachment attachment = new Attachment();
							// attachment.setFileName(ActivityStringUtils.cutStr(fileName,
							// MappingInputLengthConfig.getValue("FILE_NAME_LENGTH")));
							// attachment.setFilePath(ActivityStringUtils.cutStr(MappingConstantConfig.getValue("UPLOAD_PATH"),
							// MappingInputLengthConfig.getValue("FILE_PATH_LENGTH")));
							// attachment.setFileType(ActivityStringUtils.cutStr(file.getContentType(),
							// MappingInputLengthConfig.getValue("FILE_TYPE_LENGTH")));
							// attachment.setFileSize(file.getSize());
							// attachment.setOriginalFileName(ActivityStringUtils.cutStr(originalFilename,
							// MappingInputLengthConfig.getValue("ORIGINAL_FILE_NAME_LENGTH")));
							// attachment.setCreateUserId(eUser.getCreateUserId());
							// attachmentService.addNewAttachment(attachment);
							modelMap.put("status", "success");
							// modelMap.put("data", attachment.getId());
							modelMap.put("fileName", originalFilename);
							modelMap.put("fileExtName", fileExtName);
							// } else {
							// LOGGER.error("所上传的附件大小超过限制：{}M，附件大小为：{}M",
							// systemConstant.getMaxImportFileSize(),
							// file.getSize() / 1024 / 1024);
							// modelMap.put("status", "error");
							// modelMap.put("data", "所上传的附件大小超过限制：" +
							// systemConstant.getMaxImportFileSize() + "M");
							// }
						} else {
							LOGGER.error("附件的后缀名不在允许范围之内：{}", originalFilename);
							modelMap.put("status", "error");
							modelMap.put("data", "附件的后缀名不在允许范围之内！");
						}

					} else {
						LOGGER.error("所上传的附件原始名称为空");
						modelMap.put("status", "error");
						modelMap.put("data", "所上传的附件原始名称为空，请重新上传");
					}
				}
			} catch (Exception e) {
				LOGGER.error("上传附件的操作出现异常：{}", e);
				modelMap.put("status", "exception");
				modelMap.put("data", "系统异常，请刷新页面重新请求！");
			}
		}
		response.setCharacterEncoding(Constant.DEFAULT_ENCODE.STRING_UTF8);
		response.setContentType(Constant.DEFAULT_CONTENT_TYPE.CONTENT_TYPE_UTF8);
		response.getWriter().write(this.reverMapToJson(modelMap.getModelMap()));
		return;
	}

	private void writeFile(HttpServletResponse response, File file) {
		InputStream oInput = null;
		OutputStream oOutput = null;
		try {
			oInput = new FileInputStream(file);
			oOutput = response.getOutputStream();

			// TipsLogger.log("Streaming to output buffer +++ START +++");
			byte[] oBuff = new byte[1024];
			int iSize;
			while (-1 != (iSize = oInput.read(oBuff))) {
				oOutput.write(oBuff, 0, iSize);
			}
			// TipsLogger.log("Streaming to output buffer +++ END +++");

			oOutput.flush();
		} catch (IOException ioe) {
			LOGGER.info("", ioe);
		} catch (Exception e) {
			LOGGER.info("", e);
		} finally {
			if (oInput != null) {
				try {
					oInput.close();
				} catch (IOException e) {
				}
			}
			if (oOutput != null) {
				try {
					oOutput.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
