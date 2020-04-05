/**
 * 采集单管理
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;

$(document).ready(function() {
	changeLang_js();
	//点击新增采集信息
	$('#addNewCollectInfo').click(function() {
		
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/pssCollectInfomanageAdd.do',
		    dataType : 'json',
		    data : data,
		    success : function(data) {
		    	
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
		
	});
	//取消
	$('#cancleAdd').live('click',function(){
		layer.close(newIndex);
	});
	$('#showcancleAdd').live('click',function(){
		layer.close(showIndex);
	});
	$('#changecancleAdd').live('click',function(){
		layer.close(editIndex);
	});
		
		//-------------------------------------------------
	//卫星验证
	$('#satelliteid').live('blur',function() {
		checkSatelliteid(this);
    });
	$("#satelliteid").live('change',function() {
		checkSatelliteid(this);
    });
	    
    //传感器验证
	$('#sensorid').live('change',function() {
		checkSensorid(this);
    });
    $('#sensorid').live('blur',function() {
    	checkSensorid(this);
    });
	    
    //采集编号验证
    $('#taskid').live('change',function() {
    	checkTaskid(this);
    });
    $('#taskid').live('blur',function() {
    	checkTaskid(this);
    });
});


//点击编辑修改
function updateCollectInfo(id){
	layer.msg($.i18n.prop('comeon'),{time:500});
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/showCollectInfobyid.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#change_id').attr('value',data.pssCollectInfo.id);
	    	$('#change_satelliteid').attr('value',data.pssCollectInfo.satelliteid);
	    	$('#change_sensorid').attr('value',data.pssCollectInfo.sensorid);
	    	$('#change_taskid').attr('value',data.pssCollectInfo.taskid);
	    	$('#change_upperleftlong').attr('value',data.pssCollectInfo.upperleftlong);
	    	$('#change_upperleftlat').attr('value',data.pssCollectInfo.upperleftlat);
	    	$('#change_lowerrightlong').attr('value',data.pssCollectInfo.lowerrightlong);
	    	$('#change_lowerrightlat').attr('value',data.pssCollectInfo.lowerrightlat);
	    	editIndex=layer.open({
				title:$.i18n.prop('moditycollect'),
			    type: 1,
			    area: ['600px', '500px'], //宽高
			    content: $('#changeCollectInfoWin').html()
			});
	    },
	    error:function(){
	    	setTimeout(function(){
	    		layer.alert($.i18n.prop('loadingwrong'), {
	    		    icon: 1,
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} ,500);
	    }
	});			
}
 
//-------------------------------------------------
//Purpose: 点击搜索模块的"查看全部"按钮后，消除所有的搜索条件置为空，用户名称与用户类型

//==========================================
function clearSearchInput() {
	$("#_satelliteId").val("-1");
	$("#_sensorIdswhat").val("-1");
	$("#taskidSearch").val("");
	$("#statusSearch").val("");
	$("#createStarttime").val("");
	$("#createEndtime").val("");
	$("#beginStarttime").val("");
	$("#beginEndtime").val("");
	$("#endStarttime").val("");
	$("#endEndtime").val("");
}

function sensorsSelcectsvalue(_this){
	var senti = document.getElementById("_sensorIdswhat");
	console.log(senti+"???");
}

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
						htm = htm + "<option>" + data + "</option>";
					}
					$("#_sensorIdswhat").html(htm);
				} else {
					var index = layer.alert($.i18n.prop('getsensonfaile'), {
						icon : 3,
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


//==========================================
//Purpose: 过滤检索输入框中的特殊字符
//==========================================
function replaceSpecialInput() {
	$("#taskidSearch").val(replaceSpecialStr($("#taskidSearch").val()));
}

//==========================================
//Purpose: 验证卫星代号
//==========================================
function checkSatelliteid(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
      seterror(obj,$.i18n.prop('entercode'));
      return false;
  } else {
	  seterror(obj, "");
  }
}

//==========================================
//Purpose: 验证传感器标示
//==========================================
function checkSensorid(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
      seterror(obj, $.i18n.prop('entersensonbiaoshi'));
      return false;

  } else {
    	seterror(obj, "");
  }
}

//==========================================
//Purpose: 验证采集编号
//==========================================
function checkTaskid(obj) {
	var objId = $(obj).attr("id");
	if (!checkEmpty(obj)) {
        seterror(obj, $.i18n.prop('collectcode'));
        return false;
    } else {
    	seterror(obj, "");
    }
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
	
	//删除
	function removeCollectInfo(id,uname){
		 var delIndex=layer.confirm($.i18n.prop('confirmdelete')+id+'？', {
			    btn: [$.i18n.prop('yes'),$.i18n.prop('cancle')] //按钮
			}, function(){
				layer.msg($.i18n.prop('deleting'),{time:500});
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/removeCollectInfo.do',
				    dataType : 'json',
				    data : {
				    	id:id
				    },
				    success : function(data) {
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
	
	//保存修改
	function saveChange(){
		$('.layui-layer-content').find("input[id='id']").blur();
		$('.layui-layer-content').find("input[id='satelliteid']").blur();
		$('.layui-layer-content').find("input[id='sensorid']").blur();
		$('.layui-layer-content').find("input[id='taskid']").blur();
		$('.layui-layer-content').find("input[id='upperleftlong']").blur();
		$('.layui-layer-content').find("input[id='upperleftlat']").blur();
		$('.layui-layer-content').find("input[id='lowerrightlong']").blur();
		$('.layui-layer-content').find("input[id='lowerrightlat']").blur();
        	var data =$('.layui-layer-content').find("form[id='changeForm']").serialize();
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/changeCollectInfo.do',
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
function addcollectIspay(taskid){
	window.open(ctx+"/addcollectionIspay.do?taskid="+taskid);
}
function queryPayInfo(taskid){
	window.open(ctx+"/querypayInfo.do?taskid="+taskid);
}
//查看采集单退款信息
function showCollectrefund(taskid){
	window.location.href=ctx+"/showRefundInfo.do?collectid="+taskid;
}