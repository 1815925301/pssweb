/**
 * 城市管理JS代码
 * author: mrajian
 * createDate: 2013-10-22 19:24:45
 */
$(document).ready(function() {
	changeLang_js();
	//搜索框：点击区域选择框后 省份选框会联动
	$('#areaSearch').change(function() {
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/areaChangeProvince.do',
		    dataType : 'json',
		    data : {
		    	areaId : $('#areaSearch').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status == "success") {
		    			//联动省份选择框
		    			$("#provinceSearch").empty();
						$("#provinceSearch").append('<option selected="selected" value="-1">'+$.i18n.prop('chooseprivinces')+'</option>');
						$.each(data.data, function(i, item) {   
		              		$("#provinceSearch").append('<option value="' + item.id + '">' + item.province + '</option>');
		            	});
		            	$("#provinceSearch").prev().html($.i18n.prop('choosecity'));
					} else {
						$('#oper_result_label').html(data.data);
			    		blockUIOpen('operResultWin'); 
					}
		    	}
		 	},
		    error : function(data) {}
		});
	});
	
	//搜索框：点击省份选择框后 区域选框会联动 前提是区域选框的现状为没有选中任何区域
	$('#provinceSearch').change(function() {
		if ($('#provinceSearch').val() == -1) {
			return;
		}
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/provinceChangeArea.do',
		    dataType : 'json',
		    data : {
		    	provinceId : $('#provinceSearch').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status == "success") {
		    			$("#areaSearch").val(data.data);
						$("#areaSearch").prev().html($("#areaSearch").find("option:selected").text());
					} else {
						$('#oper_result_label').html(data.data);
			    		blockUIOpen('operResultWin'); 
					}
		    	}
		 	},
		    error : function(data) {}
		});
	});
	
	//新增城市：点击区域选择框后 省份选框会联动
	$('#area').change(function() {
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/areaChangeProvince.do',
		    dataType : 'json',
		    data : {
		    	areaId : $('#area').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status == "success") {
		    			//联动省份选择框
		    			$("#province").empty();
						$("#province").append('<option selected="selected" value="-1">'+$.i18n.prop('chooseprivinces')+'</option>');
						$.each(data.data, function(i, item) {   
		              		$("#province").append('<option value="' + item.id + '">' + item.province + '</option>');
		            	});
		            	$("#province").prev().html($.i18n.prop('choosecity'));
					} else {
						$('#oper_result_label').html(data.data);
			    		blockUIOpen('operResultWin'); 
					}
		    	}
		 	},
		    error : function(data) {}
		});
	});
	
	//新增城市：点击省份选择框后 区域选框会联动 前提是区域选框的现状为没有选中任何区域
	$('#province').change(function() {
		if ($('#province').val() == -1) {
			return;
		}
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/provinceChangeArea.do',
		    dataType : 'json',
		    data : {
		    	provinceId : $('#province').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status == "success") {
		    			$("#area").val(data.data);
						$("#area").prev().html($("#area").find("option:selected").text());
						checkArea($("#area"));
					} else {
						$('#oper_result_label').html(data.data);
			    		blockUIOpen('operResultWin'); 
					}
		    	}
		 	},
		    error : function(data) {}
		});
	});
	
	//修改城市信息：点击区域选择框后 省份选框会联动
	$('#change_area').change(function() {
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/areaChangeProvince.do',
		    dataType : 'json',
		    data : {
		    	areaId : $('#change_area').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status == "success") {
		    			//联动省份选择框
		    			$("#change_province").empty();
						$("#change_province").append('<option selected="selected" value="-1">'+$.i18n.prop('chooseprivinces')+'</option>');
						$.each(data.data, function(i, item) {   
		              		$("#change_province").append('<option value="' + item.id + '">' + item.province + '</option>');
		            	});
		            	$("#change_province").prev().html($.i18n.prop('choosecity'));
					} else {
						$('#oper_result_label').html(data.data);
			    		blockUIOpen('operResultWin'); 
					}
		    	}
		 	},
		    error : function(data) {}
		});
	});
	
	//修改城市信息：点击省份选择框后 区域选框会联动 前提是区域选框的现状为没有选中任何区域
	$('#change_province').change(function() {
		if ($('#change_province').val() == -1) {
			return;
		}
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/provinceChangeArea.do',
		    dataType : 'json',
		    data : {
		    	provinceId : $('#change_province').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status == "success") {
		    			$("#change_area").val(data.data);
						$("#change_area").prev().html($("#change_area").find("option:selected").text());
					} else {
						$('#oper_result_label').html(data.data);
			    		blockUIOpen('operResultWin'); 
					}
		    	}
		 	},
		    error : function(data) {}
		});
	});
	
	//点击新增城市
	$('#addNewCity').click(function() {
		$("#area").change();
		$('#city').val(""); $('#city').attr("class", "width130"); 
		$('#area').val(-1); $('#area').attr("class", "u_select");
		$("#area").prev().html($.i18n.prop('choosequyu'));
		$('#province').val(-1); $('#province').attr("class", "u_select");
		$("#province").prev().html($.i18n.prop('chooseprivince'));
		$('#city_note').val(""); $('#city_note').attr("class", "width220"); 
		$("#city_error").show(); $("#city_error").html("");
		$("#area_error").show(); $("#area_error").html("");
		$("#province_error").show(); $("#province_error").html("");
		$("#city_note_error").show(); $("#city_note_error").html("");
		blockUIOpen('addNewCityWin');
		setButtonStatus("saveNewCity", false); //设置按钮可用
	});
	
	//保存新增加的城市信息
	$('#saveNewCity').live("click", function() {
		setButtonStatus("saveNewCity", true); //设置按钮不可用
		$('#city').blur();
		$('#area').blur();
		$('#province').blur();
		$('#city_note').blur();
		var is_error = false;
		var _error = ["city", "area", "province", "city_note"];
		$.each(_error, function(key, val) {
        	var error_flag = $('#' + val).attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("wrong") >= 0) {
                	is_error = true;
                	return false;
                }
        });
        if (is_error) {
        	setButtonStatus("saveNewCity", false); //设置按钮可用
        	return false;
        }
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/saveNewCity.do',
		    dataType : 'json',
		    data : {
		    	city : $('#city').val(),
		    	provinceId : $('#province').val(),
		    	note : $('#city_note').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			$('#oper_result_label').html(data.data + $.i18n.prop('pagewillnew'));
			    		blockUIOpen('operResultWin');
			    		reloadCurrentPage($("#pageTotal").val());
					} else {
						$.each(data.data, function(i, item) {   
		              		seterror(item.key, item.value, false);
		            	});
		            	$("#saveNewCity").attr('disabled', false);
					}
		    	}
		 	},
		    error : function(data) {}
		});
	});
	
	//查看城市信息
    $("a[id*='showCity']").on('click', function() {
		var objId = $(this).attr("id");
		var cityId = objId.replace("showCity_", "");
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/showCity.do',
		    dataType : 'json',
		    data : {
		    	cityId : cityId
		    },
		    success : function(data) {
		    	if (data && data.status == "success") {
		    		$('#label_city').html(data.data.city);
		    		$('#label_province').html(data.data.province);
		    		$('#label_area').html(data.data.areaName);
		    		$('#label_city_create_time').html(data.data.createTime);
		    		$('#label_city_create_userName').html(data.data.createUserName);
		    		$('#label_city_update_time').html(data.data.updateTime);
		    		$('#label_city_update_userName').html(data.data.updateUserName);
		    		$('#label_city_note').html(data.data.note);
		    		blockUIOpen('cityInfoWin');
				} else {
					$('#oper_result_label').html(data.data);
			    	blockUIOpen('operResultWin');
				}
		    },
		    error : function(data) {}
		});
	});
	
	//修改城市信息 将城市信息铺回到页面
    $("a[id*='updateCity']").on('click', function() {
		var objId = $(this).attr("id");
		var cityId = objId.replace("updateCity_", "");
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/showCity.do',
		    dataType : 'json',
		    data : {
		    	cityId : cityId
		    },
		    success : function(data) {
		    	if (data && data.status == "success") {
		    		$('#change_city_id').val(cityId);
		    		$('#change_city').val(data.data.city);
		    		$('#change_area').val(data.data.areaId);
		    		$("#change_area").prev().html($("#change_area").find("option:selected").text());
		    		$('#change_city_note').val(data.data.note);
		    		areaChangeProvinceForUpdate(data.data.areaId, data.data.provinceId);
		    		$('#change_city').blur();
		    		$('#change_area').blur();
		    		$('#change_city_note').blur();
		    		blockUIOpen('changeCityWin');
		    		setButtonStatus("saveChangeCity", false); //设置按钮可用
				} else {
					$('#oper_result_label').html(data.data);
			    	blockUIOpen('operResultWin');
				}
		    },
		    error : function(data) {}
		});
	});
	
	//点击删除城市的按钮
	$("a[id*='removeCity']").on('click', function() {
		var objId = $(this).attr("id");
		var cityId = objId.replace("removeCity_", "");
		$('#remove_alert_info').html($.i18n.prop('deletecityornot') + $("#show_city_" + cityId).html() + "”？");
		$('#remove_city_id').val(cityId);
		blockUIOpen('removeCityConfrim');
		setButtonStatus("yesRemoveCity", false); //设置按钮可用
	});
	
	//删除城市
	$('#yesRemoveCity').live("click", function() {
		setButtonStatus("yesRemoveCity", true); //设置按钮不可用
		$.blockUIClose;
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/removeCity.do',
		    dataType : 'json',
		    data : {
		    	cityId : $('#remove_city_id').val()
		    },
		    success : function(data) {
		    	if (data && data.status == "success") {
		    		$('#oper_result_label').html(data.data + $.i18n.prop('pagewillnew'));
			    	blockUIOpen('operResultWin');
			    	reloadCurrentPage($("#pageNumInput").val());
				} else {
					$('#oper_result_label').html(data.data);
			    	blockUIOpen('operResultWin');
				}
		    },
		    error : function(data) {}
		});
	});
	
	//保存所修改的城市信息
	$('#saveChangeCity').live("click", function() {
		setButtonStatus("saveChangeCity", true); //设置按钮不可用
		$('#change_city').blur();
		$('#change_area').blur();
		$('#change_province').blur();
		$('#change_city_note').blur();
		var is_error = false;
		var _error = ["change_city", "change_province", "change_area", "change_city_note"];
		$.each(_error, function(key, val) {
        	var error_flag = $('#' + val).attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("wrong") >= 0) {
                	is_error = true;
                	return false
                }
        });
        if (is_error) {
        	setButtonStatus("saveChangeCity", false); //设置按钮可用
        	return false;
        }
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/changeCity.do',
		    dataType : 'json',
		    data : {
		    	id : $('#change_city_id').val(),
		    	city : $('#change_city').val(),
		    	provinceId : $('#change_province').val(),
		    	note : $('#change_city_note').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			$('#oper_result_label').html(data.data + $.i18n.prop('pagewillnew'));
			    		blockUIOpen('operResultWin');
			    		reloadCurrentPage($("#pageNumInput").val());
					} else {
						$.each(data.data, function(i, item) {   
		              		seterror(item.key, item.value, false);
		            	});
		            	setButtonStatus("saveChangeCity", false); //设置按钮可用
					}
		    	}
		 	},
		    error : function(data) {}
		});
	});
	
	//城市名称校验
	$('#city').change(function() {
        checkCity(this);
    });
    $('#city').blur(function() {
        checkCity(this);
    });
    
    //城市名称校验
	$('#change_city').change(function() {
        checkCity(this);
    });
    $('#change_city').blur(function() {
        checkCity(this);
    });
    
    //所属区域校验
	$('#area').change(function() {
        checkArea(this);
    });
    $('#area').blur(function() {
        checkArea(this);
    });
    
    //所属区域校验
	$('#change_area').change(function() {
        checkArea(this);
    });
    $('#change_area').blur(function() {
        checkArea(this);
    });
    
    //所属省份校验
    $('#province').change(function() {
        checkProvince(this);
    });
    $('#province').blur(function() {
        checkProvince(this);
    });
    
    //所属省份校验
    $('#change_province').change(function() {
        checkProvince(this);
    });
    $('#change_province').blur(function() {
        checkProvince(this);
    });
    
    //城市备注校验
	$('#city_note').change(function() {
        checkCityNote(this);
    });
    $('#city_note').blur(function() {
        checkCityNote(this);
    });
    
    //城市备注校验
	$('#change_city_note').change(function() {
        checkCityNote(this);
    });
    $('#change_city_note').blur(function() {
        checkCityNote(this);
    });
	
});

