package com.sinosoft.business.dao;

import java.util.List;

import com.sinosoft.business.po.Attachment;
import com.sinosoft.business.po.query.AttachmentQuery;

/**
 * @Package com.sinosoft.business.dao
 * @ClassName: AttachmentDao
 * @Description: 系统附件信息 服务层接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-11-4 下午05:40:09
 */
public interface AttachmentDao {
	
	/**
	 * 根据附件id获取附件信息
	 * @param attachmentId
	 * @return Attachment
	 * @throws
	 * @author zzq
	 * @date 2013-11-4 下午05:47:08
	 * @version V1.0
	 */
	public Attachment getAttachmentById(Long attachmentId);
	
	/**
	 * 根据检索条件获取附件的记录信息
	 * @param attachmentQuery
	 * @return List<Attachment>
	 * @throws
	 * @author zzq
	 * @date 2013-11-4 下午05:49:25
	 * @version V1.0
	 */
	public List<Attachment> getAttachmentInfoByQuery(AttachmentQuery attachmentQuery);
	
	/**
	 * 新增附件信息
	 * @param Attachment
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-19 下午03:13:43
	 * @version V1.0
	 */
	public Integer addNewAttachment(Attachment attachment);
	
	/**
	 * 根据附件id更新附件信息
	 * @param Attachment
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-19 下午03:13:46
	 * @version V1.0
	 */
	public Integer updateAttachmentById(Attachment attachment);
	
	/**
	 * 根据附件id删除附件信息 物理删除
	 * @param attachmentId
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-19 下午03:13:49
	 * @version V1.0
	 */
	public Integer deleteAttachmentById(Long attachmentId);
	
}
