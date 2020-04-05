<#include "top.ftl">
<!--日期插件-->
<script src="js/calendar/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script language="javascript">
		function show() {
			var a = document.getElementById('aa');
			a.style.display == "none" ? a.style.display = ""
					: a.style.display = "none";
		}
	</script>

	<div class="gr_w">
	<div class=" clear"></div>
	<div class="xw_w">
	<div class="xw_bor">

	<div  class="xw_mbx"><@spring.message "CurrentPosition"/>：<a href="./home.do"><@spring.message "HomePage"/></a>：<a href="./orderMain.do"><@spring.message "order"/></a></div>
	</div>
	<div class="lcimg">
		 <!--新加的代码-->
            <div class="lcimg-row lcimg-row-bg">
                <div class="lcimg-col-3 lcimg-1">
                    <div><i>1</i></div>
                </div>
                <div class="lcimg-col-3 lcimg-2"><div><i>2</i></div></div>
                <div class="lcimg-col-3 lcimg-3"><div><i>3</i></div></div>
                <div class="lcimg-col-3 lcimg-4 active"><div><i>4</i></div></div>
            </div>
            <div class="lcimg-row lcimg-row-txt">
                <div class="lcimg-col-3">
                    <span><@spring.message "mycar"/></span>
                </div>
                <div class="lcimg-col-3">
                    <span><@spring.message "orderinfo"/></span>
                </div>
                <div class="lcimg-col-3">
                    <span><@spring.message "submitorder"/></span>
                </div>
                <div class="lcimg-col-3 active">
                    <span><@spring.message "ordersystem"/></span>
                </div>
            </div>
            <!--新加的代码END-->
            </div>
		<div class="gr_t">
			<span><a href="#" onclick="show();"><img
					src="imgnew/icon21.gif" width="26" height="26" /> </a> </span><span><img
				src="imgnew/icon19.png" width="33" height="30" /> </span><@spring.message "query"/>
		</div>
		<div class="clear"></div>

		<div id="aa">
			<form id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
			<input type="hidden" value="" id='pageNumInput'  name='pageNumInput' >
			<!--查询条件开始-->
			<div class="ddcxf"><!--卫星--><@spring.message "satellite"/>：</div>
			<div class="ddcxinput">
				<select id="_satelliteId" name="_satelliteId" onchange="sensorSelcect(this)">
					<option value="-1" selected="selected"><@spring.message "choose"/></option>
					<#if collectSatellite?? && (collectSatellite?size > 0)>
				    	<#list collectSatellite as satellite>
							<option value="${satellite}" <#if _satelliteId=='${satellite}'>selected</#if>>${satellite}</option>
				  		</#list>
				  	</#if>
				</select>
			</div>

			<div class="ddcxf"><!--传感器--><@spring.message "sensorid"/>：</div>
			<div class="ddcxinput">
				<select id="_sensorId" name="_sensorId">
					<option value="-1" selected="selected"><@spring.message "choose"/></option>
					<#if sensorIdS?? && (sensorIdS?size > 0)>
				    	<#list sensorIdS as sensor>
					<option value="${sensor}" <#if _sensorId=='${sensor}'>selected</#if>>${sensor}</option>
						</#list>
				  	</#if>
				</select>
			</div>

			<div class="ddcxf"><!--订单状态--><@spring.message "Type"/>：</div>
			<div class="ddcxinput">
				<select id="_taskType" name="_taskType">
					<option value="-1" selected="selected"><@spring.message "choose"/></option>
					<option value="1" <#if _taskType==1>selected</#if>><!-- 生产订单 --><@spring.message "ProductionOrder"/></option>
					<option value="2" <#if _taskType==2>selected</#if>><!-- 产品订单 --><@spring.message "ProductOrder"/></option>
				</select>
			</div>

			<div class="ddcxf"><!--产品级别--><@spring.message "level"/>：</div>
			<div class="ddcxinput">
				<select id="_productLevel" name="_productLevel">
					<option value="-1" selected="selected"><@spring.message "choose"/></option>
					<option value="LEVEL0" <#if _productLevel=='LEVEL0'>selected</#if>>0</option>							
					<option value="LEVEL1" <#if _productLevel=='LEVEL1'>selected</#if>>1</option>
					<option value="LEVEL2A" <#if _productLevel=='LEVEL2A'>selected</#if>>2A</option>
					<option value="LEVEL2B" <#if _productLevel=='LEVEL2B'>selected</#if>>2B</option>
					<option value="LEVEL3A" <#if _productLevel=='LEVEL3A'>selected</#if>>3A</option>
					<option value="LEVEL3B" <#if _productLevel=='LEVEL3B'>selected</#if>>3B</option>
					<option value="LEVEL4" <#if _productLevel=='LEVEL4'>selected</#if>>4</option>
					
				</select>
			</div>

			<div class="clear"></div>
			<div class="ddcxf"><!--审核状态--><@spring.message "auditstate"/>：</div>
			<div class="ddcxinput">
				<select id="_areadystate" name="_areadystate">
					<option value="-1" selected="selected"><@spring.message "choose"/></option>
					<option value="3" <#if _areadystate==3>selected</#if>><!-- 未通过 --><@spring.message "NotThrough"/></option>
					<option value="2" <#if _areadystate==2>selected</#if>><!-- 通过 --><@spring.message "PassThrough"/></option>							
					<option value="1" <#if _areadystate==1>selected</#if>><!-- 等待 --><@spring.message "WaitFor"/></option>
				</select>
			</div>

			<div class="ddcxf"><!--处理状态--><@spring.message "processing"/>：</div>
			<div class="ddcxinput">
				<select id="_orderState" name="_orderState">
					<option value="-1" selected="selected"><@spring.message "choose"/></option>
					<option value="1" <#if _orderState==1>selected</#if>><!--完成--><@spring.message "Complete"/></option>
					<option value="5" <#if _orderState==5>selected</#if>><!--失败--><@spring.message "Fail"/></option>							
					<option value="4" <#if _orderState==4>selected</#if>><!--取消--><@spring.message "Cancel"/></option>							
					<option value="3" <#if _orderState==3>selected</#if>><!-- 等待 --><@spring.message "WaitFor"/></option>							
					<option value="2" <#if _orderState==2>selected</#if>><!--处理中--><@spring.message "InTreatment"/></option>	
				</select>
			</div>

			<div class="ddcxf"><!--订单号--><@spring.message "orderCode"/>：</div>
			<div class="cxtjinput">
				<input type="text" id="_orderId" name="_orderId" value="${_orderId}" onchange="checkNum('_orderId')">
			</div>

			<div class="ddcxf"><!--景序列号--><@spring.message "sceneid"/>：</div>
			<div class="cxtjinput">
				<input type="text" id="_sceneId"  name="_sceneId" value="${_sceneId}" onchange="checkNum('_sceneId')">
			</div>
			<div class=" clear"></div>

			<div class="ddcxf"><!--订购时间开始--><@spring.message "OrderTime"/>：</div>
			<div class="cxtjinput01">
				<input type="text" id="orderStartTime" name ="orderStartTime" value="${orderStartTime}" onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" />
			</div>
			
			<div class="cxtjicon"><!--订购时间结束--><@spring.message "to"/></div>
			<div class="cxtjinput01">
				<input type="text" id="orderEndTime" name ="orderEndTime" value="${orderEndTime}" onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" />
			</div>

			<div class="ddcxf"><!--审核时间开始--><@spring.message "CheckTime"/>：</div>
			<div class="cxtjinput01">
				<input type="text" id="checkStartTime" name ="checkStartTime" value="${checkStartTime}" onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" />
			</div>
			
			<div class="cxtjicon"><!--审核时间结束--><@spring.message "to"/></div>
			<div class="cxtjinput01">
				<input type="text" id="checkEndTime" name ="checkEndTime" value="${checkEndTime}" onclick="WdatePicker({dataFmt:'yyyy-MM-dd',lang:'<@spring.message "lang"/>'});" />
			</div>
			
			<div class="ddcxf"><!--是否故障订单--><@spring.message "orderisfault"/>：</div>
			<div class="ddcxinput">
				<select id="_isfault" name="_isfault">
					<option value="-1" selected="selected"><@spring.message "choose"/></option>
					<option value="0" <#if isfaultsearch?? && isfaultsearch==0>selected</#if>><!--完成--><@spring.message "yes"/></option>
					<option value="1" <#if isfaultsearch?? && isfaultsearch==1>selected</#if>><!--失败--><@spring.message "no"/></option>							
					
				</select>
			</div>
			<div class="clear"></div>
			<div class="bcqx">
				<a id="searchMatch"><!--搜索--><@spring.message 'Search'/></a>
				<a href="javascript:reset();" ><!--重置--><@spring.message 'reset'/></a>
			</div>
			
			<!--查询条件结束-->
			
		</div>

		<div class="ggcicon">
			<!--我的订单--><@spring.message 'MyOrder'/>（<a href="#">${totalInfo.totalCount}</a>）
		</div>

			<div class="ddcxinput">
				<select id="SortBySearch" name="SortBySearch">
					<option value="-1" selected="selected"><@spring.message "choose"/></option>
					<option value="orderid" <#if sortBySearch?? && sortBySearch=='orderid'>selected</#if>>订单号</option>
					<option value="tasktime" <#if sortBySearch?? && sortBySearch=='tasktime'>selected</#if>>订单日期</option>							
					<option value="orderstate" <#if sortBySearch?? && sortBySearch=='orderstate'>selected</#if>>订单状态</option>
					<option value="checkstate" <#if sortBySearch?? && sortBySearch=='checkstate'>selected</#if>>审核状态</option>
					<option value="username" <#if sortBySearch?? && sortBySearch=='username'>selected</#if>>用户名</option>
				
				</select>
				
			</div>
			<input type="button" id="orderSortby" value="<@spring.message 'orderby'/>" style="margin-top: 25px;">
			</form>
		<div class="wddd_r">
			<ul>
				<li>
					<a id="coverage"><!--覆盖显示--><@spring.message "Coverage"/></a>
				</li>
				<li>
					<a href="javascript:orderExcel('shp');"><!--导出shape文件--><@spring.message 'Export'/> Shape</a>
				</li>
				<li>
					<a href="javascript:orderExcel('xls');"><!--导出Excel--><@spring.message 'Export'/> Excel</a>
				</li>
				<li>
					<a href="javascript:orderExcel('csv');"><!--导出CSV--><@spring.message 'Export'/> CSV</a>
				</li>
				<li>
					<a href="javascript:orderExcel('xml');"><!--导出CSV--><@spring.message 'Export'/> XML</a>
				</li>
			</ul>
		</div>
		<div class="clear"></div>


		<table width="1099" border="0" cellspacing="0" cellpadding="0"
			class="ddtable">
			<!--订单信息table》thead标题开始-->
			<tr class="ddtr">
				<td width="3%" height="40" align="center" bgcolor="#f6f6f6">
			  	</td>

				<td width="25%" height="40" align="center" bgcolor="#f6f6f6"><!-- 商品信息 --><@spring.message "ProductInfo"/></td>
				<td width="5%" height="40" align="center" bgcolor="#f6f6f6"><!-- 订单类型 --><@spring.message "OrderType"/></td>
				<td width="5%" height="40" align="center" bgcolor="#f6f6f6"><!-- 审核状态 --><@spring.message "auditstate"/></td>
				<td width="5%" height="40" align="center" bgcolor="#f6f6f6"><!-- 处理状态 --><@spring.message "TreatmentStatus"/></td>
				<td width="5%" height="40" align="center" bgcolor="#f6f6f6"><!-- 订单步骤 --><@spring.message "TaskStep"/></td>
				<td width="5%" height="40" align="center" bgcolor="#f6f6f6"><!-- 地址 --><@spring.message "Site"/></td>
				<td width="5%" height="40" align="center" bgcolor="#f6f6f6"><!-- 是否支付 --><@spring.message "FriendlyTips"/></td>
				<td width="5%" height="40" align="center" bgcolor="#f6f6f6"><!-- 是否支付 --><@spring.message "WhetherToPay"/></td>
				<td width="5%" height="40" align="center" bgcolor="#f6f6f6"><!-- 操作 --><@spring.message "operation"/></td>

			</tr>
			<!-- 序号变量声明 -->
			<#assign num = totalInfo.startNum>
			<!--订单信息table》thead标题结束-->
			<#if orderMainList?? && (orderMainList?size > 0)>
				<!--主订单信息数据遍历开始-->
		    	<#list orderMainList as orderMain>
			    	<!--主订单信息数据填充开始-->
					<tr class="ddmc dylan">
						<td height="40" colspan="10" align="left" bgcolor="#eaf2ff" onclick="TestBlack('${orderMain.orderMainId}');">
							<div class="ddjt" >
								<img src="imgnew/icon23.png" id="img${orderMain.orderMainId}" width="6" height="11" />
							</div> 
							<input type="checkbox" id="che_${orderMain.orderMainId}" onclick="checkAll('${orderMain.orderMainId}')" />
							<!-- 序号 -->
							<#assign num = num + 1>
							<b>${num }</b>&nbsp;
							<!-- 订单名称 -->
							<@spring.message "OrderName"/>：${orderMain.orderName}
							<!-- 子订单 -->
							<@spring.message "SubOrder"/>：${orderMain.count}<@spring.message "strip"/>
							<!-- 订购时间 -->
							<@spring.message "OrderTime"/>：${orderMain.orderTime?string("yyyy-MM-dd HH:mm:ss")}
							<!-- 备注 -->
							<@spring.message "Remark"/>：${orderMain.remark}
							<@spring.message "price"/>：${orderMain.price}${orderMain.uuit}
							<#if orderMain.ispay==1>
						<a onclick="showpayinfo('${orderMain.orderMainId }')"><@spring.message "queryPrice"/></a>
						<#else>
						<a onclick="payinfo('${orderMain.orderMainId }')"><@spring.message "insertPrice"/></a>
						</#if>
						<#if orderMain.isrefund==0>
							<a onclick="showrefundinfo('${orderMain.orderMainId }')"><@spring.message "insertPrice"/></a>
						</#if>
						
						</td>
						
					</tr>
					<!--主订单信息数据填充结束-->
			    	<#if orderMain.orderInfoList?? && (orderMain.orderInfoList?size > 0)>
	    				<!--子订单信息数据遍历开始-->
				    	<#list orderMain.orderInfoList as order>
			<tr id="" style="display:none" idflag="${orderMain.orderMainId}" class="ddnr dylan ${orderMain.orderMainId}">
				<td height="60"  colspan="2" bgcolor="#ffffff" width="25%">
					<div class="ddinput">
						<input type="checkbox" name="checkbox" cheFlag="${orderMain.orderMainId}" value="${order.orderid}_${order.productlevel}" />
					</div>
					<div class="ddimg">
						<img src="imgnew/img06.gif" width="87" height="87" />
					</div>
					<div class="ddfont">
						<!--产品类型--><@spring.message "productcate"/>：<#if order.productcate==1><@spring.message "dataproduct"/></#if><#if order.productcate==2><@spring.message "metaproduct"/></#if><br />
						<!--订单号--><a href="${ctx }/showOrderinfoByid.do?orderid=${order.orderid}" target="_Blank"><@spring.message "orderid"/>：${order.orderid}</a><br /> 
						<!--卫星--><@spring.message "satellite"/>：${order.satelliteid}<br /> 
						<!--传感器--><@spring.message "sensorid"/>：${order.sensorid}<br />
						
					</div>
					<div class="ddfont" style="display: inline-block; ">
						<!--景序列号--><@spring.message "sceneid"/>：<a href="${ctx }/showOrderinfoBysceneid.do?orderid=${order.orderid}" target="_Blank">${order.sceneid}</a><br/>
						<!--产品类型--><@spring.message "ProductType"/>：${order.producttype}<br /> 
						<!--产品级别--><#if order.productcate==1><@spring.message "productlevel"/>：${order.productlevel} <br /> </#if>
						<!--优先级--><@spring.message "priority"/>：${order.priority }
					</div></td>
				<td height="60" bgcolor="#ffffff" width="5%">
					<!--订单类型-->
					<#if order.tasktype==1>
						<@spring.message "ProductionOrder"/>
					<#else>
						<@spring.message "ProductOrder"/>
					</#if>
				</td>
				<td height="60" bgcolor="#ffffff">
					<!--审核状态-->
					<#if order.checkstate==1>
						<!-- 等待 --><@spring.message "WaitFor"/>
					</#if>
					<#if order.checkstate==2>
						<!-- 通过 --><@spring.message "PassThrough"/>
					</#if>
					<#if order.checkstate==3>
						<!-- 未通过 --><@spring.message "NotThrough"/>
					</#if>
				</td>
				<td height="60" bgcolor="#ffffff">
					<!--处理状态-->
					<#if order.orderstate==1>
						<!--完成--><@spring.message "Complete"/>
					</#if>
					<#if order.orderstate==2>
						<!--失败--><@spring.message "Fail"/>
					</#if>
					<#if order.orderstate==3>
						<!--取消--><@spring.message "Cancel"/>
					</#if>
					<#if order.orderstate==4||order.orderstate="">
						<!-- 等待 --><@spring.message "WaitFor"/>
					</#if>
					<#if order.orderstate==5>
						<!--处理中--><@spring.message "InTreatment"/>
					</#if>
				</td>
				<td height="60" bgcolor="#ffffff">
					<div class="help-tip">
						<!--订单步骤-->
						<#if order.tasktype==1><!-- 判断订单类型-->
							<!-- 生产订单 步骤 -->
							<@spring.message "${productionOrderProcedure[(order.taskSteptime-1)]}"/>
							
						<#else>
							<!-- 产品订单 步骤 -->
							<@spring.message "${productOrderProcedure[(order.taskSteptime-1)]}"/>
						</#if>
						<!-- 因暂时找不到数据来源,故此段代码中的数据为样例数据 -->
						<div class="tcbox">
							<div class="tctb">
								<div class="tczt">步骤</div>
								<div class="tcsj">状态</div>
							</div>
							<#if order.tasktype==1><!-- 判断订单类型-->
								<!-- 生产订单 步骤 -->
								<#list productionOrderProcedure as productionProcedure>
									<div class="tcnrbg" style="">
										<div class="tczt"><@spring.message "${productionProcedure}"/></div>
										<div class="tcsj"><span style="color: green;">
											<#if (productionProcedure_index < order.taskSteptime)>✔</#if>
										</span></div>
									</div>
								</#list>
							<#else>
								<!-- 产品订单 步骤 -->
								<#list productOrderProcedure as productProcedure>
									<div class="tcnrbg">
										<div class="tczt" style=""><@spring.message "${productProcedure}"/></div>
										<div class="tcsj"><span style="color: green;">
											<#if (productProcedure_index < order.taskSteptime)>✔</#if>
										</span></div>
									</div>
								</#list>
							</#if>
						</div>
						<!-- 因暂时找不到数据来源,故此段代码中的数据为样例数据 -->
					</div>
				</td>
				<td height="60" align="center" bgcolor="#ffffff">
					<!--地址-->
					
					<#if order.orderstate==1 && order.ftpurl != ""
					&& order.productuploaddir !="">
						<a href="${order.ftpurl}${order.productuploaddir}" target="blank">
							下载
						</a>
					</#if>
				</td>
				<td  height="60" align="center" bgcolor="#ffffff">
					
					<#if order.isfault==0>
							<!-- 是 -->${order.friendlyprompt }
					<#else>
							<!-- 否 -->
					</#if>
				</td>
				<td height="60" align="center" bgcolor="#ffffff">
					<!--是否支付-->
					<#if order.ispay==1>
							<!-- 是 --><@spring.message "yes"/>
					</#if>
					<#if order.ispay==0>
							<!-- 否 --><@spring.message "no"/>
					</#if>
					<#if order.ispay==2>
							<!-- 否 -->免费
					</#if>
				</td>
				<td height="60" align="center" bgcolor="#ffffff" style="border-right-style:none"><div class="ddcx">
						<a href="javascript:addorderinfo('${order.orderid}')"><!--重新订购--><@spring.message "ReOrder"/></a>
					<#if (order.orderTracking)??>
						<a style="margin-top: 1px; background-color: gray;" ><!-- 已跟踪 --><@spring.message 'AlreadyTrack'/></a>
					<#else>
						<a href="javascript:openOrderTracking('${order.orderid}')" style="margin-top: 1px; background-color: green;"><!-- 订单跟踪 --><@spring.message 'Ordertracking'/></a>
					</#if>
					<#if order.order!=1&&order.orderstate!=3>
						<a onclick="cacleOrder('${order.orderid}','3')"><@spring.message "cancelOrder"/></a>
						</#if>
						<#if order.orderstate==3>
						<a onclick="cacleOrder('${order.orderid}','4')"><@spring.message "recoveryOrder"/></a>
						</#if>
					</div>
				</td>
			</tr>
						</#list>
						<!--子订单信息数据遍历结束-->
					<#else>
					</#if>
					
				</#list>
				<!--主订单信息数据遍历结束-->
			<#else>
			</#if>



