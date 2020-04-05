/**
 * 产量管理JS代码
 * author: zzq
 * createDate: 2014-5-17 19:24:45
 */
$(document).ready(function() {
	changeLang_js();

	//点击新增常量表
	$("#addNewConstant").click(function() {
		$('.layui-layer-content').find("input[id='add_name']").val("constant_"); 
		$('.layui-layer-content').find("input[id='add_description']").val(""); 
		$("#add_name_error").show(); $("#add_name_error").html("");
		$("#add_description_error").show(); $("#add_description_error").html("");
		newIndex=layer.open({
			title:
				$.i18n.prop('addnewcontents'),
		    type: 1,
		    //skin: 'layui-layer-rim', //加上边框
		    area: ['600px', '240px'], //宽高
		    content: $('#addNewConstantWin').html()
		});
		setButtonStatus("saveNewConstant", false); //设置按钮可用
	});
	//保存新增加的常量表
	$('#saveNewConstant').live("click", function() {
		setButtonStatus("saveNewConstant", true); //设置按钮不可用
		$('#add_name').blur();
		$('#add_description').blur(); 
		var is_error = false;
		var _error = ["add_name", "add_description"];
		$.each(_error, function(key, val) {
        	var error_flag = $('#' + val).attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("wrong") >= 0) {
                	is_error = true;
                	return false;
                }
        });
        if (!is_error) {
        var data =$('.layui-layer-content').find("form[id='addForm']").serialize();
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/addConstant.do',
		    dataType : 'json',
		    data : data,
		    success : function(data) {
		    	console.log("data:"+data);
		    	if (data) {
		    		if (data.status != "error") {
		    			var index=layer.alert($.i18n.prop('addsuccess'),{title:$.i18n.prop('title')}, function(){
		    				layer.close(index);
		    				layer.close(newIndex);
		    			});
		    			location.reload();
					} else {
						$.each(data.data, function(i, item) {   
		              		seterror(item.key, item.value);
		            	});
					}
		    	}
		 	},
		    error : function(data) {}
		});
		
        }
	});
	
	//点击新增常量数据
	$("a[id*='addListInfo']").click(function() {
		$('#add_code').val("");
		$('#add_value').val("");
		
		var objId = $(this).attr("id");
		var id = objId.replace("addListInfo_", "");
		
		$('#add_constant_id').val(id.split("@")[0]); 
		$('#add_constant_tableName').val(id.split("@")[1]); 
		//$('#add_constant_id').attr("class", "u_select");
		//$("#add_constant_id").prev().html("请选择常量表");
		//$("#add_constant_id_error").html("");
		$("#add_code_error").show(); $("#add_code_error").html("");
		$("#add_value_error").show(); $("#add_value_error").html("");
		//$('#add_constant_id').attr("class", "u_select"); 
		newIndex=layer.open({
			title:$.i18n.prop('addnewconstant'),
		    type: 1,
		    //skin: 'layui-layer-rim', //加上边框
		    area: ['600px', '240px'], //宽高
		    content: $('#addNewListWin').html()
		});
		//blockUIOpen('addNewListWin');
		setButtonStatus("saveNewList", false); //设置按钮可用
	});
	
	//保存新增加的常量数据
	$('#saveNewList').live("click", function() {
		setButtonStatus("saveNewList", true); //设置按钮不可用
		$('#add_code').blur(); 
		$('#add_value').blur(); 
		var is_error = false;
		var _error = ["add_code","add_value"];
		$.each(_error, function(key, val) {
        	var error_flag =$('.layui-layer-content').find("input[id='"+val+"']").attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("wrong") >= 0) {
                	is_error = true;
                	return false;
                }
        });
	if (!is_error) {
		  jQuery.ajax({
			type : 'POST',
		    url : ctx + '/addConstantChild.do',
		    dataType : 'json',
		    data : {
		    	
		    	constant_id:$('.layui-layer-content').find("input[id='add_constant_id']").val(),
		    	tableName:$('.layui-layer-content').find("input[id='add_constant_tableName']").val(),
		    	code:$('.layui-layer-content').find("input[id='add_code']").val(),
		    	value:$('.layui-layer-content').find("input[id='add_value']").val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.success == "true") {
		    			var index=layer.alert($.i18n.prop('addsuccess'),{title:$.i18n.prop('title')}, function(){
		    				layer.close(index);
		    				layer.close(newIndex);
		    			});
		    			location.reload();
					} else {
						$.each(data.errorData, function(i, item) {   
		              		seterror(item.key, item.value, false);
		            	});
		            	setButtonStatus("saveNewList", false); //设置按钮可用
					}
		    	}
		 	},
		    error : function(data) {}
		});
	  }
	});
	
	//查看常量值信息
    $("a[id*='showListInfo']").on('click', function() {
    	var objId = $(this).attr("id");
		var id = objId.replace("showListInfo_", "");
		var constant_id = id.split("@")[0];
		var tableName = id.split("@")[1];
		var description = id.split("@")[2];
    	
    	jQuery.ajax({
			type : 'POST',
		    url : ctx + '/showConstValue.do',
		    dataType : 'json',
		    data : {
		    	tableName : tableName
		    },
		    success : function(data) {
		    	var size = data.rows.length;
		    	var htmlModel = "<table class='tableWin' border='1'>"
	  	 			+ "<tr><td colspan='3' class='top'>"+$.i18n.prop('contanstable')+"【"+tableName+"("+description+")】"+$.i18n.prop('valuetable')+""+"</td></tr>"
					+ "<tr>"
					+ "<td class='center' width='10%'>"+$.i18n.prop('code')+"</td><td class='center' width='20%'>"+$.i18n.prop('contantsvalue')+"</td><td class='center' width='18%'>"+$.i18n.prop('option')+"</td>"
					+ "</tr>";
		    	for(var i = 0; i < size; i ++){
		    		if(data.rows[i].code != undefined && data.rows[i].code != ""){ 
			    		htmlModel += "<tr>";
			    		htmlModel += "<td class='center' width='10%'>"+data.rows[i].code+"</td>";
			    		htmlModel += "<td class='center' width='20%'>"+data.rows[i].value+"</td>";
			    		htmlModel += "<td class='center' width='18%'><a class=\"btn blue\" id=\"removeConstValue_" + data.rows[i].id +"@"+ data.rows[i].code + "@" + tableName + "\"><i class=\"icon-trash\"></i>"+$.i18n.prop('delete')+"</a></td>";
			    		htmlModel += "</tr>";
			    	}
		    	}
		    	htmlModel += "</table>";
			    $('#constantValue').html(htmlModel);
			    
			    newIndex=layer.open({
					title:$.i18n.prop('viewdata'),
				    type: 1,
				    //skin: 'layui-layer-rim', //加上边框
				    area: ['600px', '240px'], //宽高
				    content: $('#constInfoWin').html()
				});
			    
		    },
		    error : function(data) {
		    	
		    }
		});
    	
    });
    
   //修改常量信息 将常量信息铺回到页面
    $("a[id*='updateListInfo']").on('click', function() {
    	var selectedIds = $("#listRoles").jqGrid("getGridParam", "selarrrow");
    	if(selectedIds){
    		if(selectedIds.length>1){
    			$('#oper_result_label').html($.i18n.prop('chooseonedataedit'));
    			blockUIOpen('operResultWin');
    		}else{
    			var relrow = $("#listRoles").jqGrid('getGridParam','selrow');
    	    	var rowDatas = $("#listRoles").jqGrid('getRowData', relrow);
    	    	var id = rowDatas["id"];
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/showList.do',
				    dataType : 'json',
				    data : {
				    	id : id
				    },
				    success : function(data) {
				    	if (data && data.status == "success") {
				    		$('#edit_id').val(data.data.id);
				    		$('#edit_value').val(data.data.value);
				    		$('#edit_code').val(data.data.code);
				    		$('#edit_constant_id').val(data.data.constant_id);
				    		$("#edit_constant_id").prev().html($("#edit_constant_id").find("option:selected").text());
				    		
				    		$('#edit_code_old').val(data.data.code);
				    		$('#edit_value').blur();
				    		$('#edit_code').blur();
				    		$('#edit_constant_id').blur();
				    		blockUIOpen('editListWin');
				    		setButtonStatus("saveEditRole", false); //设置按钮可用
						} else {
							$('#oper_result_label').html(data.data);
					    	blockUIOpen('operResultWin');
						}
				    },
				    error : function(data) {}
				});
    		}
    	}else{
    		$('#oper_result_label').html($.i18n.prop('chooseonedataedit'));
			blockUIOpen('operResultWin');
    	}
	});
    
