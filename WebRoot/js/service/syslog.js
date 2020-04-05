/**
 * 日志管理JS代码
 * author: mrajian
 * createDate: 2013-10-22 19:24:45
 */
$(document).ready(function() {

  //点击查看
    $("a[id*='showLog']").on('click', function() {
    	layer.msg('玩命加载中',{time:500});
    	var objId = $(this).attr("id");
    	var logId = objId.replace("showLog_", "");
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/showLog.do',
		    dataType : 'json',
		    data : {
				   logId : logId
			},
		    success : function(data) {
		    	if (data && data.status == "success") {

		    		$('#label_service_name').html(data.data.serviceName);
		    		$('#label_user_name').html(data.data.userName);
		    		$('#label_org_name').html(data.data.orgName);
		    		$('#label_result_code').html(data.data.resultCode);
		    		$('#label_result_msg').html(data.data.resultMsg);
		    		$('#label_time_cost').html(data.data.timeCost);
		    		$('#label_full_url').html(data.data.fullUrl);
		    		$('#label_remote_ip').html(data.data.remoteIp);
		    		$('#label_create_time').html(data.data.createTime);
		    		$('#label_response_time').html(data.data.responseTime);

		    		newIndex=layer.open({
		    			title:'日志信息',
		    		    type: 1,
		    		    //skin: 'layui-layer-rim', //加上边框
		    		    area: ['800px', '400px'], //宽高
		    		    content: $('#showLogWin').html()
		    		});
				}
		    
		    },
		    error:function(){
		    	layer.msg('玩命加载中',{time:500});
		    	setTimeout(function(){
		    		layer.alert('加载错误，请重试！', {
		    		    icon: 1,
		    		    skin: 'layer-ext-moon' 
		    		});
		    	} ,500);
		    }
		});
	});
    
})

//-------------------------------------------------
//Purpose: 点击搜索模块的"查看全部"按钮后，消除所有的搜索条件置为空，用户名称与用户类型
//==========================================
function clearSearchInput() {
	$("#userName").val("");
	$("#serviceName").val("");
}
