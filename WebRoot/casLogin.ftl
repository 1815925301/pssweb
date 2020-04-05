<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中国石油大学招标办公室 - 用户登录</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/login.css">

<script language="JavaScript" type="text/javascript">
	if (self.parent.frames.length != 0) {
		self.parent.location=document.location;
	}
</script>

<body onload="javascritp:document.getElementById(&#39;username&#39;).focus();">
<input id="errorInfo" type="hidden" value="">
<form action="${ctx}/login_check" method="post">  
  <div class="wrap">
    <div class="meb_logo">
      <div class="top"></div>
    </div>
    <div class="main">
      <div class="form">
        <div class="regist_link">
        <a href="http://localhost:8080/ebidding/register.html" target="_blank">立即注册</a>
        </div>
        <div class="login">
          <div class="hd">
            <h2>电子招标投标平台</h2>
            <span>Online electronic bidding trading platform</span></div>
          <div class="bd">
            <div class="box">
              <div class="bx_l">用户名</div>
              <div class="bx_r txt">
                <Input type="text" name="username" value="<#if username??>${username}</#if>" style="ime-mode:disabled" class="required"/>
                <div class="tooltip" style="display: none;"> <span class="tip_con">用户名提示信息用户名提示信息</span> <i class="tip_arrow"></i> </div>
              </div>
            </div>
            <div class="box">
              <div class="bx_l">密　码</div>
              <div class="bx_r txt">
                <input type="password" id="password" name="password" value="" style="ime-mode:disabled" class="required">
                <div class="tooltip"> <span class="tip_con">密码提示信息</span> <i class="tip_arrow"></i> </div>
              </div>
            </div>
            <div class="box">
              <div class="bx_l">验证码</div>
              <div class="bx_r yzm">
                <div class="yzm_l">
                  <input type="text" id="seccode" name="seccode" value="" style="ime-mode:disabled" maxlength="4" class="required">
                  <div class="tooltip"> <span class="tip_con">验证码提示信息</span> <i class="tip_arrow"></i> </div>
                </div>
                <div class="yzm_r"> <span><img class="changeCaptcha" id="captcha" src=""></span> <a href="#" class="changeCaptcha">看不清换一张</a> </div>
              </div>
            </div>
            <div class="box login_btn">
              <INPUT id=btnLogin type="submit" value=登陆>
              <br><#if message??><p><span style="color:red">${message}</span></p></#if>
            </div>
            <div class="box bot_link" >没有账号？<a  target="_blank">立即注册</a>|<a  target="_blank">忘记密码？</a></div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="footer">
    <div class="pro">Copyright ® 2014 zb.upc.edu.cn 技术支持：<a href="#" target="_blank">中科软科技股份有限公司</a></div>
    <div class="links">  </div>
  </div>
</form>
</body></html>
