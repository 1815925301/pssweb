/**
 * 采集单管理
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;

$(document).ready(function() { 
	
	changeLang_js();
});
var scquery= new Array();
//定义数组
var arr = new Array();
 
 
//批量删除
function batchDeletion(){
	if(sc.length==0){
		alert($.i18n.prop('choosedate'));
	}else{
	var ids=document.getElementById("idlist").value;
	 if(ids.length>17){
		arr = ids.split(",");
		 for(var i = 0;i<arr.length;i++){
			 var idslist = arr[i].split("_")[1];
			 scquery.push(idslist);
		 }
		 document.getElementById("idnodate").value=scquery;
	 }else{
		 var idss =ids.split("_")[1];
		 scquery.push(idss);
		 document.getElementById("idnodate").value=scquery;
	 }
	 var idss=document.getElementById("idnodate").value;
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/deletemoreShopCar.do',
	    dataType : 'json',
	    data : {
	    	"shopid":idss
	    },
	    success : function(data) {
	    	$('#searchForm').submit(); 
	 	},
	});
	  
   }
}

/**
 * 下载excle
 * @author Dylan
 * @date 16-09-01 15:56:30
 */
function orderExcel(){
	var sonOrderIds = $("#idlist").val(); 
	if(sonOrderIds == ""){
		var index=layer.alert($.i18n.prop('youhavenoexportorderf'),{title:$.i18n.prop('title')}, function(){
			layer.closeAll();
		});
		return false
	}
	window.location.href=ctx + "/exporXls.do?flag=2&sonOrderIds="+sonOrderIds;
}

/*
 * 购物车覆盖显示
 */
function shopCarCoverage(){
	var orderids=$("#idlist").val(); 
	if(orderids==""){
		alert($.i18n.prop('chooseonedateatleast'));
		return false;
	}
	var orderisd = orderids.split("_")[1];
	window.location.href=ctx+"/shopCarCoverage.do?orderid="+orderisd;
}


//点击编辑
function updateCollectInfo(id){ }

//点击查看
function showShopCar(orderid){
	
	window.location.href=ctx+"/getPssShopCarinfo.do?orderid="+orderid;
	   
	   
};
function hidenShopCar(){
		layer.close(showIndex);
}
function hidenUpdateShopCar(){
	layer.close(editIndex);
}

//点击编辑修改
function updateShopCar(orderid){
	layer.msg($.i18n.prop('comeon'),{time:500});
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/showPssshopcarbyid.do',
	    dataType : 'json',
	    data : {
	    	orderid:orderid
	    },
	    success : function(data) {
	    	console.log(data);
	    	jQuery('#show_orderid').attr('value',data.PssShopCar.orderid);
	    	jQuery('#update_sceneid').attr('value',data.PssShopCar.sceneid);
	    	jQuery('#update_ordertype').attr('value',data.PssShopCar.ordertype);
	    	jQuery('#update_productlevel').attr('value',data.PssShopCar.productlevel);
	    	jQuery('#update_productid').attr('value',data.PssShopCar.productid);
	    	jQuery('#update_satelliteid').attr('value',data.PssShopCar.satelliteid);
	    	jQuery('#update_sensorid').attr('value',data.PssShopCar.sensorid);
	    	jQuery('#update_producttype').attr('value',data.PssShopCar.producttype);
	    	jQuery('#update_area').attr('value',data.PssShopCar.area);
	    	jQuery('#update_price').attr('value',data.PssShopCar.price);
	    	editIndex=layer.open({
				title:$.i18n.prop('modityinformation'),
			    type: 1,
			    //skin: 'layui-layer-rim', //加上边框
			    area: ['600px', '500px'], //宽高
			    content: $('#showUpdateShopCarWin').html()
			});
	    },
	    error:function(){
	    	setTimeout(function(){
	    		layer.alert($.i18n.prop('loadingwrong'), {
	    		  
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} ,500);
	    }
	});			
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

document.getElementById("idlist").value=sc;



var allprice;
/**
 * 订购order
 */
function order(){
	var orderIds = ""; 
	$('input[name="sonChecklist"]:checked').each(function(){ 
		orderIds = orderIds + $(this).val() + ","; 
	});
	var len=$('input[name="sonChecklist"]:checked').length;
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/getOrderCount.do',
	   
	  
	    success : function(data) {
	    	if(len>data.souns){
	    		alert("您今天还能下"+data.souns+"单,请重新选择");
	    		return;
	    	}
	    	else{
	    		
	    	
	    	
	    	var ids=document.getElementById("idlist").value;
	    	arr = ids.split(",");
	    	 for(var i = 0;i<arr.length;i++){
	    		 var idslist = arr[i].split("_")[1];
	    		 scquery.push(idslist);
	    	 }
	    	document.getElementById("idnodate").value=scquery;
	    	 var idss=document.getElementById("idnodate").value;
	    	
	    	
	    	if(idss== ""){
	    		var index=layer.alert($.i18n.prop('choosesonorder'),{title:$.i18n.prop('title')}, function(){
	    			layer.closeAll();
	    		});
	    		return false;
	    	}
	    	if(idss.length>0){
	    		orderIds = orderIds.substring(0, orderIds.length-1);//截取字符串
	    	}
	    	var price = $("#pricecounts").text();
	    	
	    	window.location.href=ctx + "/orderCreate.do?orderIds="+idss+"&&allprices="+price;
	    	  }	
	    },
	  
	    error:function(){
	    		
	    	} 
	});
	
}

