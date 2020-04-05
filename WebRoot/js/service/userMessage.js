
$(document).ready(function() {
	changeCountry();

	changeLang_js();
});
// 国家和省份联动
function changeCountry() {
	var optiontitle='<option value="-1">--'+$.i18n.prop('choose')+'--</option>';
	var countryCode = jQuery("#country").val();
	// 如果没有选中国家，省级列表置空，否则加载该国家的所有省份
	if (countryCode == -1) {
		$("#province").empty();
		jQuery("#province").append(optiontitle);
		changeProvince();
	} else {
		$("#province").empty();
		jQuery("#province").append(optiontitle);
		jQuery.ajax({
			type : 'POST',
			url : ctx + '/getProvince.do',
			dataType : 'json',
			data : {
				countryCode : countryCode
			},
			success : function(data) {
				if (data.pssProvinceList != null) {
					var provinces = data.pssProvinceList;
					for (var i = 0; i < provinces.length; i++) {
						var province = provinces[i];
						var option;
						// 页面初始化时，在省份列表中默认选中用户所在省
						if (data.user.province == province.code) {
							option = '<option value="' + province.code
									+ '" selected="selected">' + province.name
									+ '</option>';
						} else {
							option = '<option value="' + province.code + '">'
									+ province.name + '</option>';
						}
						jQuery("#province").append(option);
					}
				}
				changeProvince();
			},
			error : function() {
				layer.alert($.i18n.prop('wrong'), {
					
					skin : 'layer-ext-moon'
				});
			}
		});
	}
}


