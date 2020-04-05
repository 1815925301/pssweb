package com.sinosoft.common.constant;

public class EnumRoleButton {
	
	//按鈕可用
	public static final int canUse=1;
	
	//按鈕不可用
	public static final int notUse=0;
	
	//根据id判断按钮是否可用
	public static int getUserValue(String roleid){
		String[] roles = MappingConstantConfig.getValue("ROLES").split(",");
		for(String str : roles){
			if(str.equals(String.valueOf(roleid))){
				return canUse;
			}
		}
		return notUse;
	}
	//根据id判断帮助修改删除新增按钮是否可用
	public static int getHelpUserValue(String roleid){
		String[] roles = MappingConstantConfig.getValue("HELPROLES").split(",");
		for(String str : roles){
			if(str.equals(String.valueOf(roleid))){
				return canUse;
			}
		}
		return notUse;
	}
}
