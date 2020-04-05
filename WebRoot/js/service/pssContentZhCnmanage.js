/**
 * 学生管理
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;

$(document).ready(function(){
	$("#searchMatch").click(function(){
//		alert("进入搜索");
	});
	changeLang_js();
    
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
	//点击新增新闻
	$('#addContent').click(function() {
		$('.layui-layer-content').find("input[id='contentid_add']").val(""); 
		$('.layui-layer-content').find("input[id='contenttitle_add']").val(""); 
		$('.layui-layer-content').find("input[id='content_add']") .html("");
		$('.layui-layer-content').find("input[id='isrelease_add']").html("");
		$('.layui-layer-content').find("input[id='contenttype_add']").html("");
		$('.layui-layer-content').find("input[id='createtime_add']").html("");
		$('.layui-layer-content').find("input[id='image_add']").html("");
		newIndex=layer.open({
			title:$.i18n.prop('newaddnew'),
		    type: 1,
		    //skin: 'layui-layer-rim', //加上边框
		    area: ['600px', '500px'], //宽高
		    content: $('#addNewContent').html()
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


//修改
function updatecontent(id){
	layer.msg($.i18n.prop('comeon'),{time:500});
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/modifycontent.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#contentid_modify').attr('value',data.contents.contentid);
	    	$('#contenttitle_modify').attr('value',data.contents.contenttitle);
	    	$('#content_modify').attr('value',data.contents.content);
	    	$('#isrelease_modify').attr('value',data.contents.isrelease);
	    	$('#contenttype_modify').attr('value',data.contents.contenttype);
	    	$('#createtime_modify').attr('value',data.contents.createtime);
	    	$('#image_modify').attr('value',data.contents.image);
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
			    content: $('#changeContent').html()
			});
	    },
	    error:function(){
	    	setTimeout(function(){
	    		layer.alert($.i18n.prop('tryagain'), {
	    		    icon: 1,
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} ,500);
	    }
	});			
}
  //点击查看
function showcontent(id){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/selectcontentbyid.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#content_title').html(data.content.contenttitle);
	        $('#content_content').html(data.content.content);
	    	$('#isrelease').html(data.content.isrelease);
	    	$('#contenttype').html(data.content.contenttype);
	    	$('#create_time').html(data.content.createtime);
	    	$('#image_type').html(data.content.image);
//	    	alert(data.systemconfig.configid);
//	    	alert(data.systemconfig.configkey);
//	    	alert(data.systemconfig.configdes);
//	    	alert(data.systemconfig.createtime);
//	    	alert(data.systemconfig.configtype);
	    	showIndex=layer.open({
				title:$.i18n.prop('viewuser'),
			    type: 1,
			    area: ['880px', '450px'], //宽高
			    content: $('#showcontent').html()
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
	function removecontent(id,uname){
		 var delIndex=layer.confirm($.i18n.prop('confirmdelete')+uname+'？', {
			    btn: [$.i18n.prop('yes'),$.i18n.prop('cancle')] //按钮
			}, function(){
				layer.msg($.i18n.prop('deleting'),{time:500});
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/deletecontentbyid.do',
				    dataType : 'json',
				    data : {
				    	dataId:id
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
//保存新增
	function saveContent(){
		$('.layui-layer-content').find("input[id='contentid_add']").blur();
		$('.layui-layer-content').find("input[id='contenttitle_add']").blur();
		$('.layui-layer-content').find("input[id='content_add']").blur();
		$('.layui-layer-content').find("input[id='isrelease_add']").blur();
		$('.layui-layer-content').find("input[id='contenttype_add']").blur();
		$('.layui-layer-content').find("input[id='createtime_add']").blur();
		$('.layui-layer-content').find("input[id='image_add']").blur();
//		
		var is_error = false;
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
        	var data =$('.layui-layer-content').find("form[id='addContentForm']").serialize();
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/saveNewContent.do',
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
	
	//保存修改
	function saveChangecontent(){
		
		$('.layui-layer-content').find("input[id='contentid_modify']").blur();
		$('.layui-layer-content').find("input[id='contenttitle_modify']").blur();
		$('.layui-layer-content').find("input[id='content_modify']").blur();
		$('.layui-layer-content').find("input[id='isrelease_modify']").blur();
		$('.layui-layer-content').find("input[id='contenttype_modify']").blur();
		$('.layui-layer-content').find("input[id='createtime_modify']").blur();
		$('.layui-layer-content').find("input[id='image_modify']").blur();
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
//        if (!is_error) {
        	var data =$('.layui-layer-content').find("form[id='changeFormcontent']").serialize();
        	
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/saveContentConfirm.do',
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
//        }
	};