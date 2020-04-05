<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>vrss2-pss</title>
<link href="cssnew/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
 		 var ctx = '${ctx}';
	</script>
</head>

<body class="dlbg">

<div class="dlbox">
<form method="post" action="${ctx}/login_check" method="post">
<div class="dltitle">User Login</div>
<div class="dllogo"><img src="imgnew/logo01.png" width="257" height="137" /></div>
<div class="dl_rbox">
<div class="dlfont">UserName：</div>
<div class="dlinput"> <input type="text" id="username" name="username"/></div>
<div class="clear"></div>
<div class="dlfont">Pssword：</div>
<div class="dlinput"><input type="password" name="password"/></div>
<#if message??><p><span style="color:red">${message}</span></p></#if>
<div class="clear"></div>
<div class="dlanniu"><button class="btnlogin">Login</button>
<a href="${ctx}/forgotPassword.html" target="blank">Forgot password？</a>
<a href="${ctx}/forgotuserName.html" target="blank">Forgot UserName？</a>
</div>
<div class="dlanniu01"><a href="${ctx}/register.html">Register</a></div>

</form>
</div>
</div>
<div class="dlcopybg">2015 Agencia Bolivariana para Actividades Espaciales - All Rights Reserved.</div>


</body>
</html>
