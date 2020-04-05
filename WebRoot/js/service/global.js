/*
 * 现在只有一个方法，用于发送url请求，处理后，自动刷新页面。
 */
function changeLanguage(global){
	//alert(global);
	$.ajax({
		url: ctx + "/changeLanguage.do",
		data:{locale:global},
		type:"post",
		dataType:"json",
		async:true,
		cache:false,
		success:function(result){
			window.location.reload();//刷新页面
		}
		});
}