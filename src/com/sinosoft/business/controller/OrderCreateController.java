package com.sinosoft.business.controller;

import java.util.Locale;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.business.service.OrderCreateService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;

/**
 * 订单生成页面
 * 
 * @author Dylan
 * @date 16-08-20 14:15:50
 */
@Controller
public class OrderCreateController {

	@Resource
	private OrderCreateService orderCreateService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderCreateController.class);

	/**
	 * 订单生成页面初始化
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/orderCreate.do")
	public String initOrderCreatePage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		model.addAttribute("pageHanName", "底层子任务"); // 页面名称
		orderCreateService.initOrderCreatePage(model, "POST", request);
		ActivityWebUtils.WrapperModle(request, model);
		return "manage/" + model.get("pageName");
	}

	/**
	 * 订单生成（新增订单信息）
	 * 
	 * @param request
	 * @param response
	 * @param orderIds
	 * @param orderName
	 * @param remark
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/addOrder.do")
	public @ResponseBody
	Map<String, Object> addOrder(HttpServletRequest request,
			HttpServletResponse response, String orderIds, String orderName,
			String remark) {
		LOGGER.debug("生成订单信息！");
		
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (orderCreateService.addOrderCreate(request, modelMap, orderIds,
					orderName, remark)) {
				modelMap.put("status", "success");
				modelMap.put("data", PropertiesUtils.key("ordersuccess", locale));
			} else {
				modelMap.put("status", "exception");
			}

		} catch (Exception e) {
			LOGGER.error("生成订单信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}

}