//删除购物车数据
function removeShopCar(id){
	
		if(id==null){
			alert("");
			return;
		}
		
		 var delIndex=layer.confirm($.i18n.prop('confirmdelete'), {
			    btn: [$.i18n.prop('yes'),$.i18n.prop('cancle')] //按钮
			}, function(){
				layer.msg($.i18n.prop('deleting'),{time:500});
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/removeShopCar.do',
				    dataType : 'json',
				    data : {
				    	orderid:id
				    },
				    success : function(data) {
				    		layer.alert(data.data, {
				    		   
				    		    skin: 'layer-ext-moon' 
				    		});
				    		location.href=ctx +"/pssshopcar.do";
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
	    	sc.splice(0,sc.length);
//	        $("[name=sonChecklist]").prop("checked", true);  
	        $("[name=sonChecklist]").each(function(){
	            var id=$(this).attr("id"); 
//	            var keys = -18;
//	            chkSonClick(id,keys);
	           // sc.remove(id);
	            $("[name=checkboxdate]").attr("checked",true);
	            var idss = id.split("_")[0];
	           // alert(idss);
	            chkdateClick(idss);
	            
	        });   
	    }else{  
	        $("[name=sonChecklist]").prop("checked", false);
	        $("[name=sonChecklist]").each(function(){  
	            var id=$(this).attr("id"); 
	            var keys = -18;
	            var veuel=-15;
//	            chkSonClick(id,keys,veuel);  
	            chkworongClick(id);
	            $("[name=checkboxdate]").attr("checked",false);
	        });
	   }  
	     
	}  
	
	/***
	 * 点击日期勾选日期下的子订单
	 */
	var priceSum=0;
	 priceall = parseInt(priceSum);
	function chkdateClick(strs){
		
		//alert(strs);
		var chexboxtime =document.getElementById(strs);
		if(chexboxtime.checked){
			priceall=0;
		$("input[id^='"+strs+"_']").each(function(index, element) {
			 sc.remove(element.id);
			$("#"+element.id).attr("checked",true);
//			var ids=document.getElementById("idlist").value;
		chkworongClick(element.id);
		});
		}else{
			$("input[id^='"+strs+"_']").each(function(index, element) {
				var ids=document.getElementById("idlist").value;
				sc.remove(element.id);
				$("#"+element.id).attr("checked",false);
				chkworongClick(element.id);
			});
		}
	}
	var priceList=new Array();
	// 单选  
	function chkworongClick(id){ 
		    var check = document.getElementById(id);
		   var privce =  $("#aloneprive_"+id.split("_")[1]).text();
		   var uuits=$("#uuits").text();
		   
		   if(("()")==(privce)){
			   privces=0;
		   }else{
			   privces = parseInt(privce);
		   }	
		   //转化为int类型
		   console.log(check);
		    if(check.checked){
		    	sc.push(id);
		    	priceList.remove(id+"_"+privces);
		    	priceList.push(id+"_"+privces);
		    	priceall+=privces;
		      console.log(priceList+$.i18n.prop('radioin'));
		    }else{
		    	priceList.remove(id+"_"+privces);
		    	var idss=5;
		         sc.remove(id);
		      //  priceall-=privces;
		    	//$("#pricecounts").text(priceall);
		    }
		    if(sc.length>0){
		    	priceall=0;
		    	for ( var i = 0; i < priceList.length; i++) {
		    		var str=priceList[i].split("_")[2];
			    	priceall+=parseInt(str);
				}
		    	$("#pricecounts").text(priceall+uuits);
				$("#Itemcounts").text(sc.length);
		    }else{
		    	$("#pricecounts").text("0");
		    	$("#Itemcounts").text("0");
		    }
	    document.getElementById("idlist").value=sc;
	    
	}
	
	
	
	
	
	
	
	
	 /***
     * 往清空购物车后台传id
     */
    function clickAllIdall(){
    	var orderIds = "1";
//	            $("[name=sonChecklist]").prop("checked", true);  
//		        $("[name=sonChecklist]").each(function(){  
//		            var id=$(this).attr("id");  
//		            var ids = id.split("_")[1];
//		            orderIds = orderIds + ids + ","; 
//		        });   
	        	
	        jQuery.ajax({
				type : 'POST',
			    url : ctx + '/removeAllShopCar.do',
			    dataType : 'json',
			    data : {
			    	sc:orderIds,
			    },
			    success : function(data) {
			    	$('#searchForm').submit(); 
			 	},
	    	});
	} 
   
