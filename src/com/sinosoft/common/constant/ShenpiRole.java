package com.sinosoft.common.constant;

import java.util.ArrayList;
import java.util.List;

public class ShenpiRole {
	public static final Long zhushen = (long) 13; // 主审人
	public static final List<Long> qita =new ArrayList<Long>();//其他人
	public static final Long xiaozhang = (long) 10; // 主审人
	public static Long getZhushen() {
		return zhushen;
	}
	public static List<Long> getQita() {
		List<Long> qita =new ArrayList<Long>();
		Long t1=(long)3;
		Long t2=(long)4;
		Long t3=(long)5;
		Long t4=(long)6;
		Long t5=(long)7;
		Long t6=(long)8;
		Long t7=(long)9;
		Long t8=(long)11;
		Long t9=(long)12;
		Long t10=(long)14;
		Long t11=(long)15;
		Long t12=(long)16;
		qita.add(t1);
		qita.add(t2);
		qita.add(t3);
		qita.add(t4);
		qita.add(t5);
		qita.add(t6);
		qita.add(t7);
		qita.add(t8);
		qita.add(t9);
		qita.add(t10);
		qita.add(t11);
		qita.add(t12);
		return qita;
	}
	public static Long getXiaozhang() {
		return xiaozhang;
	}
	
}
