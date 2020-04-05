package com.sinosoft.business.service;

import java.util.List;

import com.sinosoft.business.po.PubZoncode;
import com.sinosoft.business.po.query.PubZoncodeQuery;

/**
 * 获取省、市、县、乡
* @ClassName: PubZoncodeServer 
* @Description: TODO
* @author zzq
* @mail zzq1229@126.com 
* @date 2014年8月8日 上午11:10:25 
*
 */
public interface PubZoncodeServer {
	/**
	 * 根据条件获取数据
	* @Title: getPubZonCodeList 
	* @Description: TODO
	* @return List<PubZoncode>    返回类型 
	* @param pzq
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年8月8日 上午11:12:31 
	* @version V1.0
	 */
	public List<PubZoncode> getPubZonCodeList(PubZoncodeQuery pzq);
	
	/**
	 * 根据主键获取数据
	* @Title: getPubZonCodeById 
	* @Description: TODO
	* @return PubZoncode    返回类型 
	* @param id
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年8月8日 上午11:13:08 
	* @version V1.0
	 */
	public PubZoncode getPubZonCodeById(String zonecode);
	
}
