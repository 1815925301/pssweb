package com.sinosoft.common.calendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.sinosoft.common.calendar
 * @ClassName: CalenderWarning
 * @Description: 日历单元信息 与提醒信息
 * @author zzq
 * @Version V1.0
 * @date 2013-11-25 下午03:27:51
 */
public class CalenderWarning implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String dateStr; // 日期字符串 格式为 yyyy-MM-dd
	private String date; // 阳历日期数字
	private String lunar; // 农历日期
	private Integer week; // 星期
	private boolean isGrep; // 在页面上是否置灰
	private String className; // 在页面中的样式
	private List<String> activityInfo = new ArrayList<String>(); // 待举办的活动
	private List<String> waitingWorkInfo = new ArrayList<String>(); // 待处理的工作
	private List<String> alreadyWorkInfo = new ArrayList<String>(); // 已完成的工作

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLunar() {
		return lunar;
	}

	public void setLunar(String lunar) {
		this.lunar = lunar;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public List<String> getActivityInfo() {
		return activityInfo;
	}

	public boolean getIsGrep() {
		return isGrep;
	}

	public void setGrep(boolean isGrep) {
		this.isGrep = isGrep;
	}

	public void setActivityInfo(List<String> activityInfo) {
		this.activityInfo = activityInfo;
	}

	public List<String> getWaitingWorkInfo() {
		return waitingWorkInfo;
	}

	public void setWaitingWorkInfo(List<String> waitingWorkInfo) {
		this.waitingWorkInfo = waitingWorkInfo;
	}

	public List<String> getAlreadyWorkInfo() {
		return alreadyWorkInfo;
	}

	public void setAlreadyWorkInfo(List<String> alreadyWorkInfo) {
		this.alreadyWorkInfo = alreadyWorkInfo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public void addActivityInfo(String activityInfo) {
		this.activityInfo.add(activityInfo);
	}
	
	public void addWaitingWorkInfo(String workInfo) {
		this.waitingWorkInfo.add(workInfo);
	}
	
	public void addAlreadyWorkInfo(String workInfo) {
		this.alreadyWorkInfo.add(workInfo);
	}

	public String toString() {
		return "日期: " + dateStr + "; 阳历日期数字: " + date + "; 农历日期: " + lunar
				+ "; 星期: " + week + "; 是否置灰：" + isGrep;
	}

}
