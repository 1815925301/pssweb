package com.sinosoft.business.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.sinosoft.business.uilt.AnalysisFileShow;
import com.sinosoft.common.web.ActivityModelMap;

@Controller
public class AnalysisDataController {
	@RequestMapping(method = RequestMethod.POST, value = "/analysisData.do")
	public @ResponseBody Map<String, Object> returnData(HttpServletRequest request){
		String path = System.getProperty("user.dir").replace("bin", "webapps")+"\\pssweb\\upload\\TAS_TPPS_BAEP_201612211728.xml";
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			String area = AnalysisFileShow.parseArea(path);
			modelMap.put("area", area);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return modelMap.getModelMap();
	}
}
