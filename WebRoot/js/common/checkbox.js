/**
 * checkBox 全选及组选插件 1.0
 * 使用需遵循以下html结构，具体记录的checkbox的id和value都为记录id
 * 
 * 1、有组选的情况
 * <input type="checkbox" class="checkbox-all" onclick="checkBox.selectAll(this)"/> 全选checkbox
 * <div class="checkbox-group-content">
 *   <input type="checkbox" class="checkbox-items" onclick="checkBox.selectGroup(this)"/> 组选checkbox
 * 
 *   <input type="checkbox" class="checkbox-item" id="${id}" value="${id}" onclick="checkBox.selectOne(this)"/> 记录1 checkbox
 *   <input type="checkbox" class="checkbox-item" id="${id}" value="${id}" onclick="checkBox.selectOne(this)"/> 记录2 checkbox
 *   <input type="checkbox" class="checkbox-item" id="${id}" value="${id}" onclick="checkBox.selectOne(this)"/> 记录2 checkbox
 * 	 ...                                                                                                        ...
 * </div>    
 * 
 * 2、没有组选的情况
 * <input type="checkbox" class="checkbox-all" onclick="checkBox.selectAll(this)"/> 全选checkbox
 * 
 * <input type="checkbox" class="checkbox-item" id="${id}" value="${id}" onclick="checkBox.selectOne(this)"/> 记录1 checkbox
 * <input type="checkbox" class="checkbox-item" id="${id}" value="${id}" onclick="checkBox.selectOne(this)"/> 记录2 checkbox
 * <input type="checkbox" class="checkbox-item" id="${id}" value="${id}" onclick="checkBox.selectOne(this)"/> 记录2 checkbox
 * ...  
 */
