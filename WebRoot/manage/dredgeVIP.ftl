<#include "top.ftl">
<script type="text/javascript">
function getDredgeVIP(){
	var roleName = $("input:radio[name='roleName']:checked").val();
	var dredgeType = $("input:radio[name='dredgeType']:checked").val();
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/dredgeVIP2.do',
	    dataType : 'json',
	    data:{
	    	roleName:roleName
	    },
	    success : function(data) {
	    	var price;
	    	if(dredgeType==1){
	    		price = data.onemonth;
	    	}else if(dredgeType==2){
	    		price = data.treemonths;
	    	}else if(dredgeType==3){
	    		price = data.year;
	    	}
	    	$('#price').text(price);
	    		
	    },
	    error:function(){
	    	alert("error");
	    	} 
	});
}
function submitVIPInfo(){
	var roleName = $("input:radio[name='roleName']:checked").val();
	var dredgeType = $("input:radio[name='dredgeType']:checked").val();
	var price = $('#price').text();
	jQuery.ajax({
		type : 'POST',
	    url : ctx + '/updateBeforeUserRoleInfo.do',
	    dataType : 'json',
	    data:{
	    	roleName:roleName,
	    	dredgeType:dredgeType,
	    	price:price
	    },
	    success : function(data) {
			var num = data.num;
			if(num>0){
				// alert("跳转到付费页面");
				window.location.href=("rolepay.do");
			}
	    		
	    },
	    error:function(){
	    	alert("error");
	    	} 
	});
}
</script>
<div align="center">
<form align="center" id="formSubmit" action="updateBeforeUserRoleInfo.do" method="post">
	<div>请选择您要开通的会员</div><br/>
	<label><input name="roleName" type="radio" value="2" checked="checked" onclick="getDredgeVIP()">白银会员</label>
	<label><input name="roleName" type="radio" value="61" onclick="getDredgeVIP()">黄金会员</label>
	<label><input name="roleName" type="radio" value="62" onclick="getDredgeVIP()">钻石会员</label><br/>
	<div>请选择你要开通的时长</div><br/>
	<label><input name="dredgeType" type="radio" value="1" checked="checked" onclick="getDredgeVIP()">一个月</label>
	<label><input name="dredgeType" type="radio" value="2" onclick="getDredgeVIP()">一季度</label>
	<label><input name="dredgeType" type="radio" value="3" onclick="getDredgeVIP()">一年</label><br/>
	<div><span>金额</span><span id="price" >${onemonth }</span></div>
	<input type="button" onclick="submitVIPInfo()" value="确认开通">
</form>
</div>
<#include "foot.ftl">

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>
