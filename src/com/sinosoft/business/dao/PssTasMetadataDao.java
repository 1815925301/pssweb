

package com.sinosoft.business.dao;
import java.util.List;
import java.util.Map;

import com.sinosoft.business.po.PssTasMetadata;
import com.sinosoft.business.po.query.PssTasMetadataQuery;


public interface PssTasMetadataDao {
	
	/**
     *按条件查询
     */
   
	public List<PssTasMetadataQuery> getPssTasMetadataByQuery(PssTasMetadataQuery pssTasMetadataquery);

	public PssTasMetadata getTasMetadataByid(PssTasMetadataQuery metadataQuery);

	public PssTasMetadata selectTasPssMetadataById(PssTasMetadata pssTasMetadata);

	
	/**
	 * 根据条件查询子订单
	 * 
	 * @author Dylan
	 * @param condMap
	 *            Map<String, Object> 主订单id和子订单id(支持多个及单个子订单id查询)
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> findSonProductByCond(
			String id);

}