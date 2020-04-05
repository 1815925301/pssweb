<#include "top.ftl">
 <#--<#include "iframetop.ftl"> -->
<!-- BEGIN PAGE CONTAINER-->

<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/tundra/tundra.css">
<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/claro/claro.css">
<link href="cssnew/style.css" rel="stylesheet">	
<script src="${gisurl}/arcgis_js_api/library/2.7/jsapi/" type="text/javascript"></script>

<script src="js/map/map_init.js" type="text/javascript"></script>

<script src="js/map/image.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	changeLang_js();
	if('${test1}'==1){
		var gpsdivs= document.getElementById("productquery");//获取要隐藏的div的ID
	    gpsdivs.style.display="none";//给div的属性改变,显示
	    var gpsdivs1= document.getElementById("productList");//获取要隐藏的div的ID
	    gpsdivs1.style.display="";//给div的属性改变,显示
	}else{
		var gpsdivs= document.getElementById("productList");//获取要隐藏的div的ID
	    gpsdivs.style.display="none";//给div的属性改变,显示
	}
	// 用户更新提示
	updateTip();
});

</script>
<div style="float: right; ">
<div class="main-im" >
<div style="margin-left:100px;width:280px">
		<a href="javascript:pan_method();"><img
				src="img/map/nav_pan.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:zoomin_method();"><img
				src="img/map/nav_zoomin.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:zoomout_method();"><img
				src="img/map/nav_zoomout.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:zoomtofull_method();"><img
				src="img/map/nav_fullextent.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:lengthMeasure_method();"><img
				src="img/map/line_up.png"/> </a>
		<a href="javascript:void(0);" onclick="javascript:areaMeasure_method();"><img
				src="img/map/area_up.png"/> </a>
		<a href="javascript:void(0);" onclick="javascript:rectangle_method();"><img
				src="img/map/polygn_up.png" /> </a>
		<a href="javascript:circle_method();"><img
				src="img/map/polygn_yuan.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:polygon_method();"><img
				src="img/map/polygn.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:deactivate_method();"><img
				src="img/map/delete_up.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:point_method();"><img
				src="img/map/Point_up.png" /> </a>
		<#--		
		<a href="javascript:void(0);" onclick="javascript:addPathRow_method();"><img
				src="img/map/layer_add.png" /> </a>-->
    </div>
    <div style="float:left;margin-left:410px;">
	    <select id="pathRow" style="width:92px;margin-top:-22px;" onChange="changePathRow();">
	                <option value="-1"></option>
		        	<option value="VRSS_1">VRSS_1</option>
		        	<option value="VRSS_2">VRSS_2</option>
		        	<option value="GF_1">GF_1</option>
		        	<option value="GF_2">GF_2</option>
	    </select>
    </div>	
    <div style="float:left;margin-left:505px;">
	    <select id="mapType" style="width:82px;margin-top:-40px;" onChange="changeMapType();">
		        	<option value="vector"><@spring.message "Vector"/></option>
		        	<option value="image"><@spring.message "striograph"/></option>
	    </select>
    </div>	
<div id="open_im" class="open-im">&nbsp;</div> 
<div class="im_main" id="im_main">
<div id="close_im" class="close-im"><a href="javascript:void(0);" title="点击关闭">&nbsp;</a></div>
<div class="Cst"><@spring.message "basicParameter"/></div>
<div class="gjcs"><a href = "javascript:void(0)" id="button-gjcs"><@spring.message "advanceParameters"/></a></div>
<div class=" clear"></div>
<script type="text/javascript" language="javascript"> 
//<!CDATA[ 
function g(o){return document.getElementById(o);} 
function HoverLi(n,m,o){ 
//如果有N个标签,就将i<=N;  
//本功能非常OK,兼容IE7,FF,IE6 
	for(var i=1;i<=o;i++){
		g('tb_'+i+'_'+m).className='normaltab';
		g('tbc_0'+i+'_'+m).className='undis';
		//g('m01_'+i+'_'+m).className='undis';
	}
	g('tb_'+n+'_'+m).className='hovertab'; 
	g('tbc_0'+n+'_'+m).className='dis';
	//g('m01_'+n+'_'+m).className='dis';
} 
//]]> 
</script> 
<div class="w936"> 
<div id="tb_" class="tb_"> 
<ul> 
<li id="tb_1_1" class="hovertab" onclick="x:HoverLi(1,1,2);lonLatQuery();"><@spring.message "longlat"/></li> 
<li id="tb_2_1" class="normaltab" onclick="i:HoverLi(2,1,2);adminAreaQuery();"><@spring.message "region"/></li> 
</ul> 
</div> 
<div class="ctt5"> 
<div class="dis" id="tbc_01_1">
<div class="wdf"><@spring.message "LongitudeLeft"/></div>
<div class="wdinput"><input name="" id="leftuplong" type="text" /></div>
<div class="wdf"><@spring.message "LatitudeLeft"/></div>
<div class="wdinput"><input name="" id="leftuplat" type="text" /></div>
<DIV class="clear"></DIV>
<div class="wdf"><@spring.message "LowerRightLongitude"/></div>
<div class="wdinput"><input name="" id="rightdownlong" type="text" /></div>
<div class="wdf"><@spring.message "LowerRightLatitude"/></div>
<div class="wdinput"><input name="" id="rightdownlat" type="text" /></div>
<div class="clear"></div>

