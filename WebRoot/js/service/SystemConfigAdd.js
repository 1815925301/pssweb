/**
 * 学生管理
 */
//layer打开的顺序
var newIndex,showIndex,editIndex;

/*$(document).ready(function(){
	$("#config_key_add").click(function(){
//		alert("进入搜索");
		
		
	});
	
});
*/

//验证配置值的校验
$('#config_value_add').live('blur',function() {
	 var flag=checkchina($(this).val());
	 if(!flag){
		 $("#inputValue").show();
	 }else{
		 $("#inputValue").hide();
	 }
});
//验证配置名称的校验
$('#config_key_add').live('blur',function() {
	 var flag=checkOrderName($(this).val());
	 if(!flag){
		 $("#inputName").show();
	 }else{
		 $("#inputName").hide();
	 }
});


function importprosur(){alert();
	$.ajaxFileUpload({
		type: "POST",
		//处理文件上传操作的服务器端地址
		url : ctx +"/saveNewSystemConfig.do",
		secureuri : false, //是否启用安全提交,默认为false    
		fileElementId : 'file', //文件选择框的id属性   
		dataType:'json', //服务器返回的格式,可以是json或xml等   
		cache: false,
		success : function(data) {
			if(data.success){
				queryProductSurvey();
			}
		}
	});
}

//校验
//jQuery().ready(function() {
//	alert();
//	jQuery("#addSystemForm").validate({
//		rules : {
//			configkey : {
//				required : true,
//				rangelength : [ 1, 10 ]
//			},
//			configvalue : {
//				required : true,
//				rangelength : [ 1, 10 ]
//			},
//			configdiscribe : {
//				required : true,
//				rangelength : [ 1, 20 ]
//			}
//
//		},
//		messages : {
//			configkey : {
//				required : "配置名称不能为空",
//				rangelength : "请输入配置名称"
//			},
//			configvalue : {
//				required : "配置值不能为空",
//				rangelength : "请输入配置值"
//			},
//			configdiscribe : {
//				required : "配置描述不能为空",
//				rangelength : "请输入配置描述"
//			}
//		}
//	});
//});

	
	