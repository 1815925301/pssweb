/**
 * 学生管理
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;

$(document).ready(function(){
	$("#searchMatch").click(function(){
//		alert("进入搜索");
		
		
	});
	
});
jQuery(".open").click(
		function() {
			$("#searchdisable").show();
			$("#sopen").hide();
			$("#sclostyh").show();
		});

jQuery("#sclostyh").click(
		function() {
			$("#searchdisable").hide();
			$("#sopen").show();
			$("#sclostyh").hide();
		});

$(document).ready(function() {
	//点击新增配置管理
	$('#addSystemConfig').click(function() {
		jQuery.ajax({
				type : 'POST',
			    url : ctx + '/SystemConfigAdd.do',
			    dataType : 'json',
			});
		
	});
	
	
});

////搜索按钮触发的事件
//$("#searchMatch").click(function() {
//	alert(111);
//	$("#pageNumInput").val(1);
//	//replaceSpecialInput();
//	formSubmit();
//});
//
////点击搜索模块的"查看全部"按钮后，消除所有的搜索条件并将分页页码置为1
//$("#searchAll").click(function() {
//	$("#pageNumInput").val(1);
//	clearSearchInput();
//	formSubmit();
//});

//修改
function updatesystemConfig(id){
	layer.msg($.i18n.prop('comeon'),{time:500});
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/modifysystem.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#configid_modify').attr('value',data.systemconfig.configid);
	    	$('#configname_modify').attr('value',data.systemconfig.configkey);
	    	$('#configvalue_modify').attr('value',data.systemconfig.configvalue);
	    	$('#configdes_modify').attr('value',data.systemconfig.configdes);
	    	$('#createtime_modify').attr('value',data.systemconfig.createtime);
	    	$('#configtype_modify').attr('value',data.systemconfig.configtype);
//	    	if(data.userInfo.roleName!=null){
//	    		$("#change_roleId option[text='"+data.userInfo.roleName+"']").attr("selected", true);  
//	    	}else{
//	    		 $("#change_roleId").append("<option value='-1' selected>请选择角色</option>");  
//	    	}
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
	    		layer.alert($.i18n.prop('loaderrortryaggin'), {
	    		    icon: 1,
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} ,500);
	    }
	});			
}
  //点击查看
function showsystemConfig(id){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/selectdatabyid.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#config_id').html(data.systemconfig.configid);
	    	$('#config_key').html(data.systemconfig.configkey);
	        $('#config_value').html(data.systemconfig.configvalue);
	    	$('#configd_ess').html(data.systemconfig.configdes);
	    	$('#create_time').html(data.systemconfig.createtime);
	    	$('#config_type').html(data.systemconfig.configtype);
	    	showIndex=layer.open({
				title:$.i18n.prop('viewuser'),
			    type: 1,
			    area: ['880px', '450px'], //宽高
			    content: $('#showsystemconfig').html()
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
		 var delIndex=layer.confirm($.i18n.prop('confirmdeletes')+uname+'？', {
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
        	alert(data);
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
	};
	
//上传文件
	
//	function openUpload_(){
//		alert("进入上传js");
//		$.ajaxFileUpload({
//			type: "POST",
//			//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)   
//			secureuri : false, //是否启用安全提交,默认为false    
//			fileElementId : 'fileToUpload', //文件选择框的id属性   
//			dataType:'json', //服务器返回的格式,可以是json或xml等   
//			url : ctx + '/uploadfile.do',
//			success : function(data) {
//			if(data.resault=="success"){
//					alert("保存成功");
//				}else{
//					alert("保存失败");
//				}
//				
//			}
//		});	
//	}
	
	
	