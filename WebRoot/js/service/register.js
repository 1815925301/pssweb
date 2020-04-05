
// 国家和省份联动

$(document).ready(function() {
	
});
function changeCountry() {
	var optiontitle='<option value="-1">--请选择--</option>';
	var countryCode = jQuery("#country").val();
	
	// 如果没有选中国家，省级列表置空，否则加载该国家的所有省份
	if (countryCode == -1) {
		$("#province").empty();
		jQuery("#province").append(optiontitle);
		changeProvince();
	}else if(countryCode!=45){
	
		document.getElementById('city').style.display='none';
		document.getElementById('cityinput').style.display='block';
	
	
		$("#province").empty();
		jQuery("#province").append(optiontitle);
		jQuery.ajax({
			type : 'POST',
			url : './getProvince.do',
			dataType : 'json',
			data : {
				countryCode : countryCode
			},
			success : function(data) {
				if (data.pssProvinceList != null) {
					var provinces = data.pssProvinceList;
					for (var i = 0; i < provinces.length; i++) {
						var province = provinces[i];
						var option;
						
							option = '<option value="' + province.code + '">'
									+ province.name + '</option>';
						
						jQuery("#province").append(option);
					}
				}
				changeProvince();
			},
			error : function() {
			
			}
		});
	}else{
		
		document.getElementById('city').style.display='block';
		document.getElementById('cityinput').style.display='none';
		$("#province").empty();
		jQuery("#province").append(optiontitle);
		jQuery.ajax({
			type : 'POST',
			url : './getProvince.do',
			dataType : 'json',
			data : {
				countryCode : countryCode
			},
			success : function(data) {
				if (data.pssProvinceList != null) {
					var provinces = data.pssProvinceList;
					for (var i = 0; i < provinces.length; i++) {
						var province = provinces[i];
						var option;
						
							option = '<option value="' + province.code + '">'
									+ province.name + '</option>';
						
						jQuery("#province").append(option);
					}
				}
				changeProvince();
			},
			error : function() {
			
			}
		});
	}
}


// 省份与市联动
function changeProvince() {

	
	var optiontitle='<option value="-1">--请选择--</option>';
	var provinceCode = $('#province option:selected').val();
	
	// 如果没有选中省份，市级列表置空，否则加载该省份的所有市
	if(provinceCode==-1){
		$("#city").empty();
		jQuery("#city").append(optiontitle);
		changeCity();
	}else{
		
		$("#city").empty();
		jQuery("#city").append(optiontitle);
		
		jQuery.ajax({
		type : 'POST',
	    url :  './getCity.do',
	    dataType : 'json',
	    data : {
	    	provinceCode:provinceCode
	    },
	    success : function(data) {
	    	if (data.pssCityList != null) {
    			var cityes = data.pssCityList;
    			for(var i =0;i<cityes.length;i++){
    				var city = cityes[i];
    				var option;
    			
    				 option = '<option value="' + city.code
					+ '" >'
									+ city.name
									+ '</option>';
    				 jQuery("#city").append(option);
    			}
							
    			
    		}
	    	changeCity();
	    },
	    error:function(){
	    	
	    	} 
	});
    
	}
	
}

//市和县联动
function changeCity() {
var optiontitle='<option value="-1">--请选择--</option>';
var cityCode = jQuery("#city").val();
//如果没有选中市，县级列表置空，否则加载该市的所有县
	if(cityCode==-1){
		$("#county").empty();
		jQuery("#county").append(optiontitle);
		
	}else{	
		$("#county").empty();
		jQuery("#county").append(optiontitle);
		jQuery.ajax({
		type : 'POST',
	    url : './getTown.do',
	    dataType : 'json',
	    data : {
	    	cityCode:cityCode
	    },
	    success : function(data) {
	    	if (data.pssTownList != null) {
    			var towns = data.pssTownList;
    			for(var i =0;i<towns.length;i++){
    				var town = towns[i];
    				var option;
    				
    				 option = '<option value="' + town.code
					+ '" >'
									+ town.name
									+ '</option>';
    				 jQuery("#county").append(option);	 
    			}
							
    			
    		}
	    },
	    error:function(){
	    		
	    	} 
	});
    
	}
}

function lockCancle(){
	window.location.href=ctx + "/login.do";
}

