
/*
	$.validator.setDefaults({
		submitHandler: function() { alert("submitted!"); }
	});
	
	$().ready(function() {
		$("#signupForm").validate();
		
		var value = $("#phoneNumber").attr("class"); 
		var value2 = $("#xm").attr("class"); 
		var value1 = $("#phoneNumber").val();
		var value3 = $("#xm1").text();  
		alert(value+"----"+value2+"----"); 
		alert("<fmt:message key="user.phoneNumber"/>");
	});*/
function checkidcard(num){
	var len = num.length, re;
	if (len == 15)
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
	else if (len == 18)
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
	else{
		//alert("请输入15或18位身份证号,您输入的是 "+len+ "位"); 
		return false;
	}
	var a = num.match(re);
	if (a != null){
		if (len==15){
			var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);
			var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
		}else{
			var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);
			var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
		}
		if (!B){
			//alert("输入的身份证号 "+ a[0] +" 里出生日期不对！"); 
			return false;
		}
	}

	return true;
} 

function checkchina(str){
	var reg = /^[\u0391-\uFFE5]+$/;
	//alert(reg.test(str));
    if(!reg.test(str)){
       return false;
    }
    return true;
} 



//检查名称不能为数字
function checkenglish(str){
	alert();
	var reg = /^[A-Za-z]+$/;
	//alert(reg.test(str));
    if(!reg.test(str)){
       return false;
    }
    return true;
} 


function checkpassword(str){
	var  password=jQuery("#password").val();
	var  againpassword =jQuery("#againpassword").val();
	
	 if(password!= againpassword){
       return false;
    }
    return true;
	
}


function checkdata(str){
//	var reg = /^[A-Za-z0-9]+$/;
	var reg = /^[A-Za-z0-9]+$/;
//	alert(reg.test(str));
    if(!reg.test(str)){
       return false;
    }
    return true;
} 


function checkemail(str){
	var reg =/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	//alert(reg.test(str));
    if(!reg.test(str)){
       return false;
    }
    return true;
} 


function checkPhone(str){
	var reg =/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
	//alert(reg.test(str));
    if(!reg.test(str)){
       return false;
    }
    return true;
} 

function checkMobile(str){
	var reg =/^((\(\d{3}\))|(\d{3}\-))?1[358]\d{9}$/;
	//alert(reg.test(str));
    if(!reg.test(str)){
       return false;
    }
    return true;
} 

function checkUrl(str){
	var reg =/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
	//alert(reg.test(str));
    if(!reg.test(str)){
       return false;
    }
    return true;
}



function checklongitude(str){
	var reg =/^-?(?:(?:180(?:\.0{1,5})?)|(?:(?:(?:1[0-7]\d)|(?:[1-9]?\d))(?:\.\d{1,5})?))$/;
	//alert(reg.test(str));
    if(!reg.test(str)){
       return false;
    }
    return true;
}


function checklatitude(str){
	var reg =/^-?(?:90(?:\.0{1,5})?|(?:[1-8]?\d(?:\.\d{1,5})?))$/;
	//alert(reg.test(str));
    if(!reg.test(str)){
       return false;
    }
    return true;
}

function checkDouble(str){
//alert("double");
	var reg = /^[-\+]?\d+(\.\d+)?$/;
	if(!reg.test(str)){
		return false;
	}
	return true;

}


function checkIngeter(str){
//alert("ingeter");
	var reg =/^[-\+]?\d+$/;
	if(!reg.test(str)){
		return false;
	}
	return true;

}



// 添加验证方法 (身份证号码验证)
	jQuery.validator.addMethod("isIdCardNo", function(value, element) {   
		return this.optional(element) || checkidcard(value);   
	}); 
	
	//添加中文验证方法
	jQuery.validator.addMethod("chinaNo", function(value, element) {
	
		return this.optional(element) || checkchina(value);   
	}); 
	

	//添加英文验证方法
	jQuery.validator.addMethod("englishNo", function(value, element) {
	
		return this.optional(element) || checkenglish(value);   
	}); 
	
	
	//添加英文验证方法
	jQuery.validator.addMethod("dataNo", function(value, element) {
	
		return this.optional(element) || checkdata(value);   
	}); 


	//添加邮箱验证方法
	jQuery.validator.addMethod("emailNo", function(value, element) {
	
		return this.optional(element) || checkemail(value);   
	}); 


	//添加电话验证方法
	jQuery.validator.addMethod("phoneNo", function(value, element) {
	
		return this.optional(element) || checkPhone(value);   
	}); 


	//添加手机验证方法
	jQuery.validator.addMethod("mobileNo", function(value, element) {
	
		return this.optional(element) || checkMobile(value);   
	}); 


	//添加url验证方法
	jQuery.validator.addMethod("urlNo", function(value, element) {
	
		return this.optional(element) || checkUrl(value);   
	}); 

	//添加经度验证方法
	jQuery.validator.addMethod("longitudeNo", function(value, element) {
	
		return this.optional(element) || checklongitude(value);   
	}); 

	//添加纬度验证方法
	jQuery.validator.addMethod("latitudeNo", function(value, element) {
	
		return this.optional(element) || checklatitude(value);   
	}); 

	//添加double验证方法
	jQuery.validator.addMethod("doubleNo", function(value, element) {
	
		return this.optional(element) || checkDouble(value);   
	}); 

	//添加ingeter验证方法
	jQuery.validator.addMethod("integerNo", function(value, element) {
	
		return this.optional(element) || checkIngeter(value);   
	}); 
	
	//验证密码
	jQuery.validator.addMethod("passwordNo", function(value,element) {
	
		return this.optional(element) || checkpassword(value);   
	}); 