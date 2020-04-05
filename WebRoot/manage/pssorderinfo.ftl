<#include "top.ftl">

<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
				<div class="main-hd">
				<form  class="form-horizontal"  id="searchForm" action="${ctx}${currentPageUrl}" method="POST">
									<input type="hidden" value="" id="pageNumInput"  name='pageNumInput' >
		                              		<@spring.message "Payment_status"/>:           
		                              <select class="form-control" id="orgNameSearch" name="ispaySearch"  >
									      	<option value="-1" selected="selected"><@spring.message "logchoose"/></option>
										   	 <option value="1" <#if ispaySearch?? && ispaySearch==1>selected</#if>><@spring.message "Not_paid"/></option>
										   	 <option value="2" <#if ispaySearch?? && ispaySearch==2>selected</#if>><@spring.message "Already_paid"/></option>
										   	
										</select> 
										   <@spring.message "Audit_status"/>:
		                            	    <select class="form-control" id="orgNameSearch" name="checkstateSearch"  >
									      	<option value="-1" selected="selected"><@spring.message "logchoose"/></option>
										   	 <option value="1" <#if checkstateSearch?? && checkstateSearch==1>selected</#if>><@spring.message "Waitfor_audit"/></option>
										   	 <option value="2" <#if checkstateSearch?? && checkstateSearch==2>selected</#if>><@spring.message "Audit_pass"/></option>
										   	 <option value="3" <#if checkstateSearch?? && checkstateSearch==3>selected</#if>><@spring.message "Audit_failure"/></option>
										</select> 
										
		                             	  <@spring.message "productlevel"/>:
		                             	  <select class="form-control" id="productlevelSearch" name="productlevelSearch"  >
									      <option value="-1" selected="selected"><@spring.message "logchoose"/></option>
										  <#if productlevelList?? && (productlevelList?size > 0)>
							                <#list productlevelList as productlevel>
										   	 <option value="${productlevel.productlevel}" <#if productlevelSearch?? && productlevelSearch=productlevel.productlevel>selected</#if>>${productlevel.productlevel}</option>
											</#list>
										  </#if>
										</select>
	                                  	<br>
		                            	
										&nbsp;&nbsp;&nbsp;<@spring.message "orderid"/>:
		                                <input type="text" value="<#if orderidInput??>${orderidInput}</#if>" id="orderidInput" name="orderidInput">
		                               	<!-- 传感器:
		                               	   <select class="form-control" id="satelliteidSearch" name="satelliteidSearch"  >
									      <option value="-1" selected="selected">请选择</option>
										  <#if orgList?? && (orgList?size > 0)>
							                <#list orgList as orgInfo>
										   	 <option value="${orgInfo.id}" <#if orgId?? && orgId=orgInfo.id>selected</#if>>${orgInfo.orgName}</option>
											</#list>
										  </#if>
										</select> -->
										<@spring.message "userName"/>:
										<input type="text" value="<#if usernameInput??>${usernameInput}</#if>" id="usernameInput"  name="usernameInput" >
										 <@spring.message "tasktime"/>:
										<input type="text" id="starttime" name ="starttime" value="${Starttime}" style="width:112px" class="form-control Wdate" onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
			                           <input type="text" id="endtime" name ="endtime"value="${enttime}" style="width:112px" class="form-control Wdate" onclick="WdatePicker({dataFmt:'yyyy-MM-dd'});"/>
									
										<@spring.message "Order_type"/>:
										<select class="form-control" id="tasktypeSearch" name="tasktypeSearch"  >
									      <option value="-1" selected="selected"><@spring.message "logchoose"/></option>
										
										<br>
										
										
										
			                           &nbsp; <input  type="button" class="btn btcms" id="searchMatch" value="<@spring.message 'search'/>" onclick="searchMatch()">
			                            <input  type="button" class="btn btcms" id="searchAll" value="<@spring.message 'configsearchall'/>">
									</form>
			</div>
			
			<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box blue">
							<div class="portlet-body">
								<div class="clearfix">
									<div class="btn-group">
										&nbsp;
										<button  class="btn btcms" id="checkorderState">
										<i class="fa fa-plus"></i>&nbsp;<@spring.message "audit"/>
										</button>
									</div>
									
								</div>
				
				
				<!-- BEGIN PAGE CONTENT-->
				
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                               		<td width="3%"><input type="checkbox" id="allcheck"></td>
	                                <td width="5%"><@spring.message "orderid"/></td>
							        <td width="5%"><@spring.message "Order_type"/></td>
							        <td width="5%"><@spring.message "userName"/></td>
							        <td width="5%"><@spring.message "satellite"/></td>
							        <td width="6%"><@spring.message "orderType"/></td>
							        <td width="6%"><@spring.message "productlevel"/></td>
							        <td width="10%"><@spring.message "tasktime"/></td>
							        <td width="5%"><@spring.message "Payment_status"/></td>
							        <td width="5%"><@spring.message "Audit_status"/></td>
							        <td width="5%"><@spring.message "Treatment_status"/></td>
							        <td width="5%"><@spring.message "download"/></td>
							         <td width="5%"><@spring.message "newoption"/></td>
                                </tr>
                            </thead>
                            <tbody>
                                <#if orderList?? && (orderList?size > 0)>
				             
				                <#list orderList as order>
				               
				                <tr> 
				                <td class="botValue">
				                <input type="checkbox" name="subcheck" id="huoquzhi" value="${order.username}-${order.orderid}"></td> 
							    <td class="botValue">${order.orderid}</td>
							      <#if order.tasktype==1>
							       <td class="botValue"><@spring.message "Production_order"/></td>
							        <#else>
							       <td class="botValue"><@spring.message "Not_produced"/></td>
							      </#if>
							      <td  class="botValue">${order.username}</td>
							      <td class="botValue">${order.satelliteid}</td>
							      <td class="botValue">${order.orderstate}</td>
							      <td class="botValue">${order.productlevel}</td>
							 		<td class="botValue">${order.tasktime}</td>
							 		  <#if order.ispay==0 || order.ispay="">
							       <td class="botValue"><@spring.message "Not_paid"/></td>
							        </#if>
							       <#if order.ispay==1>
							       <td class="botValue"><@spring.message "Already_paid"/></td>
							        </#if>
							        
							 		<#if order.checkstate==1>
							       <td class="botValue"><@spring.message "Waitfor_audit"/></td>
							        </#if>
							       <#if order.checkstate==2>
							       <td class="botValue"><@spring.message "Audit_pass"/></td>
							        </#if>
							        <#if order.checkstate==3>
							       <td class="botValue"><@spring.message "report.order.orderstate.chuling"/></td>
							      </#if>
							      <#if order.checkstate="">
							      <td></td>
							      </#if>
							      <td class="botValue"><@spring.message "Audit_failure"/></td>
							      <td class="botValue"><@spring.message "treatmenting"/></td>
							      <td class="botValue">
							       <a class="btn btcms" onclick="showpssorderinfo('${order.orderid}')"><i class="fa fa-train"></i>&nbsp;<@spring.message "logview"/></a>
							        
							      </td>
							    </tr>
							    </#list>
				              <#else>
				                <tr>  
							      <td class="botValue" colspan="6" style="padding:10px 0;"><@spring.message "nomore"/></td>
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
											      	<input class="btn blue" type="button"   id="pageTurn${totalInfo.currentPage-1}" value="<@spring.message 'logup'/>" >
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
											        <input class="btn blue" type="button" id="pageTurn${totalInfo.currentPage+1}" value="<@spring.message 'logdown'/>" /></li>
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
        




