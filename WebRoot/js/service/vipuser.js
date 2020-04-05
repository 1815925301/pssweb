/**
 * VIP用户管理
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;


$(document).ready(function() {
	changeLang_js();
	$("#searchAll").click(function() {
		alert();
		$("#pageNumInput").val(1);
		clearSearchInput();
		$('#searchForm').submit(); 
	});
	
	//新增VIP用户
	$('#addvipuser').click(function() {

		$('.layui-layer-content').find("input[id='transtype_add']").val("");
		$('.layui-layer-content').find("input[id='company_add']").val(""); 
		$('.layui-layer-content').find("input[id='ipaddress_add']").val("");
		$('.layui-layer-content').find("input[id='cusername_add']").val("");
		$('.layui-layer-content').find("input[id='port_add']").val(""); 
		$('.layui-layer-content').find("input[id='cpassword_add']").val("");
		
		$('.layui-layer-content').find("select[id='path1']").val("");
		$('.layui-layer-content').find("select[id='path2']").val("");
		$('.layui-layer-content').find("select[id='path3']").val("");
		$('.layui-layer-content').find("select[id='path4']").val("");
		$('.layui-layer-content').find("select[id='path5']").val("");
		
		newIndex=layer.open({
			title:$.i18n.prop('add'),
		    type: 1,
		    //skin: 'layui-layer-rim', //加上边框
		    area: ['700px', '500px'], //宽高
		    content: $('#addNewvipuser').html()
		   
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

	//删除VIP管理用户
	function deleteVipuser(id,company){
		 var delIndex=layer.confirm($.i18n.prop('confirmdelete')+company+'？', {
			    btn: [$.i18n.prop('yes'),$.i18n.prop('cancle')] //按钮
			}, function(){
				layer.msg($.i18n.prop('deleting'),{time:500});
				jQuery.ajax({
					type : 'POST',
				    url : ctx + '/deleteVipuser.do',
				    dataType : 'json',
				    data : {
				    	id:id
				    },
				    success : function(data) {
				    		layer.alert(data.data, {
				    		    skin: 'layer-ext-moon' 
				    		});
				    		$("#searchAll").click();
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
//保存新增VIP管理用户
	function savevipuser(){
        	var data =$('.layui-layer-content').find("form[id='addNewvipuser']").serialize();
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/saveNewvipuser.do',
			    dataType : 'json',
			    data : data,
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			var index=layer.alert($.i18n.prop('addsuccess'),{title:$.i18n.prop('title')}, function(){
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

	