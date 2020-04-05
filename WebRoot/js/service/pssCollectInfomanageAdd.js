$(document).ready(function() {
	changeLang_js();
	
});

function clearSearchInput() {
	$("#upperleftlong").val("");
	$("#upperleftlong_error").html("");
	$("#upperleftlat_error").html("");
	$("#lowerrightlong_error").html("");
	$("#lowerrightlat_error").html("");
	$("#cloudcover_error").html("");
	$("#sideangle_error").html("");
	$("#upperleftlat").val("");
	$("#lowerrightlong").val("");
	$("#lowerrightlat").val("");
	$("#createtime").val("");
	$("#begintime").val("");
	$("#endtime").val("");
	$("#sceneid").val("");
	$("#cloudcover").val("");
	$("#sideangle").val("");
	$("#productlevel").val("-1");
	$("#earthsurfacename").val("-1");
	$("#sensorid").val("");
}

function checkupperleftlong(){
	var upperleftlong= document.getElementById("upperleftlong").value;
	var upperleftlong_error=document.getElementById("upperleftlong_error");
	
	if(upperleftlong>180 || upperleftlong<-180){
		upperleftlong_error.innerHTML="<font size='2' color='red'>"+$.i18n.prop('jingdufanwei')+"</font>";
		return false;
	}else{
		upperleftlong_error.innerHTML="";
		return true;
	}
}
function checkupperleftlat(){
	var upperleftlat= document.getElementById("upperleftlat").value;
	var upperleftlat_error=document.getElementById("upperleftlat_error");
	
	if(upperleftlat>180 || upperleftlat<-180){
		upperleftlat_error.innerHTML="<font size='2' color='red'>"+$.i18n.prop('jingdufanwei')+"</font>";
		return false;
	}else{
		upperleftlat_error.innerHTML="";
		return true;
	}
}
function checklowerrightlong(){
	var lowerrightlong= document.getElementById("lowerrightlong").value;
	var lowerrightlong_error=document.getElementById("lowerrightlong_error");
	
	if(lowerrightlong>90 || lowerrightlong<-90){
		lowerrightlong_error.innerHTML="<font size='2' color='red'>"+$.i18n.prop('jingdufan')+"</font>";
		return false;
	}else{
		lowerrightlong_error.innerHTML="";
		return true;
	}
}
function checklowerrightlat(){
	var lowerrightlat= document.getElementById("lowerrightlat").value;
	var lowerrightlat_error=document.getElementById("lowerrightlat_error");
	
	if(lowerrightlat>90 || lowerrightlat<-90){
		lowerrightlat_error.innerHTML="<font size='2' color='red'>"+$.i18n.prop('jingdufan')+"</font>";
		return false;
	}else{
		lowerrightlat_error.innerHTML="";
		return true;
	}
}
function checkCloudcover(){
	var cloudcover=document.getElementById("cloudcover").value;
	var cloudcover_error=document.getElementById("cloudcover_error");
	
	if(cloudcover>100 || cloudcover<0 ){
		cloudcover_error.innerHTML="<font size='2' color='red'>"+$.i18n.prop('notrue')+"</font>";
		return false;
	}else{
		cloudcover_error.innerHTML="";
		return true;
	}
}

$('#sideangle').live('blur',function() {
	 var flag=checksideangle($(this).val());
	 if(!flag){
		 $("#inputSideangle").show();
	 }else{
		 $("#inputSideangle").hide();
	 }
});
$('#cloudcover').live('blur',function() {
	var flag=checkcloudcover($(this).val());
	if(!flag){
		$("#inputCloudcover").show();
	}else{
		$("#inputCloudcover").hide();
	}
});