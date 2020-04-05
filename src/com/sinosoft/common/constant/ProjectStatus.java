package com.sinosoft.common.constant;

public class ProjectStatus {

	public static final int SHENBAO = 0; // 申报
	public static final int SHENPI = 1; // 审批
	public static final int ANPAI = 2; // 安排
	public static final int FABU = 3; // 发布
	public static final int RESULT = 4; // 结果
	public static final int GUIDANG = 5; // 归档
	public static final int FEIBIAO = 6; //废标

//	private static final String shenbao = "申报";
//	private static final String shenpi = "审批";
//	private static final String anpai = "安排";
//	private static final String fabu = "发布";
//	private static final String result = "结果";
//	private static final String guidang = "归档";
	
	private static final String shenbao = "审批";
	private static final String shenpi = "安排";
	private static final String anpai = "发布";
	private static final String fabu = "填报结果";
	private static final String result = "归档";
	private static final String guidang = "归档完成";
	private static final String feibiao = "已废标";

	public static String getValue(int value) {
		if (value == SHENBAO) {
			return shenbao;
		}
		if (value == SHENPI) {
			return shenpi;
		}
		if (value == ANPAI) {
			return anpai;
		}
		if (value == FABU) {
			return fabu;
		}
		if (value == GUIDANG) {
			return guidang;
		}
		if (value == RESULT) {
			return result;
		}
		if (value == FEIBIAO){
			return feibiao;
		}
		return null;
	}

	public static int getShenbao() {
		return SHENBAO;
	}

	public static int getShenpi() {
		return SHENPI;
	}

	public static int getAnpai() {
		return ANPAI;
	}

	public static int getFabu() {
		return FABU;
	}

	public static int getGuidang() {
		return GUIDANG;
	}

	public static String getResult() {
		return result;
	}
	
	

}
