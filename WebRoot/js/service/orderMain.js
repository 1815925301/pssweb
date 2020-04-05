//layer打开的顺序
var newIndex,showIndex,editIndex;

$(document).ready(function() {
	changeLang_js();

});


/**
 * 折叠/展开子订单
 * 
 * @param _class
 *            当前对象的子对象class
 * @author Dylan
 * @date 16-08-14 13:52:30
 */
function TestBlack(_class) {
	var trs = $("." + _class);// 根据class获取对象
	if (trs.length > 0) {// 对象是否有值
		if (trs[0].style.display == "none") {
//			$("#img"+_class).attr('src', 'imgnew/icon23s.png');
			for (i = 0; i < trs.length; i++) {
				trs[i].style.display = "table-row"; // 这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法
			}
		} else {
//			$("#img"+_class).attr('src', 'imgnew/icon23.png');
			for (i = 0; i < trs.length; i++) {
				trs[i].style.display = "none"; // 这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法
			}
		}
	}
}

/**
 * 复选框全选/取消
 * @param name
 */
function checkAll(name){
    if ($("#che_"+name+":checked").length > 0){
    	$("tr[idflag="+name+"] span").addClass("checked");
        $("input[cheFlag="+name+"]").each(function(){ 
    		this.checked = true;
    	}); 
    }else{
    	$("tr[idflag="+name+"] span").removeClass();
        $("input[cheFlag="+name+"]").each(function(){ 
    		this.checked = false;
    	}); 
   }  
     
}  

function downloadftp(filename){
	jQuery.ajax({
		type : 'POST',
		url : ctx + '/ftpdown.do',
		data : {
			filename : filename
		},
		success : function(data) {
			
		}
	});
}
/**
 * 关闭 追加"订单跟踪"信息窗口
 */
function closeOrderTracking(){
	layer.close(editIndex);
}
/**
 * 打开 追加"订单跟踪"信息窗口
 */
function openTracking(){
	editIndex=layer.open({
		title:$.i18n.prop('addorderflower'),
		type: 1,
		area: ['500px', '280px'], //宽高
		content: $('#addInfoWindow').html()
	});
}
/**
 * 打开 追加"订单跟踪"信息窗口
 * @param orderid	订单id
 * @author Dylan
 * @date 16-10-11 10:30:39
 */
function openOrderTracking(orderid){
	$('#orderid').attr('value', "");//清空 订单id
	$('#orderid').attr('value',orderid);//回填订单id
	$('#orderTracking').text("");//清空 "订单跟踪"信息
	jQuery.ajax({
		type : 'POST',
		url : ctx + '/getOrderinfoByid.do',
		dataType : 'json',
		data : {
			orderid : orderid
		},
		success : function(data) {
			if (undefined != data) {
				var orderTracking = data.order.orderTracking;
				if(undefined != orderTracking){
					$('#orderTracking').text(orderTracking);//回填 "订单跟踪"信息
				}
			} 
			openTracking();
		}
	});
}

/**
 * 追加"订单跟踪"信息
 * @author Dylan
 * @date 16-10-11 10:30:39
 */
function addOrderTracking(){
	var orderid = $('#orderid').val();
	var data =$('.layui-layer-content').find("form[id='changeForm']").serialize();
	var orderTracking = (data.split("&")[2]).split("=")[1];
	if(orderTracking == ""){
//		var index=layer.alert('“订单追踪”信息不能为空！',{icon: 3, title:'提示'}, function(){
//			layer.closeAll();
//		});
		return false;
	}
	
	jQuery.ajax({
		type : 'POST',
		url : ctx + '/addOrderTracking.do',
		dataType : 'json',
		data : data,
		success : function(data) {
			if (data.status == "success") {
				var index = layer.alert($.i18n.prop('orderflloweraddsuccess'), {
					title : $.i18n.prop('title')
				}, function() {
					layer.closeAll();
				});
				closeOrderTracking();//关闭窗口
			} else {
				var index = layer.alert($.i18n.prop('orderflloweraddfaile'), {
					title :  $.i18n.prop('title')
				}, function() {
					layer.closeAll();
				});
			}
		}
	});
	
}

/**
 * 二级联动（传感器）
 * 
 * @param _this
 *            当前对象
 * @author Dylan
 * @date 16-08-14 13:52:30
 */
function sensorSelcect(_this) {
	var satelliteId = $(_this).val();
	var htm = "<option value='-1'>"+$.i18n.prop('choose')+"</option>";
	if ("-1" != satelliteId) {// 判断是否为请选择
		jQuery.ajax({
			type : 'POST',
			url : ctx + '/sensorSelcect.do',
			dataType : 'json',
			data : {
				satelliteId : satelliteId
			},
			success : function(data) {
				if (data.status == "success") {
					var valArray = data.valArray;
					for (x in valArray) {
						var data = valArray[x];
						htm = htm + "<option value=''>" + data + "</option>";
					}
					$("#_sensorId").html(htm);
				} else {
					var index = layer.alert($.i18n.prop('getsensonfaile'), {
						title : $.i18n.prop('title')
					}, function() {
						layer.closeAll();
					});
				}
			}
		});
	} else {
		$("#_sensorId").html(htm);
	}

}

