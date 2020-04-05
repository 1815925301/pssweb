<#include "top.ftl">

<#--  <#include "iframetop.ftl">  -->
<!-- BEGIN PAGE CONTAINER-->

<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/tundra/tundra.css">
<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/claro/claro.css">
<link href="cssnew/style.css" rel="stylesheet">	

<script src="${gisurl}/arcgis_js_api/library/2.7/jsapi/" type="text/javascript"></script>
<script src="js/map/map_init.js" type="text/javascript"></script>
<script src="js/map/image.js" type="text/javascript"></script>

<div style="float: right; ">
	<div class="main-im">
		<div>
			<a href="javascript:pan_method();"><img src="img/map/nav_pan.png" />
			</a> <a href="javascript:void(0);" onclick="javascript:zoomin_method();"><img
				src="img/map/nav_zoomin.png" /> </a> <a href="javascript:void(0);"
				onclick="javascript:zoomout_method();"><img
				src="img/map/nav_zoomout.png" /> </a> <a href="javascript:void(0);"
				onclick="javascript:zoomtofull_method();"><img
				src="img/map/nav_fullextent.png" /> </a> <a href="javascript:void(0);"
				onclick="javascript:rectangle_method();"><img
				src="img/map/polygn_up.png" /> </a> <a
				href="javascript:circle_method();"><img
				src="img/map/polygn_yuan.png" /> </a> <a href="javascript:void(0);"
				onclick="javascript:polygon_method();"><img
				src="img/map/polygn.png" /> </a> <a href="javascript:void(0);"
				onclick="javascript:deactivate_method();"><img
				src="img/map/delete_up.png" /> </a>
		</div>
		<div id="open_im" class="open-im">&nbsp;</div>
		<div class="im_main" id="im_main">
			<div id="close_im" class="close-im">
				<a href="javascript:void(0);" title="点击关闭">&nbsp;</a>
			</div>
			<div class="Cst"><@spring.message "basicParameter"/></div>
		
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
 

	
	<div id="productquery">
		<form  action="${ctx}${currentPageUrl}" method="GET">
		<div class="dis" id="tbc_01_1">
						<div class="wdf"><@spring.message "LongitudeLeft"/></div>
						<div class="wdinput">
							<input name="" id="leftuplong" type="text" />
						</div>
						<div class="wdf"><@spring.message "LatitudeLeft"/></div>
						<div class="wdinput">
							<input name="" id="leftuplat" type="text" />
						</div>
						<DIV class="clear"></DIV>
						<div class="wdf"><@spring.message "LowerRightLongitude"/></div>
						<div class="wdinput">
							<input name="" id="rightdownlong" type="text" />
						</div>
						<div class="wdf"><@spring.message "LowerRightLatitude"/></div>
						<div class="wdinput">
							<input name="" id="rightdownlat" type="text" />
						</div>
		    
		        <div class="clear"></div>
		        <div style="margin-bottom:20px">
			<input id="cc" class="easyui-combotree" data-options="url:'${ctx}/getcombotree.do',method:'get',label:'ProductType:',labelPosition:'top',multiple:true" style="width:100%">
		</div>
               
		    </div>
		
		<div id="centerPointQuery" class="biao_tab">
			<table cellspacing="4" cellpadding="0" border="0">
				<tbody>
				<#--
				    <tr style="height: 30px"><td><span style="font-size: 12pt"><@spring.message "centerPoint"/></span></td></tr>
					<tr>
						<td><@spring.message "centerLong"/></td>
						<td><input id="leftuplong" type="text" size="5"
							style="width: 80px">
						</td>
						<td><@spring.message "centerlat"/></td>
						<td><input id="leftuplat" type="text" size="5"
							style="width: 80px">
						</td>
					</tr>-->
					
					<tr style="display:none">
						<td>右下经度 :</td>
						<td><input id="rightdownlong" type="text" size="5"
							style="width: 80px"></td>
						<td>右下纬度 :</td>
						<td><input id="rightdownlat" type="text" size="5"
							style="width: 80px"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="dataShow"></div>
		<div class="clear"></div>
		<div id="adminAreaQuery" class="biao_tab">
			
			  
			 <div><@spring.message "region"/></div>
			   
				<div class="jcwz"><@spring.message "country"/></div>
				<div class="wdinput01"><select id="country"  style="width: 90px" onchange="selectCountry(this);">
									<option>
									
									</option>
								</select></div>
				
			 	<div class="jcwz"><@spring.message "states"/></div>
			 	
			 	<div class="wdinput01"><select id="states" onchange="selectState(this);" style="width: 100px">
									<option>
										
									</option>
								</select></div>
		</div>
		<div class="clear"></div>
		<div class="line"></div>
			<div class="jcwz"><@spring.message "satellite_sensorid"/></div>
			<div class="clear"></div>
		<div class="wxxzbox">
		
					<div class="wxf" id="VRSS-1">VRSS-1 :</div>
				
						<input id="PAN-1" type="checkbox" name="sensorid" id="PAN-1" value="VRSS-1_PAN-1">PAN-1 
						<input 	id="PAN-2" type="checkbox" name="sensorid" id="PAN-2 " value="VRSS-1_PAN-2">PAN-2 
						<input id="WMC-1" type="checkbox" name="sensorid" id="WMC-1" value="VRSS-1_WMC-1">WMC-1
					
				
						<input id="MSS-1" type="checkbox" name="sensorid" id="MSS-1" value="VRSS-1_MSS-1">MSS-1 
						<input id="MSS-2" type="checkbox" name="sensorid" id="MSS-2" value="VRSS-1_MSS-2">MSS-2 
						<input id="WMC-2" type="checkbox" name="sensorid" id="WMC-2" value="VRSS-1_WMC-2">WMC-2
					<div class="clear"></div>
					<div class="line"></div>
					<div class="wxf" id="GF-1">VRSS-2 :</div>
				
						<input  type="checkbox" name="sensorid" id="PANNs" value="VRSS-2_PAN">PAN 
						<input	 type="checkbox" name="sensorid" id="MSSSs" value="VRSS-2_MSS">MSS
						<input  type="checkbox" name="sensorid" id="IRCCs" value="VRSS-2_IRC">IRC
					<div class="clear"></div>
					<div class="line"></div>
					<div class="wxf" id="GF-1">GF-1 :</div>
				
						<input  type="checkbox" name="sensorid" id="PAN" value=">GF-1_PAN">PAN 
						<input	 type="checkbox" name="sensorid" id="MSS" value="GF-1_MSS">MSS
						<input  type="checkbox" name="sensorid" id="WMC" value="GF-1_WMC">WMC
					<div class="clear"></div>
					<div class="line"></div>
					<div class="wxf" id="GF-2">GF-2 :</div>
				
						<input  type="checkbox" name="sensorid" id="PPAN" value="GF-2_PAN">PAN 
						<input type="checkbox" name="sensorid" id="MMSS" value="GF-2_MSS">MSS
					
		
		</div>
		
		<div class="clear"></div>
		<div class="line"></div>
		<div class="jcwz"><@spring.message "resolution"/></div>
		<div class="cxtjinput03"><input type="text" id="mingsd"></div>
		<div class="cxtjicon03">-</div>
		<div class="cxtjinput03"><input type="text" id="maxgsd"></div>
		<div class="jcwz"><@spring.message "imageTime"/></div>
