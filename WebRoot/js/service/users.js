$(document).ready(function() {
	changeLang_js();
	         
});
/**
 * 
 * 用户管理
 */
//layer打开的顺序
var newIndex,showIndex,editIndex,lockIndex;
$(document).ready(function() {
	
	$('#checkUserisLock').click(function() {
		var id="";
		$("input:checkbox[name='subcheck']:checked").each(function() { // 遍历name=test的多选框
	           id=id+$(this).val()+",";
	           
		});
		if(id.length<=1){
			alert($.i18n.prop('chooseautouser'));
			return;
		}
		$("#lock_userName").val(id.substring(0, id.length-1)); 
		$("#lockuserName").html(id.substring(0, id.length-1)); 
		
		lockIndex=layer.open({
			title:$.i18n.prop('auto'),
		    type: 1,
		    //skin: 'layui-layer-rim', //加上边框
		    area: ['600px', '500px'], //宽高
		    content: $('#userisLockWin').html()
		});
	});
	 
	$("input[id*='pageTurn']").click(function() {
		var inputId = $(this).attr("id");
		var value = inputId.replace("pageTurn","");
		$("#pageNumInput").val(value);
		formSubmit();
	});

	//搜索按钮触发的事件
	$("#searchMatch").click(function() {
		$("#pageNumInput").val(1);
		//replaceSpecialInput();
		formSubmit();
	});

	//点击搜索模块的"查看全部"按钮后，消除所有的搜索条件并将分页页码置为1
	$("#searchAll").click(function() {
		$("#pageNumInput").val(1);
		clearSearchInput();
		formSubmit();
	});
	//点击新增用户
	$('#addNewUser').click(function() {
		$('.layui-layer-content').find("input[id='userName']").val(""); 
		$('.layui-layer-content').find("input[id='trueName']").val(""); 
		$('.layui-layer-content').find("input[id='password']").val(""); 
		$('.layui-layer-content').find("input[id='surepassword']").val(""); 
		$('.layui-layer-content').find("input[id='userEmail']").val("");
		$('.layui-layer-content').find("input[id='orgId']").val(-1);
		$('.layui-layer-content').find("input[id='lockStr']").val(0);
		$('.layui-layer-content').find("input[id='roleId']").val(-1);
		$('.layui-layer-content').find("input[id='userName_error']") .html("");
		$('.layui-layer-content').find("input[id='trueName_error']").html("");
		$('.layui-layer-content').find("input[id='password_error']") .html("");
		$('.layui-layer-content').find("input[id='surepassword_error']").html("");
		$('.layui-layer-content').find("input[id='userEmail_error']").html("");
		
		newIndex=layer.open({
			title:$.i18n.prop('adduser'),
		    type: 1,
		    //skin: 'layui-layer-rim', //加上边框
		    area: ['600px', '500px'], //宽高
		    content: $('#addNewUserWin').html()
		});
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
		
		//-------------------------------------------------
	//用户名校验
	$('#userName').live('blur',function() {
        checkUserName(this);
    });
	$("#userName").live('change',function() {
        checkUserName(this);
    });
	    
    //用户登录密码校验
	$('#password').live('change',function() {
        checkUserPassword(this);
    });
    $('#password').live('blur',function() {
        checkUserPassword(this);
    });
	    
    //用户确认密码校验
    $('#surepassword').live('change',function() {
        checkConfirmUserPass(this);
    });
    $('#surepassword').live('blur',function() {
        checkConfirmUserPass(this);
    });
    
	    //用户真实姓名校验
	$('#trueName').live('change',function() {
        checkTrueName(this);
    });
    $('#trueName').live('blur',function() {
    	checkTrueName(this);
    });
	    
	    //用户邮件校验
//		$('#userEmail').live('change',function() {
//	        checkUserEmail(this);
//	    });
//	    $('#userEmail').live('blur',function() {
//	        checkUserEmail(this);
//	    });
	    
	    //用户机构校验
	$('#orgId').live('change',function() {
		checkOrgName();
    });
    $('#orgId').live('blur',function() {
    	checkOrgName();
    });
});

function lockCancle(){
	layer.close(lockIndex);
}

//点击编辑
function updateUser(id){
	layer.msg($.i18n.prop('comeon'),{time:500});
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/showUsers.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#change_id').attr('value',data.userInfo.id);
	    	$('#change_userName').attr('value',data.userInfo.userName);
	    	$('#change_trueName').attr('value',data.userInfo.trueName);
	    	$('#change_userEmail').attr('value',data.userInfo.userEmail);
	    	$('#change_orgId').find("option[text='"+data.userInfo.orgName+"']").attr("selected",true);
	    	$('#change_lockStr').find("option[text='"+data.userInfo.lockStr+"']").attr("selected",true);
	    	if(data.userInfo.roleName!=null){
	    		$("#change_roleId option[text='"+data.userInfo.roleName+"']").attr("selected", true);  
	    	}else{
	    		 $("#change_roleId").append("<option value='-1' selected>"+$.i18n.prop('chooseroles')+"</option>");  
	    	}
	    	editIndex=layer.open({
				title:$.i18n.prop('modifyuser'),
			    type: 1,
			    //skin: 'layui-layer-rim', //加上边框
			    area: ['600px', '500px'], //宽高
			    content: $('#changeUserWin').html()
			});
	    },
	    error:function(){
	    	setTimeout(function(){
	    		layer.alert($.i18n.prop('loadingwrong'), {
	    		    icon: 1,
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} ,500);
	    }
	});			
}
  //点击查看
function showUser(id){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/showUsers.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#show_userName').html(data.userInfo.userName);
	    	$('#show_trueName').html(data.userInfo.trueName);
	    	$('#show_userEmail').html(data.userInfo.userEmail);
	    	$('#show_orgName').html(data.userInfo.orgName);
	    	$('#show_lockStr').html(data.userInfo.lockStr);
	    	$('#show_roleName').html(data.userInfo.roleName);
	    	showIndex=layer.open({
				title:$.i18n.prop('viewroles'),
			    type: 1,
			    area: ['480px', '450px'], //宽高
			    content: $('#showUserWin').html()
			});
	    },
	    error:function(){
	    }
	});
};
//-------------------------------------------------
//Purpose: 点击搜索模块的"查看全部"按钮后，消除所有的搜索条件置为空，用户名称与用户类型
//==========================================
function clearSearchInput() {
	$("#userNameSearch").val("");
	$("#userEmailSearch").val("");
	$("#orgNameSearch").val("-1");
}