/**
 * 下载函数
 * @param flag xls下载excel文件，shp下载shape文件
 * @returns {Boolean}
 * @author Dylan
 * @date 16-09-01 15:56:30
 */
function orderExcel(flag){
	var sonOrderIds = ""; 
	$('input[name="checkbox"]:checked').each(function(){ 
		sonOrderIds = sonOrderIds + $(this).val() + ","; 
	}); 
	if(sonOrderIds == ""){
		var index=layer.alert($.i18n.prop('nochooseexportorder'),{title:$.i18n.prop('title')}, function(){
			layer.closeAll();
			return;
		});
	}else{
		if(sonOrderIds.length>0){
			sonOrderIds = sonOrderIds.substring(0, sonOrderIds.length-1);//截取字符串
		}
		if("xls"==flag){//xls下载excel文件
			window.location.href=ctx + "/exporXls.do?flag=1&sonOrderIds="+sonOrderIds;
		}else if("shp" == flag){//shp下载shape文件
			window.location.href=ctx + "/exportShp.do?sonOrderIds="+sonOrderIds;
		}else if("csv"==flag){
			window.location.href=ctx + "/exportCsv.do?flag=1&sonOrderIds="+sonOrderIds;
		}else if("xml"==flag){
			window.location.href=ctx + "/exportXml.do?sonOrderIds="+sonOrderIds;
		}
		
	}
	
}

/**
 * 重置查询条件
 */
function reset(){
	$("#_satelliteId").val("-1");//卫星
	$("#_sensorId").val("-1");//传感器
	$("#_taskType").val("-1");//类型
	$("#_productLevel").val("-1");//产品级别
	$("#_areadystate").val("-1");//审核状态
	$("#_orderState").val("-1");//处理状态
	$("#_orderId").val("");//订单号
	$("#_sceneId").val("");//景序列号
	$("#orderStartTime").val("");
	$("#orderEndTime").val("");
	$("#checkStartTime").val("");
	$("#checkEndTime").val("");
	$("#_isfault").val("-1");
	
	
}

/**
 * 校验纯数字校验
 */
function checkNum(_id) {
	var reg = new RegExp("^[0-9]*$");
	var obj = $("#" + _id).val();
	var len = obj.length;
	for ( var i = 0; i < len; i++) {
		if (reg.test(obj)) {// 判断是否为纯数字
			$("#" + _id).val(obj);
			return false;
		} else {
			if (i == len - 1) {
				$("#" + _id).val("");
			} else {
				obj = obj.substring(0, obj.length - 1);// 截取字符串
			}
		}
	}
}


function addorderinfo(orderid){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/savePssShop.do',
	    dataType : 'json',
	    data : {
	    	orderid:orderid
	    },
	    success : function(data) {
	    	if (data) {
	    		if (data.status != "error") {
	    			var index=layer.alert($.i18n.prop('addsuccess'),{title:$.i18n.prop('title')}, function(){
	    				layer.closeAll();
	    				 $.ajax({
	    						type : 'POST',
	    					    url : ctx + '/queryshopcarcontol.do',
	    					    success : function(data) {
	    					     	$(".gwc_sl").text(data.carcontol);
	    					    },
	    					    error:function(){
	    					    }
	    					});
	    			});
				} else {
					$.each(data.data, function(i, item) {   
	              		seterror(item.key, item.value);
	            	});
				}
	    	}
	 	},
	    error : function(data) {
	    	
	    }
	    	
	});    
	
};



	$('#coverage').click(function() {
		var orderid="";
			$("input:checkbox[name='checkbox']:checked").each(function(){ 
				orderid = orderid + $(this).val() + ",";
			});
		if(orderid==""){
			alert($.i18n.prop('chooseonedateatleast'));
			return false;
		}if(orderid.length>0){
			orderid = orderid.substring(0, orderid.length-1);//截取字符串
			window.open(ctx+"/coverageOrderInfo.do?orderid="+orderid);
		}
			
	});
//支付信息填写
function payinfo(orderMainid){
	window.open(ctx+"/orderpayinfo.do?orderMainid="+orderMainid);
	
}
//支付信息查看
function showpayinfo(orderMainid){
	window.open(ctx+"/showorderpayinfo.do?orderMainid="+orderMainid);
}
//订单取消
function cacleOrder(orderid,orderstate){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/cancleOrder.do',
	    dataType : 'json',
	    data : {
	    	orderid:orderid,
	    	orderstate:orderstate
	    },
	    success : function(data) {
	    	if (data) {
	    		if (data.status != "error") {
	    			var index=layer.alert('保存成功',{title:$.i18n.prop('title')}, function(){
	    				layer.closeAll();
	    				window.location.href=ctx+"/orderMain.do";
	    			});
				} else {
					$.each(data.data, function(i, item) {   
	              		seterror(item.key, item.value);
	            	});
				}
	    	}
	 	},
	    error : function(data) {
	    	
	    }
	    	
	});    
}

$("#orderSortby").click(function() {
	$("#pageNumInput").val(1);
	//replaceSpecialInput();
	formSubmit();
});
//查看退款信息
function showrefundinfo(orderMainid){
	window.open(ctx+"/showRefundInfo.do?orderMainid="+orderMainid);
}