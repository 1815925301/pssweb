<#include "top.ftl">

<!-- BEGIN PAGE CONTAINER--> 
       <div class="Sonwidth">
	<div class="lcimg">
		  <!--新加的代码-->
            <div class="lcimg-row lcimg-row-bg">
                <div class="lcimg-col-3 lcimg-1">
                    <div><i>1</i></div>
                </div>
                <div class="lcimg-col-3 lcimg-2 active"><div><i>2</i></div></div>
                <div class="lcimg-col-3 lcimg-3"><div><i>3</i></div></div>
                <div class="lcimg-col-3 lcimg-4"><div><i>4</i></div></div>
            </div>
            <div class="lcimg-row lcimg-row-txt">
                <div class="lcimg-col-3">
                    <span><@spring.message "mycar"/></span>
                </div>
                <div class="lcimg-col-3 active">
                    <span><@spring.message "orderinfo"/></span>
                </div>
                <div class="lcimg-col-3">
                    <span><@spring.message "submitorder"/></span>
                </div>
                <div class="lcimg-col-3">
                    <span><@spring.message "ordersystem"/></span>
                </div>
            </div>
            <!--新加的代码END-->
	</div>
	
		
		<div class="ggcanniu">
	
	</div>
	<div class="clear"></div>
	
			<div class="container-fluid">
			<input type="hidden" id="orderIds" value = "${orderIds}" />
				<div class="main-hd" style="text-align: center; padding-top: 5px;">
					<div class="form-group">
						<div class="row">
							<div class="col-md-5">
								<input type="hidden" id="price" value="${allprices}">
								<label><@spring.message "orderMainName"/>：</label>
								<input type="text" class="form-control" id="orderName" placeholder="" style="height: 28px;">
								<span id="input_Name" Style="color: red; display:none; font-size: 12pt;">*请输入正确名称，名称由中文、字母、数字组成长度为1至15</span>
								<span class="help-inline"  id='config_key_'></span>
							</div>
							<div class="col-md-5">
								<label><@spring.message "Remark"/>：</label>
								<textarea class="form-control" rows="3" id = "remark"></textarea>
							</div>
							<div class="bcqx">
							
							<a  onclick = "addOrder()"><@spring.message 'productOrder'/></a>
							</div>
						</div>
					</div>
			    </div>
			
			
				
				 <table width="100%" border="0" class="gwc_table">
				<!-- BEGIN PAGE CONTENT-->
				 <input type="hidden" id="idlist" name="idlist" />
	<tr class="gwc_tr">
    <td width="158" height="0" bgcolor="#f6f6f6">
     
    <td width="726" height="0" align="left" bgcolor="#f6f6f6"><@spring.message "comminfomation"/></td>
    <td width="200" height="0" align="center" bgcolor="#f6f6f6"><@spring.message "price"/></td>
    <td width="200" height="0" align="center" bgcolor="#f6f6f6"><@spring.message "totalprices"/>：${allprices}</td>
    <td width="200" height="0" align="center" bgcolor="#f6f6f6"><@spring.message "useroperation"/></td>
  </tr>
    
     <#if orderList?? && (orderList?size > 0)>
				             
				                <#list orderList as order>
				               	
    <tr class="gwc_tr">
    <td colspan="2">
      <div class="gwc_img" style="float: left;"><img src="imgnew/img03.gif" width="119" height="87" /></div> 
      <div class="gwc_f">
        <@spring.message "order_form"/>：<#if order.ordertype==2><@spring.message "productorder"/><#else><@spring.message "notproduct"/></#if><br />
        <@spring.message "sceneid"/>： ${order.sceneid}<br />
        <@spring.message "producttype"/>： ${order.producttype}
      </div>
      <div class="gwc_f">
         <@spring.message "productlevel"/>： ${order.productlevel}<br />
        <@spring.message "satellite"/>： ${order.satelliteid}  <br />
       <@spring.message "sensorid"/>： ${order.sensorid}
  	 </div>
      <div class="gwc_f">
        <@spring.message "productselect"/>：<br />
        <@spring.message "productnum"/>: ${order.productid}<br /> 
        <@spring.message "Order_time"/>: 
        </div>
      </td>
      <td>
      	${order.price}
      </td>
      <td>
      </td>
      <td align="center"><div class="cz_font"><a onclick="showShopCar(${order.orderid});" target="_blank"><@spring.message "Check"/></a></td>
  </tr>
                           		
      </#list>                         		
       </#if>  
						
		</table>				
							
                    </div>
                </div>
            </div>
        </div>
        