//  //点击删除常量的按钮
//	$("a[id*='removeListInfo']").on('click', function() {
//		var selectedIds = $("#listRoles").jqGrid("getGridParam", "selarrrow");
//    	if(selectedIds){
//    		$('#remove_alert_info').html("是否要删除数据？");
//    		//$('#remove_role_id').val(selectedIds);
//    		blockUIOpen('removeListInfoWin');
//    		setButtonStatus("yesRemove", false); //设置按钮可用
//    	}
//	});
//	
//	//删除按钮
//	$('#yesRemove').live("click", function() {
//		var selectedIds = $("#listRoles").jqGrid("getGridParam", "selarrrow");
//		if(selectedIds){
//			if(selectedIds.length>0){
//				var ids = "";
//				for(var i=0;i<selectedIds.length;i++){
//					var v = $("#listRoles").jqGrid('getRowData', selectedIds[i]);
//					ids+=v["id"]+";";
//				    console.log("ids:"+ids);
//				}
//				setButtonStatus("yesRemove", true); //设置按钮不可用
//				$.blockUIClose;
//				jQuery.ajax({
//					type : 'POST',
//				    url : ctx + '/removeList.do',
//				    dataType : 'json',
//				    data : {
//				    	ids : ids
//				    },
//				    success : function(data) {
//				    	if (data) {
//				    		if (data.status != "error") {
//				    			if (data.status == "failure" && data.fresh == "false") {
//				    				$('#oper_result_label').html(data.data);
//					    			blockUIOpen('operResultWin');
//				    			} else {
//				    				$('#oper_result_label').html(data.data + "页面即将刷新！");
//					    			blockUIOpen('operResultWin');
//					    			query();
//				    			}
//							} else {
//								$.each(data.data, function(i, item) {   
//				              		seterror(item.key, item.value, false);
//				            	});
//				            	setButtonStatus("yesRemove", false); //设置按钮可用
//							}
//				    	}
//				 	},
//				    error : function(data) {}
//				});
//			}
//		}
//	});
		
	//删除常量表
	 $("a[id*='removeListInfo']").click( function() {
		 var objId = $(this).attr("id");
		 var constId = objId.replace("removeListInfo_", "");
		 var constant_id = constId.split("@")[0];
		 var tableName = constId.split("@")[1];
		 var description = constId.split("@")[2];
		 
		 var delIndex=layer.confirm($.i18n.prop('confirmdeletes')+tableName+'？', {
			    btn: [$.i18n.prop('yes'),$.i18n.prop('cancle')] //按钮
			}, function(){
				layer.msg($.i18n.prop('deleting'),{time:500});
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/removeList.do',
				    dataType : 'json',
				    data : {
				    	id:constant_id
				    },
				    success : function(data) {
				    		layer.alert(data.data, {
				    		   
				    		    skin: 'layer-ext-moon' 
				    		});
				    		$("#searchAll").click();
				    },
				    error:function(){
				    		layer.alert($.i18n.prop('deletefaile'), {
				    		   
				    		    skin: 'layer-ext-moon' 
				    		});
				    	} 
				});
			}, function(){
			    layer.close(delIndex);
			});
	 });

	//删除常量信息
	 $("a[id*='removeConstValue']").live('click', function() {
		 var objId = $(this).attr("id");
			var id = objId.replace("removeConstValue_", "");
			var constValue_id = id.split("@")[0];
			var constValue_code = id.split("@")[1];
			var tableName = id.split("@")[2];
		 
		 var delIndex=layer.confirm($.i18n.prop('confirmdeletes')+constValue_code+'？', {
			    btn: [$.i18n.prop('yes'),$.i18n.prop('cancle')] //按钮
			}, function(){
				layer.msg($.i18n.prop('delting'),{time:500});
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/removeConstValue.do',
				    dataType : 'json',
				    data : {
				    	id : constValue_id,
				    	tableName : tableName,
				    },
				    success : function(data) {
				    		layer.alert(data.data, {
				    		 
				    		    skin: 'layer-ext-moon' 
				    		});
				    		$("#searchAll").click();
				    },
				    error:function(){
				    		layer.alert($.i18n.prop('deletefaile'), {
				    		   
				    		    skin: 'layer-ext-moon' 
				    		});
				    	} 
				});
			}, function(){
			    layer.close(delIndex);
			});
	 });
	
	//保存所修改的常量信息
	$('#saveEditRole').live("click", function() {
		setButtonStatus("saveEditRole", true); //设置按钮不可用
		$('#edit_code').blur();
		$('#edit_value').blur();
		$('#edit_constant_id').blur();
		var is_error = false;
		var _error = ["edit_constant_id", "edit_code","edit_value"];
		$.each(_error, function(key, val) {
        	var error_flag = $('#' + val).attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("wrong") >= 0) {
                	is_error = true;
                	return false
                }
        });
        if (is_error) {
        	setButtonStatus("saveEditRole", false); //设置按钮可用
        	return false;
        }
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/changeList.do',
		    dataType : 'json',
		    data : {
		    	id : $('#edit_id').val(),
		    	code : $('#edit_code').val(),
		    	value : $('#edit_value').val(),
		    	constant_id : $('#edit_constant_id').val(),
		    	code_old : $('#edit_code_old').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			if (data.status == "failure" && data.fresh == "false") {
		    				$('#oper_result_label').html(data.data);
			    			blockUIOpen('operResultWin');
		    			} else {
		    				$('#oper_result_label').html(data.data + $.i18n.prop('pagewillnew'));
			    			blockUIOpen('operResultWin');
			    			//reloadCurrentPage($("#pageNumInput").val());
			    			query();
		    			}
					} else {
						$.each(data.data, function(i, item) {   
		              		seterror(item.key, item.value, false);
		            	});
		            	setButtonStatus("saveEditRole", false); //设置按钮可用
					}
		    	}
		 	},
		    error : function(data) {}
		});
	});
	
	function query(){
		var val = $('#constantInfoSearch').val();
//		console.log("val:"+val);
		if(val=='-1'){
			val = "";
		}
	    jQuery("#listRoles").jqGrid('setGridParam',{  
	        datatype:'json',  
	        url:ctx+'/getConstantList.do',
	        postData:{'constant_id':val}, //发送数据  
	        page:1,
	        mtype:"POST"       
	    }).trigger("reloadGrid"); //重新载入 
	}
	
	
	//取消
	$('#cancleConstantAdd').live('click',function(){
		layer.close(newIndex);
	});
	
	$('#cancleListAdd').live('click',function(){
		layer.close(newIndex);
	});
	
	
	//表名称
	$('#add_name').change(function() {
		checkName(this);
    });
    $('#add_name').blur(function() {
    	checkName(this);
    });
    //表描述
    $('#add_description').change(function() {
    	checkDescription(this);
    });
    $('#add_description').blur(function() {
    	checkDescription(this);
    });
    //编码
	$('#add_code').change(function() {
		checkCode(this);
    });
    $('#add_code').blur(function() {
    	checkCode(this);
    });
    $('#edit_code').change(function() {
		checkCode(this);
    });
    $('#edit_code').blur(function() {
    	checkCode(this);
    });
    //常量值
    $('#add_value').change(function() {
    	checkValue(this);
    });
    $('#add_value').blur(function() {
    	checkValue(this);
    });
    $('#edit_value').change(function() {
    	checkValue(this);
    });
    $('#edit_value').blur(function() {
    	checkValue(this);
    });
    //常量表
    $('#add_constant_id').change(function() {
    	//checkConstant_id(this);
    });
    $('#add_constant_id').blur(function() {
    	//checkConstant_id(this);
    });
    $('#edit_constant_id').change(function() {
    	//checkConstant_id(this);
    });
    $('#edit_constant_id').blur(function() {
    	//checkConstant_id(this);
    });
});

