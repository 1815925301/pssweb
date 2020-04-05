
$(document).ready(function() {
	changeLang_js();
	         
});

var onekey=[];
//读取配置文件获取一级菜单
jQuery(document).ready(function (){
	var productcate = $("#producttype_").val();//
	var cateList = productcate.split(",");//所有一级菜单数据
	var oneselectoption="";
	if(cateList.length>0){
		for (var i=0;i<cateList.length;i++)
		{
			var cate = cateList[i].split("_");//根据下划线分割key和val
			if(cate.length>0){
				onekey[i]=cate[0];
				oneselectoption=oneselectoption + "<option value='"+cate[0]+"'>"+cate[1]+"</option>";
			}
		}
		
	}
	
	//动态添加
	 jQuery("#btnAdd").click(function(){  
	        var $table = $("#tabid tr.col");//获取table的tr集合  
	        var len = $table.length;//获取table的行数  
	        //if(len>=0){
	        var dtatHtml = "<tr class='col'><td><select  name='producttype_"+len+"' id='producttype' class='producttype_"+len+"' onChange='changeCity("+len+");'>";
	        dtatHtml = dtatHtml + oneselectoption;
		 
	               
	        dtatHtml = dtatHtml+"</select></td><td><select name='productlevel_"+len+"' id='city' class='city_"+len+"'>" +
	        		"<option value=''></option></select></td><td>" +
	        		
	        		
	        		"<input type='text' name='productarea_"+len+"' id='productarea' />" +
        			"</td><td><input type='text' name='price_"+len+"' id='price' /></td></tr>"; 
	          
	        $("#tabid").append(dtatHtml);
	        //}
	        var addid="productcate_"+len;
	        var addvalue=$("."+addid).val();
	        jQuery.ajax({
				type : 'POST',
			    url : ctx + '/selectvaluebyid.do',
			    dataType : 'json',
			    data : {
			    	selectid:addvalue
			    },
			    success : function(data) {
			    	$(".city_"+len).html("");
			    	$.each(data, function(i, item) {   
			    	
			    		var option = '<option value="' + i+ '" >'+ item+ '</option>';
						jQuery(".city_"+len).append(option);
	            	});
			    }
	          });			
    	    });  
	  
	 
    });
//获取值,提交
    function addBatchMarker(){
    	var productcate;
    	var productlevel;
    	var price;
    	var productarea;
    	var columnNameInputs=jQuery("#tabid tr.col");
    	var versionnum;
	var len=columnNameInputs.length;
	var i=0;
	var values="";
	for(i;i<len;i++){
		productcate=jQuery("select[name='producttype_"+i+"']").val();
		productlevel=jQuery("select[name='productlevel_"+i+"']").val();
		price=jQuery("input[name='price_"+i+"']").val();
		versionnum=$("#versionnum").val();
		productarea=jQuery("input[name='productarea_"+i+"']").val();
		if(productarea==""){
		productarea=0;
		}
		values=values+productcate+'#'+productlevel+'#'+productarea+'#'+price+'#'+ versionnum+ '@';
	}
	values=values.substring(0, values.length-1);
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/saveNewProductPrice.do',
	    dataType : 'json',
	    data : {
	    	values:values
	    },
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
	    }
});			
	
}
    
   //点击一个下拉框，第二个下拉框的值变换
    function changeCity(len){
    	var onevalue=$(".producttype_"+len).val();//
    	//第一个下拉框里面的值，去动态修第二个下拉框里面的值 
		jQuery.ajax({
					type : 'POST',
				    url : ctx + '/selectvaluebyid.do',
				    dataType : 'json',
				    data : {
				    	selectid:onevalue
				    },
				    success : function(data) {
				    	$(".city_"+len).html("");
				    	$.each(data, function(i, item) {   
				    	
				    		var option = '<option value="' + i+ '" >'+ item+ '</option>';
							jQuery(".city_"+len).append(option);
		            	});
				    }
		});			
        
    }
    
