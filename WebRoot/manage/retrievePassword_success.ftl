<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible"
	content="IE=8, IE=9, IE=10, IE=EDGE, chrome=1">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Expires" CONTENT="-1">
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>vrss2-pss</title>
<meta name="keywords" content="委内瑞拉遥感卫星二号地面应用系统公共服务分系统">
<meta name="description" content="委内瑞拉遥感卫星二号地面应用系统公共服务分系统">

<script src="js/menu/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${ctx}/js/login.js" type="text/javascript"></script>
<!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
<script src="js/service/register.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="cssnew/style.css" />
<script type="text/javascript">
function check_password(){
	var password=document.getElementById("password").value;
	var password_error=document.getElementById("password_error");
	var i=/^[a-zA-Z]\w{5,17}$/
	if(password == ""){
		password_error.innerHTML="<font size='2' color='red'>Password cant be empty</font>";
		return false;
		
	}else if(!i.test(password)){
		password_error.innerHTML="<font size='2' color='red'>Password to begin with a letter，The length is between 6 to 18</font>";
		return false;
	}else{
		password_error.innerHTML="";
		return true;
	}
}
function check_confirmPs(){
	var password=document.getElementById("password").value;
	var confirmPs=document.getElementById("confirmPs").value;
	var confirmPs_error=document.getElementById("confirmPs_error");
	if(password==confirmPs){
		confirmPs_error.innerHTML="";
		return true;
		
	}else{
		confirmPs_error.innerHTML="<font size='2' color='red'>Entered passwords differ</font>";
		return false;
	}
}
function check_all(){
	if(!check_password()){
		//${"password"}.focus();
		document.getElementById("password").focus();
		return false;
	}
	if(!check_confirmPs()){
		//${"confirmPs"}.focus();
		document.getElementById("confirmPs").focus();
		return false;
	}

	else{
		return true;
	}
}
</script>
<body class="wjmmbg">
	<div class="wjmm_w">
		<form action="./updatePassword.html" method="post" id="retrievepswd"
			onsubmit="return check_all()">
			<div>
				<img src="imgnew/logo.png" width="284" height="70" />
			</div>
			<div class="wjmm_bg">
				<div class="wjmm_t">Set a new password</div>
				<div class="wjmm_bdb">
					<input type="hidden" name="id" value="${u.id}" />
					<div class="wjmmf">New Password：</div>
					<div class="wjmm_input">
						<input name="password" id="password" type="password"
							onblur="check_password()" />
					</div>
					<span id="password_error"></span>
					<div class="clear"></div>
					<div class="wjmmf">Confirm Password：</div>
					<div class="wjmm_input">
						<input name="confirmPs" id="confirmPs" type="password"
							onblur="check_confirmPs()" />
					</div>
					<span id="confirmPs_error"></span>
					<DIV class="clear"></DIV>
					<div class="wjmm_anniu">
							<input type="submit" value="Change Password" class="formsubmit">
						
					</div>

				</div>
			</div>
		</form>
	</div>

</body>

</html>

<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>