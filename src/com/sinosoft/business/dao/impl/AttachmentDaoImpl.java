package com.sinosoft.business.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.AttachmentDao;
import com.sinosoft.business.po.Attachment;
import com.sinosoft.business.po.query.AttachmentQuery;

/**
 * @Package com.sinosoft.business.dao.impl
 * @ClassName: AttachmentDaoImpl
 * @Description: 系统附件信息 服务层实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-11-4 下午05:51:02
 */
@Repository(value="attachmentDao")
public class AttachmentDaoImpl extends BaseDao implements AttachmentDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentDaoImpl.class);

	@Override
	public Attachment getAttachmentById(Long id) {
		LOGGER.debug("Dao层：根据附件id获取附件信息");
		return getReadSqlSession().selectOne("attachmentDao.selectAttachmentById", id);
	}
	
	@Override
	public List<Attachment> getAttachmentInfoByQuery(AttachmentQuery attachmentQuery) {
		LOGGER.debug("Dao层：根据检索条件获取附件的记录信息");
		return getReadSqlSession().selectList("attachmentDao.selectAttachmentInfoByQuery", attachmentQuery);
	}

	@Override
	public Integer addNewAttachment(Attachment attachment) {
		LOGGER.debug("Dao层：新增附件信息");
		return insert("attachmentDao.insertNewAttachment", attachment);
	}

	@Override
	public Integer updateAttachmentById(Attachment attachment) {
		LOGGER.debug("Dao层：根据记录ID更新附件信息");
		return update("attachmentDao.updateAttachmentById", attachment);
	}

	@Override
	public Integer deleteAttachmentById(Long attachmentId) {
		LOGGER.debug("Dao层：根据记录ID删除附件信息 物理删除");
		return delete("attachmentDao.deleteAttachmentById", attachmentId);
	}
	
}
