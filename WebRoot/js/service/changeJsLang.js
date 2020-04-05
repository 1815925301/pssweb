function changeLang_js(){
	$.ajax({
		url: ctx + "/getLanguage.do",
		type:"post",
		async:true,
		cache:false,
		success:function(data){
			var language_js = data.language_js;
			if(language_js=="zh"  || language_js==null){
				jQuery.i18n.properties({
			        name : 'strings_zh', //资源文件名称
			        path : 'i18n/', //资源文件路径
			        mode : 'map' //用Map的方式使用资源文件中的值
			    });
			}
			if(language_js=="en"){
				jQuery.i18n.properties({
			        name : 'strings_en', //资源文件名称
			        path : 'i18n/', //资源文件路径
			        mode : 'map' //用Map的方式使用资源文件中的值
			    });
			}
		  }
		});
	
}