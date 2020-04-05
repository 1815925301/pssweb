/**
 * 生成订单
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;

$(document).ready(function() { 
	changeLang_js();
});

/**
 * 验证配置名称的校验
 */
$('#orderName').live('blur',function() {
	 var flag = checkOrderName($(this).val());//名称校验规则为中文、字母、数字长度为1至15
	 if(!flag){
		 $("#input_Name").show();
	 }else{
		 $("#input_Name").hide();
	 }
});

//点击编辑
function updateCollectInfo(id){ }

//点击查看
function showShopCar(id){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/getPssShopCar.do',
	    dataType : 'json',
	    data : {
	    	orderid:id
	    },
	    success : function(data) {
	    	console.log(data);
	    	$('#show_sceneid').html(data.PssShopCar.sceneid);
	    	$('#show_ordertype').html(data.PssShopCar.ordertype);
	    	$('#show_productlevel').html(data.PssShopCar.productlevel);
	    	$('#show_productid').html(data.PssShopCar.productid);
	    	
	    	$('#show_satelliteid').html(data.PssShopCar.satelliteid);
	    	$('#show_sensorid').html(data.PssShopCar.sensorid);
	    	$('#show_producttype').html(data.PssShopCar.producttype);
	    	$('#show_area').html(data.PssShopCar.area);
	    	$('#show_price').html(data.PssShopCar.price);
	    	showIndex=layer.open({
				title:$.i18n.prop('viewdetil'),
			    type: 1,
			    area: ['480px', '477px'], //宽高
			    content: $('#showShopCarWin').html()
			});
	    },
	    error:function(){
	    }
	});
};
function hidenShopCar(){
		layer.close(showIndex);
}
function hidenUpdateShopCar(){
	layer.close(editIndex);
}

//保存修改
function saveChange(){
	$('.layui-layer-content').find("input[id='orderid']").blur();
	$('.layui-layer-content').find("input[id='sceneid']").blur();
	$('.layui-layer-content').find("input[id='ordertype']").blur();
	$('.layui-layer-content').find("input[id='productlevel']").blur();
	$('.layui-layer-content').find("input[id='productid']").blur();
	$('.layui-layer-content').find("input[id='satelliteid']").blur();
	$('.layui-layer-content').find("input[id='sensorid']").blur();
	$('.layui-layer-content').find("input[id='producttype']").blur();
	$('.layui-layer-content').find("input[id='area']").blur();
	$('.layui-layer-content').find("input[id='price']").blur();
//	checkOrgName();
//	var is_error = false;
//	var _error = ["userName", "trueName", "password", "surepassword", "orgId"];
//	$.each(_error, function(key, val) {
//		var objId = $('#' + val).attr("id");
//		var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
//    	var error_flag = $(Objects).parent().parent().attr('class');
//        if (error_flag != undefined)
//        	if (error_flag.indexOf("error") >= 0) {
//            	is_error = true;
//            	return false;
//            }
//    });
//    if (!is_error) {
    	var data =$('.layui-layer-content').find("form[id='changeForm']").serialize();
    	jQuery.ajax({
			type : 'POST',
		    url : ctx + '/updatepssShopCar.do',
		    dataType : 'json',
		    data : data,
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			var index=layer.alert($.i18n.prop('savesuccess'),{title:$.i18n.prop('title')}, function(){
		    				layer.closeAll();
		    				location.href=ctx +"/pssshopcar.do";
		    				//$("#searchAll").click();
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
//    }
};

/**
 * 添加订单
 */
function addOrder(){
	var price=$("#price").val();//价格
	var orderIds = $("#orderIds").val();//子订单id
	var orderName = $("#orderName").val();//主订单名称
	var remark = $("#remark").val();//主订单备注
	var flag = checkOrderName(orderName);//名称校验规则为中文、字母、数字长度为1至15
	if(!flag){
		$("#input_Name").show();
		return false;
	}else{
		$("#input_Name").hide();
	}
	
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/addOrder.do',
	    dataType : 'json',
	    data : {
	    	orderIds : orderIds,
	    	orderName : orderName,
	    	price:price,
	    	remark : remark
	    },
	    success : function(data) {
	    	
	    	if (data) {
	    		if (data.status != "error") {
	    			var index=layer.alert($.i18n.prop('savesuccess'),{title:$.i18n.prop('title')}, function(){
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
	    error : function(data) {}
	});
	
	
}
//订购order
function order(as){
	
	var chk_value =[]; 
	$('input[name="sonChecklist"]:checked').each(function(){ 
	chk_value.push($(this).val()); 
	}); 
	if(chk_value.length==0){
		alert($.i18n.prop('nochoosecontent')); 
	}
//	alert(chk_value);
	
	
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/orderCreate.do',
	    dataType : 'json',
	    success : function(data) {
	    	
//	    	if (data) {
//	    		if (data.status != "error") {
//	    			var index=layer.alert('保存成功！',{icon: 3, title:'提示'}, function(){
//	    				layer.closeAll();
//	    				location.href=ctx +"/pssshopcar.do";
//	    				//$("#searchAll").click();
//	    			});
//				} else {
//					$.each(data.data, function(i, item) {   
//	              		seterror(item.key, item.value);
//	            	});
//				}
//	    	}
	 	},
	    error : function(data) {}
	});
}

	//==========================================
	///数组删除操作扩写 @yinli
	//==========================================
	Array.prototype.indexOf = function(val) {
	    for (var i = 0; i < this.length; i++) {
	        if (this[i] == val) return i;
	    }
	    return -1;
	};
	Array.prototype.remove = function(val) {
	    var index = this.indexOf(val);
	    if (index > -1) {
	        this.splice(index, 1);
	    }
	};

	//==========================================
	//单选，全选 @yinli
	//==========================================
	var sc= new Array();//定义一个勾选数组
	//全选  
	function checkAll(){    
	    if ($("#chkAll:checked").length > 0){  
	        $("[name=sonChecklist]").prop("checked", true);  
	        $("[name=sonChecklist]").each(function(){  
	            var id=$(this).attr("id");  
	            chkSonClick(id);  
	        });   
	    }else{  
	        $("[name=sonChecklist]").prop("checked", false);
	        $("[name=sonChecklist]").each(function(){  
	            var id=$(this).attr("id");  
	            chkSonClick(id);  
	        });
	   }  
	     
	}  
	// 单选  
	function chkSonClick(id){   
	    var check = document.getElementById(id); 
	    if(check.checked){
	      sc.push(id);
	    }else{
	      sc.remove(id); 
	    }
	    document.getElementById("idlist").value=sc; 
	}
