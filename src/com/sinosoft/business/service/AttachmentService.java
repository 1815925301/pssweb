package com.sinosoft.business.service;

import com.sinosoft.business.po.Attachment;

/**
 * @Package com.sinosoft.business.service
 * @ClassName: AttachmentService
 * @Description: 系统附件信息 服务层 接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-11-1 下午01:49:42
 */
public interface AttachmentService {
	
	/**
	 * 根据附件id获取附件信息
	 * @param id
	 * @return Attachment
	 * @throws
	 * @author zzq
	 * @date 2013-11-4 下午07:19:30
	 * @version V1.0
	 */
	public Attachment getAttachmentById(Long id);
	
	/**
	 * 新增附件信息
	 * @param Attachment
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-11-4 下午07:19:56
	 * @version V1.0
	 */
	public Integer addNewAttachment(Attachment attachment);
	
	/**
	 * 根据附件id删除附件信息 物理删除
	 * @param attachmentId
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-11-4 下午07:20:27
	 * @version V1.0
	 */
	public Integer deleteAttachmentByAttachmentId(Long attachmentId);
	
	/**
	 * 修改附件信息
	 * @param Attachment
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2014-2-26 上午01:26:07
	 * @version V1.0
	 */
	public Integer updateAttachment(Attachment attachment);

}