// 省份与市联动
function changeProvince() {
	var optiontitle='<option value="-1">--'+$.i18n.prop('choose')+'--</option>';
	var provinceCode = $('#province option:selected').val();
	// 如果没有选中省份，市级列表置空，否则加载该省份的所有市
	if(provinceCode==-1){
		$("#city").empty();
		jQuery("#city").append(optiontitle);
		changeCity();
	}else{
		
		$("#city").empty();
		jQuery("#city").append(optiontitle);
		
		jQuery.ajax({
		type : 'POST',
	    url : ctx + '/getCity.do',
	    dataType : 'json',
	    data : {
	    	provinceCode:provinceCode
	    },
	    success : function(data) {
	    	if (data.pssCityList != null) {
    			var cityes = data.pssCityList;
    			for(var i =0;i<cityes.length;i++){
    				var city = cityes[i];
    				var option;
    				// 页面初始化时，在市列表中默认选中用户所在市
    				if(data.user.city == city.code){
    					option = '<option value="' + city.code
    					+ '"selected="selected" >'
    									+ city.name
    									+ '</option>';}
    				else{
    				 option = '<option value="' + city.code
					+ '" >'
									+ city.name
									+ '</option>';}
							jQuery("#city").append(option);
    			}
    		}
	    	changeCity();
	    },
	    error:function(){
	    		layer.alert($.i18n.prop('moveprivincefaile'), {
	    		  
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} 
	});
    
	}
}

//市和县联动
function changeCity() {
var optiontitle='<option value="-1">--'+$.i18n.prop('choose')+'--</option>';
var cityCode = jQuery("#city").val();
//如果没有选中市，县级列表置空，否则加载该市的所有县
	if(cityCode==-1){
		$("#county").empty();
		jQuery("#county").append(optiontitle);
		
	}else{	
		$("#county").empty();
		jQuery("#county").append(optiontitle);
		jQuery.ajax({
		type : 'POST',
	    url : ctx + '/getTown.do',
	    dataType : 'json',
	    data : {
	    	cityCode:cityCode
	    },
	    success : function(data) {
	    	if (data.pssTownList != null) {
    			var towns = data.pssTownList;
    			for(var i =0;i<towns.length;i++){
    				var town = towns[i];
    				var option;
    				// 页面初始化时，在县列表中默认选中用户所在县
    				if(data.user.county == town.code){
    					option = '<option value="' + town.code
    					+ '" selected="selected" >'
    									+ town.name
    									+ '</option>';}
    				else{
    				 option = '<option value="' + town.code
					+ '" >'
									+ town.name
									+ '</option>';}
							jQuery("#county").append(option);
    			}
    		}
	    },
	    error:function(){
	    		layer.alert($.i18n.prop('faile'), {
	    		    
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} 
	});
    
	}
}

// 更新用户信息
function saveUserMessage(){
	var data =$('#userForm').serialize();
	var flag = validateUser();
	if(flag){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/saveUserMessage.do',
	    dataType : 'json',
	    data:data,
	    success : function(data) {
	    	layer.alert($.i18n.prop('success'), {
    		    
//    		    skin: 'layer-ext-moon' 
    		    title:$.i18n.prop('title')
	    		
    		});
	    },
	    error:function(){
	    		layer.alert($.i18n.prop('faile'), {
	    		    
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} 
	});
	}
}


// 校验电子邮箱
$('#email').live('blur', function() {
	var flag = checkemail($(this).val());
	var null_check = checknull($(this).val());
	// 检验电子邮箱是否为空
	if (!null_check) {
		$("#email_tip").show();
		$("#email_error").hide();
	} else {
		$("#email_tip").hide();
		if (!flag) {
			$("#email_error").show();
			result = false;
		} else {
			$("#email_error").hide();
		}
	}
});

// 检验名称不能为空
$("#trueName").live('blur', function() {
	var flag = checknull($(this).val());
	if (!flag) {
		$("#trueName_error").show();
		result = false;
	} else {
		$("#trueName_error").hide();
	}

});

// 检验用户职称不能为空

$("#userTitle").live('blur', function() {
	var flag = checknull($(this).val());
	if (!flag) {
		$("#userTitle_error").show();
		result = false;
	} else {
		$("#userTitle_error").hide();
	}

});
// 检验两次输入密码是否相同
$("#againpassword").live('blur', function() {
	var flag = checkpassword($(this).val());
	if (!flag) {
		$("#apassword_error").show();
		result = false;
	} else {
		$("#apassword_error").hide();
	}
});

// 检验密码是否为空
$("#password").live('blur', function() {
	var null_check = checknull($(this).val());
	if (!null_check) {
		$("#password_error").show();
		result = false;
	} else {
		$("#password_error").hide();
	}
});

// 检验ip地址
$("#ipAddress").live('blur', function() {
	var flag = checkIp($(this).val());
	var null_check = checknull($(this).val());
	if (!null_check) {
		$("#ipAddress_error").hide();
	} else {
		if (!flag) {
			$("#ipAddress_error").show();
			result = false;
		} else {
			$("#ipAddress_error").hide();
		}
	}
});

// 检验邮编

$("#postcode").live('blur', function() {
	var flag = checkpostcode($(this).val());
	var null_check = checknull($(this).val());
	// 检验邮编是否为空
	if (!null_check) {
		$("#postcode_tip").show();
		$("#postcode_error").hide();
	} else {
		$("#postcode_tip").hide();
		if (!flag) {
			$("#postcode_error").show();
			result = false;
		} else {
			$("#postcode_error").hide();
		}
	}
});

// 检验传真
$("#faxfax").live('blur', function() {
	var flag = checkFax($(this).val());
	var null_check = checknull($(this).val());
	// 校验传真是否为空
	if (!null_check) {
		$("#fax_tip").show();
		$("#fax_error").hide();
	} else {
		$("#fax_tip").hide();
		if (!flag) {
			$("#fax_error").show();
			result = false;
		} else {
			$("#fax_error").hide();
		}
	}
});

// 检验手机号
$("#userMobile").live('blur', function() {
	var flag = checkPhone($(this).val());
	var null_check = checknull($(this).val());
	if (!null_check) {
		$("#mobile_error").hide();
	} else {
		if (!flag) {
			$("#mobile_error").show();
			result = false;
		} else {
			$("#mobile_error").hide();
		}
	}

});

// 检验座机号
$("#userPhone").live('blur', function() {
	var flag = checkMobile($(this).val());
	var null_check = checknull($(this).val());
	// 校验座机号是否为空
	if (!null_check) {
		$("#phone_tip").show();
		$("#phone_error").hide();
	} else {
		$("#phone_tip").hide();
		if (!flag) {
			$("#phone_error").show();
			result = false;
		} else {
			$("#phone_error").hide();
		}
	}
});
// 校验证件号
$("#identitycard").live('blur', function() {
	var idcard = $(this).val();
	var flag = checkidcard($(this).val());
	var null_check = checknull($(this).val());
	// 检验证件号是否为空
	if (!null_check) {
		$("#identitycard_tip").show();
		$("#identitycard_error").hide();
	} else {
		$("#identitycard_tip").hide();
		if (!flag) {
			$("#identitycard_error").show();
			result = false;
		} else {
			$("#identitycard_error").hide();
		}
	}
});

// 校验法人邮箱
$("#lawyerMail").live('blur', function() {
	var flag = checkemail($(this).val());
	var null_check = checknull($(this).val());
	if (!null_check) {
		$("#lawyerMail_error").hide();
	} else {
		if (!flag) {
			$("#lawyerMail_error").show();
		} else {
			$("#lawyerMail_error").hide();
		}
	}

});

// 校验法人电话
$("#lawyerPhone").live('blur', function() {
	var flag = checkPhone($(this).val());
	var null_check = checknull($(this).val());
	if (!null_check) {
		$("#lawyerPhone_error").hide();
	} else {
		if (!flag) {
			$("#lawyerPhone_error").show();
		} else {
			$("#lawyerPhone_error").hide();
		}
	}

});

// 检验公司邮编
$("#companyCode").live('blur', function() {
	var flag = checkpostcode($(this).val());
	var null_check = checknull($(this).val());
	if (!null_check) {
		$("#companyCode_error").hide();
	} else {
		if (!flag) {
			$("#companyCode_error").show();
			result = false;
		} else {
			$("#companyCode_error").hide();
		}
	}
});

// 检验公司传真
$("#companyFax").live('blur', function() {
	var flag = checkFax($(this).val());
	var null_check = checknull($(this).val());
	if (!null_check) {
		$("#companyFax_error").hide();
	} else {
		if (!flag) {
			$("#companyFax_error").show();
			result = false;
		} else {
			$("#companyFax_error").hide();
		}
	}

});
// 检验公司ip地址
$("#companyIp").live('blur', function() {
	var flag = checkIp($(this).val());
	var null_check = checknull($(this).val());
	if (!null_check) {
		$("#companyIp_error").hide();
	} else {
		if (!flag) {
			$("#companyIp_error").show();
			result = false;
		} else {
			$("#companyIp_error").hide();
		}
	}
});
// 检验公司名称是否为空
$("#company").live('blur', function() {
	var flag = checknull($(this).val());
	if (!flag) {
		$("#company_error").show();
		result = false;
	} else {
		$("#company_error").hide();
	}
});



// 检验所属机构代码是否为空
$("#orgId").live('blur', function() {
	var flag = checknull($(this).val());
	if (!flag) {
		$("#orgId_error").show();
		result = false;
	} else {
		$("#orgId_error").hide();
	}
});

// 检验通讯地址是否为空
$("#address").live('blur', function() {
	var flag = checknull($(this).val());
	if (!flag) {
		$("#address_tip").show();
	} else {
		$("#address_tip").hide();
	}
});
	
// 保存时验证
function validateUser() { 
	var result = true;
	// 通讯地址为空
	var address=checknull($('#address').val());
	if (!address) {
		$("#address_tip").show();
		result = false;
	} else {
		$("#address_tip").hide();
		
	} 
	
	var userMobile=$('#userMobile').val();
	var flag = checkPhone(userMobile);
	var null_check = checknull(userMobile);
	if (!null_check) {
		$("#mobile_error").hide();
	} else {
		if (!flag) {
			$("#mobile_error").show();
			result = false;
		} else {
			$("#mobile_error").hide();
		}
	}
	// 机构代码
	var orgId = checknull($('#orgId').val());
	if (!orgId) {
		$("#orgId_error").show();
		result = false;
	} else {
		$("#orgId_error").hide();
	
	} 
	// 公司名称
	var company = checknull($('#company').val());
	if (!company) {
		$("#company_error").show();
		result = false;
	} else {
		$("#company_error").hide();
		
	}
	// 检验真实名称是否为空
	var trueName = checknull($('#trueName').val());
	if (!trueName) {
		$("#trueName_error").show();
		result = false;
	} else {
		$("#trueName_error").hide();
		
	}
	// 检验密码输入是否正确
	var apassword = checkpassword($("#againpassword").val());
	if (!apassword) {
		$("#apassword_error").show();
		result = false;
	} else {
		$("#apassword_error").hide();
		
	}
	// 检验用户职称是否正确
	var userTitle = checknull($('#userTitle').val());
	if (!userTitle) {
		$("#userTitle_error").show();
		result = false;
	} else {
		$("#userTitle_error").hide();
		
	}
	var faxlength = $('#faxfax').val().length;
	if(faxlength==0){
		$("#fax_error").show();
	}else{
	var fax_flag = checkFax($('#faxfax').val());
	var fax = checknull($('#faxfax').val());
	// 校验传真是否为空
	if (!fax) {
		$("#fax_tip").show();
		$("#fax_error").hide();
		result = false;
	} else {
		$("#fax_tip").hide();
		if (!fax_flag) {
			$("#fax_error").show();
			result = false;
		} else {
			$("#fax_error").hide();
		}
	}
	}
	
	var postcodelength = $('#postcode').val().length;
	if(postcodelength==0){
		$("#companyCode_error").show();
	}else{
	var postcode_flag = checkpostcode($('#postcode').val());
	var postcode = checknull($('#postcode').val());
	// 检验邮编是否为空
	if (!postcode) {
		$("#postcode_tip").show();
		$("#postcode_error").hide();
	} else {
		$("#postcode_tip").hide();
		if (!postcode_flag) {
			$("#postcode_error").show();
			result = false;
		} else {
			$("#postcode_error").hide();
		}
	}
	}
	var phoneleng = $('#userPhone').val().length;
	if(phoneleng==0){
		$("#phone_error").show();
		result = false;
	}else{
	var phone = checkMobile($('#userPhone').val());
	var phone_flag = checknull($('#userPhone').val());
	// 校验座机号是否为空
	if (!phone_flag) {
		$("#phone_tip").show();
		$("#phone_error").hide();
	} else {
		$("#phone_tip").hide();
		if (!phone) {
			$("#phone_error").show();
			result = false;
		} else {
			$("#phone_error").hide();
		}
	}
	}
	return result;
}

function getVip(color){
	
	if(color=="black"){
//		alert("跳转到会员信息页面");
		window.location.href=("InfoOfVIP.do");//跳转到会员信息页面
		
	}else{
//		alert("跳转到会员开通页面")
		window.location.href=("dredgeVIP.do?roleName=2");//跳转到会员开通页面

	}
	
	
}
