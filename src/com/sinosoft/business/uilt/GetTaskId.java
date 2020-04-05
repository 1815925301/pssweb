package com.sinosoft.business.uilt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class GetTaskId{
		
	private static Map<String,String> map=new HashMap<String, String>(); 
	private static String STATNUM="000001";
	public String getDateNum(String xulei){
		StringBuffer sb=new StringBuffer();
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR); 
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DATE); 
		sb.append(year);
		if(month<10){
			sb.append("0"+month);
		}else{
			sb.append(month);
	}
		if(day<10){
			sb.append("0"+day);
		}else{
			sb.append(day);
		}
		sb.append(xulei);
	return sb.toString();
	}

	public String getLastSixNum(String s){
		String rs=s;
		int i=Integer.parseInt(rs);
		i+=1;
		rs=""+i;
		for (int j = rs.length(); j <6; j++) {
			rs="0"+rs;
		} 
			return rs; 
		}
		public synchronized String getNum(String xulei){
			String dateNum=getDateNum(xulei);
			String last6Num=map.get(dateNum);
			if(last6Num==null){
				map.put(dateNum,STATNUM);
			}else{
				map.put(dateNum,getLastSixNum(last6Num));
			}
			return dateNum+map.get(dateNum);
		}
} 