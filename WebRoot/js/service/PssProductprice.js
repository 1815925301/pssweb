/**
 * 产品价格
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;



$(document).ready(function() {
	changeLang_js();

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
	$('#addNewProductprice').click(function() {
		window.location.href=ctx+"/addProductprice.do";
			
	});
	         
});
	function addInput(){
		
		   txt = $("<input type='text'><input type='text'><button type='button' onclick='addInput()'><i class='icon-ok'></i>+</button>");     // 创建的input对象
		    $("ul li:last").append(txt);                        // 将创建的input添加到相应的位置
		    $("ul li:last input").focus();                        // 获取新添加的input，使用focus()设置焦点

	}

    function addRow(table) {
    	
        var vals = ['<input type="text" id="a" />', '<input type="text" id="a" />', "<a href='###' onclick='addRow(table)'>"+$.i18n.prop('add')+"</a> <a href='###' onclick='del(table,this)'>"+$.i18n.prop('delete')+"</a>"];
       var tr = table.insertRow(table.tBodies[0].rows.length);
       alert(tr.insertCell());
        for (var i = 0; i <3; i++) {
            //var td = tr.insertCell();
           // td.innerHTML = vals[i];
        	$("#tr").append(vals[i]);
        }
    }
   function del(table, row) {
        var ind = row.parentElement.parentElement.rowIndex;
        table.tBodies[0].deleteRow(ind);
    }
   //审核弹窗
   function checkProductprice(versionnum){
	 
	   $("#versionnum_ve").html(versionnum);
	   $("#versionnum_id").val(versionnum);
		stateIndex=layer.open({
			title:$.i18n.prop('auto'),
		    type: 1,
		    area: ['600px', '500px'], //宽高
		    content: $('#checkProduct').html()
		});
   }
//审核产品价格版本
	function updateCheck(isaudit){
		//获取表单的值
		var data =$('.layui-layer-content').find("form[id='priceForm']").serialize();
		data=data+"&isaudit="+isaudit;
		jQuery.ajax({
			type : 'POST',
		    url : ctx + '/updateisaudit.do',
		    dataType : 'json',
		    data : data,
		    success : function(data) {
		    	if (data) {
		    		if (data.status != "error") {
		    			var index=layer.alert($.i18n.prop('optionsuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
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
//发布
function releaseprice(versionnum){
	layer.msg($.i18n.prop('comeon'),{time:500});
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/Productpriceupdate.do',
	    dataType : 'json',
	    data : {
	    	versionnum:versionnum
	    },
	    success : function(data) {
	    	if (data) {
	    		if (data.status != "error") {
	    			var index=layer.alert($.i18n.prop('fabusuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
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
	    error : function(data) {
	    	
	    }
	});			
}
//取消发布
function cancelrelease(versionnum){
	layer.msg($.i18n.prop('comeon'),{time:500});
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/cancelrelease.do',
	    dataType : 'json',
	    data : {
	    	versionnum:versionnum
	    },
	    success : function(data) {
	    	if (data) {
	    		if (data.status != "error") {
	    			var index=layer.alert($.i18n.prop('canclefabusuccess'),{icon: 3, title:$.i18n.prop('title')}, function(){
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
	    error : function(data) {
	    	
	    }
	});			
}

  //点击查看
function showProductprice(versionnum){
	var html='<table class="tableWin" border="1">'
		html=html+'<thead>'
		html=html+'<tr><td>'+$.i18n.prop('producttype')+'</td><td>'+$.i18n.prop('productlevel')+'</td><td>'+$.i18n.prop('mianji')+'</td><td>'+$.i18n.prop('price')+'</td></tr>'
		html=html+'</thead>'
		html=html+'<tbody id="ProductTable">'
		var str="";
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/selectproductprice.do',
	    dataType : 'json',
	    data : {
	    	versionnum:versionnum
	    },
	    success : function(data){
	    	
	    	if (data.productpriceList.length>0) {
	    		for ( var i = 0; i < data.productpriceList.length; i++) {
	    			if(data.productpriceList[i].productcate==1){
	    				/*html=html+'<tr><td>'+data.productpriceList[i].productcate+'</td><td>'*/
	    				html=html+'<tr><td>'+$.i18n.prop('dataproduct')+'</td>'
	    			}
	    			if(data.productpriceList[i].productcate==2){
	    				/*html=html+'<tr><td>'+data.productpriceList[i].productcate+'</td><td>'*/
	    				html=html+'<tr><td>'+$.i18n.prop('professionproduct')+'</td>'
	    			}
	    			html=html+'<td>'+data.productpriceList[i].productlevel+'</td>'
	    			html=html+'<td>'+data.productpriceList[i].productarea+'</td>'
	    			html=html+'<td>'+data.productpriceList[i].price+'</td></tr>'
	    			
    			
				}
	    		
	    	}
	    	html=html+'</tbody>'
			html=html+'</table>';
	    	//$('#showProduct').html(html);
	    	showIndex=layer.open({
	    		title:$.i18n.prop('viewproducetprice'),
	    	    type: 1,
	    	    area: ['880px', '450px'], //宽高
	    	    content: $('#showProduct').html(html)
	    	});
	    },
	    error:function(){
	    	html=html+'</tbody>'
			html=html+'</table>';
	    	showIndex=layer.open({
	    		title:$.i18n.prop('viewproducetprice'),
	    	    type: 1,
	    	    area: ['880px', '450px'], //宽高
	    	    content: $('#showProduct').html(html)
	    	});
	    }
		
	});
 
	
	    	
};
//-------------------------------------------------
//Purpose: 点击搜索模块的"查看全部"按钮后，消除所有的搜索条件置为空，用户名称与用户类型
//==========================================
function clearSearchInput() {
	$("#versionnumInput").val("");
}
//保存新增价格
	function saveprice(){
        	var data =$('.layui-layer-content').find("form[id='addPriceForm']").serialize();
        	jQuery.ajax({
				type : 'POST',
			    url : ctx + '/saveNewProductPrice.do',
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
	
	