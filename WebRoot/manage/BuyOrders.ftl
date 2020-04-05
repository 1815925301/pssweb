<#include "top.ftl">

<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
				<div class="main-hd">
				
				</div>
			
			
				
				
				<!-- BEGIN PAGE CONTENT-->
				
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                               		<td width="3%"><input type="checkbox" id="allcheck"></td>
	                                <td width="5%"><@spring.message "ordernum"/></td>
							        <td width="5%"><@spring.message "ordertype"/></td>
							        <td width="5%"><@spring.message "usernames"/></td>
							        <td width="5%"><@spring.message "satllite"/></td>
							        <td width="5%"><@spring.message "orderstate"/></td>
							        <td width="6%"><@spring.message "productleavle"/></td>
							        <td width="10%"><@spring.message "TaskTime"/></td>
							        <td width="5%"><@spring.message "paystate"/></td>
							        <td width="5%"><@spring.message "checkstate"/></td>
							        <td width="5%"><@spring.message "down"/></td>
							         <td width="13%"><@spring.message "option"/></td>
                                </tr>
                            </thead>
                            <tbody>
                                <#if getBuyOrders?? && (getBuyOrders?size > 0)>
				             
				                <#list getBuyOrders as order>
				               
				                <tr> 
				                <td class="botValue"><input type="checkbox" name="subcheck" id="${order.orerid}" value="${order.orderid}"></td> 
							    <td class="botValue">${order.orderid}</td>
							      <#if order.tasktype==1>
							       <td class="botValue"><@spring.message "productorder"/></td>
							        <#else>
							       <td class="botValue"><@spring.message "noproduct"/></td>
							      </#if>
							      <td class="botValue">${order.username}</td>
							      <td class="botValue">${order.satelliteid}</td>
							      <#if order.orderstate==1||order.orderstate="">
							       <td class="botValue"><@spring.message "wait"/></td>
							        </#if>
							       <#if order.orderstate==2>
							       <td class="botValue"><@spring.message "handling"/></td>
							        </#if>
							        <#if order.orderstate==3>
							       <td class="botValue"><@spring.message "dealsuccess"/></td>
							      </#if>
							     	<#if order.orderstate==4>
							       <td class="botValue"><@spring.message "dealfail"/></td>
							      </#if>
							      
							      <td class="botValue">${order.productlevel}</td>
							 		<td class="botValue">${order.tasktime}</td>
							 		  <#if order.ispay==1||order.ispay=0>
							       <td class="botValue"><@spring.message "nopay"/></td>
							        </#if>
							       <#if order.ispay==2>
							       <td class="botValue"><@spring.message "ispay"/></td>
							        </#if>
							        
							 		<#if order.checkstate==1||order.checkstate="">
							       <td class="botValue"><@spring.message "waitcheck"/></td>
							        </#if>
							       <#if order.checkstate==2>
							       <td class="botValue"><@spring.message "checkpass"/></td>
							        </#if>
							        <#if order.checkstate==3>
							       <td class="botValue"><@spring.message "checkfaild"/></td>
							      </#if>
							      
							     
							      <td class="botValue"><@spring.message "nodown"/></td>
							      <td class="botValue">
							       <a class="btn btcms" onclick="showpssorderinfo('${order.orderid}')"><i class="fa fa-train"></i>&nbsp;<@spring.message "view"/></a>
							      </td>
							    </tr>
							    </#list>
				              <#else>
				                <tr>  
							      <td class="botValue" colspan="6" style="padding:10px 0;"><@spring.message "nouserinformation"/>！</td>
							    </tr>
				              </#if>
						</tbody>
						<!--table 表格结束-->
							<!-- 列表分页模块开始 start -->
				           <tfoot>
                                <tr>
                                    <td colspan="8">
                                   		 <div class="span6"><div class="dataTables_info" id="sample_1_info"></div></div>
                                   		 <div class="span6">
										<div class="dataTables_paginate paging_bootstrap pagination">
                                     	<ul>
											     <#if (totalInfo.totalCount > 0)>
											      <#if (totalInfo.currentPage > 1)>
											      	<li class="prev disabled">
											      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value=<@spring.message "useruppage"/>>
											      	</li>
											        <li class="active">
											       	 <input class="btn blue" type="button" id="pageTurn1" value="1" /></li>
											      </#if>
											      
											      <#if (totalInfo.currentPage - 2 > 1)>
											        <#assign minPage = totalInfo.currentPage - 2>
											      <#else>
											        <#if (totalInfo.currentPage == 1)>
											          <#assign minPage = 1>
											        <#else>
											          <#assign minPage = 2>
											        </#if>
											      </#if>
											      
											      <#if (minPage - 1 > 1)>
											        ...
											      </#if>
											      
											      <#if (totalInfo.pageTotal - 2 > totalInfo.currentPage)>
											        <#assign maxPage = totalInfo.currentPage + 2>
											      <#else>
											        <#if (totalInfo.currentPage == totalInfo.pageTotal) || (totalInfo.pageTotal - totalInfo.currentPage == 1)>
											          <#assign maxPage = totalInfo.currentPage>
											        <#else>
											          <#assign maxPage = totalInfo.currentPage + 1>
											        </#if>
											      </#if>
											      
											      <#list minPage..maxPage as t>
											        <#if (totalInfo.currentPage == t)>
												     <li class="footable-page active">
												      <input class="btn blue " type="button" id="pageTurn${t}" value="${t}" /></li>
												    <#else> 
												    <li class="footable-page">
												      <input class="btn blue" type="button" id="pageTurn${t}" value="${t}" /></li>
												    </#if>
												  </#list>
											      
											      
											      <#if (totalInfo.pageTotal - maxPage > 1)>
											        ...
											      </#if>
											      
											      <#if (totalInfo.currentPage < totalInfo.pageTotal)>
											       <li class="footable-page">
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.pageTotal}" value="${totalInfo.pageTotal}" /></li>
											        <li class="footable-page-arrow">
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+1}" value=<@spring.message "userdownpage"/> /></li>
											      </#if>
											      <input type="hidden" id="pageTotal" value="${totalInfo.pageTotal}">
											     </#if>
											    </div>
											  </div>
											</ul>
                                    </td>
                                </tr>
                            </tfoot>
						<!-- 列表分页模块结束 end -->
						</table>
                    </div>
                </div>
            </div>
        </div>
        
