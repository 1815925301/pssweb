package com.sinosoft.common.constant;

public class DealConstant {

	// 0 未审批 1 主审审批 2chuzhang审批3keshi审批人 4校长5 审批完成
	public static final int undeal = 0;
	public static final int zhushenshnepi = 1;
	public static final int chuzhangshenpi = 3;
	public static final int keshishenpi =2;
	public static final int xiaozhangshenpi = 4;
	public static final int shenpifinish = 5;

	private static final String unDeal = " 未审批";
	private static final String zhushenDealed = "主审已审批";
	private static final String chuzhangDealed = "处长已审批";
	private static final String keshiDealed = "科室已审批";
	private static final String xiaozhangDealed = "校长已审批";
	private static final String Dealed = "审批通过";

	private static final String zhushenapproval = "主审审批";
	private static final String chuzhangapproval = "处长审批";
	private static final String keshiapproval = "科室审批";
	private static final String xiaozhangapproval = "校长审批";

	public static String getUndeal() {
		return unDeal;
	}

	public static String getZhushendealed() {
		return zhushenDealed;
	}

	public static String getChuzhangdealed() {
		return chuzhangDealed;
	}

	public static String getKeshidealed() {
		return keshiDealed;
	}

	public static String getXiaozhangdealed() {
		return xiaozhangDealed;
	}

	public static String getDealed() {
		return Dealed;
	}

	public static String getZhushenapproval() {
		return zhushenapproval;
	}

	public static String getChuzhangapproval() {
		return chuzhangapproval;
	}

	public static String getKeshiapproval() {
		return keshiapproval;
	}

	public static String getXiaozhangapproval() {
		return xiaozhangapproval;
	}

	public static int getZhushenshnepi() {
		return zhushenshnepi;
	}

	public static int getChuzhangshenpi() {
		return chuzhangshenpi;
	}

	public static int getKeshishenpi() {
		return keshishenpi;
	}

	public static int getXiaozhangshenpi() {
		return xiaozhangshenpi;
	}

	public static int getShenpifinish() {
		return shenpifinish;
	}
	
	

}
