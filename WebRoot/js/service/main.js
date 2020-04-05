/**
 * zzq
 * 2015-11-07
 * 主页js，实现菜单点击，切换功能 
 */
var editUserIndex,editPwdIndex;
$(document).ready(function() {
	changeLang_js();
//判断IE浏览器的版本
function checkBrowserType(){
	version = 0;
	if(navigator.appName.indexOf("Internet Explorer") != -1){
		temp = navigator.appVersion.split("MSIE");
		version = parseFloat(temp[1]);
	}
	return version;
}

$("#changeUser").click( function() {
	layer.msg($.i18n.prop('comeon'),{time:500});
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/currentUserInfo.do',
	    dataType : 'json',
	    success : function(data) {
	    	$('#change_userName').attr('value',data.data.userName);
	    	$('#change_trueName').attr('value',data.data.trueName);
	    	$('#change_userEmail').attr('value',data.data.userEmail);
	    	editUserIndex=layer.open({
				title:$.i18n.prop('modifyuser'),
			    type: 1,
			    //skin: 'layui-layer-rim', //加上边框
			    area: ['600px', '250px'], //宽高
			    content: $('#changeUserWin').html()
			});
	    },
	    error:function(){
	    	layer.msg($.i18n.prop('comeon'),{time:500});
	    	setTimeout(function(){
	    		layer.alert($.i18n.prop('tryagain'), {
	    		    icon: 1,
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} ,500);
	    }
	});
	
});
$('#saveUser').live('click',function(){
	$('.layui-layer-content').find("input[id='change_userName']").blur();
	$('.layui-layer-content').find("input[id='change_trueName']").blur();
	$('.layui-layer-content').find("input[id='change_userEmail']").blur();
	var is_error = false;
	var _error = ["change_userName","change_trueName","change_userEmail"];
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
		    url : ctx + '/setCurrentUserInfo.do',
		    dataType : 'json',
		    data : {
		    	userName:$('.layui-layer-content').find("input[id='change_userName']").val(),
		    	trueName:$('.layui-layer-content').find("input[id='change_trueName']").val(),
		    	userEmail:$('.layui-layer-content').find("input[id='change_userEmail']").val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			var index=layer.alert($.i18n.prop('modifysuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
		    				layer.close(index);
		    				layer.close(editIndex);
		    			});
		    			location.reload();
					} else {
						$.each(data.data, function(i, item) {   
		              		seterror($('.layui-layer-content').find("input[id='"+item.key+"']"), item.value);
		            	});
					}
		    	}
		 	},
		    error : function(data) {}
    	});
    }
});

$("#changePwd").click( function() {
	$('.layui-layer-content').find("input[id='old_password']").val(""); 
	$('.layui-layer-content').find("input[id='new_password']").val(""); 
	$('.layui-layer-content').find("input[id='confirm_new_password']").val(""); 
	$('.layui-layer-content').find("input[id='old_password_error']") .html("");
	$('.layui-layer-content').find("input[id='new_password_error']").html("");
	$('.layui-layer-content').find("input[id='confirm_new_password_error']").html("");
	
	editPwdIndex=layer.open({
		title:$.i18n.prop('modifyuserpassword'),
	    type: 1,
	    //skin: 'layui-layer-rim', //加上边框
	    area: ['600px', '300px'], //宽高
	    content: $('#changePwdWin').html()
	});
	
});

$('#savePwd').live('click',function(){
	$('.layui-layer-content').find("input[id='old_password']").blur();
	$('.layui-layer-content').find("input[id='new_password']").blur();
	$('.layui-layer-content').find("input[id='confirm_new_password']").blur();
	var is_error = false;
	var _error = ["old_password","new_password","confirm_new_password"];
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
			 url : ctx + '/setCurrentUserPassInfo.do',
			    dataType : 'json',
			    data : {
			    	password : $('.layui-layer-content').find("input[id='old_password']").val(),
			    	newPassword : $('.layui-layer-content').find("input[id='new_password']").val(),
			    	confirmNewPassword :$('.layui-layer-content').find("input[id='confirm_new_password']").val()
			    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			var index=layer.alert($.i18n.prop('modifysuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
		    				layer.close(index);
		    				layer.close(editIndex);
		    			});
		    			location.reload();
					} else {
						$.each(data.data, function(i, item) {   
		              		seterror( $('.layui-layer-content').find("input[id='"+item.key+"']"), item.value);
		            	});
					}
		    	}
		 	},
		    error : function(data) {}
    	});
    }
});