<!--添加常量表窗口 start -->
<div class="row-fluid"  id='addNewConstantWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" method="post" class="form-horizontal" id='addForm'>
							<div class="control-group">
								<label class="control-label"><@spring.message "tablename"/>：</label>
								<div class="controls">
									<input type="text" maxlength="40" class="width220" id="add_name" name="name" value="constant_" />
	  	                            <span id="add_name_error" class="red"></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "describ"/></label>
								<div class="controls">
									<input type="text" class="width220" maxlength="100" id="add_description" name="description" value="" />
		                            <span id="add_description_error" class="red"></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" id="saveNewConstant"><i class="icon-ok"></i> <@spring.message "usersave"/></button>
								<button type="button" class="btn blue" id='cancleConstantAdd'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>

<!--添加常量表窗口 end -->

<!-- 添加常量数据窗口 start -->
<div class="row-fluid"  id='addNewListWin'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" method="post" class="form-horizontal">
						<input type="hidden" value="" id="add_constant_id" />
						<input type="hidden" value="" id="add_constant_tableName" />
							<div class="control-group">
								<label class="control-label"><@spring.message "code"/>：</label>
								<div class="controls">
								    <input type="text" maxlength="15" class="width220" id="add_code" name="code" value="" />
	  	                            <span id="add_code_error" class="red"></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><@spring.message "ConstantValue"/>：</label>
								<div class="controls">
									 <input type="text" class="width220" maxlength="120" id="add_value" name="value" value="" />
		                             <span id="add_value_error" class="red"></span>
								</div>
							</div>
							<div class="form-actions">
								 <input type="button" class="btn blue" id="saveNewList" value=<@spring.message "normal"/> />
	                             <input type="button" class="btn blue" id="cancleListAdd" value=<@spring.message "usercanel"/> />
							</div>
						</form>
				</div>
	 		</div>
</div>

<!-- 添加常量数据窗口 end -->

<!-- 用户信息展示窗口 start -->
<div class="row-fluid"  id='showpssorderinfo'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "ordertype"/></label>
								<label class="control-label"  id='order_tasktype'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "usernames"/></label>
								<label class="control-label"  id='order_username'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "satllite"/></label>
								<label class="control-label"  id='order_satelliteid'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "orderstate"/></label>
								<label class="control-label"  id='order_orderstate'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "paystate"/></label>
								<label class="control-label"  id='order_ispay'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "productleavle"/></label>
								<label class="control-label"  id='order_productlevel'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "TaskTime"/></label>
								<label class="control-label"  id='order_tasktime'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "checkstate"/></label>
								<label class="control-label"  id='order_checkstate'></label>
							</div>
						
							<div class="form-actions ">
								<button type="button" class="btn blue" id='showcancleAdd'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
</div>
<!-- 用户信息展示窗口 end -->
<!-- 常量信息展示窗口 end -->

