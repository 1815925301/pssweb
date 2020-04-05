/**
 * 角色管理JS代码
 * author: zzq
 * createDate: 2013-10-19 19:24:45
 */
//layer打开的顺序
var newIndex,showIndex,editIndex,editAuthIndex;

$(document).ready(function() {
	changeLang_js();
	//点击新增角色
	$('#addNewRole').click(function() {
		$('.layui-layer-content').find("input[id='roleName']").val(""); 
		$('.layui-layer-content').find("input[id='note']").val(""); 
		$('.layui-layer-content').find("input[id='roleName_error']") .html("");
		$('.layui-layer-content').find("input[id='note_error']").html("");
		
		newIndex=layer.open({
			title:$.i18n.prop('addrole'),
		    type: 1,
		    //skin: 'layui-layer-rim', //加上边框
		    area: ['600px', '240px'], //宽高
		    content: $('#addNewRoleWin').html()
		});
	});
	
	//保存新增加的角色信息
	$('#save').live("click", function() {
		$('.layui-layer-content').find("input[id='roleName']").blur();
		var is_error = false;
		var _error = ["roleName"];
		$.each(_error, function(key, val) {
			var objId = $('#' + val).attr("id");
			var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
        	var error_flag = $(Objects).parent().parent().attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("error") >= 0) {
                	is_error = true;
                	return false;
                }
        });
        if (!is_error) {
        	var data =$('.layui-layer-content').find("form[id='addForm']").serialize();
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/saveNewRole.do',
			    dataType : 'json',
			    data : data,
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			var index=layer.alert($.i18n.prop('addsuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
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
	
		//取消
		$('#cancleAdd').live('click',function(){
			layer.close(newIndex);
		});
		$('#showcancleAdd').live('click',function(){
			layer.close(showIndex);
		});
		$('#changecancleAdd').live('click',function(){
			layer.close(editIndex);
		});
		$('#cancleAuthAdd').live('click',function(){
			layer.close(editAuthIndex);
		});
		
		//点击编辑
	    $("a[id*='updateRole']").click( function() {
	    	var objId = $(this).attr("id");
			var id=objId.replace("updateRole_", "");
	    	layer.msg($.i18n.prop('comeon'),{time:500});
			jQuery.ajax({
				type : 'POST',
			    url : ctx + '/showRole.do',
			    dataType : 'json',
			    data : {
			    	roleId:id
			    },
			    success : function(data) {
			    	$('#change_roleName').attr('value',data.data.roleName);
			    	$('#change_roleId').attr('value',data.data.id);
			    	$('#change_note').attr('value',data.data.note);
			    	editIndex=layer.open({
						title:$.i18n.prop('modity'),
					    type: 1,
					    //skin: 'layui-layer-rim', //加上边框
					    area: ['600px', '280px'], //宽高
					    content: $('#changeRoleWin').html()
					});
			    },
			    error:function(){
			    	layer.msg($.i18n.prop('comeon'),{time:500});
			    	setTimeout(function(){
			    		layer.alert($.i18n.prop('loadingwrong'), {
			    		    icon: 1,
			    		    skin: 'layer-ext-moon' 
			    		});
			    	} ,500);
			    }
			});
			
		});
	    
	    //编辑保存
	    $('#saveChange').live('click',function(){
	    	$('.layui-layer-content').find("input[id='change_roleName']").blur();
			var is_error = false;
			var _error = ["change_roleName"];
			$.each(_error, function(key, val) {
				var objId = $('#' + val).attr("id");
				var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
	        	var error_flag = $(Objects).parent().parent().attr('class');
	            if (error_flag != undefined)
	            	if (error_flag.indexOf("error") >= 0) {
	                	is_error = true;
	                	return false;
	                }
	        });
	        if (!is_error) {
	        	jQuery.ajax({
					type : 'POST',
				    url : ctx + '/changeRole.do',
				    dataType : 'json',
				    data : {
				    	id:$('.layui-layer-content').find("input[id='change_roleId']").val(),
				    	roleName:$('.layui-layer-content').find("input[id='change_roleName']").val(),
				    	note:$('.layui-layer-content').find("input[id='change_note']").val()
				    },
				    success : function(data) {
				    	if (data) {
				    		if (data.status != "error") {
				    			var index=layer.alert($.i18n.prop('moditysuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
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
	    
	  //点击查看
	    $("a[id*='showRole']").click( function() {
	    	layer.msg($.i18n.prop('comeon'),{time:500});
	    	var objId = $(this).attr("id");
			var id=objId.replace("showRole_", "");
			jQuery.ajax({
				type : 'POST',
			    url : ctx + '/showRole.do',
			    dataType : 'json',
			    data : {
			    	roleId:id
			    },
			    success : function(data) {
			    	console.log(data);
			    	$('#show_roleName').html(data.data.roleName);
			    	$('#show_createTime').html(data.data.createTime);
			    	$('#show_createUserName').html(data.data.createUserName);
			    	$('#show_updateTime').html(data.data.updateTime);
			    	$('#show_updateUserName').html(data.data.updateUserName);
			    	$('#show_note').html(data.data.note);
			    	showIndex=layer.open({
						title:$.i18n.prop('viewroles'),
					    type: 1,
					    //skin: 'layui-layer-rim', //加上边框
					    area: ['480px', '330px'], //宽高
					    content: $('#showRoleWin').html()
					});
			    },
			    error:function(){
			    	layer.msg($.i18n.prop('comeon'),{time:500});
			    	setTimeout(function(){
			    		layer.alert($.i18n.prop('loadingwrong'), {
			    		    icon: 1,
			    		    skin: 'layer-ext-moon' 
			    		});
			    	} ,500);
			    }
			});
		});
	    
		//点击分配权限
	    $("a[id*='assignAuth']").click( function() {
	    	var objId = $(this).attr("id");
			var id=objId.replace("assignAuth_", "");
	    	layer.msg($.i18n.prop('comeon'),{time:500});
			jQuery.ajax({
				type : 'POST',
			    url : ctx + '/showRoleAuth.do',
			    dataType : 'json',
			    data : {
			    	roleId:id
			    },
			    success : function(data) {
			    	if (data && data.status == "success") {
			    		$('#change_auth_role_id').attr('value',id);
			    		//删除表格第1行之外的所有行
			    		$("#changeRoleAuthTable tr:not(:first)").remove();
			    		var newRow = "";
			    		$.each(data.data, function(i, item) {   
			              	newRow = '<tr><td colspan="5" class="rightTd bold">' + item.resourceName + '</td></tr>';
			              	$("#changeRoleAuthTable tr:last").after(newRow);
			              	var subLength = item.resourceList.length;
			              	newRow = '<tr>';
			              	var checked = "";
			              	var tdNum = 0;
			              	$.each(item.resourceList, function(j, sub) {
			              		if (tdNum % 5 == 0 && tdNum != 0) {
			              			newRow += '<tr>';
			              		}
			              		tdNum = tdNum + 1;
			              		checked = "";
			              		if (sub.haveAuth == true) {
			              			checked = "checked";
			              		}
			              		newRow += '<td>';
			              		newRow += '<input type="checkbox" name="roleAuth" value="' + sub.id + '" ' + checked + ' />';
			              		newRow += sub.resourceName;
			              		newRow += '</td>';
			              		if (tdNum % 5 == 0 && tdNum != subLength) {
			              			newRow += '</tr>';
			              		}
			              	});
			              	newRow += '</tr>';
			              	$("#changeRoleAuthTable tr:last").after(newRow);
			            });
			    		editAuthIndex=layer.open({
							title:$.i18n.prop('modityrolesquanxian'),
						    type: 1,
						    //skin: 'layui-layer-rim', //加上边框
						    area: ['600px', '600px'], //宽高
						    content: $('#changeRoleAuthWin').html()
						});
					} else {
						layer.msg($.i18n.prop('comeon'),{time:500});
				    	setTimeout(function(){
				    		layer.alert($.i18n.prop('loadingwrong'), {
				    		    icon: 1,
				    		    skin: 'layer-ext-moon' 
				    		});
				    	} ,500);
					}
			    
			    },
			    error:function(){
			    	layer.msg($.i18n.prop('comeon'),{time:500});
			    	setTimeout(function(){
			    		layer.alert($.i18n.prop('loadingwrong'), {
			    		    icon: 1,
			    		    skin: 'layer-ext-moon' 
			    		});
			    	} ,500);
			    }
			});
			
		});
	    
	  //保存所修改的角色权限信息
	    $('#saveChangeRoleAuth').live('click',function() {
	    
			var resourceIds = getCheckboxValue("roleAuth");
			var confirm = true;
			jQuery.ajax({
				type : 'POST',
			    url : ctx + '/assignRoleAuth.do',
			    dataType : 'json',
			    data : {
			    	roleId : $('#change_auth_role_id').val(),
			    	resourceIds : resourceIds
			    },
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			if (data.status == "failure" && data.fresh == "false") {
				    			var index=layer.alert(data.data,{icon: 3, title:$.i18n.prop('title')}, function(){
				    				layer.close(index);
				    				layer.close(editIndex);
				    			});
				    			location.reload();
			    			} else {
			    				var index=layer.alert(data.data,{icon: 3, title:$.i18n.prop('title')}, function(){
				    				layer.close(index);
				    				layer.close(editIndex);
				    			});
				    			location.reload();
			    			}
						} else {
							$.each(data.data, function(i, item) {   
			              		seterror(item.key, item.value, false);
			            	});
			    
						}
			    	}
			 	},
			    error : function(data) {}
			});
			
		});
	    
		//-------------------------------------------------
		//角色名校验
		$('#roleName').live('blur',function() {
	        checkRoleName(this);
	    });
		$("#roleName").live('change',function() {
	        checkRoleName(this);
	    });
	    
});
//-------------------------------------------------
//Purpose: 点击搜索模块的"查看全部"按钮后，消除所有的搜索条件置为空，用户名称与用户类型
//==========================================
function clearSearchInput() {
	$("#roleNameSearch").val("");
}

//==========================================
//Purpose: 过滤检索输入框中的特殊字符
//==========================================
function replaceSpecialInput() {
	$("#roleNameSearch").val(replaceSpecialStr($("#userNameSearch").val()));
}


//==========================================
//Purpose: 验证角色名输入控件
//==========================================
function checkRoleName(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(obj, $.i18n.prop('enterrolename'));
        return false;
    } else {
    	seterror(obj, "");
    }
}


 function checkIsNull(obj,error){
		checkEmpty(obj);
		if ($(obj).val() != null && $(obj).val() != "" ){
			seterror(obj, "");
		}else {
	      	seterror(obj, error);
	      }
 }
 
 function checkEmpty(obj){
	 var objId = $(obj).attr("id");
	var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
	   if ($(Objects).val() != null && $(obj).val() != "" ){
		   return true;
	   }else{
		   return false;
	   }
 }
 
//设置错误信息样式
	function seterror(obj,error){
		var objId = $(obj).attr("id");
		var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
		var errorObj=$('.layui-layer-content').find("span[id='"+objId+"_error']");
		if(error!=""){
			$(Objects).parent().parent().removeClass("control-group").addClass("control-group error");
			$(errorObj).html(error);
		}else{
			$(Objects).parent().parent().removeClass("control-group error").addClass("control-group");
			$(errorObj).html('');
		}
	}
	
	//删除
	 $("a[id*='removeRole']").click( function() {
		 var uname=$(this).attr("uname");
		 var objId = $(this).attr("id");
		var id=objId.replace("removeRole_", "");
		 var delIndex=layer.confirm($.i18n.prop('confirmdelete')+uname+'？', {
			    btn: [$.i18n.prop('yes'),$.i18n.prop('cancle')] //按钮
			}, function(){
				layer.msg($.i18n.prop('deleting'),{time:500});
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/removeRole.do',
				    dataType : 'json',
				    data : {
				    	roleId:id
				    },
				    success : function(data) {
				    		layer.alert(data.data, {
				    		    icon: 1,
				    		    skin: 'layer-ext-moon' 
				    		});
				    		$("#searchAll").click();
				    },
				    error:function(){
				    		layer.alert($.i18n.prop('deletefaile'), {
				    		    icon: 1,
				    		    skin: 'layer-ext-moon' 
				    		});
				    	} 
				});
			}, function(){
			    layer.close(delIndex);
			});
	 });
