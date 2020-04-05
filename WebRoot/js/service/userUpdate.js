
$(document).ready(function() {
	changeLang_js();
	         
});

// 用户更新设置
function saveUpdate(){
	var data =$('#updateForm').serialize();
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/saveUserUpdate.do',
	    dataType : 'json',
	    data:data,
	    success : function(data) {
	    	layer.alert($.i18n.prop('success'), {
    		    icon: 1,
    		    skin: 'layer-ext-moon' 
    		});
	    },
	    error:function(){
	    		layer.alert($.i18n.prop('faile'), {
	    		    icon: 1,
	    		    skin: 'layer-ext-moon' 
	    		});
	    	} 
	});
}