<div class="cxtjinput03"> <input type="text" id="imagingstarttime"
					name="imagingstarttime" value="${imagingstarttime}"
					style="width:95px" class="form-control Wdate"
					onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" /></div>
					<div class="cxtjicon03"><@spring.message "until"/></div>
<div class="cxtjinput03"><input
					type="text" id="imagingstoptime" name="imagingstoptime"
					value="${imagingstoptime}" style="width:95px"
					class="form-control Wdate"
					onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" /></div>
			<div class="w936"> 
			<div id="tb_" class="tb_1"> 
			<div class="jcwz1"><@spring.message "shp"/></div>
			</div>
			<div class="ctt5"> 
			<div class="dis" id="tbc_01_2">
		
					<@spring.message "fileName"/>
					<div><input id="shpid" name="importShp" type="text" size="7" style="width: 100px"> 
					<input type="file" id="shpfile" name ="file" style="width: 150px" ></div>
					<input class="btn blue" type="button" value="<@spring.message 'imp'/>" onclick="importShapeFiles()"/>
					<div class="clear"></div>
					</div>
					</div>
					</div>
					
	<#--
	 <div class="biao_tab">
			<span> <font> <@spring.message "time"/></font> </span>
		</div>
		<table cellspacing="4" cellpadding="0" border="0">
			<tbody>
			    <tr>
					<td><@spring.message "collectTime"/> <input type="text" id="sceneStarttime"
						name="sceneStarttime" value="${sceneStarttime}"
						style="width:85px" class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" />— <input
						type="text" id="sceneStarttime" name="sceneStarttime"
						value="${sceneStarttime}" style="width:85px"
						class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" /></td>
				</tr>
				<tr>
					<td><@spring.message "productTime"/> <input type="text" id="productStarttime"
						name="productStarttime" value="${productStarttime}"
						style="width:85px" class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" />— <input
						type="text" id="productEndtime" name="productEndtime"
						value="${productEndtime}" style="width:85px"
						class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" /></td>
				</tr>
				</tbody>
				</table> -->
				<div class="clear"></div>
<div class="ccanniutc"><a  href = "javascript:void(0)" id="button-cx" ><input class="btn blue" type="button" value="<@spring.message 'selectQuery'/>"  onclick = "topicProductSearch()"/></a></div>
				
	     
	</form>
</div>
</div>
</div>
</div>
</div>
<div id="jqmetercontainer" class="claro" style="text-align:center; display:none;">
		<div style="text-align:center;color:black;font-size:20;margin-top:20%">请稍等...</div>
		<img width="50" alt="" src="imgnew/gdt1.gif" />
</div>
 <div id="map" class="claro" style="width:100%; min-width:1140px; height:827px; position:relative; margin-top:20px;" frameborder="0"></div>
<input type="hidden" value="" name="selectdata" id="selectdata"></input>
<!--点击查询弹出-->
<div id="light" class="white_content">
<div class="main-im01">

<div id="open_im01" class="open-im01">&nbsp;</div>  
<div class="im_main01" id="im_main01"></div>  
<div id="close_im01" class="close-im01"><a href="javascript:void(0);" title="点击关闭"></a></div>





<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script src="js/constants.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script src="js/common/jquery.min.js" type="text/javascript"></script>
<script src="js/common/ajaxfileupload.js" type="text/javascript"></script>
<script src="js/service/product.js" type="text/javascript"></script>
<script src="js/service/topicProductSearch.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>
<link href="easyui/css/easyui.css" rel="stylesheet">
<script type="text/javascript" src="easyui/js/jquery.min.js"></script>
<script type="text/javascript" src="easyui/js/jquery.easyui.min.js"></script>

<script type="text/javascript">
jQuery(function() {
	dojo.addOnLoad(topitMapInit);
});
</script>

