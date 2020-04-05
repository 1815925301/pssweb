$(document).ready(function() {
	//生成验证码  
	$('.changeCaptcha').click(function () {
		$('#captcha').hide().attr('src', ctx+'/verifyCode?' + Math.floor(Math.random()*100) ).fadeIn();  
	}); 
	$('#captcha').click(function () {
	     $(this).hide().attr('src', ctx+'/verifyCode?' + Math.floor(Math.random()*100) ).fadeIn();  
	}); 
	
	$('#captcha').attr('src', ctx+'/verifyCode?' + Math.floor(Math.random()*100) ).fadeIn(); 
	
});


 