<!-- 用户信息展示窗口 start -->
<div class="row-fluid"  id='showpssorderinfo'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal">
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "Order_type"/></label>
								<label class="control-label"  id='order_tasktype'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "userName"/></label>
								<label class="control-label"  id='order_username'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "satellite"/></label>
								<label class="control-label"  id='order_satelliteid'></label>
							</div>
							
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "orderType"/></label>
								<label class="control-label"  id='order_orderstate'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "Payment_status"/></label>
								<label class="control-label"  id='order_ispay'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "productlevel"/></label>
								<label class="control-label"  id='order_productlevel'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "tasktime"/></label>
								<label class="control-label"  id='order_tasktime'></label>
							</div>
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "Audit_status"/></label>
								<label class="control-label"  id='order_checkstate'></label>
							</div>
						
							<div class="form-actions ">
								<button type="button" class="btn blue" id='showcancleAdd'><@spring.message "newcancel"/></button>
							</div>
						</form>
				</div>
</div>
<!-- 用户信息展示窗口 end -->
<!-- 常量信息展示窗口 end -->


<!-- 订单审核窗口 and -->
<div class="row-fluid"  id='orderStateWin'  style="display:none">
					<div class="tab-content">
						<form action="#" class="form-horizontal" id="orderForm">
							<div class="control-group form_lab_div">
								<label class="control-label"><@spring.message "Order_information"/></label>
								<label class="control-label" id='orderID'></label>
								<input type="hidden" id="order_id" name='ordermath'>
							</div>
							<div class="control-group form_lab_div">
									<label class="control-label"><@spring.message "checknote"/></label>
									<input type="text" id="note" name='note' />
							</div>
							<div class="form-actions ">
								<button type="button" class="btn blue" onclick='updateCheckState("2")'><@spring.message "Audit_pass"/></button>
								<button type="button" class="btn blue" onclick='updateCheckState("3")'><@spring.message "Audit_failure"/></button>
								<button type="button" class="btn blue" onclick='stateCancle()'><@spring.message "newcancel"/></button>
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
