package com.sinosoft.base.po;

import java.util.List;

/**
 * easyui combotree json格式数据
 * @author jinyu
 *
 */
public class ComboTree {
   public String id;
   public String text;
   public List<ComboTree> children;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public List<ComboTree> getChildren() {
	return children;
}
public void setChildren(List<ComboTree> children) {
	this.children = children;
}

   
}