var checkBox = {
	/**
	 * 选一个的class
	 */
	boxItemClass : ".checkbox-item",
	/**
	 * 选一组的class
	 */
	boxGourpClass : ".checkbox-items",
	/**
	 * 全选的class
	 */
	boxAllClass : ".checkbox-all",
	/**
	 * 包含this.boxGourpClass和this.boxItemClass的标签
	 */
	boxGroupContent : ".checkbox-group-content",
	/**
	 * 选中项id集合
	 */
	selectedItems : [],

	/**
	 * 初始化checkbox选中项
	 */
	init : function() {
		for ( var i = 0; i < this.selectedItems.length; i++) {
			jQuery("#" + this.selectedItems[i]).attr("checked", "checked");
		}
		this.setAllGroupBox();
		this.setSelectAllBox();
		//选中个数
		this.setSelectedNum();
	},
	
	/**
	 * 选择一个
	 */
	selectOne : function(obj) {
		if (jQuery(obj).attr("checked") == "checked") {
			this.addItem(jQuery(obj).val());
		} else {
			this.removeItem(jQuery(obj).val());
		}
		this.setAllGroupBox();
		this.setSelectAllBox();
	
		this.setSelectedNum();
	},
	
	/**
	 * 选择一组
	 */
	selectGroup : function(obj) {
		alert();
		console.log(obj);
		var items = jQuery(obj).parents(this.boxGroupContent).find(this.boxItemClass);
//		var items = jQuery(obj).parents(".gwc_table").find(this.boxItemClass);
		console.log(items);
		console.log(items+"1111111111111");
		if (jQuery(obj).attr("checked") == "checked") {
			items.attr("checked", "checked");
			//设置全选按钮
			this.setSelectAllBox();
			//选中项的值放入集合
			this.setItemsValue(items, true);
		} else {
			items.removeAttr("checked");
			jQuery(this.boxAllClass).removeAttr("checked");
			//取消项的值移出集合
			this.setItemsValue(items, false);
		}
		
		this.setSelectedNum();
	},
	
	/**
	 * 全选
	 */
	selectAll : function(obj) {
		console.log(obj);
		if (jQuery(obj).attr("checked") == "checked") {
			alert();
			jQuery(this.boxAllClass).attr("checked", "checked");
			if (jQuery(this.boxGourpClass)) {
				jQuery(this.boxGourpClass).attr("checked", "checked");
			}
			jQuery(this.boxItemClass).attr("checked", "checked");
//			console.log("11111+"+jQuery(obj).attr("checked"));
			//选中项的值放入集合
			this.setItemsValue(jQuery(this.boxItemClass), true);
		} else {
			alert(1);
			jQuery(this.boxAllClass).removeAttr("checked");
			if (jQuery(this.boxGourpClass)) {
				jQuery(this.boxGourpClass).removeAttr("checked");
			}
			jQuery(this.boxItemClass).removeAttr("checked");
			//取消项的值移出集合
			this.setItemsValue(jQuery(this.boxItemClass), false);
		}
		
		this.setSelectedNum();
	},
	
	/**
	 * 设置分组按钮是否被选中
	 */
	setAllGroupBox : function() {
		if (jQuery(this.boxGourpClass)) {
			jQuery(this.boxGourpClass).each(function() {
				var currGroup = jQuery(this).parents(checkBox.boxGroupContent);
				var flag = false;
				currGroup.find(checkBox.boxItemClass).each(function() {
					if (jQuery(this).attr("checked") != "checked") {
						flag = false;
						return false;
					} else {
						flag = true;
					}
				});
				if (flag) {
					jQuery(this).attr("checked", "checked");
				} else {
					jQuery(this).removeAttr("checked");
				}
			});
		}
	},
	
	/**
	 * 设置全选按钮是否被选中
	 */
	setSelectAllBox : function() {
		//所有分组是否全选
		var flag = false;
		jQuery(this.boxItemClass).each(function() {
			if (jQuery(this).attr("checked") != "checked") {
				flag = false;
				return false;
			} else {
				flag = true;
			}
		});
		if (flag) {
			jQuery(this.boxAllClass).attr("checked", "checked");
		} else {
			jQuery(this.boxAllClass).removeAttr("checked");
		}
	},
	
	/**
	 * 设置多个checkbox的id值
	 * @param {Array} items checkbox的jquery对象集合
	 * @param {boolean} checkedFlag 是否选中
	 */
	setItemsValue : function(items, checkedFlag) {
		if (checkedFlag) {
			items.each(function() {
				checkBox.addItem(jQuery(this).val());
			});
		} else {
			items.each(function() {
				checkBox.removeItem(jQuery(this).val());
			});
		}
	},
	
	/**
	 * 删除指定checkbox的多个id，格式：'id1,id2,id3'
	 * @param {Object} ids
	 */
	removeItems : function(ids) {
		if(ids) {
			var id = ids.split(",");
			for(var i=0,j=id.length;i<j;i++) {
				this.removeItem(id[i]);
			}
		}
	},
	
	/**
	 * 删除指定checkbox的id
	 * @param id checkbox的id
	 */
	removeItem : function(id) {
		for ( var i = 0, j = this.selectedItems.length; i < j; i++) {
			if (this.selectedItems[i] == id) {
				this.selectedItems.splice(i, 1);
				return false;
			}
		}
	},
	
	/**
	 * 添加多个选中项
	 * @param {Object} ids 数据格式：'id1,id2,id3,id4...'
	 */
	addItems : function(ids) {
		if(ids) {
			var id = ids.split(",");
			for(var i = 0; i < id.length; i++) {
				this.addItem(id[i]);
			}
		}
	},
	
	/**
	 * 添加指定checkbox的value
	 * @param id checkbox的id
	 */
	addItem : function(id) {
		//添加项已存在
		var idExist = false;
		for ( var i = 0, j = this.selectedItems.length; i < j; i++) {
			if (this.selectedItems[i] == id) {
				idExist = true;
				return false;
			}
		}
		if(!idExist) {
			this.selectedItems.push(id);
		}
	},
	
	/**
	 * 设置已选个数
	 */
	setSelectedNum : function() {
		if (this.getSelectedNumEl()) {
			this.getSelectedNumEl().val(this.selectedItems.length);
		}
	},
	
	/**
	 * 获取当前页面checkBox选项值
	 */
	getSelectedItems : function() {
		var retStr = "";
		jQuery(this.boxItemClass).each(function() {
			if (jQuery(this).attr("checked") == "checked") {
				if (retStr == "") {
					retStr += jQuery(this).val();
				} else {
					retStr += "," + jQuery(this).val();
				}
			}
		});
		return retStr;
	},
	
	/**
	 * 获取所有checkBox选项值
	 */
	getAllSelectedItems : function() {
		var retStr = "";
		for ( var i = 0; i < this.selectedItems.length; i++) {
			if (retStr == "") {
				retStr += this.selectedItems[i];
			} else {
				retStr += "," + this.selectedItems[i];
			}
		}
		return retStr;
	},
	
	/**
	 * 是否有被选中项
	 */
	hasSelected : function() {
		if(this.selectedItems.length > 0) {
			return true;
		}
		return false;
	},
	
	/**
	 * 已选中个数的input元素
	 */
	getSelectedNumEl : function() {
		if (document.getElementById("selectedNum")) {
			return $("#selectedNum");
		}
		return null;
	}
}