//==========================================
//Purpose: 点击搜索模块的"查看全部"按钮后，消除所有的搜索条件置为空，城市名称与城市类型
//==========================================
function clearSearchInput() {
	$("#citySearch").val("");
	$("#areaSearch").val(-1);
}

//==========================================
//Purpose: 过滤检索输入框中的特殊字符
//==========================================
function replaceSpecialInput() {
	$("#citySearch").val(replaceSpecialStr(Trim($("#citySearch").val())));
}

//==========================================
//Purpose: 验证城市名称
//==========================================
function checkCity(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(objId, $.i18n.prop('entercityname'), false);
        return false;
    } else if ($(obj).val().length > 15) {
        seterror(objId, $.i18n.prop('citynamenolong'), false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}

//==========================================
//Purpose: 验证所属区域信息
//==========================================
function checkArea(obj) {
	var objId = $(obj).attr("id");
	if ($(obj).val() == -1) {
        seterror(objId,$.i18n.prop('chooseloca'), false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}

//==========================================
//Purpose: 验证所属省份信息
//==========================================
function checkProvince(obj) {
	var objId = $(obj).attr("id");
	if ($(obj).val() == -1) {
        seterror(objId, $.i18n.prop('chooseprivince'), false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}

//==========================================
//Purpose: 验证备注的输入
//==========================================
function checkCityNote(obj) {
	var objId = $(obj).attr("id");
	checkEmpty(obj);
	if ($(obj).val() != null && $(obj).val() != "" && $(obj).val().length > 120) {
        seterror(objId, $.i18n.prop('notelength') false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}

function areaChangeProvinceForUpdate(areaId, provinceId){
	jQuery.ajax({
		type : 'POST',
		url : ctx + '/areaChangeProvince.do',
		dataType : 'json',
		data : {
			areaId : areaId
		},
		success : function(data) {
			if (data) {
				if (data.status == "success") {
					//联动省份选择框
		    		$("#change_province").empty();
					$("#change_province").append('<option value="-1">'+$.i18n.prop('chooseprivinces')+'</option>');
					$.each(data.data, function(i, item) {   
		            	$("#change_province").append('<option value="' + item.id + '">' + item.province + '</option>');
		            });
		            $("#change_province").val(provinceId);
		            $("#change_province").prev().html($("#change_province").find("option:selected").text());
				} else {
					$('#oper_result_label').html(data.data);
			    	blockUIOpen('operResultWin'); 
				}
		    }
		 },
		 error : function(data) {}
	});
}