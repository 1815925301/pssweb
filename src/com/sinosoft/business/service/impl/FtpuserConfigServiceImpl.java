

package com.sinosoft.business.service.impl;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.FtpuserConfigDao;
import com.sinosoft.business.po.FtpuserConfig;
import com.sinosoft.business.po.query.FtpuserConfigQuery;
import com.sinosoft.business.service.FtpuserConfigService;
import com.sinosoft.common.web.ActivityModelMap;
/**
 * @Package com.sinosoft.business.service.impl
 * @ClassName: FtpuserConfigServiceImpl
 * @Description: VIP用户管理  MVC控制层service入口
 * @author wlg
 * @Version V1.0
 * @date 2016-08-20
 */

@Service("ftpuserConfigService")
public class FtpuserConfigServiceImpl implements FtpuserConfigService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PssOrderInfoServiceImpl.class);
	@Resource
	private FtpuserConfigDao ftpuserConfigDao;

	/**
	 * 页面初始化
	 */
	@Override
	public void ftpuserConfigPageInit(HttpServletRequest request,ModelMap model,String method){
		LOGGER.debug("Service层:查询vip用户");
		FtpuserConfigQuery ftpuserConfigQuery=new FtpuserConfigQuery();
		ftpuserConfigQuery.setSortBy("id");
		ftpuserConfigQuery.setSortType("2");
		if(method.equals("POST")){
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				ftpuserConfigQuery.setPage(Integer.parseInt(pageNum));
			}
			String cusernameInput=request.getParameter("cusernameInput");
			if(!StringUtils.isBlank(cusernameInput)){
				ftpuserConfigQuery.setCusername(cusernameInput);
				model.addAttribute("cusernameInput", cusernameInput);
			}
			String transtypeInput=request.getParameter("transtypeInput");
			if(!StringUtils.isBlank(transtypeInput)){
				ftpuserConfigQuery.setTranstype(transtypeInput);
				model.addAttribute("transtypeInput", transtypeInput);
			}
			String companyInput=request.getParameter("companyInput");
			if(!StringUtils.isBlank(companyInput)){
				ftpuserConfigQuery.setCompany(companyInput);
				model.addAttribute("companyInput", companyInput);
			}
		}
		Integer totalCount = ftpuserConfigDao.getCountByQuery(ftpuserConfigQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount,
				ftpuserConfigQuery.getPageSize(), ftpuserConfigQuery.getPage(),
				ftpuserConfigQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<FtpuserConfig> ftpuserList = ftpuserConfigDao.getFtpuserConfigInfoByQuery(ftpuserConfigQuery);
		model.addAttribute("ftpuserList", ftpuserList);
	}

	/** 
	 * 创建FtpuserConfig
	 **/
	@Override
	public Boolean save(HttpServletRequest request,FtpuserConfig ftpuserConfig,ActivityModelMap modelMap){
		boolean result =false;
		Integer resultNum=ftpuserConfigDao.insertFtpuserConfig(ftpuserConfig);
		if(resultNum!=null){
			result=true;
			LOGGER.debug("新增成功！");
		}
		return result;
	}
	/** 
	 * 删除FtpuserConfig
	 **/
	@Override
    public boolean remove(Long id,HttpServletRequest request,ActivityModelMap modelMap){
		boolean result = false;
		Integer resultNum = ftpuserConfigDao.deleteFtpuserConfig(id);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}
    

}
