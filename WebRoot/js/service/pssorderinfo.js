/**
 * 用户管理
 */
var newIndex,showIndex,editIndex,stateIndex;
$(document).ready(function() {
	//搜索按钮触发的事件
	$("#searchMatch").click(function() {
		$("#pageNumInput").val(1);
		//replaceSpecialInput();
		formSubmit();
		
	});
	changeLang_js();
	//点击搜索模块的"查看全部"按钮后，消除所有的搜索条件并将分页页码置为1
	$("#searchAll").click(function() {
		$("#pageNumInput").val(1);
		clearSearchInput();
		formSubmit();
	});
	function formSubmit() {
		$("#searchForm").submit(); 
	};
	//分页所用的JS逻辑
	$("input[id*='pageTurn']").click(function() {
		var inputId = $(this).attr("id");
		var value = inputId.replace("pageTurn","");
		$("#pageNumInput").val(value);
		formSubmit();
	});
	
	$('#checkorderState').click(function() {
		var id="";
		$("input:checkbox[name='subcheck']:checked").each(function() { // 遍历name=subcheck的多选框
	           id=id+$(this).val()+",";
	          
		});
		if(id.length<1){
			alert(
					$.i18n.prop('chooseauto'));
			return;
		}

		$("#order_id").val(id.substring(0, id.length-1)); 
		$("#orderID").html(id.substring(0, id.length-1)); 
		
		stateIndex=layer.open({
			title:
				$.i18n.prop('auto'),
		    type: 1,
		    area: ['600px', '500px'], //宽高
		    content: $('#orderStateWin').html()
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
//取消
function stateCancle(){
	layer.close(stateIndex);
}



  //点击查看
function showpssorderinfo(orderid){
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/showPssOrderInfo.do',
	    dataType : 'json',
	    data : {
	    	orderid:orderid
	    },
	    success : function(data) {
	    	
	    	if(data.orderInfo.tasktype==1){
	    		$('#order_tasktype').html(
	    				$.i18n.prop('productorder'));
	    	}else{
	    		$('#order_tasktype').html(
	    				$.i18n.prop('nopruduct'));
	    	}
	        $('#order_username').html(data.orderInfo.username);
	    	$('#order_satelliteid').html(data.orderInfo.satelliteid);
	    	$('#order_orderstate').html(data.orderInfo.orderstate);
	    	if(data.orderInfo.ispay==2){
	    		$('#order_ispay').html(
	    				$.i18n.prop('payed'));
	    	}else{
	    		$('#order_ispay').html(
	    				$.i18n.prop('nopay'));
	    	}
	    	$('#order_productlevel').html(data.orderInfo.productlevel);
	    	$('#order_tasktime').html(data.orderInfo.tasktime);
	    	if(data.orderInfo.checkstate==3){
	    		$('#order_checkstate').html(
	    				$.i18n.prop('autofaile'));
	    	}else if(data.orderInfo.checkstate==2){
	    		$('#order_checkstate').html(
	    				$.i18n.prop('autosuccess'));
	    	}else{
	    		$('#order_checkstate').html(
	    				$.i18n.prop('waitauto'));
	    	}
	    	
	    	showIndex=layer.open({
				title:
					$.i18n.prop('vieworder'),
			    type: 1,
			    area: ['880px', '450px'], //宽高
			    content: $('#showpssorderinfo').html()
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
	$("#orgNameSearch").val("-1");
	$("#orgStateSearch").val("-1");
	
	$("#sceneidInput").val("");
	$("#productlevelSearch").val("-1");
	$("#orderidInput").val("");
	$("#orgNameSearch").val("-1");
	$("#usernameInput").val("");
	$("#tasktypeSearch").val("-1");
}

	//审核订单
	function updateCheckState(checkstate){
		//获取表单的值
		var data =$('.layui-layer-content').find("form[id='orderForm']").serialize();
		data=data+"&checkstate="+checkstate;
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/updateOrderState.do',
		    dataType : 'json',
		    data : data,
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			var index=layer.alert(
		    					$.i18n.prop('savesuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
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

	  $(function() {
          $("#allcheck").click(function() {
               $('input[name="subcheck"]').attr("checked",this.checked); 
           });
           var $subcheck = $("input[name='subcheck']");
           $subBox.click(function(){
               $("#allcheck").attr("checked",$subBox.length == $("input[name='subcheck']:checked").length ? true : false);
           });
       });