$('#change_userName').live('blur',function() {
    checkUserName(this);
});
$("#change_userName").live('change',function() {
    checkUserName(this);
});

//用户真实姓名校验
$('#change_trueName').live('change',function() {
    checkTrueName(this);
});
$('#change_trueName').live('blur',function() {
	checkTrueName(this);
});

//用户邮件校验
$('#change_userEmail').live('change',function() {
    checkUserEmail(this);
});
$('#change_userEmail').live('blur',function() {
    checkUserEmail(this);
});

//取消
$('#cancleUser').live('click',function(){
	layer.close(editUserIndex);
});
//取消
$('#canclePwd').live('click',function(){
	layer.close(editPwdIndex);
});
//原密码验证
$('#old_password').live('change',function() {
	checkOldPassword(this);
});
$('#old_password').live('blur',function() {
	checkOldPassword(this);
});
//新密码验证
$('#new_password').live('change',function() {
	checkNewPassword(this);
});
$('#new_password').live('blur',function() {
	checkNewPassword(this);
});
//新密码确认验证
$('#confirm_new_password').live('change',function() {
	checkConfirmNewPassword(this);
});
$('#confirm_new_password').live('blur',function() {
	checkConfirmNewPassword(this);
});
//首次登陆系统加载菜单
/**$("a[id*='menuItemLabel']").on('click', function() {
//--------------------原菜单显示方法 开始--------------------------
	var objId = $(this).attr("menuid");
	var id = $(this).attr("id").replace("menuItemLabel","");
	var menuname =  $(this).attr("menuname");
	moduleCurrIndex = id;
	
	$("#crumbMin").html(menuname);
	$("#crumbMin").attr("menuname",menuname);
	$("#crumbMin").attr("menuid",objId);

	var i = true;
	$("li[id*='resource_table_']").each(function(){
		var id = $(this).attr("id").replace("resource_table_","");
		if(objId==id){
			$(this).attr("style","");
			if(i){
				$("#crumbMinActive").html($(this).attr("menuname"));
				var href =$(this).find("a").attr("href");
				$("#crumbMinActive").attr("href",href);
				
				//document.getElementById("main_iframe").src = href;
			}
			i = false;
		}else{
			$(this).attr("style","display:none");
		};
	});
	$("a[id*='menuItemLabel']").each(function(){
		$(this).parent().attr("class","");
	});
	$(this).parent().attr("class","active");
});	**/
//二级菜单点击事件
/**$("li[id*='resource_table_']").live('click', function() {
var name =  $(this).attr("name");
	/**var href =	$(this).attr("menusrc");
	var menuname = 	$(this).attr("menuname");
	var id = $(this).attr("id");
	var name =  $(this).attr("name");
	//tab 标签
	$(".J_menuTab").each(function(){
		$(this).attr("class","J_menuTab");
	});
	//ifream
	$(".J_iframe").each(function(){
		$(this).attr("style","display: none;");
	});*/
	//菜单颜色
/**	$("li[name*='"+name+"']").each(function(){
		$(this).attr("class","");
	});
	$(this).attr("class","start active");
	
	/**$(".page-tabs-content").append('<a href="javascript:;" class="active J_menuTab" data-id="'+href+'">'+menuname+'&nbsp;&nbsp;<i class="fa fa-times-circle"></i></a>');
	$("#content-main").append('<iframe class="J_iframe" name="iframe'+id+'" width="100%" height = "500px" ' 
	+'src="'+ctx+href+'" frameborder="0" data-id="'+href+'" seamless="" ></iframe>');*/
	//getMenuIfreamHeigth(true);
//});



