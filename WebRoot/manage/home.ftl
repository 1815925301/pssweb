<#include "top.ftl">
<script>

$(document).ready(function(){
	
	//鐢ㄦ埛鏇存柊鎻愮ず
	 $.ajax({
				type : 'POST',
			    url : ctx + '/queryuserday.do',
			    success : function(data) {
			     	//alert(data.eUser.userName+"--------"+data.daysBetween+"澶�");
			    },
			    error:function(){
			    }
			});
		
	
	$.ajaxSetup ({ cache: false });
	//瀵艰鎺у埗
	$(".en_navli").each(function(index){		
		$(this).hover(function(){
			$(this).addClass('innavli');
			$(this).find('a').find('span').addClass('inspan');
			},function(){
				$(this).removeClass('innavli');
				$(this).find('a').find('span').removeClass('inspan');
				});
	});
	
	
});</script>
<script type="text/javascript" src="js/jquery.jslides.js"></script>
<!--banner-->
<iframe style="width:100%; height:100%" src="<#if language_js='en'>${bathPath}/site2/index.shtml<#else>${bathPath}/site1/index.shtml</#if>"></iframe>

<#include "foot.ftl">
