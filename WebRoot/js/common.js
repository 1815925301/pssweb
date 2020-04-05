/**
 * 系统页面头部功能JS代码 包含公用的JS代码
 * author: mrajian
 * createDate: 2013-10-17 19:24:45
 */
$(document).ready(function() {
	
//	$("#handleWating").ajaxStart(function() {
//		$.maskUI.open($(this));
//	});
//	$("#handleWating").ajaxStop(function() {
//		if($("#handleWating").length > 0 && !$("#handleWating").is(":hidden")) {
//			$.unmaskUI();
//		}
//	});
	
	/*//全局的ajax访问，处理ajax清求时sesion超时
	$.ajaxSetup({
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		complete : function(xhr, textStatus) {
			//session timeout
			if (xhr.status == 911) {
				blockUIOpen('sessionTimeoutConfrim');
			}
		}
	});
	
	
	//点击查看当前用户信息
	$('#userInfo').click(function() {
		jQuery.ajax({
			type : 'POST',
		   	contentType : 'application/json',
		    url : ctx + '/currentUserInfo.do',
		    dataType : 'json',
		    success : function(data) {
		    	if (data && data.status == "success") {
		    		$('#label_user_name').html(data.data.userName);
		    		$('#label_user_email').html(data.data.userEmail);
		    		$('#label_true_name').html(data.data.trueName);
		    		$('#label_org_name').html(data.data.orgName);
		    		$('#label_user_mobile').html(data.data.userMobile);
		    		$('#label_user_phone').html(data.data.userPhone);
		    		$('#label_create_time').html(data.data.createTime);
		    		$('#label_note').html(data.data.note);
		    		blockUIOpen('currentUserInfoWin');
				}
		 	},
		    error : function(data) {}
		});
	});
	
	//进入用户信息修改窗口
	$('#changeUserBaseInfo').live("click", function() {
		jQuery.ajax({
			type : 'POST',
		   	contentType : 'application/json',
		    url : ctx + '/currentUserInfo.do',
		    dataType : 'json',
		    success : function(data) {
		    	if (data && data.status == "success") {
		    		$('#change_user_name').val(data.data.userName);
		    		$('#change_user_email').val(data.data.userEmail);
		    		$('#change_true_name').val(data.data.trueName);
		    		$('#change_user_mobile').val(data.data.userMobile);
		    		$('#change_user_phone').val(data.data.userPhone);
		    		$('#change_note').val(data.data.note);
		    		$('#change_user_name').blur();
		    		$('#change_user_email').blur();
		    		$('#change_true_name').blur();
		    		$('#change_user_mobile').blur();
		    		blockUIOpen('changeUserBaseInfoWin');
				}
		 	},
		    error : function(data) {}
		});
	});
	
	//打开修改用户密码的窗口
	$('#changeUserPass').live("click", function() {
		//老密码校验
		$('#old_password').val(""); $('#old_password').attr("class", "width130"); 
	    $('#new_password').val(""); $('#new_password').attr("class", "width130");
	    $('#confirm_new_password').val(""); $('#confirm_new_password').attr("class", "width130");
	    $("#old_password_error").show(); $("#old_password_error").html("");
	    $("#new_password_error").show(); $("#new_password_error").html("");
	    $("#confirm_new_password_error").show(); $("#confirm_new_password_error").html("");
        blockUIOpen('changeUserPassWin');
	});
	
	//保存所修改的用户基本信息
	$('#saveChangeUserBaseInfo').live("click", function() {
		$('#change_user_name').blur();
		$('#change_user_email').blur();
		$('#change_true_name').blur();
		$('#change_user_mobile').blur();
		var is_error = false;
		var _error = ["change_user_name", "change_user_email", "change_true_name", "change_user_mobile", "change_user_phone", "change_note"];
		$.each(_error, function(key, val) {
        	var error_flag = $('#' + val).attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("wrong") >= 0) {
                	is_error = true;
                	return false
                }
        });
        if (is_error) return false;
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/setCurrentUserInfo.do',
		    dataType : 'json',
		    data : {
		    	userName : $('#change_user_name').val(),
		    	userEmail : $('#change_user_email').val(),
		    	trueName : $('#change_true_name').val(),
		    	userMobile : $('#change_user_mobile').val(),
		    	userPhone : $('#change_user_phone').val(),
		    	note : $('#change_note').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			$('#oper_result_label').html(data.data);
			    		blockUIOpen('operResultWin');
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
	
	//保存所修改的用户密码信息
	$('#saveChangeUserPassInfo').live("click", function() {
		$('#old_password').blur();
		$('#new_password').blur();
		$('#confirm_new_password').blur();
		var is_error = false;
		var _error = ["old_password", "new_password", "change_true_name", "confirm_new_password"];
		$.each(_error, function(key, val) {
        	var error_flag = $('#' + val).attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("wrong") >= 0) {
                	is_error = true;
                	return false
                }
        });
        if (is_error) return false;
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/setCurrentUserPassInfo.do',
		    dataType : 'json',
		    data : {
		    	password : $('#old_password').val(),
		    	newPassword : $('#new_password').val(),
		    	confirmNewPassword : $('#confirm_new_password').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			$('#oper_result_label').html(data.data);
			    		blockUIOpen('operResultWin');
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
	
	//点击用户设置
	$('#userInfoSet').click(function() {
		blockUIOpen('userInfoSetWin');
	});
	
	//点击退出系统按钮
	$('#logout').click(function() { 
		blockUIOpen('logoutConfrim');
    });
    //退出系统
	$('#yesLogout').live("click", function() { 
        blockUIClose();
		location.href = ctx + '/logout'
	});
	//所有的 取消 都可以使用该事件 关闭当前窗口
	$('#cancle').live("click", function() { 
        blockUIClose();
        return false;
	});
	
	//用户登录名校验
	$('#change_user_name').change(function() {
        checkUserName(this);
    });
    $('#change_user_name').blur(function() {
        checkUserName(this);
    });
    
    //用户真实姓名校验
	$('#change_true_name').change(function() {
        checkIsNull(this, "真实姓名");
    });
    $('#change_true_name').blur(function() {
        checkIsNull(this, "真实姓名");
    });
    
    //用户电子邮箱校验
	$('#change_user_email').change(function() {
        checkUserEmail(this, "电子邮箱");
    });
    $('#change_user_email').blur(function() {
        checkUserEmail(this, "电子邮箱");
    });
    
    //手机号码校验
	$('#change_user_mobile').change(function() {
        checkUserMobile(this);
    });
    $('#change_user_mobile').blur(function() {
        checkUserMobile(this);
    });
    
    //座机号码长度验证
    $('#change_user_phone').change(function() {
        checkUserPhone(this);
    });
    $('#change_user_phone').blur(function() {
        checkUserPhone(this);
    });
    
    //备注长度验证
    $('#change_note').change(function() {
        checkChangeNote(this);
    });
    $('#change_note').blur(function() {
        checkChangeNote(this);
    });
    
    //老密码校验
	$('#old_password').change(function() {
        checkOldPassword(this);
    });
    $('#old_password').blur(function() {
        checkOldPassword(this);
    });
    
    //新密码校验
	$('#new_password').change(function() {
        checkNewPassword(this);
    });
    $('#new_password').blur(function() {
        checkNewPassword(this);
    });
    
    //确认新校验
	$('#confirm_new_password').change(function() {
        checkConfirmNewPassword(this);
    });
    $('#confirm_new_password').blur(function() {
        checkConfirmNewPassword(this);
    });
    
    
    //系统右下角返回页面顶部
    
    ;(function($) { 
        $.scrollBtn = function(options) { 
            var opts = $.extend({}, $.scrollBtn.defaults, options); 

            var $scrollBtn = $('<div></div>').css({ 
                                bottom: opts.bottom + 'px', 
                                right: opts.right + 'px' 
                            }).addClass('scroll-up') 
                            .attr('title', opts.title) 
                            .click(function() { 
                                $('html, body').animate({scrollTop: 0}, opts.duration); 
                            }).appendTo('body'); 
                                                                                 
            $(window).bind('scroll', function() { 
                var scrollTop = $(document).scrollTop(), 
                    viewHeight = $(window).height(); 

                if(scrollTop <= opts.showScale) { 
                    if($scrollBtn.is(':visible')) 
                        $scrollBtn.fadeOut(500); 
                } else { 
                    if($scrollBtn.is(':hidden'))  
                        $scrollBtn.fadeIn(500); 
                } 

                if(isIE6()) { 
                    var top = viewHeight + scrollTop - $scrollBtn.outerHeight() - opts.bottom; 
                    $scrollBtn.css('top', top + 'px'); 
                } 
            }); 

            function isIE6() { 
                if($.browser.msie) { 
                    if($.browser.version == '6.0') return true; 
                } 
            } 
        }; 

        $.scrollBtn.defaults = { 
            showScale: 100,   
            right:10, 
            bottom:10, 
            duration:200, 
            title:'返回到顶部' 
        } 
    })(jQuery); 

    $.scrollBtn({ 
        showScale: 200, 
        bottom:20, 
        right:20 
    });
});

//==========================================
//Purpose: 在form表单提交前 添加遮罩层 并延时一定时间后再提交
//==========================================
function formSubmit() {
	blockUIOpen('handleWating');
	setTimeout(function() { 
    	$.unblockUI({ 
        	onUnblock: function(){ $('#searchForm').submit(); } 
        }); 
    }, 500);
}

//==========================================
//Purpose: 验证用户名输入控件
//==========================================
function checkUserName(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(objId, "请输入用户名", false);
        return false;
    } else if (!$(obj).val().match(/^[a-zA-Z]{1}([a-zA-Z0-9_@.]){5,29}$/)) {
    	//只处验证不能为空并且只能为英文或者数字或者下划线组成的6-30个字符
        seterror(objId, "<br/>用户名为字母、数字和下划线组成的6-30个字符,且以字母开头", false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}

//==========================================
//Purpose: 验证用户原密码输入控件
//==========================================
function checkOldPassword(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(objId, "请输入原密码", false);
        return false;
    } else if (!$(obj).val().match(/^([a-zA-Z0-9]|[._]|[`~!@#$%^&*\(\)\-+\{\}\[\]\\|;:'",.\/<>?]){6,15}$/)) {
        seterror(objId, "<br/>密码为英文字母、数字和特殊字符(不含空格)组成的6-15个字符", false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}
//==========================================
//Purpose: 验证用户新密码输入控件
//==========================================
function checkNewPassword(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(objId, "请输入新密码", false);
        return false;
    } else if (!$(obj).val().match(/^([a-zA-Z0-9]|[._]|[`~!@#$%^&*\(\)\-+\{\}\[\]\\|;:'",.\/<>?]){6,15}$/)) {
        seterror(objId, "<br/>密码为英文字母、数字和特殊字符(不含空格)组成的6-15个字符", false);
        return false;
    } else {
    	seterror(objId, "", true);
    	checkUserPassStrong(objId);
    }
    if ($(obj).val() == $("#confirm_new_password").val()) {
    	if ($("#confirm_new_password_error").html() == "两次密码不一致，请确认") {
    		seterror("confirm_new_password", "", true);
    	}
    }
}
//==========================================
//Purpose: 验证用户确认新密码输入控件
//==========================================
function checkConfirmNewPassword(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(objId, "请输入确认密码", false);
        return false;
    } else if($(obj).val() != $("#new_password").val()) {
    	seterror(objId, "两次密码不一致,请确认", false);
        return false;
    } else if (!$(obj).val().match(/^([a-zA-Z0-9]|[._]|[`~!@#$%^&*\(\)\-+\{\}\[\]\\|;:'",.\/<>?]){6,15}$/)) {
        seterror(objId, "<br/>密码为英文字母、数字和特殊字符(不含空格)组成的6-15个字符", false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}
//==========================================
//Purpose: 验证用户电子邮箱输入控件
//==========================================
function checkUserEmail(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(objId, "请输入电子邮箱", false);
        return false;
    } else if (!isEmail($(obj).val())) {
        seterror(objId, "电子邮箱格式有误", false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}
//==========================================
//Purpose: 验证用户手机号码输入控件
//==========================================
function checkUserMobile(obj) {
	var objId = $(obj).attr("id");
	checkEmpty(obj);
	if ($(obj).val() != null && $(obj).val() != "" && !isMobile($(obj).val())) {
        seterror(objId, "手机号码格式有误", false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}

//==========================================
//Purpose: 验证备注的输入
//==========================================
function checkChangeNote(obj) {
	var objId = $(obj).attr("id");
	checkEmpty(obj);
	if ($(obj).val() != null && $(obj).val() != "" && $(obj).val().length > 120) {
        seterror(objId, "备注长度限制为120字符", false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}

//==========================================
//Purpose: 验证座机号码的输入
//==========================================
function checkUserPhone(obj) {
	var objId = $(obj).attr("id");
	checkEmpty(obj);
	if ($(obj).val() != null && $(obj).val() != "" && $(obj).val().length > 15) {
        seterror(objId, "座机号码长度限制为15字符", false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}

function reloadCurrentPage(pageNum) {
	if (pageNum) {
		setTimeout(function() { 
			$.unblockUI({ 
        		onUnblock: function(){ $("#pageTurn" + pageNum).click(); } 
       		}); 
    	}, 1500);
	} else {
		setTimeout(function() { 
			$.unblockUI({ 
        		onUnblock: function(){ $('#searchForm').submit(); } 
       		}); 
    	}, 1500);
	}
}

function reloadPage(url) {
	setTimeout(function() { 
		$.unblockUI({ 
        	onUnblock: function(){ window.location.href=""+url+""; } 
       	}); 
    }, 1500);
}

function reloadShowPage(activityId) {
	setTimeout(function() { 
		$.unblockUI({ 
        	onUnblock: function(){
        		var form = $("<form>");   //定义一个form表单
				form.attr('style', 'display:none');   //在form表单中添加查询参数
				form.attr('target', '');
				form.attr('method', 'post');
				form.attr('action', ctx + "/activityShow.do");
				var input = $('<input>'); 
				input.attr('type', 'hidden'); 
				input.attr('name', 'activityId'); 
				input.attr('value', activityId); 
				$('body').append(form);  //将表单放置在web中
				form.append(input);   //将查询参数控件提交到表单上
				form.submit();   //表单提交
				form.remove();
        	} 
       	}); 
    }, 1500);
}

function reloadChangePage(activityId) {
	setTimeout(function() { 
		$.unblockUI({
        	onUnblock: function(){
        		var form = $("<form>");   //定义一个form表单
				form.attr('style', 'display:none');   //在form表单中添加查询参数
				form.attr('target', '');
				form.attr('method', 'post');
				form.attr('action', ctx + "/activityChange.do");
				var inputA = $('<input>'); 
				inputA.attr('type', 'hidden'); 
				inputA.attr('name', 'activityId'); 
				inputA.attr('value', activityId); 
				var inputB = $('<input>'); 
				inputB.attr('type', 'hidden'); 
				inputB.attr('name', 'autoSubmit'); 
				inputB.attr('value', 'true'); 
				$('body').append(form);  //将表单放置在web中
				form.append(inputA);   //将查询参数控件提交到表单上
				form.append(inputB);   //将查询参数控件提交到表单上
				form.submit();   //表单提交
				form.remove();
        	} 
       	}); 
    }, 500);
}

function checkUserPassStrong(objId) {
	//校验密码强度
    var strong = checkStrong($("#" + objId).val());
    $("#" + objId + "_error").show();
    if (strong == "1") {
    	$("#" + objId + "_error").html("<font color='black'>(密码强度</font><font color='red'> 弱 </font><font color='black'>,仅提示,不影响提交)");
    } else if (strong == "2") {
    	$("#" + objId + "_error").html("<font color='black'>(密码强度</font><font color='blue'> 中 </font><font color='black'>,仅提示,不影响提交)</font>");
    } else {
    	$("#" + objId + "_error").html("<font color='black'>(密码强度</font><font color='green'> 强 </font><font color='black'>,仅提示,不影响提交)</font>");
    }
}


//系统中英文切换
$("a[id*='locale_cn']").on('click', function() {
	var inputId = $(this).attr("id");
	//en_US
	reloadPage(ctx + "/dashboardCompliance.do?locale=zh_CN ");
	
});
$("a[id*='locale_en']").on('click', function() {
	var inputId = $(this).attr("id");
	//en_US
	reloadPage(ctx + "/dashboardCompliance.do?locale=en_US ");
	
});
*/
	
	//----------------------------------------------------------------------------------------------
	
	//分页所用的JS逻辑
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

	
});
//==========================================
//Purpose: 在form表单提交前 添加遮罩层 并延时一定时间后再提交
//==========================================
function formSubmit() {
	$('#searchForm').submit(); 
};

