package com.sinosoft.common.calendar;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Package com.sinosoft.common.calendar
 * @ClassName: ActivityCalendar
 * @Description: 日历生成服务类  完成的功能：输入年份和月份，返回该月的日期信息，包括农历、星期
 * @author zzq
 * @Version V1.0
 * @date 2013-11-25 下午02:04:03
 */
@Service("activityCalendarService")
public class ActivityCalendarService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ActivityCalendarService.class);
	
	/**
	 * 根据年份与月份 获取日历信息
	 * @param currentMonth
	 * @param currentYear
	 * @return List<CalenderWarning>
	 * @throws
	 * @author zzq
	 * @date 2013-11-25 下午03:45:26
	 * @version V1.0
	 */
	public List<CalenderWarning> getActivityCalendar(Integer currentYear, Integer currentMonth) {
		currentMonth = currentMonth - 1;
		LOGGER.debug("根据年份与月份 获取日历信息：年份 {} 月份 {}", currentYear, currentMonth);
		List<CalenderWarning> calenderList = new ArrayList<CalenderWarning>();
		List<CalenderWarning> calenderMiddleList = getDayInfo(getMonthWeekNum(currentYear, currentMonth, 1), currentYear, currentMonth, false);
		if (calenderMiddleList != null && calenderMiddleList.size() > 0) {
			int beforeNum = calenderMiddleList.get(0).getWeek() - 1;
			if (beforeNum != 0) {
				List<CalenderWarning> calenderBeforeList = null;
				//需要获取上一个月最后 beforeNum 天的日期
				if (currentMonth > 0) {
					calenderBeforeList = getDayInfo(getMonthWeekNum(currentYear, currentMonth - 1, 1), currentYear, currentMonth - 1, true);
				} else {
					//得挪到上一年了
					calenderBeforeList = getDayInfo(getMonthWeekNum(currentYear - 1, 11, 1), currentYear - 1, 11, true);
				}
				if (calenderBeforeList != null && calenderBeforeList.size() > beforeNum) {
					calenderList.addAll(calenderBeforeList.subList(calenderBeforeList.size() - beforeNum, calenderBeforeList.size()));
				}
			}
			calenderList.addAll(calenderMiddleList);
			int afterNum = 7 - calenderMiddleList.get(calenderMiddleList.size() - 1).getWeek();
			if (afterNum != 0) {
				List<CalenderWarning> calenderAfterList = null;
				//需要获取下一个月最初 afterNum 天的日期信息
				if (currentMonth + 1 < 12) {
					calenderAfterList = getDayInfo(getMonthWeekNum(currentYear, currentMonth + 1, 1), currentYear, currentMonth + 1, true);
				} else {
					//得挪到下一年了
					calenderAfterList = getDayInfo(getMonthWeekNum(currentYear + 1, 0, 1), currentYear + 1, 0, true);
				}
				if (calenderAfterList != null && calenderAfterList.size() > afterNum) {
					calenderList.addAll(calenderAfterList.subList(0, afterNum));
				}
			}
		}
		return calenderList;
	}

	public static void main(String[] args) {
		ActivityCalendarService aa = new ActivityCalendarService();
		List<CalenderWarning> calenderList = aa.getActivityCalendar(2013, 10);
		if (calenderList != null && calenderList.size() > 0) {
			for (CalenderWarning cw : calenderList) {
				System.out.println(cw.toString());
			}
		}
	}

	/**
	 * 得到月份第N天的星期
	 * @param ActivityCalendarService
	 * @return int
	 * @throws
	 * @author zzq
	 * @date 2013-11-25 下午03:49:26
	 * @version V1.0
	 */
	public int getMonthWeekNum(int reyear, int remonth, int monthDateNum) {
		Calendar now = Calendar.getInstance(); // 实例化Calendar
		now.set(reyear, remonth, monthDateNum); // 设置时间为所要查询的年月的第N天
		return (int) (now.get(Calendar.DAY_OF_WEEK));// 得到第一天的星期
	}

	/**
	 * 
	 * @param ActivityCalendarService
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-11-25 下午03:49:31
	 * @version V1.0
	 */
	public List<CalenderWarning> getDayInfo(int weekNum, int currentYear, int currentMonth, boolean isGrep) {
		int monthDayCount = 0; // 月份的天数
		int count = 1;
		String[] LunarDate = new String[49];

		// now MONTH是从0开始的, 对于一月第几天来说，DAY_OF_MONTH第一天就是1.
		// 对于一年第几个月来说，MONTH一月份是0，二月份是1...
		@SuppressWarnings("deprecation")
		Date date = new Date(currentYear, currentMonth + 1, 1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1); // 前个月
		monthDayCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 最后一天

		weekNum = weekNum + 6; // 将星期数加6，使显示正确
		monthDayCount = monthDayCount + weekNum;

		Lunar lunar = new Lunar();
		for (int i = 0; i < monthDayCount; i++) {
			LunarDate[i] = lunar.getLunarDate(currentYear, currentMonth + 1, i + 1);
		}
		List<CalenderWarning> calenderList = new ArrayList<CalenderWarning>();
		CalenderWarning calender = null;
		for (int i = weekNum; i < monthDayCount; i++, count++) {
			calender = new CalenderWarning();
			calender.setDateStr(currentYear + "-" + (currentMonth + 1) + "-" + count);
			calender.setGrep(isGrep);
			calender.setLunar(LunarDate[i - weekNum]);
			//获取星期
			Integer week = getMonthWeekNum(currentYear, currentMonth, count) - 1;
			if (week == 0) {
				week = 7;
			}
			calender.setWeek(week);
			if (currentMonth == 9 && count == 1) {
				calender.setDate("国庆");
			} else if (currentMonth == 0 && count == 1) {
				calender.setDate("元旦");
			} else if (currentMonth == 11 && count == 24) {
				calender.setDate("平安夜");
			} else if (currentMonth == 11 && count == 25) {
				calender.setDate("圣诞");
			} else if (currentMonth == 1 && count == 14) {
				calender.setDate("情人节");
			} else if (currentMonth == 3 && count == 1) {
				calender.setDate("愚人节");
			} else if (currentMonth == 4 && count == 1) {
				calender.setDate("劳动节");
			} else {
				calender.setDate(count + "");
			}
			calenderList.add(calender);
		}
		return calenderList;
	}

}