<!-- 常量信息修改窗口 start -->
<ul id="addorderinfoWin" class="msg_Tip_box width500" style="display:none">
	<a class="mask_close pr10" href="javascript:$.unblockUI()"></a>
	<div class="center tabDivInfo">
	 <div class="biao_tab">
			<span> <font><@spring.message "Coordinates"/> </font> </span>
		</div>
		<table cellspacing="4" cellpadding="0" border="0">
			<tbody>
				<tr>
					<td><@spring.message "LongitudeLeft"/> :</td>
					<td><input id="leftuplong" type="text" size="5"
						style="width: 80px">
					</td>
					<td><@spring.message "LatitudeLeft"/> :</td>
					<td><input id="leftuplat" type="text" size="5"
						style="width: 80px">
					</td>
				</tr>
				<tr>
					<td><@spring.message "BottomRightLongitude"/> :</td>
					<td><input id="rightdownlong" type="text" size="5"
						style="width: 80px"></td>
					<td><@spring.message "LowerrightLatitude"/> :</td>
					<td><input id="rightdownlat" type="text" size="5"
						style="width: 80px"></td>
				</tr>
			</tbody>
		</table>
		<div class="biao_tab">
			<span> <font><@spring.message "SatelliteConditions"/> </font> </span>
		</div>
		<table cellspacing="4" cellpadding="0" border="0">
			<tbody>
				<tr>
					<td>VRSS-1 :</td>
				</tr>
				<tr>
					<td><input id="leftuplat" type="checkbox" name="sensorid" value="PAN-1">PAN-1 <input
						id="leftuplat" type="checkbox" name="sensorid" value="PAN-2">PAN-2</td>
				</tr>
				<tr>
					<td><input id="leftuplat" type="checkbox" name="sensorid" value="MSS-1">MSS-1 <input
						id="leftuplat" type="checkbox" name="sensorid" value="MSS-1">MSS-1</td>
				</tr>
			</tbody>
		</table>
		<div class="biao_tab">
			<span> <font><@spring.message "TimeConditions"/> </font> </span>
		</div>
		<table cellspacing="4" cellpadding="0" border="0">
			<tbody>

				<!--<tr>
					<td>采集时间 <input type="text" id="createStarttime"
						name="createStarttime" value="${createStarttime}"
						style="width:85px" class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" />— <input
						type="text" id="createEndtime" name="createEndtime"
						value="${createEndtime}" style="width:85px"
						class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" /></td>
				</tr>--!>
				<tr>
					<td>生产时间 <input type="text" id="createStarttime"
						name="createStarttime" value="${createStarttime}"
						style="width:85px" class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" />— <input
						type="text" id="createEndtime" name="createEndtime"
						value="${createEndtime}" style="width:85px"
						class="form-control Wdate"
						onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});" /></td>
				</tr>
			</tbody>
		</table>
		<div class="biao_tab">
			<span> <font>其他条件 </font> </span>
		</div>
		<table cellspacing="4" cellpadding="0" border="0">
			<tbody>

				<tr>
					<td>产品级别  <select id="productlevel" name="productlevel">
							<option value="LEVEL1">LEVEL1</option>
							<option value="LEVEL2A">LEVEL2A</option>
							<option value="LEVEL2B">LEVEL2B</option>
							<option value="LEVEL3A">LEVEL3A</option>
							<option value="LEVEL3B">LEVEL3B</option>
							<option value="LEVEL4">LEVEL4</option>
					</select></td>
				</tr>
				<tr>
					<td> 
				 
					</td>
				</tr>
				<tr>
					<td>景序列号 
					<input id="sceneid" name="sceneid" type="text" size="7" maxlength="9" style="width: 100px">
					</td>
				</tr>
				<!--<<tr>
					<td> 分  辨  率
					input id="mincloudcoverage" type="text" size="7" maxlength="9" style="width: 50px">
至
<input id="maxcloudcoverage" type="text" size="7" maxlength="9" style="width: 50px">
					</td>
				</tr>--!>
				<tr>
					<td>子PATH
					<input id="scenepath" name="scenepath" type="text" size="7" maxlength="9" style="width: 50px">
子ROW
<input id="scenerow" name="scenerow" type="text" size="7" maxlength="9" style="width: 50px">
					</td>
				</tr>
			</tbody>
		</table>
	  <input type="button" class="botton" id="saveEditRole" value="  确  定  " />
	  <input type="button" class="botton" id="cancle" value="  取  消  " />
	</div>
</ul>
<!-- 常量信息修改窗口 end -->
<!-- 订单审核窗口 and -->
<div class="row-fluid"  id='orderStateWin'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id="orderForm">
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "ordernum"/></label>
								<label class="control-label" id='orderID'></label>
								<input type="hidden" id="order_id" name='ordermath'>
							</div>
							<div class="control-group form_lab_div">
									<label class="control-label"><@spring.message "checknote"/></label>
									<input type="text" id="note" name='note' />
							</div>
							<div class="form-actions ">
								<button type="button" class="btn blue" onclick='updateCheckState("2")'><@spring.message "checkpass"/></button>
								<button type="button" class="btn blue" onclick='updateCheckState("3")'><@spring.message "checkfaild"/></button>
								<button type="button" class="btn blue" onclick='stateCancle()'><@spring.message "usercanel"/></button>
							</div>
						</form>
				</div>
</div>
<#include "foot.ftl">
<script src="js/common.js" type="text/javascript"></script>
<script src="js/common/util.js" type="text/javascript"></script>
<script src="js/my97/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>