//------------------------------------------------------------------
function searchMatch3(){
	$("#pageNumInput").val(1);
	$('#searchForm3').submit(); 
};

  
// 求两个时间的天数差 日期格式为 YYYY/MM/dd   

function daysBetween(DateOne,DateTwo)  
{   
  var cha=((Date.parse(DateOne)- Date.parse(DateTwo))/86400000);   
  return Math.abs(cha);  
}  

// 更新信息提示
function updateTip(){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/updateTip.do',
	    dataType : 'json',
	    success : function(data) {
	    	// 获取当前时间
	    	var myDate = new Date();
	    	
	    	// 最近一次更新时间
	    	var oldTime = new Date(data.eUser.updateTime);
	    	
	    	// 激活时间激活时间
	    	var activateTime = new Date(data.eUser.activatetime);
	    	
	    	// 如果没有更新过，更新时间即为激活时间
	    	if (data.eUser.updateTime == null){
	    		oldTime = activateTime;
	    	}
	    	// 将当前时间和上次更新时间转化为YYYY/MM/dd 格式
	    	var betweenDay = daysBetween(myDate.toLocaleDateString(),oldTime.toLocaleDateString());
	    		if(betweenDay == parseInt(data.messageUpdate.frequency)){
	    			if(confirm("尊敬的"+data.eUser.userName+"，您的账号即将冻结，请更新个人信息！")){
	    				//window.location.href=ctx+"/userMessage.do";
	    				window.location.href="../pssweb/UserMessage.do";
	    			}
	    		}

	    	},
	    error:function(){
	    	alert("error!");
	    }
	});
}



