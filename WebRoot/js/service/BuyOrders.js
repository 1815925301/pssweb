/**
 * 定购单
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;

$(document).ready(function() {
	changeLang_js();
	         
});
  //点击查看
function showpssorderinfo(id){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/BuyOrderinfoByid.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#order_tasktype').html(data.orders.tasktype);
	    	$('#order_username').html(data.orders.username);
	        $('#order_satelliteid').html(data.orders.satelliteid);
	    	$('#order_orderstate').html(data.orders.orderstate);
	    	$('#order_ispay').html(data.orders.ispay);
	    	$('#order_productlevel').html(data.orders.productlevel);
	    	$('#order_tasktime').html(data.orders.tasktime);
	    	$('#order_checkstate').html(data.orders.checkstate);
	    	showIndex=layer.open({
				title:$.i18n.prop('ViewOrders'),
			    type: 1,
			    area: ['880px', '450px'], //宽高
			    content: $('#showpssorderinfo').html()
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
}

//==========================================
//Purpose: 过滤检索输入框中的特殊字符
//==========================================
function replaceSpecialInput() {
	$("#userNameSearch").val(replaceSpecialStr($("#userNameSearch").val()));
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
	function removesystemConfig(id,uname){
		 var delIndex=layer.confirm($.i18n.prop('confirmdelete')+uname+'？', {
			    btn: [$.i18n.prop('yes'),$.i18n.prop('cancle')] //按钮
			}, function(){
				layer.msg($.i18n.prop('deleting'),{time:500});
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/deletedatabyid.do',
				    dataType : 'json',
				    data : {
				    	dataId:id
				    },
				    success : function(data) {
				    	alert("delete");
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
	};
//保存新增
	function saveSystemConfig(){
	
		
		$('.layui-layer-content').find("input[id='config_id_add']").blur();
		$('.layui-layer-content').find("input[id='config_key_']").blur();
		$('.layui-layer-content').find("input[id='config_value_']").blur();
		$('.layui-layer-content').find("input[id='configd_ess_']").blur();
		$('.layui-layer-content').find("input[id='create_time_']").blur();
		$('.layui-layer-content').find("input[id='config_type_add']").blur();
		
//		var is_error = false;
//		var _error = ["userName", "trueName"];
//		$.each(_error, function(key, val) {
//			var objId = $('#' + val).attr("id");
//			var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
//        	var error_flag = $(Objects).parent().parent().attr('class');
//            if (error_flag != undefined)
//            	if (error_flag.indexOf("error") >= 0) {
//                	is_error = true;
//                	return false;
//                }
//        });
        	var data =$('.layui-layer-content').find("form[id='addSystemForm']").serialize();
        	
//        	$("#submit").submit(function(){
//        		alert(111);
//        		return true;
//        		});
//        	$('#addSystemForm').submit(); 
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/saveNewSystemConfig.do',
			    dataType : 'json',
			    data : data,
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			var index=layer.alert($.i18n.prop('addsuccess'),{title:$.i18n.prop('title')}, function(){
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
	};
	$("#submit").submit(function(){
		return true;
		});

	//保存修改
	function saveChange(){
		
		$('.layui-layer-content').find("input[id='userName']").blur();
		$('.layui-layer-content').find("input[id='trueName']").blur();
		$('.layui-layer-content').find("input[id='password']").blur();
		$('.layui-layer-content').find("input[id='surepassword']").blur();
//		checkOrgName();
//		var is_error = false;
//		var _error = ["userName", "trueName", "password", "surepassword", "orgId"];
//		$.each(_error, function(key, val) {
//			var objId = $('#' + val).attr("id");
//			var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
//        	var error_flag = $(Objects).parent().parent().attr('class');
//            if (error_flag != undefined)
//            	if (error_flag.indexOf("error") >= 0) {
//                	is_error = true;
//                	return false;
//                }
//        });
        	var data =$('.layui-layer-content').find("form[id='changeForm']").serialize();
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/saveSystemConfigConfirm.do',
			    dataType : 'json',
			    data : data,
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			var index=layer.alert($.i18n.prop('addsuccess'),{title:$.i18n.prop('title')}, function(){
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
	};
	
//上传文件
	
	function openUpload_(){
		$.ajaxFileUpload({
			type: "POST",
			//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)   
			secureuri : false, //是否启用安全提交,默认为false    
			fileElementId : 'fileToUpload', //文件选择框的id属性   
			dataType:'json', //服务器返回的格式,可以是json或xml等   
			url : ctx + '/uploadfile.do',
			success : function(data) {
			if(data.resault=="success"){
					alert($.i18n.prop('savesuccess'));
				}else{
					alert($.i18n.prop('savefaile'));
				}
				
			}
		});	
	}
	
	
	