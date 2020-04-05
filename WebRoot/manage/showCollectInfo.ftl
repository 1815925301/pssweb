<#include "top.ftl">
<#include "iframetop.ftl">
<!-- BEGIN PAGE CONTAINER-->

<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/claro/claro.css">
	
<script src="${gisurl}/arcgis_js_api/library/2.7/jsapi/" type="text/javascript"></script>
<script src="js/constants.js" type="text/javascript"></script>
<script src="js/map/map_init.js" type="text/javascript"></script>
<script src="js/map/image.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
});
 jQuery(function() {
	dojo.addOnLoad(collectMapInit);
});

</script>
<div class="Sonwidth">
	<div class=" clear"></div>
	<div class="xw_w">
	<div class="xw_bor">

	<div  class="xw_mbx"><@spring.message "CurrentPosition"/>：<a href="./home.do"><@spring.message "HomePage"/></a>：<a href="./pssCollectInfomanage.do"><@spring.message "Collection"/></a>：<a href="./showCollectInfo.do?id=${pssCollectInfo.id}"><@spring.message "CollectionDetails"/></a></div>
</div>

    <div>
		<a href="javascript:pan_method();"><img
				src="img/map/nav_pan.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:zoomin_method();"><img
				src="img/map/nav_zoomin.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:zoomout_method();"><img
				src="img/map/nav_zoomout.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:zoomtofull_method();"><img
				src="img/map/nav_fullextent.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:rectangle_method();"><img
				src="img/map/polygn_up.png" /> </a>
		<a href="javascript:circle_method();"><img
				src="img/map/polygn_yuan.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:polygon_method();"><img
				src="img/map/polygn.png" /> </a>
		<a href="javascript:void(0);" onclick="javascript:deactivate_method();"><img
				src="img/map/delete_up.png" /> </a>
    </div>
    <div id="collectMap" class="claro" style="height: 50%;">
	</div>
    <table border="1" id="table">
							<tr align="center" name="bt"  class="cxtr" bgcolor="#1d53a1" align="center">
								<td><@spring.message "leftuplong"/></td>
								<td><@spring.message "leftuplat"/></td>
								<td><@spring.message "rightdownlong"/></td>
								<td><@spring.message "rightdownlat"/></td>
								<td><@spring.message "rightuplong"/></td>
								<td><@spring.message "rightuplat"/></td>
								<td><@spring.message "leftdownlong"/></td>
								<td><@spring.message "leftdownlat"/></td>
								
								<td><@spring.message "satellite"/></td>
								<td><@spring.message "begintime"/></td>
								<td><@spring.message "endtime"/></td>
								<td><@spring.message "productlevel"/></td>
								<td><@spring.message "sensorid"/></td>
								<td><@spring.message "cloudcover"/></td>
								<td><@spring.message "sideangle"/></td>
								<td><@spring.message "earthsurfacename"/></td>
							</tr>
							<tr align="center">
							 
								<td id="upperleftlong">${pssCollectInfo.upperleftlong}</td>
								<td id="upperleftlat">${pssCollectInfo.upperleftlat}</td>
								<td id="lowerrightlong">${pssCollectInfo.lowerrightlong}</td>
								<td id="lowerrightlat">${pssCollectInfo.lowerrightlat}</td>
								
								<td id="upperrightlong">${pssCollectInfo.upperrightlong}</td>
								<td id="upperrightlat">${pssCollectInfo.upperrightlat}</td>
								<td id="lowerleftlong">${pssCollectInfo.lowerleftlong}</td>
								<td id="lowerleftlat">${pssCollectInfo.lowerleftlat}</td>
								 
								<td>${pssCollectInfo.satelliteid}</td>
								<td>${pssCollectInfo.begintime}</td>
								<td>${pssCollectInfo.endtime}</td>
								<td>${pssCollectInfo.productlevel}</td>
								<td>${pssCollectInfo.sensorid}</td>
								<td>${pssCollectInfo.cloudcover}</td>
								<td>${pssCollectInfo.sideangle}</td>
								<td>${pssCollectInfo.earthsurfacename}</td>
							</tr>
						</table>

					
 </div>

<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>

<input type="hidden" id="upperleftlon"  value = "${pssCollectInfo.upperleftlong}" />
<input type="hidden" id="upperleftlatitude"  value = "${pssCollectInfo.upperleftlat}" />
<input type="hidden" id="lowerrightlon" value = "${pssCollectInfo.lowerrightlong}" />
<input type="hidden" id="lowerrightlatitude" value = "${pssCollectInfo.lowerrightlat}" />





