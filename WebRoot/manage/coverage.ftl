<#include "top.ftl">
<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/tundra/tundra.css">
<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/claro/claro.css">
<link rel="stylesheet" type="text/css"
	href="css/table.css">
	
<script src="${gisurl}/arcgis_js_api/library/2.7/jsapi/" type="text/javascript"></script>
<script src="js/map/map_init.js" type="text/javascript"></script>
<div id="coverMap" class="claro" style="float: left; width: 100%;height: 100%;"/>

<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script src="js/constants.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(function() {
	dojo.addOnLoad(coverMapInit);
});

</script>

<input type="hidden" id="pointStrs" value = "${points}" />
