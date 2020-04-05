<#include "top.ftl">
<#include "iframetop.ftl">
<!-- BEGIN PAGE CONTAINER-->

<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/tundra/tundra.css">
<link rel="stylesheet" type="text/css"
	href="${gisurl}/arcgis_js_api/library/2.7/jsapi/js/dojo/dijit/themes/claro/claro.css">
<link rel="stylesheet" type="text/css"
	href="css/table.css">
	
<script src="${gisurl}/arcgis_js_api/library/2.7/jsapi/" type="text/javascript"></script>
<script src="js/map/map_init.js" type="text/javascript"></script>
<script src="js/map/image.js" type="text/javascript"></script>
<script src="js/service/product.js" type="text/javascript"></script>

<script type="text/javascript">

</script>
<div style="float: left; width: 750px;height: 1050px;">
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
    <div id="map" class="claro" style="height: 100%;">
	</div>
    
</div>
<div style="float: left;">
	
	<div id="productquery">
		<form  action="${ctx}${currentPageUrl}" method="GET">
			<ul class="nav nav-tabs" role="tablist" data-target=".gpsdiv"
				data-toggle="tags">
				<li role="presentation" class="active"><a href="#home"
					aria-controls="home" onclick="lonLatQuery();" role="tab"
					data-toggle="tab">ææåºå</a>
				</li>
		    </ul>
		
		<div class="biao_tab">
			<span> <font>shpå¯¼å¥ </font> </span>
		</div>
		<table cellspacing="4" cellpadding="0" border="0">
			<tbody>
				<tr>
					<td>æä»¶å
					<input id="shpid"  type="text" size="7" style="width: 100px">
					<input type="file" id="shpfile"  name ="file" >
					
					</td>
				</tr>
			</tbody>
		</table>
	     <input type="button" class="btn btcms" onclick="importSensitiveShape()" value="ä¸ä¼ " >
	</form>
</div>

 <div class="row-fluid" id="sensitiveList">
	<table class="table table-striped table-bordered table-hover dataTables-example">
            <thead>
                <tr>
			        <td>æä»¶åç§°</td>
			        <td>ä¸ä¼ æ¶é´</td>
                </tr>
            </thead>
            <tbody>
            
		    </tbody>
		</table>
</div>

<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script src="js/constants.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>

<script type="text/javascript">
jQuery(function() {
	dojo.addOnLoad(init);
});

</script>
