<#include "top.ftl">

<div class="Sonwidth">
	<div class=" clear"></div>
	<div class="xw_w">
	<div class="xw_bor">

	<div  class="xw_mbx"><@spring.message "CurrentPosition"/>：<a href="./home.do"><@spring.message "HomePage"/></a>：<a href="./pssshopcar.do"><@spring.message "Shopcar"/></a>：<a href="./getPssShopCarinfo.do?orderid=${shopCar.orderid}"><@spring.message "OrderDetails"/></a></div>
	</div>
<table width="1099" border="0" class="cp_table">
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "order_form"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "producttype"/></B></td>
    <td width="183" height="45" align="center">${shopCar.tasktype }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "priority"/></B></td>
    <td width="183" height="45" align="center">${shopCar.priority }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "satellite"/></B></td>
    <td width="183" height="45" align="center">${shopCar.satelliteid }</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "sensorid"/></B></td>
    <td width="183" height="45" align="center">${shopCar.sensorid }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Ordering_medium"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "settlement_method"/></B></td>
    <td width="183" height="45" align="center">${shopCar.clearform }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Settlement_price"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Order_time"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Task_type"/></B></td>
    <td width="183" height="45" align="center">${shopCar.tasktype }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Task_time"/></B></td>
    <td width="183" height="45" align="center">${shopCar.tasktime }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Operator_name"/></B></td>
    <td width="183" height="45" align="center">${shopCar.operatorname }</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "customer_type"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "sceneid"/></B></td>
    <td width="183" height="45" align="center">${shopCar.sceneid }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "productlevel"/></B></td>
    <td width="183" height="45" align="center">${shopCar.productlevel }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "productspectrum"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "radiometricmethod"/></B></td>
    <td width="183" height="45" align="center">${shopCar.radiometricmethod }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "userName"/></B></td>
    <td width="183" height="45" align="center">${shopCar.username }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Map_pointing"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "earthmodel"/></B></td>
    <td width="183" height="45" align="center">${shopCar.earthmodel }</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Geometric_correction"/></B></td>
    <td width="183" height="45" align="center">${shopCar.geodeticmethod }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Polynomial_model"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Normal_method"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "productformat"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "mapprojection"/></B></td>
    <td width="183" height="45" align="center">${shopCar.mapprojection }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "resampletechnique"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Pixel_spacing"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "continuousview"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "floatingratio"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Image_data_type"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "coordinate"/></B></td>
    <td width="183" height="45" align="center"></td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Imaging_algorithm"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Doppler_centroid"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Doppler_frequency"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Azimuth_visual"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Distance_apparent_number"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
   <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Distance_weighted_window"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Azimuth_window"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "MTF_processing"/></B></td>
    <td width="183" height="45" align="center">${shopCar.mtfcpromode }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Speckle_algorithm"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
    <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "pattern_datasource"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "cutting_method"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "correction_method"/></B></td>
    <td width="183" height="45" align="center">${shopCar.mtfcpromode }</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "Spectral_method"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
      <tr>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "DEM_source"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "begintime"/></B></td>
    <td width="183" height="45" align="center">&nbsp;</td>
    <td width="183" height="45" align="center" bgcolor="#e0ebfd"><B><@spring.message "endtime"/></B></td>
    <td width="183" height="45" align="center"></td>
  </tr>
			
	</table>
	
</div>	



<#include "foot.ftl">
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
