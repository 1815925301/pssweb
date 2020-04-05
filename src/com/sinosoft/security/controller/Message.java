package com.sinosoft.security.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// url映射的名称
public class Message {
	
	@RequestMapping(value = "/test")
	public String addMessage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("ctx", request.getContextPath()); //应用名称
		model.addAttribute("pageHanName", "工作提醒"); //页面名称
		List<Channel> channelList = new ArrayList<Channel>();
		Channel c1 = new Channel(1,"天然");
		Channel c2 = new Channel(2,"有机");
		Channel c3 = new Channel(3,"牛奶");
		Set<Channel> child = new HashSet<Channel>();
		child.add(c3);
		c1.setChild(child);
		channelList.add(c1);channelList.add(c2);channelList.add(c3);
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		model.addAttribute("channelIds", set);
		model.addAttribute("channelList", channelList);
		model.addAttribute("message", "设置返回消息add.do "); // 设置返回消息
		return "manage/test";
	}
	
	
}