<#--<div class="xzanniu"><a href="#" id="dfm" onclick="changeHMS()" ><@spring.message "Transfer"/></a></div>-->

</div> 
<div class="undis" id="tbc_02_1">
<div class="jcwz"><@spring.message "country"/></div>
<div class="wdinput01">
<select id="country"  onchange="selectCountry(this);"  style="width: 100px;">
									<option>								
									</option>
								</select>
								</div>
<div class="jcwz"><@spring.message "states"/></div>
<div class="wdinput01"><select id="states" onchange="selectState(this);" style="width: 100px;">
									<option>							
									</option>
								</select></div>
<DIV class="clear"></DIV>


</div> 
</div> 
</div>

<div class="clear"></div>
<div class="line"></div>

<div class="jcwz"><@spring.message "resolution"/></div>
<div class="cxtjinput03"><input type="text" id="mingsd"></div>
<div class="cxtjicon03">-</div>
<div class="cxtjinput03"><input type="text" id="maxgsd"></div>
<div class="clear"></div>
<div class="jcwz"><@spring.message "cloudCover"/></div>
<div class="cxtjinput03"> <input id="mincloudCoverage" type="text"></div>
	           			<div class="cxtjicon03"><@spring.message "until"/></div>
<div class="cxtjinput03"> <input id="maxcloudCoverage" type="text"></div>
<div class="clear"></div>
<div class="jcwz"><@spring.message "acquisitionTime"/></div>
<div class="cxtjinput03"> <input type="text" id="sceneStarttime"
					name="sceneStarttime" value="${sceneStarttime}"
					style="width:95px" class="form-control Wdate"
					onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" /></div>
<#-- <div class="cxtjicon03"><img src="imgnew/icon20.png" width="17" height="17" /></div> -->
<div class="cxtjicon03"><@spring.message "until"/></div>
<div class="cxtjinput03"><input
					type="text" id="sceneEndtime" name="sceneEndtime"
					value="${sceneEndtime}" style="width:95px"
					class="form-control Wdate"
					onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" /></div>
<#-- <div class="cxtjicon03"><img src="imgnew/icon20.png" width="17" height="17" /></div> -->
<div class="clear"></div>
<div class="line01"></div>

<div class="jcwz"><@spring.message "level"/></div>
<div class="wdinput01"><select id="productlevel" name="productLevel">
						<option value="LEVEL1">LEVEL1</option>
						<option value="LEVEL2A">LEVEL2A</option>
						<option value="LEVEL2B">LEVEL2B</option>
						<option value="LEVEL3A">LEVEL3A</option>
						<option value="LEVEL3B">LEVEL3B</option>
						<option value="LEVEL4">LEVEL4</option>
						<option value="LEVEL0">LEVEL0</option>
				</select></div>
<div class="clear"></div>
<div class="line"></div>
<div class="jcwz"><@spring.message "satelliteChoose"/></div>
<div class="clear"></div>
<div class="wxxzbox">
<div class="wxf" id="VRSS-1">VRSS-1 （<@spring.message "vrss1msage"/>）</div>
<div class="wxf01"><@spring.message "sensorid"/></div>
<div class="wxfx">
<div class="wxfxk"><input id="PAN-1" name="sensorid" id="PAN-1" type="checkbox" value="VRSS-1_PAN-1" />PAN-1</div>
<div class="wxfxk"><input id="PAN-2" name="sensorid" id="PAN-2" type="checkbox" value="VRSS-1_PAN-2" />PAN-2</div>
<div class="wxfxk"><input id="WMC-1" name="sensorid" id="WMC-1" type="checkbox" value="VRSS-1_WMC-1" />WMC-1</div>
<div class="wxfxk"><input id="WMC-2" name="sensorid" id="WMC-2" type="checkbox" value="VRSS-1_WMC-2" />WMC-2</div>
<div class="wxfxk"><input id="MSS-1" name="sensorid" id="MSS-1" type="checkbox" value="VRSS-1_MSS-1" />MSS-1 </div>
<div class="wxfxk"><input id="MSS-2" name="sensorid" id="MSS-2" type="checkbox" value="VRSS-1_MSS-2" />MSS-2</div>

