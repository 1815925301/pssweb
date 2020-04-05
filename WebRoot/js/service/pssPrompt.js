/**
 * 采集单管理
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;

$(document).ready(function() {

	changeLang_js();

	//点击新增用户
	$('#addNewPrompt').click(function() {
		$('.layui-layer-content').find("input[id='key_add']").val(""); 
		$('.layui-layer-content').find("input[id='value_add']").val(""); 
		$('.layui-layer-content').find("input[id='des_add']").val(""); 
		$('.layui-layer-content').find("input[id='createtime_add']").val(""); 
		newIndex=layer.open({
			title:$.i18n.prop('addinformation'),
		    type: 1,
		    //skin: 'layui-layer-rim', //加上边框
		    area: ['600px', '500px'], //宽高
		    content: $('#addNewPromptWin').html()
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
	    
});

//保存新增
function savePrompt(){
    	var data =$('.layui-layer-content').find("form[id='addPromptForm']").serialize();
    	jQuery.ajax({
			type : 'POST',
		    url : ctx + '/savePssPrompt.do',
		    dataType : 'json',
		    data : data,
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			var index=layer.alert($.i18n.prop('addsuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
		    				layer.closeAll();
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

//点击编辑修改
function updatePrompt(id){
	layer.msg($.i18n.prop('comeon'),{time:500});
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/showPromptbyid.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#change_id').attr('value',data.pssPrompt.id);
	    	$('#change_key').attr('value',data.pssPrompt.key);
	    	$('#change_value').attr('value',data.pssPrompt.value);
	    	$('#change_des').attr('value',data.pssPrompt.des);
	    	$('#change_createtime').attr('value',data.pssPrompt.createtime);
	    	editIndex=layer.open({
				title:$.i18n.prop('modifyusertishi'),
			    type: 1,
			    area: ['600px', '500px'], //宽高
			    content: $('#changePromptWin').html()
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
  //点击查看
function showPrompt(id){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/showPromptbyid.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#show_id').html(data.pssPrompt.id);
	    	$('#show_key').html(data.pssPrompt.key);
	    	$('#show_value').html(data.pssPrompt.value);
	    	$('#show_des').html(data.pssPrompt.des);
	    	$('#show_createtime').html(data.pssPrompt.createtime);
	    	showIndex=layer.open({
				title:$.i18n.prop('viewinformation'),
			    type: 1,
			    area: ['480px', '450px'], //宽高
			    content: $('#showPromptWin').html()
			});
	    },
	    error:function(){
	    }
	});
};



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
	function saveChange(id){
		
		
        	var data =$('.layui-layer-content').find("form[id='changepaForm']").serialize();
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/changePrompt.do',
			    dataType : 'json',
			    data : data,
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			var index=layer.alert($.i18n.prop('savesuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
			    				layer.closeAll();
			    				$("#searchMatch").click();
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