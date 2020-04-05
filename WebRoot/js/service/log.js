/**
 * 用户管理
 */
//layer打开的顺序
var showIndex;

$(document).ready(function() {

	$("#searchMatch3").click(function() {
		
	});

	//点击搜索模块的"查看全部"按钮后，消除所有的搜索条件并将分页页码置为1
	$("#searchAll3").click(function() {
		$("#pageNumInput").val(1);
		clearSearchInput();
		$('#searchForm3').submit(); 
	});
  
		
	  //点击查看
	    $("a[id*='showLog']").click( function() {
	    	layer.msg($.i18n.prop('comeon'),{time:500});
	    	var objId = $(this).attr("id");
			var id=objId.replace("showLog_", "");
			jQuery.ajax({
				type : 'POST',
			    url : ctx + '/showLog.do',
			    dataType : 'json',
			    data : {
			    	logId:id
			    },
			    success : function(data) {
			    	console.log(data);
			    	$('#log_label_serviceName').html(data.data.serviceName);
			    	$('#log_label_fullUrl').html(data.data.fullUrl);
			    	$('#log_label_timeCost').html(data.data.timeCost);
			    	$('#log_label_userName').html(data.data.userName);
			    	$('#log_label_resultCode').html(data.data.resultCode);
			    	$('#log_label_resultMsg').html(data.data.resultMsg);
			    	$('#log_label_remoteIp').html(data.data.remoteIp);
			    	$('#log_label_serverIp').html(data.data.serverIp);
			    	$('#log_label_createTiem').html(data.data.createTiem);
			    	$('#log_label_responseTime').html(data.data.responseTime);
			    	showIndex=layer.open({
						title:$.i18n.prop('viewlog'),
					    type: 1,
					    //skin: 'layui-layer-rim', //加上边框
					    area: ['480px', '500px'], //宽高
					    content: $('#showLogWin').html()
					});
			    },
			    error:function(){
			    	layer.msg($.i18n.prop('comeon'),{time:500});
			    	setTimeout(function(){
			    		layer.alert($.i18n.prop('loaderrortryaggin'), {
			    		    skin: 'layer-ext-moon' 
			    		});
			    	} ,500);
			    }
			});
		});
	    
});
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

	function ShowCancleClose(){
		layer.close(showIndex);
	}
	
	 function showPage(tabId){
			 var id=tabId.replace("tab_", "");
			 $("#tabid").val(id);
			 formSubmit();
		 // $('#maintab a[href="#'+tabId+'"]').tab('show'); // 显示点击的tab页面
		  }
function searchMatch3Show(){
	$("#pageNumInput").val(1);
	$('#searchForm3').submit(); 
}
function searchAll3Show(){
	$("#pageNumInput").val(1);
	$("#username").val("");
	$("#description").val("");
	$('#searchForm3').submit(); 
}

function showUserLog(username){
	$("#username").val(username);
	$("#pageNumInput").val(1);
	$('#searchForm3').submit(); 
}

function deleteAll(){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/deleteAll.do',
	    dataType : 'json',
	    data : {
	    	username:$('#username').val(),
	    	description:$('#description').val(),
	    	startDate:$('#startDate').val(),
	    	endDate:$('#endDate').val()
	    },
	    success : function(data) {
	    	if(data.status=="success"){
	    		var index=layer.alert($.i18n.prop('deletesuccess'),{title:$.i18n.prop('title')}, function(){
    				layer.close(index);
    			});
	    	}else{
	    	layer.msg($.i18n.prop('comeon'),{time:500});
	    	setTimeout(function(){
	    		layer.alert($.i18n.prop('deleteerrortryagain'), {
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} ,500);
	    	}
	    },
	    error:function(){
	    	layer.msg($.i18n.prop('comeon'),{time:500});
	    	setTimeout(function(){
	    		layer.alert($.i18n.prop('deleteerrortryagain'), {
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} ,500);
	    }
	});
}
	 