</div>
<div class="clear"></div>
<div class="line"></div>

<div class="wxf" id="VRSS-2">VRSS-2 （<@spring.message "vrss2msage"/>）</div>
<div class="wxf01"><@spring.message "sensorid"/></div>
<div class="wxfx">
<div class="wxfxk"><input id="PAN" name="sensorid"  type="checkbox" value="VRSS-2_PAN" />PAN</div>
<div class="wxfxk"><input id="MSS" name="sensorid" type="checkbox" value="VRSS-2_MSS" />MSS</div>
<div class="wxfxk"><input id="IRC" name="sensorid" type="checkbox" value="VRSS-2_IRC" />IRC</div>
</div>
<div class="clear"></div>
<div class="line"></div>

<div class="wxf" id="GF-1">GF-1 :（<@spring.message "gf1"/>）</div>
<div class="wxf01"><@spring.message "sensorid"/></div>
<div class="wxfx">
<div class="wxfxk"><input type="checkbox" id="PAN1" name="sensorid" value="GF-1_PAN">PAN </div>
<div class="wxfxk"><input  type="checkbox" id="MSS1" name="sensorid" value="GF-1_MSS">MSS </div>
<div class="wxfxk"><input type="checkbox" id="WMC" name="sensorid" value="GF-1_WMC">WMC</div>
</div>
<div class="clear"></div>
<div class="line"></div>

<div class="wxf" id ='GF-2'>GF-2（<@spring.message "gf2"/>）</div>
<div class="wxf01"><@spring.message "sensorid"/></div>
<div class="wxfx">
<div class="wxfxk"><input  type="checkbox" id="PAN2" name="sensorid" value="GF-2_PAN">PAN</div>
<div class="wxfxk"><input  type="checkbox" id="MSS2" name="sensorid" value="GF-2_MSS">MSS</div>
</div>
<div class="clear"></div>
<div class="line"></div>
</div>



<div class="clear"></div>
<div class="ccanniutc"  onclick = "productSearch()"><a href = "javascript:void(0)"  ><input class="btn blue" type="button" value="<@spring.message 'selectQuery'/>"/></a></div>
</div>

<!--点击高级参数弹出-->
<div id="gjcs" class="white_content01">
<div class="main-im02">
<div id="open_im02" class="open-im02">&nbsp;</div>  
<div class="im_main02" id="im_main02">
<div id="close_im02" class="close-im02"><a href="javascript:void(0);" title="点击关闭"></a></div>
<!--高级参数内容-->
<div class="jcwz"><@spring.message "productTime"/></div>
<div class="cxtjinput03"><input type="text" id="productStarttime"
						name="productStarttime" value="${productStarttime}"
						style="width:95px" class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" /></div>
<#-- <div class="cxtjicon03"><img src="imgnew/icon20.png" width="17" height="17" /></div> -->
<div class="cxtjicon03"><@spring.message "until"/></div>
<div class="cxtjinput03"><input
						type="text" id="productEndtime" name="productEndtime"
						value="${productEndtime}" style="width:95px"
						class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" /></div>
<#-- <div class="cxtjicon03"><img src="imgnew/icon20.png" width="17" height="17" /></div> -->
<div class="clear"></div>
<#-- <div class="jcwz">接收站</div>
<div class="wxfx01">
<div class="wxfxk01"><input name="" type="checkbox" value="" />密云</div>
<div class="wxfxk01"><input name="" type="checkbox" value="" />乌鲁木齐</div>
<div class="wxfxk01"><input name="" type="checkbox" value="" />广州</div>
<div class="wxfxk01"><input name="" type="checkbox" value="" />喀什</div>
<div class="wxfxk01"><input name="" type="checkbox" value="" />牡丹江</div>
<div class="wxfxk01"><input name="" type="checkbox" value="" />三亚</div>
</div> -->
<div class="clear"></div>
<div class="line"></div>
<div class="jcwz"><@spring.message "orbitid"/></div>
<div class="wdinput02"><input id="orbitid" name="orbitid" type="text" /></div>
<div class="clear"></div>
<div class="wdf01">Path：</div>
<div class="cxtjinput03"><input id="minPath" type="text" /></div>
<div class="cxtjicon03">-</div>
<div class="cxtjinput03"><input id="maxPath"  type="text" /></div>
<div class="clear"></div>
<div class="wdf01">Pow：</div>
<div class="cxtjinput03"><input id="minRow" type="text" /></div>
<div class="cxtjicon03">-</div>
<div class="cxtjinput03"><input id="maxRow" type="text" /></div>
<div class="clear"></div>
<div class="line"></div>