function checkUsername(){
	var userName=document.getElementById('userName').value;
	if(userName==""){
		document.getElementById('userName_error').style.display="block";
		return false;
	}else{
		document.getElementById('userName_error').style.display="none";
		return true;
	}
}
function checktrueName(){
	var trueName=document.getElementById('trueName').value;
	if(trueName==""){
		document.getElementById('trueName_error').style.display='block';
		return false;
	}else{
		document.getElementById('trueName_error').style.display='none';
		return true;
	}
}
function checkuserTitle(){
	var userTitle=document.getElementById('userTitle').value;
	if(userTitle==""){
		document.getElementById('userTitle_error').style.display='block';
		return false;
	}else{
		document.getElementById('userTitle_error').style.display='none';
		return true;
	}
}
function checkpassword111(){
	var password=document.getElementById('password1').value;
	if(password==""){
		document.getElementById('password_error').style.display='block';
		return false;
	}else{
		document.getElementById('password_error').style.display='none';
		return true;
	}
}
function checkagainpassword(){
	var password=document.getElementById('password1').value;
	var againpassword=document.getElementById('againpassword').value;
	
	if(password!=againpassword){
		document.getElementById('againpassword_error').style.display='block';
		return false;
	}else{
		document.getElementById('againpassword_error').style.display='none';
		return true;
	}
}
function checkidentitycard(){
	var identitycard=document.getElementById('identitycard').value;
	var i=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
	if(identitycard==""){
		document.getElementById('identitycard_tip').style.display='block';
		return false;
	}else if(!i.test(identitycard)){
		document.getElementById('identitycard_tip').style.display='none';
		document.getElementById('identitycard_error').style.display='block';
		return false;
	}else{
		document.getElementById('identitycard_tip').style.display='none';
		document.getElementById('identitycard_error').style.display='none';
		return true;
	}
}
function checkpostcode1(){
	var postcode=document.getElementById('postcode').value;
	var i=/[1-9]\d{5}(?!\d)/;
	if(postcode==""){
		document.getElementById('postcode_tip').style.display='block';
		return false;
	}else if(!i.test(postcode)){
		document.getElementById('postcode_tip').style.display='none';
		document.getElementById('postcode_error').style.display='block';
		return false;
	}else{
		document.getElementById('postcode_tip').style.display='none';
		document.getElementById('postcode_error').style.display='none';
		return true;
	}
}
function checkMobile1(){
	var mobile=document.getElementById('mobile').value;
	var i=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	if(mobile==""){
		document.getElementById('mobile_tip').style.display='block';
		return false;
	}else if(!i.test(mobile)){
		document.getElementById('mobile_tip').style.display='none';
		document.getElementById('mobile_error').style.display='block';
		return false;
	}else{
		document.getElementById('mobile_tip').style.display='none';
		document.getElementById('mobile_error').style.display='none';
		return true;
	}
}
function checkEmail(){
	var email=document.getElementById('email').value;
	var i=/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	if(email==""){
		document.getElementById('email_tip').style.display='block';
		return false;
	}else if(!i.test(email)){
		document.getElementById('email_tip').style.display='none';
		document.getElementById('email_error').style.display='block';
		return false;
	}else{
		document.getElementById('email_tip').style.display='none';
		document.getElementById('email_error').style.display='none';
		return true;
	}
}

function checkfax(){
	var fax=document.getElementById('fax').value;
	var i=/^(\d{3,4}-)?\d{7,8}$/;
	if(fax==""){
		document.getElementById('fax_tip').style.display='block';
		return false;
	}else if(!i.test(fax)){
		document.getElementById('fax_tip').style.display='none';
		document.getElementById('fax_error').style.display='block';
		return false;
	}else{
		document.getElementById('fax_tip').style.display='none';
		document.getElementById('fax_error').style.display='none';
		return true;
	}
}
function checkipAddress(){
	var ipAddress=document.getElementById('ipAddress').value;
	var i=/\d+\.\d+\.\d+\.\d+/;
	if(ipAddress==""){
		document.getElementById('ipAddress_tip').style.display='block';
		return false;
	}else if(!i.test(ipAddress)){
		document.getElementById('ipAddress_tip').style.display='none';
		document.getElementById('ipAddress_error').style.display='block';
		return false;
	}else{
		document.getElementById('ipAddress_tip').style.display='none';
		document.getElementById('ipAddress_error').style.display='none';
		return true;
	}
}
function checkcompany(){
	var company=document.getElementById('company').value;
	if(company==""){
		document.getElementById('company_error').style.display='block';
		return false;
	}else{
		document.getElementById('company_error').style.display='none';
		return true;
	}
}
function checkall(){
	if(!checkUsername()){
		document.getElementById('userName').focus();
		return false;
	}
	if(!checktrueName()){
		document.getElementById('trueName').focus();
		return false;
	}
	if(!checkuserTitle()){
		document.getElementById('userTitle').focus();
		return false;
	}if(!checkpassword111()){
		document.getElementById('password1').focus();
		return false;
	}if(!checkagainpassword()){
		document.getElementById('againpassword').focus();
		return false;
	}if(!checkidentitycard()){
		document.getElementById('identitycard').focus();
		return false;
	}if(!checkpostcode1()){
		document.getElementById('postcode').focus();
		return false;
	}if(!checkMobile1()){
		document.getElementById('mobile').focus();
		return false;
	}
	if(!checkEmail()){
		document.getElementById('email').focus();
		return false;
	}
	if(!checkfax()){
		document.getElementById('fax').focus();
		return false;
	}
	if(!checkipAddress()){
		document.getElementById('ipAddress').focus();
		return false;
	}
	if(!checkcompany()){
		document.getElementById('company').focus();
		return false;
	}
}