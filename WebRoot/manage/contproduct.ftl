<#include "top.ftl">

 <#if plist?? && (plist?size > 0)>
            		<#list plist as plist>
            		<div style="float: left;width: 450px; margin-left: 20px;">
            		
            		<table border="1" cellspacing="1">
            			<tr><td><@spring.message "productNumber"/>:</td><td>${plist.productid }</td><td><@spring.message "productlevel"/>:</td><td>${plist.productlevel }</td></tr>
            			<tr><td><@spring.message "satellite"/>:</td><td>${plist.satelliteid }</td><td><@spring.message "sensorid"/>:</td><td>${plist.sensorid }</td></tr>
            			<tr><td><@spring.message "leftuplonglat"/>:</td><td>${plist.dataupperleftlong }/${plist.dataupperleftlat }</td><td><@spring.message "rightdownlonglat"/></td><td>${plist.datalowerrightlong }/${plist.datalowerrightlat }</td></tr>
            			<tr><td><@spring.message "rightuplonglat"/>:</td><td>${plist.datalowerghtlong }/${plist.dataupperrightlat }</td><td><@spring.message "leftdownlonglat"/></td><td>${plist.datalowerleftlong }/${plist.datalowerleftlat }</td></tr>
            			<tr><td><@spring.message "viewPath/Row"/>:</td><td>${plist.scenepath }/${plist.scenerow }</td><td><@spring.message "substarPath/Row"/>:</td><td>${plist.satpath }/${plist.satrow }</td></tr>
            			<tr><td><@spring.message "productstarttime"/>:</td><td>${plist.imageingstarttime }</td><td><@spring.message "productendtime"/>:</td><td>${plist.imageimgstoptime }</td></tr>
            			<tr><td><@spring.message "sceneid"/>:</td><td>${plist.sceneid }</td><td><@spring.message "productdate"/>:</td><td>${plist.productdate }</td></tr>
            			<tr><td><@spring.message "producttype"/>:</td><td>${plist.tasktype }</td><td><@spring.message "resampletechnique"/>:</td><td>${plist.resampletechnique }</td></tr>
            			<tr><td><@spring.message "radiometricmethod"/>:</td><td>${plist.radiometricmethod }</td><td><@spring.message "mapprojection"/>:</td><td>${plist.mapprojection }</td></tr>
            			<tr><td><@spring.message "earthmodel"/>:</td><td>${plist.earthmodel }</td><td><@spring.message "sunelevation"/>:</td><td>${plist.sunelevation }</td></tr>
            		</table>
            		
            		</div>
       		
            				</#list>
				   </#if>

<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/util.js" type="text/javascript"></script>