<div class="jcwz"><@spring.message "dataQuality"/></div>
<div class="wdinput01"><select id="quality" name="quality">
									<option value="0">0</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
								  </select></div>
<div class="clear"></div>
<#-- <div class="jcwz">过滤</div>
<div class="wdinput01"><select name=""></select></div>
<div class="clear"></div> -->

<div class="jcwz"><@spring.message "childPath"/></div>
<div class="cxtjinput03"><input id="scenepath" name="scenePath" type="text" /></div>
<div class="clear"></div>
<div class="jcwz"><@spring.message "childPow"/></div>
<div class="cxtjinput03"><input id="scenerow" name="sceneRow" type="text" /></div>
<div class="clear"></div>
<div class="line"></div>

<div class="w936"> 
<div id="tb_" class="tb_1"> 
<div class="jcwz1"><@spring.message "shp"/></div>
</div> 
<div class="ctt5"> 
<div class="dis" id="tbc_01_2">

<div class="wdf"><@spring.message "fileName"/></div>
<div ><input id="shpid"  type="text" size="7" style="width: 100px;"><input type="file" id="shpfile" name ="file"></div>
<div class="wdf" ><input class="btn blue" type="button" value="<@spring.message 'imp'/>" onclick="importShapeFiles()"/></div>
<div class="clear"></div>
</div> 
</div> 
</div>


<div class="clear"></div>
<div class="w936" style="margin-top:20px;"> 
<div id="tb_" class="tc_"> 
<ul> 
<li id="tb_1_3" class="hovertab" onclick="x:HoverLi(1,3,2);"><@spring.message "productNumber"/></li> 
<li id="tb_2_3" class="normaltab" onclick="i:HoverLi(2,3,2);"><@spring.message "sceneNumber"/></li> 
</ul> 
</div> 
<div class="ctt5"> 
<div class="dis" id="tbc_01_3">
<div class="wdf"><@spring.message "fileName"/></div>
<div class="wdinput02"><input  type="file" id="myfile" /></div>
<div class="wdf" ><input class="btn blue" type="button" value="<@spring.message 'imp'/>" onclick="readProductidFile()"/></div>
<div class="clear"></div>
<div class="wdf"><@spring.message "productNumber"/></div>
<div class="wdinput03"><input id="productidStrs" name="productStrs" type="text" /></div>

</div> 
<div class="undis" id="tbc_02_3">
<div class="wdf"><@spring.message "fileName"/></div>
<div class="wdinput02"><input type="file" id="myfiles" /></div>
<div class="wdf" ><img src="imgnew/icon26.gif" width="72" height="30" onclick="readSceneidFile()"/></div>
<div class="clear"></div>
<div class="wdf"><@spring.message "sceneNumber"/></div>
<div class="wdinput03"><input id="sceneidStrs" name="sceneidStrs" type="text" /></div>

</div> 
</div> 
</div>
</div>
</div>
</div>
<!--结束-->
</div>
</div>

<div id="jqmetercontainer" class="claro" style="text-align:center; display:none;">
		<div style="text-align:center;color:black;font-size:20;margin-top:20%">...</div>
		<img width="50" alt="" src="imgnew/gdt1.gif" />
</div>
 <div id="map" class="claro" style="width:100%; min-width:1140px; height:827px; position:relative; margin-top:20px;" frameborder="0"></div>

<!--点击查询弹出-->
<div id="light" class="white_content">
<div class="main-im01">
<div id="open_im01" class="open-im01">&nbsp;</div>  
<div class="im_main01" id="im_main01" style="overflow: scroll;"></div>  
<div id="close_im01" class="close-im01"><a href="javascript:void(0);" title="点击关闭"></a></div>

<!--查询内容-->
<#-- <div class="cxtb">
<div class="cxdg"><img src="imgnew/icon27.gif" width="86" height="31" /></div>
<div class="cxmore"><a href="#">更多</a></div>
</div>

