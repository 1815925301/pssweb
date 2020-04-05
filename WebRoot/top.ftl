

<!-- 国际化页面标签定义类型 -->

<#import "ftltags/spring.ftl" as spring/>
<!--<#assign gisurl = "http://LEEYIN-PC:8086">-->
<#assign gisurl = "http://192.168.20.21:8086">
<#assign security=JspTaglibs["/WEB-INF/tags/security.tld"] />
<head>

	<meta charset="utf-8" />

	<meta http-equiv="Cache-Control" CONTENT="no-cache">
	<meta http-equiv="Expires" CONTENT="-1">
	<meta http-equiv="Pragma" CONTENT="no-cache">

	<meta http-equiv="X-UA-Compatible" content="IE=8, IE=9, IE=10, IE=EDGE, chrome=1">

	
	<title><@spring.message "projecttitle"/></title>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="" name="description" />

	<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

	<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

	<!--[if lte IE 6]>
	<link href="css/menu/bootstrap-ie6.min.css" rel="stylesheet" type="text/css"/>
	<link href="css/menu/ie.css" rel="stylesheet" type="text/css"/>
 	<![endif]-->
 	
	<!--<link href="css/menu/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>-->

	<!--<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>-->
	<link href="css/font-awesome.min.css?v=4.3.0" rel="stylesheet">

	<link href="css/menu/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="css/menu/style.css" rel="stylesheet" type="text/css"/>

	<link href="css/menu/style-responsive.css" rel="stylesheet" type="text/css"/>


	<link href="css/menu/uniform.default.css" rel="stylesheet" type="text/css"/>
	
    <link href="css/menu/menu.css?v=2.2.0" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/plugins/datapicker/datepicker3.css" />
	<!-- END GLOBAL MANDATORY STYLES -->
	
	<link rel="shortcut icon" href="img/favicon.ico" />

	<link rel="stylesheet" type="text/css" href="cssnew/style.css" />
	<!-- END GLOBAL MANDATORY STYLES -->
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>

	<script type="text/javascript" src="js/zzsc.js"></script>
	<script type="text/javascript" src="js/jquery.jslides.js"></script>
	<script src="js/layer/layer.js"></script> 
	
	<!--国际化js  -->
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>
	<script src="js/service/global.js"></script> 
	<script src="js/service/changeJsLang.js"></script> 
	<script type="text/javascript">
	
	$(document).ready(function(){
		//
		 $.ajax({
			type : 'POST',
		    url : ctx + '/queryshopcarcontol.do',
		    success : function(data) {
		     	$(".gwc_sl").text(data.carcontol);
		    },
		    error:function(){
		    }
		});
		
	});
	
	 var ctx = '${ctx}';
	 
	</script>

</head>
<div class="xin_img"><img src="imgnew/aaa.png" width="1140" height="47" /></div>
<div class="redline"></div>
<div class="top_h">
<div class="logo"><img src="imgnew/logo.png" width="443" height="110" /></div>
<div class="top_r">
<#if CURRENT_USER_INFO !="" && CURRENT_USER_INFO ??>
<div class="top_r_info"><a href="./logout"><img src="imgnew/out-left.png"></a>
        ${CURRENT_USER_INFO.userName } <@spring.message "welcome"/>
    </div>
</#if>    
<div class="cn"><a onclick=""><@spring.message "spanishlanguage"/></a>/<a onclick="changeLanguage('en_US')">EN</a>/<a onclick="changeLanguage('zh_CN')"><@spring.message "chinese"/></a></div>  
<div class="clear"></div>
<div class="topr">
<div class="gwc"><a href="./pssshopcar.do"><@spring.message "mycar"/></a><div class="gwc_sl"></div></div>
<div class="ss_box">

<form name="queryform" id = "queryform" method="post" action="${ctx}/search.do" target="_blank">
	<input type="hidden" name="catalog" id="catalog" value="<#if language_js='en'>en<#else>cn</#if>">
	<input type="hidden" name="qtype" id="qtype" value="contents">
	<input type="hidden" name="hitsPerPage" id="hitsPerPage" value="20">
	<input type="hidden" name="date1" id="date1" value="2007-1-10">
	<input type="hidden" name="showSummaries" id="showSummaries" value="true">
	<input type="hidden" name="qsort" id="qsort" value="modified">
	<div class="ss_input"><input type="text" placeholder="search……" name="query" id="query" style="height: 38px;"/></div>
	<div class="ss_anniu" ><a id="search" onclick="document.queryform.submit();"><img src="imgnew/icon02.gif" width="52" height="38" /></a></div>
</form>

</div>
</div>
</div>
<div class="clear"></div>
</div>

<div class="menu1">
<ul>
<li><a href="./pssindex.html"><@spring.message "index"/></a></li>

<li><a href="./product.do"><@spring.message "datasearch"/></a></li>
<li><a href="./topicProductSearch.do"><@spring.message "Special.product.query"/></a></li>
<li><a href="./pssCollectInfomanage.do"><@spring.message "Collection"/></a></li>
<li><a href="./pssshopcar.do"><@spring.message "Shopcar"/></a></li>
<li><a href="./orderMain.do"><@spring.message "order"/></a></li>
<li><a href="./PersonalCenter.do"><@spring.message "personal_center"/></a></li>
</ul>
</div>


            