//公共校验

//检验身份证
function checkidcard(num){
	var len = num.length;
	if (len == 15)
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
	else if (len == 18)
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
	else{
		//alert("请输入15或18位身份证号,您输入的是 "+len+ "位"); 
		return false;
	}
	var a = num.match(re);
	if (a != null){
		if (len==15){
			var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);
			var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
		}else{
			var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);
			var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
		}
		if (!B){
			//alert("输入的身份证号 "+ a[0] +" 里出生日期不对！"); 
			return false;
		}
	}

	return true;
}

/**
 * 校验主订单名称
 * @author Dylan
 * @param data	待校验数据
 * @returns	返回结果符合要返回true，反之为false
 */
function checkOrderName(data){
	var reg = /^[\u4e00-\u9fa5_a-zA-Z0-9]{1,15}$/;
	if(!reg.test(data)){
       return false;
    }
    return true;
}

//检验中文
function checkchina(str){
	var reg = /^[\u0391-\uFFE5]+$/;
    if(!reg.test(str)){
       return false;
    }
    return true;
} 


//检查名称不能为数字
function checkenglish(str){
	console.log(str);
	var reg = /^[A-Za-z]+$/;
	//alert(reg.test(str));
    if(!reg.test(str)){
       return false;
    }
    return true;
}
//非空校验
function checknull(str){
	var reg = /[\S]+$/;
	if(!reg.test(str)){
		return false;
	}
	return true;
}
//偏角校验
function checkpianjiao(str){
	var reg = /^[1-9]\d{30}$/;
	if(!reg.test(str)){
		return false;
	}
	return true;
}