<table width="320" border="0"class="cxtable">
  <tr class="cxtr">
    <td bgcolor="#1d53a1" align="center" ><input name="" type="checkbox" value="" /></td>
    <td bgcolor="#1d53a1" align="center" >操作</td>
    <td bgcolor="#1d53a1" align="center" >卫星</td>
    <td bgcolor="#1d53a1" align="center" >传感器</td>
    <td bgcolor="#1d53a1" align="center" >采集时间</td>
    <td bgcolor="#1d53a1" align="center" >云量</td>
  </tr>
  <tr>
    <td align="center"><input name="" type="checkbox" value="" /></td>
    <td><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td align="center">gf1</td>
    <td align="center">pms1</td>
    <td align="center">2016-06-10</td>
    <td align="center">0%</td>
  </tr>
  <tr>
    <td bgcolor="#f3f3f3" align="center"><input name="" type="checkbox" value="" /></td>
    <td bgcolor="#f3f3f3"><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td bgcolor="#f3f3f3" align="center">gf1</td>
    <td bgcolor="#f3f3f3" align="center">pms1</td>
    <td bgcolor="#f3f3f3" align="center">2016-06-10</td>
    <td bgcolor="#f3f3f3" align="center">0%</td>
  </tr>
  <tr>
    <td align="center"><input name="" type="checkbox" value="" /></td>
    <td><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td align="center">gf1</td>
    <td align="center">pms1</td>
    <td align="center">2016-06-10</td>
    <td align="center">0%</td>
  </tr>
  <tr>
    <td bgcolor="#f3f3f3" align="center"><input name="" type="checkbox" value="" /></td>
    <td bgcolor="#f3f3f3"><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td bgcolor="#f3f3f3" align="center">gf1</td>
    <td bgcolor="#f3f3f3" align="center">pms1</td>
    <td bgcolor="#f3f3f3" align="center">2016-06-10</td>
    <td bgcolor="#f3f3f3" align="center">0%</td>
  </tr>
  <tr>
    <td align="center"><input name="" type="checkbox" value="" /></td>
    <td><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td align="center">gf1</td>
    <td align="center">pms1</td>
    <td align="center">2016-06-10</td>
    <td align="center">0%</td>
  </tr>
  <tr>
    <td bgcolor="#f3f3f3" align="center"><input name="" type="checkbox" value="" /></td>
    <td bgcolor="#f3f3f3"><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td bgcolor="#f3f3f3" align="center">gf1</td>
    <td bgcolor="#f3f3f3" align="center">pms1</td>
    <td bgcolor="#f3f3f3" align="center">2016-06-10</td>
    <td bgcolor="#f3f3f3" align="center">0%</td>
  </tr>
  <tr>
    <td align="center"><input name="" type="checkbox" value="" /></td>
    <td><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td align="center">gf1</td>
    <td align="center">pms1</td>
    <td align="center">2016-06-10</td>
    <td align="center">0%</td>
  </tr>
  <tr>
    <td bgcolor="#f3f3f3" align="center"><input name="" type="checkbox" value="" /></td>
    <td bgcolor="#f3f3f3"><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td bgcolor="#f3f3f3" align="center">gf1</td>
    <td bgcolor="#f3f3f3" align="center">pms1</td>
    <td bgcolor="#f3f3f3" align="center">2016-06-10</td>
    <td bgcolor="#f3f3f3" align="center">0%</td>
  </tr>
  <tr>
    <td align="center"><input name="" type="checkbox" value="" /></td>
    <td><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td align="center">gf1</td>
    <td align="center">pms1</td>
    <td align="center">2016-06-10</td>
    <td align="center">0%</td>
  </tr>
  <tr>
    <td bgcolor="#f3f3f3" align="center"><input name="" type="checkbox" value="" /></td>
    <td bgcolor="#f3f3f3"><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td bgcolor="#f3f3f3" align="center">gf1</td>
    <td bgcolor="#f3f3f3" align="center">pms1</td>
    <td bgcolor="#f3f3f3" align="center">2016-06-10</td>
    <td bgcolor="#f3f3f3" align="center">0%</td>
  </tr>
  <tr>
    <td align="center"><input name="" type="checkbox" value="" /></td>
    <td><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td align="center">gf1</td>
    <td align="center">pms1</td>
    <td align="center">2016-06-10</td>
    <td align="center">0%</td>
  </tr>
  <tr>
    <td bgcolor="#f3f3f3" align="center"><input name="" type="checkbox" value="" /></td>
    <td bgcolor="#f3f3f3"><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td bgcolor="#f3f3f3" align="center">gf1</td>
    <td bgcolor="#f3f3f3" align="center">pms1</td>
    <td bgcolor="#f3f3f3" align="center">2016-06-10</td>
    <td bgcolor="#f3f3f3" align="center">0%</td>
  </tr>
  <tr>
    <td align="center"><input name="" type="checkbox" value="" /></td>
    <td><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td align="center">gf1</td>
    <td align="center">pms1</td>
    <td align="center">2016-06-10</td>
    <td align="center">0%</td>
  </tr>
  <tr>
    <td bgcolor="#f3f3f3" align="center"><input name="" type="checkbox" value="" /></td>
    <td bgcolor="#f3f3f3"><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td bgcolor="#f3f3f3" align="center">gf1</td>
    <td bgcolor="#f3f3f3" align="center">pms1</td>
    <td bgcolor="#f3f3f3" align="center">2016-06-10</td>
    <td bgcolor="#f3f3f3" align="center">0%</td>
  </tr>
  <tr>
    <td align="center"><input name="" type="checkbox" value="" /></td>
    <td><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td align="center">gf1</td>
    <td align="center">pms1</td>
    <td align="center">2016-06-10</td>
    <td align="center">0%</td>
  </tr>
  <tr>
    <td bgcolor="#f3f3f3" align="center"><input name="" type="checkbox" value="" /></td>
    <td bgcolor="#f3f3f3"><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td bgcolor="#f3f3f3" align="center">gf1</td>
    <td bgcolor="#f3f3f3" align="center">pms1</td>
    <td bgcolor="#f3f3f3" align="center">2016-06-10</td>
    <td bgcolor="#f3f3f3" align="center">0%</td>
  </tr>
  <tr>
    <td align="center"><input name="" type="checkbox" value="" /></td>
    <td><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td align="center">gf1</td>
    <td align="center">pms1</td>
    <td align="center">2016-06-10</td>
    <td align="center">0%</td>
  </tr>
  <tr>
    <td bgcolor="#f3f3f3" align="center"><input name="" type="checkbox" value="" /></td>
    <td bgcolor="#f3f3f3"><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td bgcolor="#f3f3f3" align="center">gf1</td>
    <td bgcolor="#f3f3f3" align="center">pms1</td>
    <td bgcolor="#f3f3f3" align="center">2016-06-10</td>
    <td bgcolor="#f3f3f3" align="center">0%</td>
  </tr>
  <tr>
    <td align="center"><input name="" type="checkbox" value="" /></td>
    <td><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td align="center">gf1</td>
    <td align="center">pms1</td>
    <td align="center">2016-06-10</td>
    <td align="center">0%</td>
  </tr>
  <tr>
    <td bgcolor="#f3f3f3" align="center"><input name="" type="checkbox" value="" /></td>
    <td bgcolor="#f3f3f3"><div class="cximg"><img src="imgnew/img09.gif" width="25" height="19" /></div>
    <div class="cximg"><img src="imgnew/icon38.png" width="17" height="12" /></div>
    </td>
    <td bgcolor="#f3f3f3" align="center">gf1</td>
    <td bgcolor="#f3f3f3" align="center">pms1</td>
    <td bgcolor="#f3f3f3" align="center">2016-06-10</td>
    <td bgcolor="#f3f3f3" align="center">0%</td>
  </tr>
</table> 

-->

<#-- <div class="page01">
<a href="#">上页</a>
<a href="#">1</a>
<a href="#">2</a>
<a href="#">3</a>
<a href="#">…</a>
<a href="#">15</a>
<a href="#">下页</a>
</div>

<div class="xcps">
<span>返回景数：0</span><br />
总景数：0
</div> -->



</div>
</div>
</div>
</div>

<div id="showgcp" style="display: none;">
	是否GCP过滤：<input type="radio" value="0" name="gcp"  checked="checked">是<input type="radio" value="1" name="gcp">否
	</br>
	<input type="button" value="定制" onclick="addshopcarscens()">
</div>
<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script src="js/constants.js" type="text/javascript"></script>
<!-- 
<%--<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>--%>
<%--<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>--%>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
 -->
<script src="js/common/jquery.min.js" type="text/javascript"></script>
<script src="js/common/ajaxfileupload.js" type="text/javascript"></script>

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>
<script type="text/javascript">
jQuery(function() {
	dojo.addOnLoad(init);
});

</script>