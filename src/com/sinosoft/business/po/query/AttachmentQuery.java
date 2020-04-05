package com.sinosoft.business.po.query;

import com.sinosoft.po.query.BasePaginationQuery;

/**
 * @Package com.sinosoft.business.po.query
 * @ClassName: AttachmentQuery
 * @Description: 系统附件 Query类
 * @author zzq
 * @Version V1.0
 * @date 2013-11-4 下午05:45:18
 */
public class AttachmentQuery extends BasePaginationQuery {

	private Long id; // 附件id
	private String fileName; // 文件名称
	private String originalFileName; // 文件原始名称
	private String fileType; // 文件类型
	private String filePath; // 文件相对路径

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

}
