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
		<form  action="" method="POST" id="addSpecial">
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
		    <div id="productType">
		        <div  class="jcwz"><@spring.message "topicProductType"/></div>
		        <div class="wdinput01"><select id="topicProductType" onChange="changeProductType();">
		        	<option value="atmosphere"><@spring.message "atmosphere"/></option>
		        	<option value="water"><@spring.message "water"/></option>
		        	<option value="environment"><@spring.message "environment"/></option>
		        	<option value="damage"><@spring.message "damage"/></option>
		        </select></div>
		        <div class="clear"></div>
		        <div id="atmosphere">
		        <input name="producttype" type="radio" id="aerosol" value="TAS_TPPS_AODP" /><@spring.message "aerosol"/>
		       <input name="producttype" type="radio" id="sandstorm" value="TAS_TPPS_SMP" /><@spring.message "sandstorm"/>
                <input name="producttype" type="radio" id="visibility" value="TAS_TPPS_VAP" /><@spring.message "visibility"/>
                </div>
                <div id="water" style="display:none">
                <div>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_WEP" /><@spring.message "waterExtract"/>
		        <input name="producttype" type="radio" id="aa" value="TAS_TPPS_WRSRP" /><@spring.message "waterReflect"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_CAMP" /><@spring.message "chlorophyl"/>
                </div>
                <div>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_WSMMP" /><@spring.message "suspend"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_WTAP" /><@spring.message "temperature"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_WSTP" /><@spring.message "eutrophy"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_WEMP" /><@spring.message "pollution"/>
                </div></div>
                <div id="environment" style="display:none">
                <div>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_WTPIP" /><@spring.message "vegetation"/>
		        <input name="producttype" type="radio" id="aa" value="TAS_TPPS_SRP" /><@spring.message "surface"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_EVIP" /><@spring.message "vegetationEnhance"/>
               </div>
               <div>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_PRIP" /><@spring.message "physiologicalPlant"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_VCMP" /><@spring.message "coverage"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_LAIP" /><@spring.message "LAI"/>
                </div>
                <div>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_LEUCP" /><@spring.message "land_ecological"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_SEMP" /><@spring.message "surface_emissivity"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_SRTMP" /><@spring.message "surface_temperature"/>
               </div>
               <div>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_SBTMP" /><@spring.message "temperature_value"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_UHIMP" /><@spring.message "heat_island"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_CAMP" /><@spring.message "coastline"/>
                </div></div>
                <div id="damage" style="display:none">
                <div>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_FSAP" /><@spring.message "firePoint"/>
		        <input name="producttype" type="radio" id="aa" value="TAS_TPPS_BAEP" /><@spring.message "fireArea"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_FAMP" /><@spring.message "floodArea"/>
                </div>
                <div>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_FDMP" /><@spring.message "floodDepth"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_DFTMP" /><@spring.message "mudslidesRoute"/>
                <input name="producttype" type="radio" id="aa" value="TAS_TPPS_DFRMP" /><@spring.message "mudslidesArea"/>
                </div></div>
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
				
						<input id="PAN-1" type="radio" name="sensorid" value="VRSS-1_PAN-1">PAN-1 
						<input 	id="PAN-2" type="radio" name="sensorid" value="VRSS-1_PAN-2">PAN-2 
						<input id="WMC-1" type="radio" name="sensorid" value="VRSS-1_WMC-1">WMC-1
					
				
						<input id="MSS-1" type="radio" name="sensorid" value="VRSS-1_MSS-1">MSS-1 
						<input id="MSS-2" type="radio" name="sensorid" value="VRSS-1_MSS-2">MSS-2 
						<input id="WMC-2" type="radio" name="sensorid" value="VRSS-1_WMC-2">WMC-2
					<div class="clear"></div>
					<div class="line"></div>
					<div class="wxf" id="GF-1">VRSS-2 :</div>
				
						<input id="PAN" type="radio" name="sensorid" value=">VRSS-2_PAN">PAN 
						<input	id="MSS" type="radio" name="sensorid" value="VRSS-2_MSS">MSS
						<input id="IRC" type="radio" name="sensorid" value="VRSS-2_IRC">IRC
					<div class="clear"></div>
					<div class="line"></div>
					<div class="wxf" id="GF-1">GF-1 :</div>
				
						<input id="PAN" type="radio" name="sensorid" value=">GF-1_PAN">PAN 
						<input	id="MSS" type="radio" name="sensorid" value="GF-1_MSS">MSS
						<input id="WMC" type="radio" name="sensorid" value="GF-1_WMC">WMC
					<div class="clear"></div>
					<div class="line"></div>
					<div class="wxf" id="GF-2">GF-2 :</div>
				
						<input id="PAN" type="radio" name="sensorid" value="GF-2_PAN">PAN 
						<input id="MSS" type="radio" name="sensorid" value="GF-2_MSS">MSS
					
		
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
					onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" /></div>
					<div class="cxtjicon03"><@spring.message "until"/></div>
<div class="cxtjinput03"><input
					type="text" id="imagingstoptime" name="imagingstoptime"
					value="${imagingstoptime}" style="width:95px"
					class="form-control Wdate"
					onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" /></div>
			<div class="w936"> 
			<div class="jcwz"><@spring.message "Remark"/></div>
			<textarea name="MSG" cols="50" rows=3 style="width: 279px; height: 75px;"></textarea>
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
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" />— <input
						type="text" id="sceneStarttime" name="sceneStarttime"
						value="${sceneStarttime}" style="width:85px"
						class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" /></td>
				</tr>
				<tr>
					<td><@spring.message "productTime"/> <input type="text" id="productStarttime"
						name="productStarttime" value="${productStarttime}"
						style="width:85px" class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" />— <input
						type="text" id="productEndtime" name="productEndtime"
						value="${productEndtime}" style="width:85px"
						class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" /></td>
				</tr>
				</tbody>
				</table> -->
				<div class="clear"></div>
<div class="ccanniutc"><input class="btn blue" type="button" value="<@spring.message 'save'/>"  onclick = "topicProductsave()"/></div>
				
	     
	</form>
</div>
</div>
</div>
</div>
</div>

 <div id="map" class="claro" style="width:100%; min-width:1140px; height:827px; position:relative; margin-top:20px;" frameborder="0"></div>






<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script src="js/constants.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script src="js/common/jquery.min.js" type="text/javascript"></script>
<script src="js/common/ajaxfileupload.js" type="text/javascript"></script>
<script src="js/service/product.js" type="text/javascript"></script>
<script src="js/service/topicproductadd.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>


<script type="text/javascript">
jQuery(function() {
	dojo.addOnLoad(topitMapInit);
});

</script>

