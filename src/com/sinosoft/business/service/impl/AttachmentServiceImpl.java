package com.sinosoft.business.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sinosoft.business.dao.AttachmentDao;
import com.sinosoft.business.po.Attachment;
import com.sinosoft.business.service.AttachmentService;

/**
 * @Package com.sinosoft.business.service.impl
 * @ClassName: AttachmentServiceImpl
 * @Description: 系统附件信息 服务层 实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-11-4 下午07:39:24
 */
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentServiceImpl.class);
	
	@Resource
	private AttachmentDao attachmentDao;

	@Override
	public Attachment getAttachmentById(Long id) {
		LOGGER.debug("Service层：根据附件id获取附件信息");
		return attachmentDao.getAttachmentById(id);
	}

	@Override
	public Integer addNewAttachment(Attachment attachment) {
		LOGGER.debug("Service层：新增附件信息");
		return attachmentDao.addNewAttachment(attachment);
	}

	@Override
	public Integer deleteAttachmentByAttachmentId(Long attachmentId) {
		LOGGER.debug("Service层：根据附件id删除附件信息 物理删除");
		return attachmentDao.deleteAttachmentById(attachmentId);
	}
	
	@Override
	public Integer updateAttachment(Attachment attachment) {
		LOGGER.debug("Service层：更新附件信息");
		return attachmentDao.updateAttachmentById(attachment);
	}

}
