package com.sinosoft.business.po;

import java.io.Serializable;

/**
 * @Package com.sinosoft.business.po
 * @ClassName: Attachment
 * @Description: 系统附件信息 实体类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-30 下午01:10:59
 */
public class Attachment implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Long id; // 主键id
	private String fileName; // 文件名称
	private String originalFileName; // 文件原始名称
	private String fileType; // 文件类型
	private String filePath; // 文件相对路径
	private Long fileSize; // 文件大小
	private String profile; // 文件摘要
	private Long createUserId; // 创建记录用户id
	private String createTime; // 创建记录时间
	private Long updateUserId; // 修改记录用户的id
	private String updateTime; // 修改记录时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