//邮政编码校验
function checkpostcode(str){
	var reg = /^[1-9]\d{5}$/;
	if(!reg.test(str)){
		return false;
	}
	return true;
}
//传感器偏角校验
function checksideangle(str){
	var reg = /^(([1-9]|[12][0-9]|30)|(([1-9]|[12][0-9])\.[0-9][0-9]{0,1})|(0\.(0[1-9]|[1-9][0-9]{0,1}))|(30\.0{1,2}))$/;
	if(!reg.test(str)){
		return false;
	}
	return true;
}
//云盖量校验
function checkcloudcover(str){
	var reg = /^([1-9]\d?(\.\d{1,2})?|0\.\d{1,2}|100)$/;
	if(!reg.test(str)){
		return false;
	}
	return true;
}
//检验密码
function checkpassword(str){
	var  password=jQuery("#password").val();
	var  againpassword =jQuery("#againpassword").val();
	 if(password!= againpassword){
       return false;
    }
    return true;
	
}

//检验日期
function checkdata(str){
	var reg = /^[A-Za-z0-9]+$/;
    if(!reg.test(str)){
       return false;
    }
    return true;
} 

//检验电子邮箱
function checkemail(str){
	var reg =/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if(!reg.test(str)){
       return false;
    }
    return true;
} 