<!-- 列表分页模块开始 start -->
	<tr style="background-color: rgb(246, 246, 246);">
        <td colspan="9" style="background-color: rgb(255, 255, 255);">
       		 <div class="span6"><div class="dataTables_info" id="sample_1_info"></div></div>
       		 <div class="span6">
			<div class="dataTables_paginate paging_bootstrap pagination">
			
				<ul>
				     <#if (totalInfo.totalCount > 0)>
				      <#if (totalInfo.currentPage > 1)>
				      	<li class="prev disabled">
				      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value="<@spring.message "previous"/>" >
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
				        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+1}" value="<@spring.message "next"/>" /></li>
				      </#if>
				      <input type="hidden" id="pageTotal" value="${totalInfo.pageTotal}">
				     </#if>
				    </div>
				  </div>
				</ul>
				
		    </div>
		  </div>
        </td>
    </tr>
<!-- 列表分页模块结束 end -->


		</table>  


		<div class="clear"></div>


<!--
		<div class="page">
			每页显示<span>10</span>条<a href="#">首页</a><a href="#">1</a><a href="#">2</a><a
				href="#">3</a><a href="#">4</a><a href="#">尾页</a>
		</div>
-->

<!-- 追加"订单跟踪"信息窗口 start -->
<div class="row-fluid"  id='addInfoWindow'  style="display:none">
			<div class="portlet-body form">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id="changeForm">
						<input type="hidden" value="" id='change_id'  name='id'>
							<div class="control-group">
								<label class="control-label"><!--订单号--><@spring.message "orderCode"/></label>
								<div class="controls">
									<input type="text"  class="m-wrap medium"  id='orderid' name='orderid' style="height: 35px;" readonly />
									<span class="help-inline" id='orderid_error'></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><!-- 订单跟踪 --><@spring.message 'Ordertracking'/></label>
								<div class="controls">
									<textarea class="form-control" rows="3" id="orderTracking" name='orderTracking'></textarea>
									<span class="help-inline"  id='orderTracking_error'></span>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn blue" onclick="addOrderTracking();"><i class="icon-ok"></i><@spring.message "save"/></button>
								<button type="button" class="btn blue" onclick="closeOrderTracking();"><@spring.message "cancle"/></button>
							</div>
						</form>
				</div>
	 		</div>
</div>
<!-- 追加"订单跟踪"信息窗口 end -->





	</div>

<#include "foot.ftl">

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.i18n.properties-1.0.9.js"></script>
