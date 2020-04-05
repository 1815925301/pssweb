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
<body class="wjmmbg">
	<div class="wjmm_w">
		<form action="./retrievePassword.html" method="post" id="forgotpswd">
			<div>
				<img src="imgnew/logo.png" width="284" height="70" />
			</div>
			<div class="wjmm_bg">
				<div class="wjmm_t">Forget UserInfo</div>
				<div class="wjmm_bdb">
					<input type="hidden" value="${identifier }" name="identifier"/>
					<div class="wjmmf">E-mail：</div>
					<div class="wjmm_input">
						<input name="userEmail" id="userEmail" type="text" />
					</div>
					<DIV class="clear"></DIV>
					<div class="wjmm_anniu">
						<a
							<a onclick="document.getElementById('forgotpswd').submit();return false">Retrieve</a>
					</div>

				</div>
			</div>
		</form>
	</div>

</body>
</html>

<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>