//1 一级菜单导航
/**$("a[id='crumbMin']").on('click', function() {
	var objId = $(this).attr("menuid");
	var id = $(this).attr("id").replace("menuItemLabel","");
	var menuname =  $(this).attr("menuname");
	moduleCurrIndex = id;
	
	$("#crumbMin").html(menuname);
	$("#crumbMin").attr("menuname",menuname);
	$("#crumbMin").attr("menuid",objId);

	var i = true;
	$("li[id*='resource_table_']").each(function(){
		var id = $(this).attr("id").replace("resource_table_","");
		if(objId==id){
			$(this).attr("style","");
			if(i){
				$("#crumbMinActive").html($(this).attr("menuname"));
				
				var href =$(this).find("a").attr("href");
				$("#crumbMinActive").attr("href",href);
				document.getElementById("main_iframe").src = href;
			}
			i = false;
		}else{
			$(this).attr("style","display:none");
		};
	});
	//getMenuIfreamHeigth(true);
});


$("iframe[name*='iframe']").load(function(){
	$("iframe[name*='iframe']").each(function(){
		var id = $(this).attr("id");
		//getMenuIfreamHeigth(id,true);
	})
}); 

function getMenuIfreamHeigth(id,flag) {
    var iframe = document.getElementById(id);
	try{
		var bHeight =  $(window).height();
		var dHeight = $(document).height();
		var height = Math.max(bHeight, dHeight);
		var bodyHeight  = bHeight;
		
		if(flag){
			iframe.height = bodyHeight-93;
		}else{
			iframe.height = bodyHeight-53;
		}
	}catch (ex){
	
	}
}*/
});

//==========================================
//Purpose: 验证用户名输入控件
//==========================================
function checkUserName(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
      seterror(obj, $.i18n.prop('enterusername'));
      return false;
  } else if (!$(obj).val().match(/^[a-zA-Z]{1}([a-zA-Z0-9_@.]){5,29}$/)) {
  	//只处验证不能为空并且只能为英文或者数字或者下划线组成的6-30个字符
      seterror(obj,$.i18n.prop('checkusername'));
      return false;
  } else {
  	seterror(obj, "");
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

//==========================================
//Purpose: 验证用户原密码输入控件
//==========================================
function checkOldPassword(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
      seterror(obj, $.i18n.prop('enteroldcode'));
      return false;
  } else if (!$(obj).val().match(/^([a-zA-Z0-9]|[._]|[`~!@#$%^&*\(\)\-+\{\}\[\]\\|;:'",.\/<>?]){6,15}$/)) {
      seterror(obj, $.i18n.prop('codegeshi'));
      return false;
  } else {
  	seterror(obj, "", true);
  }
}
//==========================================
//Purpose: 验证用户新密码输入控件
//==========================================
function checkNewPassword(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
      seterror(obj, $.i18n.prop('enternewcode'));
      return false;
  } else if (!$(obj).val().match(/^([a-zA-Z0-9]|[._]|[`~!@#$%^&*\(\)\-+\{\}\[\]\\|;:'",.\/<>?]){6,15}$/)) {
      seterror(obj,  $.i18n.prop('codegeshi'));
      return false;
  } else {
  	seterror(obj, "");
  	/*checkUserPassStrong(objId);*/
  }
  if ($(obj).val() == $('.layui-layer-content').find("input[id='confirm_new_password']").val()) {
  	if ($("#confirm_new_password_error").html() == $.i18n.prop('codedifferent')) {
  		seterror("confirm_new_password", "");
  	}
  }
}
//==========================================
//Purpose: 验证用户确认新密码输入控件
//==========================================
function checkConfirmNewPassword(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
      seterror(obj, $.i18n.prop('enterconfirmcode'));
      return false;
  } else if($(obj).val() != $('.layui-layer-content').find("input[id='new_password']").val()) {
  	  seterror(obj, $.i18n.prop('codedifferent'));
      return false;
  } else if (!$(obj).val().match(/^([a-zA-Z0-9]|[._]|[`~!@#$%^&*\(\)\-+\{\}\[\]\\|;:'",.\/<>?]){6,15}$/)) {
      seterror(obj, $.i18n.prop('codegeshi'));
      return false;
  } else {
  	seterror(obj, "");
  }
}