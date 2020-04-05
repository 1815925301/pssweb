/**
 * 省份管理JS代码
 * author: mrajian
 * createDate: 2013-10-19 19:24:45
 */
$(document).ready(function() {
	changeLang_js();
	//点击新增省份
	$('#addNewProvince').click(function() {
		$('#province').val(""); $('#province').attr("class", "width130"); 
		$('#area').val(-1); $('#area').attr("class", "u_select");
		$("#area").prev().html($.i18n.prop('choosequyu'));
		$('#province_note').val(""); $('#province_note').attr("class", "width220"); 
		$("#province_error").show(); $("#province_error").html("");
		$("#area_error").show(); $("#area_error").html("");
		$("#province_note_error").show(); $("#province_note_error").html("");
		blockUIOpen('addNewProvinceWin');
		setButtonStatus("saveNewProvince", false); //设置按钮可用
	});
	
	//保存新增加的省份信息
	$('#saveNewProvince').live("click", function() {
		setButtonStatus("saveNewProvince", true); //设置按钮不可用
		$('#province').blur();
		$('#area').blur();
		$('#province_note').blur();
		var is_error = false;
		var _error = ["province", "area", "province_note"];
		$.each(_error, function(key, val) {
        	var error_flag = $('#' + val).attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("wrong") >= 0) {
                	is_error = true;
                	return false;
                }
        });
        if (is_error) {
        	setButtonStatus("saveNewProvince", false); //设置按钮可用
        	return false;
        }
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/saveNewProvince.do',
		    dataType : 'json',
		    data : {
		    	province : $('#province').val(),
		    	areaId : $('#area').val(),
		    	note : $('#province_note').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			$('#oper_result_label').html(data.data + $.i18n.prop('pagenow'));
			    		blockUIOpen('operResultWin');
			    		reloadCurrentPage($("#pageTotal").val());
					} else {
						$.each(data.data, function(i, item) {   
		              		seterror(item.key, item.value, false);
		            	});
		            	setButtonStatus("saveNewProvince", false); //设置按钮可用
					}
		    	}
		 	},
		    error : function(data) {}
		});
	});
	
	//查看省份信息
    $("a[id*='showProvince']").on('click', function() {
		var objId = $(this).attr("id");
		var provinceId = objId.replace("showProvince_", "");
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/showProvince.do',
		    dataType : 'json',
		    data : {
		    	provinceId : provinceId
		    },
		    success : function(data) {
		    	if (data && data.status == "success") {
		    		$('#label_province').html(data.data.province);
		    		$('#label_area').html(data.data.areaName);
		    		$('#label_province_create_time').html(data.data.createTime);
		    		$('#label_province_create_userName').html(data.data.createUserName);
		    		$('#label_province_update_time').html(data.data.updateTime);
		    		$('#label_province_update_userName').html(data.data.updateUserName);
		    		$('#label_province_note').html(data.data.note);
		    		blockUIOpen('provinceInfoWin');
				} else {
					$('#oper_result_label').html(data.data);
			    	blockUIOpen('operResultWin');
				}
		    },
		    error : function(data) {}
		});
	});
	
	//修改省份信息 将省份信息铺回到页面
    $("a[id*='updateProvince']").on('click', function() {
		var objId = $(this).attr("id");
		var provinceId = objId.replace("updateProvince_", "");
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/showProvince.do',
		    dataType : 'json',
		    data : {
		    	provinceId : provinceId
		    },
		    success : function(data) {
		    	if (data && data.status == "success") {
		    		$('#change_province_id').val(provinceId);
		    		$('#change_province').val(data.data.province);
		    		$('#change_area').val(data.data.areaId);
		    		$("#change_area").prev().html($("#change_area").find("option:selected").text());
		    		$('#change_province_note').val(data.data.note);
		    		$('#change_province').blur();
		    		$('#change_area').blur();
		    		$('#change_province_note').blur();
		    		blockUIOpen('changeProvinceWin');
		    		setButtonStatus("saveChangeProvince", false); //设置按钮可用
				} else {
					$('#oper_result_label').html(data.data);
			    	blockUIOpen('operResultWin');
				}
		    },
		    error : function(data) {}
		});
	});
	
	//点击删除省份的按钮
	$("a[id*='removeProvince']").on('click', function() {
		var objId = $(this).attr("id");
		var provinceId = objId.replace("removeProvince_", "");
		$('#remove_alert_info').html($.i18n.prop('deleteprivince') + $("#show_province_" + provinceId).html() + "”？");
		$('#remove_province_id').val(provinceId);
		blockUIOpen('removeProvinceConfrim');
		setButtonStatus("yesRemoveProvince", false); //设置按钮可用
	});
	
	//删除省份
	$('#yesRemoveProvince').live("click", function() {
		setButtonStatus("yesRemoveProvince", true); //设置按钮不可用
		$.blockUIClose;
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/removeProvince.do',
		    dataType : 'json',
		    data : {
		    	provinceId : $('#remove_province_id').val()
		    },
		    success : function(data) {
		    	if (data && data.status == "success") {
		    		$('#oper_result_label').html(data.data +$.i18n.prop('pagenow'));
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
	
	//保存所修改的省份信息
	$('#saveChangeProvince').live("click", function() {
		setButtonStatus("saveChangeProvince", true); //设置按钮不可用
		$('#change_province').blur();
		$('#change_area').blur();
		$('#change_province_note').blur();
		var is_error = false;
		var _error = ["change_province", "change_area", "change_province_note"];
		$.each(_error, function(key, val) {
        	var error_flag = $('#' + val).attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("wrong") >= 0) {
                	is_error = true;
                	return false
                }
        });
        if (is_error) {
        	setButtonStatus("saveChangeProvince", false); //设置按钮可用
        	return false;
        }
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/changeProvince.do',
		    dataType : 'json',
		    data : {
		    	id : $('#change_province_id').val(),
		    	province : $('#change_province').val(),
		    	areaId : $('#change_area').val(),
		    	note : $('#change_province_note').val()
		    },
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			$('#oper_result_label').html(data.data + $.i18n.prop('pagenow'));
			    		blockUIOpen('operResultWin');
			    		reloadCurrentPage($("#pageNumInput").val());
					} else {
						$.each(data.data, function(i, item) {   
		              		seterror(item.key, item.value, false);
		            	});
		            	setButtonStatus("saveChangeProvince", false); //设置按钮可用
					}
		    	}
		 	},
		    error : function(data) {}
		});
	});
	
	//省份名称校验
	$('#province').change(function() {
        checkProvince(this);
    });
    $('#province').blur(function() {
        checkProvince(this);
    });
    
    //省份名称校验
	$('#change_province').change(function() {
        checkProvince(this);
    });
    $('#change_province').blur(function() {
        checkProvince(this);
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
    
    //省份备注校验
	$('#province_note').change(function() {
        checkProvinceNote(this);
    });
    $('#province_note').blur(function() {
        checkProvinceNote(this);
    });
    
    //省份备注校验
	$('#change_province_note').change(function() {
        checkProvinceNote(this);
    });
    $('#change_province_note').blur(function() {
        checkProvinceNote(this);
    });
	
});

//==========================================
//Purpose: 点击搜索模块的"查看全部"按钮后，消除所有的搜索条件置为空，省份名称与省份类型
//==========================================
function clearSearchInput() {
	$("#provinceSearch").val("");
	$("#areaSearch").val(-1);
}

//==========================================
//Purpose: 过滤检索输入框中的特殊字符
//==========================================
function replaceSpecialInput() {
	$("#provinceSearch").val(replaceSpecialStr(Trim($("#provinceSearch").val())));
}

//==========================================
//Purpose: 验证省份名称
//==========================================
function checkProvince(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(objId, $.i18n.prop('enterprivince'), false);
        return false;
    } else if ($(obj).val().length > 15) {
        seterror(objId, $.i18n.prop('privincename'), false);
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
        seterror(objId,$.i18n.prop('choosequyu'), false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}

//==========================================
//Purpose: 验证备注的输入
//==========================================
function checkProvinceNote(obj) {
	var objId = $(obj).attr("id");
	checkEmpty(obj);
	if ($(obj).val() != null && $(obj).val() != "" && $(obj).val().length > 120) {
        seterror(objId, $.i18n.prop('notelength'), false);
        return false;
    } else {
    	seterror(objId, "", true);
    }
}