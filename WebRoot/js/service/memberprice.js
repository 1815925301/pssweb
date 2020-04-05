/**
 * VIP用户管理
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;

//点击搜索模块的"查看全部"按钮后，消除所有的搜索条件并将分页页码置为1
$("#searchAll").click(function() {
	$("#pageNumInput").val(1);
	clearSearchInput();
	formSubmit();
});

$(document).ready(function() {
	changeLang_js();

	//新增VIP用户
	$('#addMemberprice').click(function() {


		
		newIndex=layer.open({
			title:$.i18n.prop('newadd'),
		    type: 1,
		    //skin: 'layui-layer-rim', //加上边框
		    area: ['700px', '500px'], //宽高
		    content: $('#showMemberprice').html()
		   
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

	function Canclesave(){
		layer.close(newIndex);
	}
	
	//保存新增会员价格
	function savememberprice(){
		var data =$('.layui-layer-content').find("form[id='addpriceForm']").serialize();
    	jQuery.ajax({
			type : 'POST',
		    url : ctx + '/savememberprice.do',
		    dataType : 'json',
		    data : data,
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			var index=layer.alert($.i18n.prop('modifysuccess'),{title:$.i18n.prop('title')}, function(){
		    				layer.closeAll();
		    				window.location.href=ctx+"/memberprice.do";
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
    //保存修改会员价格
	function updatememberprice(){
        	var data =$('.layui-layer-content').find("form[id='memeberpriceForm']").serialize();
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/updatememberprice.do',
			    dataType : 'json',
			    data : data,
			    success : function(data) {
			    	if (data) {
			    		if (data.status != "error") {
			    			var index=layer.alert($.i18n.prop('modifysuccess'),{title:$.i18n.prop('title')}, function(){
			    				layer.closeAll();
			    				window.location.href=ctx+"/memberprice.do";
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
	//修改
	var pthy=$("#Ordinary_member").val();
	var byhy=$("#Silver_member").val();
	var hjhy=$("#Gold_member").val();
	var zshy=$("#Diamond_member").val();
	var viphy=$("#VIP_member").val();
	function updatemember(id){
		layer.msg($.i18n.prop('comeon'),{time:500});
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/selectmemberprice.do',
		    dataType : 'json',
		    data : {
		    	id:id
		    },
		    success : function(data) {
		    	$('#member_id').attr('value',data.memberprice.id);
		    	if(data.memberprice.membertype==1){
		    		$('#membertype_id').attr('value',pthy);
		    	}
		    	if(data.memberprice.membertype==2){
		    		$('#membertype_id').attr('value',byhy);
		    	}
		    	if(data.memberprice.membertype==3){
		    		$('#membertype_id').attr('value',hjhy);
		    	}
		    	if(data.memberprice.membertype==4){
		    		$('#membertype_id').attr('value',zshy);
		    	}
		    	if(data.memberprice.membertype==5){
		    		$('#membertype_id').attr('value',pviphy);
		    	}
		    	
		    	
		    	$('#onemonth_id').attr('value',data.memberprice.onemonth);
		    	$('#treemonths_id').attr('value',data.memberprice.treemonths);
		    	$('#sixmonths_id').attr('value',data.memberprice.sixmonths);
		    	$('#year_id').attr('value',data.memberprice.year);
		    	

		    	editIndex=layer.open({
					title:$.i18n.prop('modifyvip'),
				    type: 1,
				    //skin: 'layui-layer-rim', //加上边框
				    area: ['600px', '500px'], //宽高
				    content: $('#changememberprice').html()
				});
		    },
		    error:function(){
		    	setTimeout(function(){
		    		layer.alert($.i18n.prop('tryagain'), {
		    	
		    		    skin: 'layer-ext-moon' 
		    		});
		    	} ,500);
		    }
		});			
	}	
	
function Cancle(){
	layer.close(editIndex);
}