//==========================================
//Purpose: 点击搜索模块的"查看全部"按钮后，消除所有的搜索条件置为空，常量名称与常量类型
//==========================================
function clearSearchInput() {
	$("#roleNameSearch").val("");
}

//==========================================
//Purpose: 过滤检索输入框中的特殊字符
//==========================================
function replaceSpecialInput() {
	$("#roleNameSearch").val(replaceSpecialStr(Trim($("#roleNameSearch").val())));
}
//==========================================
//Purpose: 验证表名称
//==========================================
function checkName(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(objId, $.i18n.prop('entertablename'), false);
        return false;
    } else if ($(obj).val().length > 40) {
        seterror(objId, $.i18n.prop('tablecode'), false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}

//==========================================
//Purpose: 验证描述
//==========================================
function checkDescription(obj) {
	var objId = $(obj).attr("id");
	checkEmpty(obj);
	if ($(obj).val() != null && $(obj).val() != "" && $(obj).val().length > 80) {
        seterror(objId, $.i18n.prop('notelengths'), false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}

//==========================================
//Purpose: 验证编码
//==========================================
function checkCode(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
      seterror(objId, $.i18n.prop('entercodes'), false);
      return false;
  } else if ($(obj).val().length > 40) {
      seterror(objId,  $.i18n.prop('codelength'), false);
      return false;
  } else {
  	seterror(objId, "", true);
  }
}

//==========================================
//Purpose: 验证常量值
//==========================================
function checkValue(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
	    seterror(objId, $.i18n.prop('entercontants'), false);
	    return false;
	} else if ($(obj).val().length > 80) {
	    seterror(objId, $.i18n.prop('codelengthnomorethaneighty'), false);
	    return false;
	} else {
		seterror(objId, "", true);
	}
}

//==========================================
//Purpose: 验证常量表
//==========================================
function checkConstant_id(obj) {
	var objId = $(obj).attr("id");
	if ($(obj).val() == -1) {
        seterror(objId, $.i18n.prop('choosecontantstable'), false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}