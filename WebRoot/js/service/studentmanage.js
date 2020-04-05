/**
 * 学生管理
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;

$(document).ready(function() {
	//点击新增学生
	$('#addNewUser').click(function() {
		$('.layui-layer-content').find("input[id='userName']").val(""); 
		$('.layui-layer-content').find("input[id='trueName']").val(""); 
		$('.layui-layer-content').find("input[id='userName_error']") .html("");
		$('.layui-layer-content').find("input[id='trueName_error']").html("");
		
		newIndex=layer.open({
			title:'新增学生',
		    type: 1,
		    //skin: 'layui-layer-rim', //加上边框
		    area: ['600px', '500px'], //宽高
		    content: $('#addNewUserWin').html()
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


//点击编辑
function updateUser(id){
	layer.msg('玩命加载中',{time:500});
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/showUsers.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#change_id').attr('value',data.userInfo.id);
	    	$('#change_userName').attr('value',data.userInfo.userName);
	    	$('#change_trueName').attr('value',data.userInfo.trueName);
	    	$('#change_userEmail').attr('value',data.userInfo.userEmail);
	    	$('#change_orgId').find("option[text='"+data.userInfo.orgName+"']").attr("selected",true);
	    	$('#change_lockStr').find("option[text='"+data.userInfo.lockStr+"']").attr("selected",true);
	    	if(data.userInfo.roleName!=null){
	    		$("#change_roleId option[text='"+data.userInfo.roleName+"']").attr("selected", true);  
	    	}else{
	    		 $("#change_roleId").append("<option value='-1' selected>请选择角色</option>");  
	    	}
	    	editIndex=layer.open({
				title:'修改用户信息',
			    type: 1,
			    //skin: 'layui-layer-rim', //加上边框
			    area: ['600px', '500px'], //宽高
			    content: $('#changeUserWin').html()
			});
	    },
	    error:function(){
	    	setTimeout(function(){
	    		layer.alert('加载错误，请重试！', {
	    		    icon: 1,
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} ,500);
	    }
	});			
}
  //点击查看
function showUser(id){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/showUsers.do',
	    dataType : 'json',
	    data : {
	    	id:id
	    },
	    success : function(data) {
	    	$('#show_userName').html(data.userInfo.userName);
	    	$('#show_trueName').html(data.userInfo.trueName);
	    	$('#show_userEmail').html(data.userInfo.userEmail);
	    	$('#show_orgName').html(data.userInfo.orgName);
	    	$('#show_lockStr').html(data.userInfo.lockStr);
	    	$('#show_roleName').html(data.userInfo.roleName);
	    	showIndex=layer.open({
				title:'查看用户',
			    type: 1,
			    area: ['480px', '450px'], //宽高
			    content: $('#showUserWin').html()
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
	function removeUser(id,uname){
		 var delIndex=layer.confirm('确认要删除 '+uname+'？', {
			    btn: ['确认','取消'] //按钮
			}, function(){
				layer.msg('正在删除',{time:500});
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/removeUser.do',
				    dataType : 'json',
				    data : {
				    	userId:id
				    },
				    success : function(data) {
				    		layer.alert(data.data, {
				    		    icon: 1,
				    		    skin: 'layer-ext-moon' 
				    		});
				    		$("#searchAll").click();
				    },
				    error:function(){
				    		layer.alert('删除失败！', {
				    		    icon: 1,
				    		    skin: 'layer-ext-moon' 
				    		});
				    	} 
				});
			}, function(){
			    layer.close(delIndex);
			});
	};
//保存
	function saveUser(){
		$('.layui-layer-content').find("input[id='userName']").blur();
		$('.layui-layer-content').find("input[id='trueName']").blur();
		var is_error = false;
		var _error = ["userName", "trueName"];
		$.each(_error, function(key, val) {
			var objId = $('#' + val).attr("id");
			var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
        	var error_flag = $(Objects).parent().parent().attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("error") >= 0) {
                	is_error = true;
                	return false;
                }
        });
        if (!is_error) {
        	var data =$('.layui-layer-content').find("form[id='addForm']").serialize();
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/saveNewUser.do',
			    dataType : 'json',
			    data : data,
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			var index=layer.alert('添加成功！',{icon: 3, title:'提示'}, function(){
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
        }
	};
	
	//保存修改
	function saveChange(){
		$('.layui-layer-content').find("input[id='userName']").blur();
		$('.layui-layer-content').find("input[id='trueName']").blur();
		$('.layui-layer-content').find("input[id='password']").blur();
		$('.layui-layer-content').find("input[id='surepassword']").blur();
		checkOrgName();
		var is_error = false;
		var _error = ["userName", "trueName", "password", "surepassword", "orgId"];
		$.each(_error, function(key, val) {
			var objId = $('#' + val).attr("id");
			var Objects=$('.layui-layer-content').find("input[id='"+objId+"']");
        	var error_flag = $(Objects).parent().parent().attr('class');
            if (error_flag != undefined)
            	if (error_flag.indexOf("error") >= 0) {
                	is_error = true;
                	return false;
                }
        });
        if (!is_error) {
        	var data =$('.layui-layer-content').find("form[id='changeForm']").serialize();
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/changeUser.do',
			    dataType : 'json',
			    data : data,
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			var index=layer.alert('保存成功！',{icon: 3, title:'提示'}, function(){
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
        }
	};