<#include "top.ftl">

<div class="Sonwidth">
	<div class=" clear"></div>
	<div class="xw_w">
	<div class="xw_bor">

	<div  class="xw_mbx"><@spring.message "CurrentPosition"/>：<a href="./home.do"><@spring.message "HomePage"/></a>：<a href="./orderMain.do"><@spring.message "order"/></a>：<a href="./showOrderinfoByid.do?orderid=${order.orderid}"><@spring.message "OrderDetails"/></a></div>
	</div>
	<font size="4"><@spring.message "Basic_information_display"/></font>




	<table width="1099" border="0" class="cp_table">
  	<tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "satellite"/></B></td>
    <td width="183" height="45" align="center">${order.satelliteid }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "sensorid"/></B></td>
    <td width="183" height="45" align="center">${order.sensorid }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "resampletechnique"/></B></td>
    <td width="183" height="45" align="center">${order.resampletechnique }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Order_type"/></B></td>
    <td width="183" height="45" align="center">${order.tasktype }</td>
  	</tr>
  	<tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "mapprojection"/></B></td>
    <td width="183" height="45" align="center">${order.mapprojection }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "productlevel"/></B></td>
    <td width="183" height="45" align="center">${order.productlevel }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Map_pointing"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Settlement_price"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "tasktime"/></B></td>
    <td width="183" height="45" align="center">${order.tasktime }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "producttype"/></B></td>
    <td width="183" height="45" align="center">${order.producttype }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Image_data_type"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "coordinate"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Product_identification"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "productformat"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "priority"/></B></td>
    <td width="183" height="45" align="center">${order.priority }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "settlement_method"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Ordering_medium"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Distance_weighted_window"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "earthmodel"/></B></td>
    <td width="183" height="45" align="center">${order.earthmodel }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Scene_number"/></B></td>
    <td width="183" height="45" align="center">${order.scenecount }</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Geometric_correction"/></B></td>
    <td width="183" height="45" align="center">${order.geodeticmethod }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Polynomial_model"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Doppler_centroid"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Attitude_type"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Doppler_frequency"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Azimuth_visual"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Azimuth_window"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "pattern_datasource"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "correction_method"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "pattern_datasource"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Spectral_method"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "resampletechnique"/></B></td>
    <td width="183" height="45" align="center">${order.resampletechnique }</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "DEMdatasource"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "productid"/></B></td>
    <td width="183" height="45" align="center">${order.productid }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "sceneid"/></B></td>
    <td width="183" height="45" align="center">${order.sceneid }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Ephemeris_data_type"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
   <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "radiometricmethod"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Track_type"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Attitude_parameters"/></B></td>
    <td width="183" height="45" align="center">${order.attitudedata }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Whether_to_do_MTF"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
    <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "MTF_processing"/></B></td>
    <td width="183" height="45" align="center">${order.mtfcpromode }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Pixel_spacing"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "productspectrum"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "receivingstation"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
      <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Operator_name"/></B></td>
    <td width="183" height="45" align="center">${order.operatorname }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Audit_person"/></B></td>
    <td width="183" height="45" align="center">${order.checkusername }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "floatingratio"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Whether_to_noise"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
    <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Imaging_algorithm"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "cutting_method"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B>GSD</B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Control_source"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
    <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "scattering_correction"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "leftuplong"/></B></td>
    <td width="183" height="45" align="center">${order.dataupperleftlong }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "leftuplat"/></B></td>
    <td width="183" height="45" align="center">${order.dataupperleftlat }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "rightuplong"/></B></td>
    <td width="183" height="45" align="center">${order.dataupperrightupperlong }</td>
  </tr>
    <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "rightuplat"/></B></td>
    <td width="183" height="45" align="center">${order.dataupperrightlat }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "leftdownlong"/></B></td>
    <td width="183" height="45" align="center">${order.datalowerleftlong }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "leftdownlat"/></B></td>
    <td width="183" height="45" align="center">${order.datalowerleftlat }</td>
     <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Band_registration"/></B></td>
    <td width="183" height="45" align="center">${order.bands }</td>
  </tr>
    <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "rightdownlong"/></B></td>
    <td width="183" height="45" align="center">${order.datalowerrightlong }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "rightdownlat"/></B></td>
    <td width="183" height="45" align="center">${order.datalowerrightlat }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Completion_status"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Single_sign"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
      <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Normal_method"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "pattern_datasource"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "band_number"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "DEM_source"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
    <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Doppler_frequency"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Azimuth_window"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Product_identification"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "equalization"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
     <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Projection_zoning"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Strip_number"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Adaptive_production"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
    
 
</table>
	


	<font size="4"><@spring.message "Other_information"/></font>
	
	
	<table width="1099" border="0" class="cp_table">
	<tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Whether_passed"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Whether_settlement"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Operator_name"/></B></td>
    <td width="183" height="45" align="center">${order.operatorname }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "customer_type"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
  	<tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "orderType"/></B></td>
    <td width="183" height="45" align="center">${order.orderstate }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Audit_person"/> </B></td>
    <td width="183" height="45" align="center">${order.checkusername }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "checktime"/></B></td>
    <td width="183" height="45" align="center">${order.checktime }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "price"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
	</table>

	<font size="4"><@spring.message "Other_information"/></font>
	
	<table width="1099" border="0" class="cp_table">
  	<tr>
		<td width="183" height="45"  align="center" bgcolor="#e0ebfd"><B><@spring.message "checknote"/></B></td>
		<td width="183" height="45" align="center">${order.note }</td>
  
	</table>

</div>
</div>
<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
