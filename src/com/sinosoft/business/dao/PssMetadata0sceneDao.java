/*
 * Powered By 尹力
 * Since 2015 - 2016-56-25
 */

package com.sinosoft.business.dao;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssMetadata0scene;
import com.sinosoft.business.po.PssProduct;
import com.sinosoft.business.po.query.PssMetadata0sceneQuery;


public interface PssMetadata0sceneDao {

	PssMetadata0scene selectPssMetadata0sceneQueryById(PssMetadata0scene pssMetadata0scene);

	PssMetadata0scene getScensByqueryById(String metadataid);

	
}