//==========================================
//Purpose: 过滤检索输入框中的特殊字符
//==========================================
function replaceSpecialInput() {
	$("#userNameSearch").val(replaceSpecialStr($("#userNameSearch").val()));
	$("#userEmailSearch").val(replaceSpecialStr($("#userEmailSearch").val()));
}

//==========================================
//Purpose: 验证密码的输入
//==========================================
function checkUserPassword(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
      seterror(obj,$.i18n.prop('entercodeme'));
      return false;
  } else if (!$(obj).val().match(/^([a-zA-Z0-9]|[._]|[`~!@#$%^&*\(\)\-+\{\}\[\]\\|;:'",.\/<>?]){6,15}$/)) {
      seterror(obj, $.i18n.prop('codegeshi'));
      return false;
  } else {
	  seterror(obj, "");
  }
}

//==========================================
//Purpose: 验证确认密码的输入
//==========================================
function checkConfirmUserPass(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
      seterror(obj, $.i18n.prop('enterconfirmcode'));
      return false;
  } else if($(obj).val() != $('.layui-layer-content').find("input[id='password']").val()) {
  	seterror(obj, $.i18n.prop('twocodecomfirm'));
      return false;
  } else {
    	seterror(obj, "");
  }
}

//==========================================
//Purpose: 验证用户名输入控件
//==========================================
function checkUserName(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(obj, $.i18n.prop('enterusernmae'));
        return false;
    } else if (!$(obj).val().match(/^[a-zA-Z]{1}([a-zA-Z0-9_@.]){5,29}$/)) {
    	//只处验证不能为空并且只能为英文或者数字或者下划线组成的6-30个字符
        seterror(obj, $.i18n.prop('checkusername'));
        return false;
    } else {
    	seterror(obj, "");
    }
}

//==========================================
//Purpose: 验证用户机构选择控件
//==========================================
function checkOrgName(){
	var obj=$('.layui-layer-content').find("select[id='orgId']");
	var errorObj=$('.layui-layer-content').find("span[id='orgId_error']");
	if ($(obj).val() == -1) {
		$(obj).parent().parent().removeClass("control-group").addClass("control-group error");
		$(errorObj).html($.i18n.prop('choosejigou'));
	}else{
		$(obj).parent().parent().removeClass("control-group error").addClass("control-group");
		$(errorObj).html('');
	}
}
//==========================================
//Purpose: 验证用户电子邮箱输入控件
//==========================================
function checkUserEmail(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(obj, $.i18n.prop('enteremail'));
        return false;
    } else if (!isEmail($(obj).val())) {
        seterror(obj, $.i18n.prop('emailwrong'));
        return false;
    } else {
    	seterror(obj, "");
    }
}

//==========================================
//Purpose: 验证用户真实姓名输入控件
//==========================================
function checkTrueName(obj){
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(obj, $.i18n.prop('enterrealname'));
        return false;
    }  else {
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
	function removeUser(id,uname){
		 var delIndex=layer.confirm($.i18n.prop('confirmdelete')+uname+'？', {
			    btn: [$.i18n.prop('yes'),$.i18n.prop('cancle')] //按钮
			}, function(){
				layer.msg($.i18n.prop('deleteling'),{time:500});
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/removeUser.do',
				    dataType : 'json',
				    data : {
				    	userId:id
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
	};
//保存
	function saveUser(){
		$('.layui-layer-content').find("input[id='userName']").blur();
		$('.layui-layer-content').find("input[id='trueName']").blur();
		$('.layui-layer-content').find("input[id='password']").blur();
		$('.layui-layer-content').find("input[id='surepassword']").blur();
		//$('#userEmail').blur();
		checkOrgName();
		var is_error = false;
		var _error = ["userName", "trueName", "password", "surepassword", "orgId"];
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
			    url : ctx + '/saveNewUser.do',
			    dataType : 'json',
			    data : data,
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			var index=layer.alert($.i18n.prop('addsuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
			    				layer.closeAll();
			    				$("#searchAll").click();
			    			});
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
	};
	function updatelockUser(isLock){
		var data =$('.layui-layer-content').find("form[id='userForm']").serialize();
		data=data+"&isLock="+isLock;
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/checkrUserIslock.do',
		    dataType : 'json',
		    data : data,
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			var index=layer.alert($.i18n.prop('savesuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
		    				layer.closeAll();
		    				$("#searchAll").click();
		    			});
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
	
	//保存修改
	function saveChange(){
		$('.layui-layer-content').find("input[id='userName']").blur();
		$('.layui-layer-content').find("input[id='trueName']").blur();
		$('.layui-layer-content').find("input[id='password']").blur();
		$('.layui-layer-content').find("input[id='surepassword']").blur();
		checkOrgName();
		var is_error = false;
		var _error = ["userName", "trueName", "password", "surepassword", "orgId"];
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
        	var data =$('.layui-layer-content').find("form[id='changeForm']").serialize();
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/changeUser.do',
			    dataType : 'json',
			    data : data,
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			var index=layer.alert($.i18n.prop('savesuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
			    				layer.closeAll();
			    				$("#searchAll").click();
			    			});
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
	};
	  $(function() {
          $("#allcheck").click(function() {
               $('input[name="subcheck"]').attr("checked",this.checked); 
           });
           var $subcheck = $("input[name='subcheck']");
           $subBox.click(function(){
               $("#allcheck").attr("checked",$subBox.length == $("input[name='subcheck']:checked").length ? true : false);
           });
       });