<#include "top.ftl">
<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/tundra/tundra.css">
<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/claro/claro.css">
<link rel="stylesheet" type="text/css"
	href="css/table.css">
	
<script src="${gisurl}/arcgis_js_api/library/2.7/jsapi/" type="text/javascript"></script>
<script src="js/map/map_init.js" type="text/javascript"></script>

<div class="Sonwidth">
<div class="xw_w">
<div class="xw_bor">

<div  class="xw_mbx"><@spring.message "CurrentPosition"/>：<a href="./home.do"><@spring.message "HomePage"/></a>：<a href="./product.do"><@spring.message "datasearch"/></a>:<a href="./showProduct.do?productid=${product.productid }&productlevel=${product.productlevel }"><@spring.message "queryDetails"/></a></div>
</div>
<div class="">
	<font size="4"><@spring.message "Detailed_information_presentation"/></font>
	
</div>

<div class="clear"></div>


	<table width="1099" border="0" class="cp_table">
	
		<tr>
			<td width="183"  height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "satellite"/></B></td>
			<td width="183" height="45" align="center">${product.satelliteid }</td>
			
			
			<td width="183"  height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "sensorid"/></B></td>
			<td width="183" height="45" align="center">${product.sensorid }</td>
			
			<td width="183"  height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "sceneserialno"/></B></td>
			<td width="183" height="45" align="center">${product.sceneserialno}</td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "ifgcpexist"/></B></td>
			<td width="183" id="leftuplonglat" height="45" align="center">${product.ifgcpexist}</td>
			</tr>
			<tr>

			
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "imageingstarttime"/></B></td>
			<td width="183" id="rightdownlonglat" height="45" align="center">${product.imageingstarttime}</td>
			
			
			<td width="145" align="center" bgcolor="#e0ebfd"><B><@spring.message "imageingstoptime"/></B></td>
			<td width="183" id="rightuplonglat"  height="45" align="center">${product.imageingstoptime}</td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "bands"/></B></td>
			<td width="183" height="45" align="center">${product.bands}</td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "instrumentmode"/></B></td>
			<td width="183" id="leftdownlonglat" height="45" align="center">${product.instrumentmode}</td>
			
			
			</tr>
			<tr>
			
			
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "scenestartline"/></B></td>
			<td width="183" height="45" align="center">${product.scenestartline}</td>
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "scenestopline"/></B></td>
			<td width="183" height="45" align="center">${product.scenestopline}</td>
			
			
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "gain"/></B></td>
			<td width="183" height="45" align="center">${product.gain}</td>
			
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "integratelevel"/></B></td>
			<td width="183" height="45" align="center">${product.integratelevel}</td>
			</tr>
			
			<tr>
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "satpath"/></B></td>
			<td width="183" height="45" align="center">${product.satpath }</td>
			
			
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "satrow"/></B></td>
			<td width="183" height="45" align="center">${product.satrow}</td>
			
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "satpathbias"/></B></td>
			<td width="183" height="45" align="center">${product.satpathbias }</td>
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "satrowbias"/></B></td>
			<td width="183" height="45" align="center">${product.satrowbias }</td>
			</tr>
	
			<tr>
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "direction"/></B></td>
			<td width="183" height="45" align="center">${product.direction }</td>
			
			
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "scenepath"/></B></td>
			<td width="183" height="45" align="center">${product.scenepath }</td>
			
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "scenerow"/></B></td>
			<td width="183" height="45" align="center">${product.scenerow }</td>
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "scenepathbias"/></B></td>
			<td width="183" height="45" align="center">${product.scenepathbias }</td>
			</tr>
			
			<tr>
			
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "scenerowbias"/></B></td>
			<td width="183" height="45" align="center">${product.scenerowbias}</td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "satoffnadir"/></B></td>
			<td width="183" height="45" align="center">${product.satoffnadir}</td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "mirroroffnadir"/></B>:</td>
			<td width="183" height="45" align="center">${product.mirroroffnadir }</td>
			
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "scenecenterlat"/></B></td>
			<td width="183" height="45" align="center">${product.scenecenterlat}</td>
			
			</tr>
			
			<tr>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "scenecenterlong"/></B></td>
			<td width="183" height="45" align="center">${product.scenecenterlong}</td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "upperleftlong"/></B></td>
			<td width="183" height="45" align="center">${product.upperleftlong}</td>
			
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "upperleftlat"/></B></td>
			<td width="183" height="45" align="center">${product.upperleftlat}</td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "upperrightlat"/></B></td>
			<td width="183" height="45" align="center">${product.upperrightlat}</td>
			</tr>
	
			<tr>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "upperrightlong"/> </B></td>
			<td width="183" height="45" align="center">${product.upperrightlong}</td>
			
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "lowerleftlat"/></B></td>
			<td width="183" height="45" align="center">${product.lowerleftlat}</td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "lowerleftlong"/></B></td>
			<td width="183" height="45" align="center">${product.lowerleftlong}</td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "lowerrightlong"/></B></td>
			<td width="183" height="45" align="center">${product.lowerrightlong }</td>
			</tr>
			
			
			<tr>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "lowerrightlat"/></B></td>
			<td width="183" height="45" align="center">${product.lowerrightlat }</td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "sunelevation"/></B></td>
			<td width="183" height="45" align="center">${product.sunelevation}</td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "sunazimuthelevation"/></B></td>
			<td width="183" height="45" align="center">${product.sunazimuthelevation }</td>
			
			
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "assessmentmethod"/> </B></td>
			<td width="183" height="45" align="center">${product.assessmentmethod}</td>
			</tr>
			
			<tr>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "cloudcoverquote"/> </B></td>
			<td width="183" height="45" align="center">${product.cloudcoverquote }</td>
			</tr>
	
			<tr>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "cloudcoverquadrant1"/></B></td>
			<td width="183" height="45" align="center">${product.cloudcoverquadrant1}</td>
			
			
			<td width="183" align="center"  bgcolor="#e0ebfd"><B><@spring.message "cloudcoverquadrant2"/></B></td>
			<td width="183" height="45" align="center">${product.cloudcoverquadrant2}</td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "cloudcoverquadrant3"/></B></td>
			<td width="183" height="45" align="center">${product.cloudcoverquadrant3}</td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "cloudcoverquadrant4"/></B></td>
			<td width="183" height="45" align="center">${product.cloudcoverquadrant4}</td>
			</tr>
			
			
			</table>
			</div>
<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/constants.js" type="text/javascript"></script>
<script type="text/javascript">
  jQuery(function() {
	dojo.addOnLoad(productDetailInit);
});

</script>