<!--查询详情表窗口 start -->
 <div class="row-fluid"  id='showShopCarWin'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
						<input type="hidden" value="" id='show_id'  name='id' >
							<div class="control-group form_lab_div">
								<label class="control-label">景序列号：</label>
								<label class="control-label" style="width: 6%" id='show_sceneid'></label>
								<label class="control-label">订购单类型：</label>
								<label class="control-label" style="width: 6%" id='show_ordertype'></label>
							</div>
							 
							<div class="control-group form_lab_div">
								<label class="control-label">产品级别：</label>
								<label class="control-label" style="width: 6%" id='show_productlevel'></label>
								<label class="control-label">产品号：</label>
								<label class="control-label" style="width: 6%" id='show_productid'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label">卫星：</label>
								<label class="control-label" style="width: 6%"  id='show_satelliteid'></label>
									<label class="control-label">传感器：</label>
								<label class="control-label" style="width: 6%"  id='show_sensorid'></label>
							</div>
							 
							<div class="control-group form_lab_div">
								<label class="control-label">产品类型：</label>
								<label class="control-label" style="width: 6%"  id='show_producttype'></label>
								<label class="control-label">订购面积：</label>
								<label class="control-label" style="width: 6%"  id='show_area'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label">产品价格：</label>
								<label class="control-label" style="width: 6%"  id='show_price'></label>
							</div>
							<div class="form-actions ">
								<button type="button" class="btn blue"  onclick="hidenShopCar();">取消</button>
							</div>
						</form>
				</div>
</div>

<!-- 查询详情表窗口 end -->

<!-- 常量信息展示窗口 start -->
<ul id="constInfoWin" class="msg_Tip_box width600" style="display:none">
	<a class="mask_close pr10" href="javascript:$.unblockUI()"></a>
	<div id="constantValue" class="center tabDivInfo"  style="max-height:500px;overflow-y:auto;">
	  
	</div>
</ul>
<!-- 常量信息展示窗口 end -->

<!-- 常量信息修改窗口 start -->
 <div class="row-fluid"  id='showUpdateShopCarWin'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id="changeForm">
						<input type="hidden" value="" id='show_orderid'  name='orderid' >
							 <div class="control-group">
								<label class="control-label">景序列号：</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='update_sceneid'  name='sceneid'  />
									<span class="help-inline" id='sceneid_error'></span>
								</div>
							</div>
							 <div class="control-group">
								<label class="control-label">订购单类型：</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='update_ordertype'  name='ordertype'  />
									<span class="help-inline" id='ordertype_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">产品级别：</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='update_productlevel'  name='productlevel'  />
									<span class="help-inline" id='productlevel_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">产品号：</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='update_productid'  name='productid'  />
									<span class="help-inline" id='productid_error'></span>
								</div>
							</div>
							 <div class="control-group">
								<label class="control-label">卫星：</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='update_satelliteid'  name='satelliteid'  />
									<span class="help-inline" id='satelliteid_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">传感器：</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='update_sensorid'  name='sensorid'  />
									<span class="help-inline" id='sensorid'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">产品类型：</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='update_producttype'  name='producttype'  />
									<span class="help-inline" id='producttype_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">订购面积：</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='update_area'  name='area'  />
									<span class="help-inline" id='area_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">产品价格：</label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='update_price'  name='price'  />
									<span class="help-inline" id='priceerror'></span>
								</div>
							</div>
							<div class="form-actions ">
							<button type="button" class="btn blue" onclick="saveChange();"><i class="icon-ok"></i> 保存</button>
								<button type="button" class="btn blue"  onclick="hidenUpdateShopCar();">取消</button>
							</div>
						</form>
				</div>
</div>
<!-- 常量信息修改窗口 end -->
<#include "foot.ftl">
<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/service/pssshopcar.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>