//检验手机号
function checkPhone(str){
	var reg =/^1[34578]\d{9}$/;
    if(!reg.test(str)){
       return false;
    }
    return true;
} 
//检验电话号
function checkMobile(str){
	var reg =/^0\d{2,3}-[1-9]\d{6,7}$/;
    if(!reg.test(str)){
       return false;
    }
    return true;
} 

//检验url地址
function checkUrl(str){
	var reg =/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
    if(!reg.test(str)){
       return false;
    }
    return true;
}


//
function checklongitude(str){
	var reg =/^-?(?:(?:180(?:\.0{1,5})?)|(?:(?:(?:1[0-7]\d)|(?:[1-9]?\d))(?:\.\d{1,5})?))$/;
    if(!reg.test(str)){
       return false;
    }
    return true;
}

//
function checklatitude(str){
	var reg =/^-?(?:90(?:\.0{1,5})?|(?:[1-8]?\d(?:\.\d{1,5})?))$/;
	//alert(reg.test(str));
    if(!reg.test(str)){
       return false;
    }
    return true;
}

function checkDouble(str){
	var reg = /^[-\+]?\d+(\.\d+)?$/;
	if(!reg.test(str)){
		return false;
	}
	return true;

}


//检查ip
function checkIp(str){
	var reg = /^((25[0-5]|2[0-4]\d|[01]?\d\d?)($|(?!\.$)\.)){4}$/;
	if(!reg.test(str)){
		return false;
	}
	return true;

}

//检查传真
function checkFax(str){
	var reg = /^(\d{3,4}-)?\d{7,8}$/;
	if(!reg.test(str)){
		return false;
	}
	return true;

}
function checkIngeter(str){
//alert("ingeter");
	var reg =/^[-\+]?\d+$/;
	if(!reg.test(str)){
		return false;
	}
	return true;
}







