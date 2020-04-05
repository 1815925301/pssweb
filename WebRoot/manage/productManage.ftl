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
<div>
<div style="float: left;"><img src="./img/map3.jpg" width="300" height="300"></img></div>
<div id="productMap" class="claro"   height: 335px; "></div>
</div>

<div class="clear"></div>
<div class="">
<font size="4"><@spring.message "Image_based_information"/></font>
	
</div>

	<table width="1099" border="0" class="cp_table">
	
		<tr>
			<td width="183"  height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "satellite"/></B></td>
			<td width="183" height="45" align="center">${product.satelliteid }</td>
			
			
			<td width="183"  height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "sensorid"/></B></td>
			<td width="183" height="45" align="center">${product.sensorid }</td>
			
			<td width="183"  height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "createtime"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "leftuplonglat"/></B></td>
			<td width="183" id="leftuplonglat" height="45" align="center">${product.dataupperleftlong }/${product.dataupperleftlat }</td>
			</tr>
			<tr>

			
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "rightdownlonglat"/></B></td>
			<td width="183" id="rightdownlonglat" height="45" align="center">${product.datalowerrightlong }/${product.datalowerrightlat }</td>
			
			
			<td width="145" align="center" bgcolor="#e0ebfd"><B><@spring.message "rightuplonglat"/></B></td>
			<td width="183" id="rightuplonglat"  height="45" align="center">${product.datalowerghtlong }/${product.dataupperrightlat }</td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "centerlonglat"/></B></td>
			<td width="183" height="45" align="center"></td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "leftdownlonglat"/></B></td>
			<td width="183" id="leftdownlonglat" height="45" align="center">${product.datalowerleftlong }/${product.datalowerleftlat }</td>
			
			
			</tr>
			<tr>
			
			
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "receivingstation"/></B></td>
			<td width="183" height="45" align="center"></td>
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "orbitalnumber"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "viewPath/Row"/></B></td>
			<td width="183" height="45" align="center">${product.scenepath }/${product.scenerow}</td>
			
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "substarPath/Row"/></B></td>
			<td width="183" height="45" align="center">${product.satpath }/${product.satrow}</td>
			</tr>
			
			<tr>
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "productstarttime"/></B></td>
			<td width="183" height="45" align="center">${product.imageingstarttime }</td>
			
			
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "productmiddletime"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			<td width="183" height="45" align="center"  bgcolor="#e0ebfd"><B><@spring.message "productendtime"/></B></td>
			<td width="183" height="45" align="center">${product.imageimgstoptime }</td>
			</tr>
	</table>
	<div>
		<font size="4"><@spring.message "productparamet"/></font>
	</div>
	<table width="1099" border="0" class="cp_table">
			<tr>
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "productlevel"/></B></td>
			<td width="183" height="45" align="center">${product.productlevel }</td>
			
			
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "sceneid"/></B></td>
			<td width="183" height="45" align="center">${product.sceneid }</td>
			
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "productid"/></B></td>
			<td width="183" height="45" align="center">${product.productid }</td>
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "productdate"/></B></td>
			<td width="183" height="45" align="center">${product.productdate }</td>
			</tr>
			
			<tr>
			
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "productspectrum"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "productresolution"/></B></td>
			<td width="183" height="45" align="center"></td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "producttype"/></B>:</td>
			<td width="183" height="45" align="center">${product.tasktype }</td>
			
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "continuousview"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			</tr>
			
			<tr>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "floatingratio"/></B></td>
			<td width="183" height="45" align="center"></td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "productformat"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "productwidthhigh"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "datastripnumber"/></B></td>
			<td width="183" height="45" align="center"></td>
			</tr>
	
			<tr>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "cloudcoverage"/> </B></td>
			<td width="183" height="45" align="center"></td>
			
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "productsize"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "ellipsoidal"/></B></td>
			<td width="183" height="45" align="center"></td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "resampletechnique"/></B></td>
			<td width="183" height="45" align="center">${product.resampletechnique }</td>
			</tr>
			
			
			<tr>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "radiometricmethod"/></B></td>
			<td width="183" height="45" align="center">${product.radiometricmethod }</td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "DEMdatasource"/></B></td>
			<td width="183" height="45" align="center"></td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "mapprojection"/></B></td>
			<td width="183" height="45" align="center">${product.mapprojection }</td>
			
			
			<td width="183" align="center" bgcolor="#e0ebfd"><B><@spring.message "projectedbandnumber"/> </B></td>
			<td width="183" height="45" align="center"></td>
			</tr>
			
			<tr>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "earthmodel"/> </B></td>
			<td width="183" height="45" align="center">${product.earthmodel }</td>
			</tr>
	</table>
	<div>
		<font size="4"><@spring.message "Otherparameters"/></font>
	</div>
	<table width="1099" border="0" class="cp_table">
			<tr>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "satelliteaerialangle"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			
			<td width="183" align="center"  bgcolor="#e0ebfd"><B><@spring.message "satellitepitchangle"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "satelliterollangle"/></B></td>
			<td width="183" height="45" align="center"></td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "satelliteazimuth"/></B></td>
			<td width="183" height="45" align="center"></td>
			</tr>
			<tr>
			
			<td width="183" align="center"  bgcolor="#e0ebfd"><B><@spring.message "satellitealtitude"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "camerasideview"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "sunelevation"/></B></td>
			<td width="183" height="45" align="center">${product.sunelevation }</td>
			
			
			<td width="183" align="center"  bgcolor="#e0ebfd"><B><@spring.message "sunazimuth"/></B></td>
			<td width="183" height="45" align="center">${product.sunazimuth }</td>
			</tr>
			<tr>
			
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Cameraangle"/></B></td>
			<td width="183" height="45" align="center"></td>
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Gainmode"/></B></td>
			<td width="183" height="45" align="center">${product.gain }</td>
			
			
			<td width="183" align="center"  bgcolor="#e0ebfd"><B><@spring.message "integrationtime"/></B></td>
			<td width="183" height="45" align="center"></td>
			
			<td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Integralseries"/></B></td>
			<td width="183" height="45" align="center"></td>
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