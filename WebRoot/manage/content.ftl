<#include "top.ftl">
<script>
$(document).ready(function(){
	//閻€劍鍩涢弴瀛樻煀閹绘劗銇�
	 $.ajax({
				type : 'POST',
			    url : ctx + '/queryuserday.do',
			    success : function(data) {
			     	//alert(data.eUser.userName+"--------"+data.daysBetween+"婢讹拷");
			    },
			    error:function(){
			    }
			});
		
	
	$.ajaxSetup ({ cache: false });
	//鐎佃壈顢戦幒褍鍩�
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
<iframe id="myFrame" style="width:100%; height:100%" 
src="<#if language_js='en'>${bathPath}/site2/${path}<#else>${bathPath}/site1/${path}</#if>"></iframe>

<#include "foot.ftl">
